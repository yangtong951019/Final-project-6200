package interfaces;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import DAO.GroupDAO;
import controller.StudentController;
import controller.TeacherController;
import models.Classroom;
import models.Group;
import models.School;
import models.Student;
import models.Teacher;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;

public class PanelAssignGroup extends JPanel {
	private JTable table;
	private Teacher t;

	/**
	 * Create the panel.
	 */
	public PanelAssignGroup(JPanel panelBottom, Teacher t) {
		this.t = t;

		JButton btnBackButton = new JButton("Back");
		btnBackButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnBackButton.setVerticalAlignment(SwingConstants.TOP);
		btnBackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnBackAction(panelBottom);
			}
		});
		setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel("Chosse a Group for Teacher");
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
		table.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null }, },
				new String[] { "Group", "Min Age", "Max Age", "Class" }));
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
			for (Group g : c.getGroups()) {
				if (g.getTeacher() == null) {
					Object[] row = new Object[4];
					row[0] = g;
					row[1] = c.getMinAge();
					if (c.getMaxAge() != Integer.MAX_VALUE)
						row[2] = c.getMaxAge();
					row[3] = c.getClassroomID();
					model.addRow(row);
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
		int SelectedRow = table.getSelectedRow();
		if (SelectedRow == -1) {
			JOptionPane.showMessageDialog(null, "Please select a row!", "WARNING", JOptionPane.WARNING_MESSAGE);
			return;
		} else {
			Group g = (Group) table.getValueAt(SelectedRow, 0);
			try {
				TeacherController.AssignGroupForTeacher(t, g);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		panelBottom.remove(this);
		Component[] ca = panelBottom.getComponents();
		PanelViewTeacher panelViewTeacher = (PanelViewTeacher) ca[ca.length - 1];
		panelViewTeacher.populateTable();
		CardLayout card = (CardLayout) panelBottom.getLayout();
		card.previous(panelBottom);
	}

}
