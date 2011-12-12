package com.d4l3k.Link.Gate.Time;

import org.bukkit.event.block.SignChangeEvent;

import com.d4l3k.Link.BaseGate;
import com.d4l3k.Link.Data;

public class Clock extends BaseGate{
	public Clock(SignChangeEvent event)
	{
		super(event);
		this.gateName = "Triggered Clock";
		this.gateID = "[Clock]";
		this.addOutput("Tick", "double");
		this.addInput("TickRate","double");
		this.addInput("Value","double");
		this.addInput("Reset","double");
		this.gateSelfTriggered = true;
	}
	public Clock() {
	}
	public void Execute(int input, Object oldval, Object newval)
	{
		if(((Double)this.getInput(2, 0.0))>=1.0)
		{
			gateDouData = 0.0;
		}
		else
		{
			Double tickRate = (Double)this.getInput(0, 10.0);
			gateDouData+=Data.serverTickRate;
			if(gateDouData>=tickRate)
			{
				this.setOutput(0, 0.0);
				this.setOutput(0, (Double)this.getInput(1, 1.0));
				gateDouData -= tickRate;
			}
			
		}
	}
}
