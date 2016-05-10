package org.wintermartens.kartgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;

import java.util.HashMap;
import java.util.Map;

public class KartGame extends ApplicationAdapter
{
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private Map<String, Texture> textures;

	private Kart kart;
	private Race race;

	@Override
	public void create()
	{
		this.textures = new HashMap<>();

		Gdx.input.setInputProcessor(new GameInput(this));

		textures.put("kart", new Texture("kart.png"));
		textures.put("map", new Texture("map.png"));
		textures.put("spawnpoint", new Texture("debug_spawnpoint.png"));
		textures.put("line1", new Texture("debug_line1.png"));
		textures.put("line2", new Texture("debug_line2.png"));
		this.race = new Race(this);
		this.kart = new Kart(this, new Vector2(), 0, 1000f, 1.2f, 1, 0, 0, 0, 0);
		batch = new SpriteBatch();
		camera = new OrthographicCamera(1280, 720);
		camera.translate(1280 / 2, 720 / 2);
	}

	@Override
	public void render()
	{
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		race.draw(batch);
		kart.draw(batch);
		batch.end();

		kart.update(Gdx.graphics.getDeltaTime());
	}

	@Override
	public void dispose()
	{
		super.dispose();
	}

	private void finish()
	{

	}

	private void start()
	{
		Vector2 start = race.getSpawnPoints().first();

		kart.setPosition(start);
		kart.setBaseLoc(start);
		kart.setYaw(start.angleRad(race.getLines().first().getCenter()));
		kart.setCurrentLap(0);
		kart.setCurrentLineId(0);
		race.start();
	}

	public Texture getTexture(String key)
	{
		if(textures.containsKey(key))
			return textures.get(key);

		return new Texture(new Pixmap(128, 128, Pixmap.Format.Alpha));
	}

	public Kart getKart()
	{
		return kart;
	}

	public void setKart(Kart kart)
	{
		this.kart = kart;
	}

	public Race getRace()
	{
		return race;
	}

	public void setRace(Race race)
	{
		this.race = race;
	}

	public OrthographicCamera getCamera()
	{
		return camera;
	}
}
