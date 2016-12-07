package view_To_Do;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model_To_Do.Categorie;

public class ModifCategorieDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private Categorie info;
	private  JTextField nom;
	private MyFrame owner;


	public ModifCategorieDialog(MyFrame parent, String title, boolean modal){
		super(parent, title, modal);
		this.owner = parent;
		this.setSize(550, 270);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.initComponent();
	}

	public Categorie showModifDialog(){
		this.setVisible(true);      
		return  info;      
	}

	private void initComponent(){
		JPanel panCat = new JPanel();
		panCat.setPreferredSize(new Dimension(220, 150));
		panCat.setBorder(BorderFactory.createTitledBorder("Nom de la categorie"));
		panCat.setLayout(new BoxLayout(panCat, BoxLayout.PAGE_AXIS));

		JPanel jp1 = new JPanel();
		ArrayList<Categorie> listCat = owner.getController().getListCategorie();
		String[] tab = new String[listCat.size()];
		for (int i = 0; i < listCat.size(); i++) {
			tab[i] = listCat.get(i).getNom();
		}
		JComboBox<String> combo = new JComboBox<String>(tab);
		combo.setPreferredSize(new Dimension(100, 20));
		JLabel oldCat = new JLabel("Catégorie à modifier :");
		jp1.add(oldCat);
		jp1.add(combo);

		JPanel jp2 = new JPanel();
		nom = new JTextField();
		nom.setPreferredSize(new Dimension(100, 25));

		JLabel nomLabel = new JLabel("Nouveau nom :");
		jp2.add(nomLabel);
		jp2.add(nom);

		panCat.add(jp1);
		panCat.add(jp2);

		JPanel content = new JPanel();
		content.setBackground(Color.white);
		content.add(panCat);

		JPanel control = new JPanel();
		JButton okBouton = new JButton("OK");

		okBouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				if (!nom.getText().trim().isEmpty()) {
					info = owner.getController().getListCategorie().get(combo.getSelectedIndex());
					info.setNom(nom.getText());
					setVisible(false);
				} else {
					setVisible(false);
				}
			}
		});



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
