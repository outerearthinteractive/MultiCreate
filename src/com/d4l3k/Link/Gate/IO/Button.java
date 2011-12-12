package com.d4l3k.Link.Gate.IO;

import org.bukkit.craftbukkit.block.CraftSign;
import org.bukkit.entity.Player;
import org.bukkit.event.block.SignChangeEvent;

import com.d4l3k.Link.BaseGate;

public class Button extends BaseGate{
	public Button(SignChangeEvent event)
	{
		super(event);
		this.gateName = "Value Button";
		this.gateID = "[Button]";
		this.addOutput("Pressed", "double");
		this.gateStrData = event.getLine(1)+event.getLine(2)+event.getLine(3);
		double tog = 0.0;
		String[] parse = this.gateStrData.split(",");
		try
		{
			tog = Double.parseDouble(parse[0]);
		} catch(Exception ex) {} // Catches can be empty cause we set the alt value above w/ tog.
		this.setOutput(0, tog);
		double output = 0.0;
		try
		{
			output = Double.parseDouble(parse[parse.length-1]);
		} catch(Exception ex) {}
		event.setLine(1, Double.toString(output));
	}
	public Button() {
		// TODO Auto-generated constructor stub
	}
	public void Click(Player player) {
		CraftSign sig = new CraftSign(this.gateBlock);
		String[] parse = this.gateStrData.split(",");
		
		for(int i=1;i<parse.length;i++)
		{
			double tog = 0.0;
			try
			{
				tog = Double.parseDouble(parse[i]);
			}
			catch(Exception ex){}
			this.setOutput(0, tog);
			sig.setLine(1, Double.toString(tog));
		}
		double tog = 0.0;
		try
		{
			tog = Double.parseDouble(parse[0]);
		}
		catch(Exception ex){}
		this.setOutput(0, tog);
		sig.update();
		
	}
}
