package com.example.ndlmaps;

import pataix.geoloc.APosition;

import com.google.android.maps.*;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends MapActivity {
	
	private MapView mapView;
	private MapController mc;
	private GeoPoint location;
	private APosition pos;
	private Button refresh ;
	TextView txtLongitude;
	TextView txtLatitude;
	
	private MyLocationOverlay myLocation = null;
	
	
	
	
	

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		 mapView = (MapView) this.findViewById(com.example.ndlmaps.R.id.mapView);
		 pos = new APosition(this);
		 txtLongitude = (TextView)findViewById(R.id.xlongitude);
		 txtLatitude = (TextView)findViewById(R.id.xlatitude);
		 mc = mapView.getController();
		 
		 
		 myLocation = new MyLocationOverlay(getApplicationContext(), mapView);
		 mapView.getOverlays().add(myLocation);
		 
		 
		 //************bouton Zoom***********************
		 mapView.setBuiltInZoomControls(true);
		 
		 
		//*************bouton refresh *******************
		refresh = (Button)findViewById(com.example.ndlmaps.R.id.button1);
		refresh.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				
				 myLocation = new MyLocationOverlay(getApplicationContext(), mapView);
				 mapView.getOverlays().add(myLocation);
				 myLocation.enableMyLocation();
				double latitude  = pos.getPosition().getLatitude();
		 		double longitude = pos.getPosition().getLongitude();
		 		mc.setCenter(new GeoPoint((int) (latitude * 1000000.0),(int) (longitude * 1000000.0)));
		 		
//		 		
		 		txtLatitude.setText(latitude + "");
		 		txtLongitude.setText(longitude + "");
//				
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

}
