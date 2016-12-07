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

	public TacheLongCours(String nom, String description, Calendar echeance, Categorie categorie, int granularite) throws ExceptionTacheAnterieur{
		super(nom, description, echeance, categorie);
		this.granularite = granularite;
	}

	public TacheLongCours(int id, String nom, Calendar echeance, int granularite) throws ExceptionTacheAnterieur{
		super(id, nom, echeance);
		this.granularite = granularite;
	}
	
	protected void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
		super.readObject(ois);
		this.granularite = ois.readInt();
	}
	
	protected void writeObject(ObjectOutputStream oos) throws IOException{
		super.writeObject(oos);
		oos.writeInt(this.granularite);
	}
	public Map<String,String> getInformation() { 
		Map<String,String> str = super.getInformation();
		str.put("Granularite",String.valueOf(this.granularite));
		return str;
	}
	
}
