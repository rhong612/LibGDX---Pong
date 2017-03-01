package com.pong.raymondhong;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

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

        stage = new Stage();

        playerLabel = new Label("PLAYER", new Label.LabelStyle(new BitmapFont(), Color.BLUE));
        playerScoreLabel = new Label(Integer.toString(playerScore), new Label.LabelStyle(new BitmapFont(), Color.BLUE));
        enemyLabel = new Label("ENEMY", new Label.LabelStyle(new BitmapFont(), Color.BLUE));
        enemyScoreLabel = new Label(Integer.toString(enemyScore), new Label.LabelStyle(new BitmapFont(), Color.BLUE));

        Table labelTable = new Table();
        labelTable.top();
        labelTable.setFillParent(true);

        labelTable.add(playerLabel).expandX().padTop(10);
        labelTable.add(enemyLabel).expandX().padTop(10);

        labelTable.row();

        labelTable.add(playerScoreLabel).expandX();
        labelTable.add(enemyScoreLabel).expandX();

        stage.addActor(labelTable);
    }

    public void draw() {
        playerScoreLabel.setText(Integer.toString(playerScore));
        enemyScoreLabel.setText(Integer.toString(enemyScore));
        stage.draw();
    }

    public void incrementPlayerScore() {
        playerScore++;
    }

    public void incrementEnemyScore() {
        enemyScore++;
    }
}
