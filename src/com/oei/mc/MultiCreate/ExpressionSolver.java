package com.oei.mc.MultiCreate;

import java.util.ArrayList;

import org.bukkit.Location;

//This class will solve equations. How neat!

public class ExpressionSolver {
	private boolean solved = false; //Switched on upon solve, switched off when switching.
	private boolean result = false; //default action, harmless.
	
	private String eqn_x = "";
	private String eqn_y = "";
	private String eqn_z = "";
	
	private boolean solve_x = false;
	private boolean solve_y = false;
	private boolean solve_z = false;
	
	
	public ExpressionSolver(ArrayList<String> vars, ArrayList<String> equations){
		if(vars.contains("x")){
			solve_x = true;
			eqn_x = equations.get(vars.indexOf("x"));
		}
		if(vars.contains("y")){
			solve_y = true;
			eqn_y = equations.get(vars.indexOf("y"));
		}
		if(vars.contains("z")){
			solve_z = true;
			eqn_z = equations.get(vars.indexOf("z"));
		}
	}
	public boolean getResult(){
		if(result){
			return true;
		} else {
			return false;
		}
	}
	public void solveNow(Location pos){
		boolean objection = true;//is there any objection as to the comparison against the equation?
		if(solve_x = true){
			double x = pos.getX();
			objection = true; //objects to equality of the entire function
			
		}
		if(solve_y = true){
			double y = pos.getY();
			objection = true;
		}
		if(solve_z = true){
			double z = pos.getZ();
			objection = true;
		}
		solved = true;
		result = !objection; //if there is no objection, it passes.
		
	}
	
}
