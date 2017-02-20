package com.pong.raymondhong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * An Actor that represents the Pong Ball
 */
public class PongBall extends Actor {
    private Sprite ballSprite;
    private Body ballBody;
    private static final float ballSpeed = 10f;

    /*
    Constructs a PongBall without a Body - most functions will fail without a Body
     */
    public PongBall() {
        ballSprite = new Sprite(new Texture("pongball.jpg"));
        ballSprite.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
    }

    @Override
    public void draw(Batch batch, float alpha) {
        ballSprite.draw(batch);
    }

    @Override
    public void act(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            ballBody.setLinearVelocity(0f, ballSpeed);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            ballBody.setLinearVelocity(-1.0f * ballSpeed, 0f);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            ballBody.setLinearVelocity(0f, -1.0f * ballSpeed);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            ballBody.setLinearVelocity(ballSpeed, 0f);
        }
        ballSprite.setPosition(ballBody.getPosition().x, ballBody.getPosition().y);
    }

    /**
     * Attaches a body to the ball
     * @param body the body to be attached
     */
    public void attachBody(Body body) {
        ballBody = body;
    }
}