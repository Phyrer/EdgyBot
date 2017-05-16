package discordbot.commands;

import java.util.Arrays;

import discordbot.Command;
import discordbot.communication.Sets;
import discordbot.utils.IO;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class AddGreetingCommand implements Command {
	
	private final String HELP = "USAGE: ~addGreet <Your greeting>\t\t\tAdd a greeting that the bot will temporarily be able to say.";
	
	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		return true;
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		//TODO: learn all the words
		String newWord = Arrays.toString(args).replaceAll(", |\\[|\\]", " ");	//complicated Regex thingy
		if (!Sets.greetings.contains(newWord)){
			Sets.greetings.add(newWord);
			IO.out("I just learned the word " + newWord + ".\nThanks for teaching me! Hopefully it wasn't evil... >.>");
			System.out.println("The element \"" + newWord + "\" was added to the list of greetings");
		} else{
			IO.out("I already knew that word, sweetheart ;)");
		}
		
	}

	@Override
	public String help() {
		return HELP;
	}

	@Override
	public void executed(boolean success, MessageReceivedEvent event) {
		return;
		
	}

}
