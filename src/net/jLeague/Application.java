package net.jLeague;

import javax.swing.JFrame;
import javax.swing.UIManager;

import net.jLeague.gui.ApplicationGUI;

public class Application {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");
			ApplicationGUI app = new ApplicationGUI();
			app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			app.setLocationRelativeTo(null);
			app.setResizable(false);
			app.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
