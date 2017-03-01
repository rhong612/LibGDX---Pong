package com.pong.raymondhong.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.pong.raymondhong.Hud;
import com.pong.raymondhong.Pong;

/**
 * An Entity that represents the Pong Ball
 */
public class PongBall extends com.pong.raymondhong.entities.Entity {
    private static final float initialBallSpeed = 5f;
    private Hud hud;

    /*
    Constructs a PongBall without a Body - most functions will fail without a Body
     */
    public PongBall(World world, Hud hud) {
        super(new Sprite(new Texture("pongball.jpg")));
        this.hud = hud;
        initializeBody(world);
        applyRandomForceToBall();
    }

    private void applyRandomForceToBall() {
        double rand = Math.random();
        if (rand < 0.25) {
            body.applyForceToCenter(new Vector2(initialBallSpeed, -1.0f * initialBallSpeed), true);
        }
        else if (rand < 0.5){
            body.applyForceToCenter(new Vector2(initialBallSpeed, initialBallSpeed), true);
        }
        else if (rand < 0.75) {
            body.applyForceToCenter(new Vector2(-1.0f * initialBallSpeed, initialBallSpeed), true);
        }
        else {
            body.applyForceToCenter(new Vector2(-1.0f * initialBallSpeed, -1.0f * initialBallSpeed), true);
        }
    }

    @Override
    public void act(float delta) {
        if ((body.getPosition().y > Gdx.graphics.getHeight() / Pong.PIXELS_PER_METER)) {
            hud.incrementPlayerScore();
            resetBall();
        }
        else if (body.getPosition().y < 0)  {
            hud.incrementEnemyScore();
            resetBall();
        }
    }

    private void resetBall() {
        body.setTransform(Gdx.graphics.getWidth() / 2 / Pong.PIXELS_PER_METER, Gdx.graphics.getHeight() / 2 / Pong.PIXELS_PER_METER, body.getAngle());
        body.setLinearVelocity(0f, 0f);
        applyRandomForceToBall();
    }

    /**
     * Initializes the body
     * @param world the world to host the body
     */
    public void initializeBody(World world) {
        BodyDef pongBallDef = new BodyDef();
        pongBallDef.type = BodyDef.BodyType.DynamicBody;
        pongBallDef.position.set(Gdx.graphics.getWidth() / 2 / Pong.PIXELS_PER_METER, Gdx.graphics.getHeight() / 2 / Pong.PIXELS_PER_METER);
        body = world.createBody(pongBallDef);
        CircleShape shape = new CircleShape();
        shape.setRadius(getWidth() / 2 / Pong.PIXELS_PER_METER);
        FixtureDef fixture = new FixtureDef();
        fixture.shape = shape;
        fixture.density = 0.1f;
        fixture.restitution = 1f;
        fixture.friction = 0f;
        body.createFixture(fixture);
        shape.dispose();
    }
}