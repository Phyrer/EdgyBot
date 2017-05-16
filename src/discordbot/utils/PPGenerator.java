package discordbot.utils;

import java.util.TimerTask;

import discordbot.Main;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;

public class PPGenerator extends TimerTask{
	@Override
	public void run() {
		//go through all the guilds the bot is in
		for (Guild guild: Main.jda.getGuilds()){
			//go through each member of each guild
			for (Member member: guild.getMembers()){
				if (member.getOnlineStatus() == OnlineStatus.ONLINE){	//if they are online, generate PP based on Palenta level
					PlayerAccount player = Main.players.get(member.getUser().getId());
					if (player != null) player.setPpScore(player.getPpScore() + (int) player.getPalenta().getPppm());
				}
			}
		}
	}
}
