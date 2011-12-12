package com.d4l3k.Link.Gate.Location;

import org.bukkit.Location;
import org.bukkit.event.block.SignChangeEvent;

import com.d4l3k.Link.BaseGate;
import com.d4l3k.Link.GateLocation;

public class LocationSplit extends BaseGate{
	public LocationSplit(SignChangeEvent event)
	{
		super(event);
		this.gateName = "Location Splitter";
		this.gateID = "[LocSplit]";
		this.addOutput("X", "double");
		this.addOutput("Y", "double");
		this.addOutput("Z", "double");
		
		this.addInput("ToSplit", "location");
	}
	public LocationSplit() {
		// TODO Auto-generated constructor stub
	}
	public void Execute(int input, Object oldval, Object newval)
	{
		Location loc = (Location)this.getInput(0, new GateLocation(gateBlock.getWorld()));
		this.setOutput(0, loc.getX());
		this.setOutput(1, loc.getY());
		this.setOutput(2, loc.getZ());
	}
}
