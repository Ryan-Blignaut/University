package dev.bot;

import Models.GameObject;
import Models.GameState;
import Models.PlayerAction;

public interface IBaseBot
{
	void setBot(GameObject bot);

	PlayerAction getPlayerAction();

	void computeNextPlayerAction(PlayerAction playerAction);

	void setGameState(GameState gameState);

	GameObject getBot();

	boolean isAlive();

}
	