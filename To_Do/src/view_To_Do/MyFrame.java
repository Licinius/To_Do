package view_To_Do;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class MyFrame extends JFrame{
	
	private JMenu[] menuHorizontal = new JMenu[2];
	
	public MyFrame(){
		//this.matriceTree = matriceTree;
		setSize(800, 800); 
		setTitle("Ma liste"); 
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
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
			
		MyFrame f = new MyFrame();
		f.setVisible(true);
	}
}
