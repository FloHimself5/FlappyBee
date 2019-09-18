package com.example.firstgame;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.widget.TextView;
import android.widget.Toast;

public class Score extends Activity implements View.OnClickListener{

    Button buttonAdd, buttonShow, buttonDelete;
    myDbAdapter helper;

    int score;
    long highscoreID;
    int highscore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        helper = new myDbAdapter(this);
        buttonAdd = (Button) findViewById(R.id.button_add);
        buttonAdd.setOnClickListener(this);
        buttonShow = (Button) findViewById(R.id.button_show);
        buttonShow.setOnClickListener(this);
        buttonDelete = (Button) findViewById(R.id.button_delete);
        buttonDelete.setOnClickListener(this);
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
        String name = "Bob";
        String date = stringHour + ":" + stringMinute + " - " + stringDatum;
        String scoreSring = Integer.toString(score);
        long id = helper.insertData(name,date,scoreSring);
        refresh();
        return id;
    }

    public void delete( View view)
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

    public void viewdata(View view)
    {
        String data = helper.getData();
        Toast.makeText(getApplicationContext(),data, Toast.LENGTH_SHORT).show();
        Message.message(this, data);
        refresh();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.button_add:
                addEntry(5);
               // Message.message(getApplicationContext(),"Enter Both Name and Password");
                break;
            case R.id.button_show:
                viewdata(v);
                break;
            case R.id.button_delete:
               delete(v);
                break;

            default:
                break;
        }
    }
}
