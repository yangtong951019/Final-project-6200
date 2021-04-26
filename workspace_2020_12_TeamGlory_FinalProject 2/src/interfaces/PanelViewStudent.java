package interfaces;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import models.Classroom;
import models.Group;
import models.School;
import models.Student;

import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelViewStudent extends JPanel {
	/**
	 * 
	 */
	private JTable tableStudent;

	/**
	 * Create the panel.
	 */
	public PanelViewStudent(JPanel panelBottom) {
		setLayout(new BorderLayout(0, 0));

		JLabel lblStudentTable = new JLabel("Student Table");
		lblStudentTable.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblStudentTable.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblStudentTable, BorderLayout.NORTH);

		tableStudent = new JTable();
		tableStudent.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null }, },
				new String[] { "StudentID", "Name", "Age", "Grade", "Classroom" }));
		JScrollPane panelTable = new JScrollPane(tableStudent);
		add(panelTable, BorderLayout.CENTER);
		populateTable();
		JPanel panelRight = new JPanel();
		add(panelRight, BorderLayout.EAST);

		JButton btnAddStudent = new JButton("Add A Student");
		btnAddStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelAddStudent panelAddStudent = new PanelAddStudent(panelBottom);
				panelBottom.add(panelAddStudent, "panelAddStudent");
				CardLayout card = (CardLayout) panelBottom.getLayout();
				card.show(panelBottom, "panelAddStudent");
			}
		});
		JButton btnNewButton = new JButton("Add Students By CSV");
		panelRight.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panelRight.add(btnAddStudent);
		panelRight.add(btnNewButton);
	}

	public void populateTable() {
		School school = School.getInstance();
		DefaultTableModel model = (DefaultTableModel) tableStudent.getModel();
		model.setRowCount(0);
		for (Classroom c : school.getClassrooms()) {
			for (Group g : c.getGroups()) {
				for (Student s : g.getStudents()) {
					Object[] row = new Object[5];
					row[0] = s;
					row[1] = s.getName();
					row[2] = s.getAge();
					row[3] = s.getGrade();
					row[4] = c.getClassroomID();
					model.addRow(row);
				}
			}
		}
	}
}
