package org.wintermartens.kartgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
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
	private Vector2 clickLoc;

	public GameInput(KartGame game)
	{
		this.game = game;

		touchPos = new Vector3();
		clickLoc = new Vector2();
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
			game.getCamera().unproject(touchPos);
			clickLoc.set(touchPos.x, touchPos.y);
			game.getRace().getSpawnPoints().add(clickLoc.cpy());
			return true;
		}

		if(Gdx.input.isKeyPressed(Input.Keys.L))
		{
			game.getCamera().unproject(touchPos);
			clickLoc.set(touchPos.x, touchPos.y);
			game.getRace().getLines().add(new RaceLine(clickLoc.cpy(), null));
			return true;
		}

		if(Gdx.input.isKeyPressed(Input.Keys.E))
		{
			game.getCamera().unproject(touchPos);
			clickLoc.set(touchPos.x, touchPos.y);
			for(RaceLine line : game.getRace().getLines())
				if(line.getLoc2() == null)
				{
					line.setLoc2(clickLoc.cpy());
					break;
				}

			return true;
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
		OrthographicCamera camera = game.getCamera();

		if(amount == 0)
			return false;

		if(amount > 0 && camera.zoom < 2)
			camera.zoom *= 1.1f;

		if(amount < 0 && camera.zoom > 0.5)
			camera.zoom /= 1.1f;

		System.out.println(camera.zoom);

		return true;
	}

	//endregion
}
