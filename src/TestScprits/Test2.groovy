package TestScprits

import core.Entity

class TestScript extends core.Script {

	@Override
	public void Start(Entity entity) {
		// TODO Auto-generated method stub
		
	}
    public void hello() {
		//println "old code";
		newCode();
	}
	@Override
	public void GAMELOOP_TICK_BEFORE_PHYSICS(){
	  hello();
    }
	@Override
	public boolean DISABLE() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public UUID getSCRIPTID() {
		// TODO Auto-generated method stub
		return null;
	}
	public newCode() {
		println "new code"
	}
	
}
new TestScript();

