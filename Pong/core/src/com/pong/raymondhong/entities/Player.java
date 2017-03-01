package com.pong.raymondhong.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.pong.raymondhong.Pong;

/**
 * An Entity representing the Player Board
 */
public class Player extends Entity implements InputProcessor {
    private static final float playerSpeed = 0.2f;

    /**
     * Constructs a Player Board
     */
    public Player(World world) {
        super(new Sprite(new Texture("pongboard.jpg")));
        initializeBody(world);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void act(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            float newPos = body.getPosition().x - playerSpeed;
            if (newPos * Pong.PIXELS_PER_METER - getWidth() / 2 <= 0) {
                body.setTransform(getWidth() / 2 /Pong.PIXELS_PER_METER, body.getPosition().y, body.getAngle());
            }
            else {
                body.setTransform(newPos, body.getPosition().y, body.getAngle());
            }
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            float newPos = body.getPosition().x + playerSpeed;
            if (newPos * Pong.PIXELS_PER_METER + getWidth() / 2 >= Gdx.graphics.getWidth()) {
                body.setTransform((Gdx.graphics.getWidth() - getWidth() / 2) / Pong.PIXELS_PER_METER, body.getPosition().y, body.getAngle());
            }
            else {
                body.setTransform(newPos, body.getPosition().y, body.getAngle());
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
        bodyDef.position.set(Gdx.graphics.getWidth() / 2 / Pong.PIXELS_PER_METER, getHeight() / 2 / Pong.PIXELS_PER_METER);
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

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        float newPos = screenX;
        if (newPos + getWidth() / 2 >= Gdx.graphics.getWidth()) {
            body.setTransform((Gdx.graphics.getWidth() - getWidth() / 2) / Pong.PIXELS_PER_METER, body.getPosition().y, body.getAngle());
        }
        else if (newPos - getWidth() / 2 <= 0) {
            body.setTransform(getWidth() / 2 /Pong.PIXELS_PER_METER, body.getPosition().y, body.getAngle());
        }
        else {
            body.setTransform(newPos / Pong.PIXELS_PER_METER, body.getPosition().y, body.getAngle());
        }
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        float newPos = screenX;
        if (newPos + getWidth() / 2 >= Gdx.graphics.getWidth()) {
            body.setTransform((Gdx.graphics.getWidth() - getWidth() / 2) / Pong.PIXELS_PER_METER, body.getPosition().y, body.getAngle());
        }
        else if (newPos - getWidth() / 2 <= 0) {
            body.setTransform(getWidth() / 2 /Pong.PIXELS_PER_METER, body.getPosition().y, body.getAngle());
        }
        else {
            body.setTransform(newPos / Pong.PIXELS_PER_METER, body.getPosition().y, body.getAngle());
        }
        return true;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}