package Enums;

public enum ObjectTypes
{
	PLAYER(1, DangerLevel.NEUTRAL),
	FOOD(2, DangerLevel.GOOD),
	WORMHOLE(3, DangerLevel.BAD),
	GAS_CLOUD(4, DangerLevel.BAD),
	ASTEROID_FIELD(5, DangerLevel.BAD);

	public final Integer value;
	private final DangerLevel dangerLevel;

	ObjectTypes(Integer value, DangerLevel dangerLevel)
	{
		this.value = value;
		this.dangerLevel = dangerLevel;
	}

	public DangerLevel getDangerLevel()
	{
		return dangerLevel;
	}

	public static ObjectTypes valueOf(Integer value)
	{
		for (ObjectTypes objectType : ObjectTypes.values())
		{
			if (objectType.value == value) return objectType;
		}

		throw new IllegalArgumentException("Value not found");
	}

	public enum DangerLevel
	{
		GOOD, BAD, NEUTRAL
	}
}
