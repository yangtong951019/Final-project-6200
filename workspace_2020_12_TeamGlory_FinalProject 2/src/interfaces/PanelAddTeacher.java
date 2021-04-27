package interfaces;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.StudentController;
import controller.TeacherController;
import models.Classroom;
import models.School;

public class PanelAddTeacher extends JPanel {
	private JTextField txtName;
	private JTextField txtAge;
	private JTextField txtCredit;

	/**
	 * Create the panel.
	 */
	public PanelAddTeacher(JPanel panelBottom) {
		setLayout(null);

		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblName.setBounds(125, 73, 80, 22);
		add(lblName);

		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(250, 74, 202, 21);
		add(txtName);

		JLabel lblAge = new JLabel("Age:");
		lblAge.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblAge.setBounds(125, 150, 80, 22);
		add(lblAge);

		txtAge = new JTextField();
		txtAge.setColumns(10);
		txtAge.setBounds(250, 151, 202, 21);
		add(txtAge);

		JLabel lbCredit = new JLabel("Credit:");
		lbCredit.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lbCredit.setBounds(125, 225, 80, 22);
		add(lbCredit);

		txtCredit = new JTextField();
		txtCredit.setColumns(10);
		txtCredit.setBounds(250, 226, 202, 21);
		add(txtCredit);

		JLabel lblHead = new JLabel("Add A Teacher");
		lblHead.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblHead.setHorizontalAlignment(SwingConstants.CENTER);
		lblHead.setBounds(189, 24, 194, 33);
		add(lblHead);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnaddAction(panelBottom);
			}
		});
		btnAdd.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnAdd.setBounds(233, 313, 97, 23);
		add(btnAdd);

		JButton btnBack = new JButton("back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnBackAction(panelBottom);
			}
		});
		btnBack.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnBack.setBounds(10, 10, 80, 23);
		add(btnBack);
	}


	private void btnaddAction(JPanel panelBottom) {
		String name = txtName.getText();
		int age = Integer.parseInt(txtAge.getText());
		int credit = Integer.parseInt(txtCredit.getText());
		try {
			TeacherController.addTeacher(name, age, credit);;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		panelBottom.remove(this);
		Component[] ca = panelBottom.getComponents();
		PanelViewTeacher panelViewTeacher = (PanelViewTeacher) ca[ca.length-1];
		panelViewTeacher.populateTable();
		CardLayout card = (CardLayout) panelBottom.getLayout();
		card.previous(panelBottom);
		
	}
	private void btnBackAction(JPanel panelBottom) {
		panelBottom.remove(this);
		CardLayout card = (CardLayout) panelBottom.getLayout();
		card.previous(panelBottom);
	}

}
