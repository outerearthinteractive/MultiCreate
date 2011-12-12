package com.d4l3k.Link;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.block.CraftSign;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;

public class InteractListener extends PlayerListener {
	public static Core plugin; 
	public InteractListener(Core instance) { 
        plugin = instance;
	}
	public void onPlayerInteract(PlayerInteractEvent event)
	{
		if(!event.getAction().equals(Action.LEFT_CLICK_BLOCK)&&!event.getAction().equals(Action.RIGHT_CLICK_BLOCK))
			return;
		Player plr = event.getPlayer();
		if(!Data.playerEditMode.containsKey(plr))
		{
			Data.playerEditMode.put(plr, false);
		}
		
		if(Data.playerEditMode.get(plr))
		{
			Block block = event.getClickedBlock();
			Material mat = block.getType();
			if(mat.equals(Material.SIGN_POST)||mat.equals(Material.WALL_SIGN))
			{
				int status = Data.playerEditStatus.get(plr);
				BaseGate gate = Data.getBaseGate(block);
				if(gate.equals(new BaseGate()))
					return;
				if(status == 0)
				{
					if(gate.gateInputNames.size()==0)
					{
						plr.sendMessage("[LINK] "+ChatColor.RED+"Gate has no Input Values. Canceling...");
						Data.playerEditStatus.put(plr,0);
						return;
					}
					if(!Core.permissionHandler.has(event.getPlayer(), "link."+gate.gatePerm))
					{
						plr.sendMessage("[LINK] "+ChatColor.RED+"Insufficient Permissions to edit Gate!");
						Data.playerEditStatus.put(plr,0);
						return;
					}
					Data.playerEditSelection.put(plr,0);
					Data.playerEditFirstGate.put(plr, gate.gateBlock);
					ChatColor clr = ChatColor.RED;
					if(!Data.getBaseGate(gate.gateInputs.get(0)).gateName.equalsIgnoreCase(""))
					{
						clr = ChatColor.GREEN;
					}
					CraftSign sig = new CraftSign(gate.gateBlock);
					sig.setLine(2, clr+gate.gateInputNames.get(0));
					sig.setLine(3, clr+gate.gateInputTypes.get(0));
					sig.update();
					Data.playerEditStatus.put(plr,1);
				}
				if(status == 1)
				{
					if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK))
					{
						int data = Data.playerEditSelection.get(plr);
						BaseGate gateGood = Data.getBaseGate(Data.playerEditFirstGate.get(plr));
						data++;
						if(data>=gateGood.gateInputNames.size())
						{
							data = 0;
						}
						Data.playerEditSelection.put(plr,data);
						CraftSign sig = new CraftSign(gateGood.gateBlock);
						ChatColor clr = ChatColor.RED;
						if(!Data.getBaseGate(gateGood.gateInputs.get(data)).gateName.equalsIgnoreCase(""))
						{
							clr = ChatColor.GREEN;
						}
						sig.setLine(2, clr+gateGood.gateInputNames.get(data));
						sig.setLine(3, clr+gateGood.gateInputTypes.get(data));
						sig.update();
					}
					if(event.getAction().equals(Action.LEFT_CLICK_BLOCK))
					{
						plr.sendMessage("[LINK] "+ChatColor.GOLD+"First Gate Selected! Select second now!");
						BaseGate gateGood = Data.getBaseGate(Data.playerEditFirstGate.get(plr));
						CraftSign sig = new CraftSign(gateGood.gateBlock);
						sig.setLine(2, "");
						sig.setLine(3, "");
						sig.update();
						Data.playerEditStatus.put(plr,2);
					}
				}
				if(status == 2)
				{
					if(gate.gateOutputNames.size()==0)
					{
						plr.sendMessage("[LINK] "+ChatColor.RED+"Gate has no Output Values. Canceling...");
						Data.playerEditStatus.put(plr,0);
						return;
					}
					Data.playerEditSecondSelection.put(plr,0);
					Data.playerEditSecondGate.put(plr, gate.gateBlock);
					CraftSign sig = new CraftSign(gate.gateBlock);
					sig.setLine(2, gate.gateOutputNames.get(0));
					sig.setLine(3, gate.gateOutputTypes.get(0));
					sig.update();
					Data.playerEditStatus.put(plr,3);
				}
				if(status == 3)
				{
					if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK))
					{
						int data = Data.playerEditSecondSelection.get(plr);
						BaseGate gateGood = Data.getBaseGate(Data.playerEditSecondGate.get(plr));
						data++;
						if(data>=gateGood.gateOutputNames.size())
						{
							data = 0;
						}
						Data.playerEditSecondSelection.put(plr,data);
						CraftSign sig = new CraftSign(gateGood.gateBlock);
						sig.setLine(2, gateGood.gateOutputNames.get(data));
						sig.setLine(3, gateGood.gateOutputTypes.get(data));
						sig.update();
					}
					if(event.getAction().equals(Action.LEFT_CLICK_BLOCK))
					{
						BaseGate firstGate = Data.getBaseGate(Data.playerEditFirstGate.get(plr));
						BaseGate secondGate = Data.getBaseGate(Data.playerEditSecondGate.get(plr));
						int firstSelection = Data.playerEditSelection.get(plr);
						int secondSelection = Data.playerEditSecondSelection.get(plr);
						String type = secondGate.gateOutputTypes.get(secondSelection);
						String type1 = firstGate.gateInputTypes.get(firstSelection);
						if(!type.equalsIgnoreCase(type1))
						{
							plr.sendMessage("[LINK] "+ChatColor.RED+"Wrong output type! Must be: "+type1);
							return;
						}
						firstGate.gateInputs.set(firstSelection, Data.playerEditSecondGate.get(plr));
						firstGate.gateInputIndexs.set(firstSelection, secondSelection);
						Data.updateGate(firstGate);
						Object val = secondGate.gateOutputs.get(secondSelection);
						Data.updateInput(secondGate.gateBlock, secondSelection, val, val);
						CraftSign sig = new CraftSign(secondGate.gateBlock);
						sig.setLine(2, "");
						sig.setLine(3, "");
						sig.update();
						Data.playerEditStatus.put(plr, 0);
						Data.saveGates();
						plr.sendMessage("[LINK] "+ChatColor.GREEN+"Gates Linked!");
					}
				}
			}
		}
		else
		{
			Block block = event.getClickedBlock();
			Material mat = block.getType();
			if(mat.equals(Material.SIGN_POST)||mat.equals(Material.WALL_SIGN))
			{
				BaseGate gate = Data.getBaseGate(block);
				GateConfig.interactBaseGate(gate, plr);
			}
		}
	}
}
