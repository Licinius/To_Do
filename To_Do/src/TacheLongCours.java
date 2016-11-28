import java.util.Calendar;

import ExceptionTo_Do.ExceptionTacheAnterieur;

public class TacheLongCours extends Tache {

	private String granularite;

	public TacheLongCours(int id, String nom, String description, Calendar echeance, Categorie categorie, String granularite) throws ExceptionTacheAnterieur{
		super(id, nom, description, echeance, categorie);
		this.granularite = granularite;
	}

	public TacheLongCours(int id, String nom, Calendar echeance, String granularite) throws ExceptionTacheAnterieur{
		super(id, nom, echeance);
		this.granularite = granularite;
	}
	
}
