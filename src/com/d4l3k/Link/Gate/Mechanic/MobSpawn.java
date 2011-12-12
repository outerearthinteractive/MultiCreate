package com.d4l3k.Link.Gate.Mechanic;

import org.bukkit.Location;
import org.bukkit.craftbukkit.block.CraftSign;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Wolf;
import org.bukkit.event.block.SignChangeEvent;

import com.d4l3k.Link.BaseGate;

public class MobSpawn extends BaseGate{
	public MobSpawn(SignChangeEvent event)
	{
		super(event);
		this.gateName = "Mob Spawner";
		this.gateID = "[MobSpawn]";
		this.gatePerm = "danger";
		
		this.addInput("Mob", "string");
		this.addInput("Target", "location");
		this.addInput("Execute", "double");
	}
	public MobSpawn() {
		// Auto-generated constructor stub
	}
	public void Execute(int input, Object oldval, Object newval)
	{
		CreatureType[] creatures = CreatureType.values();
		String mob = (String)this.getInput(0, "");
		if((Double)this.getInput(2, 0.0)>=1.0)
		{
			Location loc = (Location)this.getInput(1, gateBlock.getLocation());
			for(int i=0;i<creatures.length;i++)
			{
				if(creatures[i].name().equalsIgnoreCase(mob))
				{
					gateBlock.getWorld().spawnCreature(loc, creatures[i]);
				}
			}
			if(mob.equalsIgnoreCase("pigzombie"))
			{
				gateBlock.getWorld().spawnCreature(loc, CreatureType.PIG_ZOMBIE);
			}
			else if(mob.equalsIgnoreCase("dog"))
			{
				LivingEntity mobA = gateBlock.getWorld().spawnCreature(loc, CreatureType.WOLF);
				((Wolf) mobA).setSitting(true);
			}
			else if(mob.equalsIgnoreCase("angrywolf"))
			{
				LivingEntity mobA = gateBlock.getWorld().spawnCreature(loc, CreatureType.WOLF);
				((Wolf) mobA).setAngry(true);
			}
		}
		CraftSign sig = new CraftSign(this.gateBlock);
		sig.setLine(1, mob);
		sig.update();
	}
}
