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
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

public class Pong extends ApplicationAdapter {
	private float playerSpeed;
	private float enemySpeed;
	private Music bgm;
	private Stage stage;

	private SpriteBatch spriteBatch;
	private Sprite ballSprite;
	private World world;
	private Body ballBody;

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
				sprite.translateX(-1.0f * playerSpeed);
			}
			else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
				sprite.translateX(playerSpeed);
			}
		}
	}

	public class Enemy extends Actor {
		Sprite sprite = new Sprite(new Texture("pongboard.jpg"));

		public Enemy() {
			setPosition(Gdx.graphics.getWidth() / 2  - sprite.getWidth() / 2, Gdx.graphics.getHeight() - sprite.getHeight());
			sprite.setPosition(getX(), getY());
		}

		@Override
		public void draw(Batch batch, float alpha) {
			sprite.draw(batch);
		}

		@Override
		public void act(float delta) {
			super.act(delta);
			sprite.setPosition(getX(), getY());
		}

		@Override
		public float getWidth() {
			return sprite.getWidth();
		}

		@Override
		public float getHeight() {
			return sprite.getHeight();
		}

	}

	@Override
	public void create () {
		playerSpeed = 5.0f;
		enemySpeed = 5.0f;

		Player player = new Player();
		Enemy enemy = new Enemy();

		MoveToAction enemyMovementRight = new MoveToAction();
		enemyMovementRight.setPosition(Gdx.graphics.getWidth() - enemy.getWidth(), Gdx.graphics.getHeight() - enemy.getHeight());
		enemyMovementRight.setDuration(enemySpeed);

		MoveToAction enemyMovementLeft = new MoveToAction();
		enemyMovementLeft.setPosition(0, Gdx.graphics.getHeight() - enemy.getHeight());
		enemyMovementLeft.setDuration(enemySpeed);

		SequenceAction enemyMovementSequence = new SequenceAction();
		enemyMovementSequence.addAction(enemyMovementRight);
		enemyMovementSequence.addAction(enemyMovementLeft);

		RepeatAction enemyMovementRepeat = new RepeatAction();
		enemyMovementRepeat.setAction(enemyMovementSequence);
		enemyMovementRepeat.setCount(RepeatAction.FOREVER);

		enemy.addAction(enemyMovementRepeat);

		stage = new Stage();
		stage.addActor(player);
		stage.addActor(enemy);

		bgm = Gdx.audio.newMusic(Gdx.files.internal("BGM.ogg"));
		bgm.play();


		ballSprite = new Sprite(new Texture("pongball.jpg"));
		ballSprite.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);


		world = new World(new Vector2(0, -98f), true);
		BodyDef pongBallDef = new BodyDef();
		pongBallDef.type = BodyDef.BodyType.DynamicBody;
		pongBallDef.position.set(ballSprite.getX(), ballSprite.getY());
		ballBody = world.createBody(pongBallDef);

		spriteBatch = new SpriteBatch();
	}

	@Override
	public void render () {
		world.step(Gdx.graphics.getDeltaTime(), 6, 2);
		ballSprite.setPosition(ballBody.getPosition().x, ballBody.getPosition().y);

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		spriteBatch.begin();
		ballSprite.draw(spriteBatch);
		spriteBatch.end();

		stage.act();
		stage.draw();
	}
	
	@Override
	public void dispose () {
		stage.dispose();
		bgm.dispose();
	}
}
