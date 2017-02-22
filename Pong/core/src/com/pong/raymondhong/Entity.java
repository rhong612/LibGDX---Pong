package com.pong.raymondhong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Raymond on 2/22/2017.
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
