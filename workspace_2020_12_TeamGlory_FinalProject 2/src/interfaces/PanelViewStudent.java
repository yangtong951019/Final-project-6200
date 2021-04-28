package interfaces;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dataGenerator.CSVreader;
import models.Classroom;
import models.Group;
import models.School;
import models.Student;
import models.Teacher;

import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;

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
		tableStudent.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null, null, null }, },
				new String[] { "StudentID", "Name", "Age", "Grade", "Registration Day", "Classroom", "Group" }));
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
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileChoose(new JButton());
			}
		});
		panelRight.setLayout(new GridLayout(5, 1, 0, 0));
		panelRight.add(btnAddStudent);
		panelRight.add(btnNewButton);

		JButton btnReRgister = new JButton("Re-register");
		btnReRgister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int SelectedRow = tableStudent.getSelectedRow();
				if (SelectedRow == -1) {
					JOptionPane.showMessageDialog(null, "Please select a row!", "WARNING", JOptionPane.WARNING_MESSAGE);
					return;
				} else {
					Student s = (Student) tableStudent.getValueAt(SelectedRow, 0);
					Classroom c = (Classroom) tableStudent.getValueAt(SelectedRow, 5);
					Group g = (Group) tableStudent.getValueAt(SelectedRow, 6);
					PanelAlterStudent panelAlterStudent = new PanelAlterStudent(panelBottom, s, c,g);
					panelBottom.add(panelAlterStudent);
					CardLayout card = (CardLayout) panelBottom.getLayout();
					card.next(panelBottom);
				}
			}
		});
		panelRight.add(btnReRgister);
		
		JButton btnNewButton_1 = new JButton("Vaccine situation");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int SelectedRow = tableStudent.getSelectedRow();
				if (SelectedRow == -1) {
					JOptionPane.showMessageDialog(null, "Please select a row!", "WARNING", JOptionPane.WARNING_MESSAGE);
					return;
				} else {
					Student s = (Student) tableStudent.getValueAt(SelectedRow, 0);
					PanelCheckVaccine panelCheckVaccine = new PanelCheckVaccine(panelBottom, s);
					panelBottom.add(panelCheckVaccine);
					CardLayout card = (CardLayout) panelBottom.getLayout();
					card.next(panelBottom);
				}
			}
		});
		panelRight.add(btnNewButton_1);
	}

	public void populateTable() {
		School school = School.getInstance();
		DefaultTableModel model = (DefaultTableModel) tableStudent.getModel();
		model.setRowCount(0);
		for (Classroom c : school.getClassrooms()) {
			for (Group g : c.getGroups()) {
				for (Student s : g.getStudents()) {
					Object[] row = new Object[7];
					row[0] = s;
					row[1] = s.getName();
					row[2] = s.getAge();
					row[3] = s.getGrade();
					row[4] = s.getRegistrationDay();
					row[5] = c;
					row[6] = g;
					model.addRow(row);
				}
			}
		}
	}

	private void fileChoose(JButton developer) {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("csv", "csv");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(developer);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File csv = chooser.getSelectedFile();
			if (csv == null) {
				return;
			}
			try {
				FileInputStream input = new FileInputStream(csv);
				CSVreader.readCSV(csv);
				input.close();
			} catch (IOException | SQLException e) {
				JOptionPane.showMessageDialog(null, "Upload Error", "Warning", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "Upload Successfull", "Information", JOptionPane.INFORMATION_MESSAGE);
			populateTable();
		}
	}
}
