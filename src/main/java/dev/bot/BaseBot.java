package dev.bot;

import Models.GameObject;
import Models.GameState;
import Models.PlayerAction;

import java.util.Optional;

public abstract class BaseBot implements IBaseBot
{
	protected GameObject bot;
	protected PlayerAction playerAction;
	protected GameState gameState;
	private boolean alive = true;

	public BaseBot()
	{
		this.playerAction = new PlayerAction();
		this.gameState = new GameState();
	}

	@Override
	public void setBot(GameObject bot)
	{
		this.bot = bot;
	}

	@Override
	public GameObject getBot()
	{
		return bot;
	}

	@Override
	public PlayerAction getPlayerAction()
	{
		return this.playerAction;
	}

	@Override
	public void computeNextPlayerAction(PlayerAction playerAction)
	{
		alive = gameState.getPlayerGameObjects().contains(bot);
		onUpdate();
	}

	@Override
	public void setGameState(GameState gameState)
	{
		this.gameState = gameState;
		Optional<GameObject> optionalBot = gameState.getPlayerGameObjects().stream().filter(gameObject -> gameObject.id.equals(bot.id)).findAny();
		optionalBot.ifPresent(bot -> this.bot = bot);
		firstRun();
	}

	@Override
	public boolean isAlive()
	{
		return alive;
	}

	protected abstract void firstRun();

	protected abstract void onUpdate();

}
