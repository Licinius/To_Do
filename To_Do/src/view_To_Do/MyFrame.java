package view_To_Do;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.event.MenuListener;

import controller_To_Do.ControllerApplication;
import model_To_Do.Tache;

public class MyFrame extends JFrame{
	
//	private JMenu[] menuHorizontal = new JMenu[2];
	private ControllerApplication controller;
	private JPanel panelTache = new JPanel();
	private JScrollPane scroll = new JScrollPane();
	private JButton[] tabButtonMenu = new JButton[3];
	
	public MyFrame(){
		//this.matriceTree = matriceTree;
		setSize(800, 800);
		setResizable(false);
		setTitle("Ma liste"); 
		setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JMenuBar jmb = new JMenuBar();
		jmb.setLayout(new GridLayout());
		tabButtonMenu[0] = new JButton("Créer tâche");
		tabButtonMenu[0].addActionListener(new creerTacheListener());
		
		tabButtonMenu[1] = new JButton("Créer catégorie");
		
		tabButtonMenu[2] = new JButton("Modifier catégorie");

		for (int i = 0; i < tabButtonMenu.length; i++) {
			jmb.add("North",tabButtonMenu[i]);
		}
		setJMenuBar(jmb);
		panelTache.add(scroll);
		try {
			controller = new ControllerApplication(this);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		panelTache.setSize(50, 800);
		add(panelTache,"North");
		printTache(controller.getListTache());
		

	}
	

	
	public void printTache(ArrayList<Tache> list) {
		panelTache.removeAll();
		for (int i = 0; i < list.size(); i++) {
			JPanel jp = new JPanel();
			jp.setLayout(new BorderLayout());
			jp.setSize(800, 100);
			String str = list.get(i).toStringPourTesterPourLesJLabels().replaceAll("\t", "    ");;
			JLabel tabJ = new JLabel(str);
			tabJ.setBorder(new LineBorder(Color.BLACK));
			jp.add(tabJ);
			SuppressionBouton spB = new SuppressionBouton(list.get(i));
			spB.addActionListener(new supprimerTacheListener());
			jp.add(spB, BorderLayout.EAST);
			
			panelTache.add(jp);			
		}
		this.repaint();
		this.revalidate();
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
	class creerTacheListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			TacheDialog t = new TacheDialog(null,"Créer une nouvelle tache",true);		
			Tache tacheInfo = t.showTacheDialog(); 
		}
		
	}
	
	class supprimerTacheListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			SuppressionBouton bouton= (SuppressionBouton)e.getSource();
			try {
				controller.deleteTache(bouton.getTache());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			printTache(controller.getListTache());
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
