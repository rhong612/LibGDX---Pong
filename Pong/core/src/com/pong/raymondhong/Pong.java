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
	private Music bgm;
	private Stage stage;
	private World world;

	@Override
	public void create () {
		bgm = Gdx.audio.newMusic(Gdx.files.internal("BGM.ogg"));
		bgm.play();

		//Initialize all actors and add them to the stage
		Player player = new Player();
		Enemy enemy = new Enemy();
		PongBall ball = new PongBall();
		stage = new Stage();
		stage.addActor(player);
		stage.addActor(enemy);
		stage.addActor(ball);

		//Add gravity and create pong ball body
		world = new World(new Vector2(0, -98f), true);
		BodyDef pongBallDef = new BodyDef();
		pongBallDef.type = BodyDef.BodyType.DynamicBody;
		pongBallDef.position.set(ball.getX(), ball.getY());

		//Attach the body to the ball actor
		ball.attachBody(world.createBody(pongBallDef));

	}

	@Override
	public void render () {
		world.step(Gdx.graphics.getDeltaTime(), 6, 2); //Run physics simulation

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act();
		stage.draw();
	}
	
	@Override
	public void dispose () {
		stage.dispose();
		bgm.dispose();
		world.dispose();
	}
}
