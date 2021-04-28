package interfaces;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import controller.StudentController;
import models.Classroom;
import models.Group;
import models.School;
import models.Student;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class PanelAlterStudent extends JPanel {
//	private JTextField txtName;
//	private JTextField txtAge;
//	private JTextField txtGrade;
	
	private JTextField txtName;
	private JTextField txtAge;
	private JTextField txtGrade;
	private JTextField txtPolio;
	private JTextField txtDTap;
	private JTextField txtHepatitisB;
	private JTextField txtHib;

	/**
	 * Create the panel.
	 */
	public PanelAlterStudent(JPanel panelBottom, Student s, Classroom c,Group g) {
		setLayout(null);

//		JLabel lblName = new JLabel("Name:");
//		lblName.setFont(new Font("Times New Roman", Font.PLAIN, 15));
//		lblName.setBounds(125, 73, 80, 22);
//		add(lblName);
//
//		txtName = new JTextField();
//		txtName.setEnabled(false);
//		txtName.setEditable(false);
//		txtName.setColumns(10);
//		txtName.setBounds(250, 74, 202, 21);
//		txtName.setText(s.getName());
//		add(txtName);
//
//		JLabel lblAge = new JLabel("Age:");
//		lblAge.setFont(new Font("Times New Roman", Font.PLAIN, 15));
//		lblAge.setBounds(125, 150, 80, 22);
//		add(lblAge);
//
//		txtAge = new JTextField();
//		txtAge.setColumns(10);
//		txtAge.setBounds(250, 151, 202, 21);
//		txtAge.setText(s.getAge() + "");
//		add(txtAge);
//
//		JLabel lblGrade = new JLabel("Grade:");
//		lblGrade.setFont(new Font("Times New Roman", Font.PLAIN, 15));
//		lblGrade.setBounds(125, 225, 80, 22);
//		add(lblGrade);
//
//		txtGrade = new JTextField();
//		txtGrade.setColumns(10);
//		txtGrade.setBounds(250, 226, 202, 21);
//		txtGrade.setText(s.getGrade() + "");
//		add(txtGrade);
//
//		JLabel lblHead = new JLabel("Add A Student");
//		lblHead.setFont(new Font("Times New Roman", Font.PLAIN, 20));
//		lblHead.setHorizontalAlignment(SwingConstants.CENTER);
//		lblHead.setBounds(189, 24, 194, 33);
//		add(lblHead);
//
//		JButton btnAlter = new JButton("Re-register");
//		btnAlter.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				btnAlterAction(panelBottom, s, c,g);
//			}
//		});
//		btnAlter.setFont(new Font("Times New Roman", Font.PLAIN, 15));
//		btnAlter.setBounds(233, 313, 97, 23);
//		add(btnAlter);
//
//		JButton btnBack = new JButton("back");
//		btnBack.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				btnBackAction(panelBottom);
//			}
//		});
//		btnBack.setFont(new Font("Times New Roman", Font.PLAIN, 15));
//		btnBack.setBounds(10, 10, 80, 23);
//		add(btnBack);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblName.setBounds(79, 150, 80, 22);
		add(lblName);

		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(140, 150, 97, 21);
		add(txtName);

		JLabel lblAge = new JLabel("Age:");
		lblAge.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblAge.setBounds(79, 229, 80, 22);
		add(lblAge);

		txtAge = new JTextField();
		txtAge.setColumns(10);
		txtAge.setBounds(140, 229, 97, 21);
		add(txtAge);

		JLabel lblGrade = new JLabel("Grade:");
		lblGrade.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblGrade.setBounds(79, 314, 80, 22);
		add(lblGrade);

		txtGrade = new JTextField();
		txtGrade.setColumns(10);
		txtGrade.setBounds(140, 314, 97, 21);
		add(txtGrade);

		JLabel lblHead = new JLabel("Student Re-registration");
		lblHead.setEnabled(false);
		lblHead.setForeground(new Color(128, 0, 128));
		lblHead.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblHead.setHorizontalAlignment(SwingConstants.CENTER);
		lblHead.setBounds(223, 24, 194, 33);
		add(lblHead);

		JButton btnAlter = new JButton("Re-register");
		btnAlter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAlterAction(panelBottom, s, c,g);
			}
		});
		btnAlter.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnAlter.setBounds(230, 407, 187, 23);
		add(btnAlter);

		JButton btnBack = new JButton("back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnBackAction(panelBottom);
			}
		});
		btnBack.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnBack.setBounds(10, 10, 80, 23);
		add(btnBack);
		
		JLabel lblVaccine = new JLabel("How many of those vaccines have you done?");
		lblVaccine.setForeground(new Color(0, 0, 0));
		lblVaccine.setFont(new Font("PT Serif Caption", Font.PLAIN, 12));
		lblVaccine.setBounds(326, 81, 305, 16);
		add(lblVaccine);
		
		JLabel lblDTap = new JLabel("DTap:");
		lblDTap.setBounds(349, 132, 80, 16);
		add(lblDTap);
		
		JLabel lblHepatitisB = new JLabel("HepatitisB:");
		lblHepatitisB.setBounds(349, 172, 80, 16);
		add(lblHepatitisB);
		
		JLabel lblHib = new JLabel("Hib:");
		lblHib.setBounds(349, 211, 80, 16);
		add(lblHib);
		
		JLabel lblMMR = new JLabel("MMR:");
		lblMMR.setBounds(349, 295, 80, 16);
		add(lblMMR);
		
		JLabel lblPolio = new JLabel("Polio:");
		lblPolio.setBounds(349, 255, 80, 16);
		add(lblPolio);
		
		JLabel lblVaricella = new JLabel("Varicella:");
		lblVaricella.setBounds(349, 336, 80, 16);
		add(lblVaricella);
		
		JCheckBox chckbxMMR = new JCheckBox("done (only one needed)");
		chckbxMMR.setBounds(431, 291, 200, 23);
		add(chckbxMMR);
		
		JCheckBox chckbxVaricella = new JCheckBox("done (only one needed)");
		chckbxVaricella.setBounds(431, 332, 200, 23);
		add(chckbxVaricella);
		
		txtPolio = new JTextField();
		txtPolio.setBounds(441, 250, 69, 26);
		add(txtPolio);
		txtPolio.setColumns(10);
		
		txtDTap = new JTextField();
		txtDTap.setColumns(10);
		txtDTap.setBounds(441, 127, 69, 26);
		add(txtDTap);
		
		txtHepatitisB = new JTextField();
		txtHepatitisB.setColumns(10);
		txtHepatitisB.setBounds(441, 167, 69, 26);
		add(txtHepatitisB);
		
		txtHib = new JTextField();
		txtHib.setColumns(10);
		txtHib.setBounds(441, 206, 69, 26);
		add(txtHib);
		
		JLabel lblStudentInfo = new JLabel("Student Info");
		lblStudentInfo.setFont(new Font("PT Serif Caption", Font.PLAIN, 12));
		lblStudentInfo.setBounds(125, 81, 112, 16);
		add(lblStudentInfo);

	}

	public void populateCB(JComboBox<Classroom> cbClassroom) {
		for (Classroom c : School.getInstance().getClassrooms()) {
			cbClassroom.addItem(c);
		}
	}

	private void btnAlterAction(JPanel panelBottom, Student s, Classroom classroom,Group group) {
		String name = txtName.getText();
		int age = Integer.parseInt(txtAge.getText());
		int grade = Integer.parseInt(txtGrade.getText());
		if (age < 6) {
			JOptionPane.showMessageDialog(null, "We Don't Accept Children Younger Than 6 Months", "WARNING",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		if (age >= classroom.getMinAge() && age <= classroom.getMaxAge()) {
			try {
				StudentController.UpdateStudentInformation(s, age, grade,hib,dTap,pollio,hepatitisB,mMR,varicella);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
		ArrayList<Classroom> cs = new ArrayList<Classroom>();
		for (Classroom c : School.getInstance().getClassrooms()) {
			if (StudentController.checkClassroom(age, c))
				cs.add(c);
		}
		Classroom c;
		if (cs.size() <= 0) {
			try {
				cs.add(StudentController.addSuitableClass(age));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
		}
		Classroom[] classes = new Classroom[cs.size()];
		for (int i = 0; i < cs.size(); i++) {
			classes[i] = cs.get(i);
		}
		c = (Classroom) JOptionPane.showInputDialog(null, "Please Choose Your New Classroom:\n", "Classroom",
				JOptionPane.PLAIN_MESSAGE, null, classes, classes[0]);
		if (c == null)
			return;
		try {
			StudentController.UpdateStudentInformation(s, age, grade,hib,dTap,pollio,hepatitisB,mMR,varicella,c,group);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		}
		panelBottom.remove(this);
		Component[] ca = panelBottom.getComponents();
		PanelViewStudent panelViewStudent = (PanelViewStudent) ca[ca.length - 1];
		panelViewStudent.populateTable();
		CardLayout card = (CardLayout) panelBottom.getLayout();
		card.previous(panelBottom);
	}

	private void btnBackAction(JPanel panelBottom) {
		panelBottom.remove(this);
		CardLayout card = (CardLayout) panelBottom.getLayout();
		card.previous(panelBottom);
	}
}
