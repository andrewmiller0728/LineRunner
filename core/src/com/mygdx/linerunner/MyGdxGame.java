package com.mygdx.linerunner;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends Game {

	private GameScreen screen;

	@Override
	public void create() {
		this.setScreen(screen = new GameScreen());
	}

	@Override
	public void render() {
		screen.render(Gdx.graphics.getDeltaTime());
	}
}
