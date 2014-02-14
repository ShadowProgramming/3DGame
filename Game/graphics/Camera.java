package game.graphics;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;

public class Camera 
{
	private float x, y, z;
	private float rotx, roty, rotz;
	private float rotxMax, rotxMin;
	
	private float fov, aspect;
	private float near, far;
	
	private float eyeHeight = 0.15f;
	
	private float walkingVelocityConstant = 0.01f;
	private float rotationVelocityConstant = 0.8f;
	private float floatationVelocityConstant = 0.04f;
	
	private float walkingVelocity = walkingVelocityConstant; 
	private float rotationVelocity = rotationVelocityConstant; 
	private float floatationVelocity = floatationVelocityConstant;
	
	private boolean crouching, sprinting;

	/**
	 * Creates a new camera with specified characteristics
	 * @param fov
	 * @param aspect
	 * @param near
	 * @param far
	 */
	public Camera(float fov, float aspect, float near, float far) 
	{
		x = 0;
		y = 2.2f;
		z = 0;
		rotx = 0;
		roty = 0 ;
		rotz = 0;
		rotxMax = 70.0f;
		rotxMin = -70.0f;
		crouching = false;
		sprinting = false;
		
		this.fov = fov;
		this.aspect = aspect;
		this.near = near;
		this.far = far;
		
		initProjection();
	}
	
	private void initProjection() 
	{
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		gluPerspective(fov, aspect, near, far);
		glMatrixMode(GL_MODELVIEW);
		
		glEnable(GL_DEPTH_TEST);
		glEnable(GL_TEXTURE_2D);
	}
	
	public void useView() 
	{
		glRotatef(rotx, 1, 0, 0);
		glRotatef(roty, 0, 1, 0);
		glRotatef(rotz, 0, 0, 1);
		glTranslatef(-x, -y, -z);
	}
	
	public void update()
	{
		if(isCrouching() && !isSprinting())
		{
			walkingVelocity = 0.004f;
			eyeHeight = 0.1f;
		}
		else if(!isSprinting())
		{
			walkingVelocity = walkingVelocityConstant;
			eyeHeight = 0.15f;
		}
		
		if(isSprinting() && !isCrouching())
			walkingVelocity = walkingVelocityConstant + 0.005f;
		else if(!isCrouching())
			walkingVelocity = walkingVelocityConstant;
		
		fov += 0.001f;
		initProjection();
		
		useView();
	}
	
	
	
	public void move(float amount, float dir)
	{
		z -= amount * Math.sin(Math.toRadians(roty + 90 * dir));
		x -= amount * Math.cos(Math.toRadians(roty + 90 * dir));
	}
	
	/**
	 * Moves camera on the Y axis
	 * @param amount
	 */
	public void moveY(float amount)
	{
		y -= amount;
	}
	
	/**
	 * Rotates camera vertically
	 * @param amount
	 */
	public void rotateX(float amount)
	{
		if(rotx + amount < rotxMin)
		{
			rotx = rotxMin;
			return;
		}
		if(rotx + amount > rotxMax)
		{
			rotx = rotxMax;
			return;
		}
		
		rotx += amount;
	}
	
	/**
	 * Rotates camera horizontally
	 * @param amount
	 */
	public void rotateY(float amount)
	{
		roty += amount;
	}
	
	/**
	 * Rotates camera in forward cylindrical motion
	 * @param amount
	 */
	public void rotateZ(float amount)
	{
		rotz += amount;
	}

	public float getX()
	{	
		return x;
	}	
	
	public float getY()
	{	
		return y;
	}
	
	public float getZ()
	{
		return z;
	}

	public void setX(float x)
	{
		this.x = -x;
	}
	
	public void setY(float y)
	{
		this.y = -y;
	}
	
	public void setZ(float z)
	{
		this.z = -z;
	}
	
	/**
	 * Sets the player into the "crouching" position
	 * @param crouching
	 */
	public void setCrouching(boolean crouching)
	{
		this.crouching = crouching;
	}
	
	/**
	 * Sets the player into a sprint
	 * @param sprinting
	 */
	public void setSprinting(boolean sprinting)
	{
		this.sprinting = sprinting;
	}
	
	/**
	 * Sets the speed in which the player walks
	 * @param speed
	 */
	public void setWalkingSpeed(float speed)
	{
		this.walkingVelocity = speed;
	}
	
	/**
	 * Sets the speed at which the player rotates
	 * @param speed
	 */
	public void setRotationSpeed(float speed)
	{
		this.rotationVelocity = speed;
	}
	
	/**
	 * Sets the speed at which the player travels upward during jumping
	 * @param speed
	 */
	public void setJumpSpeed(float speed)
	{
		this.floatationVelocity = speed;
	}
	
	/**
	 * Sets the height of the player's eyes from the player's feet
	 * @param height
	 */
	public void setEyeHeight(float height)
	{
		this.eyeHeight = height;
	}
	
	/**
	 * Returns whether or not the player is crouching
	 * @return
	 */
	public boolean isCrouching()
	{
		return this.crouching;
	}
	
	/**
	 * Returns whether or not the player is sprinting
	 * @return
	 */
	public boolean isSprinting()
	{
		return this.sprinting;
	}
	
	/**
	 * Returns the height of the player's eyes from the player's feet
	 * @return
	 */
	public float getEyeHeight()
	{
		return this.eyeHeight;
	}
	
	/**
	 * Returns the height of the player's eyes on the Y axis
	 * @return
	 */
	public float getActualEyeHeight()
	{
		return y + this.eyeHeight;
	}
	
	/**
	 * Returns the player's current walking speed
	 * @return
	 */
	public float getWalkingSpeed()
	{
		return this.walkingVelocity;
	}
	
	/**
	 * Returns the player's current rotation speed
	 * @return
	 */
	public float getRotationSpeed()
	{
		return this.rotationVelocity;
	}
	
	/**
	 * Returns the player's current jumping speed (Velocity)
	 * @return
	 */
	public float getJumpSpeed()
	{
		return this.floatationVelocity;
	}
	
	public float getRotX()
	{	
		return rotx;
	}	
	
	public float getRotY()
	{	
		return roty;
	}
	
	public float getRotZ()
	{
		return rotz;
	}

	public void setRotX(float rotx)
	{
		this.rotx = rotx;
	}
	
	public void setRotY(float roty)
	{
		this.roty = roty;
	}
	
	public void setRotZ(float rotz)
	{
		this.rotz = rotz;
	}
}
