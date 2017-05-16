package discordbot.utils;

import net.dv8tion.jda.core.entities.Member;

public class PlayerAccount {
	private String ID;
	private String playerName;
	private long ppScore;
	
	private Palenta palenta;	//each player has a palenta pet
	
	public PlayerAccount(Member player){		//Used when creating a player account during a session
		this.ID = player.getUser().getId();
		this.playerName = player.getNickname();
		ppScore = 1000;
		palenta = new Palenta(this);
	}
	
	public PlayerAccount(String data){			//Used when creating a player account from file
		String[] values = data.split(Bot.NINE_ELEVEN);
		ID = values[0];
		playerName = values[1];
		ppScore = Long.parseLong(values[2],10);
		palenta = new Palenta(this, Integer.parseInt(values[3]));
	}
	
	//✈▐▐   is our data seperator
	@Override
	public String toString(){
		return "" + ID + Bot.NINE_ELEVEN + playerName + Bot.NINE_ELEVEN + ppScore + Bot.NINE_ELEVEN + palenta.getExp();
	}

	public String getID() {
		return ID;
	}

	public String getPlayerName() {
		return playerName;
	}

	public long getPpScore() {
		return ppScore;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public void setPpScore(long ppScore) {
		this.ppScore = ppScore;
	}
	
	public void setPalenta(Palenta palenta){
		this.palenta = palenta;
	}
	
	public Palenta getPalenta(){
		return palenta;
	}
}
