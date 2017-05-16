package discordbot.commands;

import java.util.Iterator;
import java.util.Map.Entry;

import discordbot.Command;
import discordbot.Main;
import discordbot.utils.IO;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class HelpCommand implements Command {
	/*
	=======  AUFMERKSAMKEIT BITTE !! =======
	Der Bot hat neue features! Alle bitte ein Mal kurz mit ~register einen Spieleraccount anlegen.
	
	Features:
	~stats           Lass dir deine Spielerstatistik anzeigen!
	~slot            Setze 250 PlagiatsPunkte (PPoints) um bis zu 4200 zu gewinnen! Gib "~slot 5" oder eine andere beliebige Zahl ein, um sowohl Einsatz als auch Gewinn zu multiplizieren.
	
	Mit den Plagiatspunkten könnt ihr euer behindertes Haustier, eure Palenta, füttern mit dem Befehl ~feed <PPoints>, wobei PPoints die Anzahl ist, die ihr verfüttern wollt.
	Palenta generiert dann automatisch solange ihr online seid mehr PP, je nach ihrem Level. Füttert sie um sie aufzuleveln!
	
	Ansonsten fügt man nun Scheiße mit ~addRandom <Scheiße> hinzu, mit ~random ruft man sie auf.
	
	Viel Spaß!
	========================================
	 */
	
	private final String HELP = "USAGE: ~help\t\t\tShows all possible commands.";
	
	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		return true;
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		String helpMessage = "```=== The following commands can be used: ===";
		Iterator<Entry<String, Command>> it = Main.commands.entrySet().iterator();
		while(it.hasNext()){
			Entry<String, Command> entry = it.next();
			Command c = entry.getValue();
			if (c.help() != null) helpMessage += "\n" + c.help();
		}
		helpMessage += "```";	//beautiful markdown
		IO.out(helpMessage);
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
