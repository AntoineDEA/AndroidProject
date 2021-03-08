package com.example.finalproject.vue;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalproject.R;
import com.example.finalproject.controleur.Controle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Integer.parseInt;

public class ThirdActivity extends AppCompatActivity {

    Spinner spinner1;
    Spinner spinner2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        spinner1 = (Spinner) findViewById(R.id.spinner);
        List exempleList = new ArrayList();
        exempleList.add("Médecin");
        exempleList.add("Travail");
        exempleList.add("Dentiste");
        ArrayAdapter adapter = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                exempleList
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);

        spinner2 = (Spinner) findViewById(R.id.spinner2);
        List exempleList2 = new ArrayList();
        exempleList.add("Famille");
        exempleList2.add("Marie");
        exempleList2.add("José");
        exempleList2.add("Jean-Luc");
        exempleList2.add("Antoine");
        ArrayAdapter adapter2 = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                exempleList2
        );
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        TextView textView1 = findViewById(R.id.textView5);
        TextView textView2 = (TextView) findViewById(R.id.textView7);
        TextView textView3 = (TextView) findViewById(R.id.textView10);
        TextView textView4 = (TextView) findViewById(R.id.textView20);
        Intent intent = getIntent();
        final String value = intent.getStringExtra("key");
        textView1.setText("Ajout d'un rendez-vous à la date du :" + value);

        List<String> intList = new ArrayList<>( Arrays.asList(value.split("-")));

        textView2.setText(intList.get(0));
        textView3.setText(intList.get(1));
        textView4.setText(intList.get(2));

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
    private EditText txtType;
    private EditText txtPersonne;
    private TextView textView;
    private Controle controle;
    private String phone;

    private void init(){

        txtLibelle = (EditText)findViewById(R.id.editTextTextPersonName2);
        txtHeure = (EditText)findViewById(R.id.editTextNumber);
        txtMinute = (EditText)findViewById(R.id.editTextNumber2);
        txtHeure2 = (EditText)findViewById(R.id.editTextNumber3);
        txtMinute2 = (EditText)findViewById(R.id.editTextNumber4);
        this.controle = Controle.getInstance(this);
        ecouteCalcul();
        recupProfil();

    }

    private void ecouteCalcul(){

        ((Button) findViewById(R.id.button5)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String libelle = "";
                Integer jour =0;
                Integer mois =0;
                Integer annee =0;
                Integer heure =0;
                Integer minute =0;
                Integer heure2 =0;
                Integer minute2 =0;
                String type = "";
                String personne = "";

                try {

                    TextView textView1 = (TextView) findViewById(R.id.textView7);
                    TextView textView2 = (TextView) findViewById(R.id.textView10);
                    TextView textView3 = (TextView) findViewById(R.id.textView20);

                    libelle = txtLibelle.getText().toString();
                    jour = Integer.valueOf(textView1.getText().toString());
                    mois = Integer.valueOf(textView2.getText().toString());
                    annee = Integer.valueOf(textView3.getText().toString());
                    heure = parseInt(txtHeure.getText().toString());
                    minute = parseInt(txtMinute.getText().toString());
                    heure2 = parseInt(txtHeure2.getText().toString());
                    minute2 = parseInt(txtMinute2.getText().toString());
                    type = spinner1.getSelectedItem().toString();
                    personne = spinner2.getSelectedItem().toString();

                } catch (Exception e){};

                if(jour == 0 || mois == 0){
                    Toast.makeText(ThirdActivity.this, "Saisie incorrecte", Toast.LENGTH_SHORT).show();
                }else{
                    afficheResult(libelle, jour, mois, annee, heure, minute, heure2, minute2, type, personne);
                }

            }
        });

    }

    private void afficheResult(String libelle, Integer jour, Integer mois, Integer annee, Integer heure, Integer minute, Integer heure2, Integer minute2, String type, String personne){
        Toast.makeText(ThirdActivity.this, "Ajout réussis", Toast.LENGTH_SHORT).show();
        phone="0662594937";
        String message = "Création du rendez-vous " + libelle + " la date du : " + jour + ":" + mois + ":" + annee + " , de" + heure + ":" + minute + " à " + heure2 + ":" + minute2 + ".";
        SmsManager.getDefault().sendTextMessage(phone,null , message, null , null );
        this.controle.creerRdv(libelle,  jour,  mois,  annee,  heure,  minute, heure2,  minute2, type, personne, this);
    }

    private void recupProfil(){

        if(controle.getAnnee() != null){

            txtLibelle.setText(controle.getLibelle());
            txtJour.setText(controle.getJourRdv().toString());
            txtMois.setText(controle.getMoisRdv().toString());
            txtAnnee.setText(controle.getAnneeRdv().toString());
            txtHeure.setText(controle.getHeureRdv().toString());
            txtMinute.setText(controle.getMinuteRdv().toString());
            txtHeure2.setText(controle.getHeure2Rdv().toString());
            txtMinute2.setText(controle.getMinute2Rdv().toString());
            txtType.setText(controle.getLibelleRdv().toString());
            txtPersonne.setText(controle.getLibelleRdv().toString());
            ((Button)findViewById(R.id.button5)).performClick();


        }

    }


}
