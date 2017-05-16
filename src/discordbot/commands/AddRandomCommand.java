package discordbot.commands;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

import discordbot.Command;
import discordbot.communication.Sets;
import discordbot.utils.Bot;
import discordbot.utils.IO;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class AddRandomCommand implements Command {
	
	private final String HELP = "USAGE: ~addRandom <Your thoughts here>\t\t\tAdd your random thoughts here and the bot will remember them.";
	
	PrintWriter pw;
	FileInputStream fileInput;
	Scanner scan;
	
	public AddRandomCommand(){
		try {
			fileInput = new FileInputStream(Bot.RANDOM_STUFF_FILE);
			scan = new Scanner(fileInput, Bot.encoding);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		return true;
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		try {
			String newStuff = Arrays.toString(args).replaceAll(", |\\[|\\]", " ").replace("\r", " ").replace("\n", " ");	//complicated Regex thingy
			newStuff = newStuff.substring(1, newStuff.length()-1);	//remove space in front and after
			if (!Sets.randomStuff.contains(newStuff)){
				save(Bot.RANDOM_STUFF_FILE,newStuff);
				load(Bot.RANDOM_STUFF_FILE);
				IO.out("\"" + newStuff + "\" was added to the database.\nPlease be gentle with me >.<");
			} else{
				IO.out("Some fucker already taught me that.\r\nYou're not the real OG");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void save(String fileName, String sentence) throws IOException{	//saves this as a new line in the file
		pw = new PrintWriter(new FileWriter(Bot.RANDOM_STUFF_FILE, true));
		pw.println(sentence);
	    pw.close();
	}
	
	protected void load(String fileName) throws FileNotFoundException{	//load new Array from file
		if(scan.hasNextLine()){
			String content;
			content = scan.nextLine();
			Sets.randomStuff.add(content);
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
