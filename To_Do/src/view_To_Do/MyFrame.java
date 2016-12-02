package view_To_Do;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import controller_To_Do.ControllerApplication;
import exception_To_Do.ExceptionTacheAnterieur;
import model_To_Do.Categorie;
import model_To_Do.Tache;
import model_To_Do.TachePonctuelle;

public class MyFrame extends JFrame{
	
//	private JMenu[] menuHorizontal = new JMenu[2];
	private ControllerApplication controller;
	private JPanel listTache = new JPanel();;
	
	
	public MyFrame(ControllerApplication cA){
		//this.matriceTree = matriceTree;
		setSize(800, 800); 
		setTitle("Ma liste"); 
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	
		JMenuBar jmb = new JMenuBar();
		jmb.add(new JMenu("Créer tâche"));jmb.add(new JMenu("Créer catégorie"));jmb.add(new JMenu("Modifier catégorie"));
		setJMenuBar(jmb);
		
		controller = cA;
		printTache(controller.getListTache());
		listTache.setLayout(new BoxLayout(listTache, BoxLayout.PAGE_AXIS));
		
		add(listTache);
	}
	
	public void printTache(ArrayList<Tache> list) {
		String str;
		for (int i = 0; i < list.size(); i++) {
			str = list.get(i).toStringPourTesterPourLesJLabels().replaceAll("\t", "    ");;
			JLabel tabJ = new JLabel(str);
			tabJ.setBorder(new LineBorder(Color.BLACK));
			listTache.add(tabJ);
		}

	}
	

	class JMenuListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JMenuItem menuItem= (JMenuItem)e.getSource();
			
		}
	}
	
	class JButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
		}
	}
	public static void main(String[] args) {
			
		ControllerApplication cA = null;
		try {
			cA = new ControllerApplication();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		Categorie cat = new Categorie("Personnel");
		Categorie cat2 = new Categorie("Professionnel");
		Calendar date = new GregorianCalendar(2016, 12, 12); //YYYY MM DD
		Calendar date2 = new GregorianCalendar(2019, 12, 25);
		Calendar date3 = new GregorianCalendar(2021, 02, 15);
		TachePonctuelle t=null, t2=null, t3=null;
		try {
			t = new TachePonctuelle("Faire a manger","Surement du Poulet", date, cat);
			t2 = new TachePonctuelle("Acheter des cadeaux","Un gros camion pour Lulu", date2, cat);
			t3 = new TachePonctuelle("Trouver un boulot","C'est fini le chomage", date3, cat2);
		} catch (ExceptionTacheAnterieur e1) {
			e1.printStackTrace();
		}
		try {
			cA.createCategorie(cat);
			cA.createCategorie(cat2);
			cA.createTache(t);
			cA.createTache(t2);
			cA.createTache(t3);
		} catch (IOException e) {
			System.out.println("Erreur");
			e.printStackTrace();
		}
		
//		try {
//			cA.deleteCategorie(0);
//			cA.deleteCategorie(1);
//			cA.deleteCategorie(2);
//			cA.deleteCategorie(3);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	
		cA.printCategorie();
		cA.printTache();
		
		MyFrame f = new MyFrame(cA);
		f.setVisible(true);
	}
}
