package discordbot.commands;

import discordbot.Command;
import discordbot.Main;
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
		if (event.getMember() == null)
			IO.out("You can't shutdown the bot from private chat.\nDo it somewhere where you can prove your permissions.");

		if (Main.players.containsKey(event.getMember().getUser().getId())
				&& Main.players.get(event.getMember().getUser().getId()).isAdmin()) {
			IO.out2("RIP ME :(");
			System.out.println("Saving files, shutting down bot...");
			System.exit(0);
		}else{
			IO.out2("You have no powere here! ... Beep Boop");
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
