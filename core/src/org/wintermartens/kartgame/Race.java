package org.wintermartens.kartgame;

import com.badlogic.gdx.utils.Array;

/**
 * Created by Cedric Martens on 2016-05-09.
 */
public class Race
{
	private KartGame kartGame;
	private Array<RaceLine> lines;

	private long start;
	private long finish;

	private float startX;
	private float startY;

	private float startDirection;

	public Race(KartGame kartGame, Array<RaceLine> lines, long start, long finish)
	{
		this.kartGame = kartGame;
		this.lines = lines;
		this.start = start;
		this.finish = finish;
	}
}
