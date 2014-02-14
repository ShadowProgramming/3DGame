package game.main;

import static org.lwjgl.opengl.GL11.*;
import game.graphics.Camera;
import game.graphics.Render;
import game.input.InputHandler;
import game.input.KeyHandler;
import game.physics.PhysicsEngine;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Game 
{

	private Timer timer;
	
	public Camera camera;
	public Render render;
	public PhysicsEngine physics;
	public InputHandler input;
	
	private void start() 
	{
		initDisplay();
		timer = new Timer();
		timer.getDelta();
		timer.lastFPS = timer.getTime();
		gameLoop();
		dispose();
	}
	
	private void gameLoop()
	{
		initObjects();

		while(!Display.isCloseRequested()) 
		{	
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			glLoadIdentity();
			camera.update();
			
			input.update();
			render.update(timer.getFPS());
			physics.update();
			timer.updateFPS();
			Display.sync(60);
			Display.update();
		}
	}
	
	private void initObjects()
	{
		render = new Render(this);
		camera = new Camera(70, (float)Display.getWidth()/(float)Display.getHeight(), 0.1f, 1000);
		physics = new PhysicsEngine(this, 0.03f);
		input = new InputHandler(this);
	}
	
	private static void initDisplay() 
	{
		System.err.println("Initiating Display...");
		try {
			//Display.setFullscreen(true);
			Display.setDisplayMode(new DisplayMode(900, 700));
			Display.create();
		} catch (LWJGLException e) {
			Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	
	private static void dispose() 
	{
		Display.destroy();
	}
	
	public static void main(String args[]) 
	{
		Game game = new Game();
		game.start();
	}
	
}
