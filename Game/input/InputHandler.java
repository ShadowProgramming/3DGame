package game.input;

import org.lwjgl.opengl.Display;

import game.main.Game;

public class InputHandler 
{
	
	public KeyHandler keyhandler;
	public MouseHandler mousehandler;
	
	public InputHandler(Game game)
	{
		keyhandler = new KeyHandler(game);
		/*mousehandler = new MouseHandler(game);
		Display.getParent().addFocusListener(mousehandler);
		Display.getParent().addMouseListener(mousehandler);
		Display.getParent().addMouseMotionListener(mousehandler);*/
	}
	
	public void update()
	{
		keyhandler.update();
		//mousehandler.update();
	}
}
