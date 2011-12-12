package com.d4l3k.Link.Gate.Location;

import org.bukkit.Location;
import org.bukkit.event.block.SignChangeEvent;

import com.d4l3k.Link.BaseGate;

public class LocationMake extends BaseGate{
	public LocationMake(SignChangeEvent event)
	{
		super(event);
		this.gateName = "Location Maker";
		this.gateID = "[LocMake]";
		this.addOutput("Output", "location");
		
		this.addInput("X", "double");
		this.addInput("Y", "double");
		this.addInput("Z", "double");
	}
	public LocationMake() {
		// TODO Auto-generated constructor stub
	}
	public void Execute(int input, Object oldval, Object newval)
	{
		
		double x = (Double)this.getInput(0, 0.0);
		double y = (Double)this.getInput(1, 0.0);
		double z = (Double)this.getInput(2, 0.0);
		Location loc = new Location(gateBlock.getWorld(),x,y,z);
		this.setOutput(0, loc);
	}
}
