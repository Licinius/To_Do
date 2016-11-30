package model_To_Do;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import controller_To_Do.ControllerApplication;
import exception_To_Do.ExceptionTacheAnterieur;

public class main {

	public static void main(String[] args) {
			ControllerApplication cA = null;
			try {
				cA = new ControllerApplication();
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Categorie cat = new Categorie(0,"Personnel");
			Calendar date = new GregorianCalendar(2016, 12, 12);
			TachePonctuelle t=null;
			try {
				t = new TachePonctuelle(0, "Faire a manger","Surement du Poulet", date,cat );
			} catch (ExceptionTacheAnterieur e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				cA.createCategorie(cat);
				cA.createTache(t);
			} catch (IOException e) {
				System.out.println("Erreur");
				e.printStackTrace();
			}
			cA.printCategorie();
			cA.printTache();
			try {
				cA.deleteCategorie(cat);
			} catch (IOException e) {
				System.out.println("Erreur");
				e.printStackTrace();
			}
			cA.printCategorie();
			cA.printTache();
			try {
				cA.deleteAll();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}

}
