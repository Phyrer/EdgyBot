package discordbot.commands;

import discordbot.Command;
import discordbot.utils.IO;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class PingCommand implements Command{
	
	private final String HELP = "USAGE: ~ping\t\t\tSimple bot test. Bot will reply with PONG.";

	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		return true;
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		IO.out("PONG!");
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
