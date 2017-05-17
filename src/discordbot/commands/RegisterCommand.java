package discordbot.commands;

import discordbot.Command;
import discordbot.Main;
import discordbot.utils.Bot;
import discordbot.utils.IO;
import discordbot.utils.PlayerAccount;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class RegisterCommand implements Command {
	
	private final String HELP = "USAGE: ~register\t\t\tRegisters you as player if you aren't already registered.";

	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		return true;
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		if (!Main.players.containsKey(event.getMember().getUser().getId())){	//create new account
			Main.players.put(event.getMember().getUser().getId(), new PlayerAccount(event.getMember()));
			IO.out("You have created a player account! From now on, your palenta will generate PlagiatsPunkte for you.");

			Main.savePlayerAccounts(Bot.PLAYERS_FILE);
		} else IO.out("You are already registered. Tipp: Playing ~slot auto-registers you!");
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
