package com.d4l3k.MultiCreate;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class InteractListener extends PlayerListener {
	public static Main plugin; 
	public InteractListener(Main instance) { 
        plugin = instance;
	}
	public void onPlayerMove(PlayerMoveEvent event)
	{
		Player player = event.getPlayer();
		GameMode mode = player.getGameMode();
		if(mode==GameMode.SURVIVAL&&plugin.playerShouldCreate(player))
		{
			player.setGameMode(GameMode.CREATIVE);
			//player.getInventory().;
		}
		else if(mode==GameMode.CREATIVE&&!plugin.playerShouldCreate(player))
		{
			player.setGameMode(GameMode.SURVIVAL);
		}
	}
	public void onPlayerPickupItem(PlayerPickupItemEvent event)
	{
		
	}
}
