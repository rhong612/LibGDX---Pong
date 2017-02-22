package com.pong.raymondhong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

/**
 * An Actor representing the AI Enemy Pong Board
 */
public class Enemy extends Entity {
    private static final float enemySpeed = 5.0f;

    /**
     * Constructs an Enemy PongBoard
     */
    public Enemy(World world) {
        super(new Sprite(new Texture("pongboard.jpg")));

        MoveToAction enemyMovementRight = new MoveToAction();
        enemyMovementRight.setPosition(Gdx.graphics.getWidth() - getWidth(), Gdx.graphics.getHeight() - getHeight());
        enemyMovementRight.setDuration(enemySpeed);

        MoveToAction enemyMovementLeft = new MoveToAction();
        enemyMovementLeft.setPosition(0, Gdx.graphics.getHeight() - getHeight());
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
    public void act(float delta) {

    }

    @Override
    public void initializeBody(World world) {

    }
}
