package org.wintermartens.kartgame;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Cedric Martens on 2016-05-09.
 */
public class Kart
{
	private float yaw;

	private Vector2 speed;
	private float topSpeed;

	private float acceleration;
	private float deceleration;

	private float turning;
	private float brakeSpeed;

	private int currentLineId;
	private int currentLap;

	private Vector2 baseLoc;
	private KartGame kartgame;

	public Kart(KartGame kartgame, float topSpeed, float acceleration, float deceleration, float turning, float brakeSpeed)
	{
		this.kartgame = kartgame;
		this.topSpeed = topSpeed;
		this.acceleration = acceleration;
		this.deceleration = deceleration;
		this.turning = turning;
		this.brakeSpeed = brakeSpeed;
	}
}
