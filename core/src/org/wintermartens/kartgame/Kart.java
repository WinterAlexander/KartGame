package org.wintermartens.kartgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

/**
 *
 * Created by Cedric Martens on 2016-05-09.
 */
public class Kart
{
	private KartGame kartgame;

	private Texture texture;

	private Vector2 position;
	private float yaw; //in radians (direction)

	private float width, height;

	private Vector2 movement, newMovement;
	private float topSpeed;

	private float acceleration;
	private float deceleration;

	private float turning;
	private float brakeSpeed;

	private float reverseAcceleration;
	private float reverseTopSpeed;

	private int currentLineId;
	private int currentLap;

	private Vector2 baseLoc;
	private ParticleEffect fuel;

	/**
	 * Creates a new kart
	 * @param kartgame
	 * @param position Starting position
	 * @param yaw direction
	 * @param currentLineId
	 * @param currentLap
	 */
	public Kart(KartGame kartgame, Vector2 position, float yaw, int currentLineId, int currentLap)
	{
		this.kartgame = kartgame;

		this.texture = this.kartgame.getTexture("kart");
		this.position = position;
		this.yaw = yaw;
		this.width = 60;
		this.height = 40;
		this.movement = new Vector2();
		this.newMovement = new Vector2();
		this.topSpeed = 500f;
		this.acceleration = 300f;
		this.deceleration = 400f;
		this.turning = 50f;
		this.brakeSpeed = 4f;
		this.reverseAcceleration = acceleration / 2;
		this.reverseTopSpeed = topSpeed / 2;
		this.currentLineId = currentLineId;
		this.currentLap = currentLap;
		this.baseLoc = new Vector2(position);

		fuel = new ParticleEffect();
		fuel.load(Gdx.files.internal("Effects/fuel.p"), Gdx.files.internal("Effects"));
		fuel.start();

	}

	public void update(float deltaTime)
	{

		float previousYaw = yaw;

		move(deltaTime);
		kartgame.getCamera().position.set(position.x, position.y, 0);
		kartgame.getCamera().rotate(-(yaw - previousYaw) * MathUtils.radiansToDegrees);
		fuel.setPosition(position.x, position.y);
		//fuel.scaleEffect(Math.max(movement.len() / topSpeed * 10, 0.01f));
		//il reste des tweaks a faire au niveau du sable

		for (int i = 0; i < fuel.getEmitters().size; i++)
		{ //get the list of emitters - things that emit particles
			fuel.getEmitters().get(i).getAngle().setLow(MathUtils.radiansToDegrees * yaw - 180); //low is the minimum rotation
			fuel.getEmitters().get(i).getAngle().setHigh(MathUtils.radiansToDegrees * yaw - 180); //high is the max rotation
		}
	}

	public void turn(float delta, boolean clockwise)
	{
		yaw += (clockwise ? -1 : 1) * Math.PI * delta; //* movement.len() / topSpeed;
		movement.setLength(movement.len() - (turning * delta));
	}

	public void move(float deltaTime)
	{
		if(Gdx.input.isKeyPressed(Input.Keys.SPACE) || Gdx.input.isKeyPressed(Input.Keys.UP))
			accelerate(deltaTime);
		else
			decelerate(deltaTime, Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT));

		if(Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A))
			turn(deltaTime, false);

		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D))
			turn(deltaTime, true);

		position.add(movement.x * deltaTime, movement.y * deltaTime);
	}

	public void replace()
	{

	}

	/**
	 * Accelerates the kart up to topSpeed, is also used to decelerate while moving back
	 */
	private void accelerate(float deltaTime)
	{
		newMovement.set(acceleration * deltaTime * turning, 0);
		newMovement.setAngleRad(yaw);

		movement.setLength(movement.len() - deltaTime * turning);

		if(movement.len() + newMovement.len() > topSpeed)
			movement.setLength(topSpeed - newMovement.len());

		movement.add(newMovement);
	}

	private void decelerate(float deltaTime, boolean brake)
	{
		if(movement.len() < 25)
		{
			movement = new Vector2(0, 0);
			return;
		}

		movement.setLength(movement.len() - deceleration * deltaTime * (brake ? brakeSpeed : 1));
	}

	public void draw(SpriteBatch batch)
	{

		if(fuel.isComplete())
			fuel.reset();

		fuel.draw(batch, Gdx.graphics.getDeltaTime());

		batch.draw(texture,
				position.x - width / 2, position.y - height / 2,
				width / 2, height / 2,
				width, height,
				1, 1,
				MathUtils.radiansToDegrees * yaw,
				0, 0,
				texture.getWidth(), texture.getHeight(),
				false, false);
		//batch.draw(texture, position.x - width / 2, position.y - height / 2, width, height);
	}

	private Vector2 getDirection(float direction)
	{
		return new Vector2((position.x + MathUtils.cos(direction)) * 3f, (position.y + MathUtils.sin(direction)) * 3f);
	}

	public KartGame getKartgame()
	{
		return kartgame;
	}

	public void setKartgame(KartGame kartgame)
	{
		this.kartgame = kartgame;
	}

	public Vector2 getPosition()
	{
		return position;
	}

	public void setPosition(Vector2 position)
	{
		this.position = position;
	}

	public float getYaw()
	{
		return yaw;
	}

	public void setYaw(float yaw)
	{
		this.yaw = yaw;
	}

	public float getWidth()
	{
		return width;
	}

	public void setWidth(float width)
	{
		this.width = width;
	}

	public float getHeight()
	{
		return height;
	}

	public void setHeight(float height)
	{
		this.height = height;
	}

	public Vector2 getMovement()
	{
		return movement;
	}

	public void setMovement(Vector2 movement)
	{
		this.movement = movement;
	}

	public float getTopSpeed()
	{
		return topSpeed;
	}

	public void setTopSpeed(float topSpeed)
	{
		this.topSpeed = topSpeed;
	}

	public float getAcceleration()
	{
		return acceleration;
	}

	public void setAcceleration(float acceleration)
	{
		this.acceleration = acceleration;
	}

	public float getDeceleration()
	{
		return deceleration;
	}

	public void setDeceleration(float deceleration)
	{
		this.deceleration = deceleration;
	}

	public float getTurning()
	{
		return turning;
	}

	public void setTurning(float turning)
	{
		this.turning = turning;
	}

	public float getBrakeSpeed()
	{
		return brakeSpeed;
	}

	public void setBrakeSpeed(float brakeSpeed)
	{
		this.brakeSpeed = brakeSpeed;
	}

	public float getReverseAcceleration()
	{
		return reverseAcceleration;
	}

	public void setReverseAcceleration(float reverseAcceleration)
	{
		this.reverseAcceleration = reverseAcceleration;
	}

	public float getReverseTopSpeed()
	{
		return reverseTopSpeed;
	}

	public void setReverseTopSpeed(float reverseTopSpeed)
	{
		this.reverseTopSpeed = reverseTopSpeed;
	}

	public int getCurrentLineId()
	{
		return currentLineId;
	}

	public void setCurrentLineId(int currentLineId)
	{
		this.currentLineId = currentLineId;
	}

	public int getCurrentLap()
	{
		return currentLap;
	}

	public void setCurrentLap(int currentLap)
	{
		this.currentLap = currentLap;
	}

	public Vector2 getBaseLoc()
	{
		return baseLoc;
	}

	public void setBaseLoc(Vector2 baseLoc)
	{
		this.baseLoc = baseLoc;
	}
}
