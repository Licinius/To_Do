package view_To_Do;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller_To_Do.ControllerApplication;
import model_To_Do.Categorie;
import model_To_Do.Tache;
import model_To_Do.TachePonctuelle;

public class MyFrame extends JFrame{

	//	private JMenu[] menuHorizontal = new JMenu[2];
	private ControllerApplication controller;
	private JPanel panelTache = new JPanel();
	private JScrollPane scroll ;
	private JButton[] tabButtonMenu = new JButton[4];

	public MyFrame(){
		//this.matriceTree = matriceTree;
		setSize(800, 600);
		setTitle("Ma liste"); 
		setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JMenuBar jmb = new JMenuBar();
		jmb.setLayout(new GridLayout());
		tabButtonMenu[0] = new JButton("Créer tâche");
		tabButtonMenu[0].addActionListener(new creerTacheListener(this));
		tabButtonMenu[1] = new JButton("Créer catégorie");
		tabButtonMenu[1].addActionListener(new creerCategorieListener());
		tabButtonMenu[2] = new JButton("Modifier catégorie");
		tabButtonMenu[2].addActionListener(new ModifCatListener(this));
		tabButtonMenu[3]=new JButton("Supprimer catégorie");
		tabButtonMenu[3].addActionListener(new SupprimerCategorie(this));

		for (int i = 0; i < tabButtonMenu.length; i++) {
			jmb.add("North",tabButtonMenu[i]);
		}
		setJMenuBar(jmb);
		try {
			controller = new ControllerApplication(this);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		panelTache.setSize(800,4600);
		add(panelTache,"Center");
		printTache();
		scroll = new JScrollPane(panelTache,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.add(scroll,"East");

	}

	public ControllerApplication getController() {
		return controller;
	}

	public void printTache() {
		ArrayList<Tache> list = controller.getListTache();
		panelTache.removeAll();
		panelTache.setLayout(new GridLayout(list.size(),2,50,50));
		for (int i = 0; i < list.size(); i++) {
			JPanel east = new JPanel();
			east.setLayout(new GridLayout(2,0));
			PanelTache jp = new PanelTache(list.get(i));
			if(list.get(i).isRetard())
				jp.setBorder(BorderFactory.createLineBorder(Color.RED));
			else
				jp.setBorder(BorderFactory.createLineBorder(Color.BLACK));

			if(list.get(i).isTermine())
				jp.setBorder(BorderFactory.createLineBorder(Color.GREEN));

			BoutonTache mB = new BoutonTache("Modifier", list.get(i));
			mB.addActionListener(new ModifTacheListener(this));
			east.add(mB);
			BoutonTache spB = new BoutonTache("Supprimer", list.get(i));
			spB.addActionListener(new supprimerTacheListener());
			east.add(spB);
			if(list.get(i) instanceof TachePonctuelle){
				BoutonTache tB = new BoutonTache("Termine", list.get(i));
				tB.addActionListener(new terminerBoutonTache());
				east.add(tB);
			}else{
				BoutonTache tB = new BoutonTache("Augmenter granularite", list.get(i));
				tB.addActionListener(new augmenterGranularite());
				east.add(tB);
			}
			jp.add(east,BorderLayout.LINE_END);
			panelTache.add(jp);			
		}

		this.repaint();
		this.revalidate();
	}

	class creerTacheListener implements ActionListener{

		private MyFrame mf;

		public creerTacheListener(MyFrame myFrame) {
			mf = myFrame;
		}

		public void actionPerformed(ActionEvent e) {
			TacheDialog t = new TacheDialog(mf,"Créer une nouvelle tache",true);		
			Tache tacheInfo = t.showTacheDialog(); 
			try {
				controller.createTache(tacheInfo);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			printTache();
		}

	}

	class ModifTacheListener implements ActionListener{

		private MyFrame mf;

		public ModifTacheListener(MyFrame myFrame) {
			mf = myFrame;
		}

		public void actionPerformed(ActionEvent e) {

			BoutonTache bouton= (BoutonTache)e.getSource();
			ModifTacheDialog t = new ModifTacheDialog(mf,"Créer une nouvelle tache",true, bouton.getTache());		
			Tache tacheInfo = t.showModifTacheDialog(); 
			try {
				controller.modifierTache(bouton.getTache());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			printTache();
		}

	}

	class ModifCatListener implements ActionListener{

		private MyFrame mf;

		public ModifCatListener(MyFrame myFrame) {
			mf = myFrame;
		}

		public void actionPerformed(ActionEvent e) {
			ModifCategorieDialog c = new ModifCategorieDialog(mf,"Modifier nom categorie",true);		
			Categorie catInfo = c.showModifDialog();
			try {
				controller.modifierCategorie(catInfo);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			printTache();
		}

	}

	class SupprimerCategorie implements ActionListener{

		private MyFrame mf;

		public SupprimerCategorie(MyFrame myFrame) {
			mf = myFrame;
		}

		public void actionPerformed(ActionEvent e) {
			SupprimerCategorieDialog c = new SupprimerCategorieDialog(mf," Supprimer categorie",true);		
			int idCat= c.showModifDialog();
			try {
				controller.deleteCategorie(idCat);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			printTache();
		}

	}
	class terminerBoutonTache implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			BoutonTache bouton= (BoutonTache)e.getSource();
			try {
				controller.terminerTache(bouton.getTache());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			printTache();
		}
	}

	class augmenterGranularite implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			BoutonTache bouton= (BoutonTache)e.getSource();
			try {
				controller.modiferGranularite(bouton.getTache());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			printTache();
		}
	}
	class supprimerTacheListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			BoutonTache bouton= (BoutonTache)e.getSource();
			try {
				controller.deleteTache(bouton.getTache());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			printTache();
		}

	}
	class creerCategorieListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			CategorieDialog t = new CategorieDialog(null,"Créer une nouvelle catégorie",true);		
			Categorie categorieInfo = t.showTacheDialog();
			try {
				controller.createCategorie(categorieInfo);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

	}
	public static void main(String[] args) {

		MyFrame f = new MyFrame();
		f.setVisible(true);

		//		Categorie cat = new Categorie("Personnel");
		//		Categorie cat2 = new Categorie("Professionnel");
		//		Calendar date = new GregorianCalendar(2016, 11, 12); //YYYY MM-1 DD
		//		Calendar date2 = new GregorianCalendar(2019, 11, 25);
		//		Calendar date3 = new GregorianCalendar(2021, 01, 15);
		//		TachePonctuelle t=null, t2=null, t3=null;
		//		try {
		//			t = new TachePonctuelle("Faire a manger","Surement du Poulet", date, cat);
		//			t2 = new TachePonctuelle("Acheter des cadeaux","Un gros camion pour Lulu", date2, cat);
		//			t3 = new TachePonctuelle("Trouver un boulot","C'est fini le chomage", date3, cat2);
		//		} catch (ExceptionTacheAnterieur e1) {
		//			e1.printStackTrace();
		//		}
		//		try {
		//			cA.createCategorie(cat);
		//			cA.createCategorie(cat2);
		//			cA.createTache(t);
		//			cA.createTache(t2);
		//			cA.createTache(t3);
		//		} catch (IOException e) {
		//			System.out.println("Erreur");
		//			e.printStackTrace();
		//		}

		//		try {
		//			cA.deleteCategorie(0);
		//			cA.deleteCategorie(1);
		//			cA.deleteCategorie(2);
		//			cA.deleteCategorie(3);
		//		} catch (IOException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		}

		//		cA.printCategorie();
		//		cA.printTache();


	}
}
