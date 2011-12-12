package com.d4l3k.Link.Gate.IO;

import org.bukkit.event.block.SignChangeEvent;

import com.d4l3k.Link.BaseGate;


public class ConstantValue extends BaseGate{
	public ConstantValue(SignChangeEvent event)
	{
		super(event);
		this.gateName = "Constant Value";
		this.gateID = "[Constant]";
		this.gateBlock = event.getBlock();
		this.gateStrData = event.getLine(1)+event.getLine(2)+event.getLine(3);
		
		try
		{
			double parse = Double.parseDouble(this.gateStrData);
			this.addOutput("Value","double");
			this.setOutput(0, parse);
		}
		catch(Exception Ex)
		{
			this.addOutput("Value","string");
			this.setOutput(0, this.gateStrData);
		}
		
	}
	public ConstantValue() {
		// TODO Auto-generated constructor stub
	}
	public void Execute(int input, Object oldval, Object newval)
	{
		
	}
}
