package com.d4l3k.Link.Gate.IO;

import org.bukkit.craftbukkit.block.CraftSign;
import org.bukkit.event.block.SignChangeEvent;

import com.d4l3k.Link.BaseGate;

public class GetTime extends BaseGate{
	public GetTime(SignChangeEvent event)
	{
		super(event);
		this.gateName = "Get Time";
		this.gateID = "[GetTime]";
		this.addOutput("Time", "double");
		this.addInput("Update", "double");
	}
	public GetTime() {
		// TODO Auto-generated constructor stub
	}
	public void Execute(int input, Object oldval, Object newval)
	{
		if((Double)this.getInput(1, 0.0)<1.0)
			return;
		
		CraftSign sig = new CraftSign(this.gateBlock);
		double time = (double)gateBlock.getWorld().getTime();
		this.setOutput(0, time);
		sig.setLine(1, Double.toString(time));
		sig.update();
		
	}
}
