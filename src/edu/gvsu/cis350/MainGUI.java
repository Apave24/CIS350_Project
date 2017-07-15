package edu.gvsu.cis350;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.TmdbPeople;
import info.movito.themoviedbapi.model.people.Person;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextPane;

import java.awt.Font;
import java.util.Random;

import javax.swing.UIManager;

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
		
		TmdbApi tmdbApi 
			= new TmdbApi("ee5a0a6208f35c4a8010636efd3f5d9b");
		TmdbPeople tmdbPeople = tmdbApi.getPeople();
		TmdbMovies tmdbMovies = tmdbApi.getMovies(); 
	
		Person primaryActor = TmDBModel.getPrimaryActor(tmdbPeople);
		Person relatedActor = TmDBModel.getRelatedActor(tmdbPeople,tmdbMovies, primaryActor);
		Person actor;
		
		Random randomIndex = new Random();
		final int correctAnswer = randomIndex.nextInt(4);
		
		frame = new JFrame();
		frame.setBounds(100, 100, 620, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		if (correctAnswer == 0) {
			actor = relatedActor;
		} else {
			actor = TmDBModel.getIncorrectAnswers(
					tmdbPeople, primaryActor);
		}
		JButton btnOne = new JButton(actor.getName());
		btnOne.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		btnOne.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				if (correctAnswer == 0) {
					JOptionPane.showMessageDialog(null, "Correct Answer", "", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Incorrect Answer", "", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnOne.setBounds(20, 175, 100, 50);
		panel.add(btnOne);
		
		if (correctAnswer == 1) {
			actor = relatedActor;
		} else {
			actor = TmDBModel.getIncorrectAnswers(
					tmdbPeople, primaryActor);
		}
		JButton btnTwo = new JButton(actor.getName());
		btnTwo.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		btnTwo.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				if (correctAnswer == 1) {
					JOptionPane.showMessageDialog(null, "Correct Answer", "", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Incorrect Answer", "", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnTwo.setBounds(140, 175, 100, 50);
		panel.add(btnTwo);
		
		if (correctAnswer == 2) {
			actor = relatedActor;
		} else {
			actor = TmDBModel.getIncorrectAnswers(
					tmdbPeople, primaryActor);
		}
		JButton btnThree = new JButton(actor.getName());
		btnThree.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		btnThree.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				if (correctAnswer == 2) {
					JOptionPane.showMessageDialog(null, "Correct Answer", "", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Incorrect Answer", "", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnThree.setBounds(260, 175, 100, 50);
		panel.add(btnThree);
		
		if (correctAnswer == 3) {
			actor = relatedActor;
		} else {
			actor = TmDBModel.getIncorrectAnswers(
					tmdbPeople, primaryActor);
		}
		JButton btnFour = new JButton(actor.getName());
		btnFour.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		btnFour.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				if (correctAnswer == 3) {
					JOptionPane.showMessageDialog(null, "Correct Answer", "", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Incorrect Answer", "", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnFour.setBounds(380, 175, 100, 50);
		panel.add(btnFour);
		
		if (correctAnswer == 4) {
			actor = relatedActor;
		} else {
			actor = TmDBModel.getIncorrectAnswers(
					tmdbPeople, primaryActor);
		}
		JButton btnFive = new JButton(actor.getName());
		btnFive.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		btnFive.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				if (correctAnswer == 4) {
					JOptionPane.showMessageDialog(null, "Correct Answer", "", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Incorrect Answer", "", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnFive.setBounds(500, 175, 100, 50);
		panel.add(btnFive);
		
		JTextPane textPane3 = new JTextPane();
		textPane3.setText(primaryActor.getName());
		textPane3.setBackground(UIManager.getColor("Button.background"));
		textPane3.setBounds(310, 31, 259, 26);
		panel.add(textPane3);
		
		JTextPane txtpnWhichActorListed = new JTextPane();
		txtpnWhichActorListed.setText(
				"Which actor listed below is in a movie with:");
		txtpnWhichActorListed.setBackground(UIManager.getColor("Button.background"));
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
