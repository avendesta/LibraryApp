package librarysystem.panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import business.Address;
import business.SystemController;

public class AddAuthorPanel implements MessageableWindow {
	private JPanel mainPanel;
	private JTextField fnameTextField;
	private JTextField lnameTextField;
	private JTextField streetTextField;
	private JTextField cityTextField;
	private JTextField stateTextField;
	private JTextField zipTextField;
	private JTextField bioTextField;
	private JTextField idTextField;
	private JLabel bioLabel;
	private JButton addAuthorButton;
	private JLabel zipLabel;
	private JTextField cellphoneTextField;
	
	public JPanel getMainPanel() {
		return mainPanel;
	}

	/**
	 * Create the panel.
	 */
	public AddAuthorPanel() {
		mainPanel=new JPanel();
		mainPanel.setLayout(null);
		
		JLabel title = new JLabel("Add Author");
		title.setBounds(43, 1, 72, 16);
		mainPanel.add(title);
		
		JLabel memberIdLabel = new JLabel("Author ID");
		memberIdLabel.setBounds(30, 27, 68, 16);
		mainPanel.add(memberIdLabel);
		
		idTextField = new JTextField();
		idTextField.setBounds(133, 22, 98, 26);
		mainPanel.add(idTextField);
		idTextField.setColumns(10);
		
		JLabel firstNameLabel = new JLabel("First Name");
		firstNameLabel.setBounds(30, 58, 68, 16);
		mainPanel.add(firstNameLabel);
		
		fnameTextField = new JTextField();
		fnameTextField.setBounds(133, 53, 98, 26);
		mainPanel.add(fnameTextField);
		fnameTextField.setColumns(10);
		
		JLabel lastNameLabel = new JLabel("Last Name");
		lastNameLabel.setBounds(253, 58, 66, 16);
		mainPanel.add(lastNameLabel);
		
		lnameTextField = new JTextField();
		lnameTextField.setBounds(331, 53, 89, 26);
		mainPanel.add(lnameTextField);
		lnameTextField.setColumns(10);
		
		JLabel streetLabel = new JLabel("Street");
		streetLabel.setBounds(30, 86, 36, 16);
		streetLabel.setHorizontalAlignment(SwingConstants.LEFT);
		mainPanel.add(streetLabel);
		
		streetTextField = new JTextField();
		streetTextField.setBounds(133, 81, 98, 26);
		mainPanel.add(streetTextField);
		streetTextField.setColumns(10);
		
		JLabel cityLabel = new JLabel("City");
		cityLabel.setBounds(253, 86, 36, 16);
		mainPanel.add(cityLabel);
		
		cityTextField = new JTextField();
		cityTextField.setBounds(331, 81, 89, 26);
		mainPanel.add(cityTextField);
		cityTextField.setColumns(10);
		
		JLabel stateLabel = new JLabel("State");
		stateLabel.setBounds(30, 114, 31, 16);
		mainPanel.add(stateLabel);
		
		stateTextField = new JTextField();
		stateTextField.setBounds(133, 109, 98, 26);
		mainPanel.add(stateTextField);
		stateTextField.setColumns(10);
		
		JLabel cellphoneLabel = new JLabel("Cell Phone");
		cellphoneLabel.setBounds(253, 27, 66, 16);
		mainPanel.add(cellphoneLabel);
		
		zipTextField = new JTextField();
		zipTextField.setBounds(331, 109, 89, 26);
		mainPanel.add(zipTextField);
		zipTextField.setColumns(10);
		
		bioTextField = new JTextField();
		bioTextField.setBounds(30, 159, 390, 68);
		mainPanel.add(bioTextField);
		bioTextField.setColumns(10);
		
		bioLabel = new JLabel("Bio");
		bioLabel.setBounds(30, 142, 61, 16);
		mainPanel.add(bioLabel);
		
		addAuthorButton = new JButton("Add");
		addAuthorButton.setBounds(303, 249, 117, 29);
		attachAddButtonListener(addAuthorButton);
		mainPanel.add(addAuthorButton);
		
		zipLabel = new JLabel("Zip");
		zipLabel.setBounds(253, 114, 61, 16);
		mainPanel.add(zipLabel);
		
		cellphoneTextField = new JTextField();
		cellphoneTextField.setBounds(331, 22, 89, 26);
		mainPanel.add(cellphoneTextField);
		cellphoneTextField.setColumns(10);
		
	}

	private void attachAddButtonListener(JButton btn) {
		btn.addActionListener(evt -> {
			String authorId = idTextField.getText().trim();
			String fName = fnameTextField.getText().trim();
			String lName = lnameTextField.getText().trim();
			String street = streetTextField.getText().trim();
			String city = cityTextField.getText().trim();
			String state = stateTextField.getText().trim();
			String zip = zipTextField.getText().trim();
			String phoneNumber = cellphoneTextField.getText().trim();
			String bio = bioTextField.getText().trim();
			Address address = new Address(street, city, state, zip);
			boolean done=new SystemController().addAuthor(authorId, fName, lName, phoneNumber, address,bio);
			if(!done) {
				displayError("author NOT added!!");
				return;
			}
			displayInfo("new author added successfully");
			updateData();
		});
	}

	
	@Override
	public void updateData() {
		idTextField.setText("");
		fnameTextField.setText("");
		lnameTextField.setText("");
		streetTextField.setText("");
		cityTextField.setText("");
		stateTextField.setText("");
		zipTextField.setText("");
		bioTextField.setText("");
	}

}