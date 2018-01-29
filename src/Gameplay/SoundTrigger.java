package Gameplay;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundTrigger 
{
	
	Clip clip;
	
	public SoundTrigger(String filename)
	{
	    try
	    {
	        clip = AudioSystem.getClip();
	        clip.open(AudioSystem.getAudioInputStream(new File(filename)));
	        clip.start();
	    }
	    catch (Exception exc)
	    {
	        exc.printStackTrace(System.out);
	    }
	}
	
	public SoundTrigger(String filename, int flag)
	{
	    try
	    {
	        clip = AudioSystem.getClip();
	        clip.open(AudioSystem.getAudioInputStream(new File(filename)));
	    }
	    catch (Exception exc)
	    {
	        exc.printStackTrace(System.out);
	    }
	}
	
	public void start(){
		clip.start();
	}
	
	public boolean isRunning() {
		return clip.isRunning();
	}
	
	public void stop(){
		clip.stop();
	}


	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);	
	}
}
