package org.wintermartens.kartgame;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * Represents a race in the game
 *
 * Created by Alexander Winter on 2016-05-09.
 */
public class Race
{
	private KartGame kartGame;

	private Array<RaceLine> lines;
	private Array<Vector2> spawnPoints;

	private long start;
	private long finish;

	public Race(KartGame kartGame)
	{
		this.kartGame = kartGame;
		this.lines = new Array<>();
		this.spawnPoints = new Array<>();
		this.start = -1;
		this.finish = -1;
	}

	public void start()
	{
		start = TimeUtils.millis();
	}

	public long getTime()
	{
		if(start == -1)
			return 0;

		if(finish == -1)
			return TimeUtils.timeSinceMillis(start);

		return finish - start;
	}

	public void finish()
	{
		finish = TimeUtils.millis();
	}

	public void draw(SpriteBatch batch)
	{
		batch.draw(kartGame.getTexture("map"), 0, 0, 1280 * 4, 720 * 4);

		for(Vector2 spawnPoint : spawnPoints)
			batch.draw(kartGame.getTexture("spawnpoint"), spawnPoint.x - 16, spawnPoint.y - 16, 32, 32);

		for(RaceLine line : lines)
		{
			batch.draw(kartGame.getTexture("line1"), line.getLoc1().x - 16, line.getLoc1().y - 16, 32, 32);

			if(line.getLoc2() != null)
				batch.draw(kartGame.getTexture("line2"), line.getLoc2().x - 16, line.getLoc2().y - 16, 32, 32);
		}

	}

	public KartGame getKartGame()
	{
		return kartGame;
	}

	public void setKartGame(KartGame kartGame)
	{
		this.kartGame = kartGame;
	}

	public Array<RaceLine> getLines()
	{
		return lines;
	}

	public void setLines(Array<RaceLine> lines)
	{
		this.lines = lines;
	}

	public Array<Vector2> getSpawnPoints()
	{
		return spawnPoints;
	}

	public void setSpawnPoints(Array<Vector2> spawnPoints)
	{
		this.spawnPoints = spawnPoints;
	}

	public long getStart()
	{
		return start;
	}

	public void setStart(long start)
	{
		this.start = start;
	}

	public long getFinish()
	{
		return finish;
	}

	public void setFinish(long finish)
	{
		this.finish = finish;
	}
}
