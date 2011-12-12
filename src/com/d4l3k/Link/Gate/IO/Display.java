package com.d4l3k.Link.Gate.IO;

import org.bukkit.craftbukkit.block.CraftSign;
import org.bukkit.event.block.SignChangeEvent;

import com.d4l3k.Link.BaseGate;

public class Display extends BaseGate{
	public Display(SignChangeEvent event)
	{
		super(event);
		this.gateName = "Display";
		this.gateID = "[Display]";
		this.addInput("Value", "double");
		this.addInput("Value", "string");
	}
	public Display() {
		// TODO Auto-generated constructor stub
	}
	public void Execute(int input, Object oldval, Object newval)
	{
		CraftSign sig = new CraftSign(this.gateBlock);
		String msg = (String)this.getInput(1, "");
		msg+=((Double)this.getInput(0, 0.0)).toString();
		sig.setLine(1, msg);
		sig.update();
	}
}
