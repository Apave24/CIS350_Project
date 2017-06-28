package edu.gvsu.cis350;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CounterView implements IView, ActionListener {
	
	private JFrame frame;
	private JTextField result;
	private JButton incr;
	private JButton decr;
	private JButton reset;
	
	private CounterController controller;
	private IModel model;

	public CounterView(IModel model, CounterController controller) {
		this.model = model;
		this.controller = controller;
		
		frame = new JFrame("A Simple Counter");

		result = new JTextField(5);
		result.setEditable(false);
		setResult(model.getValue());
		
		incr = new JButton("Increment");
		decr = new JButton("Decrement");
		reset = new JButton("Reset");
		incr.addActionListener(this);
		decr.addActionListener(this);
		reset.addActionListener(this);
		
		JPanel p = new JPanel();
		p.add(result);
		p.add(incr);
		p.add(decr);
		p.add(reset);
		
		frame.add(p, BorderLayout.CENTER);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public void setResult(int val) {
		result.setText(val + "");
	}

	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == incr) {
			controller.incr(1);
			setResult(model.getValue());
			return;
		}
		
		if (event.getSource() == decr) {
			controller.decr(1);
			setResult(model.getValue());
			return;
		}
		
		if (event.getSource() == reset) {
			controller.reset();
			setResult(model.getValue());
		}
	}

}
