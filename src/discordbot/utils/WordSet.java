package discordbot.utils;

import java.util.ArrayList;

public class WordSet extends ArrayList<String>{
	/**
	 * Add method works like that of a set as no dublicates will be taken
	 * Other than that, it's actually just an ArrayList
	 */
	
	private static final long serialVersionUID = 1L;

	@Override
    public boolean contains(Object o) {
        String paramStr = (String)o;
        for (String s : this) {
            if (paramStr.equalsIgnoreCase(s)) return true;
        }
        return false;
    }
	
	@Override
	public boolean add(String o){	//only add if it's not already in there to avoid dublicates
		if (!this.contains(o)){
			return super.add(o);
		}
		else return false;
	}
}
