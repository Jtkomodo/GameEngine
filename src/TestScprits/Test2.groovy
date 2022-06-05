package TestScprits

import core.Entity

class TestScript extends core.Script {
  UUID ID=UUID.randomUUID();
	@Override
	public void Start(Entity entity) {
		// TODO Auto-generated method stub
		
	}
    public void hello() {
		
	}
	
	
	
	@Override
	public void GAMELOOP_TICK_BEFORE_PHYSICS(){
	   // println "brandnew"
    }
	@Override
	public boolean DISABLE() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public UUID getSCRIPTID() {
		// TODO Auto-generated method stub
		return ID;
	}
	
	
}
new TestScript();

