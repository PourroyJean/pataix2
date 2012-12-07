package pataix.objects;

import org.json.JSONException;
import org.json.JSONObject;

public class Commentaire {
	protected	String id;
	protected	String idtel;
	protected	String commentaire;
	
	public Commentaire () {}
	
	public Commentaire (Commentaire Comm){
		id = Comm.id;
		idtel = Comm.idtel;
		commentaire = Comm.commentaire;
	}
	
	public Commentaire (String a_id, String a_idtel, String a_commentaire) {
		id = a_id;
		idtel = a_idtel;
		commentaire = a_commentaire;
	}
	
	public JSONObject ToJson(JSONObject jsonRes) throws JSONException
	{
		jsonRes.put("id", id);
		jsonRes.put("idtel", idtel);
		jsonRes.put("commentaire", commentaire);
		return jsonRes;
		
	}
	
	public void FromJson(JSONObject jsonRecup) throws JSONException
	{
		id = jsonRecup.getString("id");
		idtel = jsonRecup.getString("idtel");
		commentaire = jsonRecup.getString("commentaire");		
	}
	
	public String getId () {
		return id;
	}
	
	public String getIdtel () {
		return idtel;
	}
	
	public String getCommentaire () {
		return commentaire;
	}
	
	public String setId (String a_id) {
		return id = a_id;
	}
	
	public String setIdtel (String a_idtel) {
		return idtel = a_idtel;
	}
	
	public String setCommentaire (String a_commentaire) {
		return commentaire = a_commentaire;
	}
}
