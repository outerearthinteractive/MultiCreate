package com.d4l3k.Link.Gate.IO;

import org.bukkit.craftbukkit.block.CraftSign;
import org.bukkit.entity.Player;
import org.bukkit.event.block.SignChangeEvent;

import com.d4l3k.Link.BaseGate;

public class Toggle extends BaseGate{
	public Toggle(SignChangeEvent event)
	{
		super(event);
		this.gateName = "Value Toggle";
		this.gateID = "[Toggle]";
		this.addOutput("Value", "double");
		
		this.gateStrData = event.getLine(1)+event.getLine(2)+event.getLine(3);
		this.gateDouData = 0.0;
		updateOutput();
		event.setLine(1, Double.toString((Double)this.gateOutputs.get(0)));
	}
	public Toggle() {
		// TODO Auto-generated constructor stub
	}
	public void Execute(int input, Object oldval, Object newval)
	{
		
		
	}
	public void updateOutput()
	{
		String[] parse = this.gateStrData.split(",");
		double tog = 0.0;
		try
		{
			int index = (int) Math.round(gateDouData);
			tog = Double.parseDouble(parse[index]);
		}
		catch(Exception ex)
		{
			
		}
		this.setOutput(0, tog);
		this.gateDouData+=1;
		if(this.gateDouData>=parse.length)
		{
			this.gateDouData=0.0;
		}
	}
	public void Click(Player player) {
		CraftSign sig = new CraftSign(this.gateBlock);
		updateOutput();
		sig.setLine(1, Double.toString((Double)this.gateOutputs.get(0)));
		sig.update();
	}
}
