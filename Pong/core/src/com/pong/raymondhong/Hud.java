package com.pong.raymondhong;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

/**
 * A class to keep track of the Score
 */
public class Hud {
    private int playerScore;
    private int enemyScore;

    private Stage stage;

    private Label playerLabel;
    private Label enemyLabel;
    private Label playerScoreLabel;
    private Label enemyScoreLabel;

    public Hud() {
        playerScore = 0;
        enemyScore = 0;
    }

    public void draw() {
        stage.draw();
    }
}
