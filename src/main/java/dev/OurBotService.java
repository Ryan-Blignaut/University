package dev;

import Enums.PlayerActions;
import Models.*;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class OurBotService
{
	private GameObject bot;
	private PlayerAction playerAction;
	private GameState gameState;

	public OurBotService()
	{
		this.playerAction = new PlayerAction();
		this.gameState = new GameState();
	}


	public GameObject getBot()
	{
		return this.bot;
	}

	public void setBot(GameObject bot)
	{
		this.bot = bot;
	}

	public PlayerAction getPlayerAction()
	{
		return this.playerAction;
	}

	public void setPlayerAction(PlayerAction playerAction)
	{
		this.playerAction = playerAction;
	}

	public void computeNextPlayerAction(PlayerAction playerAction)
	{
		playerAction.action = PlayerActions.FORWARD;
//		playerAction.heading = new Random().nextInt(360);
		playerAction.heading = 1;
		if (!gameState.getGameObjects().isEmpty())
		{
//			var foodList = gameState.getGameObjects()
//					.stream().filter(item -> item.getGameObjectType() == ObjectTypes.FOOD)
//					.sorted(Comparator
//							.comparing(item -> getDistanceBetween(bot, item)))
//					.collect(Collectors.toList());
//
//			playerAction.heading = getHeadingBetween(foodList.get(0));

			playerAction.heading = getHeading(playerAction.heading);


//			see if we are "close" to a player and if we are decently bigger than it
//			gameState.getPlayerGameObjects().forEach(player ->
//			{
//				if (getDistanceBetween(bot, player) < 64 && (bot.getSize() + 10) > player.getSize())
//					playerAction.setHeading(getHeadingBetween(player));
//			});


		}

		this.playerAction = playerAction;
	}

	public GameState getGameState()
	{
		return this.gameState;
	}

	public void setGameState(GameState gameState)
	{
		this.gameState = gameState;
		updateSelfState();
	}

	private void updateSelfState()
	{
		Optional<GameObject> optionalBot = gameState.getPlayerGameObjects().stream().filter(gameObject -> gameObject.id.equals(bot.id)).findAny();
		optionalBot.ifPresent(bot -> this.bot = bot);
	}

	private double getDistanceBetween(GameObject object1, GameObject object2)
	{
		var triangleX = Math.abs(object1.getPosition().x - object2.getPosition().x);
		var triangleY = Math.abs(object1.getPosition().y - object2.getPosition().y);
		return Math.sqrt(triangleX * triangleX + triangleY * triangleY);
	}

	private int getHeadingBetween(GameObject otherObject)
	{
		var direction = toDegrees(Math.atan2(otherObject.getPosition().y - bot.getPosition().y,
				otherObject.getPosition().x - bot.getPosition().x));
		return (direction + 360) % 360;
	}

	private int toDegrees(double v)
	{
		return (int) (v * (180 / Math.PI));
	}


	private int getHeading(int current)
	{
//			we need different routines based on conditions
//			conditions
//			    move closer to centre if close to edge
//			    if size of bot smaller  than other
//				or bot is too close to hazard
//		        then run
//			    else attack
//				if no danger search for closest food
//
//			we should be efficient with the code and only look a small way away from the bot

		AtomicInteger heading = new AtomicInteger(current);
/*
		gameState.getGameObjects().stream().forEach(gameObject ->
		{
//			close enough to be careful of
			if (getDistanceBetween(bot, gameObject) > bot.getSize() * 2)
			{
				if (gameObject.gameObjectType.getDangerLevel() == ObjectTypes.DangerLevel.BAD)
				{
//					run away
//					final int objX = gameObject.getPosition().getX();
//					final int objY = gameObject.getPosition().getY();
//					final int botX = bot.getPosition().getX();
//					final int botY = bot.getPosition().getY();
//					TODO:needs better implementation
					heading.set(-getHeadingBetween(gameObject));


				}
			}
		});*/


//		Code to detect when we are too close to the world border
		final World world = gameState.getWorld();
		final double distanceBetweenBotAndEdge = getDistanceBetween(bot.position, world.getCenterPoint());
		if (distanceBetweenBotAndEdge + bot.getSize() * 2 > world.getRadius())
			heading.set(getHeadingBetween(world.getCenterPoint()));

		return heading.get();
	}

	private double getDistanceBetween(Position position1, Position position2)
	{
		final int triangleX = Math.abs(position1.x - position2.x);
		final int triangleY = Math.abs(position1.y - position2.y);
		return Math.sqrt(triangleX * triangleX + triangleY * triangleY);
	}

	private int getHeadingBetween(Position position)
	{
		var direction = toDegrees(Math.atan2(position.y - bot.getPosition().y,
				position.x - bot.getPosition().x));
		return (direction + 360) % 360;
	}

}
