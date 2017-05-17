package discordbot.utils;

import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Bot{
	//INSERT ALL GLOBAL STUFF HERE
	public static String DISCORD_TOKEN = "Mjc3NzczOTAwMjcwNzk2ODAw.C3i34A.FqxEcMeEU5J6dco1oQ1ugN7CVtc";
	public static String TEST_CHANNEL = "277812394611310593";
	public static MessageChannel currentChannel;
	public static MessageReceivedEvent currentEvent;
	public static final String RANDOM_STUFF_FILE = System.getProperty("user.dir") + "/" + "\\src\\discordbot\\saves\\RandomStuff.txt";
	public static final String PLAYERS_FILE = System.getProperty("user.dir") + "/" + "\\src\\discordbot\\saves\\Players.txt";
	public static final String DATA_SEPARATOR = "\t";
	public static final String ENCODING = "UTF-8";
}