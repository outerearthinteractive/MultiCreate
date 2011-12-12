package com.d4l3k.Link.Gate.IO;

import org.bukkit.craftbukkit.block.CraftSign;
import org.bukkit.event.block.SignChangeEvent;

import com.d4l3k.Link.BaseGate;

public class RedstoneIn extends BaseGate{
	public RedstoneIn(SignChangeEvent event)
	{
		super(event);
		this.gateName = "Redstone In";
		this.gateID = "[RedstoneIn]";
		this.addOutput("Current", "double");
		
		this.gateStrData = event.getLine(1);
		this.gateDouData = 0.0;
		updateOutput();
		event.setLine(1, Double.toString((Double)this.gateOutputs.get(0)));
	}
	public RedstoneIn() {
		// TODO Auto-generated constructor stub
	}
	public void Execute(int input, Object oldval, Object newval)
	{
		CraftSign sig = new CraftSign(this.gateBlock);
		updateOutput();
		sig.setLine(1, Double.toString((Double)this.gateOutputs.get(0)));
		sig.update();
		
	}
	public void updateOutput()
	{
		this.setOutput(0,gateBlock.getBlockPower()+0.0);
	}
}
