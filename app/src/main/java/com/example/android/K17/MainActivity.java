package com.example.android.k17;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {


    Calendar calendar=new GregorianCalendar();
    Spinner spinner;
    public final static String A = "com.example.android.kurukshetra.NAME";
    public final static String B = "com.example.android.kurukshetra.DOB";
    public final static String C = "com.example.android.kurukshetra.DEPT";
    public final static String D = "com.example.android.kurukshetra.YEAR";
    String msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         final Button reset=(Button) findViewById(R.id.reset);
        int a;

        spinner = (Spinner) findViewById(R.id.year);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.year, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                msg=(String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        reset.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (v==reset) {
                    startActivity(new Intent(MainActivity.this, MainActivity.class));
                }
            }
        });
    }


    private int  validate(String registerdate) {

        String regEx ="^(0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])[- /.](19|20)\\d{2}$";
        Matcher matcherObj = Pattern.compile(regEx).matcher(registerdate);
        if (matcherObj.matches())
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }


    public void Submit(View view) {
        Intent intent = new Intent(this, com.example.android.k17.DisplayMessageActivity.class);

        EditText a= (EditText) findViewById(R.id.name);
        EditText b= (EditText) findViewById(R.id.Dob);
        EditText c= (EditText) findViewById(R.id.Dept);




        String name=a.getText().toString();
        String dob=b.getText().toString();
        String dept=c.getText().toString();


        if (name.matches("")) {
            Toast.makeText(this, "You did not enter a username", Toast.LENGTH_SHORT).show();
            return;
        }
        if (dob.matches("")) {
            Toast.makeText(this, "You did not enter your DOB", Toast.LENGTH_SHORT).show();
            return;
        }
        if (dept.matches("")) {
            Toast.makeText(this, "You did not enter your department", Toast.LENGTH_SHORT).show();
            return;
        }
        int i=validate(dob);




            String[] y = dob.split("/");
            int ye, mo, da, year, day, month;
            if (i == 1) {
                ye = Integer.parseInt(y[2]);
                mo = Integer.parseInt(y[0]);
                da = Integer.parseInt(y[1]);
                year = calendar.get(Calendar.YEAR);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                month = calendar.get(Calendar.MONTH) + 1;

                if (ye > year || (ye == year && mo > month) || (ye == year && mo == month && da > day)) {
                    Toast.makeText(getApplicationContext(), "Invalid Birthday!", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        

else{
            Toast.makeText(getApplicationContext(), "Invalid Format!", Toast.LENGTH_SHORT).show();
            return;
        }

        intent.putExtra(A,name);
        intent.putExtra(B,dob);
        intent.putExtra(C,dept);
        intent.putExtra(D,msg);


        startActivity(intent);


    }




}

