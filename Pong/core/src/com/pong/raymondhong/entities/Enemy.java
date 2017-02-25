package com.pong.raymondhong.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.pong.raymondhong.Pong;


/**
 * An Entity representing the AI Enemy Pong Board
 */
public class Enemy extends Entity {
    private static final float enemySpeed = 0.2f;
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

        float wOffset = getWidth() / 2 / Pong.PIXELS_PER_METER;
        float hOffset = getHeight() / 2 / Pong.PIXELS_PER_METER;
        Vector2[] vertices = new Vector2[6];
        vertices[0] = new Vector2(0 - wOffset, getHeight() / 2 / Pong.PIXELS_PER_METER - hOffset);
        vertices[1] = new Vector2(getWidth() / 10 / Pong.PIXELS_PER_METER - wOffset, getHeight() / Pong.PIXELS_PER_METER - hOffset);
        vertices[2] = new Vector2((getWidth() * 9/10) / Pong.PIXELS_PER_METER - wOffset, getHeight() / Pong.PIXELS_PER_METER - hOffset);
        vertices[3] = new Vector2(getWidth() / Pong.PIXELS_PER_METER - wOffset, getHeight() / 2 / Pong.PIXELS_PER_METER - hOffset);
        vertices[4] = new Vector2(getWidth() / 10 / Pong.PIXELS_PER_METER - wOffset, 0 - hOffset);
        vertices[5] = new Vector2((getWidth() * 9/10) / Pong.PIXELS_PER_METER - wOffset, 0 - hOffset);
        shape.set(vertices);

        FixtureDef fixture = new FixtureDef();
        fixture.shape = shape;
        fixture.friction = 0f;
        body.createFixture(fixture);
        shape.dispose();
    }
}
