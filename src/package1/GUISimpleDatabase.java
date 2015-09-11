package package1;

import javax.swing.JFrame;

import javax.swing.JPanel;
/**********************************************************************
 * CIS 163
 * @author bensonb and Jesse Roe
 * Creates an instance of the database panel.
 **********************************************************************/
public class GUISimpleDatabase {
	
	public static void main(String args[]){
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new SimpleDatabasePanel();
		frame.setResizable(false);
		frame.setSize(400,400);
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setVisible(true);
	} 
}
