package model_To_Do;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

import exception_To_Do.ExceptionTacheAnterieur;

public class TacheLongCours extends Tache {

	private static final long serialVersionUID = 3081649104084734383L;
	private int granularite;
	private Calendar dateDebut;

	public TacheLongCours(String nom, String description, Calendar echeance, Categorie categorie) throws ExceptionTacheAnterieur{
		super(nom, description, echeance, categorie);
		this.granularite = 0;
		dateDebut=  Calendar.getInstance();
	}

	public TacheLongCours(int id, String nom, Calendar echeance, int granularite) throws ExceptionTacheAnterieur{
		super(id, nom, echeance);
		this.granularite = granularite;
	}
	protected void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
		super.readObject(ois);
		this.granularite = ois.readInt();
		this.dateDebut = (Calendar) ois.readObject();
	}

	protected void writeObject(ObjectOutputStream oos) throws IOException{
		super.writeObject(oos);
		oos.writeInt(this.granularite);
		oos.writeObject(dateDebut);
	}
	/**
	 * IsRetard pour un objet TacheLongCours est plus complexe car il suit la règle suivante  :
	 * Si on nomme d la durée impartie pour une tˆache
	 * (différence entre son échéance et sa date de d´ebut), on vérifie l’avancement à chaque pas de d/4 : l’avancement
	 *	doit être au moins de 25% à d/4, de 50% à d/2, de 75% à 3d/4 et de 100% à d. 
	 * 
	 */
	public boolean isRetarded(){
		long diff =  super.getEcheance().getTimeInMillis()-dateDebut.getTimeInMillis();
		long restant = Calendar.getInstance().getTimeInMillis()- dateDebut.getTimeInMillis() ;
		if(restant <diff/4){
			return false;
		}
		if (restant<diff/2){
			return granularite>=25;
				
		}
		if (restant<3*diff/4){
			return granularite>=50;
		}
		if(restant<diff){
			return granularite>=75;
		}
		
		return granularite>=100;
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
	
}
