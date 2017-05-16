package discordbot.utils;

import java.util.Random;

import discordbot.communication.Sets;
import net.dv8tion.jda.core.exceptions.RateLimitedException;

public class IO {
	
	public static Random rand = new Random();
	
	public static void processInput(String input){
		if (Sets.greetings.contains(input)){
			out(Sets.greetings.get(rand.nextInt(Sets.greetings.size())));
		}
	}
	
	public static void sendMessage(String msg){			//queues into main channel no matter what, may lag
		Bot.currentChannel.sendMessage(msg).queue();
	}
	
	public static void out2(String msg){		//just a short form of sendMessage
		sendMessage(msg);
	}
	
	public static void out(String msg){			//automatically switches to private channel when spammed
		try{
			Bot.currentChannel.sendMessage(msg).complete(false);
		} catch (RateLimitedException e){
			msg = msg + "\n\n```---This message was sent here because the main channel got spammed too much.---```";
			Bot.currentEvent.getAuthor().openPrivateChannel().complete();
			Bot.currentEvent.getAuthor().getPrivateChannel().sendMessage(msg).queue();
		}
	}
}
