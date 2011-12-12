package com.d4l3k.Link.Gate.Double;

import org.bukkit.craftbukkit.block.CraftSign;
import org.bukkit.event.block.SignChangeEvent;

import com.d4l3k.Link.BaseGate;
import com.d4l3k.Link.Data;


public class DoubleGreater extends BaseGate{
	public DoubleGreater(SignChangeEvent event)
	{
		super(event);
		this.gateName = "Double Greater";
		this.gateID = "[Greater]";
		this.addOutput("Value", "double");
		this.addInput("Double1", "double");
		this.addInput("Double2", "double");
	}
	public DoubleGreater() {
		// TODO Auto-generated constructor stub
	}
	public void Execute(int input, Object oldval, Object newval)
	{
		double data = 0.0;
		if((Double)Data.getInput(this, 0, 0.0)>(Double)Data.getInput(this, 1, 0.0))
		{
			data = 1.0;
		}
		
		this.setOutput(0, data);
		CraftSign sig = new CraftSign(this.gateBlock);
		sig.setLine(1, "V: "+Double.toString(data));
		sig.update();
	}
}
