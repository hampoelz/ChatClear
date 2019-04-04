package net.hampoelz.ChatClear.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import net.hampoelz.ChatClear.Main.Config;
import net.hampoelz.ChatClear.Main.Main;

public class OwnChatClear implements CommandExecutor
{
	Main plugin;
	Boolean isPlayerOnline = Boolean.valueOf(false);
	  
	public OwnChatClear(Main main)
	{
		this.plugin = main;
	}
	  
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		String NoPermissions = Config.getMessagesNoPermissions();
		
		if ((sender instanceof ConsoleCommandSender))
		{
			sender.sendMessage("§cThis command can only be executed as a player");
	    }
	    
		if ((sender instanceof Player))
		{
			Player p = (Player)sender;
			
			if (!p.hasPermission("chatclear.self"))
			{
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', NoPermissions));
	        
				return true;
			}
			
			ChatClearSelf(p);
		}
	    
		return true;
	  }
	  
	  public void ChatClearSelf(Player player)
	  {
		  String Success = Config.getMessagesSuccessChatClearSelf();
	    
		  for (int i = 0; i <= 100; i++)
		  {
			  player.sendMessage("");
		  }
	    
		  player.sendMessage(ChatColor.translateAlternateColorCodes('&', Success));
	  }
}
