package game.physics;

import game.graphics.Cube;
import game.main.Game;

import java.util.ArrayList;
import java.util.List;

public class PhysicsEngine {
	
	private Game game;
	
	private List<Cube> terrainCubes = new ArrayList<Cube>();
	
	private float gravitationalConstant;
	
	private boolean walkingBounceFlag = false;
	
	/**
	 * Handles all physics-related concepts and algorithms 
	 * @param game
	 * 			Game instance
	 * @param gravity
	 * 			Gravitational Strength
	 */
	public PhysicsEngine(Game game, float gravity)
	{
		this.game = game;
		this.gravitationalConstant = gravity;
		game.camera.setJumpSpeed(game.camera.getJumpSpeed() + gravity);
	}
	
	/**
	 * Updates and handles physics
	 */
	public void update()
	{
		handleCollision();
		//handleWalkingBounce();
		handleGravity();
	}
	
	/**
	 * Handle Collision with the terrain
	 */
	public void handleCollision()
	{
		for(int i = 0; i < terrainCubes.size(); i++)
		{
			if(terrainCubes.get(i).isColliding() == Cube.DIRECTION_FRONT)
				System.out.println("front");
			if(terrainCubes.get(i).isColliding() == Cube.DIRECTION_BACK)
				System.out.println("back");
			if(terrainCubes.get(i).isColliding() == Cube.DIRECTION_LEFT)
				System.out.println("left");
			if(terrainCubes.get(i).isColliding() == Cube.DIRECTION_RIGHT)
				game.camera.setY(terrainCubes.get(i).getX() + terrainCubes.get(i).getSize() + 0.4f);
		}
	}
	
	/**
	 * Handles gravitational pull
	 */
	public void handleGravity()
	{	
		boolean isAboveOrHandled = false;
		
		for(int i = 0; i < terrainCubes.size(); i++)
		{
			if(terrainCubes.get(i).isAbove())
			{
					game.camera.moveY(getGravity());
					isAboveOrHandled = true;
			}
			else if(terrainCubes.get(i).isWithinBounds() && !terrainCubes.get(i).isBelow())
			{
				game.camera.setY(-terrainCubes.get(i).getY() - ((terrainCubes.get(i).getSize() * 2) + game.camera.getEyeHeight()));
				isAboveOrHandled = true;
			}
		}
		
		if(!isAboveOrHandled)
		{
			game.camera.moveY(getGravity());
		}
		
		terrainCubes.clear();
	}
	
	public void handleWalkingBounce()
	{
		if(walkingBounceFlag)
			game.camera.moveY(0.3f);
		else
			game.camera.moveY(-0.3f);
		walkingBounceFlag = !walkingBounceFlag;
	}
	
	/**
	 * Adds a cube to the physics engine
	 * @param cube
	 */
	public void addCube(Cube cube)
	{
		terrainCubes.add(cube);
	}
	
	/**
	 * Returns the force of gravity
	 * @return
	 */
	public float getGravity()
	{
		return this.gravitationalConstant;
	}

}
