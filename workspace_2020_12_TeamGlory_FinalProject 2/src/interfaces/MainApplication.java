package interfaces;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JPanel;

import java.sql.SQLException;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JSplitPane;

import dataGenerator.DataGenerator;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class MainApplication {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainApplication window = new MainApplication();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainApplication() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
			DataGenerator.Generate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		frame.getContentPane().add(splitPane);
		splitPane.setDividerLocation(100);
		
		JPanel panelTop = new JPanel();
		splitPane.setLeftComponent(panelTop);
		panelTop.setLayout(null);
		
		
		
		JButton btnOverdueRecord = new JButton("Overdue Registrations List");

		btnOverdueRecord.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnOverdueRecord.setBounds(529, 18, 216, 66);
		panelTop.add(btnOverdueRecord);
		
		JPanel panelBottom = new JPanel();
		splitPane.setRightComponent(panelBottom);
		CardLayout card=new CardLayout(0, 0);
		panelBottom.setLayout(card);
		
		PanelWelcome panelWelcome=new PanelWelcome(panelBottom);
		PanelViewStudent panelViewStudent=new PanelViewStudent(panelBottom);
		PanelViewTeacher panelViewTeacher = new PanelViewTeacher(panelBottom);
		PanelOverdueList panelOverdueList = new PanelOverdueList(panelBottom);
		panelBottom.add(panelWelcome,"panelWelcome");
	
		
		JButton btnViewStudent = new JButton("View Students");
		btnViewStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelBottom.removeAll();
				panelBottom.add(panelViewStudent,"panelViewStudent");
				card.next(panelBottom);
			}
		});
		btnViewStudent.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnViewStudent.setBounds(69, 18, 140, 66);
		panelTop.add(btnViewStudent);
		
		JButton btnViewTeacher = new JButton("View Teacher");
		btnViewTeacher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelBottom.removeAll();
				panelBottom.add(panelViewTeacher,"panelViewTeacher");
				card.next(panelBottom);
			}
		});
		btnViewTeacher.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnViewTeacher.setBounds(307, 18, 140, 66);
		panelTop.add(btnViewTeacher);
		
		btnOverdueRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelBottom.removeAll();
				panelBottom.add(panelOverdueList,"panelOverdueList");
				card.next(panelBottom);
			}
		});
	}
}
