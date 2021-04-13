package dev;

import Enums.ObjectTypes;
import Enums.PlayerActions;
import Models.GameObject;
import dev.bot.BaseBot;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class CustomBotService extends BaseBot
{
/*	we need different routines based on conditions
	conditions:
		move closer to centre if close to edge
		if size of bot smaller  than other
		or bot is too close to hazard
		then run
		else attack
		if no danger search for closest food
		we should be efficient with the code and only look a small way away from the bot*/

	private int heading;
	private GameObject target;

	@Override
	protected void firstRun()
	{
		heading = 1;
	}

	@Override
	protected void onUpdate()
	{
		playerAction.action = PlayerActions.FORWARD;
//		test of branching ryan

		if (!gameState.getGameObjects().isEmpty())
		{
			if (target == null)
			{
				System.out.println("looking for new target");
				heading = searchForNewTarget();
			}

		}


//		if too close to edge
//		if ()


		playerAction.heading = heading;
	}

	private int searchForNewTarget()
	{
		final int direction;
		final List<GameObject> list = gameState.getGameObjects();
		final Optional<GameObject> closetFood = getCloset(list, gameObject -> gameObject.getGameObjectType() == ObjectTypes.FOOD);
		final Optional<GameObject> closetWormHole = getCloset(list, gameObject -> gameObject.getGameObjectType() == ObjectTypes.WORMHOLE);
		final Optional<GameObject> closetPlayer = getCloset(gameState.getPlayerGameObjects(), gameObject -> gameObject.getId() != bot.getId());

		if (closetPlayer.isPresent() && closetPlayer.get().getSize() > bot.getSize())
		{
			direction = -Util.getHeadingBetween(bot, closetPlayer.get());
			target = closetPlayer.get();
		} else if (closetPlayer.isPresent() && closetPlayer.get().getSize() < bot.getSize())
		{
			direction = Util.getHeadingBetween(bot, closetPlayer.get());
			target = closetPlayer.get();
		} else if (closetFood.isPresent())
		{
			System.out.println("Going for a food");
			direction = Util.getHeadingBetween(bot, closetFood.get());
			target = closetFood.get();
		} else
		{
			direction = 1;
		}
		return direction;
	}

	private <T extends GameObject> Optional<T> getCloset(List<T> gameObjects, Predicate<T> predicate)
	{
		return gameObjects.stream()
//				.parallel()
				.filter(predicate)
				.min(Comparator.comparingDouble(gameObject -> Util.getDistanceBetween(bot, gameObject)));
	}

	private Optional<GameObject> getCloset(ObjectTypes objectType)
	{
		return gameState.getGameObjects().stream()
				.filter(gameObject -> gameObject.getGameObjectType() == objectType)
				.min(Comparator.comparingDouble(gameObject -> Util.getDistanceBetween(bot, gameObject)));
	}

	private void path(GameObject gameObject)
	{

	}
}
