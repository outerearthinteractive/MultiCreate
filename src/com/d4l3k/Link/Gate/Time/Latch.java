package com.d4l3k.Link.Gate.Time;

import org.bukkit.craftbukkit.block.CraftSign;
import org.bukkit.event.block.SignChangeEvent;

import com.d4l3k.Link.BaseGate;

public class Latch extends BaseGate{
	public Latch(SignChangeEvent event)
	{
		super(event);
		this.gateName = "Double Latch";
		this.gateID = "[Latch]";
		this.addOutput("Value", "double");
		this.addInput("Click", "double");		
	}
	public Latch() {
	}
	public void Execute(int input, Object oldval, Object newval)
	{
		if(((Double)this.getInput(0, 0.0))>=1.0)
		{
			gateDouData *= -1;
			gateDouData += 1;
		}
		
		this.setOutput(0, gateDouData);
		CraftSign sig = new CraftSign(this.gateBlock);
		sig.setLine(1, Double.toString(gateDouData));
		sig.update();
		
	}
}
