package edu.gvsu.cis350;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

/**
 * @author evankiel
 *
 */
public class MainGUI {

	/**
	 * 
	 */
	private JFrame frame;
	
	/**
	 * @param args String[]
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
		btnTwo.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
			}
		});
		btnTwo.setBounds(95, 189, 80, 29);
		panel.add(btnTwo);
		
		JButton btnThree = new JButton("THREE");
		btnThree.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
			}
		});
		btnThree.setBounds(185, 189, 80, 29);
		panel.add(btnThree);
		
		JButton btnFour = new JButton("FOUR");
		btnFour.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
			}
		});
		btnFour.setBounds(275, 189, 80, 29);
		panel.add(btnFour);
		
		JButton btnFive = new JButton("FIVE");
		btnFive.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
			}
		});
		btnFive.setBounds(365, 189, 80, 29);
		panel.add(btnFive);
		
		JTextPane textField = new JTextPane();
		textField.setBounds(5, 151, 80, 26);
		panel.add(textField);
		
		JTextPane textField1 = new JTextPane();
		textField1.setBounds(95, 151, 80, 26);
		panel.add(textField1);
		
		JTextPane textField2 = new JTextPane();
		textField2.setBounds(185, 151, 80, 26);
		panel.add(textField2);
		
		JTextPane textField3 = new JTextPane();
		textField3.setBounds(275, 151, 80, 26);
		panel.add(textField3);
		
		JTextPane textField4 = new JTextPane();
		textField4.setBounds(365, 151, 80, 26);
		panel.add(textField4);
		
		JTextPane textPane3 = new JTextPane();
		textPane3.setBounds(310, 31, 80, 26);
		panel.add(textPane3);
		
		JTextPane txtpnWhichActorListed = new JTextPane();
		txtpnWhichActorListed.setText(
				"Which actor listed below is in a movie with:");
		txtpnWhichActorListed.setBounds(16, 31, 282, 16);
		panel.add(txtpnWhichActorListed);
		
		JSplitPane splitPane = new JSplitPane();
		frame.getContentPane().add(splitPane, BorderLayout.SOUTH);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
			}
		});
		splitPane.setLeftComponent(btnQuit);
		
		JButton btnPlay = new JButton("Play");
		splitPane.setRightComponent(btnPlay);
	}
}
