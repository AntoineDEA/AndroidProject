package com.example.finalproject.vue;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalproject.R;

public class MainActivity extends AppCompatActivity {

    CalendarView calender;
    TextView date_view;
    Button buttonEvent;

    String[] names = {"lkl","jhbvjklh","iuhlihlh","liuhoiuh"};
    ArrayAdapter<String> adapter;
    @Override


    protected void onCreate(Bundle savedInstanceState)
    {
        final String[] test = {""};
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calender = (CalendarView)
                findViewById(R.id.calender);
        date_view = (TextView)
                findViewById(R.id.date_view);
        calender
                .setOnDateChangeListener(
                        new CalendarView
                                .OnDateChangeListener() {
                            @Override
                            public void onSelectedDayChange(
                                    CalendarView view,
                                    int year,
                                    int month,
                                    int dayOfMonth)
                            {
                                String Date
                                        = dayOfMonth + "-"
                                        + (month + 1) + "-" + year;
                                date_view.setText(Date);
                                test[0] = Date;
                            }
                        });

        buttonEvent = (Button) findViewById(R.id.button3);
        buttonEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SecondActivity.class);
                if(test[0] == ""){
                    Toast.makeText(MainActivity.this, "Veuillez sélectionner une date", Toast.LENGTH_SHORT).show();
                }else {
                    intent.putExtra("key", "" + test[0] + "");
                    startActivity(intent);
                }
            }
        });

        buttonEvent = (Button) findViewById(R.id.button2);
        buttonEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ThirdActivity.class);
                if(test[0] == ""){
                    Toast.makeText(MainActivity.this, "Veuillez sélectionner une date", Toast.LENGTH_SHORT).show();
                }else {
                    intent.putExtra("key", "" + test[0] + "");
                    startActivity(intent);
                }
            }
        });

        buttonEvent = (Button) findViewById(R.id.button);
        buttonEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), FourthActivity.class);
                startActivity(intent);
                Log.d("CREATION", "!!!!!!!!!!!!!!!!!!!!!!!!!!!Je suis passé ici!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            }
        });

    }

    /*public void open_dialog(View v){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        View row = getLayoutInflater().inflate(R.layout.row_item,null);
        ListView list = (ListView)row.findViewById(R.id.listView);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,names);

        list.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        alertDialog.setView(row);
        AlertDialog dialog = alertDialog.create();
        dialog.show();

    }

     */
}