package com.DeStilleGast.YAACH4DB;

import com.DeStilleGast.YAACH4DB.Interfaces.ICommandWrapper;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

/**
 * Created by DeStilleGast on 17-1-2018.
 */
public class CommandWrapper extends MessageReceivedEvent implements ICommandWrapper {

    private String usedAlias;
    private String[] args;

    public CommandWrapper(JDA api, long responseNumber, Message message, String usedAlias, String[] args) {
        super(api, responseNumber, message);

        this.usedAlias = usedAlias;
        this.args = args;
    }

    public void reply(String message){ this.getTextChannel().sendMessage(message).queue(); }

    public void reply(MessageEmbed message){ this.getTextChannel().sendMessage(message).queue(); }

    @Override
    public User getAuthor(){ return this.getMessage().getAuthor(); }

    @Override
    public String[] getArgs() {
        return args;
    }

    @Override
    public String getUsedAlias() {
        return usedAlias;
    }

    //Try to get argument, otherwise
    @Override
    public String getArgumentOr(int index, String other){
        if(args.length > index){
            return args[index];
        }

        return other;
    }

}
