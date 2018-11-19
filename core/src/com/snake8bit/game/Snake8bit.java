package com.snake8bit.game;
import com.badlogic.gdx.Game;
import com.snake8bit.game.Assets.GameAsset;
import com.snake8bit.game.Assets.GameJson;
import com.snake8bit.game.Screen.MainMenuScreen;

public class Snake8bit extends Game {

    GameAsset gameAsset;
    public static PlayServices playservices;

    public Snake8bit(PlayServices playservices) {
        this.playservices = playservices;
    }
    @Override
    public void create () {
        gameAsset = new GameAsset();
        GameJson.load();
        this.setScreen(MainMenuScreen.getInstance(this,true));
    }

    @Override
    public void render () {

        super.render();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void dispose () {

    }
}
