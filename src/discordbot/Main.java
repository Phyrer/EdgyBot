package discordbot;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Timer;

import discordbot.commands.AddGreetingCommand;
import discordbot.commands.AddPPCommand;
import discordbot.commands.AddRandomCommand;
import discordbot.commands.FeedCommand;
import discordbot.commands.GameCommand;
import discordbot.commands.HelpCommand;
import discordbot.commands.PingCommand;
import discordbot.commands.RandomCommand;
import discordbot.commands.RegisterCommand;
import discordbot.commands.RussianRouletteCommand;
import discordbot.commands.SaveCommand;
import discordbot.commands.ShutdownCommand;
import discordbot.commands.SlotCommand;
import discordbot.commands.StatsCommand;
import discordbot.commands.VotekickCommand;
import discordbot.communication.Sets;
import discordbot.utils.Autosave;
import discordbot.utils.Bot;
import discordbot.utils.CommandParser;
import discordbot.utils.PPGenerator;
import discordbot.utils.PlayerAccount;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;

public class Main{
	
	public static JDA jda;
	public static final CommandParser parser = new CommandParser();
	
	public static HashMap<String, Command> commands = new HashMap<String, Command>();
	public static HashMap<String, PlayerAccount> players = new HashMap<String, PlayerAccount>();
	
	public static void main(String[]Args){
		//CONNECT
		try{
			jda = new JDABuilder(AccountType.BOT).addListener(new BotListener()).setToken(Bot.DISCORD_TOKEN).buildBlocking();
			jda.setAutoReconnect(true);
		} catch(Exception e){
			e.printStackTrace();
		}
		
		//LOAD FILES AND PROCESSES
		loadFiles();
		Timer time = new Timer();	//start generating PPoints
		PPGenerator generator = new PPGenerator();
		Autosave saver = new Autosave();
		time.scheduleAtFixedRate(generator, 0, 60000);
		time.scheduleAtFixedRate(saver, 60000, 60000);

		//INITIALIZE ALL POSSIBLE COMMANDS
		commands.put("ping", new PingCommand());
		commands.put("addGreet", new AddGreetingCommand());
		commands.put("addRandom", new AddRandomCommand());
		commands.put("addPP", new AddPPCommand());
		commands.put("stats", new StatsCommand());
		commands.put("feed", new FeedCommand());
		commands.put("game", new GameCommand());
		commands.put("help", new HelpCommand());
		commands.put("random", new RandomCommand());
		commands.put("register", new RegisterCommand());
		commands.put("russian", new RussianRouletteCommand());
		commands.put("save", new SaveCommand());
		commands.put("shutdown", new ShutdownCommand());
		commands.put("slot", new SlotCommand());
		commands.put("votekick", new VotekickCommand());
		
		//=== UGLY SHUTDOWN HOOK ===
		Runtime.getRuntime().addShutdownHook( new Thread() {
			@Override
			public void run() {
			    savePlayerAccounts(Bot.PLAYERS_FILE);
			}
		});
	}
	
	//===== MAIN METHODS =====
	
	public static void handleCommand(CommandParser.CommandContainer cmd){
		if (commands.containsKey(cmd.invoke)){
			boolean safe = commands.get(cmd.invoke).called(cmd.args, cmd.event);
			
			if (safe){
				//TODO: log what the person did
				commands.get(cmd.invoke).action(cmd.args, cmd.event);
				commands.get(cmd.invoke).executed(safe, cmd.event);
			} else{
				commands.get(cmd.invoke).executed(safe, cmd.event);
			}
		}
	}
	
	//=== SAVES ===
	@SuppressWarnings("rawtypes")
	synchronized public static void savePlayerAccounts(String fileName){
		try{
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(Bot.PLAYERS_FILE, false), Bot.ENCODING));
			Iterator it = players.entrySet().iterator();
			while(it.hasNext()){
				Map.Entry entry = (Map.Entry)it.next();
				pw.println(entry.getValue());
			}
			pw.close();
		} catch(Exception e){
			System.out.println("Couldn't save player accounts.");
			e.printStackTrace();
		}
	}
	
	//=== LOADS ===
	synchronized public static void loadFiles(){			//Hopefully loads EVERYTHING
		FileInputStream fileInput;
		Scanner scan;
		//--- LOAD Players.txt ---
		try{
			fileInput = new FileInputStream(Bot.PLAYERS_FILE);
			scan = new Scanner(fileInput, Bot.ENCODING);
			while (scan.hasNextLine()){
				String playerData = scan.nextLine();
				if (playerData.length() > 5){
					String ID = playerData.split(Bot.DATA_SEPARATOR)[0];
					players.put(ID, new PlayerAccount(playerData));
				}
			}
		} catch(Exception e){
			System.out.println("Could not load player data.");
			e.printStackTrace();
		}
		//--- LOAD RandomStuff.txt ---
		try {
			fileInput = new FileInputStream(Bot.RANDOM_STUFF_FILE);
			scan = new Scanner(fileInput, Bot.ENCODING);
			while (scan.hasNextLine()){
				String content;
				content = scan.nextLine();
				if (!Sets.randomStuff.contains(content))
					Sets.randomStuff.add(content);
			}
			scan.close();
		} catch (FileNotFoundException e) {
			System.out.println("The file " + Bot.RANDOM_STUFF_FILE + " could not be found.");
			e.printStackTrace();
		}
	}
	
}