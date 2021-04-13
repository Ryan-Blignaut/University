package dev;

import Models.GameObject;
import Models.Position;

public class Util
{

	public static double getDistancePositionsBetween(Position position1, Position position2)
	{
		final int triangleX = Math.abs(position1.x - position2.x);
		final int triangleY = Math.abs(position1.y - position2.y);
		return Math.sqrt(triangleX * triangleX + triangleY * triangleY);
	}

	public static int getHeadingBetween(GameObject bot,Position position)
	{
		int direction = toDegrees(Math.atan2(position.y - bot.getPosition().y,
				position.x - bot.getPosition().x));
		return (direction + 360) % 360;
	}

	public static int toDegrees(double v)
	{
		return (int) (v * (180 / Math.PI));
	}

	public static double getDistanceBetween(GameObject object1, GameObject object2)
	{
		var triangleX = Math.abs(object1.getPosition().x - object2.getPosition().x);
		var triangleY = Math.abs(object1.getPosition().y - object2.getPosition().y);
		return Math.sqrt(triangleX * triangleX + triangleY * triangleY);
	}

	public static int getHeadingBetween(GameObject bot,GameObject otherObject)
	{
		var direction = toDegrees(Math.atan2(otherObject.getPosition().y - bot.getPosition().y,
				otherObject.getPosition().x - bot.getPosition().x));
		return (direction + 360) % 360;
	}
}
