package com.d4l3k.Link.Gate.IO;

import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.craftbukkit.block.CraftSign;
import org.bukkit.event.block.SignChangeEvent;

import com.d4l3k.Link.BaseGate;
import com.d4l3k.Link.Core;

public class Command extends BaseGate{
	public Command(SignChangeEvent event)
	{
		super(event);
		this.gateName = "Command Executer";
		this.gateID = "[Command]";
		this.gatePerm = "restricted";
		this.addInput("Command", "double");
		this.addInput("Execute", "double");
	}
	public Command() {
		// TODO Auto-generated constructor stub
	}
	public void Execute(int input, Object oldval, Object newval)
	{
		
		CraftSign sig = new CraftSign(this.gateBlock);
		String msg = (String)this.getInput(0, "");
		if((Double)this.getInput(0, 0.0)>=1.0)
		{
			Core.server.dispatchCommand(new ConsoleCommandSender(Core.server), msg);
		}
		sig.setLine(1, msg);
		sig.update();
	}
}
