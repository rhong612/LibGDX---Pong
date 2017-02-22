package com.pong.raymondhong;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
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

	public static final float PIXELS_TO_METERS = 100f; //Every 100 pixels = 1 meter in game

	//DEBUG TOOLS
	private Box2DDebugRenderer debugger;
	private OrthographicCamera debugCam;

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

		//Add gravity
		world = new World(new Vector2(0, -98f), true);

		//Create ping pong body
		BodyDef pongBallDef = new BodyDef();
		pongBallDef.type = BodyDef.BodyType.DynamicBody;
		pongBallDef.position.set(ball.getX() + ball.getWidth() / 2, ball.getY() + ball.getHeight() / 2);
		ball.attachBody(world.createBody(pongBallDef));

		//Create player body
		BodyDef playerBoardDef = new BodyDef();
		playerBoardDef.type = BodyDef.BodyType.StaticBody;
		playerBoardDef.position.set(player.getX() + player.getWidth() / 2, player.getY() + player.getHeight() / 2);
		player.attachBody(world.createBody(playerBoardDef));

		//Setup debugging tools
		debugger = new Box2DDebugRenderer();
		debugCam = new OrthographicCamera();
		debugCam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	@Override
	public void render () {
		world.step(1f/60f, 6, 2); //Run physics simulation at a rate of 60Hz

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act();
		stage.draw();

		//Open debugging view
		debugger.render( world, debugCam.combined);
	}
	
	@Override
	public void dispose () {
		stage.dispose();
		bgm.dispose();
		world.dispose();
	}
}
