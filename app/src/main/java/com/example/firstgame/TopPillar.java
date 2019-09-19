package com.example.firstgame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class TopPillar {
    private int left, top, right, bot;
    private double vel = 5;

    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    private boolean gotPoint = false;
    private boolean paused = false;

    private int speedup = 1;

    private Bitmap bgImage;
    private Bitmap smImage;
    private  Resources resources;

    private int width = 250;

    public TopPillar(int bot, int left, Resources resources){
        this.left = left;
        right = left + width;
        this.bot = bot;
        top = bot -screenHeight;

        this.resources = resources;
        bgImage = BitmapFactory.decodeResource(resources, R.drawable.wall_final);
        smImage = Bitmap.createScaledBitmap(bgImage,width,screenHeight,true);
    }

    public TopPillar(int bot, Resources resources){
        left = screenWidth;
        right = left + width;
        this.bot = bot;
        top = bot -screenHeight;

        this.resources = resources;
        bgImage = BitmapFactory.decodeResource(resources, R.drawable.wall_final);
        smImage = Bitmap.createScaledBitmap(bgImage,width,screenHeight,true);
    }

    public boolean passed(CharacterSprite bird) {
        if(gotPoint == false && bird.getX() + bird.getImageWidth() >= left ){
            //  System.out.println("Check1");
            if((bird.getX()) <= right)
                // System.out.println("Check2");
                gotPoint = true;
                return true;
            }
        return false;
    }

    public void draw(Canvas canvas, Paint paint) {
        canvas.drawBitmap(smImage,left, (int)top, paint);
       // paint.setColor(Color.rgb(0,255,0));
       // paint.setStrokeWidth(10);
        //canvas.drawRect(left, top, right, bot,paint);
    }

    public void update() {
        if(!paused) {
            left -= vel * speedup;
            right -= vel * speedup;
        }
    }

    public boolean check() {
        if(right <= 0){
            return true;
        }
        else return false;
    }

    public int getRight() {
        return right;
    }

    public boolean hit(CharacterSprite bird) {
        //System.out.println(bird.getImageWidth());
        if(bird.getY() < bot){

            if(bird.getX() + bird.getImageWidth() >= left ){
              //  System.out.println("Check1");
                    if((bird.getX()) <= right) {
                       // System.out.println("Check2");
                        return true;
                    }
            }
        }
        return false;
    }

    public void pause() {
        paused = true;
    }

    public void unpause() {
        paused = false;
    }

    public void speedup(int speedup) {
        this.speedup = speedup;
    }
}
