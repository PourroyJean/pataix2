package pataix.objects;

import android.location.Location;
import org.json.*;

public class ALimits {
	protected String latMin;
	protected String latMax;
	protected String longMin;
	protected String longMax;
	
	public ALimits (Location position, double distance){
		double difDegLat = ((distance * 90.0)/40075.01);
		double difDegLong = ((distance * 180.0)/40007.864);
		latMin = "" + (position.getLatitude() - difDegLat);
		latMax = "" + (position.getLatitude() + difDegLat);
		longMin = "" + (position.getLongitude() - difDegLong);
		longMax = "" + (position.getLongitude() + difDegLong);
	}
	
	public JSONObject ToJson() throws JSONException
	{
		JSONObject jsonRes= new JSONObject();
		jsonRes.put("latMin", latMin);
		jsonRes.put("latMax", latMax);
		jsonRes.put("longMin", longMin);
		jsonRes.put("longMax", longMax);
		return jsonRes;
		
	}
	
	public void FromJson(JSONObject jsonRecup) throws JSONException
	{
		latMin = jsonRecup.getString("latMin");
		latMax = jsonRecup.getString("latMax");
		longMin = jsonRecup.getString("longMin");
		longMax = jsonRecup.getString("longMax");		
	}
	
		
	
	public String GetlatMin()
	{
		return latMin;
	}
	
	public String GetlongMin()
	{
		return longMin;
	}
	
	public String GetlatMax()
	{
		return latMax;
	}
	
	public String GetlongMax()
	{
		return longMax;
	}

}
