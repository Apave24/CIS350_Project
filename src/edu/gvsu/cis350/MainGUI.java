package edu.gvsu.cis350;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.TmdbPeople;
import info.movito.themoviedbapi.model.Artwork;
import info.movito.themoviedbapi.model.people.Person;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
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
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.UIManager;
import javax.swing.JLabel;
import java.awt.SystemColor;

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
	 * 
	 */
	private int correctAnswer;
	/**
	 * 
	 */
	private Person primaryActor;
	/**
	 * 
	 */
	private Person relatedActor;
	/**
	 * 
	 */
	private List<Person> actors = new ArrayList<Person>();
	/**
	 * 
	 */
	private List<Artwork> primaryActorImage;
	
	private JTextPane textPane4;
	private JTextPane textPane3;
	private JTextPane textPane2;
	private JTextPane textPane1;
	private JTextPane textPane;
	private ImageIcon icon;
	private JTextPane textPanePrimary;
	private JLabel lblNewLabel;
	
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
	 * @throws MalformedURLException 
	 */
	public MainGUI() throws MalformedURLException {
		initialize();
	}
	
	/**
	 * 
	 */
	public void update() {
		Random randomIndex = new Random();
		correctAnswer = randomIndex.nextInt(4);
		TmdbApi tmdbApi 
			= new TmdbApi("ee5a0a6208f35c4a8010636efd3f5d9b");
		TmdbPeople tmdbPeople = tmdbApi.getPeople();
		TmdbMovies tmdbMovies = tmdbApi.getMovies(); 
		primaryActor = TmDBModel.getPrimaryActor(tmdbPeople);
		relatedActor = TmDBModel.getRelatedActor(
				tmdbPeople, tmdbMovies, primaryActor);
		
		primaryActorImage 
			= tmdbPeople.getPersonImages(primaryActor.getId());
		actors.clear();
		for (int x = 0; x < 5; x++) {
			if (correctAnswer == x) {
				actors.add(relatedActor);
			} else {
				actors.add(TmDBModel.getIncorrectAnswers(
						tmdbPeople, primaryActor));
			}
		}
	}
	
	/**
	 * @throws MalformedURLException a
	 */
	public void updateGUI() {
		textPane.setText(actors.get(0).getName());
		textPane.repaint();
		textPane1.setText(actors.get(1).getName());
		textPane1.repaint();
		textPane2.setText(actors.get(2).getName());
		textPane2.repaint();
		textPane3.setText(actors.get(3).getName());
		textPane3.repaint();
		textPane4.setText(actors.get(4).getName());
		textPane4.repaint();
		textPanePrimary.setText(primaryActor.getName());
		textPanePrimary.repaint();
		
		URL url = null;
		try {
			if (primaryActorImage.size() > 0) {
			 url = new URL("https://image.tmdb.org/t/p/original" 
				+ primaryActorImage.get(0).getFilePath());
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (url != null) {
			icon = new ImageIcon(url);
			
			Image img = icon.getImage();  
			Image newimg = img.getScaledInstance(
				150, 200,  java.awt.Image.SCALE_SMOOTH);  
			icon = new ImageIcon(newimg);
		}
		
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws MalformedURLException 
	 */
	private void initialize() throws MalformedURLException {	
		update();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 620, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.getContentPane().setLayout(null);
		
		textPanePrimary = new JTextPane();
		textPanePrimary.setText(primaryActor.getName());
		textPanePrimary.setBackground(
				UIManager.getColor("Button.background"));
		textPanePrimary.setBounds(310, 31, 259, 26);
		frame.getContentPane().add(textPanePrimary);
		
		textPane4 = new JTextPane();
		textPane4.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		textPane4.setBackground(SystemColor.window);
		textPane4.setBounds(505, 291, 90, 16);
		frame.getContentPane().add(textPane4);
		
		textPane3 = new JTextPane();
		textPane3.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		textPane3.setBackground(SystemColor.window);
		textPane3.setBounds(385, 291, 90, 16);
		frame.getContentPane().add(textPane3);
		
		textPane2 = new JTextPane();
		textPane2.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		textPane2.setBackground(SystemColor.window);
		textPane2.setBounds(271, 291, 76, 16);
		frame.getContentPane().add(textPane2);
		
		textPane1 = new JTextPane();
		textPane1.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		textPane1.setBackground(SystemColor.window);
		textPane1.setBounds(145, 291, 90, 16);
		frame.getContentPane().add(textPane1);
		
		textPane = new JTextPane();
		textPane.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		textPane.setBackground(UIManager.getColor("Button.background"));
		textPane.setBounds(25, 291, 90, 16);
		frame.getContentPane().add(textPane);
		
		JButton btnOne = new JButton();
		btnOne.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		btnOne.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				if (correctAnswer == 0) {
					JOptionPane.showMessageDialog(
						null, "Correct Answer", "",
					   JOptionPane.INFORMATION_MESSAGE);
					update();
					updateGUI();
					lblNewLabel.setVisible(false);
					frame.getContentPane().repaint();
				} else {
					JOptionPane.showMessageDialog(
						null, "Incorrect Answer", "",
				       JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnOne.setBounds(20, 275, 100, 50);
		frame.getContentPane().add(btnOne);
		
		JButton btnTwo = new JButton();
		btnTwo.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		btnTwo.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				if (correctAnswer == 1) {
					JOptionPane.showMessageDialog(
						null, "Correct Answer", "",
					   JOptionPane.INFORMATION_MESSAGE);
					update();
					updateGUI();
					lblNewLabel.setVisible(false);
					frame.getContentPane().repaint();
				} else {
					JOptionPane.showMessageDialog(
						null, "Incorrect Answer", "",
					   JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnTwo.setBounds(140, 275, 100, 50);
		frame.getContentPane().add(btnTwo);
		
		JButton btnThree = new JButton();
		btnThree.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		btnThree.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				if (correctAnswer == 2) {
					JOptionPane.showMessageDialog(
						null, "Correct Answer", "",
					   JOptionPane.INFORMATION_MESSAGE);
					update();
					updateGUI();
					lblNewLabel.setVisible(false);
					frame.getContentPane().repaint();
				} else {
					JOptionPane.showMessageDialog(
						null, "Incorrect Answer", "",
					   JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnThree.setBounds(265, 275, 90, 50);
		frame.getContentPane().add(btnThree);
		
		JButton btnFour = new JButton();
		btnFour.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		btnFour.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				if (correctAnswer == 3) {
					JOptionPane.showMessageDialog(
						null, "Correct Answer", "",
					   JOptionPane.INFORMATION_MESSAGE);
					update();
					updateGUI();
					lblNewLabel.setVisible(false);
					frame.getContentPane().repaint();
				} else {
					JOptionPane.showMessageDialog(
						null, "Incorrect Answer", "",
					   JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnFour.setBounds(380, 275, 100, 50);
		frame.getContentPane().add(btnFour);
		
		JButton btnFive = new JButton();
		btnFive.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		btnFive.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				if (correctAnswer == 4) {
					JOptionPane.showMessageDialog(
						null, "Correct Answer", "",
					   JOptionPane.INFORMATION_MESSAGE);
					update();
					updateGUI();
					lblNewLabel.setVisible(false);
					frame.getContentPane().repaint();
				} else {
					JOptionPane.showMessageDialog(
						null, "Incorrect Answer", "",
					   JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnFive.setBounds(500, 275, 100, 50);
		frame.getContentPane().add(btnFive);
		

		
		JTextPane txtpnWhichActorListed = new JTextPane();
		txtpnWhichActorListed.setText(
				"Which actor listed below is in a movie with:");
		txtpnWhichActorListed.setBackground(
				UIManager.getColor("Button.background"));
		txtpnWhichActorListed.setBounds(16, 31, 282, 16);
		frame.getContentPane().add(txtpnWhichActorListed);
		
		updateGUI();

		lblNewLabel = new JLabel("");

		lblNewLabel.setIcon(icon);
		lblNewLabel.setBounds(235, 60, 150, 200);
		frame.getContentPane().add(lblNewLabel);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setBounds(10, 340, 600, 33);
		frame.getContentPane().add(splitPane);
		splitPane.setResizeWeight(0.3);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				System.exit(1);
			}
		});
		splitPane.setLeftComponent(btnQuit);
		
		JButton btnPlay = new JButton("New Question");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				update();
				updateGUI();
				lblNewLabel.setVisible(false);
				frame.getContentPane().repaint();
			}
		});
		splitPane.setRightComponent(btnPlay);
	}
}
