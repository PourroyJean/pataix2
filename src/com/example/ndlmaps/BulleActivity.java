package com.example.ndlmaps;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

public class BulleActivity extends Activity 
{
	Button bFin;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bulle);
		
		bFin = (Button) findViewById(R.id.xFinish);
		
		Bundle objetbunble  = this.getIntent().getExtras();
		if (objetbunble != null && objetbunble.containsKey("longitude") && objetbunble.containsKey("latitude")) 
		{}	
		
		//******* Initialisation de valeurs
		String title = (this.getIntent().getStringExtra("arg1"));
        String descr = (this.getIntent().getStringExtra("arg2"));
        
        EditText E1 = (EditText) findViewById(R.id.xDescr);
        E1.setText(descr);
        
		
		
		bFin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				// TODO Auto-generated method stub
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_bulle, menu);
		return true;
	}

}
