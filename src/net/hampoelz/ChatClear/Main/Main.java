package net.hampoelz.ChatClear.Main;

import java.io.IOException;

import org.bukkit.plugin.java.JavaPlugin;

import net.hampoelz.ChatClear.Commands.ChatClear;
import net.hampoelz.ChatClear.Commands.OwnChatClear;

public class Main extends JavaPlugin
{
	@Override
	public void onEnable()
	{			   		
		getCommands();
		
		try
		{
			Config.set();
			Config.save();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		System.out.println("[ChatClear] v" + getDescription().getVersion() + " enabled!");
	}

	@Override
	public void onDisable()
	{
		System.out.println("[ChatClear] v" + getDescription().getVersion() + " disabled!");
	}	
	
	private void getCommands()
	{
		getCommand("chatclear").setExecutor(new ChatClear(this));
	    getCommand("cc").setExecutor(new ChatClear(this));
	    
	    getCommand("ownchatclear").setExecutor(new OwnChatClear(this));
	    getCommand("occ").setExecutor(new OwnChatClear(this));
	}
}
