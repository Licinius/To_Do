package model_To_Do;
import java.util.Calendar;
import java.util.Map;

import exception_To_Do.ExceptionTacheAnterieur;

public class TacheLongCours extends Tache {

	private static final long serialVersionUID = 3081649104084734383L;
	private int granularite;


	public TacheLongCours(String nom, String description, Calendar echeance, Categorie categorie) throws ExceptionTacheAnterieur{
		super(nom, description, echeance, categorie);
		this.granularite = 0;
		
	}

	public TacheLongCours(int id, String nom, Calendar echeance, int granularite) throws ExceptionTacheAnterieur{
		super(id, nom, echeance);
		this.granularite = granularite;
	}

	/**
	 * IsRetard pour un objet TacheLongCours est plus complexe car il suit la règle suivante  :
	 * Si on nomme d la durée impartie pour une tâche
	 * (différence entre son échéance et sa date de début), on vérifie l’avancement à chaque pas de d/4 : l’avancement
	 *	doit être au moins de 25% à d/4, de 50% à d/2, de 75% à 3d/4 et de 100% à d. 
	 * 
	 */
	public boolean isRetarded(){
		//si la tache est terminé
		if (granularite == 100) {
			return false;
		} else {
			//diff : différence entre la prochaine echeance et la date actuelle
			long diff = this.getNextEcheance().getTimeInMillis() - Calendar.getInstance().getTimeInMillis();
			if (diff > 0) {
				return false;
			} else {
				return true;
			}
		}
	}
	
	public int getGranularite() {
		return granularite;
	}
	
	/**
	 * Setter de la granularite si la granularite passe à 100% la tache est terminé
	 * @param granularite
	 */
	public void setGranularite(int granularite) {
		if(granularite ==100)
			super.setTermine(true);
		this.granularite = granularite;
	}
	
	/**
	 * Rajout de la granularite dans la Map
	 */
	public Map<String,String> getInformation() { 
		Map<String,String> str = super.getInformation();
		str.put("Granularite",String.valueOf(this.granularite));
		return str;
	}
	
	public Calendar getNextEcheance(){
		long diff =  super.getEcheance().getTimeInMillis()-super.getDateDebut().getTimeInMillis();
		Calendar res = Calendar.getInstance();
		if (granularite < 25){
			res.setTimeInMillis(super.getDateDebut().getTimeInMillis()+(diff/4));
			return res;
		} else if (granularite < 50){
			res.setTimeInMillis(super.getDateDebut().getTimeInMillis()+(diff/2));
			return res;
		} else if (granularite < 75){
			res.setTimeInMillis(super.getDateDebut().getTimeInMillis()+(3*diff/4));
			return res;
		} else {
			return super.getEcheance();
		}
	}
	
}
