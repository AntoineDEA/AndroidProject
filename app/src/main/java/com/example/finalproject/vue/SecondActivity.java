package com.example.finalproject.vue;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.service.controls.Control;
import android.telephony.SmsManager;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.finalproject.R;
import com.example.finalproject.controleur.Controle;
import com.example.finalproject.modele.AccesLocal;
import com.example.finalproject.modele.Event;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static java.lang.Integer.parseInt;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textView = (TextView) findViewById(R.id.textView);
        TextView textView1 = (TextView) findViewById(R.id.textView3);
        TextView textView2 = (TextView) findViewById(R.id.textView4);
        TextView textView3 = (TextView) findViewById(R.id.textView8);
        Intent intent = getIntent();
        final String value = intent.getStringExtra("key");
        textView.setText("Ajout d'un événement à la date du :" + value);

        List<String> intList = new ArrayList<>( Arrays.asList(value.split("-")));

        textView1.setText(intList.get(0));
        textView2.setText(intList.get(1));
        textView3.setText(intList.get(2));

        init();
        recupProfil();
    }

    private EditText txtLibelle;
    private EditText txtJour;
    private EditText txtMois;
    private EditText txtAnnee;
    private EditText txtHeure;
    private EditText txtMinute;
    private EditText txtHeure2;
    private EditText txtMinute2;
    private TextView textView;
    private Controle controle;
    private Context context;
    private AccesLocal acces = new AccesLocal(context);
    private String phone;

    private void init(){
        txtLibelle = (EditText)findViewById(R.id.editTextTextPersonName);
        txtHeure = (EditText)findViewById(R.id.editTextNumber5);
        txtMinute = (EditText)findViewById(R.id.editTextNumber6);
        txtHeure2 = (EditText)findViewById(R.id.editTextNumber7);
        txtMinute2 = (EditText)findViewById(R.id.editTextNumber8);
        this.controle = Controle.getInstance(this);
        ecouteCalcul();
        recupProfil();


    }

    private void ecouteCalcul(){

        ((Button) findViewById(R.id.button4)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Toast.makeText(SecondActivity.this, "Ajout réussis !", Toast.LENGTH_SHORT).show();

                String libelle = "";
                Integer jour =0;
                Integer mois =0;
                Integer annee =0;
                Integer heure =0;
                Integer minute =0;
                Integer heure2 =0;
                Integer minute2 =0;

                try {

                    TextView textView1 = (TextView) findViewById(R.id.textView3);
                    TextView textView2 = (TextView) findViewById(R.id.textView4);
                    TextView textView3 = (TextView) findViewById(R.id.textView8);

                    libelle = txtLibelle.getText().toString();
                    jour = Integer.valueOf(textView1.getText().toString());
                    mois = Integer.valueOf(textView2.getText().toString());
                    annee = Integer.valueOf(textView3.getText().toString());
                    heure = parseInt(txtHeure.getText().toString());
                    minute = parseInt(txtMinute.getText().toString());
                    heure2 = parseInt(txtHeure2.getText().toString());
                    minute2 = parseInt(txtMinute2.getText().toString());

                } catch (Exception e){};

                if(jour == 0 || mois == 0){
                    Toast.makeText(SecondActivity.this, "Saisie incorrecte", Toast.LENGTH_SHORT).show();
                }else{
                    boolean valid = checkResult(libelle, jour, mois, annee, heure, minute, heure2, minute2);
                    if(valid == true){
                        Log.d("CREATION", "!!!!!!!!!!!!!!!!!!!!!!!!!!!PAS BON!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                        Toast.makeText(SecondActivity.this, "Un événement à déja lieux sur ce crénaux", Toast.LENGTH_SHORT).show();
                    }else{
                        Log.d("CREATION", "!!!!!!!!!!!!!!!!!!!!!!!!!!!BON!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                        afficheResult(libelle, jour, mois, annee, heure, minute, heure2, minute2);
                    }
                }

            }
        });

    }

    private void afficheResult(String libelle, Integer jour, Integer mois, Integer annee, Integer heure, Integer minute, Integer heure2, Integer minute2){
        Toast.makeText(SecondActivity.this, "Ajout réussis", Toast.LENGTH_SHORT).show();
        phone="0662594937";
        String message = "Création de l'événement " + libelle + " la date du : " + jour + ":" + mois + ":" + annee + " , de" + heure + ":" + minute + " à " + heure2 + ":" + minute2 + ".";
        SmsManager.getDefault().sendTextMessage(phone,null , message, null , null );
        this.controle.creerEvent(libelle,  jour,  mois,  annee,  heure,  minute, heure2,  minute2, this);
    }

    private boolean checkResult(String libelle, Integer jour, Integer mois, Integer annee, Integer heure, Integer minute, Integer heure2, Integer minute2){

        boolean valid = false;

        Event temp = new Event(new Date(), libelle,  jour,  mois,  annee,  heure,  minute, heure2,  minute2);
        this.acces.recupDernierSpe(temp);

        return valid;

    }

    private void recupProfil(){

        if(controle.getAnnee() != null){

            txtLibelle.setText(controle.getLibelle());
            txtJour.setText(controle.getJour().toString());
            txtMois.setText(controle.getMois().toString());
            txtAnnee.setText(controle.getAnnee().toString());
            txtHeure.setText(controle.getHeure().toString());
            txtMinute.setText(controle.getMinute().toString());
            ((Button)findViewById(R.id.button4)).performClick();

        }

    }

}
