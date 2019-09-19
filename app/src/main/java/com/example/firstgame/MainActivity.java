package com.example.firstgame;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

public class MainActivity extends Activity implements View.OnClickListener {

    private FrameLayout game;
    private GameView gameView;
    private LinearLayout gameWidgets;
    private boolean paused = false;
    private ImageButton pauseButton;
    private RelativeLayout gameWidgetRel;
    private boolean stopped = false;


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        game = new FrameLayout(this);
        gameView = new GameView (this, this);
        gameWidgets = new LinearLayout (this);
        gameWidgetRel = new RelativeLayout(this);

        pauseButton = new ImageButton(this);
        pauseButton.setImageResource(R.drawable.button_pause_new);
        pauseButton.setBackground(null);

        gameWidgets.addView(pauseButton);
        gameWidgetRel.addView(gameView);
        gameWidgetRel.addView(gameWidgets);

        gameWidgetRel.setHorizontalGravity(Gravity.RIGHT);

        setContentView(gameWidgetRel);
        pauseButton.setOnClickListener(this);


    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onResume() {
        super.onResume();

        game = new FrameLayout(this);
        gameView = new GameView (this, this);
        gameWidgets = new LinearLayout (this);
        gameWidgetRel = new RelativeLayout(this);

        pauseButton = new ImageButton(this);
        pauseButton.setImageResource(R.drawable.button_pause_new);
        pauseButton.setBackground(null);

        gameWidgets.addView(pauseButton);
        gameWidgetRel.addView(gameView);
        gameWidgetRel.addView(gameWidgets);

        gameWidgetRel.setHorizontalGravity(Gravity.RIGHT);

        setContentView(gameWidgetRel);
        pauseButton.setOnClickListener(this);


    }


    public void goToScore(int score){
        Intent intent = new Intent().setClass(this, Score.class);
        intent.putExtra("score", score);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        MediaPlayer mp = MediaPlayer.create(this, R.raw.clack);
        mp.start();
        if(!paused) {
            gameView.pause();
            paused = true;
        }else {
            gameView.unpause();
            paused = false;
        }
    }
}
