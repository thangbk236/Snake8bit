package com.snake8bit.game.Screen.GamePlayScreenElement;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.snake8bit.game.Assets.GameAsset;
import com.snake8bit.game.Assets.GameConstant;
import com.snake8bit.game.Assets.GameJson;
import com.badlogic.gdx.audio.Sound;
public class GamePlayScreenTetrisPlay {
    // Matrix
    public int[][] Tetrisgrid;
    public int[][] TetrisgridRotate;
    public int[][] MapGrid;
    public int[][] NewTetrisGrid;

    public boolean FirstPlay;
    //Positon
    public Vector2 TetrisPosition;
    public Vector2 TetrisPositionRotate;
    public Vector2 MatrixMove;
    //Limit Dir
    public boolean LimitRotate;
    public boolean LimitRotateLatch;
    public boolean LimitRun;
    public boolean LimitUp;     // trang thai gioi han huong di cua tank
    public boolean LimitDown;
    public boolean LimitLeft;
    public boolean LimitRight;
    public boolean inFloor;
    public boolean isDown;
    //Tetris level
    public int TetrisLine;
    public int TetrisLevel;
    //TetrisButtonTime
    public boolean TimeLeftLatch;
    public boolean TimeRightLatch;
    public float TimeLeft;
    public float TimeRight;
    public float TimeDown;

    public float DownSpeed;
    public float _DownSpeed;
    public float _ButtonDownSpeed;
    public float TimeDownStep;
    //Score
    public int SumScore;
    public int ScoreVal;
    public boolean isSumScore;
    //Score
    public int HI_SCORE;
    public int SCORE;
    public int LEVEL;
    public int MAX_SCORE;

    public float FloorTime;
    public GamePlayScreenTetrisPlay(){
        isDown = false;
        inFloor = false;
        LimitRotate = false;
        LimitRotateLatch = false;
        LimitUp = false;
        LimitDown = false;
        LimitLeft = false;
        LimitRight = false;
        FirstPlay = true;
        _DownSpeed=2.5f;
        TetrisLine=0;
        TetrisPositionRotate = new Vector2();
        TetrisPosition = new Vector2();
        MAX_SCORE=9999999;
        SCORE=0;
        LEVEL=0;
        MatrixMove=new Vector2();
        //HI_SCORE=GameJson.gameData.HiScore;
        InitMap();
        InitTetris();
    }
    public void InitMap(){
        int[][] _MapGrid = {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},// cot 0
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},// cot 1
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};// cot n
        MapGrid = new int[_MapGrid.length][_MapGrid[0].length];
        MapGrid = _MapGrid;
    }
    public void render(float deltatime, SpriteBatch batch){
        CheckCollision();
        CheckIsRotate();
        CheckButton(deltatime);
        GameGetMap();
        GameDraw(batch);
    }
    public void CheckCollision(){
        LimitUp=false;
        LimitDown=false;
        LimitLeft=false;
        LimitRight=false;
        for (int PosX=(int)TetrisPosition.x-1;PosX<TetrisPosition.x+Tetrisgrid.length+1;PosX++){
            if (PosX>=0&&PosX<MapGrid.length){
                for (int PosY=(int)TetrisPosition.y-1;PosY<TetrisPosition.y+Tetrisgrid[0].length+1;PosY++){
                    if (PosY>=0&&PosY<MapGrid[0].length){
                        if (MapGrid[PosX][PosY]!=0){
                            for (int x = 0; x < Tetrisgrid.length; x++) {    // truc x
                                for (int y = 0; y < Tetrisgrid[0].length; y++) {   // truc x
                                    if (Tetrisgrid[x][y] !=0) {
                                        if (TetrisPosition.x+x<3){
                                            TetrisPosition.x+=1.0f;
                                        }
                                        if (TetrisPosition.x+x>MapGrid.length-3){
                                            TetrisPosition.x-=1.0f;
                                        }
                                        if ((TetrisPosition.x+x)==(PosX)&&(TetrisPosition.y+y+1)==(PosY))
                                        {
                                            //LimitUp = true;
                                        }
                                        if ((TetrisPosition.x+x)==(PosX)&&(TetrisPosition.y+y-1)==(PosY))
                                        {
                                            LimitDown = true;
                                        }
                                        if ((TetrisPosition.x+x-1)==(PosX)&&(TetrisPosition.y+y)==(PosY))
                                        {
                                            LimitLeft = true;
                                        }
                                        if ((TetrisPosition.x+x+1)==(PosX)&&(TetrisPosition.y+y)==(PosY))
                                        {
                                            LimitRight = true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    public void CheckButton(float deltaTime){
        CheckRotate(deltaTime);
        CheckDown(deltaTime);
        if (!inFloor){
            CheckLeft(deltaTime);
            CheckRight(deltaTime);
        }
        CheckRun(deltaTime);
    }
    public void CheckRotate(float deltaTime){// nut up, cho xoay khoi tetris
        if (GamePlayScreenButton.isUpButton||GamePlayScreenButton.isFireButton){
            if (!LimitRotate){
                if (!LimitRotateLatch){
                    LimitRotateLatch=true;
                    TetrisPosition.x+=TetrisPositionRotate.x;
                    TetrisPosition.y+=TetrisPositionRotate.y;
                    Tetrisgrid=TetrisgridRotate;
                }
            }
        }
        else {
            LimitRotateLatch=false;
        }
    }
    public void CheckIsRotate(){
        if (GamePlayScreenButton.isUpButton||GamePlayScreenButton.isFireButton){    // khi co nut xoay duoc bam thi se kiem tra co the quay duoc hay khong
            TetrisPositionRotate.set(0.0f,0.0f);
            MatrixMove.set(0.0f,0.0f);
            TetrisgridRotate=Rotate(Tetrisgrid);    // tao mot khoi xoay
            LimitRotate=true;// ban dau cho gioi han xoay, neu vao truong hop xoay duoc thi se mac dinh chuyen sang false
            // dau tien kiem tra xem khoi xoay nay co va cham vao map khong
            boolean TMF = false;
            boolean LEFT_TMF = false;
            boolean RIGHT_TMF = false;
            boolean _LEFT_TMF = false;
            boolean _RIGHT_TMF = false;
            boolean UP_TMF = false;
            boolean DOWN_TMF = false;
            TMF=CheckMatrixCollision(TetrisgridRotate,MapGrid,TetrisPosition,MatrixMove);

            if (TMF){   // neu bi gioi han xoay
                // dich toa do sang cac phia roi kiem tra lai
                {// voi nhung khoi 3x3 thi can dich sang cac phia 1 don vi
                    //dich trai 1 don vi
                    MatrixMove.set(-1.0f,0.0f);
                    LEFT_TMF=CheckMatrixCollision(TetrisgridRotate,MapGrid,TetrisPosition,MatrixMove);
                    if (LEFT_TMF){
                        // neu dich sang trai ma khong duoc thi dich sang phai
                        MatrixMove.set(1.0f,0.0f);
                        RIGHT_TMF=CheckMatrixCollision(TetrisgridRotate,MapGrid,TetrisPosition,MatrixMove);
                        if (RIGHT_TMF){
                            //neu dich sang phai thi dich len
                            MatrixMove.set(0.0f,1.0f);
                            UP_TMF=CheckMatrixCollision(TetrisgridRotate,MapGrid,TetrisPosition,MatrixMove);
                            if (UP_TMF){
                                // neu dich len khong duoc thi dich xuong
                                MatrixMove.set(0.0f,-1.0f);
                                DOWN_TMF=CheckMatrixCollision(TetrisgridRotate,MapGrid,TetrisPosition,MatrixMove);
                                if (DOWN_TMF){

                                }
                                else {
                                    TetrisPositionRotate.set(0.0f,-1.0f);
                                    LimitRotate=false;
                                }
                            }
                            else {
                                TetrisPositionRotate.set(0.0f,1.0f);
                                LimitRotate=false;
                            }
                        }
                        else {
                            TetrisPositionRotate.set(1.0f,0.0f);
                            LimitRotate=false;
                        }
                    }
                    else {
                        TetrisPositionRotate.set(-1.0f,0.0f);
                        LimitRotate=false;
                    }
                }
                if (LimitRotate)// neu dich 1 don vi roi ma van bi gioi han thi dich 2 don vi
                {// voi nhung khoi 4x4 thi can dich sang cac phia 2 don vi
                    if (TetrisgridRotate.length>3){
                        MatrixMove.set(-2.0f,0.0f);
                        _LEFT_TMF=CheckMatrixCollision(TetrisgridRotate,MapGrid,TetrisPosition,MatrixMove);
                        if (_LEFT_TMF){

                            MatrixMove.set(2.0f,0.0f);
                            _RIGHT_TMF=CheckMatrixCollision(TetrisgridRotate,MapGrid,TetrisPosition,MatrixMove);

                            if (_RIGHT_TMF){

                            }
                            else{
                                TetrisPositionRotate.set(2.0f,0.0f);
                                LimitRotate=false;
                            }
                        }
                        else {
                            TetrisPositionRotate.set(-2.0f,0.0f);
                            LimitRotate=false;
                        }
                    }
                }
            }
            else {  // neu khong bi gioi han xoay
                LimitRotate=false;
            }
        }
    }
    public boolean CheckMatrixCollision(int[][] Matrix1,int[][] Matrix2,Vector2 MatrixPos, Vector2 MatrixMove){
        float PosX;
        float PosY;
        for (int x=0;x<Matrix1.length;x++){
            for (int y=0;y<Matrix1[0].length;y++){
                if (Matrix1[x][y]!=0){
                    PosX=(MatrixPos.x+x+MatrixMove.x);
                    PosY=(MatrixPos.y+y+MatrixMove.y);
                    if (PosX>0&&PosX<Matrix2.length){
                        if (PosY>0&&PosY<Matrix2[0].length){
                            if (Matrix2[(int)(MatrixPos.x+x+MatrixMove.x)][(int)(MatrixPos.y+y+MatrixMove.y)]!=0){
                                return true;
                            }
                        }
                        else {
                            return true;
                        }
                    }
                    else {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public void CheckDown(float deltaTime){// cho tang toc do dich chuyen xuong khoi tetris
        DownSpeed=_DownSpeed+ _ButtonDownSpeed+(float) LEVEL*0.3f;
        if (DownSpeed>15.0f){
            DownSpeed=15.0f;
        }
        TimeDown+=deltaTime;
        if (TimeDown>1.0f/DownSpeed){
            TimeDown=0.0f;
            TimeDownStep=1.0f;
        }
        if (!LimitDown){
            FloorTime=0.0f;
            if (TimeDownStep==1.0f){
                TimeDownStep=0.0f;
                TetrisPosition.y-=1.0f;
            }
            if (GamePlayScreenButton.isDownButton){
                _ButtonDownSpeed=5.0f;
            }
            else {
                _ButtonDownSpeed=0.0f;
            }
        }
        else {// khi den day thi sau 2 chu ky thi se gan khoi tetris vao trong map
            FloorTime+=deltaTime;
            if (FloorTime>0.25f){
                FloorTime=0.0f;
                inFloor=true;
            }
        }
    }
    public void GameGetMap(){
        if (inFloor){
            inFloor = false;
            EditMap();
            ClearMap();
            CheckGameOver();
            InitTetris();
        }
    }
    public void EditMap(){
        for (int x = 0; x < Tetrisgrid.length; x++) {
            for (int y = 0; y < Tetrisgrid[0].length; y++) {
                if (Tetrisgrid[x][y]!=0) {
                    MapGrid[(int)TetrisPosition.x+x][(int)TetrisPosition.y+y]=1;
                }
            }
        }
    }
    public void DeleteLine(int index) {
        for (int y = index; y<MapGrid[0].length-3; y++) {
            for (int x = 3; x < MapGrid.length-3; x++) {
                MapGrid[x][y] = MapGrid[x][y+1];
            }
        }
    }
    public void ClearMap() {
        SumScore=0;
        isSumScore=false;
        for (int y=3;y<MapGrid[0].length-3;y++){
            boolean LineFull=false;
            for (int x=3;x<MapGrid.length-3;x++){
                if (MapGrid[x][y]==0){
                    LineFull=true;
                    break;
                }
            }
            if (!LineFull){
                DeleteLine(y);
                y=y-1;
                SumScore+=1;
                TetrisLine++;
                isSumScore=true;
            }
        }
        if (isSumScore){
            isSumScore=false;
            SCORE+=GetScore(SumScore);

            LEVEL=GetLevel(TetrisLine);
            if (SCORE>MAX_SCORE){
                SCORE=MAX_SCORE;
            }
            if (SCORE>GameJson.gameData.HiScore){
                GameJson.gameData.HiScore=SCORE;
            }
            if (GameJson.gameData.isMusic){
                GameAsset.eatSound.play();
            }
            if (GameJson.gameData.isVibrate){
                Gdx.input.vibrate(80);
            }
        }
    }

    public int GetScore(int index){
        switch (index){
            case 1:
                ScoreVal=100;
                break;
            case 2:
                ScoreVal=300;
                break;
            case 3:
                ScoreVal=500;
                break;
            case 4:
                ScoreVal=800;
                break;
            default:
                ScoreVal=0;
                break;
        }
        return ScoreVal+LEVEL*100;
    }

    public int GetLevel(int index){
        return index/50;
    }

    public void CheckGameOver(){
        for (int x=3;x<MapGrid.length-3;x++){
            if (MapGrid[x][MapGrid[0].length-4]==1){
                GamePlayScreenTetris.gameState=2;
            }
        }
    }
    public void CheckLeft(float deltaTime){// cho dich sang trai khoi tetris
        if (!LimitLeft){
            if (GamePlayScreenButton.isLeftButton){// khi duoc bam nut lan dau tien thi se cho dich sang trai 1 don vi
                // sau mot khoang thoi gian bam nut thi se tang toc do dich len
                if (!TimeLeftLatch){
                    TimeLeftLatch=true;
                    TimeLeft=0.0f;
                    TetrisPosition.x-=1.0f;
                }
                else {
                    TimeLeft+=deltaTime;
                    if (TimeLeft>=1.0f/8.0f){
                        TimeLeft=0.0f;
                        TetrisPosition.x-=1.0f;
                    }
                }
            }
            else {
                TimeLeftLatch=false;
            }
        }
    }
    public void CheckRight(float deltaTime){// cho dich sang phai khoi tetris
        if (!LimitRight){
            if (GamePlayScreenButton.isRightButton){// khi duoc bam nut lan dau tien thi se cho dich sang trai 1 don vi
                // sau mot khoang thoi gian bam nut thi se tang toc do dich len
                if (!TimeRightLatch){
                    TimeRightLatch=true;
                    TimeRight=0.0f;
                    TetrisPosition.x+=1.0f;
                }
                else {
                    TimeRight+=deltaTime;
                    if (TimeRight>=1.0f/8.0f){
                        TimeRight=0.0f;
                        TetrisPosition.x+=1.0f;
                    }
                }
            }
            else {
                TimeRightLatch=false;
            }
        }
    }
    public void CheckRun(float deltaTime){

        MatrixMove = new Vector2(0.0f,0.0f);
        for (int y=0;y<(int)(TetrisPosition.y);y++){
            MatrixMove.y=-y;
            if (CheckMatrixCollision(Tetrisgrid,MapGrid,TetrisPosition,MatrixMove)){
                y=MapGrid[0].length;
            }
        }
        if (!LimitDown)
            if (GamePlayScreenButton.isRunButton){
                if (!LimitRun){
                    if (TetrisPosition.y<MapGrid[0].length-3){
                        LimitRun=true;
                        TetrisPosition.y+=MatrixMove.y+1;
                    }
                }
            }
            else {
                LimitRun=false;
            }
    }
    public void InitTetris(){
        TetrisPosition = new Vector2((MapGrid.length-1)/2,MapGrid[0].length-5);

        if (FirstPlay){
            TetrisLevel=MathUtils.random(0,6);
            GetTetris(TetrisLevel);

            Tetrisgrid=new int[NewTetrisGrid.length][NewTetrisGrid[0].length];
            Tetrisgrid=NewTetrisGrid;

            TetrisLevel=MathUtils.random(0,6);
            GetTetris(TetrisLevel);

            FirstPlay=false;
        }
        else {
            Tetrisgrid=new int[NewTetrisGrid.length][NewTetrisGrid[0].length];
            Tetrisgrid=NewTetrisGrid;

            TetrisLevel=MathUtils.random(0,6);
            GetTetris(TetrisLevel);
        }
    }
    public void GetTetris(int index){
        switch (index)
        {
            case 0:
                int[][] TetrisTypeI = {{0,0,0,0},{1,1,1,1},{0,0,0,0},{0,0,0,0}};
                NewTetrisGrid = new int[TetrisTypeI.length][TetrisTypeI[0].length];
                NewTetrisGrid = TetrisTypeI;
                break;
            case 1:
                int[][] TetrisTypeJ = {{0,0,1},{1,1,1},{0,0,0}};
                NewTetrisGrid = new int[TetrisTypeJ.length][TetrisTypeJ[0].length];
                NewTetrisGrid = TetrisTypeJ;
                break;
            case 2:
                int[][] TetrisTypeL = {{1,0,0},{1,1,1},{0,0,0}};
                NewTetrisGrid = new int[TetrisTypeL.length][TetrisTypeL[0].length];
                NewTetrisGrid = TetrisTypeL;
                break;
            case 3:
                int[][] TetrisTypeO = {{1,1},{1,1}};
                NewTetrisGrid = new int[TetrisTypeO.length][TetrisTypeO[0].length];
                NewTetrisGrid = TetrisTypeO;
                break;
            case 4:
                int[][] TetrisTypeS = {{1,1,0},{0,1,1},{0,0,0}};
                NewTetrisGrid = new int[TetrisTypeS.length][TetrisTypeS[0].length];
                NewTetrisGrid = TetrisTypeS;
                break;
            case 5:
                int[][] TetrisTypeT = {{0,1,0},{1,1,1},{0,0,0}};
                NewTetrisGrid = new int[TetrisTypeT.length][TetrisTypeT[0].length];
                NewTetrisGrid = TetrisTypeT;
                break;
            case 6:
                int[][] TetrisTypeZ = {{0,1,1},{1,1,0},{0,0,0}};
                NewTetrisGrid = new int[TetrisTypeZ.length][TetrisTypeZ[0].length];
                NewTetrisGrid = TetrisTypeZ;
                break;
        }
    }

    public int[][] Rotate(int[][] index){
        int[][] index1 = new int[index.length][index[0].length];
        for (int j=0;j<index[0].length;j++){
            for (int i=0;i<index.length;i++){
                index1[i][index[0].length-j-1]=index[j][i];
            }
        }
        return index1;
    }
    public void GameDraw(SpriteBatch batch){
        GameJson.gameData.Score=SCORE;
        GameJson.gameData.Level=LEVEL;
        batch.begin();
        for (int x=0;x<Tetrisgrid.length;x++){
            for (int y=0;y<Tetrisgrid[0].length;y++){
                if (GamePlayScreenTetris.gameState!=3){
                    if (TetrisPosition.y+y>=16&&TetrisPosition.y+y<20){
                        continue;
                    }
                }

                if (Tetrisgrid[x][y]!=0){
                    CalignPosition(batch, GameAsset.BrickSprite72,TetrisPosition.x+x,TetrisPosition.y+y, GameConstant.MAIN_GAME_GRID_OFFSET,1.0f);
                }
            }
        }
        for (int x=0;x<Tetrisgrid.length;x++){
            for (int y=0;y<Tetrisgrid[0].length;y++){
                if (GamePlayScreenTetris.gameState!=3){
                    if (TetrisPosition.y+MatrixMove.y+y+1>=16&&TetrisPosition.y+MatrixMove.y+y+1<20){
                        continue;
                    }
                }
                if (Tetrisgrid[x][y]!=0){
                    CalignPosition(batch, GameAsset.BrickSprite72,TetrisPosition.x+x,TetrisPosition.y+MatrixMove.y+y+1, GameConstant.MAIN_GAME_GRID_OFFSET,0.2f);
                }
            }
        }

        for (int x=0;x<NewTetrisGrid.length;x++){
            for (int y=0;y<NewTetrisGrid[0].length;y++){
                if (NewTetrisGrid[x][y]!=0){
                    CalignPosition(batch, GameAsset.BrickSprite48,x+3,y+3, GameConstant.SUB_GAME_GRID_OFFSET,1.0f);
                }
            }
        }
        for (int x = 3; x < MapGrid.length-3; x++) {//MapGrid.length so cot trong mang, voi ma tran [18][27] thi se tra ve 18, ung voi so luong truc x
            for (int y = 3; y < MapGrid[0].length-4; y++) {// MapGrid[0].length so phan tu trong mang thu nhat, voi ma tran [18][27] thi se tra ve 27, ung voi so luong truc y
                if (GamePlayScreenTetris.gameState!=3){
                    if (y>=16&&y<20){
                        continue;
                    }
                }
                if (MapGrid[x][y]!=0) {
                    CalignPosition(batch, GameAsset.BrickSprite72,x,y, GameConstant.MAIN_GAME_GRID_OFFSET,1.0f);
                }
            }
        }
        batch.end();
    }
    public void CalignPosition(SpriteBatch batch, Sprite sprite, float x, float y, Vector2 offsetVal, float transparent){
        sprite.setPosition(offsetVal.x+sprite.getHeight()*(x-3),offsetVal.y+sprite.getHeight()*(y-3));
        sprite.draw(batch,transparent);
    }
}
