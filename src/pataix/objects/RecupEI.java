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

public class RecupEI {
	protected ArrayList<EI> TabMonum = new ArrayList<EI>();
	
	public ArrayList<EI> getTabMonum() {
		return TabMonum;
	}

	public RecupEI(ALimits Coord) throws UnknownHostException, IOException, JSONException{
		Socket connecServ = new Socket("allegro.tar-gz.fr", 50007);
		BufferedWriter out = new BufferedWriter(new
				OutputStreamWriter(connecServ.getOutputStream()));
		BufferedReader in = new BufferedReader(new
				InputStreamReader(connecServ.getInputStream()));
		String Action = "getEI";
		JSONObject ActionJson= new JSONObject();
		ActionJson.put("action", Action);
		out.write(Coord.ToJson(ActionJson).toString());
		
		JSONArray AStocker = new JSONArray (in.readLine());
		for (int i = 0 ; i < AStocker.length() ; ++i)
		{
			JSONObject ObjectMonum = AStocker.getJSONObject(i);
			EI Monum = new EI();
			Monum.FromJson(ObjectMonum);
			TabMonum.add(Monum);
		}
		in.close();
		out.close();
		connecServ.close();
	}
	
}
