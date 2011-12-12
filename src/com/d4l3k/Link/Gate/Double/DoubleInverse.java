package com.d4l3k.Link.Gate.Double;

import org.bukkit.craftbukkit.block.CraftSign;
import org.bukkit.event.block.SignChangeEvent;

import com.d4l3k.Link.BaseGate;

public class DoubleInverse extends BaseGate{
	public DoubleInverse(SignChangeEvent event)
	{
		super(event);
		this.gateName = "Double Inverse";
		this.gateID = "[Inverse]";
		this.addOutput("Value", "double");
		this.addInput("Value", "double");
	}
	public DoubleInverse() {
		// TODO Auto-generated constructor stub
	}
	public void Execute(int input, Object oldval, Object newval)
	{
		double data = 0.0;
		if((Double)this.getInput(0, 0.0)<1.0)
		{
			data = 1.0;
		}
		
		this.setOutput(0, data);
		CraftSign sig = new CraftSign(this.gateBlock);
		sig.setLine(1, "Equal: "+Double.toString(data));
		sig.update();
	}
}
