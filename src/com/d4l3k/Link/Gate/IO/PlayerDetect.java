package com.d4l3k.Link.Gate.IO;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.craftbukkit.block.CraftSign;
import org.bukkit.entity.Player;
import org.bukkit.event.block.SignChangeEvent;

import com.d4l3k.Link.BaseGate;
import com.d4l3k.Link.Core;

public class PlayerDetect extends BaseGate{
	public PlayerDetect(SignChangeEvent event)
	{
		super(event);
		this.gateName = "Player Detect";
		this.gateID = "[PlayerDetect]";
		this.addOutput("Player", "string");
		this.addOutput("Location", "location");
		this.addOutput("NumDetected", "double");

		this.addInput("Update", "double");
		this.addInput("Distance", "double");
		this.addInput("Next", "double");
	}
	public PlayerDetect() {
		// TODO Auto-generated constructor stub
	}
	public void Execute(int input, Object oldval, Object newval)
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
		if(input==2&&(Double)newval>=1.0&&(Double)oldval<1.0)
		{
			this.gateDouData++;
		}
		String selectPlayer = "";
		Location loc = new Location(gateBlock.getWorld(),0,0,0);
		if(gateDouData>=player.size())
		{
			gateDouData = 0.0;
		}
		if(player.size()>=1)
		{
			selectPlayer = player.get((int)gateDouData).getName();
			loc = player.get((int)gateDouData).getLocation();
		}
		this.setOutput(0, selectPlayer);
		this.setOutput(1, loc);
		this.setOutput(2, player.size());
		CraftSign sig = new CraftSign(this.gateBlock);
		sig.setLine(1, "T: "+selectPlayer);
		sig.update();
	}
}
