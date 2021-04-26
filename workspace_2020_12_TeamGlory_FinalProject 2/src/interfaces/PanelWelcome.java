package interfaces;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PanelWelcome extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelWelcome(JPanel panelBottom) {
		JLabel lblWelcome = new JLabel("Welcome to DayCare System");
		lblWelcome.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblWelcome, "welcome");
	}

}
