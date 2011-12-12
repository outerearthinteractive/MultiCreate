package com.d4l3k.Link.Gate.Mechanic;

import org.bukkit.Location;
import org.bukkit.craftbukkit.block.CraftSign;
import org.bukkit.entity.Player;
import org.bukkit.event.block.SignChangeEvent;

import com.d4l3k.Link.BaseGate;
import com.d4l3k.Link.Core;

public class PlayerPosition extends BaseGate{
	public PlayerPosition(SignChangeEvent event)
	{
		super(event);
		this.gateName = "Player Position";
		this.gateID = "[PlayerPos]";
		this.addOutput("Location", "location");
		this.addInput("Update", "double");
		this.addInput("Player", "string");
	}
	public PlayerPosition() {
		// TODO Auto-generated constructor stub
	}
	public void Execute(int input, Object oldval, Object newval)
	{
		Location pos = gateBlock.getLocation();
		String plr = (String)this.getInput(0, "");
		pos = getPlayer(plr).getLocation();
		this.setOutput(0, pos);
		CraftSign sig = new CraftSign(this.gateBlock);
		sig.setLine(1, "T: "+gateBlock.getWorld().getName());
		sig.update();
	}
	public Player getPlayer(String search)
	{
		Player[] plrs = Core.server.getOnlinePlayers();
		for(int i=0;i<plrs.length;i++)
		{
			String name = plrs[i].getName();
			if(name.contains(search)&&plrs[i].getWorld().equals(gateBlock.getWorld()))
			{
				return plrs[i];
			}
		}
		return null;
	}
}
