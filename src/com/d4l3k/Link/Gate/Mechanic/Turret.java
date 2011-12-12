package com.d4l3k.Link.Gate.Mechanic;

import org.bukkit.Location;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.util.Vector;

import com.d4l3k.Link.BaseGate;

public class Turret extends BaseGate{
	public Turret(SignChangeEvent event)
	{
		super(event);
		this.gateName = "Arrow Turret";
		this.gateID = "[Turret]";
		this.gatePerm = "danger";
		this.addInput("Target", "location");
		this.addInput("Fire", "double");
		this.addInput("Speed", "double");
		this.addInput("Spread", "double");
	}
	public Turret() {
		// Auto-generated constructor stub
	}
	public void Execute(int input, Object oldval, Object newval)
	{
		if((Double)this.getInput(1, 0.0)>=1.0)
		{
			Location loc = (Location)this.getInput(0, gateBlock.getLocation());
			Vector offset = loc.toVector().subtract(gateBlock.getLocation().toVector());
			Vector vel = offset.normalize();
			double speed = (Double)this.getInput(2, 0.6);
			double spread = (Double)this.getInput(3, 12.0);
			gateBlock.getWorld().spawnArrow(gateBlock.getLocation().add(0.5, 0.5, 0.5), vel, (float)speed, (float)spread);
			
		}
	}
}
