package org.wintermartens.kartgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.HashMap;
import java.util.Map;

public class KartGame extends ApplicationAdapter
{
	private SpriteBatch batch;
	private Map<String, Texture> textures;

	private Kart kart;
	private Race race;

	@Override
	public void create()
	{
		this.textures = new HashMap<String, Texture>();

		textures.put("kart", new Texture("kart.png"));
		//this.race = new Race();
		//this.kart = new Kart(this, );
		batch = new SpriteBatch();
	}

	@Override
	public void render()
	{
		batch.begin();

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
}
