package pataix.geoloc;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class APosition implements LocationListener
{
    protected Location    		 position;
   
    
    public Location getPosition ()
    {
   	 return position;
    }
    
    public APosition(Activity activity)
    {
    	
   	 LocationManager manager = (LocationManager) activity.getSystemService(Activity.LOCATION_SERVICE);
   	 if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER))
   	 {   		 
   		 manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 0, this);
   		 position = new Location(LocationManager.GPS_PROVIDER);
   	 }
   	 manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10000, 0, this);
   	position = new Location(LocationManager.NETWORK_PROVIDER);
    }
    
    @Override
    public void onLocationChanged(Location location)
    {
   	 position = location;
    }

    @Override
    public void onProviderDisabled(String provider)
    {
   	 // TODO Auto-generated method stub
   	 
    }

    @Override
    public void onProviderEnabled(String provider)
    {
   	 // TODO Auto-generated method stub
   	 
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras)
    {
   	 // TODO Auto-generated method stub
   	 
    }
    
}

