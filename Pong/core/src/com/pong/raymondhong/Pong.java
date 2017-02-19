package com.pong.raymondhong;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Pong extends ApplicationAdapter {
	private SpriteBatch batch;
	private Sprite sprite;
	private Texture img;
	private float speed;
	private Music bgm;
	
	@Override
	public void create () {
		float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();
		batch = new SpriteBatch();
		img = new Texture("pongboard.jpg");
		sprite = new Sprite(img);
		sprite.setPosition(width / 2 - (sprite.getWidth() / 2), 0);

		speed = 5.0f;

		bgm = Gdx.audio.newMusic(Gdx.files.internal("BGM.ogg"));
		bgm.play();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			sprite.translateX(-1.0f * speed);
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			sprite.translateX(speed);
		}


		batch.begin();
		sprite.draw(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		bgm.dispose();
	}
}
