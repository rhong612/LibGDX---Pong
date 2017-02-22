package com.pong.raymondhong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * An Actor that represents the Pong Ball
 */
public class PongBall extends Entity {
    private static final float ballSpeed = 10f;

    /*
    Constructs a PongBall without a Body - most functions will fail without a Body
     */
    public PongBall(World world) {
        super(new Sprite(new Texture("pongball.jpg")));
        initializeBody(world);
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
     * Attaches a body to the ball
     * @param world the body to be attached
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
        body.createFixture(fixture);
        shape.dispose();
    }
}