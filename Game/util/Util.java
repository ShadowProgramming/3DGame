package game.util;

public class Util 
{
	/**
	 * Returns true if the value is between the x and y variables
	 * 
	 * @param value 
	 * 			Value to test
	 * @param x
	 * 			Minimum number
	 * @param y
	 * 			Maximum number
	 */
	public static boolean isBetween(float value, float x, float y)
	{
		return (value > x && value < y);
	}
}
