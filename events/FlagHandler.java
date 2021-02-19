package events;

import java.util.LinkedList;

import gameEngine.Start;

public class FlagHandler {
	
	private static LinkedList<Flag> flags= new LinkedList<Flag>();
	private static boolean Flag_Changed=false;
	
	public static void addFlag(Flag flag) {
		if(!flags.contains(flag)) {
			flags.add(flag);
			Start.DebugPrint("flag added");
		}
	}
	
	public static void removeFlag(Flag flag) {
		if(flags.remove(flag)) {
			Start.DebugPrint("flag removed");
		}
	}
	
	public static void Flag_Changed(Flag flag) {
		if(flags.contains(flag)) {
		     Flag_Changed=true;
		}
	}
	public static boolean contatins_Flag(Flag flag) {
		return flags.contains(flag);
	}
	
	public static void updateFlags() {
		if(Flag_Changed) {
			Flag_Changed=false;
			for(int i=0;i<flags.size();i++) {
				Flag flag=flags.get(i);
				if(flag.StateChanged()) {
					flag.TriggerEvents();
				}
			
			}
		}
		
		
		
		
	}

}
