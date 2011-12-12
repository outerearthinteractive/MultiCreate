package com.d4l3k.Link.Gate.IO;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.craftbukkit.block.CraftSign;
import org.bukkit.entity.Entity;
import org.bukkit.event.block.SignChangeEvent;

import com.d4l3k.Link.BaseGate;

public class EntityDetect extends BaseGate{
	public EntityDetect(SignChangeEvent event)
	{
		super(event);
		this.gateName = "Entity Detect";
		this.gateID = "[EntDetect]";
		this.addOutput("Ent", "entity");
		this.addOutput("Location", "location");
		this.addOutput("NumDetected", "double");

		this.addInput("Update", "double");
		this.addInput("Distance", "double");
		this.addInput("Next", "double");
	}
	public EntityDetect() {
		// TODO Auto-generated constructor stub
	}
	public void Execute(int input, Object oldval, Object newval)
	{
		double Dist = (Double)this.getInput(1, 5.0);
		ArrayList<Entity> entity = new ArrayList<Entity>();
		List<Entity> entitys = gateBlock.getWorld().getEntities();
		for(int i = 0; i < entitys.size(); i++)
		{
			if(entitys.get(i).getWorld().equals(gateBlock.getWorld()))
			{
				Location loc = entitys.get(i).getLocation();
				Location ourLoc = gateBlock.getLocation();
				double xDist = ourLoc.getX()-loc.getX();
				double yDist = ourLoc.getY()-loc.getY();
				double zDist = ourLoc.getZ()-loc.getZ();
				if(xDist<=Dist&&-xDist<=Dist&&yDist<=Dist&&-yDist<=Dist&&zDist<=Dist&&-zDist<=Dist)
				{
					entity.add(entitys.get(i));
				}
			}
			
		}
		if(input==2&&(Double)newval>=1.0&&(Double)oldval<1.0)
		{
			this.gateDouData++;
		}
		Entity selectEntity = (Entity) new CraftSign(gateBlock);
		Location loc = new Location(gateBlock.getWorld(),0,0,0);
		if(gateDouData>=entity.size())
		{
			gateDouData = 0.0;
		}
		if(entity.size()>=1)
		{
			selectEntity = entity.get((int)gateDouData);
			loc = entity.get((int)gateDouData).getLocation();
		}
		this.setOutput(0, entity.get((int)gateDouData));
		this.setOutput(1, loc);
		this.setOutput(2, entity.size());
		CraftSign sig = new CraftSign(this.gateBlock);
		sig.setLine(1, "T: "+selectEntity.toString());
		sig.update();
	}
}
