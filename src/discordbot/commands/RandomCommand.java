package discordbot.commands;

import discordbot.Command;
import discordbot.communication.Sets;
import discordbot.utils.IO;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class RandomCommand implements Command{
	
	private final String HELP = "USAGE: ~random\t\t\tSends you random shit that the userbase put together.";

	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		return true;
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		IO.out(Sets.randomStuff.get(IO.rand.nextInt(Sets.randomStuff.size())));
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
