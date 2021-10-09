package librarysystem.panels;

import javax.swing.JPanel;
//import com.jgoodies.forms.layout.FormLayout;
//import com.jgoodies.forms.layout.ColumnSpec;
//import com.jgoodies.forms.layout.RowSpec;
//import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import business.Address;
import business.SystemController;

public class AddMemberPanel implements MessageableWindow {
	private JPanel mainPanel;
	private JTextField fnameTextField;
	private JTextField lnameTextField;
	private JTextField streetTextField;
	private JTextField cityTextField;
	private JTextField stateTextField;
	private JTextField zipTextField;
	private JTextField cellphoneTextField;
	private JTextField idTextField;
	
	public JPanel getMainPanel() {
		return mainPanel;
	}

	/**
	 * Create the panel.
	 */
	public AddMemberPanel() {
		mainPanel=new JPanel();
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 67, 0, 0, 0, 0, 67, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		mainPanel.setLayout(gbl_panel);
		
		JLabel lblNewLabel_1 = new JLabel("           ");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.gridwidth = 3;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 10;
		gbc_lblNewLabel_1.gridy = 1;
		mainPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JLabel title = new JLabel("Add Member");
		GridBagConstraints gbc_title = new GridBagConstraints();
		gbc_title.gridwidth = 2;
		gbc_title.insets = new Insets(0, 0, 5, 5);
		gbc_title.gridx = 1;
		gbc_title.gridy = 4;
		mainPanel.add(title, gbc_title);
		
		JLabel memberIdLabel = new JLabel("Member ID");
		GridBagConstraints gbc_lblNewLabel_11 = new GridBagConstraints();
		gbc_lblNewLabel_11.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_11.gridx = 4;
		gbc_lblNewLabel_11.gridy = 5;
		mainPanel.add(memberIdLabel, gbc_lblNewLabel_11);
		
		idTextField = new JTextField();
		GridBagConstraints gbc_idTextField = new GridBagConstraints();
		gbc_idTextField.insets = new Insets(0, 0, 5, 5);
		gbc_idTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_idTextField.gridx = 6;
		gbc_idTextField.gridy = 5;
		mainPanel.add(idTextField, gbc_idTextField);
		idTextField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("First Name");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 4;
		gbc_lblNewLabel_3.gridy = 6;
		mainPanel.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		fnameTextField = new JTextField();
		GridBagConstraints gbc_fnameTextField = new GridBagConstraints();
		gbc_fnameTextField.insets = new Insets(0, 0, 5, 5);
		gbc_fnameTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_fnameTextField.gridx = 6;
		gbc_fnameTextField.gridy = 6;
		mainPanel.add(fnameTextField, gbc_fnameTextField);
		fnameTextField.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Last Name");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 4;
		gbc_lblNewLabel_4.gridy = 7;
		mainPanel.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		lnameTextField = new JTextField();
		GridBagConstraints gbc_lnameTextField = new GridBagConstraints();
		gbc_lnameTextField.insets = new Insets(0, 0, 5, 5);
		gbc_lnameTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_lnameTextField.gridx = 6;
		gbc_lnameTextField.gridy = 7;
		mainPanel.add(lnameTextField, gbc_lnameTextField);
		lnameTextField.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Street");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 4;
		gbc_lblNewLabel_5.gridy = 8;
		mainPanel.add(lblNewLabel_5, gbc_lblNewLabel_5);
		
		streetTextField = new JTextField();
		GridBagConstraints gbc_streetTextField = new GridBagConstraints();
		gbc_streetTextField.insets = new Insets(0, 0, 5, 5);
		gbc_streetTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_streetTextField.gridx = 6;
		gbc_streetTextField.gridy = 8;
		mainPanel.add(streetTextField, gbc_streetTextField);
		streetTextField.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("City");
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 4;
		gbc_lblNewLabel_6.gridy = 9;
		mainPanel.add(lblNewLabel_6, gbc_lblNewLabel_6);
		
		cityTextField = new JTextField();
		GridBagConstraints gbc_citytextField = new GridBagConstraints();
		gbc_citytextField.insets = new Insets(0, 0, 5, 5);
		gbc_citytextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_citytextField.gridx = 6;
		gbc_citytextField.gridy = 9;
		mainPanel.add(cityTextField, gbc_citytextField);
		cityTextField.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("State");
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_7.gridx = 4;
		gbc_lblNewLabel_7.gridy = 10;
		mainPanel.add(lblNewLabel_7, gbc_lblNewLabel_7);
		
		stateTextField = new JTextField();
		GridBagConstraints gbc_stateTextField = new GridBagConstraints();
		gbc_stateTextField.insets = new Insets(0, 0, 5, 5);
		gbc_stateTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_stateTextField.gridx = 6;
		gbc_stateTextField.gridy = 10;
		mainPanel.add(stateTextField, gbc_stateTextField);
		stateTextField.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Zip");
		GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
		gbc_lblNewLabel_8.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_8.gridx = 4;
		gbc_lblNewLabel_8.gridy = 11;
		mainPanel.add(lblNewLabel_8, gbc_lblNewLabel_8);
		
		zipTextField = new JTextField();
		GridBagConstraints gbc_zipTextField = new GridBagConstraints();
		gbc_zipTextField.insets = new Insets(0, 0, 5, 5);
		gbc_zipTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_zipTextField.gridx = 6;
		gbc_zipTextField.gridy = 11;
		mainPanel.add(zipTextField, gbc_zipTextField);
		zipTextField.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("Cell Phone");
		GridBagConstraints gbc_lblNewLabel_9 = new GridBagConstraints();
		gbc_lblNewLabel_9.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_9.gridx = 4;
		gbc_lblNewLabel_9.gridy = 12;
		mainPanel.add(lblNewLabel_9, gbc_lblNewLabel_9);
		
		cellphoneTextField = new JTextField();
		GridBagConstraints gbc_cellphoneTextField = new GridBagConstraints();
		gbc_cellphoneTextField.insets = new Insets(0, 0, 5, 5);
		gbc_cellphoneTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_cellphoneTextField.gridx = 6;
		gbc_cellphoneTextField.gridy = 12;
		mainPanel.add(cellphoneTextField, gbc_cellphoneTextField);
		cellphoneTextField.setColumns(10);
		
		JLabel lblNewLabel_10 = new JLabel("");
		GridBagConstraints gbc_lblNewLabel_10 = new GridBagConstraints();
		gbc_lblNewLabel_10.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_10.gridx = 4;
		gbc_lblNewLabel_10.gridy = 13;
		mainPanel.add(lblNewLabel_10, gbc_lblNewLabel_10);
		
		JButton addMemberButton = new JButton("Add");
		GridBagConstraints gbc_addMemberButton = new GridBagConstraints();
		gbc_addMemberButton.insets = new Insets(0, 0, 5, 5);
		gbc_addMemberButton.gridx = 8;
		gbc_addMemberButton.gridy = 14;
		attachAddButtonListener(addMemberButton);
		mainPanel.add(addMemberButton, gbc_addMemberButton);
		
	}

	private void attachAddButtonListener(JButton btn) {
		btn.addActionListener(evt -> {
			String memberId = idTextField.getText().trim();
			String fName = fnameTextField.getText().trim();
			String lName = lnameTextField.getText().trim();
			String street = streetTextField.getText().trim();
			String city = cityTextField.getText().trim();
			String state = stateTextField.getText().trim();
			String zip = zipTextField.getText().trim();
			String phoneNumber = cellphoneTextField.getText().trim();
			Address address = new Address(street, city, state, zip);
			boolean done=new SystemController().addMember(memberId, fName, lName, phoneNumber, address);
			if(!done) {
				displayError("new member NOT added!!");
				return;
			}
			displayInfo("new member added successfully");
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
		cellphoneTextField.setText("");
	}

}
