package com.d4l3k.Link.Gate.Mechanic;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.block.CraftSign;
import org.bukkit.entity.Player;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.material.PistonBaseMaterial;

import com.d4l3k.Link.BaseGate;
import com.d4l3k.Link.Core;

public class SetPiston extends BaseGate{
	public PistonBaseMaterial piston;
	public boolean pistonPower;
	public SetPiston(SignChangeEvent event)
	{
		super(event);
		this.gateName = "Set Piston";
		this.gateID = "[Piston]";
		this.addOutput("Status", "double");
		this.addInput("Piston", "location");
		this.addInput("Position", "double");
	}
	public SetPiston() {
		// TODO Auto-generated constructor stub
	}
	public void Execute(int input, Object oldval, Object newval)
	{
		if(input==1)
		{
			Location pos = (Location)this.getInput(0, new Location(this.gateBlock.getWorld(),0.0,0.0,0.0));
			this.piston = new PistonBaseMaterial(pos.getBlock().getType());
			if((Double)newval>=1)
			{
				piston.setPowered(true);
				this.pistonPower = true;
			}
			else
			{
				piston.setPowered(false);
				this.pistonPower = false;
			}
		}
	}
}
