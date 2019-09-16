package com.example.firstgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainPage extends AppCompatActivity implements View.OnClickListener {

    Button buttonGame, buttonHelp, buttonScore;

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_options_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.game:
                startActivity(new Intent(this, MainActivity.class));
                return true;
            case R.id.help:
                startActivity(new Intent(this, Help.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

        //respond to menu item selection

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonGame = (Button) findViewById(R.id.button_game);
        buttonGame.setOnClickListener(this);
        buttonHelp = (Button) findViewById(R.id.button_help);
        buttonHelp.setOnClickListener(this);
        buttonScore = (Button) findViewById(R.id.button_score);
        buttonScore.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.button_game:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.button_help:
                startActivity(new Intent(this, Help.class));
                break;
            case R.id.button_score:
                startActivity(new Intent(this, Score.class));
                break;
            default:
                break;
        }

    }
}
