package discordbot;

import discordbot.utils.Bot;
import discordbot.utils.IO;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class BotListener extends ListenerAdapter{
	
	@Override
	public void onMessageReceived(MessageReceivedEvent event){
		
		Bot.currentEvent = event;
		Bot.currentChannel = event.getChannel();
		
		//COMMAND FUNCTIONS
		if(event.getMessage().getContent().startsWith("~") && event.getMessage().getAuthor().getId() != event.getJDA().getSelfUser().getId()){
			Main.handleCommand(Main.parser.parse(event.getMessage().getContent(), event));
		} else if (event.getMessage().getAuthor().getId() != event.getJDA().getSelfUser().getId()){
			//CHAT FUNCTIONS
			event.getMessage().addReaction("\uD83C\uDF46").queue();
			System.out.println("Received chat input: " + event.getMessage().getRawContent());
			IO.processInput(event.getMessage().getRawContent().toLowerCase());
		}
		
	}
	
	@Override
	public void onReady(ReadyEvent event){
		//TODO: this is the log
		//Main.log("status", "Logged in as: " + event.getJDA().getSelfUser().getName());
	}
	
}
