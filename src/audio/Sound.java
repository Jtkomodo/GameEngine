package audio;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.IntBuffer;

import org.joml.Vector2f;
import org.lwjgl.openal.AL10;
import org.newdawn.slick.openal.WaveData;

import core.CoreEngine;

import static org.lwjgl.openal.AL10.*;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {

private int soundid;
	
           public Sound(String fileName) {
        	 
        	   this.soundid=alGenBuffers();
        	 
        		InputStream stream=getClass().getResourceAsStream("/res/AudioFiles/"+fileName+".wav");
        	     stream = new BufferedInputStream(stream);
        if(stream==null) {
        	CoreEngine.DebugPrint("somthing is wrong");
        }
             //  WaveData sound=WaveData.create(stream);	
               try {
            	 
				WaveData sound=WaveData.create(AudioSystem.getAudioInputStream(stream));
				
				if(sound==null) {
	        		  System.err.print("Somthing Went wrong when loading sound file "+fileName);
	        	  }
	               
	               AL10.alBufferData(soundid,sound.format, sound.data,sound.samplerate);
	        	   sound.dispose();
			} catch (UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	  
           }

          public int getSoundId() {
        	  
        	  return this.soundid;
        	  
          }












}

