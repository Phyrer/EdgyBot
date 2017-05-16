package discordbot.commands;

import discordbot.Command;
import discordbot.Main;
import discordbot.utils.IO;
import discordbot.utils.PlayerAccount;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class StatsCommand implements Command {
	
	private final String HELP = "USAGE: ~stats\t\t\tDisplays your player stats.";

	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		return true;
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		PlayerAccount player = Main.players.get(event.getAuthor().getId());
		if (player == null){
			IO.out("You need to register for a player account first. Just type ~register in chat.");
			return;
		}
		IO.out(player.getPlayerName() + "'s stats:\n"
				+ "\nPlayer ID: " + player.getID()
				+ "\nPPoints: " + player.getPpScore()
				+ "\nPalenta level: " + player.getPalenta().getLvl());
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
