package com.example.ndlmaps;

import java.io.IOException;
import java.net.UnknownHostException;

import org.json.JSONException;

import pataix.objects.AEnvoiEI;
import pataix.objects.EI;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;


public class AFormulaire  extends Activity{
	private EditText 	editNom;
	private EditText 	editDescr;
	private Button		butValider;
	private String		retour;
	private AFormulaire This;
	private RatingBar Rating;
	
	//objet IE
	private String Id;
	private int latitude;
	private int longitude;
	private String Nom;
	private double Notation;
	private String Description;

	
	protected void onCreate(android.os.Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		This = this;
		setContentView(R.layout.form_ajout);
		
		editNom = (EditText) findViewById(R.id.editNom);
		editDescr = (EditText) findViewById(R.id.xDescr);
		butValider = (Button) findViewById(R.id.butValider);
		
		//*********** JEAN : bundle 
		Bundle objetbunble  = this.getIntent().getExtras();
		if (objetbunble != null && objetbunble.containsKey("longitude") && objetbunble.containsKey("latitude")) 
		{}	
		
		//******* Initialisation de valeurs
		longitude = Integer.parseInt(this.getIntent().getStringExtra("longitude"));
        latitude = Integer.parseInt(this.getIntent().getStringExtra("latitude"));
		Rating = (RatingBar)findViewById(R.id.xNote);
		

        
		
		Init();
	}
	
	public String getNom() {
		return retour;
	}
	
	private void Init(){
		butValider.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View v) {
	
				
				//add base de donn�e peyrobn 
				Id = "1";				
				Nom = editNom.getText().toString();
				Notation = Rating.getRating();
				Description = editDescr.getText().toString();
				
			
				
				EI Obj = new EI(Id, latitude, longitude, Nom, Notation, Description);
		
				
				new AEnvoiEI(Obj).start();

				
				Log.w("69",Notation+"" );
				retour = editNom.getText().toString();
				Intent intent = This.getIntent();
                setResult(RESULT_OK, intent);
                finish();
			}
		});
	}
	
	
	
}
