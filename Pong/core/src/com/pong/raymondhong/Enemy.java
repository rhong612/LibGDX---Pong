package com.pong.raymondhong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;


/**
 * An Entity representing the AI Enemy Pong Board
 */
public class Enemy extends Entity {
    private static final float enemySpeed = 0.4f;
    private PongBall ball; //Used for tracking
    /**
     * Constructs an Enemy PongBoard
     */
    public Enemy(World world, PongBall ball) {
        super(new Sprite(new Texture("pongboard.jpg")));
        initializeBody(world);
        this.ball = ball;
    }

    @Override
    public void act(float delta) {
        if (ball.body.getPosition().x > body.getPosition().x + enemySpeed) {
            if (getX() + enemySpeed + getWidth() >= Gdx.graphics.getWidth()) {
                body.setTransform((Gdx.graphics.getWidth() - getWidth() / 2) / Pong.PIXELS_PER_METER, body.getPosition().y, body.getAngle());
            }
            else {
                body.setTransform(body.getPosition().x + enemySpeed, body.getPosition().y, body.getAngle());
            }
        }
        else if (ball.body.getPosition().x < body.getPosition().x - enemySpeed) {
            if (getX() - enemySpeed <= 0) {
                body.setTransform(getWidth() / 2 /Pong.PIXELS_PER_METER, body.getPosition().y, body.getAngle());
            }
            else {
                body.setTransform(body.getPosition().x - enemySpeed, body.getPosition().y, body.getAngle());
            }
        }
        else {
            if (getX() + enemySpeed + getWidth() >= Gdx.graphics.getWidth()) {
                body.setTransform((Gdx.graphics.getWidth() - getWidth() / 2) / Pong.PIXELS_PER_METER, body.getPosition().y, body.getAngle());
            }
            else if (getX() - enemySpeed <= 0) {
                body.setTransform(getWidth() / 2 /Pong.PIXELS_PER_METER, body.getPosition().y, body.getAngle());
            }
            else {
                body.setTransform(ball.body.getPosition().x, body.getPosition().y, body.getAngle());
            }
        }
    }

    /**
     * Initializes the body
     * @param world the world to host the body
     */
    @Override
    public void initializeBody(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(Gdx.graphics.getWidth() / 2 / Pong.PIXELS_PER_METER, (Gdx.graphics.getHeight() - getHeight() / 2) / Pong.PIXELS_PER_METER);
        body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(getWidth() / 2 / Pong.PIXELS_PER_METER, getHeight() / 2 / Pong.PIXELS_PER_METER);
        FixtureDef fixture = new FixtureDef();
        fixture.shape = shape;
        fixture.friction = 0f;
        body.createFixture(fixture);
        shape.dispose();
    }
}
