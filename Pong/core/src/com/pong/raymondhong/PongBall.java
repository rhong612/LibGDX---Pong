package com.pong.raymondhong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

/**
 * An Entity that represents the Pong Ball
 */
public class PongBall extends Entity {
    private static final float ballSpeed = 10f;

    /*
    Constructs a PongBall without a Body - most functions will fail without a Body
     */
    public PongBall(World world) {
        super(new Sprite(new Texture("pongball.jpg")));
        initializeBody(world);

        //Give the ball an initial force to get it moving
        if (Math.random() > 0.5) {
            body.applyForceToCenter(new Vector2(ballSpeed, -1.0f * ballSpeed), true);
        }
        else {
            body.applyForceToCenter(new Vector2(ballSpeed, ballSpeed), true);
        }
    }

    @Override
    public void act(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            body.setLinearVelocity(0f, ballSpeed);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            body.setLinearVelocity(-1.0f * ballSpeed, 0f);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            body.setLinearVelocity(0f, -1.0f * ballSpeed);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            body.setLinearVelocity(ballSpeed, 0f);
        }
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