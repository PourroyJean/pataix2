package pataix.data;

import java.io.IOException;
import java.util.ArrayList;
import android.util.Log;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import pataix.objects.ALimits;
import pataix.objects.EI;

public class ADataSelect
{


    protected ArrayList <EI> VectADataItem = new ArrayList<EI>();; //Vector contenant les items dans MaxPerimeter
    protected String url = new String();
    
    public ArrayList<EI> getVectADataItem() 
    {
		return VectADataItem;
	}
    
    public ADataSelect(String url) 
    {
    	this.url = url;
    }
    
    public ArrayList<EI> SearchItem(ALimits limits) 
    {
    	
		if (!VectADataItem.isEmpty()) VectADataItem.clear(); // On vide le vector pr�c�dement remplis

    	//On vas chercher les donn�es
    	JSONObject data;

		try {
			
			data = jsonArrayFromUrl();
            JSONArray donnees= data.getJSONArray("d");

	    	for(int i = 0; i < donnees.length(); ++i)
	        {
	    		
	    		JSONObject jsonObject= donnees.getJSONObject(i);
				Log.e("JSON", "ok");

	            double latitudeItem  = Double.parseDouble(jsonObject.getString("longitude"));
	            double longitudeItem = Double.parseDouble(jsonObject.getString("latitude"));	    
	   
		        if (latitudeItem  > Double.parseDouble(limits.GetlatMin())  &&  
		        	latitudeItem  < Double.parseDouble(limits.GetlatMax())  &&
		        	longitudeItem > Double.parseDouble(limits.GetlongMin()) &&
		        	longitudeItem < Double.parseDouble(limits.GetlongMax()))
		        {

		        	//On construit les objets ADataItem et on les ajoute dans le tableau
			        String entityId     = new String(jsonObject.getString("entityid"));
			        String desc         = new String(jsonObject.getString("type"));
			        String nom          = new String(jsonObject.getString("raisonsociale"));
			        
			        VectADataItem.add(new EI(entityId, latitudeItem, longitudeItem, nom, 0, desc));
		        }
		    
		           
	        }
	    	if (VectADataItem.size() == 0) VectADataItem.add(new EI("NULL", 0, 0, "NULL", 0, "NULL"));
	    	
	        
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (JSONException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return VectADataItem;
	}

    JSONObject jsonArrayFromUrl() throws IOException
    {  
        // On r�cup�re un client capable d'envoyer des requ�tes
        HttpClient client = new DefaultHttpClient();
        HttpGet getAction = new HttpGet(url);
        HttpResponse response = client.execute(getAction);
     
        // on r�cup�re le payload de la r�ponse en String
        HttpEntity entity = response.getEntity();
        String entityStr = (entity == null) ? null : EntityUtils.toString(entity);

        try 
        {
             return new JSONObject(entityStr);
        } 
        catch (Exception ex) 
        {

            throw new IOException("Invalid response from server !");
        }
    }

}
