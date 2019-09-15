package com.example.firstgame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private GameThread thread;
    private CharacterSprite characterSprite;

    private Wall wall1;
    private Wall wall2;


    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    private int distance = screenWidth/3;

    private int grav = 1;

    private boolean gameOver = false;

    public GameView(Context context) {
        super(context);
        getHolder().addCallback(this);
        thread = new GameThread(getHolder(), this);
        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        allNew();
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

            wall1.draw(canvas,paint);
            wall2.draw(canvas,paint);

            characterSprite.addScore(wall1.getPoint(characterSprite));
            characterSprite.addScore(wall2.getPoint(characterSprite));
            // System.out.println(characterSprite.getScore());

            paint.setColor(Color.rgb(0,0,0));
            paint.setTextSize(50);
            canvas.drawText("Score:  " + characterSprite.getScore(), 10, screenHeight - 60, paint);

            characterSprite.update();
            characterSprite.draw(canvas);

            if(wall1.hit(characterSprite) || wall2.hit(characterSprite) || characterSprite.fell()){
                characterSprite.kill();
                wall1.end();
                wall2.end();

                paint.setTextSize(70);
                paint.setColor(Color.rgb(255,0,0));
                canvas.drawText("GAME OVER", screenWidth/2 - 200, screenHeight/2, paint);


            }
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
        gameOver = false;
        characterSprite = new CharacterSprite(BitmapFactory.decodeResource(getResources(), R.drawable.bee));
        wall1 = new Wall();
        wall2 = new Wall(screenWidth/2);

    }

    public void update() {
    }
}
