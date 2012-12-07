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

import android.util.Log;

public class RecupEI extends Thread{
	protected ArrayList<EI> TabMonum = new ArrayList<EI>();
	protected ALimits Coord;
	
	public ArrayList<EI> getTabMonum() {
		return TabMonum;
	}

	public RecupEI(ALimits Coord) {
		this.Coord = Coord;
	}
	
	public void run() {
		Socket connecServ;
		try {
			connecServ = new Socket("allegro.tar-gz.fr", 50007);
			BufferedWriter out = new BufferedWriter(new
					OutputStreamWriter(connecServ.getOutputStream()));
			BufferedReader in = new BufferedReader(new
					InputStreamReader(connecServ.getInputStream()));
			String Action = "getEI";
			JSONObject ActionJson= new JSONObject();
			ActionJson=Coord.ToJson(ActionJson);
			ActionJson.put("action", Action);
			out.write(ActionJson.toString());
			out.newLine();
			out.flush();
			
			JSONArray AStocker = new JSONArray (in.readLine());
			for (int i = 0 ; i < AStocker.length() ; ++i)
			{
				JSONObject ObjectMonum = AStocker.getJSONObject(i);
				EI Monum = new EI();
				Monum.FromJson(ObjectMonum);
				TabMonum.add(Monum);
				Log.e("Map", Monum.GetLocation().toString());
			}
			in.close();
			out.close();
			connecServ.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
