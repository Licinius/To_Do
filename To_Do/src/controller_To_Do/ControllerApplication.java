package controller_To_Do;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

import model_To_Do.Categorie;
import model_To_Do.Tache;
import model_To_Do.TacheLongCours;
import view_To_Do.MyFrame;

public class ControllerApplication {
	private ArrayList<Categorie> listCategorie = new ArrayList<Categorie>();
	private ArrayList<Tache> listTache = new ArrayList<Tache>();
	private MyFrame view;
	public ControllerApplication(MyFrame v) throws IOException, ClassNotFoundException{
		//Lecture des categories
		File fichierIn =  new File("save"+ File.separator +"categorie.ser") ;// ouverture d'un flux sur un fichier
		FileInputStream fichierInStream = new FileInputStream(fichierIn);
		ObjectInputStream ois=null;
		if(fichierInStream.available()>0){
			ois = new ObjectInputStream(fichierInStream);
			while(fichierInStream.available() > 0){
				listCategorie.add((Categorie)ois.readObject());
			}
			ois.close();
		}


		//Lecture des taches
		fichierIn =  new File("save"+ File.separator +"tache.ser") ;// ouverture d'un flux sur un fichier
		fichierInStream = new FileInputStream(fichierIn);
		if(fichierInStream.available()>0){
			ois = new ObjectInputStream(fichierInStream);
			while(fichierInStream.available() > 0){
				Tache tmp = (Tache)ois.readObject();
				if(!tmp.isTermine())
					tmp.setRetard(tmp.isRetarded());
					listTache.add(tmp);
			}
			ois.close();
		}
		updateTache();
		view = v;

	}
	
	public ArrayList<Tache> getListTache() {
		return listTache;
	}
	
	public ArrayList<Categorie> getListCategorie() {
		return listCategorie;
	}
	
	/**
	 * Cette fonction crée un nouveau fichier contenant les nouvelles catégories
	 * @param categorie
	 * 	La categorie que l'on veut créer
	 * @throws IOException
	 */
	public void createCategorie(Categorie categorie) throws IOException{
		if(categorie!=null){
			listCategorie.add(categorie);
			File fichierOut =  new File("save"+ File.separator +"categorie.ser") ;// ouverture d'un flux sur un fichier
			FileOutputStream fichierOutStream = new FileOutputStream(fichierOut);
			ObjectOutputStream oos=null;
			oos = new ObjectOutputStream(fichierOutStream);
			for(Categorie cat : this.listCategorie){
				oos.writeObject(cat);
			}
			oos.close();
		}
	}

	/**
	 * Cette fonction crée un nouveau fichier sans la categorie passé en paramètre
	 * @param categorie 
	 * 	La categorie que l'on veut supprimer
	 * @throws IOException
	 */
	public void deleteCategorie(Categorie categorie) throws IOException{
		if(categorie!=null){
			listCategorie.remove(categorie);
			File fichierOut =  new File("save"+ File.separator +"categorie.ser") ;// ouverture d'un flux sur un fichier
			FileOutputStream fichierOutStream = new FileOutputStream(fichierOut);
			ObjectOutputStream oos=null;
			oos = new ObjectOutputStream(fichierOutStream);
			for(Categorie cat : this.listCategorie){
				oos.writeObject(cat);
			}
			oos.close();
			for(Tache t : this.listTache){
				if(t.getCategorie() != null){
					if(t.getCategorie().equals(categorie))	t.setCategorie(null);
				}
			}
			updateTache();
		}
	}
	/**
	 * Supprime une categorie du fichier ser à l'aide de son identifiant
	 * @param id
	 * 	Identifiant de la categorie que l'on souhaite supprimer
	 * @throws IOException
	 */
	public void deleteCategorie(int id) throws IOException{
		for (int i = 0; i < listCategorie.size(); i++) {
			if (listCategorie.get(i).getIdentifiant() == id) {
				listCategorie.remove(listCategorie.get(i));
				break;
			}
		}
		File fichierOut =  new File("save"+ File.separator +"categorie.ser") ;// ouverture d'un flux sur un fichier
		FileOutputStream fichierOutStream = new FileOutputStream(fichierOut);
		ObjectOutputStream oos=null;
		oos = new ObjectOutputStream(fichierOutStream);
		for(Categorie cat : this.listCategorie){
			oos.writeObject(cat);
		}
		oos.close();
		for(Tache t : this.listTache){
			if(t.getCategorie() != null){
				if(t.getCategorie().getIdentifiant() == id)	t.setCategorie(null);
			}
		}
		updateTache();
	}
	/**
	 * Met à jour le fichier tache.ser
	 * @throws IOException
	 */
	private void updateTache() throws IOException{
		File fichierOut =  new File("save"+ File.separator +"tache.ser") ;// ouverture d'un flux sur un fichier
		FileOutputStream fichierOutStream = new FileOutputStream(fichierOut);
		ObjectOutputStream oos=null;
		oos = new ObjectOutputStream(fichierOutStream);
		for(Tache t : this.listTache){
			oos.writeObject(t);
		}
		oos.close();
	}
	
	/**
	 * Créer une tache dans le fichier ser
	 * @param tache
	 * 	La tache que l'on souhaite ajouter au fichier tache.ser
	 * @throws IOException
	 */
	public void createTache(Tache tache) throws IOException{
		if(tache !=null){
			listTache.add(tache);
			File fichierOut =  new File("save"+ File.separator +"tache.ser") ;// ouverture d'un flux sur un fichier
			FileOutputStream fichierOutStream = new FileOutputStream(fichierOut);
			ObjectOutputStream oos=null;
			oos = new ObjectOutputStream(fichierOutStream);
			for(Tache t : this.listTache){
				oos.writeObject(t);
			}
			oos.close();
		}
	}
	
	/**
	 * Supprime une tache contenu dans le fichier ser
	 * @param tache
	 * 	Tache que l'on souhaite supprimer
	 * @throws IOException
	 */
	public void deleteTache(Tache tache) throws IOException{
		if(tache!=null){
			listTache.remove(tache);
			File fichierOut =  new File("save"+ File.separator +"tache.ser") ;// ouverture d'un flux sur un fichier
			FileOutputStream fichierOutStream = new FileOutputStream(fichierOut);
			ObjectOutputStream oos=null;
			oos = new ObjectOutputStream(fichierOutStream);
			for(Tache t : this.listTache){
				oos.writeObject(t);
			}
			oos.close();
		}
	}
	
	/**
	 * Supprime une tache contenu dans le fichier ser à l'aide de son identifiant
	 * @param id	
	 * 	Identifiant de la tache que l'on souhaite supprimer
	 * @throws IOException
	 */
	public void deleteTache(int id) throws IOException{
		if(id>=0){
			for (int i = 0; i < listCategorie.size(); i++) {
				if (listTache.get(i).getId() == id) {
					listTache.remove(listTache.get(i));
					break;
				}
			}
			File fichierOut =  new File("save"+ File.separator +"tache.ser") ;// ouverture d'un flux sur un fichier
			FileOutputStream fichierOutStream = new FileOutputStream(fichierOut);
			ObjectOutputStream oos=null;
			oos = new ObjectOutputStream(fichierOutStream);
			for(Tache t : this.listTache){
				oos.writeObject(t);
			}
			oos.close();
		}
	}

	/**
	 * Permet d'afficher les catégories contenus dans le fichier categorie.ser
	 */
	public void printCategorie(){
		for(Categorie cat : this.listCategorie){
			System.out.println(cat.toString());
		}
		System.out.println();
	}

	/**
	 * Supprime tout le contenu des fichier ser en les écrasant
	 * @throws FileNotFoundException
	 */
	public void deleteAll() throws FileNotFoundException{
		File fichierOut =  new File("save"+ File.separator +"tache.ser") ;// ouverture d'un flux sur un fichier
		FileOutputStream fichierOutStream = new FileOutputStream(fichierOut);

		fichierOut =  new File("save"+ File.separator +"categorie.ser") ;// ouverture d'un flux sur un fichier
		fichierOutStream = new FileOutputStream(fichierOut);

	}
	
	/**
	 * Tri la liste de façon simplifier en mettant les taches les plus urgents en premier
	 * Complexité : O(n²)
	 */
	public void triSimple() {
		for (int i = 0; i < listTache.size()-1; i++) {
			for (int j = i+1; j < listTache.size(); j++) {
				if (listTache.get(i).getJourRestant() > listTache.get(j).getJourRestant()) {
					Collections.swap(listTache, i, j);
				}
			}
		}
	}
	

	
	/**
	 * Set tache.termine a true et supprime de list
	 * @param tache
	 * 	la tache que l'on souhaite terminer
	 * @throws IOException
	 */
	public void terminerTache(Tache tache)throws IOException {

		for (int i = 0; i < listCategorie.size(); i++) {
			if (listTache.get(i).getId() == tache.getId()) {
				listTache.get(i).setTermine(true);
				listTache.remove(listTache.get(i));
				break;
			}
		}
		File fichierOut =  new File("save"+ File.separator +"tache.ser") ;// ouverture d'un flux sur un fichier
		FileOutputStream fichierOutStream = new FileOutputStream(fichierOut);
		ObjectOutputStream oos=null;
		oos = new ObjectOutputStream(fichierOutStream);
		for(Tache t : this.listTache){
			oos.writeObject(t);
		}
		oos.close();
	}
	/**
	 * Permet de modifier dans le fichier .ser la granularite d'une tache au long cours
	 * @param tache
	 * 	Tache au long cours que l'on souhaite modifier
	 * @throws IOException
	 */
	public void modifierGranularite(Tache tache) throws IOException{
		((TacheLongCours) tache).setGranularite(((TacheLongCours) tache).getGranularite()+5);
		for (int i = 0; i < listCategorie.size(); i++) {
			if (listTache.get(i).getId() == tache.getId()) {
				((TacheLongCours) listTache.get(i)).setGranularite(((TacheLongCours) tache).getGranularite());
				if(tache.isTermine()){
					listTache.remove(listTache.get(i));
				}
				break;
			}
		}
		File fichierOut =  new File("save"+ File.separator +"tache.ser") ;// ouverture d'un flux sur un fichier
		FileOutputStream fichierOutStream = new FileOutputStream(fichierOut);
		ObjectOutputStream oos=null;
		oos = new ObjectOutputStream(fichierOutStream);
		for(Tache t : this.listTache){
			oos.writeObject(t);
		}
		oos.close();
		
	}
	
	/**
	 * Permet de modifier une categorie 
	 * @param catInfo
	 * 	Categorie que l'on souhaite modifier
	 * @throws IOException
	 */
	public void modifierCategorie(Categorie catInfo) throws IOException {
		if(catInfo !=null){
			File fichierOut =  new File("save"+ File.separator +"categorie.ser") ;// ouverture d'un flux sur un fichier
			FileOutputStream fichierOutStream = new FileOutputStream(fichierOut);
			ObjectOutputStream oos=null;
			oos = new ObjectOutputStream(fichierOutStream);
			for(Categorie cat : this.listCategorie){
				oos.writeObject(cat);
			}
			oos.close();
			for(Tache t : this.listTache){
				if(t.getCategorie() != null){
					if(t.getCategorie().getIdentifiant() == catInfo.getIdentifiant())	t.setCategorie(catInfo);
				}
			}
			updateTache();
		}
	}
	/**
	 * Permet de modifier une tache dans le fichier ser
	 * @param tacheInfo tache que l'on souhaite modifier
	 * @throws IOException
	 */
	public void modifierTache(Tache tacheInfo) throws IOException {
		if(tacheInfo !=null){//Verifie si la tache n'est pas null
			File fichierOut =  new File("save"+ File.separator +"tache.ser") ;// ouverture d'un flux sur un fichier
			FileOutputStream fichierOutStream = new FileOutputStream(fichierOut);
			ObjectOutputStream oos=null;
			oos = new ObjectOutputStream(fichierOutStream);
			for(Tache tache : this.listTache){
				oos.writeObject(tache);
			}
			oos.close();
			updateTache();
		}
	}


}
