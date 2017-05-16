package discordbot.communication;

import discordbot.utils.WordSet;

public class Sets {
	public static WordSet greetings = new WordSet();
	public static WordSet randomStuff = new WordSet();
	
	static{
		greetings.add("Hi");
		greetings.add("Hello");
		greetings.add("Hey");
		greetings.add("Hey there!");
		greetings.add("Hi there!");
		greetings.add("Hello there!");
		greetings.add("Hey there");
		greetings.add("Hi there");
		greetings.add("Hello there");
		greetings.add("hi");
		greetings.add("hello");
		greetings.add("Hey babe");
		greetings.add("Fuck you");
	}
}
