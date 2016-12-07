package view_To_Do;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import model_To_Do.Categorie;
import model_To_Do.Tache;

public class TacheDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private Tache info;
	private MyFrame owner;
	private boolean sendData;
	private JTextField nom;
	private JTextField echeance;
	private JTextField description;


	public TacheDialog(MyFrame parent, String title, boolean modal){
		super(parent, title, modal);
		owner = parent;
		this.setSize(700, 600);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.initComponent();
	}

	public Tache showTacheDialog(){
		this.sendData = false;
		this.setVisible(true);      
		return this.info;      
	}

	private void initComponent(){
		;

		JPanel comb = new JPanel();
		ButtonGroup bg = new ButtonGroup();
		JRadioButton jr1 = new JRadioButton("Normale");
		JRadioButton jr2 = new JRadioButton("Long cours");
		bg.add(jr1);
		bg.add(jr2);
		jr1.setSelected(true);
		comb.add(jr1);
		comb.add(jr2);
		comb.setPreferredSize(new Dimension(220, 20));
		comb.setBorder(BorderFactory.createTitledBorder("Type de la tache"));

		JPanel panNom = new JPanel();
		nom = new JTextField();
		nom.setPreferredSize(new Dimension(100, 25));
		panNom.setPreferredSize(new Dimension(220, 20));
		panNom.setBorder(BorderFactory.createTitledBorder("Nom de la tache"));
		JLabel nomLabel = new JLabel("Saisir un nom :");
		panNom.add(nomLabel);
		panNom.add(nom);

		JPanel panEcheance = new JPanel();
		echeance = new JTextField();
		echeance.setPreferredSize(new Dimension(100, 25));
		panEcheance.setPreferredSize(new Dimension(220, 20));
		panEcheance.setBorder(BorderFactory.createTitledBorder("Echeance de la tache"));
		JLabel echeanceLabel = new JLabel("Saisir une échéance :");
		panEcheance.add(echeanceLabel);
		panEcheance.add(echeance);
		
		JPanel panCat = new JPanel();
		panCat.setPreferredSize(new Dimension(220, 20));
		panCat.setBorder(BorderFactory.createTitledBorder("Categorie"));
		ArrayList<Categorie> listCat = owner.getController().getListCategorie();
		String[] tab = new String[listCat.size()];
		for (int i = 0; i < listCat.size(); i++) {
			tab[i] = listCat.get(i).getNom();
		}
		JComboBox combo = new JComboBox(tab);
		combo.setPreferredSize(new Dimension(100, 20));
		panCat.add(combo);

		JPanel panDescription = new JPanel();
		description = new JTextField();
		description.setPreferredSize(new Dimension(400, 100));
		panDescription.setPreferredSize(new Dimension(220, 100));
		panDescription.setBorder(BorderFactory.createTitledBorder("Description"));
		panDescription.add(description);
		
		
		JPanel content = new JPanel();
		content.setSize(700, 600);
		content.setBackground(Color.white);
		content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));
		content.add(comb);
		content.add(panNom);
		content.add(panEcheance);
		content.add(panCat);
		content.add(panDescription);

		JPanel control = new JPanel();
		JButton okBouton = new JButton("OK");

		okBouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {   
				setVisible(false);
			}
		}      
				);

		JButton cancelBouton = new JButton("Annuler");
		cancelBouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}      
		});

		control.add(okBouton);
		control.add(cancelBouton);
		this.getContentPane().add(content, BorderLayout.CENTER);
		this.getContentPane().add(control, BorderLayout.SOUTH);
	}  
}
