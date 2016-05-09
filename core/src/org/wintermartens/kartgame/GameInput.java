package org.wintermartens.kartgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 * <p>A simple InputProcessor for the game</p>
 *
 * <p>Created by winter on 09/05/16.</p>
 */
public class GameInput implements InputProcessor
{
	private KartGame game;

	private Vector3 touchPos;

	public GameInput(KartGame game)
	{
		this.game = game;

		touchPos = new Vector3();
	}

	//region Keyboard

	@Override
	public boolean keyDown(int keycode)
	{
		return false;
	}

	@Override
	public boolean keyUp(int keycode)
	{
		return false;
	}

	@Override
	public boolean keyTyped(char character)
	{
		return false;
	}

	//endregion

	//region Mouse/Touchscreen

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{
		touchPos.set(screenX, screenY, 0);

		if(Gdx.input.isKeyPressed(Input.Keys.S))
		{
			//game.getRace().getSpawnPoints().add();
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button)
	{
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer)
	{
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY)
	{
		return false;
	}

	@Override
	public boolean scrolled(int amount)
	{
		return false;
	}

	//endregion
}
