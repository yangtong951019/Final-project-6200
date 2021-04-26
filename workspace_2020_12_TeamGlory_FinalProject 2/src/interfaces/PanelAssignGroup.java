package interfaces;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import DAO.GroupDAO;
import controller.StudentController;
import models.Classroom;
import models.Group;
import models.School;
import models.Student;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;

public class PanelAssignGroup extends JPanel {
	private JTable table;
	private String Name;
	private int Age;
	private int Grade;

	/**
	 * Create the panel.
	 */
	public PanelAssignGroup(JPanel panelBottom, String Name, int Age, int Grade) {
		this.Name = Name;
		this.Age = Age;
		this.Grade = Grade;

		JButton btnBackButton = new JButton("Back");
		btnBackButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnBackButton.setVerticalAlignment(SwingConstants.TOP);
		btnBackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnBackAction(panelBottom);
			}
		});
		setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel("Chosse a Group for New Student");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		JPanel panelNorth = new JPanel();
		panelNorth.setLayout(new BorderLayout(0, 0));
		panelNorth.add(btnBackButton, BorderLayout.WEST);
		panelNorth.add(lblNewLabel, BorderLayout.CENTER);
		add(panelNorth, BorderLayout.NORTH);

		table = new JTable();
		table.setBorder(new EmptyBorder(10, 10, 0, 0));
		table.setModel(new DefaultTableModel(new Object[][] { { null, null, null }, },
				new String[] { "Class", "Group", "Remain Seats" }));
		JScrollPane panelTable = new JScrollPane(table);
		add(panelTable, BorderLayout.CENTER);

		JButton btnSubmitButton = new JButton("submit");
		btnSubmitButton.setVerticalAlignment(SwingConstants.BOTTOM);
		btnSubmitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSubmitAction(panelBottom);
			}
		});
		JPanel panelEast = new JPanel();
		panelEast.add(btnSubmitButton);
		add(panelEast, BorderLayout.EAST);
		populateTable();
	}

	public void populateTable() {
		School school = School.getInstance();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		for (Classroom c : school.getClassrooms()) {
			if (StudentController.checkClassroom(Age, c)) {
				for (Group g : c.getGroups()) {
					if (g.getStudents().size() < c.getGroupSize()) {
						Object[] row = new Object[3];
						row[0] = c;
						row[1] = g;
						row[2] = c.getGroupSize() - g.getStudents().size();
						model.addRow(row);
					}
				}
			}
		}
	}

	private void btnBackAction(JPanel panelBottom) {
		panelBottom.remove(this);
		CardLayout card = (CardLayout) panelBottom.getLayout();
		card.previous(panelBottom);
	}

	private void btnSubmitAction(JPanel panelBottom) {
		/*
		 * boolean flag = false; if(table.getRowCount()==0) { //table is empty , only
		 * contains the first line which is title flag =
		 * StudentController.addStudentWithNewGroup(Name, Age, Grade); }else { int
		 * SelectedRow = table.getSelectedRow(); if(SelectedRow == -1) { SelectedRow = 0
		 * ; }//If no row is selected , use the first row. Group g = (Group)
		 * table.getValueAt(SelectedRow, 1);
		 * 
		 * try { flag=StudentController.addStudent(Name, Age, Grade, g); } catch
		 * (SQLException e1) { // TODO Auto-generated catch block e1.printStackTrace();
		 * } } if(flag) { panelBottom.remove(this); Component[]
		 * ca=panelBottom.getComponents(); PanelViewStudent
		 * panelViewStudent=(PanelViewStudent) ca[ca.length-2];
		 * panelViewStudent.populateTable(); panelBottom.remove(ca[ca.length-1]);
		 * CardLayout card=(CardLayout) panelBottom.getLayout();
		 * card.previous(panelBottom);
		 * 
		 * }
		 */
	}

}
