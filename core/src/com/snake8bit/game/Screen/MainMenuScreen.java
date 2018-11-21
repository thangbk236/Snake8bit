package com.snake8bit.game.Screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.snake8bit.game.Assets.GameConstant;
import com.snake8bit.game.Assets.GameGrid;
import com.snake8bit.game.Assets.GameHome;
import com.snake8bit.game.Assets.GameJson;
import com.snake8bit.game.Snake8bit;

public class MainMenuScreen implements Screen {
    private OrthographicCamera camera;
    SpriteBatch batch;
    protected final Game game;
    private int gameId;
    protected static MainMenuScreen mainMenuScreen;
    GameGrid gameGrid;
    GameHome gameHome;
    private float timeToShowAds;
    private boolean timeToShowAdsLatch;
    public static boolean firstTimePlay=true;
    public boolean onSaveToJson;
    public MainMenuScreen(Game game){
        camera = new OrthographicCamera();
        camera.setToOrtho(false, GameConstant.S_WIDTH, GameConstant.S_HEIGHT);
        camera.update();
        batch = new SpriteBatch();
        this.game=game;
        gameGrid=new GameGrid(0);
        gameHome=new GameHome(game);
        timeToShowAds=0.0f;
        timeToShowAdsLatch=false;
    }

    public static MainMenuScreen getInstance(Game game,boolean newscreen){
        if (newscreen){
            mainMenuScreen=new MainMenuScreen(game);
        }
        else {
            if(mainMenuScreen==null){
                mainMenuScreen=new MainMenuScreen(game);
            }
        }
        return mainMenuScreen;
    }
    @Override
    public void render(float delta){
        if (!firstTimePlay){
            timeToShowAds+=delta;
            if (timeToShowAds>3.0f){//sau 3s thi cho hien thi ads
                if (!timeToShowAdsLatch){
                    timeToShowAdsLatch=true;
                    Snake8bit.playservices.showAds();
                }
            }
        }

        Gdx.gl.glClearColor(0.8f, 0.8f, 0.8f, 0.8f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gameGrid.GameUpdate(delta,batch);
        gameHome.GameUpdate(delta,batch);
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
