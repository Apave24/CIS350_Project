package edu.gvsu.cis350;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Alex Pavey
 *
 */
public class MainGUITest {

	/**
	 * Runs the GUI and makes sure it doesn't fail.
	 */
	@Test
	public void testUpdateGUI() {
		try {
			MainGUI testGUI = new MainGUI();
			
		/** Cannot test because these fields are private
		String previousTextPaneValue = testGUI.textPane.getText();
		String previousTextPane1Value = testGUI.textPane.getText();
		String previousTextPane2Value = testGUI.textPane.getText();
		String previousTextPane3Value = testGUI.textPane.getText();
		String previousTextPane4Value = testGUI.textPane.getText();
		**/
			
			testGUI.update();
			testGUI.updateGUI();
			
		/** Cannot test because these fields are private
		String newTextPaneValue = testGUI.textPane.getText();
		String newTextPane1Value = testGUI.textPane.getText();
		String newTextPane2Value = testGUI.textPane.getText();
		String newTextPane3Value = testGUI.textPane.getText();
		String newTextPane4Value = testGUI.textPane.getText();
		**/
			
		} catch (Exception e) {
			Assert.fail("GUI failed to initialize.");
			e.printStackTrace();
		}
	}

}
