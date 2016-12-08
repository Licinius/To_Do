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

	private static final long serialVersionUID = 1L;
	//	private JMenu[] menuHorizontal = new JMenu[2];
	private ControllerApplication controller;
	private JPanel panelTache = new JPanel();
	private JScrollPane scroll ;
	private JButton[] tabButtonMenu = new JButton[5];
	private EnumTri tri;
	private JPanel panelTri = new JPanel();
	
	
	public MyFrame(){
		//this.matriceTree = matriceTree;
		setSize(900, 600);
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
		tabButtonMenu[4] = new JButton("Bilan");
		tabButtonMenu[4].addActionListener(new Bilan());
		for (int i = 0; i < tabButtonMenu.length; i++) {
			jmb.add("North",tabButtonMenu[i]);
		}
		setJMenuBar(jmb);
		try {
			controller = new ControllerApplication();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		panelTache.setSize(900,4600);
		add(panelTache,"Center");
		tri = EnumTri.Simple;
		controller.triSimple();
		printTache();
		scroll = new JScrollPane(panelTache,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.add(scroll,"East");
		
		panelTri.setLayout(new GridLayout());
		JButton jbSimple = new JButton("Tri Simple");
		jbSimple.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tri = EnumTri.Simple;
				controller.triSimple();
				printTache();
			}
		});
		panelTri.add(jbSimple);
		
		JButton jbComplexe = new JButton("Tri Complexe");
		jbComplexe.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				tri = EnumTri.Complexe;
				controller.triComplexe();
				printTache();
			}
		});
		panelTri.add(jbComplexe);
		
		JButton jbPrioritaire = new JButton("Tri Prioritaire");
		panelTri.add(jbPrioritaire);
		add(panelTri,"South");

	}

	public ControllerApplication getController() {
		return controller;
	}
	/**
	 * Affiche les taches tels qu'elles sont rangées dans l'arraylist du controller
	 */
	public void printTache() {
		ArrayList<Tache> list = controller.getListTache();
		panelTache.removeAll();
		panelTache.setLayout(new GridLayout(list.size(),2,50,50));
		for (int i = 0; i < list.size(); i++) {
			JPanel east = new JPanel();
			east.setLayout(new GridLayout(3,0));
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
	/**
	 * Action listener du bouton créerTache qui fait appel à TacheDialog et reprint la liste de tache suivant son tri
	 * @author Team à Antroller
	 *
	 */
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
			switch(tri){
			case Complexe:
				controller.triComplexe();
				break;
			case Prioritaire:
				break;
			case Simple:
				controller.triSimple();
				controller.triSimple();
				break;
			
			}
			printTache();
		}

	}
	/**
	 * ActionListener du bouton "Créer Categorie" qui permet de créer une categorie avec CategorieDialog
	 * @author Team à Antroller
	 *
	 */
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
	/**
	 * Action listener du bouton modifierTache  qui fait appel à ModifTacheDialog et reprint la liste de tache suivant son tri
	* @author Team à Antroller
	 *
	 */
	class ModifTacheListener implements ActionListener{

		private MyFrame mf;

		public ModifTacheListener(MyFrame myFrame) {
			mf = myFrame;
		}

		public void actionPerformed(ActionEvent e) {

			BoutonTache bouton= (BoutonTache)e.getSource();
			ModifTacheDialog t = new ModifTacheDialog(mf,"Modifier tache " + bouton.getTache().getNom(),true, bouton.getTache());		
			Tache tacheInfo = t.showModifTacheDialog(); 
			try {
				controller.modifierTache(tacheInfo);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			switch(tri){
			case Complexe:
				controller.triComplexe();
				break;
			case Prioritaire:
				break;
			case Simple:
			default:
				controller.triSimple();
				break;
			
			}
			printTache();
		}

	}
	/**
	 * Action listener du bouton "Modifier Categorie"   qui fait appel à ModifCategorieDialog et reprint la liste de tache suivant son tri
	* @author Team à Antroller
	 *
	 */
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
				e1.printStackTrace();
			}
			printTache();
		}

	}
	/**
	 * Permet d'afficher un bilan
	 * @author Marvin
	 *
	 */
	class Bilan implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
		}

	}
	/**
	 * Action listener du bouton "Supprimer Categorie"   qui fait appel à SupprimerCategorieDialog et reprint la liste de tache suivant son tri
	* @author Team à Antroller
	 *
	 */
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
	/**
	 * Action listener du bouton "Terminer tache"   qui fait appel termine la tache et reprint la liste de tache suivant son tri
	* @author Team à Antroller
	 *
	 */
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
	/**
	 * ActionListener du bouton augmenterGranularite qui augmente de 5 la granularite actuelle à l'aide de la fonction modifierGranularite(Tache t)
	 * @author Team à Antroller
	 *
	 */
	class augmenterGranularite implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			BoutonTache bouton= (BoutonTache)e.getSource();
			try {
				controller.modifierGranularite(bouton.getTache());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			switch(tri){
				case Complexe:
					controller.triComplexe();
					break;
				case Prioritaire:
					break;
				case Simple:
				default:
					controller.triSimple();
					break;
			
			}
			printTache();
		}
	}
	/**
	 * ActionListener du bouton SupprimerTache qui supprime la tache cliquée
	 * @author Team à Antroller
	 *
	 */
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

	public static void main(String[] args) {
		MyFrame f = new MyFrame();
		f.setVisible(true);
	}
}
