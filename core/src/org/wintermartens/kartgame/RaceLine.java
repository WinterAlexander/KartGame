package org.wintermartens.kartgame;

import com.badlogic.gdx.math.Vector2;

/**
 * Represents a checkpoint for the race
 * Contains 2 locations being the extremities of the line to cross
 * Also used to replace players on the race
 *
 * Created by Alexander Winter on 2016-05-09.
 */
public class RaceLine
{
	private Race race;
	private Vector2 loc1, loc2;

	public RaceLine(Race race, Vector2 loc1, Vector2 loc2)
	{
		this.race = race;
		this.loc1 = loc1;
		this.loc2 = loc2;
	}

	public boolean cross(Kart kart, Vector2 vector)
	{
		return false;
	}

	public Vector2 getCenter()
	{
		return new Vector2(loc1.x + (loc2.x - loc1.x) / 2, loc1.y + (loc2.y - loc1.y) / 2);
	}

	public Race getRace()
	{
		return race;
	}

	public void setRace(Race race)
	{
		this.race = race;
	}

	public Vector2 getLoc1()
	{
		return loc1;
	}

	public void setLoc1(Vector2 loc1)
	{
		this.loc1 = loc1;
	}

	public Vector2 getLoc2()
	{
		return loc2;
	}

	public void setLoc2(Vector2 loc2)
	{
		this.loc2 = loc2;
	}
}
