package audio;


import static org.lwjgl.openal.ALC10.*;

import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import org.lwjgl.openal.AL11;
import org.lwjgl.openal.ALC;
import org.lwjgl.openal.ALCCapabilities;
import org.lwjgl.openal.ALCapabilities;

import core.CoreEngine;


public class AudioInit {
	 private static ALCCapabilities alcCapabilities;
	 private static ALCapabilities alCapabilities;
	 private static long device,context;

     public static  void  InitAudio() {
    	 
    	 String defaultDeviceName = alcGetString(0, ALC_DEFAULT_DEVICE_SPECIFIER);
    	 device = alcOpenDevice(defaultDeviceName);
    	 int[] attributes = {0};
    	 context = alcCreateContext(device, attributes);
    	 alcMakeContextCurrent(context);
    	 alcCapabilities = ALC.createCapabilities(device);
    	 alCapabilities = AL.createCapabilities(alcCapabilities);
    	int error=alcGetError(device);
    	
    	 if(error!=0) {
    		 CoreEngine.DebugPrint("erroe["+error+"]");
    		 
    	 }
        AL10.alDistanceModel(AL11.AL_EXPONENT_DISTANCE);
     }

     public static void destroy() {
    	 
    	 alcDestroyContext(context);
    	 alcCloseDevice(device);
    	 
    	 
     }



    public static void ChangeDistanceModel(int distanceModel) {
    	
    	
    	 AL10.alDistanceModel(distanceModel);
    }






}

