package com.example.finalproject.vue;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalproject.R;
import com.example.finalproject.controleur.Controle;
import com.example.finalproject.modele.AccesLocal;
import com.example.finalproject.modele.Event;

import java.util.ArrayList;
import java.util.List;

public class FourthActivity extends AppCompatActivity {

    private ListView maListe;
    List<Event> maListeEvent = new ArrayList<Event>();
    List<Event> display = new ArrayList<Event>();
    private Controle controle;
    private Context context;
    private AccesLocal acces = new AccesLocal(context);

    private List<Event> getAll(){


        Log.d("CREATION", "!!!!!!!!!!!!!!!!!!!!!!!!!!!2222222222222222222222222222222222222222222222222!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        maListeEvent = acces.getAllElements();

        return maListeEvent;


    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activiry_fourth);

        Log.d("CREATION", "!!!!!!!!!!!!!!!!!!!!!!!!!!!11111111111111111111111111111111111111111!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        display = getAll();
        maListe = (ListView) findViewById(R.id.list);
        ArrayAdapter<Event> arrayAdapter = new ArrayAdapter<Event>(this, android.R.layout.simple_list_item_1, maListeEvent);
        maListe.setAdapter(arrayAdapter);

        Log.d("CREATION", "!!!!!!!!!!!!!!!!!!!!!!!!!!!444444444444444444444444444444444444!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

    }


}
