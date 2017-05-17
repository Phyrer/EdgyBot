package discordbot.utils;

import discordbot.Main;

public class Palenta {
	//=== ATTRIBUTES ===
	private String ownerID;
	private int exp;
	private int lvl;
	private float pppm;	//PPoints per minute?
	private int thisLvlExp;	//min exp required to hit this level
	private int nextLvlExp;	//min exp required to hit the next level
	
	//=== CONSTRUCTORS ===
	public Palenta(PlayerAccount owner){
		this(owner, 1);
	}
	
	public Palenta(PlayerAccount owner, int exp){
		this.ownerID = owner.getID();
		this.exp = exp;
		this.lvl = newLevel();
		this.pppm = lvl;
	}
	
	//=== METHODS ===
	public void feed(int pp){
		exp += pp;
		//update stats
		int newLevel = newLevel();
		PlayerAccount owner = Main.players.get(ownerID);
		String message = "";
		if (newLevel != lvl){
			message += "Player " + owner.getPlayerName() + "'s palenta leveled up! Lvl " + lvl + " -> " + newLevel + "\n";
			lvl = newLevel;
			this.pppm = lvl;//(float) (lvl * (0.5 + 1/(lvl*0.5)));	//generates one PP per minute per level
		}
		//book pp from owner
		owner.setPpScore(owner.getPpScore() - pp);
		//show progress
		setProgress();
		message += new ProgressBar(exp - thisLvlExp, nextLvlExp - thisLvlExp) + " EXP\n\n";
		message += "You fed your palenta " + pp + " PPoints.";
		IO.out(message);
	}
	
	public int newLevel() { //checks if a new level is reached and returns which
		//JUST MODIFY THE FUNCTION INSIDE THE CONDITION TO ADJUST LVLUP FEELING!
		int newLvl = 1;
		for (int x = 0; exp >= (50 + 10 * Math.pow(x, 2)); x++) {	//the function is: f(x)= 50 + 10*x^2
			newLvl = 1 + x;
		}
		return newLvl;
	}
	
	public int reqExp(int lvl){
		return (int)(50 + 10 * Math.pow(lvl - 1, 2));	//first level ain't costin' yo nuttin'
	}
	
	public void setProgress(){
		thisLvlExp = reqExp(lvl);
		nextLvlExp = reqExp(1 + lvl);
	}
	
	@Override
	public String toString(){
		return "" + exp;
	}
	
	//=== GETTERS AND SETTERS ===
	public  int getExp(){
		return exp;
	}
	
	public int getLvl(){
		return lvl;
	}
	
	public String getOwnerID(){
		return ownerID;
	}
	
	public float getPppm(){
		return pppm;
	}
}
