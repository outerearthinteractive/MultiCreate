package com.d4l3k.Link;


import java.io.Serializable;

import org.bukkit.Location;
import org.bukkit.block.Block;

public class GateBlock implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private GateLocation loc;
	
	public GateBlock(Block block)
	{
		try
		{
			loc = new GateLocation(block.getLocation());
		}
		catch(Exception ex)
		{
			
		}
	}
	public Block getBlock()
	{
		
		try
		{
			Location pos = loc.getLocation();
			return pos.getBlock();
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	
}
