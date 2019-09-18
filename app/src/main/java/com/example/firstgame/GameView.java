package com.example.firstgame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Date;


class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private GameThread thread;
    private CharacterSprite characterSprite;

    private Wall wall1;
    private Wall wall2;


    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    private int distance = screenWidth/3;

    private Context myContext;
    private int speedup = 1;

    private boolean wentToScore = false;
    private MainActivity mainActivity;

    public GameView(Context context, MainActivity mainActivity) {
        super(context);
        myContext = context;
        this.mainActivity = mainActivity;
        getHolder().addCallback(this);

        setFocusable(true);

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        allNew();
        thread = new GameThread(getHolder(), this);
        thread.setRunning(true);
        thread.start();

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (canvas != null) {
            canvas.drawColor(Color.WHITE);
            Paint paint = new Paint();

            if(wall1.check()){
                if((screenWidth - wall2.getRight())> distance) {
                    wall1 = new Wall();
                }else {
                    wall1 = new Wall(distance - (Math.abs(screenWidth - wall2.getRight())));
                }
            }
            if(wall2.check()){
                if((screenWidth - wall1.getRight())> distance) {
                    wall2 = new Wall();
                }else {
                    wall2 = new Wall(distance - (Math.abs(screenWidth - wall1.getRight())));
                }
            }

            wall1.update();
            wall2.update();



            characterSprite.addScore(wall1.getPoint(characterSprite));
            characterSprite.addScore(wall2.getPoint(characterSprite));


            if(characterSprite.getScore() != 0 && characterSprite.getScore() % 10 == 0){
                speedup = characterSprite.getScore() / 10;
                wall1.speedup(speedup);
                wall2.speedup(speedup);
            }

            characterSprite.update();
            characterSprite.draw(canvas);

            wall1.draw(canvas,paint);
            wall2.draw(canvas,paint);

            if(wall1.hit(characterSprite) || wall2.hit(characterSprite) || characterSprite.fell()){
                characterSprite.kill();
                wall1.end();
                wall2.end();

                paint.setTextSize(70);
                paint.setColor(Color.rgb(255,0,0));
                canvas.drawText("GAME OVER", screenWidth/2 - 200, screenHeight/2, paint);



                if(wentToScore == false) {
                    thread.setRunning(false);
                    System.out.println(thread.getState());
                    mainActivity.goToScore(characterSprite.getScore());
                    wentToScore = true;
                }

            }

            paint.setColor(Color.rgb(0,0,0));
            paint.setTextSize(50);
            canvas.drawText("Score:  " + characterSprite.getScore(), 10, screenHeight - 60, paint);
         }
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            characterSprite.press();
            restart();
            return true;
        }
        return false;
    }

    public void restart() {
        if(!characterSprite.isAlive()){

            allNew();
        }
    }

    public void allNew(){
        wentToScore = false;
        characterSprite = new CharacterSprite(BitmapFactory.decodeResource(getResources(), R.drawable.bee));
        wall1 = new Wall();
        wall2 = new Wall(screenWidth/2);

    }

    public void unpause(){
        characterSprite.unpause();
        wall1.unpause();
        wall2.unpause();
    }

    public void pause() {
        characterSprite.pause();
        wall1.pause();
        wall2.pause();
    }

}

