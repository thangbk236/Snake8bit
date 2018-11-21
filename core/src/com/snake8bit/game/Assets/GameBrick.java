package com.snake8bit.game.Assets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class GameBrick {
    public GameBrick() {
    }
    public void CalignPosition(SpriteBatch batch, Sprite sprite, float x, float y, Vector2 offsetVal, float transparent){
        sprite.setPosition(offsetVal.x+sprite.getHeight()*(x-3),offsetVal.y+sprite.getHeight()*(y-3));
        sprite.draw(batch,transparent);
    }

    public void dispose() {

    }
}
