package librarysystem.panels;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import business.SystemController;
import business.Util;

public class AddBookCopyPanel implements MessageableWindow {
	
	private JPanel mainPanel;
	private JTextField bookIdTextField;
	private JTextField numberOfCopyTextField;
	
	public JPanel getMainPanel() {
		// TODO Auto-generated method stub
		return mainPanel;
	}
	
	public AddBookCopyPanel() {

		mainPanel=new JPanel();
		mainPanel.setLayout(null);
		
		JLabel title = new JLabel("Add Book Copy");
		title.setBounds(16, 16, 102, 16);
		mainPanel.add(title);
		
		JLabel bookIdLabel = new JLabel("Book ISBN");
		bookIdLabel.setBounds(79, 68, 61, 16);
		mainPanel.add(bookIdLabel);
		
		JLabel numberOfCopyLabel = new JLabel("Number of Copy");
		numberOfCopyLabel.setBounds(79, 117, 119, 16);
		mainPanel.add(numberOfCopyLabel);
		
		bookIdTextField = new JTextField();
		bookIdTextField.setBounds(210, 59, 130, 35);
		mainPanel.add(bookIdTextField);
		bookIdTextField.setColumns(10);
		
		numberOfCopyTextField = new JTextField();
		numberOfCopyTextField.setBounds(210, 108, 130, 35);
		mainPanel.add(numberOfCopyTextField);
		numberOfCopyTextField.setColumns(10);
		
		JButton addCopyButton = new JButton("Add Copy");
		addCopyButton.setBounds(210, 164, 130, 42);
		attachaddCopyButonListener(addCopyButton);
		mainPanel.add(addCopyButton);
		
		JLabel backgroundLabel = new JLabel("");
//		backgroundLabel.setIcon(new ImageIcon(AddBookCopyPanel.class.getResource("/images/library.jpg")));
		backgroundLabel.setBounds(-24, 0, 474, 300);
		mainPanel.add(backgroundLabel);	
	}
	
	private void attachaddCopyButonListener(JButton butn) {

		butn.addActionListener(evt -> {
			String bookId = bookIdTextField.getText().trim();
			String numberOfCopyStr = numberOfCopyTextField.getText().trim();
			
			if(!Util.isNumeric(numberOfCopyStr)) {
				displayError("Please Enter Integer in the book copy field");
				return;
			}
			int numberOfCopy = Integer.parseInt(numberOfCopyTextField.getText().trim());
			// addMultipleBookCopy needs to save the book copy to database
			boolean done = new SystemController().addMultipleBookCopy(bookId, numberOfCopy);
			if(!done) {
				displayError("Book not found");
				return;
			}
			displayInfo("A copy added!!");
			updateData();
		});
	}
	
	@Override
	public void updateData() {
		bookIdTextField.setText("");
		numberOfCopyTextField.setText("");
		mainPanel.repaint();
	}

}
