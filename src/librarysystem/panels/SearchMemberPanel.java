package librarysystem.panels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import business.LibraryMember;
import business.SystemController;

import java.awt.*;

public class SearchMemberPanel implements MessageableWindow {
	private JPanel mainPanel;
	private JTable checkoutRecordTable;
	private String[] columnNames;
	private Object[][] data;
	private JTextField searchTextField;
	private JButton searchButton;

	public JPanel getMainPanel() {
		return mainPanel;
	}

	public SearchMemberPanel() {

		mainPanel = new JPanel();
		mainPanel.setLayout(null);

		JLabel title = new JLabel("Search Member");
		title.setBounds(39, 22, 237, 16);
		mainPanel.add(title);

		searchTextField = new JTextField();
		searchTextField.setBounds(34, 60, 172, 26);
		mainPanel.add(searchTextField);
		
		searchButton = new JButton("Search Member By ID");
		searchButton.setBounds(218, 60, 182, 26);
		attachSearchButtonListener(searchButton);
		updateData();
		mainPanel.add(searchButton);

	}

	private void attachSearchButtonListener(JButton btn) {
		btn.addActionListener(evt -> {
			String id = searchTextField.getText().trim();
			LibraryMember member = new SystemController().getMember(id);

			if(member == null) {
				displayError("Member not found!");
				data = new Object[0][0];
				updateData();
				return;
			}

			String[][] info = new String[4][2];
			// Fields
			info[0][0] = "ID";
			info[1][0] = "First Name";
			info[2][0] = "Last Name";
			info[3][0] = "Telephone";
			
			// Values
			info[0][1] = member.getMemberId();
			info[1][1] = member.getFirstName();
			info[2][1] = member.getLastName();
			info[3][1] = member.getTelephone();

			data = info;
			updateData();
			displayInfo("Member info for "+member.getMemberId());
		});
	}
	
	@Override
	public void updateData() {
		columnNames = new String[] { "Field", "Value"};

		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		checkoutRecordTable = new JTable(model);
		checkoutRecordTable.setFillsViewportHeight(true);
		checkoutRecordTable.setDefaultEditor(Object.class, null);
		checkoutRecordTable.setFocusable(false);
		checkoutRecordTable.setEnabled(false);
		checkoutRecordTable.setBackground(SystemColor.LIGHT_GRAY);
		checkoutRecordTable.setBounds(17, 166, 416, 128);

		JScrollPane scroll_table = new JScrollPane(checkoutRecordTable); // add table to scroll panel
		scroll_table.setBounds(6, 98, 438, 196);
		scroll_table.setVisible(true);

		mainPanel.add(scroll_table);
		
	}
}

