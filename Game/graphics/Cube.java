package game.graphics;

import static org.lwjgl.opengl.GL11.*;
import game.main.Game;
import game.util.Util;

import org.newdawn.slick.opengl.Texture;

public class Cube 
{
	private Game game;
	private Texture texture;
	private float xPos, yPos, zPos, size;
	private boolean front, back, top, bottom, left, right, isSolid;

	public static final int DIRECTION_FRONT = 1;
	public static final int DIRECTION_BACK = 2;
	public static final int DIRECTION_LEFT = 3;
	public static final int DIRECTION_RIGHT = 4;
	public static final int DIRECTION_TOP = 5;
	public static final int DIRECTION_BOTTOM = 6;
	
	//Player related vars
	private float playerX, playerY, playerZ;
	
	/**
	 * Creates a new Cube with given attributes
	 * @param texture
	 * @param xPos
	 * @param yPos
	 * @param zPos
	 * @param front
	 * @param back
	 * @param top
	 * @param bottom
	 * @param left
	 * @param right
	 * @param size
	 * @param isSolid
	 */
	public Cube(Game game, Texture texture, float xPos, float yPos, float zPos, boolean front, boolean back,
			boolean top, boolean bottom, boolean left, boolean right, float size, boolean isSolid)
	{
		this.game = game;
		this.playerX = game.camera.getX();
		this.playerY = game.camera.getY();
		this.playerZ = game.camera.getZ();
		
		this.texture = texture;
		this.xPos = xPos;
		this.yPos = yPos;
		this.zPos = zPos;
		this.front = front;
		this.back = back;
		this.top = top;
		this.bottom = bottom;
		this.left = left;
		this.right = right;
		this.size = size;
		this.isSolid = isSolid;

		glPushMatrix();

		{

			glTranslatef(this.xPos, this.yPos + this.size, this.zPos);
			texture.bind();

			glBegin(GL_QUADS);

			{

				// Front

				if (this.front)

				{

					glVertex3f(-this.size, -this.size, this.size);
					glTexCoord2f(0, 0);

					glVertex3f(-this.size, this.size, this.size);
					glTexCoord2f(0, 1);

					glVertex3f(this.size, this.size, this.size);
					glTexCoord2f(1, 1);

					glVertex3f(this.size, -this.size, this.size);
					glTexCoord2f(1, 0);

				}

				// Back

				if (this.back)

				{

					glVertex3f(-this.size, -this.size, -this.size);
					glTexCoord2f(0, 0);

					glVertex3f(-this.size, this.size, -this.size);
					glTexCoord2f(0, 1);

					glVertex3f(this.size, this.size, -this.size);
					glTexCoord2f(1, 1);

					glVertex3f(this.size, -this.size, -this.size);
					glTexCoord2f(1, 0);

				}

				// Left

				if (this.left)

				{

					glVertex3f(-this.size, -this.size, -this.size);
					glTexCoord2f(0, 0);

					glVertex3f(-this.size, -size, this.size);
					glTexCoord2f(0, 1);

					glVertex3f(-this.size, this.size, this.size);
					glTexCoord2f(1, 1);

					glVertex3f(-this.size, this.size, -this.size);
					glTexCoord2f(1, 0);

				}

				// Right

				if (this.right)

				{

					glVertex3f(this.size, -this.size, -this.size);
					glTexCoord2f(0, 0);

					glVertex3f(this.size, -this.size, this.size);
					glTexCoord2f(0, 1);

					glVertex3f(this.size, this.size, this.size);
					glTexCoord2f(1, 1);

					glVertex3f(this.size, this.size, -this.size);
					glTexCoord2f(1, 0);

				}

				// Bottom

				if (this.bottom)

				{

					glVertex3f(-this.size, -this.size, -this.size);
					glTexCoord2f(0, 0);

					glVertex3f(this.size, -this.size, -this.size);
					glTexCoord2f(0, 1);

					glVertex3f(this.size, -this.size, this.size);
					glTexCoord2f(1, 1);

					glVertex3f(-this.size, -this.size, this.size);
					glTexCoord2f(1, 0);

				}

				// Top

				if (this.top)

				{

					glVertex3f(-this.size, this.size, -this.size);
					glTexCoord2f(0, 0);

					glVertex3f(this.size, this.size, -this.size);
					glTexCoord2f(0, 1);

					glVertex3f(this.size, this.size, this.size);
					glTexCoord2f(1, 1);

					glVertex3f(-this.size, this.size, this.size);
					glTexCoord2f(1, 0);

				}

			}

			glEnd();

		}

		glPopMatrix();

	}
	
	/**
	 * Returns true  the camera is above the cube
	 * @return
	 */
	public boolean isAbove()
	{
		boolean yGood = false;
		this.playerY = game.camera.getY();
		
		if(game.camera.getY() > (this.getY() + (getSize() * 2) + 0.15f))
			yGood = true;
		
		return (isWithinBounds() && yGood);
	}
	
	/**
	 * Returns true if the camera is under the cube
	 * @return
	 */
	public boolean isBelow()
	{
		boolean yGood = false;
		
		if(game.camera.getY() < this.getY())
			yGood = true;
		
		return (isWithinBounds() && yGood);
	}
	
	/**
	 * Returns true if the camera is within the bounds of the cube
	 * @return
	 */
	public boolean isWithinBounds()
	{
		boolean xGood = false, zGood = false;
		this.playerX = game.camera.getX();
		this.playerY = game.camera.getY();
		this.playerZ = game.camera.getZ();
		
		if( (playerX > (this.getX() - getSize())) && (playerX < (this.getX() + getSize())))
			xGood = true;
		if( (playerZ > (this.getZ() - getSize())) && (playerZ < (this.getZ() + getSize())))
			zGood = true;	
		
		return (xGood && zGood);
	}
	
	public int isColliding()
	{
		if(isSolid())
		{
			this.playerX = game.camera.getX();
			this.playerY = game.camera.getY();
			this.playerZ = game.camera.getZ();

			if(Util.isBetween(playerY, this.getY() - getSize() -  game.camera.getEyeHeight(), this.getY() + getSize() + game.camera.getEyeHeight()))
			{
				if(Util.isBetween(playerX, this.getX() + getSize(), this.getX() + getSize() + 0.1f))
					return DIRECTION_RIGHT;
				if(Util.isBetween(playerX, this.getX() - getSize() -  0.1f, this.getX() - getSize()))
					return DIRECTION_LEFT;
				if(Util.isBetween(playerZ, this.getZ() + getSize(), this.getZ() + getSize() + 0.1f))
					return 	DIRECTION_BACK;
				if(Util.isBetween(playerZ, this.getZ() - getSize() -  0.1f, this.getZ() - getSize()))
					return DIRECTION_FRONT;
				if(Util.isBetween(playerY, this.getY() + getSize(), this.getY() + getSize() + 0.1f))
					return 	DIRECTION_TOP;
				if(Util.isBetween(playerY, this.getY() - getSize() -  0.1f, this.getY() - getSize()))
					return DIRECTION_BOTTOM;
			}
		}
		
		return -1;
	}
	
	
	

	//Setters--------------------
	public void moveX(float amount)
	{
		this.xPos += amount;
	}
	
	public void moveY(float amount)
	{
		this.yPos += amount;
	}
	
	public void moveZ(float amount)
	{
		this.zPos += amount;
	}
	
	public void setX(float x)
	{
		this.xPos = x;
	}
	
	public void setY(float y)
	{
		this.yPos = y;
	}
	
	public void setZ(float z)
	{
		this.zPos = z;
	}
	
	//Getters--------------------
	public float getX()
	{
		return xPos;
	}
	
	public float getY()
	{
		return yPos;
	}

	public float getZ()
	{
		return zPos;
	}
	
	/**
	 * Returns the size of the cube from the middle of the object
	 * @return
	 */
	public float getSize()
	{
		return size;
	}
	
	/**
	 * Returns the texture of the object
	 * @return
	 */
	public Texture getTexture()
	{
		return texture;
	}
	
	/**
	 * Returns true if the object is a solid and collidable
	 * @return
	 */
	public boolean isSolid()
	{
		return isSolid;
	}
}
