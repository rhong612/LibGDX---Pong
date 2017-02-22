package com.pong.raymondhong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * An Actor representing the Player Board
 */
public class Player extends Actor {
    private Sprite sprite;
    private Body body;
    private static final float playerSpeed = 5.0f;

    /**
     * Constructs a Player Board
     */
    public Player() {
        sprite = new Sprite(new Texture("pongboard.jpg"));
        sprite.setPosition(Gdx.graphics.getWidth() / 2  - sprite.getWidth() / 2, 0);
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
    }

    @Override
    public void draw(Batch batch, float alpha) {
        sprite.draw(batch);
    }

    @Override
    public void act(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            sprite.translateX(-1.0f * playerSpeed);
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            sprite.translateX(playerSpeed);
        }
        body.setTransform(sprite.getX() + sprite.getWidth() / 2, sprite.getY() + sprite.getHeight() / 2, body.getAngle());
    }

    public void attachBody(Body body) {
        this.body = body;
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(sprite.getWidth() / 2, sprite.getHeight() / 2);
        FixtureDef fixture = new FixtureDef();
        fixture.shape = shape;
        fixture.density = 0.1f;
        body.createFixture(fixture);
        shape.dispose();
    }
}