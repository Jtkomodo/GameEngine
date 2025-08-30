package main.java.audio;

import static org.lwjgl.openal.AL10.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.LinkedList;

import org.lwjgl.openal.AL10;
import org.lwjgl.system.MemoryUtil;
import org.newdawn.slick.openal.OggData;
import org.newdawn.slick.openal.OggDecoder;

import main.java.core.CoreEngine;
import main.java.core.Game;
import main.java.core.ResourceLoader;
import main.java.core.ResourceNotFoundException;

public class Music {
	   // The size of a chunk from the stream that we want to read for each update.
	
	private static LinkedList<Music> buffersToFree=new LinkedList<Music>();
    private static int BUFFER_SIZE = 4096*8;
    public static boolean moving=false;
    
    
    // The number of buffers used in the audio pipeline
    private static int NUM_BUFFERS = 2;
    private float bitDepth=16;
    private int amountOfTimeBeforeFadeOut=10;
	private int alBuffers[]=new int[NUM_BUFFERS];
	private String fileName;
	private int format;
	private int rate;
	private int frameSize;
	private float Fadeout;
	private OggData data;
	private int numChanels;
	
	
	public Music(String fileName) {
		this.fileName=fileName;
		alGenBuffers(alBuffers);
		
		OggDecoder oggDecoder = new OggDecoder();
	
		String location="/res/AudioFiles/"+fileName+".ogg";	    
	    try {
	    	ResourceLoader r=new ResourceLoader(location);
	    	
	    	
	    	InputStream stream=r.getInputStream();
	    	if(stream==null) {
				CoreEngine.DebugPrint(location+" not found ");
				System.exit(190);
			}
	    	data=oggDecoder.getData(stream);
			
			numChanels=data.channels;
	        if(numChanels==1) {
	        	format=AL_FORMAT_MONO16;
	        }else {
	            format=AL_FORMAT_STEREO16;
	        }
	        rate=data.rate;
	        frameSize=BUFFER_SIZE*numChanels;
	        this.Fadeout=(findTimeLeft(data.data.remaining()/this.amountOfTimeBeforeFadeOut));
	      
	      //  CoreEngine.DebugPrint("SECONDS:"+findTimeLeft(data.data.remaining()));
	    //    buffer= MemoryUtil.memAlloc(frameSize);
	     //   buffersToFree.add(buffer);
			
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(190);
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
    private float  findTimeLeft(int remaining) {
    	return((remaining/(numChanels*rate*(bitDepth/8))));
    }
	public void play(Source source) {
		
		
		int buffersProccesed=alGetSourcei(source.sourceID,AL_BUFFERS_PROCESSED);
		while(buffersProccesed>0) {
			int ALbuffer=alSourceUnqueueBuffers(source.sourceID);
			buffersProccesed--;	
			int amountLeft=loadBuffer(ALbuffer);
			
		}
		 alSourceRewind(source.sourceID);
		 alSourcef(source.sourceID,AL_BUFFER,0);
	     
		   
		   
		for(int i=0;i<NUM_BUFFERS;i++) {
			if(loadBuffer(alBuffers[i])==0) {
				return;
			}
		}
		alSourceQueueBuffers(source.sourceID,alBuffers);
	    alSourcePlay(source.sourceID);
	    
		
	}
	
	private int loadBuffer(int ALbuffer) {
		int size=data.data.remaining();
		ByteBuffer buffer;
		
		
		if(size>=frameSize){
			buffer=MemoryUtil.memAlloc(frameSize);
			byte[] buf=new byte[frameSize];
			data.data.get(buf);
			buffer.put(buf);
			buffer.flip();
		}else if(size<frameSize && size>0){
			buffer=MemoryUtil.memAlloc(size);
			byte[] buf=new byte[size];
			data.data.get(buf);
			buffer.put(buf);
			buffer.flip();
		}else {
			
			//CoreEngine.DebugPrint("NO MORE MUSIC TO PLAY");
			return 0;
		}
		
	
	    AL10.alBufferData(ALbuffer,format,buffer,rate);
	    MemoryUtil.memFree(buffer);
	    return data.data.remaining();
	}
	protected void update(Source source) {
		
		int state=alGetSourcei(source.sourceID,AL_SOURCE_STATE);
		
		int buffersProccesed=alGetSourcei(source.sourceID,AL_BUFFERS_PROCESSED);
		while(buffersProccesed>0) {
			//CoreEngine.DebugPrint("NOT MOVING");
			int ALbuffer=alSourceUnqueueBuffers(source.sourceID);
			buffersProccesed--;	
			int amountLeft=loadBuffer(ALbuffer);
		    if(amountLeft>0) {
		    float timeleft=findTimeLeft(amountLeft);
		          //  CoreEngine.DebugPrint("LEFT:"+timeleft);
		            if(timeleft<=this.amountOfTimeBeforeFadeOut) {
		            	source.setGain(source.gainOriginal*((timeleft/(this.amountOfTimeBeforeFadeOut))));
		            }
		        alSourceQueueBuffers(source.sourceID,ALbuffer);
		    }else {
		     data.data.rewind();
		    // CoreEngine.DebugPrint("DONE");
		     alSourceStop(source.sourceID);
		     source.playMusic(this);
		     }
		     
		    }
		    
		   
	}
		
		
	
	
	private void Close() {
		
		alDeleteBuffers(alBuffers);
	}
	public static void close() {
		for(int i=0;i<buffersToFree.size();i++) {
	           buffersToFree.get(i).Close();
		}
		
	}

	public int[] getAlBuffers() {
		return alBuffers;
	}

	
	
	
	
	

}
