package interfaces;

import javax.swing.JPanel;

import models.Student;

import javax.swing.JButton;

import java.awt.CardLayout;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelCheckVaccine extends JPanel {
	public PanelCheckVaccine(JPanel panelBottom, Student s) {
		setLayout(null);

		JButton btnBack = new JButton("back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnBackAction(panelBottom);
			}
		});
		btnBack.setBounds(10, 10, 93, 27);
		btnBack.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		add(btnBack);

		JLabel lblHead = new JLabel("New label");
		lblHead.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblHead.setHorizontalAlignment(SwingConstants.CENTER);
		lblHead.setText("Student " + s.getName() + "'s vaccine situation is as follows:");
		lblHead.setBounds(115, 56, 359, 46);
		add(lblHead);

		JLabel lblHib = new JLabel("New label");
		lblHib.setHorizontalAlignment(SwingConstants.CENTER);
		if (s.getHib() < 1) {
			int a = 1 - s.getHib();
			lblHib.setText("Still have " + a + " shots of Hib to be given");
		} else
			lblHib.setText("Hib is done");
		lblHib.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblHib.setBounds(125, 112, 349, 15);
		add(lblHib);

		JLabel lblDTaP = new JLabel("New label");
		lblDTaP.setHorizontalAlignment(SwingConstants.CENTER);
		lblDTaP.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblDTaP.setBounds(125, 153, 349, 15);
		if (s.getDTap() < 4) {
			int a = 4 - s.getDTap();
			lblDTaP.setText("Still have " + a + " shots of DTaP to be given");
		} else
			lblDTaP.setText("DTap is done");
		add(lblDTaP);

		JLabel lblPolio = new JLabel("New label");
		lblPolio.setHorizontalAlignment(SwingConstants.CENTER);
		lblPolio.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		if (s.getPollio() < 3) {
			int a = 3 - s.getPollio();
			lblPolio.setText("Still have " + a + " shots of Polio to be given");
		} else
			lblPolio.setText("DTap is done");
		lblPolio.setBounds(125, 192, 349, 15);
		add(lblPolio);

		JLabel lblHepatitisB = new JLabel("New label");
		lblHepatitisB.setHorizontalAlignment(SwingConstants.CENTER);
		lblHepatitisB.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblHepatitisB.setBounds(125, 235, 349, 15);
		if (s.getHepatitisB() < 3) {
			int a = 3 - s.getHepatitisB();
			lblHepatitisB.setText("Still have " + a + " shots of Hepatitis B to be given");
		} else
			lblHepatitisB.setText("Hepatitis B is done");
		add(lblHepatitisB);

		JLabel lblMMR = new JLabel("New label");
		lblMMR.setHorizontalAlignment(SwingConstants.CENTER);
		lblMMR.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblMMR.setBounds(125, 277, 349, 15);
		if (s.isMMR())
			lblMMR.setText("MMR is done");
		else if (s.getAge() < 12)
			lblMMR.setText("No need to inject MMR for now");
		else
			lblMMR.setText("Need to inject MMR");
		add(lblMMR);

		JLabel lblVaricella = new JLabel("New label");
		lblVaricella.setHorizontalAlignment(SwingConstants.CENTER);
		lblVaricella.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblVaricella.setBounds(125, 314, 349, 15);
		if (s.isVaricella())
			lblVaricella.setText("Varicella is done");
		else if (s.getAge() < 12)
			lblVaricella.setText("No need to inject Varicella for now");
		else
			lblVaricella.setText("Need to inject Varicella");
		add(lblVaricella);

	}

	private void btnBackAction(JPanel panelBottom) {
		panelBottom.remove(this);
		CardLayout card = (CardLayout) panelBottom.getLayout();
		card.previous(panelBottom);
	}
}
