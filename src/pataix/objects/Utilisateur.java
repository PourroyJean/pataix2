package pataix.objects;

public class Utilisateur {
	protected String idtel;
	protected String pseudo;
	
	public Utilisateur () {}
	
	public Utilisateur (String a_idtel, String a_pseudo) {
		idtel = a_idtel;
		pseudo = a_pseudo;
	}
	
	public String GetIdtel () {
		return idtel;
	}
	
	public String GetPseudo () {
		return pseudo;
	}

}
