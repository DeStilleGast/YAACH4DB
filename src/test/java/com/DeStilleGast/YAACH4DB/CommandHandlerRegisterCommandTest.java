package com.DeStilleGast.YAACH4DB;

import com.DeStilleGast.YAACH4DB.Interfaces.ICommandHandlerConfig;
import com.DeStilleGast.YAACH4DB.Interfaces.ILogger;
import com.DeStilleGast.YAACH4DB.Internal.Command;
import com.DeStilleGast.YAACH4DB.Internal.ISimpleCommand;
import com.DeStilleGast.YAACH4DB.Internal.SimpleLogger;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import org.junit.Test;

import static junit.framework.TestCase.assertSame;

/**
 * Created by DeStilleGast on 10-2-2018.
 */
public class CommandHandlerRegisterCommandTest {

    @Test
    public void registerCommand() {
        CommandManager ch = new CommandManager(new ICommandHandlerConfig() {
            @Override
            public String prefix() {
                return "!";
            }

            @Override
            public ILogger logger() {
                return new SimpleLogger();
            }
        });

        ch.registerCommand(new ISimpleCommand() {
            @Override
            public String command() {
                return "test";
            }

            @Override
            public String[] aliases() {
                return new String[0];
            }

            @Override
            public String description() {
                return "This is a command to test the command register";
            }

            @Override
            public String usage() {
                return "Meh";
            }

            @Override
            public String category() {
                return null;
            }

            @Override
            public void execute(MessageReceivedEventWrapper cw) {

            }
        });

        ch.registerCommand(this);

        assertSame("Command register should have 2 commands registered",
                3, ch.getCommandList().size());

        /*annocationTest1(new MessageReceivedEventWrapper(null, 0, null, "", new String[0]));
        annocationTest1(new MessageReceivedEvent(null, 0, null));

        annocationTest(new MessageReceivedEventWrapper(null, 0, null, "", new String[0]));
        annocationTest(new MessageReceivedEvent(null, 0, null));*/
    }

    @Command(name = "Annotation", usage = "this depends on how you made your help command")
    public void annocationTest(MessageReceivedEventWrapper cw){

    }

    @Command(name = "Annotation1", usage = "this depends on how you made your help command")
    public void annocationTest1(MessageReceivedEvent cw){

    }


}