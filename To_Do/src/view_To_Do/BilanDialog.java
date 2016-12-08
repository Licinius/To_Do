package view_To_Do;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;


public class BilanDialog  extends JDialog {
	private static final long serialVersionUID = 1L;
	private Calendar[] info = new Calendar[2];
	private JFormattedTextField dateDebut,dateFin;



	public BilanDialog(MyFrame parent, String title, boolean modal){
		super(parent, title, modal);
		this.setSize(550, 270);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.initComponent();
	}

	public Calendar[] showChoixDate(){
		this.setVisible(true);      
		return  info;      
	}
	/**
	 * Initialise les composants de la boîte de dialogue ModifCategorie
	 */
	private void initComponent(){
		JPanel panDate = new JPanel();
		panDate.setPreferredSize(new Dimension(220, 150));
		panDate.setBorder(BorderFactory.createTitledBorder("Choisissez vos dates : "));
		panDate.setLayout(new BoxLayout(panDate, BoxLayout.PAGE_AXIS));
		JPanel content = new JPanel();
		content.setBackground(Color.white);
		content.add(panDate);
		
		JPanel panDateDebut = new JPanel();
		dateDebut = new JFormattedTextField();
	    MaskFormatter dateMask;
		try {
			dateMask = new MaskFormatter("##/##/####");
			dateMask.install(dateDebut);
		} catch (ParseException e) {
			System.err.println("Une erreur c'est produite lors de la création du champs dateDebut");
			e.printStackTrace();
		}
		dateDebut.setPreferredSize(new Dimension(100, 25));
		JLabel dateDebutLabel = new JLabel("Saisir date de début :");
		panDateDebut.add(dateDebutLabel);
		panDateDebut.add(dateDebut);
		panDate.add(panDateDebut);
		
		
		JPanel panDateFin = new JPanel();
		dateFin = new JFormattedTextField();
		try {
			dateMask = new MaskFormatter("##/##/####");
			dateMask.install(dateFin);
		} catch (ParseException e) {
			System.err.println("Une erreur c'est produite lors de la création du champs dateFin");
			e.printStackTrace();
		}
		dateFin.setPreferredSize(new Dimension(100, 25));
		JLabel dateFinLabel = new JLabel("Saisir date de fin :");
		panDateFin.add(dateFinLabel);
		panDateFin.add(dateFin);
		panDate.add(panDateFin);
		
		JPanel control = new JPanel();
		JButton okBouton = new JButton("OK");
		okBouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				if(dateFin.isValid() && dateDebut.isValid()){
					dateFin.setBackground(Color.WHITE);
					dateDebut.setBackground(Color.WHITE);
					
					String stringValuesDebut[] = dateDebut.getText().split("/");
					int valuesDebut[] = new int[stringValuesDebut.length];
					boolean formatValidDebut=true;
					for (int i = 0; i < valuesDebut.length; i++) {
						if(!stringValuesDebut[i].trim().isEmpty()){
							valuesDebut[i] = Integer.parseInt(stringValuesDebut[i].trim());
						}else{
							formatValidDebut = false;
						}
					}
					
					String stringValuesFin[] = dateFin.getText().split("/");
					int valuesFin[] = new int[stringValuesFin.length];
					boolean formatValidFin=true;
					for (int i = 0; i < valuesFin.length; i++) {
						if(!stringValuesFin[i].trim().isEmpty()){
							valuesFin[i] = Integer.parseInt(stringValuesFin[i].trim());
						}else{
							formatValidFin = false;
						}
					}
					boolean canPass = true;
					if(!(valuesDebut[0]<32) || !(valuesDebut[1]<13) || !(valuesDebut[2]>1970) || !formatValidDebut){
						dateDebut.setBackground(Color.PINK);
						canPass=false;
					}else{
						dateDebut.setBackground(Color.WHITE);
					}
					if(!(valuesFin[0]<32) || !(valuesFin[1]<13) || !(valuesFin[2]>1970) || !formatValidFin){
						dateFin.setBackground(Color.PINK);
						canPass=false;
					}else{
						dateFin.setBackground(Color.WHITE);
					}
					if(canPass){
						info[0] = new GregorianCalendar(valuesDebut[2], valuesDebut[1]-1, valuesDebut[0]);//YYYY MM-1 DD
						info[1]= new GregorianCalendar(valuesFin[2], valuesFin[1]-1, valuesFin[0]);//YYYY MM-1 DD
						if(info[0].after(info[1])){
							dateFin.setBackground(Color.PINK);
							dateDebut.setBackground(Color.PINK);
						}else{
							dateFin.setBackground(Color.WHITE);
							dateDebut.setBackground(Color.WHITE);
							setVisible(false);
						}
					}
					
				}else{
					if(!dateFin.isValid()){
						dateFin.setBackground(Color.PINK);
					}
					else{
						dateFin.setBackground(Color.WHITE);
					}
					if(!dateDebut.isValid()){
						dateDebut.setBackground(Color.PINK);
					}
					else{
						dateDebut.setBackground(Color.WHITE);
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
		this.getContentPane().add(control, BorderLayout.SOUTH);
		this.getContentPane().add(content, BorderLayout.CENTER);
	}
}
