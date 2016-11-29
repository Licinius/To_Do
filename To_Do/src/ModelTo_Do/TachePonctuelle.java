package ModelTo_Do;
import java.util.Calendar;

import ExceptionTo_Do.ExceptionTacheAnterieur;

public class TachePonctuelle extends Tache {

	public TachePonctuelle(int id, String nom, String description, Calendar echeance, Categorie categorie) throws ExceptionTacheAnterieur{
		super(id, nom, description, echeance, categorie);
	}
	
	public TachePonctuelle(int id, String nom, Calendar echeance)throws ExceptionTacheAnterieur {
		super(id, nom, echeance);
	}

}
