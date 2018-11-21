package com.snake8bit.game.Assets;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class GameGrid {
    public float TransparentHi = 0.3f;
    public float TransparentLo = 0.1f;
    public int GridMode;
    public GameGrid(int GridMode){
        this.GridMode=GridMode;
    }
    public void GameUpdate(float deltaTime,SpriteBatch batch) {

        batch.begin();
        switch (GridMode){
            case 0: // man hinh home
                for (int x=0;x<GameConstant.HOME_GAME_GRID.x;x++){ // truc x
                    for (int y=0;y<GameConstant.HOME_GAME_GRID.y;y++){ // truc y
                        CalignPosition(batch, GameAsset.BrickSprite72,x,y, GameConstant.HOME_GAME_GRID_OFFSET,TransparentLo);
                    }
                }
                break;
            case 1: // man hinh game play
                for (int x=0;x<GameConstant.MAIN_GAME_GRID.x;x++){ // truc x
                    for (int y=0;y<GameConstant.MAIN_GAME_GRID.y;y++){ // truc y
                        CalignPosition(batch, GameAsset.BrickSprite72,x,y, GameConstant.MAIN_GAME_GRID_OFFSET,TransparentLo);
                    }
                }
//                for (int x=0;x<GameConstant.SUB_GAME_GRID.x;x++){ // truc x
//                    for (int y=0;y<GameConstant.SUB_GAME_GRID.y;y++){ // truc y
//                        CalignPosition(batch, GameAsset.BrickSprite48,x,y, GameConstant.SUB_GAME_GRID_OFFSET,TransparentLo);
//                    }
//                }
                break;
            case 2:
                break;
        }
        batch.end();
    }
    public void CalignPosition(SpriteBatch batch, Sprite sprite, int x, int y, Vector2 offsetVal, float transparent){
        sprite.setPosition(offsetVal.x+sprite.getHeight()*x,offsetVal.y+sprite.getHeight()*y);
        sprite.draw(batch,transparent);
    }
}
