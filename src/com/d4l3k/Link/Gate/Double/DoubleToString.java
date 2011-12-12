package com.d4l3k.Link.Gate.Double;

import org.bukkit.craftbukkit.block.CraftSign;
import org.bukkit.event.block.SignChangeEvent;

import com.d4l3k.Link.BaseGate;

public class DoubleToString extends BaseGate{
	public DoubleToString(SignChangeEvent event)
	{
		super(event);
		this.gateName = "Double To String";
		this.gateID = "[ToString]";
		this.addOutput("Value", "string");
		this.addInput("Value", "double");
	}
	public DoubleToString() {
		// TODO Auto-generated constructor stub
	}
	public void Execute(int input, Object oldval, Object newval)
	{
		String data = Double.toString((Double)this.getInput(0, 0.0));
		this.setOutput(0, data);
		CraftSign sig = new CraftSign(this.gateBlock);
		sig.setLine(1, data);
		sig.update();
	}
}
