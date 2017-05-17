package discordbot.utils;

import java.util.TimerTask;

import discordbot.Main;

public class Autosave extends TimerTask {

	@Override
	public void run() {
		Main.savePlayerAccounts(Bot.PLAYERS_FILE);
		System.out.println("Autosaved");
	}

}
