package com.example.ndlmaps;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AFormulaire  extends Activity{
	private EditText 	editNom;
	private Button		butValider;
	private String		retour;
	private AFormulaire This;
	
	protected void onCreate(android.os.Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		This = this;
		setContentView(R.layout.form_ajout);
		
		editNom = (EditText) findViewById(R.id.editNom);
		butValider = (Button) findViewById(R.id.butValider);
		
		Init();
	}
	
	public String getNom() {
		return retour;
	}
	
	private void Init(){
		butValider.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View v) {
				retour = editNom.getText().toString();
				Intent intent = This.getIntent();
                setResult(RESULT_OK, intent);
                finish();
			}
		});
	}
	
	
	
}
