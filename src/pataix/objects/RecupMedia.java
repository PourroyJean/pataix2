package pataix.objects;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RecupMedia {
	protected ArrayList<Media> TabMonum = new ArrayList<Media>();
	
	public RecupMedia(String id) throws UnknownHostException, IOException, JSONException
	{
		Socket connecServ = new Socket("allegro.tar-gz.fr", 50007);
		BufferedWriter out = new BufferedWriter(new
				OutputStreamWriter(connecServ.getOutputStream()));
		BufferedReader in = new BufferedReader(new
				InputStreamReader(connecServ.getInputStream()));
		String Action = "getdetailEI";
		JSONObject EnvoiEI= new JSONObject();
		EnvoiEI.put("action", Action);
		EnvoiEI.put("id", id);
		out.write(EnvoiEI.toString());
		
		JSONArray JsonRecup = new JSONArray (in.readLine());
		for (int i = 0 ; i < JsonRecup.length() ; ++i)
		{
			JSONObject ObjectMed = JsonRecup.getJSONObject(i);
			Media Med = new Media();
			Med.FromJson(ObjectMed);
			TabMonum.add(Med);
		}
		in.close();
		out.close();
		connecServ.close();
	}
}
