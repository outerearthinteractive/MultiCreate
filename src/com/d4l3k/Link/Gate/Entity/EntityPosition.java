package com.d4l3k.Link.Gate.Entity;

import org.bukkit.Location;
import org.bukkit.craftbukkit.block.CraftSign;
import org.bukkit.entity.Entity;
import org.bukkit.event.block.SignChangeEvent;

import com.d4l3k.Link.BaseGate;

public class EntityPosition extends BaseGate{
	public EntityPosition(SignChangeEvent event)
	{
		super(event);
		this.gateName = "Entity Position";
		this.gateID = "[EntPos]";
		this.addOutput("Location", "location");
		this.addInput("Update", "double");
		this.addInput("Ent", "entity");
	}
	public EntityPosition() {
		// TODO Auto-generated constructor stub
	}
	public void Execute(int input, Object oldval, Object newval)
	{
		if(input==1&&(Double)oldval<1.0&&(Double)newval>=1.0)
		{
			Location pos = gateBlock.getLocation();
			pos = ((Entity)this.getInput(1, (Entity) new CraftSign(gateBlock))).getLocation();
			this.setOutput(0, pos);
			CraftSign sig = new CraftSign(this.gateBlock);
			sig.setLine(1, "T: "+gateBlock.getWorld().getName());
			sig.update();
		}
	}
}
