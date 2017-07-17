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
	
	private JButton btnOne;
	private JButton btnTwo;
	private JButton btnThree;
	private JButton btnFour;
	private JButton btnFive;
	private ImageIcon icon;
	private JTextPane textPane3;
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
		btnOne.setText(actors.get(0).getName());
		btnOne.repaint();
		btnTwo.setText(actors.get(1).getName());
		btnTwo.repaint();
		btnThree.setText(actors.get(2).getName());
		btnThree.repaint();
		btnFour.setText(actors.get(3).getName());
		btnFour.repaint();
		btnFive.setText(actors.get(4).getName());
		btnFive.repaint();
		textPane3.setText(primaryActor.getName());
		textPane3.repaint();
		
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
			lblNewLabel.setIcon(icon);
			lblNewLabel.repaint();
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
		
		btnOne = new JButton(actors.get(0).getName());
		btnOne.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		btnOne.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				if (correctAnswer == 0) {
					JOptionPane.showMessageDialog(
						null, "Correct Answer", "",
					   JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(
						null, "Incorrect Answer", "",
				       JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnOne.setBounds(20, 275, 100, 50);
		frame.getContentPane().add(btnOne);
		
		btnTwo = new JButton(actors.get(1).getName());
		btnTwo.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		btnTwo.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				if (correctAnswer == 1) {
					JOptionPane.showMessageDialog(
						null, "Correct Answer", "",
					   JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(
						null, "Incorrect Answer", "",
					   JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnTwo.setBounds(140, 275, 100, 50);
		frame.getContentPane().add(btnTwo);
		
		btnThree = new JButton(actors.get(2).getName());
		btnThree.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		btnThree.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				if (correctAnswer == 2) {
					JOptionPane.showMessageDialog(
						null, "Correct Answer", "",
					   JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(
						null, "Incorrect Answer", "",
					   JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnThree.setBounds(260, 275, 100, 50);
		frame.getContentPane().add(btnThree);
		
		btnFour = new JButton(actors.get(3).getName());
		btnFour.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		btnFour.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				if (correctAnswer == 3) {
					JOptionPane.showMessageDialog(
						null, "Correct Answer", "",
					   JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(
						null, "Incorrect Answer", "",
					   JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnFour.setBounds(380, 275, 100, 50);
		frame.getContentPane().add(btnFour);
		
		btnFive = new JButton(actors.get(4).getName());
		btnFive.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		btnFive.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				if (correctAnswer == 4) {
					JOptionPane.showMessageDialog(
						null, "Correct Answer", "",
					   JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(
						null, "Incorrect Answer", "",
					   JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnFive.setBounds(500, 275, 100, 50);
		frame.getContentPane().add(btnFive);
		
		textPane3 = new JTextPane();
		textPane3.setText(primaryActor.getName());
		textPane3.setBackground(
				UIManager.getColor("Button.background"));
		textPane3.setBounds(310, 31, 259, 26);
		frame.getContentPane().add(textPane3);
		
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
			}
		});
		splitPane.setRightComponent(btnPlay);
	}
}
