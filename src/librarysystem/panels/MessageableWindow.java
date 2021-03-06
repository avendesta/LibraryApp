package librarysystem.panels;

import static librarysystem.panels.MainFrame.statusBar;

import business.Util;


public interface MessageableWindow {
	public default void displayError(String msg) {
		statusBar.setForeground(Util.ERROR_MESSAGE_COLOR);
		statusBar.setText(msg);
	}
	
	public default void displayInfo(String msg) {
		statusBar.setForeground(Util.INFO_MESSAGE_COLOR);
		statusBar.setText(msg);
	}
	
	public void updateData();
//	public JPanel getMainPanel(); // this might help in the MainFrame
}
