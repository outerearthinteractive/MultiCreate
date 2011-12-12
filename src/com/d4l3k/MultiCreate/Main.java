package com.d4l3k.MultiCreate;

import java.util.logging.Logger;

import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.d4l3k.MultiCreate.*;

public class Main extends JavaPlugin {
	static Logger log = Logger.getLogger("Minecraft");
	public static Server server;
	
	public static void debug(String msg)
	{
		log.info("[MultiCraft] "+msg);
	}
	public void onEnable(){
		server = this.getServer();
		
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvent(Event.Type.BLOCK_PLACE, worldListener, Event.Priority.Highest, this);
		pm.registerEvent(Event.Type.BLOCK_BREAK, worldListener, Event.Priority.Highest, this);
		pm.registerEvent(Event.Type.PLAYER_MOVE, playerListener, Event.Priority.Highest, this);
		pm.registerEvent(Event.Type.PLAYER_PICKUP_ITEM, playerListener, Event.Priority.Highest, this);

		debug("is enabled; by d4l3k and arkaniad"); //together, forever <3
	}
	public void onDisable(){
		debug("has been disabled;");
	}
	private final WorldListener worldListener = new WorldListener(this);
	private final InteractListener playerListener = new InteractListener(this);
	
	public boolean playerShouldCreate(Player player)
	{
		Location loc = player.getLocation();
		if(loc.getBlockX()>=0)
		{
			return true;
		}
		return false;
	}
}
