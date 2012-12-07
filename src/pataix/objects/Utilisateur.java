package pataix.objects;

import org.json.JSONException;
import org.json.JSONObject;

public class Utilisateur {
	protected String idtel;
	protected String pseudo;
	
	public Utilisateur () {}
	
	public Utilisateur (String a_idtel, String a_pseudo) {
		idtel = a_idtel;
		pseudo = a_pseudo;
	}
	
	public JSONObject ToJson() throws JSONException
	{
		JSONObject jsonRes= new JSONObject();
		jsonRes.put("idtel", idtel);
		jsonRes.put("pseudo", pseudo);
		return jsonRes;
	}
	
	public void FromJson(JSONObject jsonRecup) throws JSONException
	{
		idtel = jsonRecup.getString("idtel");
		pseudo = jsonRecup.getString("pseudo");
	}
	
	public String GetIdtel () {
		return idtel;
	}
	
	public String GetPseudo () {
		return pseudo;
	}
	
	public String SetIdtel (String a_idtel) {
		return idtel = a_idtel;
	}
	
	public String SetPseudo (String a_pseudo) {
		return pseudo = a_pseudo;
	}

}
