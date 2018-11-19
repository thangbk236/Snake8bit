package com.snake8bit.game.Screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.snake8bit.game.Assets.GameConstant;
import com.snake8bit.game.Assets.GameJson;
import com.snake8bit.game.Screen.GamePlayScreenElement.GamePlayScreenButton;
import com.snake8bit.game.Screen.GamePlayScreenElement.GamePlayScreenSideBar;
import com.snake8bit.game.Screen.GamePlayScreenElement.GamePlayScreenTetris;

public class GamePlayScreen implements Screen {
    private static GamePlayScreen gamePlayScreen;
    private OrthographicCamera camera;
    SpriteBatch batch;
    public float time;
    protected final Game game;
    private int gameId;

    public boolean onClickLatch;
    public boolean onSaveToJson;

    GamePlayScreenButton gamePlayScreenButton;
    GamePlayScreenTetris gamePlayScreenTetris;
    GamePlayScreenSideBar gamePlayScreenSideBar;

    public GamePlayScreen(Game game){
        camera = new OrthographicCamera();
        camera.setToOrtho(false, GameConstant.S_WIDTH, GameConstant.S_HEIGHT);
        camera.update();
        batch = new SpriteBatch();
        this.game=game;
        onClickLatch=false;
        gamePlayScreenButton = new GamePlayScreenButton(game);
        gamePlayScreenTetris = new GamePlayScreenTetris(game);
        gamePlayScreenSideBar = new GamePlayScreenSideBar();
    }


    public static GamePlayScreen getInstance(Game game,boolean newscreen){
        if (newscreen){
            gamePlayScreen=new GamePlayScreen(game);
        }
        else {
            if(gamePlayScreen==null){
                gamePlayScreen=new GamePlayScreen(game);
            }
        }
        gamePlayScreen.gamePlayScreenTetris.gameState=0;
        return gamePlayScreen;
    }
    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(0.8f, 0.8f, 0.8f, 0.8f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gamePlayScreenButton.render(delta,batch);
        gamePlayScreenTetris.render(delta,batch);
        gamePlayScreenSideBar.render(delta,batch);
    }
    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub
        batch.setProjectionMatrix(camera.combined);
    }
    @Override
    public void show(){

    }
    @Override
    public void hide() {
        // TODO Auto-generated method stub

    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub
        if (!onSaveToJson){
            onSaveToJson=true;
            GameJson.save();
        }
    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub
        onSaveToJson=false;
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        batch.dispose();
    }
}
