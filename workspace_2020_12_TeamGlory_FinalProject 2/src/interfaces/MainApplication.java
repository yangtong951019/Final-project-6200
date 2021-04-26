package interfaces;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JPanel;

import java.sql.SQLException;
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
		
		
		
		JButton btnNewButton_2 = new JButton("New button");
		btnNewButton_2.setBounds(588, 41, 97, 23);
		panelTop.add(btnNewButton_2);
		
		JPanel panelBottom = new JPanel();
		splitPane.setRightComponent(panelBottom);
		CardLayout card=new CardLayout(0, 0);
		panelBottom.setLayout(card);
		
		PanelWelcome panelWelcome=new PanelWelcome(panelBottom);
		PanelViewStudent panelViewStudent=new PanelViewStudent(panelBottom);
		PanelViewTeacher panelViewTeacher = new PanelViewTeacher(panelBottom);
		panelBottom.add(panelWelcome,"panelWelcome");
		panelBottom.add(panelViewStudent,"panelViewStudent");
		panelBottom.add(panelViewTeacher,"panelViewTeacher");
		
		JButton btnViewStudent = new JButton("View Students");
		btnViewStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(panelBottom,"panelViewStudent");
			}
		});
		btnViewStudent.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnViewStudent.setBounds(69, 18, 140, 66);
		panelTop.add(btnViewStudent);
		
		JButton btnViewTeacher = new JButton("View Teacher");
		btnViewTeacher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(panelBottom, "panelViewTeacher");
			}
		});
		btnViewTeacher.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnViewTeacher.setBounds(270, 18, 140, 66);
		panelTop.add(btnViewTeacher);
	}
}
