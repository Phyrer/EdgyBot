package discordbot.utils;

public class ProgressBar {
	
	/*=== DESCRIPTION ===
	 * A progress bar designed to be printed out. Supposed to look like:
	 * [■■■■■■■□□□□□□□□□□□□□] Alt + 254 and □
	 */
	
	//=== ATTRIBUTES ===
	int progress;
	int segments;		//total number of segments
	int filled;			//Number of filled segments
	String displayMessage;	//Message to be displayed right next to the bar
	
	//=== CONSTRUCTORS ===
	/**
	 * Create new percentage progress bar
	 * @param percentage
	 */
	public ProgressBar(int percentage){
		this.progress = percentage;
		if (percentage < 0) progress = 0;
		if (percentage > 100) progress = 100;
		displayMessage = progress + "/100%";
		calcTiles(progress, 100);
	}
	
	/**
	 * Create an "n out of x" progress bar
	 * @param is
	 * @param max
	 */
	public ProgressBar(int is, int max){
		this.progress = is;
		if (is < 0) progress = 0;
		if (is > max) progress = max;
		displayMessage = progress + "/" + max;
		calcTiles(is, max);
	}
	
	//=== METHODS ===
	public void calcTiles(int is, int max){
		double ratio = (double)is/max;
		if (max > 20) max = 20;
		segments = max;
		filled = (int)(ratio * max);
	}
	
	@Override
	public String toString(){
		String res = "[";
		for (int i = 0; i < segments; i++){
			if (i < filled) res += "■";
			else res += "□";
		}
		res += "] " + displayMessage;
		return res;
	}
}
