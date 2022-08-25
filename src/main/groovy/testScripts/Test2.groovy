package main.groovy.testScripts;

import main.java.core.Entity

class TestScript extends main.java.core.Script {
  private UUID ID=UUID.randomUUID();
	
  
  public TestScript() {
  
	  
	  println("ran");
	  }
  
  
    @Override
	public void Start(Entity entity) {
		// TODO Auto-generated method stub
		
	}
    public void hello() {
		println("hello")
	}
	
	
	
	@Override
	public void GAMELOOP_TICK_BEFORE_PHYSICS(){
	   hello();
    }
	@Override
	public boolean DISABLE() {
		println("component test2 removed");
		return true;
	}

	@Override
	public UUID getSCRIPTID() {
		println("component test2 got ID");
		return this.ID;
	}
	
	
}
new TestScript();

