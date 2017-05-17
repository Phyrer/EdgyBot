package discordbot.commands;

import discordbot.Command;
import discordbot.Main;
import discordbot.utils.IO;
import discordbot.utils.PlayerAccount;
import discordbot.utils.Utils;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class AddPPCommand implements Command {

	private final String HELP = "USAGE: ~addPP <User> <Amount>\t\t\tAdds PPoints to specified account. Admin only.";

	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		return true;
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		// check permissions
		if (Main.players.containsKey(event.getMember().getUser().getId())
				&& Main.players.get(event.getMember().getUser().getId()).isAdmin()) {
			IO.out("No cheating for you.");
			return;
		}
		// checks for arguments
		if (args.length != 2) {
			IO.out("Illegal arguments. Command must be of format ~addPP <User> <Amount>.");
			return;
		}
		int amount;
		try {
			amount = Integer.parseInt(args[1]);
		} catch (NumberFormatException e) {
			IO.out("Invalid amount!");
			return;
		}
		// try to find user
		PlayerAccount target = Main.players.get(args[0]);
		if (target == null) {
			IO.out("There is no user with this ID.");
			return;
		}
		target.setPpScore(target.getPpScore() + amount);
		IO.out("" + amount + "PPoints were added to " + target.getPlayerName() + "'s account.");
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
