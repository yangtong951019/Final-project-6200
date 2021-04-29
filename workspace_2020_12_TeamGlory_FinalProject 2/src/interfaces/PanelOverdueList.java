package interfaces;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
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
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.util.Date;
import javax.swing.JLayeredPane;
import java.awt.GridLayout;

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
		setLayout(new BorderLayout(0, 0));
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 1, 0, 0));
		JLabel lblNewLabel = new JLabel("Overdue Registrations List");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel);
		add(panel,BorderLayout.NORTH);
		table = new JTable();
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
		lbPrsentDate.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lbPrsentDate);
		lbPrsentDate.setText("by "+dateString);
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane,BorderLayout.CENTER);
		
		
		
		
		populateTable();
		
		
		
	}
	private void populateTable() {
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
