package com.pong.raymondhong.entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.pong.raymondhong.Pong;

/**
 * An Entity in the game world that ensures the Box2D components are always in sync with the Scene components
 */
public abstract class Entity extends Actor {
    protected Body body;
    private Sprite sprite;

    public Entity(Sprite sprite) {
        this.sprite = sprite;
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
    }

    /**
     * Synchronizes the sprite/actor position with the Body position
     */
    private void synchronize() {
        sprite.setPosition((body.getPosition().x * Pong.PIXELS_PER_METER) - sprite.getWidth() / 2, (body.getPosition().y * Pong.PIXELS_PER_METER) - sprite.getHeight() / 2 );
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
    }

    @Override
    public void draw(Batch batch, float alpha) {
        synchronize();
        sprite.draw(batch);
    }

    public Body getBody() {
        return body;
    }

    /**
     * Change the body position
     * @param delta
     */
    @Override
    public abstract void act(float delta);

    /**
     * Initializes the body
     * @param world the world to host the body
     */
    public abstract void initializeBody(World world);
}
