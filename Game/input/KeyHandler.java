package game.input;

import org.lwjgl.input.Keyboard;

import game.graphics.Camera;
import game.main.Game;
import game.physics.PhysicsEngine;

public class KeyHandler 
{
	private Camera camera;
	
	public void setCrouching(boolean crouch)
	{
		
	}
	
	/**
	 * Handles user input from keyboard
	 * @param camera
	 */
	public KeyHandler(Game game)
	{
		this.camera = game.camera;
	}
	
	/**
	 * Checks if binded keys are down and handles function of keys
	 */
	public void update()
	{
		boolean forward = Keyboard.isKeyDown(Keyboard.KEY_W);
		boolean backward = Keyboard.isKeyDown(Keyboard.KEY_S);
		boolean left = Keyboard.isKeyDown(Keyboard.KEY_A);
		boolean right = Keyboard.isKeyDown(Keyboard.KEY_D);
		
		//Walking
		if(forward)
			camera.move(camera.getWalkingSpeed(), 1);
		if(backward)
			camera.move(-camera.getWalkingSpeed(), 1);
		if(left)
			camera.move(camera.getWalkingSpeed(), 0);
		if(right)
			camera.move(-camera.getWalkingSpeed(), 0);

		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
			camera.setCrouching(true);
		else
			camera.setCrouching(false);
		if(Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) || Keyboard.isKeyDown(Keyboard.KEY_RCONTROL))
			camera.setSprinting(true);
		else
			camera.setSprinting(false);

		//Rotation
		if(Keyboard.isKeyDown(Keyboard.KEY_LEFT))
			camera.rotateY(-camera.getRotationSpeed());
		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
			camera.rotateY(camera.getRotationSpeed());
		if(Keyboard.isKeyDown(Keyboard.KEY_UP))
			camera.rotateX(-camera.getRotationSpeed());
		if(Keyboard.isKeyDown(Keyboard.KEY_DOWN))
			camera.rotateX(camera.getRotationSpeed());
		
		//Floatation
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE))
			camera.moveY(-camera.getJumpSpeed());
		
		//Functions and Utilities
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
			System.exit(0);
	}
}
