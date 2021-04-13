package dev;

public enum Direction
{
	NORTH(0), NORTH_EAST(45), EAST(90), SOUTH_EAST(135), SOUTH(180),
	SOUTH_WEST(225), WEST(270), NORTH_WEST(315);

	int degrees;

	Direction(int degrees)
	{
		this.degrees = degrees;
	}


	public Direction getDirection(int degrees)
	{
		Direction dir = null;
		for (Direction value : values())
			if (value.degrees == degrees)
				dir = value;
		return dir;
	}


	public Direction rotate(int degrees)
	{
		return getDirection(this.degrees + degrees);
	}

	public Direction rotateClockwise()
	{
		return rotate(45);
	}

	public Direction rotateCounterClockwise()
	{
		return rotate(-45);
	}


}
