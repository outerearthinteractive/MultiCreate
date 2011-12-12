package com.d4l3k.Link.Gate.String;

import org.bukkit.craftbukkit.block.CraftSign;
import org.bukkit.event.block.SignChangeEvent;

import com.d4l3k.Link.BaseGate;

public class IfString extends BaseGate{
	public IfString(SignChangeEvent event)
	{
		super(event);
		this.gateName = "If Value Then String";
		this.gateID = "[IfString]";
		this.addOutput("Value", "string");
		this.addInput("If", "double");
		this.addInput("String", "string");
	}
	public IfString() {
		// TODO Auto-generated constructor stub
	}
	public void Execute(int input, Object oldval, Object newval)
	{
		String output = "";
		if(((Double)this.getInput(0, 0.0))>=1.0)
		{
			output = (String)this.getInput(1, "");
		}
		
		this.setOutput(0, output);
		CraftSign sig = new CraftSign(this.gateBlock);
		sig.setLine(1, output);
		sig.update();
	}
}
