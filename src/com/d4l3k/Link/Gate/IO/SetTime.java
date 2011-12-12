package com.d4l3k.Link.Gate.IO;

import org.bukkit.craftbukkit.block.CraftSign;
import org.bukkit.event.block.SignChangeEvent;

import com.d4l3k.Link.BaseGate;
import com.d4l3k.Link.Data;


public class SetTime extends BaseGate{
	public SetTime(SignChangeEvent event)
	{
		super(event);
		this.gateName = "Set Time";
		this.gateID = "[SetTime]";
		this.gatePerm = "danger";
		this.addInput("Time", "double");
		this.addInput("Update", "double");
	}
	public SetTime() {
		// TODO Auto-generated constructor stub
	}
	public void Execute(int input, Object oldval, Object newval)
	{
		if((Double)Data.getInput(this, 1, 0.0)>=1.0)
		{
			CraftSign sig = new CraftSign(this.gateBlock);
			double time = (Double)this.getInput(0, 0.0);
			gateBlock.getWorld().setTime((long)time);
			sig.setLine(1, Double.toString(time));
			sig.update();
		}
	}
}
