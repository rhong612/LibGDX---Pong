package com.pong.raymondhong;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Pong extends ApplicationAdapter {
	private Music bgm;
	private Stage stage;
	private World world;

	public static final float PIXELS_PER_METER = 50f; //Every 50 pixels = 1 meter in game

	//DEBUG TOOLS
	private Box2DDebugRenderer debugger;
	private OrthographicCamera debugCam;

	@Override
	public void create () {
		bgm = Gdx.audio.newMusic(Gdx.files.internal("BGM.ogg"));
		bgm.play();

		//Create the world and add gravity
		world = new World(new Vector2(0, 0), true);

		//Initialize all actors and add them to the stage
		Player player = new Player(world);
		Enemy enemy = new Enemy(world);
		PongBall ball = new PongBall(world);

		stage = new Stage();
		stage.addActor(player);
		stage.addActor(enemy);
		stage.addActor(ball);

		//Setup debugging tools
		debugger = new Box2DDebugRenderer();
		debugCam = new OrthographicCamera();
		debugCam.setToOrtho(false, Gdx.graphics.getWidth() / PIXELS_PER_METER, Gdx.graphics.getHeight() / PIXELS_PER_METER);
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
