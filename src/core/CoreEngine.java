package core;

public class CoreEngine {
    public static boolean DebugPrint=false;
    
    
    
    protected static void updateEngine() {
    	UpdatePhysicsEngine();
    	UpdateAnimationEngine();
    	UpdateRenderEngine();
    }
    
    
    
    private static void UpdateAnimationEngine() {
		
	}



	public static void UpdateInput() {
    	
    }
    
	private static void UpdatePhysicsEngine() {
		
	}
	private static void UpdateRenderEngine() {
		
	}
	
	public static void DebugPrint(String s) {
		if(DebugPrint) {
			System.out.println(s);
		}
	}
}
