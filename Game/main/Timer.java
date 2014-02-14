package game.main;

import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;

public class Timer {

	long lastFrame;
	
	int fps;
	long lastFPS;
	
	public long getTime() {
	    return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
	
	public int getDelta() {
	    long time = getTime();
	    int delta = (int) (time - lastFrame);
	    lastFrame = time;
	    	
	    return delta;
	}
	
	public void updateFPS() {
	    if (getTime() - lastFPS > 1000) {
	    	Display.setTitle("Game | " + fps + "FPS");
	    	
	        fps = 0; //reset the FPS counter
	        lastFPS += 1000; //add one second
	    }
	    fps++;
	}
	
	public int getFPS()
	{
		return fps;
	}
}
