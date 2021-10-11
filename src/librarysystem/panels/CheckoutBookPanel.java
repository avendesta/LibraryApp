package librarysystem.panels;

import javax.swing.*;

import business.SystemController;

public class CheckoutBookPanel implements MessageableWindow {
	private JPanel mainPanel;
	private JTextField bookISBNTextField;
	private JTextField memberIDTextField;
	
	public JPanel getMainPanel() {
		return mainPanel;
	}
	
	public CheckoutBookPanel() {

		mainPanel=new JPanel();
		mainPanel.setLayout(null);
		
		JLabel title = new JLabel("Checkout Book Copy");
		title.setBounds(23, 18, 237, 16);
		mainPanel.add(title);
		
		bookISBNTextField = new JTextField();
		bookISBNTextField.setBounds(185, 66, 111, 26);
		mainPanel.add(bookISBNTextField);
		bookISBNTextField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Book ISBN");
		lblNewLabel_1.setBounds(67, 71, 88, 16);
		mainPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Member ID");
		lblNewLabel_2.setBounds(67, 124, 88, 16);
		mainPanel.add(lblNewLabel_2);
		
		memberIDTextField = new JTextField();
		memberIDTextField.setBounds(185, 119, 111, 26);
		mainPanel.add(memberIDTextField);
		memberIDTextField.setColumns(10);
		
		JButton checkoutBookButton = new JButton("Checkout Book");
		checkoutBookButton.setBounds(186, 177, 161, 39);
		attachAddButtonListener(checkoutBookButton);
		mainPanel.add(checkoutBookButton);
		

	}
	
	private void attachAddButtonListener(JButton btn) {
		btn.addActionListener(evt -> {
			String memberId = memberIDTextField.getText().trim();
			String isbn = bookISBNTextField.getText().trim();

			boolean done=new SystemController().addEntry(memberId, isbn);
			if(!done) {
				displayError("Cannot checkout: Either the ISBN is wrong or there are no available copies");
				return;
			}
			displayInfo("Checkout successfull!!");
			updateData();
		});
	}

	@Override
	public void updateData() {
		memberIDTextField.setText("");
		bookISBNTextField.setText("");
	}
}
