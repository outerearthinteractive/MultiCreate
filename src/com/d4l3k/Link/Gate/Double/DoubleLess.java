package com.d4l3k.Link.Gate.Double;

import org.bukkit.craftbukkit.block.CraftSign;
import org.bukkit.event.block.SignChangeEvent;

import com.d4l3k.Link.BaseGate;


public class DoubleLess extends BaseGate{
	public DoubleLess(SignChangeEvent event)
	{
		super(event);
		this.gateName = "Double Less";
		this.gateID = "[Less]";
		this.addOutput("Value", "double");
		this.addInput("Double1", "double");
		this.addInput("Double2", "double");
	}
	public DoubleLess() {
		// TODO Auto-generated constructor stub
	}
	public void Execute(int input, Object oldval, Object newval)
	{
		double data = 0.0;
		if((Double)this.getInput(0, 0.0)<(Double)this.getInput(1, 0.0))
		{
			data = 1.0;
		}
		
		this.setOutput(0, data);
		CraftSign sig = new CraftSign(this.gateBlock);
		sig.setLine(1, "Less: "+Double.toString(data));
		sig.update();
	}
}
