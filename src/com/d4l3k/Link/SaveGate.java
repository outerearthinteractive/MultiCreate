package com.d4l3k.Link;

import java.io.Serializable;
import java.util.ArrayList;

public class SaveGate implements Serializable {
	
	
	
	private static final long serialVersionUID = 1L;

	public String gateName = "";
	public String gateID = "";
	public String gatePerm = "basic";
	public GateBlock gateBlock;
	public ArrayList<String> gateInputNames = new ArrayList<String>();
	public ArrayList<String> gateInputTypes = new ArrayList<String>();
	public ArrayList<GateBlock> gateInputs = new ArrayList<GateBlock>();
	public ArrayList<Integer> gateInputIndexs = new ArrayList<Integer>();
	public ArrayList<String> gateOutputNames = new ArrayList<String>();
	public ArrayList<String> gateOutputTypes = new ArrayList<String>();
	public ArrayList<Object> gateOutputs = new ArrayList<Object>();
	public String gateStrData = "";
	public Double gateDouData = 0.0;
	public Boolean gateSelfTriggered = false;
	public SaveGate(BaseGate gate)
	{
		gateName = gate.gateName;
		gateID = gate.gateID;
		gateBlock = new GateBlock(gate.gateBlock);
		gateInputNames = gate.gateInputNames;
		gateInputTypes = gate.gateInputTypes;
		for(int i=0;i<gate.gateInputs.size();i++)
		{
			gateInputs.add(new GateBlock(gate.gateInputs.get(i)));
		}
		gateInputIndexs = gate.gateInputIndexs;
		gateOutputNames = gate.gateOutputNames;
		gateOutputTypes = gate.gateOutputTypes;
		gateOutputs = gate.gateOutputs;
		gateStrData = gate.gateStrData;
		gateDouData = gate.gateDouData;
		gateSelfTriggered = gate.gateSelfTriggered;
	}
	public BaseGate getBaseGate()
	{
		BaseGate gate = GateConfig.newBaseGate(gateID);
		gate.gateName = gateName;
		gate.gateID = gateID;
		gate.gateBlock = gateBlock.getBlock();
		gate.gateInputNames = gateInputNames;
		gate.gateInputTypes = gateInputTypes;
		for(int i=0;i<gateInputs.size();i++)
		{
			gate.gateInputs.add(gateInputs.get(i).getBlock());
		}
		gate.gateInputIndexs = gateInputIndexs;
		gate.gateOutputNames = gateOutputNames;
		gate.gateOutputTypes = gateOutputTypes;
		gate.gateOutputs = gateOutputs;
		gate.gateStrData = gateStrData;
		gate.gateDouData = gateDouData;
		gate.gateSelfTriggered = gateSelfTriggered;
		return gate;
	}
}
