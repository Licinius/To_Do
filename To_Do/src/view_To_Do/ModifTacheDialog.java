package view_To_Do;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model_To_Do.Categorie;
import model_To_Do.Tache;

public class ModifTacheDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private Tache info;
	private JTextField nom;
	private JFormattedTextField echeance;
	private JTextField description;
	private MyFrame owner;


	public ModifTacheDialog(MyFrame parent, String title, boolean modal, Tache old){
		super(parent, title, modal);
		owner = parent;
		info = old;
		this.setSize(700, 600);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.initComponent();
	}
	
	public Tache showModifTacheDialog(){
		this.setVisible(true);      
		return this.info;      
	}

	private void initComponent(){

		JPanel panNom = new JPanel();
		
		nom = new JTextField(info.getNom());
		nom.setPreferredSize(new Dimension(100, 25));
		panNom.setPreferredSize(new Dimension(220, 20));
		panNom.setBorder(BorderFactory.createTitledBorder("Nom de la tache"));
		JLabel nomLabel = new JLabel("Saisir un nom :");
		panNom.add(nomLabel);
		panNom.add(nom);

		JPanel panEcheance = new JPanel();
		info.getEcheance();
		info.getEcheance();
		info.getEcheance();
		echeance = new JFormattedTextField(info.getEcheance().get(Calendar.DAY_OF_MONTH)+"/"+(info.getEcheance().get(Calendar.MONTH)+1)+"/"+info.getEcheance().get(Calendar.YEAR));	           
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
		int index = 0;
		for (int i = 0; i < listCat.size(); i++) {
			tab[i] = listCat.get(i).getNom();
			if (listCat.get(i).getIdentifiant() == info.getCategorie().getIdentifiant()) {
				index = i;
			}
		}
		JComboBox<String> combo = new JComboBox<String>(tab);
		combo.setPreferredSize(new Dimension(100, 20));
		combo.setSelectedIndex(index);
		panCat.add(combo);

		JPanel panDescription = new JPanel();
		description = new JTextField(info.getDescription());
		description.setPreferredSize(new Dimension(400, 100));
		panDescription.setPreferredSize(new Dimension(220, 100));
		panDescription.setBorder(BorderFactory.createTitledBorder("Description"));
		panDescription.add(description);
		
		
		JPanel content = new JPanel();
		content.setSize(700, 600);
		content.setBackground(Color.white);
		content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));
		content.add(panNom);
		content.add(panEcheance);
		content.add(panCat);
		content.add(panDescription);

		JPanel control = new JPanel();
		JButton okBouton = new JButton("OK");

		okBouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				
				if(!nom.getText().trim().isEmpty() && !description.getText().trim().isEmpty() && echeance.isEditValid()){
					description.setBackground(Color.WHITE);
					nom.setBackground(Color.WHITE);
					echeance.setBackground(Color.WHITE);
					String stringValues[] = echeance.getText().split("/");
					int values[] = new int[stringValues.length];
					boolean formatValid=true;
					for (int i = 0; i < values.length; i++) {
						if(!stringValues[i].trim().isEmpty()){
							values[i] = Integer.parseInt(stringValues[i].trim());
						}else{
							formatValid = false;
						}
					}
					if(values[0]<31 && values[1]<13 && values[2]>1970 && formatValid){
						Calendar date = new GregorianCalendar(values[2], values[1]-1, values[0]);//YYYY MM-1 DD
						if(date.after(Calendar.getInstance())){
							info.setCategorie(listCat.get(combo.getSelectedIndex()));
							info.setDescription(description.getText());
							info.setEcheance(new GregorianCalendar(values[2], values[1]-1, values[0]));
							info.setNom(nom.getText());
							setVisible(false);
						}
						else{
							JOptionPane.showMessageDialog(null, "La date indiqué doit être après la date d'aujourd'hui", "Attention ! ", JOptionPane.ERROR_MESSAGE);            
						}
					}else{
						echeance.setBackground(Color.PINK);
					}
				}else{
					if(nom.getText().trim().isEmpty()){
						nom.setText("");
						nom.setBackground(Color.PINK);
					}else{
						nom.setBackground(Color.WHITE);
					}
					if(description.getText().trim().isEmpty()){
						description.setText("");
						description.setBackground(Color.PINK);
					}else{
						description.setBackground(Color.WHITE);
					}
					if(!echeance.isEditValid()){
						echeance.setText("");
						echeance.setBackground(Color.PINK);
					}else{
						echeance.setBackground(Color.WHITE);
					}
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
