package com.d4l3k.Link.Gate.Time;

import org.bukkit.craftbukkit.block.CraftSign;
import org.bukkit.event.block.SignChangeEvent;

import com.d4l3k.Link.BaseGate;

public class Counter extends BaseGate{
	public Counter(SignChangeEvent event)
	{
		super(event);
		this.gateName = "Double Counter";
		this.gateID = "[Counter]";
		this.addOutput("Value", "double");
		this.addInput("Increment", "double");
		this.addInput("Reset", "double");		
	}
	public Counter() {
	}
	public void Execute(int input, Object oldval, Object newval)
	{
		if(((Double)this.getInput(1, 0.0))>=1.0)
		{
			gateDouData = 0.0;
		}
		else
		{
			Double tickRate = (Double)this.getInput(0, 1.0);
			gateDouData+= tickRate;
		}
		CraftSign sig = new CraftSign(this.gateBlock);
		sig.setLine(1, Double.toString(gateDouData));
		sig.update();
		this.setOutput(0, gateDouData);
	}
}
