package game.graphics;

import game.main.Game;

import org.newdawn.slick.opengl.Texture;

public class SkyBox extends Cube
{
	private static float yPos = -500.0f;
	
	/**
	 * Creates a new skybox
	 * @param game
	 * @param texture
	 */
	public SkyBox(Game game, Texture texture)
	{
		super(game, texture, 0, yPos, 0, true, true, true, false, true, true, 500f, false);
	}
	
	@Override
	public void moveY(float amount)
	{
		yPos += amount;
	}
}
