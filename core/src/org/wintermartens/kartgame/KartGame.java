package org.wintermartens.kartgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class KartGame extends ApplicationAdapter
{
	private Kart kart;
	private Race race;

	@Override
	public void create()
	{

	}

	@Override
	public void render()
	{

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
