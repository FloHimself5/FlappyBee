package com.example.firstgame;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Score extends Activity implements View.OnClickListener{

    Button buttonAdd, buttonShow;
    myDbAdapter helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        helper = new myDbAdapter(this);
        buttonAdd = (Button) findViewById(R.id.button_add);
        buttonAdd.setOnClickListener(this);
        buttonShow = (Button) findViewById(R.id.button_show);
        buttonShow.setOnClickListener(this);
    }

    public void addUser(View view)
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
        String score = "1";
        long id = helper.insertData(name,date,score);

    }

    public void viewdata(View view)
    {
        String data = helper.getData();
        Message.message(this,data);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.button_add:
                addUser(v);
               // Message.message(getApplicationContext(),"Enter Both Name and Password");
                break;
            case R.id.button_show:
                viewdata(v);
                break;

            default:
                break;
        }
    }
}
