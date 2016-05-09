package org.wintermartens.kartgame;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 *
 * Created by Cedric Martens on 2016-05-09.
 */
public class Race
{
	private KartGame kartGame;
	private Array<RaceLine> lines;
	private Array<Vector2> spawnPoints;

	private long start;
	private long finish;

	private float startDirection;

	public Race(KartGame kartGame, Array<RaceLine> lines, long start, long finish)
	{
		this.kartGame = kartGame;
		this.lines = lines;
		this.start = start;
		this.finish = finish;
	}

	public void draw(SpriteBatch batch)
	{
		batch.draw(kartGame.getTexture("map"), 0, 0, 1280, 720);
	}
}
