package com.d4l3k.Link;

import java.io.Serializable;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.World;

public class GateLocation implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private double locX = 0.0;
	private double locY = 0.0;
	private double locZ = 0.0;
	private int world = 0;
	
	public GateLocation(World warld)
	{
		List<World> worlds = Core.server.getWorlds();
		world = worlds.indexOf(warld);
	}
	
	public GateLocation(Location loc)
	{
		try
		{
			locX = loc.getX();
			locY = loc.getY();
			locZ = loc.getZ();
			List<World> worlds = Core.server.getWorlds();
			world = worlds.indexOf(loc.getWorld());
					
		}
		catch(Exception ex)
		{
		}
	}
	public Location getLocation()
	{
		World wald = Core.server.getWorlds().get(world);
		Location pos = new Location(wald,locX,locY,locZ);
		return pos;
	}
	
}