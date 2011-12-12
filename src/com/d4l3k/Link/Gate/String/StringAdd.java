package com.d4l3k.Link.Gate.String;

import org.bukkit.craftbukkit.block.CraftSign;
import org.bukkit.event.block.SignChangeEvent;

import com.d4l3k.Link.BaseGate;

public class StringAdd extends BaseGate{
	public StringAdd(SignChangeEvent event)
	{
		super(event);
		this.gateName = "String Add";
		this.gateID = "[StringAdd]";
		this.addOutput("Value", "double");
		this.addInput("String1", "string");
		this.addInput("String2", "string");
		this.addInput("String3", "string");
		this.addInput("String4", "string");
		this.addInput("String5", "string");
		this.addInput("String6", "string");
		this.addInput("String7", "string");
		this.addInput("String8", "string");
	}
	public StringAdd() {
		// TODO Auto-generated constructor stub
	}
	public void Execute(int input, Object oldval, Object newval)
	{
		String data = (String)this.getInput(0,"");
		for(int i=1;i<8;i++)
		{
			data+=(String)this.getInput(i,"");
		}
		
		this.setOutput(0, data);
		CraftSign sig = new CraftSign(this.gateBlock);
		sig.setLine(1, data);
		sig.update();
	}
}
