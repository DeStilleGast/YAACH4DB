package com.DeStilleGast.YAACH4DB.Interfaces;

import com.DeStilleGast.YAACH4DB.MessageReceivedEventWrapper;

/**
 * Created by DeStilleGast on 10-2-2018.
 */
public interface ICommandHandlerConfig {

    // The prefix that we are going to use
    String prefix();

    // The logger for the command handler
    ILogger logger();

    // Incase you want a message if the command wasn't found
    void onUnknownCommand(MessageReceivedEventWrapper event);
}
