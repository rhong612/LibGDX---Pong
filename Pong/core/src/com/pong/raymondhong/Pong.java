package com.pong.raymondhong;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.pong.raymondhong.entities.*;

public class Pong extends ApplicationAdapter {
	private Sound hitPaddleSound;
	private Sound hitWallSound;
	private Sound missSound;
	private Stage stage;
	private World world;
	private Hud hud;

	private Body playerBody;
	private Body enemyBody;
	private Body ballBody;

	public static final float PIXELS_PER_METER = 50f; //Every 50 pixels = 1 meter in game
	private static final float speedUpFactor = 1.01f;
	private static final float speedThreshold = 7f;
	private static final float fastSpeedUp = 1.5f;

	//DEBUG TOOLS
	private Box2DDebugRenderer debugger;
	private OrthographicCamera debugCam;

	@Override
	public void create () {

		hitPaddleSound = Gdx.audio.newSound(Gdx.files.internal("pong_paddle.wav"));
		hitWallSound = Gdx.audio.newSound(Gdx.files.internal("pong_wall.wav"));
		missSound = Gdx.audio.newSound(Gdx.files.internal("pong_miss.wav"));

		//Create the world
		world = new World(new Vector2(0, 0), true);
		hud = new Hud();

		//Initialize all actors and add them to the stage
		Player player = new Player(world);
		PongBall ball = new PongBall(world, hud, missSound);
		Enemy enemy = new Enemy(world, ball);

		playerBody = player.getBody();
		ballBody = ball.getBody();
		enemyBody = enemy.getBody();

		stage = new Stage();
		stage.addActor(player);
		stage.addActor(enemy);
		stage.addActor(ball);


		//Create bodies for the walls to prevent pong ball from escaping
		//LEFT WALL
		BodyDef leftDef = new BodyDef();
		leftDef.type = BodyDef.BodyType.StaticBody;
		final Body leftWall = world.createBody(leftDef);

		EdgeShape leftShape = new EdgeShape();
		leftShape.set(0f, 0f, 0f, Gdx.graphics.getHeight() / PIXELS_PER_METER);
		FixtureDef leftFixture = new FixtureDef();
		leftFixture.shape = leftShape;
		leftFixture.friction = 0f;
		leftFixture.restitution = 1f;
		leftWall.createFixture(leftFixture);
		leftShape.dispose();

		//RIGHT WALL
		BodyDef rightDef = new BodyDef();
		rightDef.type = BodyDef.BodyType.StaticBody;
		final Body rightWall = world.createBody(rightDef);

		EdgeShape rightShape = new EdgeShape();
		rightShape.set(Gdx.graphics.getWidth() / PIXELS_PER_METER, 0f, Gdx.graphics.getWidth() / PIXELS_PER_METER, Gdx.graphics.getHeight() / PIXELS_PER_METER);
		FixtureDef rightFixture = new FixtureDef();
		rightFixture.shape = rightShape;
		rightFixture.friction = 0f;
		rightFixture.restitution = 1f;
		rightWall.createFixture(rightFixture);
		rightShape.dispose();


		world.setContactListener(new ContactListener() {
			@Override
			public void beginContact(Contact contact) {
				Body a = contact.getFixtureA().getBody();
				Body b = contact.getFixtureB().getBody();
				checkSpeedOfBall(a, b);
				checkSpeedOfBall(b, a);

				//If contact is between ball and wall
				if ((a == ballBody && (b == leftWall || b == rightWall)) || ((a == leftWall || a == rightWall) && b == ballBody)) {
					hitWallSound.play();
				}
				//If contact is between ball and paddle
				else if ((a == ballBody && (b == playerBody || b == enemyBody)) || ((a == playerBody || a == enemyBody) && b == ballBody)) {
					hitPaddleSound.play();
				}
			}

			@Override
			public void endContact(Contact contact) {

			}

			@Override
			public void preSolve(Contact contact, Manifold oldManifold) {

			}

			@Override
			public void postSolve(Contact contact, ContactImpulse impulse) {

			}
		});

		//Setup debugging tools
		debugger = new Box2DDebugRenderer();
		debugCam = new OrthographicCamera();
		debugCam.setToOrtho(false, Gdx.graphics.getWidth() / PIXELS_PER_METER, Gdx.graphics.getHeight() / PIXELS_PER_METER);
	}

	private void checkSpeedOfBall(Body a, Body b) {
		if ((a == playerBody && b == ballBody) || (a == enemyBody && b == ballBody)) {
			if (Math.abs(ballBody.getLinearVelocity().y) < speedThreshold) {
				ballBody.setLinearVelocity(ballBody.getLinearVelocity().x * fastSpeedUp, ballBody.getLinearVelocity().y * fastSpeedUp);
			}
			else {
				ballBody.setLinearVelocity(ballBody.getLinearVelocity().x * speedUpFactor, ballBody.getLinearVelocity().y * speedUpFactor);
			}
		}
	}

	@Override
	public void render () {
		world.step(1f/60f, 6, 2); //Run physics simulation at a rate of 60Hz

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act();
		stage.draw();
		hud.draw();

		//Open debugging view
		debugger.render( world, debugCam.combined);
	}
	
	@Override
	public void dispose () {
		stage.dispose();
		world.dispose();
		hitPaddleSound.dispose();
		hitWallSound.dispose();
		missSound.dispose();
	}
}
