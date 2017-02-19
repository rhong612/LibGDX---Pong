package com.pong.raymondhong;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Pong extends ApplicationAdapter {
	private float speed;
	private Music bgm;
	private Stage stage;

	public class Player extends Actor {
		Sprite sprite = new Sprite(new Texture("pongboard.jpg"));

		public Player() {
			sprite.setPosition(Gdx.graphics.getWidth() / 2  - sprite.getWidth() / 2, 0);
		}

		@Override
		public void draw(Batch batch, float alpha) {
			sprite.draw(batch);
		}

		@Override
		public void act(float delta) {
			if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
				sprite.translateX(-1.0f * speed);
			}
			else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
				sprite.translateX(speed);
			}
		}
	}

	public class Enemy extends Actor {
		Sprite sprite = new Sprite(new Texture("pongboard.jpg"));

		public Enemy() {
			sprite.setPosition(Gdx.graphics.getWidth() / 2  - sprite.getWidth() / 2, Gdx.graphics.getHeight() - sprite.getHeight());
		}

		@Override
		public void draw(Batch batch, float alpha) {
			sprite.draw(batch);
		}
	}

	@Override
	public void create () {
		Player player = new Player();
		Enemy enemy = new Enemy();
		stage = new Stage();
		stage.addActor(player);
		stage.addActor(enemy);

		speed = 5.0f;

		bgm = Gdx.audio.newMusic(Gdx.files.internal("BGM.ogg"));
		bgm.play();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act();
		stage.draw();
	}
	
	@Override
	public void dispose () {
		stage.dispose();
		bgm.dispose();
	}
}
