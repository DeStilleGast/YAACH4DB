# NOTE: This is my first release, names from classes may change
side note: it is one of my ever first releases on github :D
so if you have some idea's tell me in the Issues and I will look at it

### Yet A Another Command Handler 4 Discord Bots
So yes, I was bit bored and a friend asked me to make a custom command handler because he did not liked the other frameworks out there.

And here it is, simple, light weight and strict usage.

### How to use this awesome command handler of yours you ask
It is pretty simple, it can look like this:

```java
public class Bot {
    private CommandManager commandManager = new CommandManager();

    public static void main(String[] args){
        commandManager.registerCommand(this);
        
        JDA bot = new JDABuilder(AccountType.BOT)
            .setToken("A chocolate cookie with chocolate chunks")
            .addEventListener(commandManager)
            .buildAsync();
    }
    
    @Command(name = "test", usage = "this depends on how you made your help command")
    public void testCommand(MessageReceivedEventWrapper event){
        event.reply("This test is working, can I have a cookie now :D");
    }
    
    @Command(name = "toast", usage = "this depends on how you made your help command")
    public void testToast(MessageReceivedEvent event){
        event.getTextChannel().sendMessage(message).queue();
    }
}
```

but you can also add command with the interface ISimpleCommand and there is everything defined for you

### Oke awesome, but how can I use this
well to make it easy, put this in your build file (gradle), it is posible that you already have some settings, just use your brain a bit and combine them :D
```gradle
repositories {
    maven { url "https://jitpack.io" }
}

dependencies {
    compile 'com.github.DeStilleGast:YAACH4DB:v1.0.1'
}
```

# Please note, this command handler doesn't have a `Unknown command` message, you should be able to add one on your own
maybe later in the future I will maybe add a function to solve that

Versions:
initial commit started by: 3.5.0_336 and up, release (v1.0.1) 
