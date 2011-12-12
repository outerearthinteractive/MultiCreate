package com.d4l3k.Link;

import org.bukkit.entity.Player;
import org.bukkit.event.block.SignChangeEvent;

import com.d4l3k.Link.Gate.IO.*;
import com.d4l3k.Link.Gate.Mechanic.*;
import com.d4l3k.Link.Gate.Double.*;
import com.d4l3k.Link.Gate.String.*;
import com.d4l3k.Link.Gate.Location.*;
import com.d4l3k.Link.Gate.Time.*;

public class GateConfig {
	//Handles executing gates.
	public static void executeBaseGate(BaseGate gate, int input, Object oldval, Object newval) 
	{
		String ID = gate.gateID;
		if(ID.equalsIgnoreCase("[Constant]"))
			((ConstantValue)gate).Execute(input,oldval,newval);
		else if(ID.equalsIgnoreCase("[Display]"))
			((Display)gate).Execute(input,oldval,newval);
		else if(ID.equalsIgnoreCase("[Toggle]"))
			((Toggle)gate).Execute(input,oldval,newval);
		else if(ID.equalsIgnoreCase("[RedstoneIn]"))
			((RedstoneIn)gate).Execute(input,oldval,newval);
		else if(ID.equalsIgnoreCase("[RedstoneOut]"))
			((RedstoneOut)gate).Execute(input,oldval,newval);
		else if(ID.equalsIgnoreCase("[StringEqual]"))
			((StringEqual)gate).Execute(input,oldval,newval);
		else if(ID.equalsIgnoreCase("[StringAdd]"))
			((StringAdd)gate).Execute(input,oldval,newval);
		else if(ID.equalsIgnoreCase("[Equal]"))
			((DoubleEqual)gate).Execute(input,oldval,newval);
		else if(ID.equalsIgnoreCase("[Add]"))
			((DoubleAdd)gate).Execute(input,oldval,newval);
		else if(ID.equalsIgnoreCase("[Divide]"))
			((DoubleDivide)gate).Execute(input,oldval,newval);
		else if(ID.equalsIgnoreCase("[Greater]"))
			((DoubleGreater)gate).Execute(input,oldval,newval);
		else if(ID.equalsIgnoreCase("[GreaterEqual]"))
			((DoubleGreaterEqual)gate).Execute(input,oldval,newval);
		else if(ID.equalsIgnoreCase("[Less]"))
			((DoubleLess)gate).Execute(input,oldval,newval);
		else if(ID.equalsIgnoreCase("[LessEqual]"))
			((DoubleLessEqual)gate).Execute(input,oldval,newval);
		else if(ID.equalsIgnoreCase("[Multiply]"))
			((DoubleMultiply)gate).Execute(input,oldval,newval);
		else if(ID.equalsIgnoreCase("[Subtract]"))
			((DoubleSubtract)gate).Execute(input,oldval,newval);
		else if(ID.equalsIgnoreCase("[ToString]"))
			((DoubleToString)gate).Execute(input,oldval,newval);
		else if(ID.equalsIgnoreCase("[Pos]"))
			((Position)gate).Execute(input,oldval,newval);
		else if(ID.equalsIgnoreCase("[PlayerPos]"))
			((PlayerPosition)gate).Execute(input,oldval,newval);
		else if(ID.equalsIgnoreCase("[PlayerDetect]"))
			((PlayerDetect)gate).Execute(input,oldval,newval);
		else if(ID.equalsIgnoreCase("[Teleport]"))
			((Teleport)gate).Execute(input,oldval,newval);
		else if(ID.equalsIgnoreCase("[LocMake]"))
			((LocationMake)gate).Execute(input,oldval,newval);
		else if(ID.equalsIgnoreCase("[LocSplit]"))
			((LocationSplit)gate).Execute(input,oldval,newval);
		else if(ID.equalsIgnoreCase("[Command]"))
			((Command)gate).Execute(input,oldval,newval);
		else if(ID.equalsIgnoreCase("[IfString]"))
			((IfString)gate).Execute(input,oldval,newval);
		else if(ID.equalsIgnoreCase("[Inverse]"))
			((DoubleInverse)gate).Execute(input,oldval,newval);
		else if(ID.equalsIgnoreCase("[StringToggle]"))
			((DoubleInverse)gate).Execute(input,oldval,newval);
		else if(ID.equalsIgnoreCase("[LocAdd]"))
			((LocationAdd)gate).Execute(input,oldval,newval);
		else if(ID.equalsIgnoreCase("[LocSubtract]"))
			((LocationSubtract)gate).Execute(input,oldval,newval);
		else if(ID.equalsIgnoreCase("[MobSpawn]"))
			((MobSpawn)gate).Execute(input,oldval,newval);
		else if(ID.equalsIgnoreCase("[Button]"))
			((Button)gate).Execute(input,oldval,newval);
		else if(ID.equalsIgnoreCase("[Clock]"))
			((Clock)gate).Execute(input,oldval,newval);
		else if(ID.equalsIgnoreCase("[Counter]"))
			((Counter)gate).Execute(input,oldval,newval);
		else if(ID.equalsIgnoreCase("[Latch]"))
			((Latch)gate).Execute(input,oldval,newval);
		else if(ID.equalsIgnoreCase("[GetTime]"))
			((GetTime)gate).Execute(input,oldval,newval);
		else if(ID.equalsIgnoreCase("[SetTime]"))
			((SetTime)gate).Execute(input,oldval,newval);
		else if(ID.equalsIgnoreCase("[All]"))
			((DoubleAll)gate).Execute(input,oldval,newval);
		else if(ID.equalsIgnoreCase("[Any]"))
			((DoubleAny)gate).Execute(input,oldval,newval);
		else if(ID.equalsIgnoreCase("[Turret]"))
			((Turret)gate).Execute(input,oldval,newval);
	}
	// Gates that execute on click.
	public static void interactBaseGate(BaseGate gate, Player player)
	{
		String ID = gate.gateID;
		if(ID.equalsIgnoreCase("[Toggle]"))
			((Toggle)gate).Click(player);
		else if(ID.equalsIgnoreCase("[StringToggle]"))
			((StringToggle)gate).Click(player);
		else if(ID.equalsIgnoreCase("[Button]"))
			((Button)gate).Click(player);
	}
	// Handles creating new gates. You can add aliases here. Just add a ||type.equalsIgnoreCase("[<alias>]").
	public static BaseGate newBaseGate(String type, SignChangeEvent event)
	{
		BaseGate obj = null;
		if(type.equalsIgnoreCase("[Constant]"))
			obj =  new ConstantValue(event);
		else if(type.equalsIgnoreCase("[Display]"))
			obj =  new Display(event);
		else if(type.equalsIgnoreCase("[Toggle]"))
			obj =  new Toggle(event);
		else if(type.equalsIgnoreCase("[RedstoneIn]"))
			obj =  new RedstoneIn(event);
		else if(type.equalsIgnoreCase("[RedstoneOut]"))
			obj =  new RedstoneOut(event);
		else if(type.equalsIgnoreCase("[StringEqual]"))
			obj =  new StringEqual(event);
		else if(type.equalsIgnoreCase("[StringAdd]"))
			obj =  new StringAdd(event);
		else if(type.equalsIgnoreCase("[Equal]")||type.equalsIgnoreCase("[==]"))
			obj =  new DoubleEqual(event);
		else if(type.equalsIgnoreCase("[Add]")||type.equalsIgnoreCase("[+]"))
			obj =  new DoubleAdd(event);
		else if(type.equalsIgnoreCase("[Divide]")||type.equalsIgnoreCase("[/]"))
			obj =  new DoubleDivide(event);
		else if(type.equalsIgnoreCase("[Greater]")||type.equalsIgnoreCase("[>]"))
			obj =  new DoubleGreater(event);
		else if(type.equalsIgnoreCase("[GreaterEqual]")||type.equalsIgnoreCase("[>=]"))
			obj =  new DoubleGreaterEqual(event);
		else if(type.equalsIgnoreCase("[Less]")||type.equalsIgnoreCase("[<]"))
			obj =  new DoubleLess(event);
		else if(type.equalsIgnoreCase("[LessEqual]")||type.equalsIgnoreCase("[<=]"))
			obj =  new DoubleLessEqual(event);
		else if(type.equalsIgnoreCase("[Multiply]")||type.equalsIgnoreCase("[*]"))
			obj =  new DoubleMultiply(event);
		else if(type.equalsIgnoreCase("[Subtract]")||type.equalsIgnoreCase("[-]"))
			obj =  new DoubleSubtract(event);
		else if(type.equalsIgnoreCase("[ToString]"))
			obj =  new DoubleToString(event);
		else if(type.equalsIgnoreCase("[Pos]")||type.equalsIgnoreCase("[Loc]"))
			obj =  new Position(event);
		else if(type.equalsIgnoreCase("[PlayerPos]")||type.equalsIgnoreCase("[PPos]"))
			obj =  new PlayerPosition(event);
		else if(type.equalsIgnoreCase("[PlayerDetect]")||type.equalsIgnoreCase("[PDetect]"))
			obj =  new PlayerDetect(event);
		else if(type.equalsIgnoreCase("[Teleport]"))
			obj =  new Teleport(event);
		else if(type.equalsIgnoreCase("[LocMake]"))
			obj =  new LocationMake(event);
		else if(type.equalsIgnoreCase("[LocSplit]"))
			obj =  new LocationSplit(event);
		else if(type.equalsIgnoreCase("[Command]"))
			obj =  new Command(event);
		else if(type.equalsIgnoreCase("[IfString]"))
			obj =  new IfString(event);
		else if(type.equalsIgnoreCase("[Inverse]"))
			obj =  new DoubleInverse(event);
		else if(type.equalsIgnoreCase("[StringToggle]"))
			obj =  new StringToggle(event);
		else if(type.equalsIgnoreCase("[LocAdd]"))
			obj =  new LocationAdd(event);
		else if(type.equalsIgnoreCase("[LocSubtract]"))
			obj =  new LocationSubtract(event);
		else if(type.equalsIgnoreCase("[MobSpawn]"))
			obj =  new MobSpawn(event);
		else if(type.equalsIgnoreCase("[Button]"))
			obj =  new Button(event);
		else if(type.equalsIgnoreCase("[Clock]"))
			obj = new Clock(event);
		else if(type.equalsIgnoreCase("[Counter]"))
			obj = new Counter(event);
		else if(type.equalsIgnoreCase("[Latch]"))
			obj = new Latch(event);
		else if(type.equalsIgnoreCase("[GetTime]"))
			obj = new GetTime(event);
		else if(type.equalsIgnoreCase("[SetTime]"))
			obj = new SetTime(event);
		else if(type.equalsIgnoreCase("[All]"))
			obj = new DoubleAll(event);
		else if(type.equalsIgnoreCase("[Any]"))
			obj = new DoubleAny(event);
		else if(type.equalsIgnoreCase("[Turret]"))
			obj = new Turret(event);
		
		return obj;
	}
	// Handles creating new gates without initializing them, for serialization
	public static BaseGate newBaseGate(String type)
	{
		BaseGate obj = null;
		if(type.equalsIgnoreCase("[Constant]"))
			obj =  new ConstantValue();
		else if(type.equalsIgnoreCase("[Display]"))
			obj =  new Display();
		else if(type.equalsIgnoreCase("[Toggle]"))
			obj =  new Toggle();
		else if(type.equalsIgnoreCase("[RedstoneIn]"))
			obj =  new RedstoneIn();
		else if(type.equalsIgnoreCase("[RedstoneOut]"))
			obj =  new RedstoneOut();
		else if(type.equalsIgnoreCase("[StringEqual]"))
			obj =  new StringEqual();
		else if(type.equalsIgnoreCase("[StringAdd]"))
			obj =  new StringAdd();
		else if(type.equalsIgnoreCase("[Equal]"))
			obj =  new DoubleEqual();
		else if(type.equalsIgnoreCase("[Add]"))
			obj =  new DoubleAdd();
		else if(type.equalsIgnoreCase("[Divide]"))
			obj =  new DoubleDivide();
		else if(type.equalsIgnoreCase("[Greater]"))
			obj =  new DoubleGreater();
		else if(type.equalsIgnoreCase("[GreaterEqual]"))
			obj =  new DoubleGreaterEqual();
		else if(type.equalsIgnoreCase("[Less]"))
			obj =  new DoubleLess();
		else if(type.equalsIgnoreCase("[LessEqual]"))
			obj =  new DoubleLessEqual();
		else if(type.equalsIgnoreCase("[Multiply]"))
			obj =  new DoubleMultiply();
		else if(type.equalsIgnoreCase("[Subtract]"))
			obj =  new DoubleSubtract();
		else if(type.equalsIgnoreCase("[ToString]"))
			obj =  new DoubleToString();
		else if(type.equalsIgnoreCase("[Pos]"))
			obj =  new Position();
		else if(type.equalsIgnoreCase("[PlayerPos]"))
			obj =  new PlayerPosition();
		else if(type.equalsIgnoreCase("[PlayerDetect]"))
			obj =  new PlayerDetect();
		else if(type.equalsIgnoreCase("[Teleport]"))
			obj =  new Teleport();
		else if(type.equalsIgnoreCase("[LocMake]"))
			obj =  new LocationMake();
		else if(type.equalsIgnoreCase("[LocSplit]"))
			obj =  new LocationSplit();
		else if(type.equalsIgnoreCase("[Command]"))
			obj =  new Command();
		else if(type.equalsIgnoreCase("[IfString]"))
			obj =  new IfString();
		else if(type.equalsIgnoreCase("[Inverse]"))
			obj =  new DoubleInverse();
		else if(type.equalsIgnoreCase("[StringToggle]"))
			obj =  new StringToggle();
		else if(type.equalsIgnoreCase("[LocAdd]"))
			obj =  new LocationAdd();
		else if(type.equalsIgnoreCase("[LocSubtract]"))
			obj =  new LocationSubtract();
		else if(type.equalsIgnoreCase("[MobSpawn]"))
			obj =  new MobSpawn();
		else if(type.equalsIgnoreCase("[Button]"))
			obj =  new Button();
		else if(type.equalsIgnoreCase("[Clock]"))
			obj =  new Clock();
		else if(type.equalsIgnoreCase("[Counter]"))
			obj =  new Counter();
		else if(type.equalsIgnoreCase("[Latch]"))
			obj =  new Latch();
		else if(type.equalsIgnoreCase("[GetTime]"))
			obj = new GetTime();
		else if(type.equalsIgnoreCase("[SetTime]"))
			obj = new SetTime();
		else if(type.equalsIgnoreCase("[All]"))
			obj = new DoubleAll();
		else if(type.equalsIgnoreCase("[Any]"))
			obj = new DoubleAny();
		else if(type.equalsIgnoreCase("[Turret]"))
			obj = new Turret();
		
		return obj;
	}
}
