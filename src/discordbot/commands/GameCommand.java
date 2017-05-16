package discordbot.commands;

import discordbot.Command;
import discordbot.utils.IO;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class GameCommand implements Command{
	
	private final String HELP = "USAGE: ~game\t\t\tAttempt to play a game that is currently not implemented.";
	
	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		return true;
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		new Thread(() -> {
			IO.out("Is this a new thread?");
			IO.out("And no, there's no game for you to play yet.");
		}).start();
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
