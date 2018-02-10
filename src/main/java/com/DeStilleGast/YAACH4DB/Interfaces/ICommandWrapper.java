package com.DeStilleGast.YAACH4DB.Interfaces;

import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.User;

/**
 * Created by DeStilleGast on 10-2-2018.
 */
public interface ICommandWrapper {
    void reply(String message);

    void reply(MessageEmbed message);

    User getAuthor();

    String[] getArgs();

    String getUsedAlias();

    //Try to get argument, otherwise
    String getArgumentOr(int index, String other);
}
