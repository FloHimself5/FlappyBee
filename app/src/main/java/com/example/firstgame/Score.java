package com.example.firstgame;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Score extends Activity implements View.OnClickListener{

    Button buttonAdd, buttonShow, buttonDelete;
    ImageButton  buttonMain, buttonGame;
    myDbAdapter helper;

    int score;
    long highscoreID;
    int highscore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        helper = new myDbAdapter(this);

        buttonMain = findViewById(R.id.button_To_Main);
        buttonMain.setOnClickListener(this);
        buttonGame = findViewById(R.id.button_To_Game);
        buttonGame.setOnClickListener(this);
/*
        buttonAdd = (Button) findViewById(R.id.button_add);
        buttonAdd.setOnClickListener(this);
        buttonShow = (Button) findViewById(R.id.button_show);
        buttonShow.setOnClickListener(this);
        buttonDelete = (Button) findViewById(R.id.button_delete);
        buttonDelete.setOnClickListener(this);
*/
        score =   getIntent().getIntExtra("score", -1);
        System.out.println("highscoreID: " + highscoreID);
        helper.insertFirst();
        System.out.println("HIGH: " + helper.getHighscore());
        highscore = helper.getHighscore();
        highscoreID = helper.getHighscoreId();
        if(score != -1) {
            if (highscore < score) {
                System.out.println("------------------------------------");
                highscoreID = addEntry(score);
                highscore = score;
                System.out.println("NEW HIGHSCORE: " + highscoreID + " " + helper.getNameById(highscoreID) + " " + helper.getScoreById(highscoreID) + " " + helper.getDateById(highscoreID));
            }
        }
       // viewdata();
       // delete();
        refresh();
    }


    public long addEntry(int score)
    {
        Date datum = new Date();
        Date time = datum;
        SimpleDateFormat formatDay = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatHour = new SimpleDateFormat("HH");
        SimpleDateFormat formatMinute = new SimpleDateFormat("mm");

        String stringDatum = formatDay.format(datum);
        String stringHour = formatHour.format(time);
        String stringMinute = formatMinute.format(time);
        String name = " ";
        String date = stringHour + ":" + stringMinute + " - " + stringDatum;
        String scoreSring = Integer.toString(score);
        long id = helper.insertData(name,date,scoreSring);
        refresh();
        return id;
    }

    public void delete()
    {
        helper.delete();
        Message.message(this, "Deleted all Data");
        refresh();
    }

    public void refresh(){
        TextView tName = (TextView) findViewById(R.id.name);
        TextView tScore = (TextView) findViewById(R.id.score);
        TextView tDate = (TextView) findViewById(R.id.date);
        tName.setText(helper.getName());
        tScore.setText(helper.getScore());
        tDate.setText(helper.getDate());
    }

    public void viewdata()
    {
        String data = helper.getData();
        Toast.makeText(getApplicationContext(),data, Toast.LENGTH_SHORT).show();
        Message.message(this, data);
        refresh();
    }

    @Override
    public void onClick(View v) {
        MediaPlayer mp = MediaPlayer.create(this, R.raw.clack);

        switch (v.getId()) {

            case R.id.button_To_Main:
                startActivity(new Intent(this, MainPage.class));
                mp.start();
                break;
            case R.id.button_To_Game:
                startActivity(new Intent(this, MainActivity.class));
                mp.start();
                break;
            default:
                break;
        }
    }
}
