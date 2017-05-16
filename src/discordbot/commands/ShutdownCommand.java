package discordbot.commands;

import discordbot.Command;
import discordbot.utils.IO;
import discordbot.utils.Utils;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class ShutdownCommand implements Command {
	
	private final String HELP = "USAGE: ~shutdown\t\t\tShuts the bot down immediately and saves all user files. Developers only.";

	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		return true;
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		if (event.getMember() == null) IO.out("You can't shutdown the bot from private chat.\nDo it somewhere where you can prove your permissions.");
		if (Utils.checkRole(event.getMember().getRoles(), new String[]{"Flaming Queen","Bot Developer"})){
			IO.out2("RIP ME :(");
			System.out.println("Saving files, shutting down bot...");
			System.exit(0);		//automatically starts shutdown hook which saves files
		}
		return;
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
