package com.example.firstgame;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class BotPillar {
    private int left, top, right, bot;
    private double vel = 5;

    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    private boolean paused = false;

    private int width = 200;
    private int speedup = 1;

    public BotPillar(int top, int left){
      this.left = left;
      this.top = top;
      right = left + width;
      bot = screenHeight;
    }

    public BotPillar(int top){
        left = screenWidth;
        this.top = top;
        right = left + width;
        bot = screenHeight;
    }

    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Color.rgb(0,255,0));
        paint.setStrokeWidth(10);
        canvas.drawRect(left, top, right, bot,paint);
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

    public boolean hit(CharacterSprite bird) {
        //System.out.println(bird.getImageWidth());
        if((bird.getY() + bird.getImageHeight()) > top){
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
