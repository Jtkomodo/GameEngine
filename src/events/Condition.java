package events;

import java.util.LinkedList;

import core.CoreEngine;

public class Condition {

	 private LinkedList<Flag> flags=new LinkedList<Flag>();
	 private Flag a,b;
	 protected Flag result=new Flag(false);
	 private Operation op;
	
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
		  this.flags.add(a);
		  this.flags.add(b);
		  this.flags.addAll(conditionA.flags);
		  this.flags.addAll(conditionB.flags);
		
		  Events eventA=new Events(conditionA,new ActionSystemUpdateCondition(conditionA));
		  Events eventB=new Events(conditionB,new ActionSystemUpdateCondition(conditionB));
		 
		  CoreEngine.DebugPrint("Flags for conditon{");
		  eventA.ActivateFlags();
		  eventB.ActivateFlags();
		  CoreEngine.DebugPrint("Flags for conditon}"); 
	  }
	 
	
	 
	 public boolean check() {
		
		  result.setState(op.check(a, b));
		  return result.State();
	 }
	
	 public void activate(Events e) {
		 for(int i=0;i<flags.size();i++) {
			 Flag flag=flags.get(i);
			 FlagHandler.addFlag(flag);
			 flag.addEvent(e);
	
		 }
		
	 }
	 public void deactivate(Events e) {
		 for(int i=0;i<flags.size();i++) {
			 Flag flag=flags.get(i);
			 FlagHandler.removeFlag(flag);
			 flag.removeEvent(e);
	
		 }
	 }
}
