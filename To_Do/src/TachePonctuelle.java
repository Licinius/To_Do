import java.util.Calendar;

public class TachePonctuelle extends Tache {

	public TachePonctuelle(int id, String nom, String description, Calendar echeance, Categorie categorie) {
		super(id, nom, description, echeance, categorie);
	}
	
	public TachePonctuelle(int id, String nom, Calendar echeance) {
		super(id, nom, echeance);
	}

}
