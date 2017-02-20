package com.pong.raymondhong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * An Actor representing the Player Board
 */
public class Player extends Actor {
    private Sprite sprite;
    private static final float playerSpeed = 5.0f;

    /**
     * Constructs a Player Board
     */
    public Player() {
        sprite = new Sprite(new Texture("pongboard.jpg"));
        sprite.setPosition(Gdx.graphics.getWidth() / 2  - sprite.getWidth() / 2, 0);
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
    }
}