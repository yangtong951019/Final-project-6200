package interfaces;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DAO.StudentDAO;
import models.School;
import models.Classroom;
import models.Group;
import models.Student;

import java.util.Date;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.util.Date;

public class PanelOverdueList extends JPanel {
	private JTable table;
	private Date date;

	/**
	 * Create the panel.
	 */
	public PanelOverdueList(JPanel panelBottom) {
		date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyy-MM-dd");  
		String dateString = sdf.format(date);
		setLayout(null);
		JLabel lblNewLabel = new JLabel("Overdue Registrations List");
		lblNewLabel.setBounds(164, 1, 217, 24);
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNewLabel);
		
		table = new JTable();
		table.setBounds(1, 27, 450, 16);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
			},
			new String[] {
				"StudentID", "Name", "Last Registration Date"
			}
		));
		table.getColumnModel().getColumn(2).setPreferredWidth(147);
		
		JLabel lbPrsentDate = new JLabel("On");
		lbPrsentDate.setBounds(357, 35, 126, 15);
		add(lbPrsentDate);
		lbPrsentDate.setText("by "+dateString);
		add(table,BorderLayout.CENTER);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(67, 60, 416, 459);
		add(scrollPane);
		populateTable();
	}
	private void populateTable() {
		System.out.println("populateOverdueList");
		School school = School.getInstance();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		for(Classroom c:school.getClassrooms()) {
			for(Group g: c.getGroups()) {
				for(Student s:g.getStudents()) {
					if(date.getTime()-s.getRegistrationDay().getTime()>(1000*60*60*24*365)) {
						Object[] row = new Object[3];
						row[0] = s;
						row[1] = s.getName();				        
						SimpleDateFormat sdf = new SimpleDateFormat();
						sdf.applyPattern("yyyy-MM-dd"); 
						row[2] = sdf.format(s.getRegistrationDay());
						model.addRow(row);
					}
				}
			}
		}
	}
}
