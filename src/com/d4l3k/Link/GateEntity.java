package com.d4l3k.Link;


import java.io.Serializable;
import java.util.List;

import org.bukkit.World;
import org.bukkit.entity.Entity;

public class GateEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int entity;
	private World world;
	
	public GateEntity(Entity ent)
	{
		entity = ent.getEntityId();
		world = ent.getWorld();
	}
	public Entity getEntity()
	{
		List<Entity> ents = world.getEntities();
		for(int i = 0;i<ents.size();i++)
		{
			Entity curEnt = ents.get(i);
			if(curEnt.getEntityId()==entity)
			{
				return curEnt;
			}
		}
		return null;
	}
	
}
