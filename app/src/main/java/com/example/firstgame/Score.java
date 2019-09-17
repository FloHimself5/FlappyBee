package com.example.firstgame;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Score extends Activity implements View.OnClickListener{

    Button buttonAdd, buttonShow, buttonDelete;
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
        buttonDelete = (Button) findViewById(R.id.button_delete);
        buttonDelete.setOnClickListener(this);
        //int score;
        addEntry(getIntent().getIntExtra("score", 0));
    }


    public void addEntry(int score)
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
    }

    public void delete( View view)
    {
        helper.delete();
        Message.message(this, "Deleted all Data");
    }

    public void viewdata(View view)
    {
        String data = helper.getData();
        Message.message(this, data);
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
