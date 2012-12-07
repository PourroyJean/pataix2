package pataix.objects;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import org.json.JSONException;
import org.json.JSONObject;

public class AEnvoiEI extends Thread{
	private EI Monum;
	
	public AEnvoiEI(EI Monum) {
		this.Monum = Monum;
	}
	
	public void run ()
	{
		Socket connecServ;
		try {
			connecServ = new Socket("allegro.tar-gz.fr", 50007);

		BufferedWriter out = new BufferedWriter(new
				OutputStreamWriter(connecServ.getOutputStream()));
		String Action = "uploadEI";
		JSONObject EnvoiEI= new JSONObject();
		EnvoiEI.put("action", Action);
		out.write(Monum.ToJson(EnvoiEI).toString());
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
