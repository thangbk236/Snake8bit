package com.snake8bit.game.Assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
import com.snake8bit.game.Screen.GamePlayScreenElement.GamePlayScreenTetrisPlay;

public class GameJson {
    public static FileHandle fileHandle = Gdx.files.local("TetrisJson.json");
    public static FileHandle fileHandle1 = Gdx.files.local("TetrisJson1.json");
    public static Json json = new Json();
    public static Json json1 = new Json();
    public static GamePlayScreenTetrisPlay gameJsonData;
    public static GameData gameData;
    public static void save() {
        json.setOutputType(JsonWriter.OutputType.json);
        json1.setOutputType(JsonWriter.OutputType.json);
        fileHandle.writeString(json.prettyPrint(gameJsonData),false);
        fileHandle1.writeString(json1.prettyPrint(gameData),false);
    }
    public static void load() {
        gameJsonData = new GamePlayScreenTetrisPlay();
        if (saveFileExists()) {    // neu file chi dinh ton tai
            gameJsonData=json.fromJson(GamePlayScreenTetrisPlay.class,fileHandle);
        }
        gameData = new GameData();
        if (saveFileExists1()) {    // neu file chi dinh ton tai
            gameData=json1.fromJson(GameData.class,fileHandle1);
        }
    }
    public static boolean saveFileExists() {
        return fileHandle.exists();
    }
    public static boolean saveFileExists1() {
        return fileHandle1.exists();
    }
}
