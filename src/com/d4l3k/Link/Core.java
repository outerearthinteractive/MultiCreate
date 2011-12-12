package com.d4l3k.Link;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;
import org.bukkit.plugin.Plugin;

public class Core extends JavaPlugin implements Runnable{
	static Logger log = Logger.getLogger("Minecraft");
	public static Server server;
	public static PermissionHandler permissionHandler;
	
	public static void debug(String msg)
	{
		log.info("[LINK] "+msg);
	}
	private void setupPermissions() {
	    if (permissionHandler != null) {
	        return;
	    }
	    
	    Plugin permissionsPlugin = this.getServer().getPluginManager().getPlugin("Permissions");
	    
	    if (permissionsPlugin == null) {
	        debug("Permission system not detected, defaulting to OP");
	        return;
	    }
	    
	    permissionHandler = ((Permissions) permissionsPlugin).getHandler();
	    debug("Found and will use plugin "+((Permissions)permissionsPlugin).getDescription().getFullName());
	}
	public void onEnable(){
		server = this.getServer();
		
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvent(Event.Type.REDSTONE_CHANGE, redstoneListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.BLOCK_BREAK, breakListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.BLOCK_PISTON_RETRACT, pistonListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.SIGN_CHANGE, signListener, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.PLAYER_INTERACT, interactListener, Event.Priority.Normal, this);
		setupPermissions();
		Data.loadGates();
		getServer().getScheduler().scheduleSyncRepeatingTask(this, this, 1L, Data.serverTickRate);
		debug("by D4l3k! is enabled!");
	}
	public void onDisable(){
		Data.saveGates();
		debug("has been disabled. ;(");
	}
	private final WorldListener signListener = new WorldListener(this);
	private final WorldListener breakListener = new WorldListener(this);
	private final WorldListener pistonListener = new WorldListener(this);
	private final WorldListener redstoneListener = new WorldListener(this);
	private final InteractListener interactListener = new InteractListener(this);
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		if(args.length>0)
		{
			if(args[0].equalsIgnoreCase("edit")||args[0].equalsIgnoreCase("e"))
			{
				if (!Core.permissionHandler.has((Player)sender, "link.edit")) {
					sender.sendMessage("[LINK] "+ChatColor.RED+"Insufficient Permissions to enable Edit!");
				}
				Data.playerEditMode.put((Player)sender, true);
				Data.playerEditStatus.put((Player)sender, 0);
				sender.sendMessage("[LINK] "+ChatColor.GREEN+"Edit Enabled");
				return true;
			}
			else if(args[0].equalsIgnoreCase("info")||args[0].equalsIgnoreCase("about")||args[0].equalsIgnoreCase("version")||args[0].equalsIgnoreCase("v"))
			{
				sender.sendMessage("[LINK] "+ChatColor.GREEN+"INFORMATION");
				sender.sendMessage(ChatColor.GOLD+"By: "+ChatColor.AQUA+"D4l3k");
				sender.sendMessage(ChatColor.GOLD+"Version: "+ChatColor.WHITE+this.getDescription().getVersion());
				sender.sendMessage(ChatColor.GOLD+"TickRate: "+ChatColor.WHITE+Integer.toString(Data.serverTickRate));
				return true;
			}
			else if(args[0].equalsIgnoreCase("h")||args[0].equalsIgnoreCase("help"))
			{
				sender.sendMessage("[LINK] "+ChatColor.GREEN+"HELP");
				sender.sendMessage(ChatColor.GOLD+"/link "+ChatColor.AQUA+"e(dit)");
				sender.sendMessage(ChatColor.WHITE+" - Enabled Link Edit Mode");
				sender.sendMessage(ChatColor.GOLD+"/link "+ChatColor.AQUA+"v(ersion) | about | info");
				sender.sendMessage(ChatColor.WHITE+" - Displays Link Version + Info");
				sender.sendMessage(ChatColor.GOLD+"/link "+ChatColor.AQUA+"h(elp)");
				sender.sendMessage(ChatColor.WHITE+" - Displays Link Help - AKA this");
				return true;
			}
		}
		if(Data.playerEditMode.containsKey((Player)sender))
		{
			if(Data.playerEditMode.get((Player)sender))
			{
				Data.playerEditMode.put((Player)sender, false);
				sender.sendMessage("[LINK] "+ChatColor.RED+"Edit Disabled");
			}
		}
		
		sender.sendMessage("[LINK] "+ChatColor.GREEN+"HELP");
		sender.sendMessage(ChatColor.GOLD+"/link "+ChatColor.AQUA+"e(dit)");
		sender.sendMessage(ChatColor.WHITE+" - Enabled Link Edit Mode");
		sender.sendMessage(ChatColor.GOLD+"/link "+ChatColor.AQUA+"v(ersion) | about | info");
		sender.sendMessage(ChatColor.WHITE+" - Displays Link Version + Info");
		sender.sendMessage(ChatColor.GOLD+"/link "+ChatColor.AQUA+"h(elp)");
		sender.sendMessage(ChatColor.WHITE+" - Displays Link Help - AKA this");
		return true;
	}
	public void run() {
		Data.runSelfTriggered();
	}
}
