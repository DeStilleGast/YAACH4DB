package com.DeStilleGast.YAACH4DB.Internal;

import com.DeStilleGast.YAACH4DB.MessageReceivedEventWrapper;

/**
 * Created by DeStilleGast on 10-1-2018.
 */
public interface ISimpleCommand {
    String command();
    String[] aliases();
    String description();
    String usage();

    void execute(MessageReceivedEventWrapper cw);
}
