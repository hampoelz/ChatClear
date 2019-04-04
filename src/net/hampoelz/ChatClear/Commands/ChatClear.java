package net.hampoelz.ChatClear.Commands;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;

import net.hampoelz.ChatClear.Main.Config;
import net.hampoelz.ChatClear.Main.Main;

public class ChatClear implements CommandExecutor
{
	Main plugin;
	  
	public ChatClear(Main main)
	{
		this.plugin = main;
	}
	  
	public boolean isPlayerOnline(Player p)
	{
		if (p != null)
		{
		    return true;
		}
		
		return false;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		String Usage = Config.getMessagesUsageChatClear();
		String NoPermissions = Config.getMessagesNoPermissions();
	    
		if (args.length == 0)
	    {
	    	if ((sender instanceof ConsoleCommandSender))
	    	{
	    		ChatClearALL("Console");
	    	}
	    	
	    	if ((sender instanceof Player))
	    	{
	    		Player p = (Player)sender;
	    		
	    		if (!p.hasPermission("chatclear.all"))
	    		{
	          
	    			p.sendMessage(ChatColor.translateAlternateColorCodes('&', NoPermissions));
	          
	    			return true;
	    		}
	        
	    		ChatClearALL(p.getName());
	    	}
	    }
		else if (args.length == 1)
	    {
			Player op = Bukkit.getPlayer(args[0]);
	      
			String Success = Config.getMessagesSuccessChatClearbyPlayer();
			
			if ((sender instanceof ConsoleCommandSender))
			{
				if (args[0].equalsIgnoreCase("reload"))
				{
					try
					{
						Config.load();
						
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aThe config was successfully reloaded"));
					}
					catch (IOException | InvalidConfigurationException e)
					{
						e.printStackTrace();
						
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cAn error occurred while reloading the configuration"));
					}
				}
				else
				{
					if (isPlayerOnline(op))
					{
						ChatClearPlayer(op, "Console");
						
						Success = Success.replace("%player%", op.getName());
				          
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Success));
					}
					else
					{
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Config.getMessagesPlayerNotOnline()));
					}
				}
			}
			
			if ((sender instanceof Player))
			{
				Player p = (Player)sender;

				if (args[0].equalsIgnoreCase("reload"))
				{
					if (p.hasPermission("chatclear.reload"))
					{
						try
						{
							Config.load();
							
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&6ChatClear&8] &aThe config was successfully reloaded"));
						}
						catch (IOException | InvalidConfigurationException e)
						{
							e.printStackTrace();
							
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&6ChatClear&8] &cAn error occurred while reloading the configuration"));
						}
					}
					else
					{
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', NoPermissions));
					}
				}
				else if (op != p)
				{
					if (p.hasPermission("chatclear.other"))
					{
						if (isPlayerOnline(op))
						{
							ChatClearPlayer(op, p.getName());
							
							Success = Success.replace("%player%", op.getName());
				            
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', Success));
						}
						else
						{
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', Config.getMessagesPlayerNotOnline()));
						}
					}
					else
					{
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', NoPermissions));
					}
				}
				else if (op == p)
				{
					if (p.hasPermission("chatclear.self"))
					{
						ChatClearSelf(op);
					}
					else
					{
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', NoPermissions));
					}
				}
			}
	    }
		else
		{
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Usage));
	    }
	    
		return true;
	  }
	  
	  public void ChatClearALL(String player)
	  {
		  String Success = Config.getMessagesSuccessChatClearALL();
	    
		  Success = Success.replace("%player%", player);
		  
		  for (int i = 0; i <= 100; i++)
		  {
			  this.plugin.getServer().broadcastMessage("");
		  }
		  
		  this.plugin.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', Success));
	  }
	  
	  public void ChatClearPlayer(Player player, String by)
	  {
		  String Success = Config.getMessagesSuccessChatClearPlayer();
	    
		  Success = Success.replace("%player%", by);
	    
		  for (Player op : Bukkit.getServer().getOnlinePlayers())
		  {
			  if (op == player)
			  {
				  for (int i = 0; i <= 100; i++)
				  {
					  player.sendMessage("");
				  }
	        
				  player.sendMessage(ChatColor.translateAlternateColorCodes('&', Success));
	        
				  break;
			  }
		  }
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
