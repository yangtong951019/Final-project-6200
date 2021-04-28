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
import java.awt.Component;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class PanelAlterStudent extends JPanel {
	private JTextField txtName;
	private JTextField txtAge;
	private JTextField txtGrade;

	/**
	 * Create the panel.
	 */
	public PanelAlterStudent(JPanel panelBottom, Student s, Classroom c,Group g) {
		setLayout(null);

		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblName.setBounds(125, 73, 80, 22);
		add(lblName);

		txtName = new JTextField();
		txtName.setEnabled(false);
		txtName.setEditable(false);
		txtName.setColumns(10);
		txtName.setBounds(250, 74, 202, 21);
		txtName.setText(s.getName());
		add(txtName);

		JLabel lblAge = new JLabel("Age:");
		lblAge.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblAge.setBounds(125, 150, 80, 22);
		add(lblAge);

		txtAge = new JTextField();
		txtAge.setColumns(10);
		txtAge.setBounds(250, 151, 202, 21);
		txtAge.setText(s.getAge() + "");
		add(txtAge);

		JLabel lblGrade = new JLabel("Grade:");
		lblGrade.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblGrade.setBounds(125, 225, 80, 22);
		add(lblGrade);

		txtGrade = new JTextField();
		txtGrade.setColumns(10);
		txtGrade.setBounds(250, 226, 202, 21);
		txtGrade.setText(s.getGrade() + "");
		add(txtGrade);

		JLabel lblHead = new JLabel("Add A Student");
		lblHead.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblHead.setHorizontalAlignment(SwingConstants.CENTER);
		lblHead.setBounds(189, 24, 194, 33);
		add(lblHead);

		JButton btnAlter = new JButton("Re-register");
		btnAlter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAlterAction(panelBottom, s, c,g);
			}
		});
		btnAlter.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnAlter.setBounds(233, 313, 97, 23);
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
