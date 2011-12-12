package com.d4l3k.Link.Gate.Mechanic;

import org.bukkit.Location;
import org.bukkit.craftbukkit.block.CraftSign;
import org.bukkit.entity.Player;
import org.bukkit.event.block.SignChangeEvent;

import com.d4l3k.Link.BaseGate;
import com.d4l3k.Link.Core;
import com.d4l3k.Link.GateLocation;

public class Teleport extends BaseGate{
	public Teleport(SignChangeEvent event)
	{
		super(event);
		this.gateName = "Player Teleport";
		this.gateID = "[Teleport]";
		this.gatePerm = "danger";
		this.addInput("Player", "string");
		this.addInput("Target", "location");
		this.addInput("Execute", "double");
	}
	public Teleport() {
		// TODO Auto-generated constructor stub
	}
	public void Execute(int input, Object oldval, Object newval)
	{
		if((Double)this.getInput(2, 0.0)>=1.0)
		{
			Player plr = Core.server.getPlayer((String)this.getInput(0, ""));
			Location loc = (Location)this.getInput(1, new GateLocation(plr.getLocation()));
			plr.teleport(loc);
			
			CraftSign sig = new CraftSign(this.gateBlock);
			sig.setLine(1, "T: "+plr.getName());
			sig.update();
		}
	}
}
