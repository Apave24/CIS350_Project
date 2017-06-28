package edu.gvsu.cis350;

public class Main {
	
	public static void main(String[] args) {
		IModel model = new CounterModel();
		CounterController controller = new CounterController(model);
		IView view = new CounterView(model, controller);
		controller.setView(view);
	}

}
