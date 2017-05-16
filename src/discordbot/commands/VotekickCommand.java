package discordbot.commands;

import java.util.HashMap;
import java.util.List;

import discordbot.Command;
import discordbot.utils.IO;
import discordbot.utils.WordSet;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class VotekickCommand implements Command {
	
	private final String HELP = "USAGE: ~votekick <@mention>\t\t\tVote to kick someone. Needs 3 votes to kick.";
	private HashMap<String, WordSet> voted = new HashMap<String, WordSet>();	//Maps ID of person to be kicked with set of IDs of persons who voted

	@Override
	public boolean called(String[] args, MessageReceivedEvent event) {
		return true;
	}

	@Override
	public void action(String[] args, MessageReceivedEvent event) {
		List<User> toBeKicked = event.getMessage().getMentionedUsers();	//get all mentioned users
		//Add all the votes and kick for every single user mentioned
		for (User u: toBeKicked){
			String uId = u.getId();
			if (!voted.containsKey(uId)){		//New user with no kick count this session
				WordSet s = new WordSet();		//The set of users who have voted for the kick
				s.add(event.getAuthor().getId());
				voted.put(uId, s);
			} else {
				voted.get(uId).add(event.getAuthor().getId());	//does not add dublicates
			}
			//Vote progression info display
			IO.out(event.getMember().getNickname() + " voted for " + event.getGuild().getMemberById(uId).getNickname() + " to be kicked.\n"
					+ voted.get(uId).size() + "/3 votes.");
			//Kick
			if (voted.get(uId).size() >= 3){				//get kicked at 3 votes lul
				event.getGuild().getController().kick(uId).queue();
				IO.out("Your comrade " + event.getGuild().getMemberById(uId).getNickname() + " got kicked.\n"
					+ "Y'all are heartless bastards... :'(");
			}
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
