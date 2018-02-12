package com.DeStilleGast.YAACH4DB.Interfaces;

import com.DeStilleGast.YAACH4DB.Internal.SimpleLogger;
import com.DeStilleGast.YAACH4DB.MessageReceivedEventWrapper;

/**
 * Created by DeStilleGast on 10-2-2018.
 */
public interface ICommandHandlerConfig {

    /** The prefix that we are going to use */
    String prefix();

    /** The logger for the command handler */
    default ILogger logger() { return new SimpleLogger(); }

    /** Incase you want a message if the command wasn't found */
    default void onUnknownCommand(MessageReceivedEventWrapper event){}

    /**
     * If you don't want to use categories, its fine, you can turn the enforcer off
     * if set to false categories may be set to null
     */
    default boolean forceCategories() { return true; }
}
