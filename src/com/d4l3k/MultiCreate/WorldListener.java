package com.d4l3k.MultiCreate;

import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPlaceEvent;

public class WorldListener extends BlockListener{
	public static Main plugin; 
	public WorldListener(Main instance) { 
        plugin = instance;
	}
	
	public void onBlockBreak(BlockBreakEvent event)
	{
		
	}
	public void onBlockPlace(BlockPlaceEvent event)
	{
		
	}
}
