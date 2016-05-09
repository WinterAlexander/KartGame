package org.wintermartens.kartgame;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.Iterator;

/**
 * Represents a checkpoint for the race
 * Contains 2 locations being the extremities of the line to cross
 * Also used to replace players on the race
 *
 * Created by Alexander Winter on 2016-05-09.
 */
public class RaceLine
{
	private Vector2 loc1, loc2;

	private Vector2 center; //buffer object

	public RaceLine(Vector2 loc1, Vector2 loc2)
	{
		this.loc1 = loc1;
		this.loc2 = loc2;

		this.center = new Vector2();
	}

	/**
	 * Retrieves the nearest location in the locations Array
	 *
	 * @param locations an Array of locations
	 * @return the nearest location
	 */
	public Vector2 getNearest(Array<Vector2> locations)
	{
		Iterator<Vector2> iterator = locations.iterator();

		if(!iterator.hasNext())
			return null;

		Vector2 bestLocation = iterator.next();
		double bestDistance = distance(bestLocation);

		for(Vector2 loc : locations)
			if(distance(loc) < bestDistance)
			{
				bestDistance = distance(loc);
				bestLocation = loc;
			}
		return bestLocation;
	}

	/**
	 * Verify if the source location is crossing the line by
	 * moving to specified destination location. For this
	 * method, the RaceLine isn't a segment but a straight.
	 * A location will still cross it outside the extremities
	 * of the line
	 *
	 * @param sourceLoc source location
	 * @param destLoc destination
	 * @return whether or not the source location is crossing the line by moving to the destination
	 */
	public boolean cross(Vector2 sourceLoc, Vector2 destLoc)
	{
		if(getLoc1().x == getLoc2().x && getLoc1().y == getLoc2().y) //si les points ne forment pas de droite
			return false;

		if(getLoc1().x == getLoc2().x) //si c'est une droite verticale
		{
			return sourceLoc.x > getLoc1().x && destLoc.x < getLoc1().x
					|| sourceLoc.x < getLoc1().x && destLoc.x > getLoc1().x;
		}
		else if(getLoc1().y == getLoc2().y) //si c'est une droite horizontale
		{
			return sourceLoc.y > getLoc1().y && destLoc.y < getLoc1().y
					|| sourceLoc.y < getLoc1().y && destLoc.y > getLoc1().y;
		}

		//si on est ici c'est une droite ax + b delta X != 0 delta Y != 0

		double a1 = (getLoc2().y - getLoc1().y) / (getLoc2().x - getLoc1().x); //pente droite du segment

		double b1 = getLoc1().y - a1 * getLoc1().x; //valeur initiale de la droite 1 (droite du segment)

		return sourceLoc.y < a1 * sourceLoc.x + b1 && destLoc.y > a1 * destLoc.x + b1
				|| sourceLoc.y > a1 * sourceLoc.x + b1 && destLoc.y < a1 * destLoc.x + b1;
	}

	/**
	 * @return the center location of the line
	 */
	public Vector2 getCenter()
	{
		center.set(loc1.x + (loc2.x - loc1.x) / 2, loc1.y + (loc2.y - loc1.y) / 2);
		return center;
	}

	/**
	 * @param location a location
	 * @return the distance between the specified location and the line
	 */
	public double distance(Vector2 location)
	{
		return Math.abs((getLoc2().y - getLoc1().y) * location.x + (getLoc1().x - getLoc2().x) * location.y + getLoc1().y * (getLoc2().x - getLoc1().x) + getLoc1().x * (getLoc1().y - getLoc2().y)) / Math.sqrt(Math.pow((getLoc2().y - getLoc1().y), 2) + Math.pow((getLoc1().x - getLoc2().x), 2));
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
