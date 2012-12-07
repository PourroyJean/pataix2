package pataix.objects;

import org.json.JSONException;
import org.json.JSONObject;

public class Media {
	protected String id;
	protected String url;
	protected String mediatype;
	
	public Media () {}
	
	public Media (String a_id, String a_url, String a_mediatype) {
		id = a_id;
		url = a_url;
		mediatype = a_mediatype;
	}
	
	public JSONObject ToJson() throws JSONException
	{
		JSONObject jsonRes= new JSONObject();
		jsonRes.put("id", id);
		jsonRes.put("url", url);
		jsonRes.put("mediatype", mediatype);
		return jsonRes;
	}
	
	public void FromJson(JSONObject jsonRecup) throws JSONException
	{
		id = jsonRecup.getString("id");
		url = jsonRecup.getString("url");
		mediatype = jsonRecup.getString("mediatype");
	}
	
	public String GetId () {
		return id;
	}
	
	public String GetUrl () {
		return url;
	}
	
	public String GetMediatype () {
		return url;
	}
	
	public String SetId (String a_id) {
		return id = a_id;
	}
	
	public String SetUrl (String a_url) {
		return url = a_url;
	}
	
	public String SetMediatype (String a_mediatype) {
		return url = a_mediatype;
	}

}
