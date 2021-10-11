package librarysystem.panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

import business.Address;
import business.SystemController;
import business.Util;

public class BookCopyStatusPanel implements MessageableWindow {
	private JPanel mainPanel;
	private JTextField bookISBNTextField;
	private JTextField copyNumberTextField;
	
	public JPanel getMainPanel() {
		return mainPanel;
	}
	
	public BookCopyStatusPanel() {

		mainPanel=new JPanel();
		mainPanel.setLayout(null);
		
		JLabel title = new JLabel("Book Copy Status");
		title.setBounds(23, 18, 237, 16);
		mainPanel.add(title);
		
		bookISBNTextField = new JTextField();
		bookISBNTextField.setBounds(185, 66, 111, 26);
		mainPanel.add(bookISBNTextField);
		bookISBNTextField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Book ISBN");
		lblNewLabel_1.setBounds(67, 71, 88, 16);
		mainPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Copy Number");
		lblNewLabel_2.setBounds(67, 124, 88, 16);
		mainPanel.add(lblNewLabel_2);
		
		copyNumberTextField = new JTextField();
		copyNumberTextField.setBounds(185, 119, 111, 26);
		mainPanel.add(copyNumberTextField);
		copyNumberTextField.setColumns(10);
		
		JButton checkoutBookButton = new JButton("Go");
		checkoutBookButton.setBounds(186, 177, 161, 39);
		attachAddButtonListener(checkoutBookButton);
		mainPanel.add(checkoutBookButton);
		

	}
	
	private void attachAddButtonListener(JButton btn) {
		btn.addActionListener(evt -> {
			String isbn = bookISBNTextField.getText().trim();
			String copyNumber = copyNumberTextField.getText().trim();
			if(!Util.isNumeric(copyNumber) || Integer.parseInt(copyNumber)<1) {
				displayError("Copy number must be integer!!");
				return;
			}

			boolean done=new SystemController().checkBookCopyStatus(isbn, Integer.parseInt(copyNumber));
			if(!done) {
				displayError("NOT AVAILABLE");
				return;
			}
			displayInfo("Available!!");
			updateData();
		});
	}

	@Override
	public void updateData() {
		mainPanel=new JPanel();
	}
}
