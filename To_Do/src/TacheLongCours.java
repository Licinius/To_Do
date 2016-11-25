import java.util.Calendar;

public class TacheLongCours extends Tache {

	private String granularite;

	public TacheLongCours(int id, String nom, String description, Calendar echeance, Categorie categorie, String granularite) {
		super(id, nom, description, echeance, categorie);
		this.granularite = granularite;
	}

	public TacheLongCours(int id, String nom, Calendar echeance, String granularite) {
		super(id, nom, echeance);
		this.granularite = granularite;
	}
	
}
