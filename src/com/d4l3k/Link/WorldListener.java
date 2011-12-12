package com.d4l3k.Link;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPistonRetractEvent;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.PistonBaseMaterial;

import com.d4l3k.Link.Gate.Mechanic.SetPiston;

public class WorldListener extends BlockListener{
	public static Core plugin; 
	public WorldListener(Core instance) { 
        plugin = instance;
	}
	public void onSignChange(SignChangeEvent event)
	{
		String type = event.getLine(0);
		BaseGate obj = GateConfig.newBaseGate(type, event);
		
		if(obj==null)
			return;
		
		if (!Core.permissionHandler.has(event.getPlayer(), "link.build")&&!Core.permissionHandler.has(event.getPlayer(), "link."+obj.gatePerm)) {
			event.getPlayer().sendMessage("[LINK] "+ChatColor.RED+"Insufficient Permissions to create Gate!");
			event.getBlock().setType(Material.AIR);
			ItemStack iT = new ItemStack(Material.SIGN, 1);
	        event.getPlayer().getInventory().addItem(new ItemStack[] { iT });
	        return;
		}
		event.setLine(0, ChatColor.AQUA+obj.gateID);
		event.getPlayer().sendMessage("[LINK] "+ChatColor.GREEN+"Creation Sucessful! "+obj.gateName);
		Data.addGate(obj);
		Data.saveGates();
	}
	public void onBlockRedstoneChange(BlockRedstoneEvent event)
	{
		if(event.getBlock().getType().equals(Material.WALL_SIGN)||event.getBlock().getType().equals(Material.SIGN_POST))
		{
			BaseGate gate = Data.getBaseGate(event.getBlock());
			if(gate.gateID.equalsIgnoreCase("[RedstoneIn]"))
			{
				GateConfig.executeBaseGate(gate, -2, event.getOldCurrent(), event.getNewCurrent());
			}
		}
	}
	public void onBlockBreak(BlockBreakEvent event)
	{
		Material type = event.getBlock().getType();
		if(type.equals(Material.SIGN_POST)||type.equals(Material.WALL_SIGN))
		{
			BaseGate gate = Data.getBaseGate(event.getBlock());
			if(gate.gateID.equals(""))
				return;
			if(!Core.permissionHandler.has(event.getPlayer(), "link."+gate.gatePerm))
			{
				event.getPlayer().sendMessage("[LINK] "+ChatColor.RED+"Insufficient Permissions to destroy Gate!");
				event.setCancelled(true);
				return;
			}
			Data.removeGate(gate);
			Data.saveGates();
			event.getPlayer().sendMessage("[LINK] "+ChatColor.RED+"Gate Destroyed! "+gate.gateName);
		}
		else
		{
			Block[] destroyedBlocks = new Block[5];
			Block block = event.getBlock();
			World world = block.getWorld();
			destroyedBlocks[0] = (new Location(world, block.getX() + 1, block.getY(), block.getZ())).getBlock();
			destroyedBlocks[1] = (new Location(world, block.getX() - 1, block.getY(), block.getZ())).getBlock();
			destroyedBlocks[2] = (new Location(world, block.getX(), block.getY(), block.getZ() + 1)).getBlock();
			destroyedBlocks[3] = (new Location(world, block.getX(), block.getY(), block.getZ() - 1)).getBlock();
			destroyedBlocks[4] = (new Location(world, block.getX(), block.getY() + 1, block.getZ())).getBlock();
			for(int i=0;i<destroyedBlocks.length;i++)
			{
				Material type1 = destroyedBlocks[i].getType();
				if(type1.equals(Material.WALL_SIGN)||i==4&&type1.equals(Material.SIGN_POST))
				{
					BaseGate gate = Data.getBaseGate(destroyedBlocks[i]);
					if(!gate.gateID.equals(""))
					{
						if(!Core.permissionHandler.has(event.getPlayer(), "link."+gate.gatePerm))
						{
							event.getPlayer().sendMessage("[LINK] "+ChatColor.RED+"Insufficient Permissions to destroy Gate! "+gate.gateName);
							event.setCancelled(true);
						}
						else
						{
							Data.removeGate(gate);
							Data.saveGates();
							event.getPlayer().sendMessage("[LINK] "+ChatColor.RED+"Gate Destroyed! "+gate.gateName);
						}
					}
				}
			}
		}
	}
	public void destroyGate(BaseGate gate)
	{
		
	}
	public void onBlockPistonRetract(BlockPistonRetractEvent event)
	{
		PistonBaseMaterial piston = new PistonBaseMaterial(event.getBlock().getType());
		for(int i=0;i<Data.allGates.size();i++)
		{
			BaseGate gate = Data.allGates.get(i);
			if(gate.gateName.equals("[Piston]"))
			{
				if(((SetPiston)gate).piston.equals(piston))
				{
					event.setCancelled(true);
				}
			}
		}
	}

}
