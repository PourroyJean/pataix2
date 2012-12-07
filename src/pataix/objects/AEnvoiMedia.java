package pataix.objects;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import org.json.JSONException;
import org.json.JSONObject;

public class AEnvoiMedia {
	AEnvoiMedia (Media Donnee) throws UnknownHostException, IOException, JSONException
	{
		Socket connecServ = new Socket("allegro.tar-gz.fr", 50007);
		BufferedWriter out = new BufferedWriter(new
				OutputStreamWriter(connecServ.getOutputStream()));
		String Action = "uploadMedia";
		JSONObject EnvoiMedia= new JSONObject();
		EnvoiMedia.put("action", Action);
		out.write(Donnee.ToJson(EnvoiMedia).toString());
		out.close();
		connecServ.close();
	}
}
