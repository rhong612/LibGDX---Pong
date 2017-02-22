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
    private static final float enemySpeed = 5.0f;

    /**
     * Constructs an Enemy PongBoard
     */
    public Enemy(World world) {
        super(new Sprite(new Texture("pongboard.jpg")));
        initializeBody(world);
    }

    @Override
    public void act(float delta) {

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
        fixture.density = 0.1f;
        body.createFixture(fixture);
        shape.dispose();
    }
}
