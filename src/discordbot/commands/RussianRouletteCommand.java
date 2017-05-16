package discordbot.commands;

import discordbot.Command;
import discordbot.Main;
import discordbot.utils.Bot;
import discordbot.utils.IO;
import discordbot.utils.Palenta;
import discordbot.utils.PlayerAccount;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class RussianRouletteCommand implements Command {
	
	private final String HELP = "USAGE: ~russian\t\t\tWin lots of cash, risking killing your pet.";
	
	PlayerAccount currentPlayer;

	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		return true;
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		String thisID = event.getAuthor().getId();
		
		if (!Main.players.containsKey(thisID)){	//create new account if none exists
			Main.players.put(thisID, new PlayerAccount(event.getMember()));
		}
		
		currentPlayer = Main.players.get(thisID);	//fetch player from registered players
		
		int bullet = IO.rand.nextInt(6);
		if (bullet < 1){
			currentPlayer.setPalenta(new Palenta(currentPlayer)); //kill palenta, create new noob one
			IO.out2("You killed your Palenta. Here's a new Lvl. 1 Palenta. Have fun.");
			//Main.savePlayerAccounts(Bot.PLAYERS_FILE);	//there's no going back
		}
		else {
			int cash = 1000 * currentPlayer.getPalenta().getLvl();
			currentPlayer.setPpScore(currentPlayer.getPpScore() + cash);//win cash
			IO.out("You were damn lucky and won " + cash + " PPoints!");
			Main.savePlayerAccounts(Bot.PLAYERS_FILE);
		}
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
