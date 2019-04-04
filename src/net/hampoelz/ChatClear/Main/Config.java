package net.hampoelz.ChatClear.Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Config
{
  public static File ConfigFile = new File("plugins/ChatClear", "config.yml");
  public static FileConfiguration Config = YamlConfiguration.loadConfiguration(ConfigFile);

//----------------------------------------------------------------------------------------------------------------------\\

  public static void save() throws IOException
  {
	  Config.save(ConfigFile);
  }

  public static void load() throws FileNotFoundException, IOException, InvalidConfigurationException
  {
	  Config.load(ConfigFile);
  }

  private static void setConfigContent(int ConfigVersion) throws IOException
  {
      Config.set("Config.ConfigVersion", ConfigVersion);

      Config.set("Config.Messages.Usage.ChatClear", "&8[&6ChatClear&8] &7Usage: &c/chatclear [Player / reload]");

      Config.set("Config.Messages.Success.ChatClearALL", "&8[&6ChatClear&8] &aThe chat has been cleared by %player%.");
      Config.set("Config.Messages.Success.ChatClearPlayer", "&8[&6ChatClear&8] &aYour chat has been cleared by %player%.");
      Config.set("Config.Messages.Success.ChatClearbyPlayer", "&8[&6ChatClear&8] &aYou have cleared the chat from %player%.");
      Config.set("Config.Messages.Success.ChatClearSelf", "&8[&6ChatClear&8] &aYou have cleared your own chat.");

      Config.set("Config.Messages.No Permissions", "&8[&6ChatClear&8] &cYou do not have permissions to run this command!");
      Config.set("Config.Messages.Player Not Online", "&8[&6ChatClear&8] &cApparently, the specified player is not online.");

      save();
  }
  
  public static void set() throws IOException
  {
    int ConfigVersion = 2;

    int Version = Config.getInt("Config.ConfigVersion");
    
    if (!ConfigFile.exists())
    {
    	setConfigContent(ConfigVersion);
    }
    else if (Version != ConfigVersion)
    {
      File ChatFileBackup = new File("plugins/ChatClear", "old config [v" + Version + "].yml");

      FileUtils.copyFile(ConfigFile, ChatFileBackup);

      for (String key : Config.getKeys(false))
      {
    	Config.set(key, null);
      }
		
      save();
		
      setConfigContent(ConfigVersion);
    }
  }

//----------------------------------------------------------------------------------------------------------------------\\

  public static String getMessagesUsageChatClear()
  {
    return Config.getString("Config.Messages.Usage.ChatClear");
  }

//----------------------------------------------------------------------------------------------------------------------\\

  public static String getMessagesSuccessChatClearALL()
  {
    return Config.getString("Config.Messages.Success.ChatClearALL");
  }

  public static String getMessagesSuccessChatClearPlayer()
  {
    return Config.getString("Config.Messages.Success.ChatClearPlayer");
  }

  public static String getMessagesSuccessChatClearbyPlayer()
  {
    return Config.getString("Config.Messages.Success.ChatClearbyPlayer");
  }

  public static String getMessagesSuccessChatClearSelf()
  {
    return Config.getString("Config.Messages.Success.ChatClearSelf");
  }

//----------------------------------------------------------------------------------------------------------------------\\

  public static String getMessagesNoPermissions()
  {
    return Config.getString("Config.Messages.No Permissions");
  }

  public static String getMessagesPlayerNotOnline()
  {
    return Config.getString("Config.Messages.Player Not Online");
  }
}
