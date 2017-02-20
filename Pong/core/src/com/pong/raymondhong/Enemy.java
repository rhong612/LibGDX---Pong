package com.pong.raymondhong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

/**
 * An Actor representing the AI Enemy Pong Board
 */
public class Enemy extends Actor {
    private Sprite sprite;
    private static final float enemySpeed = 5.0f;

    /**
     * Constructs an Enemy PongBoard
     */
    public Enemy() {
        sprite = new Sprite(new Texture("pongboard.jpg"));
        setPosition(Gdx.graphics.getWidth() / 2  - sprite.getWidth() / 2, Gdx.graphics.getHeight() - sprite.getHeight());
        sprite.setPosition(getX(), getY());

        MoveToAction enemyMovementRight = new MoveToAction();
        enemyMovementRight.setPosition(Gdx.graphics.getWidth() - sprite.getWidth(), Gdx.graphics.getHeight() - sprite.getHeight());
        enemyMovementRight.setDuration(enemySpeed);

        MoveToAction enemyMovementLeft = new MoveToAction();
        enemyMovementLeft.setPosition(0, Gdx.graphics.getHeight() - sprite.getHeight());
        enemyMovementLeft.setDuration(enemySpeed);

        SequenceAction enemyMovementSequence = new SequenceAction();
        enemyMovementSequence.addAction(enemyMovementRight);
        enemyMovementSequence.addAction(enemyMovementLeft);

        RepeatAction enemyMovementRepeat = new RepeatAction();
        enemyMovementRepeat.setAction(enemyMovementSequence);
        enemyMovementRepeat.setCount(RepeatAction.FOREVER);

        this.addAction(enemyMovementRepeat);
    }

    @Override
    public void draw(Batch batch, float alpha) {
        sprite.draw(batch);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        sprite.setPosition(getX(), getY());
    }

}
