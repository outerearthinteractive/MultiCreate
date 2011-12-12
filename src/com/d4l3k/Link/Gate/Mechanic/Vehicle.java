package com.d4l3k.Link.Gate.Mechanic;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.craftbukkit.block.CraftSign;
import org.bukkit.entity.Player;
import org.bukkit.event.block.SignChangeEvent;

import com.d4l3k.Link.BaseGate;
import com.d4l3k.Link.Core;

public class Vehicle extends BaseGate{
	public Vehicle(SignChangeEvent event)
	{
		super(event);
		this.gateName = "Vehicle Controller";
		this.gateID = "[Vehicle]";
		this.addOutput("Player", "string");
		this.addInput("Update", "double");
		this.addInput("Distance", "double");
	}
	public Vehicle() {
		// TODO Auto-generated constructor stub
	}
	public void Execute()
	{
		double Dist = (Double)this.getInput(1, 5.0);
		ArrayList<Player> player = new ArrayList<Player>();
		Player[] players = Core.server.getOnlinePlayers();
		for(int i = 0; i < players.length; i++)
		{
			if(players[i].getWorld().equals(gateBlock.getWorld()))
			{
				Location loc = players[i].getLocation();
				Location ourLoc = gateBlock.getLocation();
				double xDist = ourLoc.getX()-loc.getX();
				double yDist = ourLoc.getY()-loc.getY();
				double zDist = ourLoc.getZ()-loc.getZ();
				if(xDist<=Dist&&-xDist<=Dist&&yDist<=Dist&&-yDist<=Dist&&zDist<=Dist&&-zDist<=Dist)
				{
					player.add(players[i]);
				}
			}
			
		}
		String selectPlayer = "";
		if(player.size()>=1)
		{
			selectPlayer = player.get(0).getName();
		}
		this.setOutput(0, selectPlayer);
		CraftSign sig = new CraftSign(this.gateBlock);
		sig.setLine(1, "T: "+selectPlayer);
		sig.update();
	}
}
