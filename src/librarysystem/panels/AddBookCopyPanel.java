package librarysystem.panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import business.Author;
import business.SystemController;

import javax.swing.ImageIcon;

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
		
		JLabel bookIdLabel = new JLabel("Book ID");
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
		backgroundLabel.setIcon(new ImageIcon(AddBookCopyPanel.class.getResource("/images/library.jpg")));
		backgroundLabel.setBounds(-24, 0, 474, 300);
		mainPanel.add(backgroundLabel);	
	}
	
	private void attachaddCopyButonListener(JButton butn) {
//		butn.addActionListener(evt -> {
//			List<Author> authors = new ArrayList<Author>();
//			String fname = authFirstNameField.getText();
//			String lname = authLastNameField.getText();
//			
//			String title = titleField.getText();
//			authors.add(new Author(fname, lname));
//			
//			Data.addBookTitle(title);
//			displayInfo("The book " + title + " has been added " +
//			   "to the collection!");			
//		
//	    });
		butn.addActionListener(evt -> {
			String bookId = bookIdTextField.getText().trim();
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
