package game.graphics;

import game.main.Game;
import game.physics.PhysicsEngine;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Render 
{
	
	private Texture concrete, space;
	private SkyBox skybox;
	private Game game;
	
	public List<Cube> terrainCubes = new ArrayList<Cube>();
	
	public Render(Game game)
	{
		this.game = game;
		this.initTextures();
	}
	
	public void update(int fps)
	{		
		this.renderTerrain();
		this.renderSkybox();
	}
	
	private final float directionalIncrement = 0.01f;
	private boolean directionalIncrementFlag = false;
	
	/**
	 * Temporary method until multiple worlds implemented
	 */
	private void renderSkybox()
	{
		this.skybox = new SkyBox(game, space);

		//Sparkling Effect
		if(skybox.getY() <= -501.0f)
			directionalIncrementFlag = true;	
		if(skybox.getY() >= -499.0f)
			directionalIncrementFlag = false;
		
		if(directionalIncrementFlag)
			skybox.moveY(directionalIncrement);
		else
			skybox.moveY(-directionalIncrement);
	}
	
	/**
	 * Temporary method until multiple worlds implemented
	 */
	private void renderTerrain()
	{
		Random random = new Random(4);
		float xCoord;
		float yCoord;
		float zCoord;
		
		for(float i = 0; i < 10; i+=1f)
			for(float f = 0; f < 10; f+=1f)
			{
				xCoord = -2.0f*i;
				yCoord = 3.7f%(random.nextFloat());
				zCoord = -2.0f*f;
				
				Cube c = new Cube(game, concrete, xCoord, yCoord, zCoord, true, true, true, true, true, true, 1f, true);
				game.physics.addCube(c);
			}
	}
	
	private void initTextures()
	{
		System.err.println("Loading Textures...");
		this.concrete = loadTexture("concrete");
		this.space = loadTexture("spaceHD");
		
		System.err.println("Starting Game...");
	}
	
	public static Texture loadTexture(String key)
	{
		try {
			Texture texture =  TextureLoader.getTexture("png", new FileInputStream(new File("res/" + key + ".png")));
			System.err.println("Texture." + key + " Successfully Loaded");
			return texture;
		} catch (FileNotFoundException e) {
			Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, e);
		} catch (IOException e) {
			Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, e);
		}
		
		return null;
	}
}