package main.java.audio;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.IntBuffer;

import org.joml.Vector2f;
import org.lwjgl.openal.AL10;
import org.newdawn.slick.openal.WaveData;

import main.java.core.CoreEngine;
import main.java.core.ResourceLoader;
import main.java.core.ResourceNotFoundException;

import static org.lwjgl.openal.AL10.*;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {

	private int soundid;

	public Sound(String fileName) {

		this.soundid=alGenBuffers();
		ResourceLoader r;
		try {
			r = new ResourceLoader("/res/AudioFiles/"+fileName+".wav");

			InputStream stream=r.getInputStream();
			stream = new BufferedInputStream(stream);
			if(stream==null) {
				CoreEngine.DebugPrint("somthing is wrong");
			}
			//  WaveData sound=WaveData.create(stream);	


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
		} catch (ResourceNotFoundException e) {

			e.printStackTrace();
		}

	}

	public int getSoundId() {

		return this.soundid;

	}












}

