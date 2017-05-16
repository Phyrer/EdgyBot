package discordbot.utils;

import java.util.List;

import net.dv8tion.jda.core.entities.Role;

public class Utils {
	
	public static boolean checkRole(List<Role> list, String[] allowedRoles){
		for (Role r : list){
			for (String s : allowedRoles){
				if (s.equalsIgnoreCase(r.getName())){
					return true;
				}
			}
		}
		IO.out("You can't do that, peasant.");
		return false;
	}
}
