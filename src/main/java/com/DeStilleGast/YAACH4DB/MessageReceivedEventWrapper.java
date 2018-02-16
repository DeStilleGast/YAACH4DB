package com.DeStilleGast.YAACH4DB;

import com.DeStilleGast.YAACH4DB.Interfaces.ICommandWrapper;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DeStilleGast on 17-1-2018.
 */
public class MessageReceivedEventWrapper extends MessageReceivedEvent implements ICommandWrapper {

    private String usedAlias;
    private String[] args;

    public MessageReceivedEventWrapper(JDA api, long responseNumber, Message message, String usedAlias, String[] args) {
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

    /**
     * @param index index from argument array
     * @param other if not found (outside array) return this
     * @return found argument or the @param other
     */
    @Override
    public String getArgumentOr(int index, String other){
        if(args.length > index){
            return args[index];
        }

        return other;
    }

    /**
     * if the startIndex is 0 then this command will return something like this:
     * [you] !getArgs HelloWorld this is a test
     * [bot] HelloWorld this is a test
     *
     * if it is set to 2, it will get something like this:
     * [you] !getArgs HelloWorld this is a test
     * [bot] this is a test
     *
     * note: this summary is a explaining, this is not a real command, well you can make one, but would be useless, except for a say or repeat command
     *
     * @param startIndex index number from the arguments where it should start
     * @return all the arguments after and including the startindex
     */

    @Override
    public String getAllAfterAt(int startIndex) {
        List<String> foundArgs = new ArrayList<>();
        for(int i = startIndex; i < args.length; i++){
                foundArgs.add(args[i]);
        }

        return String.join(" ", foundArgs);
    }
}
