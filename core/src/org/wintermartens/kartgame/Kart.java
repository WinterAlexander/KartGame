package org.wintermartens.kartgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Cedric Martens on 2016-05-09.
 */
public class Kart extends Sprite
{
	private float yaw;

	private Vector2 speed;
	private float topSpeed;

	private float acceleration;
	private float deceleration;

	private float reverseAcceleration;
	private float reverseTopSpeed;

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
		this.reverseAcceleration = acceleration / 3;
		this.reverseTopSpeed = topSpeed / 3;
	}

	public void update(long deltaTime)
	{
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
			//then if the key is still pressed after x seconds
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
}
