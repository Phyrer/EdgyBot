package discordbot.commands;

import discordbot.Command;
import discordbot.Main;
import discordbot.utils.IO;
import discordbot.utils.PlayerAccount;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class SlotCommand implements Command {
	
	private final String HELP = "USAGE: ~slot to play casually or  ~slot <score multiplier> to multiply the stakes.\t\t\tPlay a slot game using your PPoints (PlagiatPoints).";
	
	PlayerAccount currentPlayer;
	
	/*
	 * PLAYING COSTS 250PP (PlagiatsPunkte)
	 * 
	 * ===== CHANCES =====
	 * Probability	Points
	 * 		25%		   0
	 * 		50%		 200
	 * 		10%		 500
	 * 		 5%		 750
	 * 		 4%		1250
	 * 		 3%		1750
	 * 		 2%		2250
	 * 		 1%		4450
	 */
	
	int cost = 250;
	int riskFactor = 1;
	final int[] wins = new int[]{0,200,500,750,1250,1750,2250,4450};
	final int[] chances = new int[]{90,75,97,25,85,94,99};
	/*
	 * 	SORTING TREE WITH ABOVE WINS' INDEX AS RESULT WITH CHANCES AS DEFINED ABOVE:
	 * 								<90?
	 * 				<75?							<97?
	 * 		<25?			<85?			<94?			<99?
	 * 	 0		 1		 2		 3		 4		 5		 6		 7
	 */
	
	final String[] messages = new String[]{"You suck.", "Haha, you still suck.", "Waow you won something.",
									"Hey, not bad, now you can lose 3 more times.",
									"You're getting there, son.", "No Russian Roulette for you ;)",
									"You rich ass madafaka.", "NOW I'M BROKE ARE YOU HAPPY YOU FUCKING DUMBASS?!?!"};
	int winSum;

	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		return true;
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		new Thread(() -> {
			play(args, event);
		}).start();
	}
	
	synchronized public void play(String[] args, MessageReceivedEvent event){
		String thisID = event.getAuthor().getId();
		if (!Main.players.containsKey(thisID)){				//create new account if none existent
			Main.players.put(thisID, new PlayerAccount(event.getMember()));
		}
		
		currentPlayer = Main.players.get(thisID);	//fetch player from registered players
		
		if (args.length > 0) riskFactor = Integer.parseInt(args[0]);
		else riskFactor = 1;
		
		if (currentPlayer.getPpScore()< cost * riskFactor){
			IO.out("Player " + currentPlayer.getPlayerName() + " doesn't have enough PPoints!");
			return;
		}
		
		int winCat = calcWinCategory(currentPlayer.getPpScore(),IO.rand.nextInt(100));
		
		winSum = (wins[winCat] - cost)*riskFactor;
		currentPlayer.setPpScore(currentPlayer.getPpScore() + winSum);
		IO.out("Player " + currentPlayer.getPlayerName() + (winSum < 0? " lost " : " won ") + winSum + " PPoints!\n"
				+ messages[winCat] + "\nYou have " + currentPlayer.getPpScore() + " PPoints left.");
	}
	
	/**
	 * === DO NOT TOUCH ===
	 * Takes a random number from 0 to 99 and sorts it according to probabilities into outcome IDs (index of wins array).
	 *  SORTING TREE WITH ABOVE WINS' INDEX AS RESULT WITH CHANCES AS DEFINED ABOVE:
	 * 								<90?
	 * 				<75?							<97?
	 * 		<25?			<85?			<94?			<99?
	 * 	 0		 1		 2		 3		 4		 5		 6		 7
	 * @param rand
	 * @return winCategory
	 */
	public int calcWinCategory(long playerPP, int rand){
		rand -= playerPP/250000;	//Every 250k you lose 1% of your luck
		if (playerPP < 1000) rand += (1000-playerPP)/100;	//Gives you a 7 point boost max
		return (rand < chances[0]? (rand < chances[1]? (rand < chances[3]? 0 : 1):(rand < chances[4]? 2 : 3))
				:(rand < chances[2]? (rand < chances[5]? 4 : 5) : (rand < chances[6]? 6 : 7)));
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
