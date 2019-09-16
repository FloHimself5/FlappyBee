package com.example.firstgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class Score extends Activity implements View.OnClickListener{

    Button buttonAdd, buttonShow;
    EditText Name, Date , Score;
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
        String t1 = "hnrfuhesfhbj";
        String t2 = "515151";
        String t3 = "LOL";
        long id = helper.insertData(t1,t2,t3);

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
