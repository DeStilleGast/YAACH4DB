### Yet A Another Command Handler 4 Discord Bots
So yes, I was bit bored and a friend asked me to make a custom command handler because he did not liked the other frameworks out there.

And here it is, simple, light weight and strict usage.

###How to use this awesome command handler of yours you ask
It is pretty simple, it can look like this:

```java
public class Bot {
    private CommandManager commandHandler = new CommandManager();

    public static void main(String[] args){
        commandHandler.registerCommand(this);
        
        JDA bot = new JDABuilder(AccountType.BOT)
            .setToken("A chocolate cookie with chocolate chunks")
            .addEventListener(commandHandler)
            .buildAsync();
    }
    
    @Command(name = "test", usage = "this depends on how you made your help command")
    public void testCommand(CommandWrapper commandWrapper){
        commandWrapper.reply("This test is working, can I have a cookie now :D");
    }
}
```

but you can also add command with the interface ISimpleCommand and there is everything defined for you

#Please note, this command handler doesn't have a `Unknown command` message, you should be able to add one on your own
maybe later in the future I will maybe add a function to solve that