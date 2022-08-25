package main.java.events;

import java.util.LinkedList;

import main.java.core.CoreEngine;


public class FlagHandler {
	
	private static LinkedList<Flag> flags= new LinkedList<Flag>();
	private static LinkedList<Flag> flagsChanged=new LinkedList<Flag>();
	private static boolean Flag_Changed=false;
	
	public static void addFlag(Flag flag) {
		if(!flags.contains(flag)) {
			flags.add(flag);
			CoreEngine.DebugPrint("flag added");
		}
	}
	
	protected static void removeFlag(Flag flag) {
		if(flags.remove(flag)) {
			CoreEngine.DebugPrint("flag removed");
		}
	}
	
	public static void Flag_Changed(Flag flag) {
		if(flags.contains(flag)) {
		     Flag_Changed=true;
		     if(!flagsChanged.contains(flag)) {
		     flagsChanged.add(flag);
		     }
		}
	}
	public static boolean contatins_Flag(Flag flag) {
		return flags.contains(flag);
	}
	
	
	
	
	public static void updateFlags() {
		if(Flag_Changed) {
			Flag_Changed=false;
			for(int i=0;i<flagsChanged.size();i++) {
				Flag flag=flagsChanged.get(i);
				flag.TriggerEvents();
			
			}
			flagsChanged.clear();
		}
		
		
		
		
	}

}
