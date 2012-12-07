package pataix.objects;

import org.json.JSONException;
import org.json.JSONObject;

import android.location.Location;

public class EI {
	protected String id;
	protected Location loc;
	protected String nom;
	protected double note;
	protected String description;
	
	public EI () {}
	
	public EI (EI elem) {
		id = elem.id;
		loc = elem.loc;
		nom = elem.nom;
		note = elem.note;
		description = elem.description;
	}
	
	public EI (String a_id, double latitude, double longitude, String a_nom, double a_note, String a_description) {
		id = a_id;
		loc.setLatitude(latitude);
		loc.setLongitude(longitude);
		nom = a_nom;
		note = a_note;
		description = a_description;
	}
	
	public JSONObject ToJson() throws JSONException
	{
		JSONObject jsonRes= new JSONObject();
		jsonRes.put("id", id);
		jsonRes.put("latitude", loc.getLatitude());
		jsonRes.put("longitude", loc.getLongitude());
		jsonRes.put("nom", nom);
		jsonRes.put("note", note);
		jsonRes.put("description", description);
		return jsonRes;
		
	}
	
	public void FromJson(JSONObject jsonRecup) throws JSONException
	{
		id = jsonRecup.getString("id");
		loc.setLatitude(jsonRecup.getDouble("latitude"));
		loc.setLongitude(jsonRecup.getDouble("longitude"));
		nom = jsonRecup.getString("nom");
		note = jsonRecup.getDouble("note");
		description = jsonRecup.getString("description");	
	}
	
	public String GetId () {
		return id;
	}
	
	public Location GetLocation () {
		return loc;
	}
	
	public String GetNom () {
		return nom;
	}
	
	public double GetNote () {
		return note;
	}
	
	public String GetDescription () {
		return description;
	}
	
	public String SetId (String a_id) {
		return id = a_id;
	}
	
	public Location SetLocation (Location a_loc) {
		return loc = a_loc;
	}
	
	public String SetNom (String a_nom) {
		return nom = a_nom;
	}
	
	public double SetNote (double a_note) {
		return note = a_note;
	}
	
	public String SetDescription (String a_description) {
		return description = a_description;
	}
}
