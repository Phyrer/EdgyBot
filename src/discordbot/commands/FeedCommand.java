package discordbot.commands;

import discordbot.Command;
import discordbot.Main;
import discordbot.utils.IO;
import discordbot.utils.PlayerAccount;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class FeedCommand implements Command {
	
	private final String HELP = "USAGE: ~feed <Amount>\t\t\tFeed your palenta with your collected PPoints to level her up!";
	
	int amount = 0;

	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		return true;
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		PlayerAccount owner = Main.players.get(event.getAuthor().getId());
		if (owner == null){
			IO.out("Please quickly register to get your own palenta! Just tyoe ~register in chat, thanks!");
			return;
		}
		
		try{
			if (args.length > 0) amount = Integer.parseInt(args[0]);
			else amount = 0;
		} catch(NumberFormatException e){
			IO.out("You fed her something wrong there... just type in the number like ~feed 100");
			return;
		}
		
		if (amount > owner.getPpScore()){
			IO.out("Player " + owner.getPlayerName() + " doesn't have enough PPoints!\nPPoints: " + owner.getPpScore());
			return;
		}
		owner.getPalenta().feed(amount);
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
