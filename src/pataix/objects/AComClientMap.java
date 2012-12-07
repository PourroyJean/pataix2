package pataix.objects;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

public class AComClientMap {
	ArrayList<EI> TabMonum = new ArrayList<EI>();
	
	AComClientMap(ALimits Coord) throws UnknownHostException, IOException, JSONException{
		Socket connecServ = new Socket("allegro.tar-gz.fr", 50007);
		BufferedWriter out = new BufferedWriter(new
				OutputStreamWriter(connecServ.getOutputStream()));
		BufferedReader in = new BufferedReader(new
				InputStreamReader(connecServ.getInputStream()));
		
		out.write(Coord.ToJson().toString());
		JSONObject AStocker = new JSONObject (in.readLine());
		for (int i = 0 ; i < AStocker.length() ; ++i)
		{
			EI Monum = new EI();
			Monum.FromJson(AStocker);
			TabMonum.add(Monum);
		}
	}
	
	
	
}
