package org.wintermartens.kartgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Cedric Martens on 2016-05-09.
 */
public class Kart extends Sprite
{
	private float yaw;

	private Vector2 movement;
	private float topSpeed;

	private float acceleration;
	private float deceleration;

	private float reverseAcceleration;
	private float reverseTopSpeed;

	private float turning;
	private float brakeSpeed;

	private float x;
	private float y;

	private Race race;

	private float direction; //in radians

	private int currentLineId;
	private int currentLap;

	private Vector2 baseLoc;
	private KartGame kartgame;

	public Kart(KartGame kartgame, float topSpeed, float acceleration, float deceleration, float turning, float brakeSpeed, float direction, Race race)
	{
		this.kartgame = kartgame;

		this.topSpeed = topSpeed;
		this.acceleration = acceleration;
		this.deceleration = deceleration;

		this.turning = turning;
		this.brakeSpeed = brakeSpeed;

		this.reverseAcceleration = acceleration / 3;
		this.reverseTopSpeed = topSpeed / 3;

		this.direction = direction;

		this.race = race;

		this.movement = new Vector2(0,0);
	}

	public void update(long deltaTime)
	{
		this.movement = getDirection(this.direction);
		move(deltaTime);
	}

	public void turn(long delta, boolean clockwise)
	{

	}

	public void move(long deltaTime)
	{
		//Brake has priority over acceleration button
		//Would be nice to be able to configure keys in menu
		if(Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)
		|| Gdx.input.isKeyPressed(Input.Keys.DOWN))
		{
			//if speed is NOT very close to 0
			//decelerate
			//else speed == 0
			//then if the key is still pressed after 0.x seconds
			//Start being in reverse mode

		}else if(Gdx.input.isKeyPressed(Input.Keys.SPACE))
		{
			accelerate();
		}

	}

	public void replace()
	{

	}

	/**
	 * Accelerates the kart up to topSpeed, is also used to decelerate while moving back
	 */
	private void accelerate()
	{
		//speed.add() will need to know which direction in rad the car faces, come talk to me if you read this
	}


	private void decelerate()
	{

	}

	private Vector2 getDirection(float direction)
	{
		return new Vector2(this.x + MathUtils.cos(direction), this.y + MathUtils.sin(direction));
	}
}
