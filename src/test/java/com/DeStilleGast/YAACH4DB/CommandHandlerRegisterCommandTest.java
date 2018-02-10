package com.DeStilleGast.YAACH4DB;

import com.DeStilleGast.YAACH4DB.Internal.Command;
import com.DeStilleGast.YAACH4DB.Internal.ISimpleCommand;
import com.DeStilleGast.YAACH4DB.Internal.SimpleLogger;
import org.junit.Test;

import static junit.framework.TestCase.assertSame;

/**
 * Created by DeStilleGast on 10-2-2018.
 */
public class CommandHandlerRegisterCommandTest {

    @Test
    public void registerCommand() {
        CommandManager ch = new CommandManager("!", new SimpleLogger());

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
            public void execute(MessageReceivedEventWrapper cw) {

            }
        });

        ch.registerCommand(this);

        assertSame("Command register should have 2 commands registered",
                2, ch.getCommandList().size());
    }

    @Command(name = "Annotation", usage = "this depends on how you made your help command")
    public void annocationTest(MessageReceivedEventWrapper cw){

    }


}