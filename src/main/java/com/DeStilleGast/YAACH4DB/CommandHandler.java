package com.DeStilleGast.YAACH4DB;

import com.DeStilleGast.YAACH4DB.Interfaces.ILogger;
import com.DeStilleGast.YAACH4DB.Internal.Command;
import com.DeStilleGast.YAACH4DB.Internal.ISimpleCommand;
import com.DeStilleGast.YAACH4DB.Internal.SimpleLogger;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by DeStilleGast on 17-1-2018.
 */
public class CommandHandler extends ListenerAdapter {
    private List<ISimpleCommand> commandMap = new ArrayList<>();
    private final ILogger logger;
    private final String prefix;

    public CommandHandler(){ // Go make a simple Command Handler with '!' as prefix and basic logger
        this("!");
    }

    public CommandHandler(String prefix){ // Go make a Command Handler with my prefix and basic logger
        this(prefix, new SimpleLogger());
    }

    public CommandHandler(String prefix, ILogger logger) { // Go make a Command Handler and use my prefix and my logger
        this.prefix = prefix;
        this.logger = logger;
    }

    public void registerCommand(Object object) {
        if (object instanceof ISimpleCommand) {
            registerInterfacedCommand((ISimpleCommand) object);
        } else {
            registerObjectCommands(object);
        }
    }

    private void registerInterfacedCommand(ISimpleCommand simpleCommand) {
        commandMap.add(simpleCommand);
        logger.ok(String.format("Command '%s' was registered !", simpleCommand.command()));
    }

    private void registerObjectCommands(Object object) {
        logger.info("Looking for @Command in [" + object.getClass().getName() + "]...");
        for (Method method : object.getClass().getMethods()) {
            Command cmd = method.getAnnotation(Command.class);
            if (cmd == null) continue;

            logger.info("Attempting to register " + method.getName() + "... ");

            try {
                Method commandMethod = checkCommand(method);

                if (cmd.name().contains(" ") || Arrays.stream(cmd.aliases()).anyMatch(alias -> alias.contains(" "))) {
                    throw new Exception("Command or alias contains a space, this will result in a unusable command !! -> " + cmd.name());
                }

                boolean alreadyExists = commandMap.stream().anyMatch(module -> module.command().equalsIgnoreCase(cmd.name()));
                if (alreadyExists)
                    throw new Exception("There already exists a command named as " + cmd.name());

                alreadyExists = commandMap.stream().anyMatch(module -> Arrays.stream(module.aliases()).anyMatch(alias -> alias.equals(cmd.name())));
                if (alreadyExists)
                    throw new Exception("There already exists a alias command named as " + cmd.name());


                ISimpleCommand convertedCommand = new ISimpleCommand() {
                    @Override
                    public String command() {
                        return cmd.name();
                    }

                    @Override
                    public String[] aliases() {
                        return cmd.aliases();
                    }

                    @Override
                    public String description() {
                        return cmd.description();
                    }

                    @Override
                    public void execute(CommandWrapper cw) {
                        try {
                            method.invoke(object, cw);
                        } catch (InvocationTargetException e) {
                            //TODO: this can happen, this only happens if the command itself crashed
                            logger.error("Command has crashed !!!");
                            logger.error(String.format("'%s' need's to get checked !!", cmd.name()));
                            logger.error("Exception thrown on line: " + e.getTargetException().getStackTrace()[0].getLineNumber());

                            e.printStackTrace();
                        } catch (Exception e) {
                            logger.error("Command handler or command (" + cmd.name() + ") has crashed, stacktrace:");
                            e.printStackTrace();
                        }
                    }


                    @Override
                    public String usage() {
                        return cmd.usage();
                    }
                };

                commandMap.add(convertedCommand);

                logger.ok(String.format("%s registed !!", cmd.name()));
            } catch (Exception ex) {
                logger.error(String.format("Failed to register %s, printstace:", cmd.name()));
                ex.printStackTrace();
            }
        }

        logger.info("Finished looking in " + object.getClass().getName());
    }

    private Method checkCommand(Method m) throws Exception {
        // Check if method is public.
        if (m.getModifiers() != Modifier.PUBLIC) throw new Exception("Method must be public.");

        // Get @Command
        Command cmd = m.getAnnotation(Command.class);
        if (cmd == null) throw new Exception("Missing @Command annotation.");

        // Check if method is valid.
        if (!(m.getReturnType() == void.class)) // You can add some other returnable types
            throw new Exception("Must have void as the return type.");
        if (m.getParameterCount() != 1) throw new Exception("Must have 1 parameter -> CommandWrapper");
        if (!CommandWrapper.class.isAssignableFrom(m.getParameterTypes()[0]))
            throw new Exception("First parameter must be a CommandWrapper or a subclass of it.");

        return m;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String commandLine = event.getMessage().getContentRaw();

        String command = commandLine.substring(0, commandLine.length()).split(" ")[0]; // Used command
        final String[] args = commandLine.substring(command.length(), commandLine.length()).split(" "); // Get all the arguments

        ISimpleCommand resultCommand = null;
        for (ISimpleCommand cmd : this.commandMap) { // Search for command
            if (cmd.command().equalsIgnoreCase(command)) { // Look in command name
                resultCommand = cmd;
                break;
            }

            // Look in the aliases
            if (Arrays.stream(cmd.aliases()).anyMatch(alias -> alias.equalsIgnoreCase(command))) {
                resultCommand = cmd;
                break;
            }
        }

        if (resultCommand != null) {
            CommandWrapper cw = new CommandWrapper(event.getJDA(), event.getResponseNumber(), event.getMessage(), command, args);
            resultCommand.execute(cw);
        }
    }

    public List<ISimpleCommand> getCommandList() {
        return Collections.unmodifiableList(this.commandMap);
    }
}