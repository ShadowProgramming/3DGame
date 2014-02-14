package game.input;

import game.graphics.Camera;
import game.main.Game;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import org.lwjgl.opengl.Display;

public class MouseHandler implements FocusListener, MouseListener, MouseMotionListener {

	private Game game;
	private Camera camera;
	
	private int mouseX, lastMouseX;
	private int mouseY, lastMouseY;

	private float rotationVelocityConstant = 0.4f;
	
	public MouseHandler(Game game)
	{
		this.game = game;
		this.camera = game.camera;
	}
	
	public void update()
	{
		if(mouseX > lastMouseX)
			camera.rotateY(rotationVelocityConstant);
		if(mouseX < lastMouseX)
			camera.rotateY(-rotationVelocityConstant);
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		lastMouseX = mouseX;
		lastMouseY = mouseY;
		
		mouseX = e.getX();
		mouseY = e.getY();
		System.out.println("FF");
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void focusGained(FocusEvent e) {
		
	}

	@Override
	public void focusLost(FocusEvent e) {
	}
}