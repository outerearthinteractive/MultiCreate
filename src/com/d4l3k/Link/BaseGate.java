package com.d4l3k.Link;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.event.block.SignChangeEvent;

public class BaseGate {
	
	public String gateName = "";
	public String gateID = "";
	public String gatePerm = "basic";
	public Block gateBlock;
	public ArrayList<String> gateInputNames = new ArrayList<String>();
	public ArrayList<String> gateInputTypes = new ArrayList<String>();
	public ArrayList<Block> gateInputs = new ArrayList<Block>();
	public ArrayList<Integer> gateInputIndexs = new ArrayList<Integer>();
	public ArrayList<String> gateOutputNames = new ArrayList<String>();
	public ArrayList<String> gateOutputTypes = new ArrayList<String>();
	public ArrayList<Object> gateOutputs = new ArrayList<Object>();
	public String gateStrData = "";
	public double gateDouData = 0.0;
	public boolean gateSelfTriggered = false;
	public BaseGate(SignChangeEvent event)
	{
		this.gateBlock = event.getBlock();
	}
	public BaseGate()
	{
		
	}
	public void addInput(String name, String type)
	{
		this.gateInputNames.add(name);
		this.gateInputTypes.add(type);
		this.gateInputs.add(null);
		this.gateInputIndexs.add(0);
	}
	public void addOutput(String name, String type)
	{
		Object default1 = 0.0;
		if(type.equalsIgnoreCase("string"))
		{
			default1 = "";
		} else if(type.equalsIgnoreCase("location"))
		{
			default1 = new GateLocation(gateBlock.getWorld());
		}
		this.addOutput(name,type,default1);
	}
	public void addOutput(String name, String type, Object default1)
	{
		this.gateOutputNames.add(name);
		this.gateOutputTypes.add(type);
		this.gateOutputs.add(default1);
	}
	public Object getInput(int index, Object escape)
	{
		Object obj = Data.getInput(this, index, escape);
		if(obj.getClass().equals(GateLocation.class))
		{
			obj = ((GateLocation)obj).getLocation();
		}
		else if(obj.getClass().equals(GateEntity.class))
		{
			obj = ((GateEntity)obj).getEntity();
		}
		return obj;
	}
	public void setOutput(int index, Object obj)
	{
		if(!gateOutputs.get(index).equals(obj))
		{
			if(obj.getClass().equals(Location.class))
			{
				obj = new GateLocation((Location)obj);
			} 
			else if(obj.getClass().equals(Entity.class))
			{
				obj = new GateEntity((Entity)obj);
			}
			Object oldobj = this.gateOutputs.get(index);
			this.gateOutputs.set(index, obj);
			Data.updateInput(gateBlock, index, oldobj, obj);
		}
	}
	public void Execute(int input, Object oldval, Object newval)
	{
		//input = -1 for self tick
		//input = -2 for other (like redstone change)
	}
}