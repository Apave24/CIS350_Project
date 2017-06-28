package edu.gvsu.cis350;

public class CounterController {
	
	private IView view;
	private IModel model;

	public CounterController(IModel model) {
		this.model = model;
	}

	public void setView(IView view) {
		this.view = view;
	}
	
	public void incr(int val) {
		model.add(val);
		view.setResult(model.getValue());
	}
	
	public void decr(int val) {
		model.sub(val);
		view.setResult(model.getValue());
	}
	
	public void reset() {
		model.reset();
		view.setResult(model.getValue());
	}
}
