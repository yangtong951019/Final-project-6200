package interfaces;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import DAO.TeacherDAO;
import models.Classroom;
import models.Group;
import models.School;
import models.Student;
import models.Teacher;

public class PanelViewTeacher extends JPanel {
	private JTable TeacherTable;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public PanelViewTeacher(JPanel panelBottom) {
	setLayout(new BorderLayout(0, 0));

		JLabel lbTeachersTable = new JLabel("Teachers Table");
		lbTeachersTable.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbTeachersTable.setHorizontalAlignment(SwingConstants.CENTER);
		add(lbTeachersTable, BorderLayout.NORTH);

		JPanel panelRight = new JPanel();
		add(panelRight, BorderLayout.EAST);

		JButton btnAddTeacher = new JButton("Add A Teacher");
		btnAddTeacher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelAddTeacher panelAddTeacher = new PanelAddTeacher(panelBottom);
				panelBottom.add(panelAddTeacher, "panelAddTeacher");
				CardLayout card = (CardLayout) panelBottom.getLayout();
				card.show(panelBottom, "panelAddTeacher");
			}
		});
		JButton btnAssignGroup = new JButton("Assign for Group");
		btnAssignGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int SelectedRow = table.getSelectedRow();
				if(SelectedRow == -1) {
					JOptionPane.showMessageDialog(null, "Please select a row!", "WARNING",
							JOptionPane.WARNING_MESSAGE);
					return;
				}else {
					Teacher t = (Teacher)table.getValueAt(SelectedRow, 0);
					PanelAssignGroup panelAssignGroup = new PanelAssignGroup(panelBottom,t);
					panelBottom.add(panelAssignGroup, "panelAssignGroup");
					CardLayout card = (CardLayout) panelBottom.getLayout();
					card.show(panelBottom, "panelAssignGroup");
				}
				
			}
		});
		panelRight.add(btnAddTeacher);
		panelRight.add(btnAssignGroup);
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"TeacherID", "Name", "Age", "Credit", "GroupID"
			}
		));
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane, BorderLayout.CENTER);
		populateTable();
		
		

	}
	public void populateTable() {
		School school = School.getInstance();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
			for (Teacher t : school.getTeachers()) {
				Object[] row = new Object[5];
				row[0] = t;
				row[1] = t.getName();
				row[2] = t.getAge();
				row[3] = t.getCredits();
				row[4] = t.getGroupID();
				model.addRow(row);
		}		
	}

}
