package test;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main extends JFrame {
	private static final long serialVersionUID = 1L;

	public Main() {
		EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }
		        
		        JFrame frame = new JFrame("Matrix!");
		        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        frame.add(new Program());		        
		        frame.pack();
		        frame.setLocationRelativeTo(null);
		        frame.setResizable(false);
		        frame.setVisible(true);
            }
		});
	}
	
	public static void main(String[] args) {
		new Main();
	} 
}