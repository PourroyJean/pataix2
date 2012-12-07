

package com.example.ndlmaps;

import java.util.ArrayList;
import java.util.List;

import pataix.data.ADataSelect;
import pataix.geoloc.APosition;
import pataix.objects.AComClientMap;
import pataix.objects.ALimits;
import pataix.objects.EI;


import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.maps.*;

public class MainActivity extends MapActivity {
	
	private MapView mapView;
	private MapController mc;
	private GeoPoint location;
	private APosition pos;
	private Button refresh ;
	EditText editLimit;
	private int limit;

	
	private MyLocationOverlay myLocation = null;
	private MyLocationOverlay me;
	
	private ArrayList<EI> LieuxOpenData = new ArrayList<EI>();
	private ALimits limits;
	
	
	
	

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		 mapView = (MapView) this.findViewById(com.example.ndlmaps.R.id.mapView);
		 pos = new APosition(this);

		 mc = mapView.getController();
		 
		 
		 myLocation = new MyLocationOverlay(getApplicationContext(), mapView);
		 mapView.getOverlays().add(myLocation);
		 
		 editLimit = (EditText)findViewById(R.id.xLimit);
		 limit = Integer.parseInt(editLimit.getText().toString());
		 
		 //************bouton Zoom***********************
		 mapView.setBuiltInZoomControls(true);
		 
		 
		//*************bouton refresh *******************
		refresh = (Button)findViewById(com.example.ndlmaps.R.id.button1);
		refresh.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				 limit = Integer.parseInt(editLimit.getText().toString());
				 Log.w("69",limit+"" );
				 myLocation = new MyLocationOverlay(getApplicationContext(), mapView);
				 mapView.getOverlays().add(myLocation);
				 myLocation.enableMyLocation();
				double latitude  = pos.getPosition().getLatitude();
		 		double longitude = pos.getPosition().getLongitude();
		 		mc.setCenter(new GeoPoint((int) (latitude * 1000000.0),(int) (longitude * 1000000.0)));
		 		
		 		 limits = new ALimits(pos.getPosition(), 10000);
		 		
			      new AsyncLoadOpenData().execute(null);

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
	
	//////////////////// NICOLAS ///////////////////////////
	
	@Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        /* We want to capture the place the user long pressed on the map and add a marker (pin) on the map at that lat/long.
         * This solution:
         *  1. Allows you to set the time threshold for what constitutes a long press
         *  2. Doesn't get fooled by scrolling, multi-touch, or non-multi-touch events

         * Thank you Roger Kind Kristiansen for the main idea
         */     

        //get the action from the MotionEvent: down, move, or up
        int actionType = ev.getAction();

        if (actionType == MotionEvent.ACTION_DOWN) {
            //user pressed the button down so let's initialize the main variables that we care about:
            // later on when the "Action Up" event fires, the "DownTime" should match the "startTimeForLongClick" that we set here  
            // the coordinate on the screen should not change much during the long press
            startTimeForLongClick=ev.getEventTime();
            xScreenCoordinateForLongClick=ev.getX();
            yScreenCoordinateForLongClick=ev.getY();

        } else if (actionType == MotionEvent.ACTION_MOVE) {
            //For non-long press actions, the move action can happen a lot between ACTION_DOWN and ACTION_UP                    
            if (ev.getPointerCount()>1) {
                //easiest way to detect a multi-touch even is if the pointer count is greater than 1
                //next thing to look at is if the x and y coordinates of the person's finger change.
                startTimeForLongClick=0; //instead of a timer, just reset this class variable and in our ACTION_UP event, the DownTime value will not match and so we can reset.                        
            } else {
                //I know that I am getting to the same action as above, startTimeForLongClick=0, but I want the processor
                //to quickly skip over this step if it detects the pointer count > 1 above
                float xmove = ev.getX(); //where is their finger now?                   
                float ymove = ev.getY();
                //these next four values allow you set a tiny box around their finger in case
                //they don't perfectly keep their finger still on a long click.
                xlow = xScreenCoordinateForLongClick - xtolerance;
                xhigh= xScreenCoordinateForLongClick + xtolerance;
                ylow = yScreenCoordinateForLongClick - ytolerance;
                yhigh= yScreenCoordinateForLongClick + ytolerance;
                if ((xmove<xlow || xmove> xhigh) || (ymove<ylow || ymove> yhigh)){
                    //out of the range of an acceptable long press, reset the whole process
                    startTimeForLongClick=0;
                }
            }

        } else if (actionType == MotionEvent.ACTION_UP) {
            //determine if this was a long click:
            long eventTime = ev.getEventTime();
            long downTime = ev.getDownTime(); //this value will match the startTimeForLongClick variable as long as we didn't reset the startTimeForLongClick variable because we detected nonsense that invalidated a long press in the ACTION_MOVE block

            //make sure the start time for the original "down event" is the same as this event's "downTime"
            if (startTimeForLongClick==downTime){ 
                //see if the event time minus the start time is within the threshold
                if ((eventTime-startTimeForLongClick)>minMillisecondThresholdForLongClick){ 
                    //make sure we are at the same spot where we started the long click
                    float xup = ev.getX();                  
                    float yup = ev.getY();
                    //I don't want the overhead of a function call:
                    xlow = xScreenCoordinateForLongClick - xtolerance;
                    xhigh= xScreenCoordinateForLongClick + xtolerance;
                    ylow = yScreenCoordinateForLongClick - ytolerance;
                    yhigh= yScreenCoordinateForLongClick + ytolerance;
                    if ((xup>xlow && xup<xhigh) && (yup>ylow && yup<yhigh)){ 

                        //**** safe to process your code for an actual long press **** 
                        //comment out these next rows after you confirm in logcat that the long press works
                        long totaltime=eventTime-startTimeForLongClick;
                        String strtotaltime=Long.toString(totaltime);                               
                        Log.d("long press detected: ", strtotaltime);


                        
                        //Now get the latitude/longitude of where you clicked.  Replace all the code below if you already know how to translate a screen coordinate to lat/long.  I know it works though.

                        //*****************
                        //I have my map under a tab so I have to account for the tab height and the notification bar at the top of the phone.  
                        // Maybe there are other ways so just ignore this if you already know how to get the lat/long of the pixels that were pressed.
                        Display display = getWindowManager().getDefaultDisplay(); 
                        int EntireScreenHeight = display.getHeight();
                        //the projection is mapping pixels to where you touch on the screen.
                        Projection proj = mapView.getProjection();
                        GeoPoint loc = proj.fromPixels((int)(ev.getX(ev.getPointerCount()-1)), (int)(ev.getY(ev.getPointerCount()-1))); 
                        int longitude=loc.getLongitudeE6();             
                        int latitude=loc.getLatitudeE6();
                        //*****************

                        //**** here's where you add code to: 
                        // put a marker on the map, save the point to your SQLite database, etc

                        //********************* JEAN : creation bundle ************
                        Bundle objetbunble = new Bundle();
                        objetbunble.putString("longitude", longitude +"");
            			objetbunble.putString("latitude", latitude + "");
            			
            			
                        
        				Log.w("Map", longitude + " " + latitude);
        				Intent myIntent = new Intent(getApplicationContext(), AFormulaire.class);
        				myIntent.putExtras(objetbunble);
        				startActivityForResult(myIntent, 0);
        				
                    }
                }
            }

        }
        
        return super.dispatchTouchEvent(ev);
	}
     
	private class AsyncLoadOpenData extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			ADataSelect select = new ADataSelect("http://dataprovence.cloudapp.net:8080/v1/dataprovencetourisme/MonumentsEtStesCulturels/?format=json");
  		  	LieuxOpenData = select.SearchItem(limits);
  		  	Log.w("JSON","Fin de DL");
  		  	

			return null;
		}

		
		@Override
		protected void onPostExecute(Void result) {

	 		Drawable marker=getResources().getDrawable(R.drawable.marker);
		    
		    marker.setBounds(0, 0, marker.getIntrinsicWidth(),
		                            marker.getIntrinsicHeight());
		    
		    mapView.getOverlays().add(new SitesOverlay(marker));
		    
		    me=new MyLocationOverlay(getApplicationContext(), mapView);
		    mapView.getOverlays().add(me);
		}
	}
	
	 private class SitesOverlay extends ItemizedOverlay<OverlayItem> {
		    private List<OverlayItem> items=new ArrayList<OverlayItem>();
		    
		    public SitesOverlay(Drawable marker) {
		      super(marker);
		      
		      boundCenterBottom(marker);
		      
		      //AComClientMap com = new AComClientMap(limits);
		      
		      //ArrayList<EI> Lieux = com.getTabMonum();
		      for (EI elem : LieuxOpenData) {
		    	  Log.w("Map",  elem.GetLocation().getLatitude() + " " + elem.GetLocation().getLongitude()  + " " + elem.GetNom());
		    	  items.add(new OverlayItem(new GeoPoint((int) (elem.GetLocation().getLatitude()*1000000), (int) (elem.GetLocation().getLongitude()*1000000)), elem.GetNom(), elem.GetDescription()));
		      }

		      populate();
		    }
		    
		    @Override
		    protected OverlayItem createItem(int i) {
		      return(items.get(i));
		    }
		    
		    @Override
		    protected boolean onTap(int i) {
		      
		      return(true);
		    }
		    
		    @Override
		    public int size() {
		      return(items.size());
		    }
	 }
        
    private int minMillisecondThresholdForLongClick = 800;
    private long startTimeForLongClick = 0;
    private float xScreenCoordinateForLongClick;
    private float yScreenCoordinateForLongClick;
    private float xtolerance=10;//x pixels that your finger can be off but still constitute a long press
    private float ytolerance=10;//y pixels that your finger can be off but still constitute a long press
    private float xlow; //actual screen coordinate when you subtract the tolerance
    private float xhigh; //actual screen coordinate when you add the tolerance
    private float ylow; //actual screen coordinate when you subtract the tolerance
    private float yhigh; //actual screen coordinate when you add the tolerance
    

}
