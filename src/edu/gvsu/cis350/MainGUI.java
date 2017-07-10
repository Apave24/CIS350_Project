package edu.gvsu.cis350;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class MainGUI {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI window = new MainGUI();
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
	public MainGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnOne = new JButton("ONE");
		btnOne.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
			}
		});
		btnOne.setBounds(5, 189, 80, 29);
		panel.add(btnOne);
		
		JButton btnTwo = new JButton("TWO");
		btnTwo.setBounds(95, 189, 80, 29);
		panel.add(btnTwo);
		
		JButton btnThree = new JButton("THREE");
		btnThree.setBounds(185, 189, 80, 29);
		panel.add(btnThree);
		
		JButton btnFour = new JButton("FOUR");
		btnFour.setBounds(275, 189, 80, 29);
		panel.add(btnFour);
		
		JButton btnFive = new JButton("FIVE");
		btnFive.setBounds(365, 189, 80, 29);
		panel.add(btnFive);
		
		textField = new JTextField();
		textField.setBounds(5, 151, 80, 26);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(95, 151, 80, 26);
		panel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(185, 151, 80, 26);
		panel.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(275, 151, 80, 26);
		panel.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(365, 151, 80, 26);
		panel.add(textField_4);
		
		JSplitPane splitPane = new JSplitPane();
		frame.getContentPane().add(splitPane, BorderLayout.SOUTH);
		
		JButton btnQuit = new JButton("Quit");
		splitPane.setLeftComponent(btnQuit);
		
		JButton btnPlay = new JButton("Play");
		splitPane.setRightComponent(btnPlay);
	}
}
