package edu.gvsu.cis350;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Alex Pavey
 *
 */
public class MainGUITest {

	/**
	 * 
	 */
	@Test
	public void testUpdateGUI() {
		try{
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
			
			Assert.assertNotEquals(previousTextPaneValue, newTextPaneValue);
			Assert.assertNotEquals(previousTextPane1Value, newTextPane1Value);
			Assert.assertNotEquals(previousTextPane2Value, newTextPane2Value);
			Assert.assertNotEquals(previousTextPane3Value, newTextPane3Value);
			Assert.assertNotEquals(previousTextPane4Value, newTextPane4Value);
			**/
			
		} catch (Exception e) {
			Assert.fail("GUI failed to initialize.");
			e.printStackTrace();
		}
	}

}
