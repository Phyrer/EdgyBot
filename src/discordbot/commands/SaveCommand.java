package discordbot.commands;

import discordbot.Command;
import discordbot.Main;
import discordbot.utils.Bot;
import discordbot.utils.IO;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class SaveCommand implements Command {
	
	private final String HELP = "USAGE: ~save\t\t\tSaves all user-specific data like slot scores to the *.txt file.";

	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		return true;
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		Main.savePlayerAccounts(Bot.PLAYERS_FILE);
		IO.out("The player stats were successfully saved.");
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
