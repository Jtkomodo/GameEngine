package main.java.events;

import java.util.LinkedList;

import main.java.core.CoreEngine;

public class Condition {

	 private LinkedList<Flag> flags=new LinkedList<Flag>();
	 private Flag a,b;
	 protected Flag result=new Flag(false);
	 private Operation op;
	 public Event eventB;
	 public Event eventA;
	
	 public Condition(Flag a,Operation op,Flag b) {
		 this.a=a;
		 this.b=b;
		 this.flags.add(a);
		 this.flags.add(b);
		 this.op=op;
	   
		 
		
	 }
	 public Condition(Flag a,Operation op,boolean b) {
		 this.a=a;
		 this.b=new Flag(b);
		 this.flags.add(a);
		 this.flags.add(this.b);
		 this.op=op;
	 }
	 
     public Condition(Condition conditionA,Operation op,Condition conditionB) {
		  this.a=conditionA.result;
		  this.b=conditionB.result;
		  this.op=op;
		  this.flags.addAll(conditionA.flags);
		  this.flags.addAll(conditionB.flags);
		  
		  eventA = new Event(conditionA,new ActionSystemUpdateCondition(conditionA));
		  eventB=new Event(conditionB,new ActionSystemUpdateCondition(conditionB));
		 
		  CoreEngine.DebugPrint("Flags for conditon{");
		  eventA.ActivateFlags();
		  eventB.ActivateFlags();
		  CoreEngine.DebugPrint("Flags for conditon}"); 
		
	  }
	 

	 
	 public boolean check() {
		
		  result.setState(op.check(a, b));
		  return result.State();
	 }
	
	 public void activate(Event e) {
         CoreEngine.DebugPrint("amount of flags="+this.flags.size());
		 for(int i=0;i<flags.size();i++) {
			 Flag flag=flags.get(i);
			 FlagHandler.addFlag(flag);
			 flag.addEvent(e);
	       
		 }
		
	 }
	 public void deactivate(Event e) {
		 for(int i=0;i<flags.size();i++) {
			 Flag flag=flags.get(i);
			 flag.removeEvent(e);
	
		 }
	 }
}
