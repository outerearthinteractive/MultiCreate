package com.d4l3k.Link.Gate.String;

import org.bukkit.craftbukkit.block.CraftSign;
import org.bukkit.event.block.SignChangeEvent;

import com.d4l3k.Link.BaseGate;

public class StringEqual extends BaseGate{
	public StringEqual(SignChangeEvent event)
	{
		super(event);
		this.gateName = "String Equal";
		this.gateID = "[StringEqual]";
		this.addOutput("Equal", "double");
		
		this.addInput("String1", "string");
		this.addInput("String2", "string");
	}
	public StringEqual() {
		// TODO Auto-generated constructor stub
	}
	public void Execute(int input, Object oldval, Object newval)
	{
		double data = 0.0;
		if(this.getInput(0, "").equals(this.getInput(1, "")))
		{
			data = 1.0;
		}
		
		this.setOutput(0, data);
		CraftSign sig = new CraftSign(this.gateBlock);
		sig.setLine(1, "Equal: "+Double.toString(data));
		sig.update();
	}
}
