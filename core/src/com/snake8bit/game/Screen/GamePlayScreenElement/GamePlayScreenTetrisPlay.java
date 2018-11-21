package com.snake8bit.game.Screen.GamePlayScreenElement;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.snake8bit.game.Assets.GameAsset;
import com.snake8bit.game.Assets.GameBrick;
import com.snake8bit.game.Assets.GameConstant;
import com.snake8bit.game.Assets.GameJson;
import com.badlogic.gdx.audio.Sound;
public class GamePlayScreenTetrisPlay {
    public float headTime;
    public float foodTime;
    public boolean headFlip;
    public boolean foodFlip;

    public int alldots;
    public int snakelength;
    public int[] snake_data_x;
    public int[] snake_data_y;
    public int snake_dir;
    public float GameTime;
    public float GameSpeed;
    public float _GameSpeed;
    public int IncreaseSpeed;
    public int food_data_x;
    public int food_data_y;
    public boolean gameoverlatch;
    public boolean VibrateLatch;
    public int vibratelong;
    public int vibrateshort;
    public int HI_SCORE;
    public int SCORE;
    public int LEVEL;
    public int MAX_SCORE;

    public GamePlayScreenTetrisPlay(){
        VibrateLatch=true;
        gameoverlatch=true;
        HI_SCORE=0;
        _GameSpeed = 5.0f;
        alldots = 900;
        snakelength = 2;
        vibratelong = 200;
        vibrateshort = 50;
        VibrateLatch = false;
        gameoverlatch = false;
        snake_data_x = new int[alldots];
        snake_data_y = new int[alldots];
        snake_dir = 0;
        snake_data_x[0] = MathUtils.random(1, 11);
        snake_data_y[0] = MathUtils.random(0, 19);
        snake_data_x[1] = snake_data_x[0] - 1;
        snake_data_y[1] = snake_data_y[0];
        food_data_x = MathUtils.random(0, 11);
        food_data_y = MathUtils.random(0, 19);
        SCORE=0;
        LEVEL=0;
        MAX_SCORE=9999999;
    }
    public void render(float deltatime, SpriteBatch batch){
        CheckButton();
        GameTime+=deltatime;
        if (GameTime>1.0f/GameSpeed){
            GameTime=0.0f;
            CheckFood();
            SnakeMove();
            CheckCollision();
        }
        GameFlip(deltatime);
        GameDraw(batch);
    }

    public void GameFlip(float deltaTime) {
        headTime+=deltaTime;
        if (headTime>0.25f) {
            headTime=0.0f;
            if (headFlip){
                headFlip=false;
            }
            else {
                headFlip=true;
            }
        }
        foodTime+=deltaTime;
        if (foodTime>0.2f){
            foodTime=0.0f;
            if (foodFlip){
                foodFlip=false;
            }
            else {
                foodFlip=true;
            }
        }
    }
    public void CheckCollision(){
        for (int i = snakelength; i>0;i--) {
            if (i>3){
                if (snake_data_x[0]==snake_data_x[i]){
                    if (snake_data_y[0]==snake_data_y[i]){
                        GamePlayScreenTetris.gameState=2;
                        i=0;
                    }
                }
            }
        }
        if(snake_data_x[0]<0){
            snake_data_x[0]=11;
        }
        else if(snake_data_y[0]<0) {
            snake_data_y[0]=19;
        }
        else if(snake_data_x[0]>11){
            snake_data_x[0]=0;
        }
        else if(snake_data_y[0]>19){
            snake_data_y[0]=0;
        }

    }
    public void CheckButton() {
        if (GamePlayScreenButton.isFireButton) {
            GameSpeed=_GameSpeed+5.0f;
        }
        else {
            GameSpeed = _GameSpeed;
        }
        if (GamePlayScreenButton.isUpButton){
            if (snake_dir!=2)
                snake_dir=1;
        }
        else if (GamePlayScreenButton.isDownButton){
            if (snake_dir!=1)
                snake_dir=2;
        }
        else if (GamePlayScreenButton.isLeftButton){
            if (snake_dir!=4)
                snake_dir=3;
        }
        else if (GamePlayScreenButton.isRightButton){
            if (snake_dir!=3)
                snake_dir=4;
        }
    }
    public void SnakeMove(){
        if (snake_dir!=0)
            for (int i = snakelength; i>0;i--){
                snake_data_x[i]=snake_data_x[i-1];
                snake_data_y[i]=snake_data_y[i-1];
            }
        switch (snake_dir){
            case 1:
                snake_data_y[0]+=1;
                break;
            case 2:
                snake_data_y[0]-=1;
                break;
            case 3:
                snake_data_x[0]-=1;
                break;
            case 4:
                snake_data_x[0]+=1;
                break;
            default:
                break;
        }
    }
    public void CheckFood() {
        if (snake_data_x[0]==food_data_x) {
            if (snake_data_y[0]==food_data_y){
                if (GameJson.gameData.isMusic){
                    GameAsset.eatSound.play();
                }
                if (GameJson.gameData.isVibrate){
                    Gdx.input.vibrate(80);
                }
                snakelength++;
                if(snakelength>180){
                    snakelength=180;
                }
                SCORE+=100;
                if ( SCORE>MAX_SCORE){
                    SCORE=MAX_SCORE;
                }
                if ( SCORE> GameJson.gameData.HiScore){
                    GameJson.gameData.HiScore=SCORE;
                }
                LEVEL=SCORE/10000;

                IncreaseSpeed++;
                if(IncreaseSpeed>100){
                    IncreaseSpeed=0;
                    _GameSpeed+=0.4f;
                    if (_GameSpeed>15.0f){
                        _GameSpeed=15.0f;
                    }
                }
                food_data_x=MathUtils.random(0,11);
                food_data_y=MathUtils.random(0,19);
            }
        }
    }
    public void GameDraw(SpriteBatch batch){
        GameJson.gameData.Score=SCORE;
        GameJson.gameData.Level=LEVEL;
        batch.begin();
        if (foodFlip){
            CalignPosition(batch, GameAsset.BrickSprite72,food_data_x,food_data_y, GameConstant.MAIN_GAME_GRID_OFFSET,1.0f);
        }
        for(int i = 0;i<snakelength;i++){
            if (i==0){
                if (headFlip) {
                    CalignPosition(batch, GameAsset.BrickSprite72,snake_data_x[i],snake_data_y[i], GameConstant.MAIN_GAME_GRID_OFFSET,1.0f);
                }
                else {
                    CalignPosition(batch, GameAsset.BrickSprite72,snake_data_x[i],snake_data_y[i], GameConstant.MAIN_GAME_GRID_OFFSET,0.3f);
                }
            }
            else {
                if(snake_data_x[i]==food_data_x){
                    if (snake_data_y[i]==food_data_y){
                    }
                    else {
                        CalignPosition(batch, GameAsset.BrickSprite72,snake_data_x[i],snake_data_y[i], GameConstant.MAIN_GAME_GRID_OFFSET,1.0f);
                    }
                }
                else {
                    CalignPosition(batch, GameAsset.BrickSprite72,snake_data_x[i],snake_data_y[i], GameConstant.MAIN_GAME_GRID_OFFSET,1.0f);
                }
            }
        }
        batch.end();
    }
    public void CalignPosition(SpriteBatch batch, Sprite sprite, int x, int y, Vector2 offsetVal, float transparent){
        sprite.setPosition(offsetVal.x+sprite.getHeight()*(x),offsetVal.y+sprite.getHeight()*(y));
        sprite.draw(batch,transparent);
    }
}
