//package librarysystem.panels;
//
//import javax.swing.JButton;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.JTextField;
//
//import business.Address;
//import business.LibraryMember;
//import business.SystemController;
//
//import javax.swing.JTable;
//import javax.swing.JTextArea;
//
//public class MemberCheckoutRecordPanel  implements MessageableWindow {
//	private JPanel mainPanel;
//	private JTable checkoutStatusTable;
//	private JTextField searchTextField;
//	
//	public JPanel getMainPanel() {
//		return mainPanel;
//	}
//	
//	public MemberCheckoutRecordPanel() {
//
//		mainPanel=new JPanel();
//		mainPanel.setLayout(null);
//		
//		JLabel title = new JLabel("Member Checkout Record");
//		title.setBounds(39, 22, 237, 16);
//		mainPanel.add(title);
//		
//		///
//        // Data to be displayed in the JTable
//        String[][] data = {
//            { "-", "-", "-", "-" }
//        };
//  
//        // Column Names
//        String[] columnNames = { "BookTitle", "BookCopyNumber", "CheckoutDate", "DueDate" };
//		///
//		checkoutStatusTable = new JTable(data,columnNames);
//		checkoutStatusTable.setBounds(17, 142, 416, 152);
////		mainPanel.add(new JScrollPane(checkoutStatusTable));
//		mainPanel.add(checkoutStatusTable);
//		
//		searchTextField = new JTextField();
//		searchTextField.setBounds(39, 53, 159, 33);
//		mainPanel.add(searchTextField);
//		
//		JLabel lblNewLabel = new JLabel("User History");
//		lblNewLabel.setBounds(39, 114, 110, 16);
//		mainPanel.add(lblNewLabel);
//		
//		JButton searchButton = new JButton("Search By Member ID");
//		searchButton.setBounds(210, 53, 182, 33);
//		attachSearchButtonListener(searchButton);
//		mainPanel.add(searchButton);
//
//	}
//	
//	private void attachSearchButtonListener(JButton btn) {
//		btn.addActionListener(evt -> {
//			String memberId = searchTextField.getText().trim();
//			System.out.println("xxxxxx "+memberId);
//			String found = new SystemController().getMemberCheckoutEntry(memberId);
//			if(found == null) {
//				displayError("No member checkout Entry found!!");
//				return;
//			}
//			displayInfo("member checkout Entry found");
//			displayInfo(found);
//			updateData();
//		});
//	}
//
//
//	@Override
//	public void updateData() {
//		
//		mainPanel=new JPanel();
//		mainPanel.repaint();
//	}
//}

package librarysystem.panels;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JScrollBar;

public class MemberCheckoutRecordPanel implements MessageableWindow {
	private JPanel mainPanel;
	private JTable checkoutStatusTable;
	private String[] columnNames;
	private Object[][] data;

	public JPanel getMainPanel() {
		return mainPanel;
	}

	public MemberCheckoutRecordPanel() {

		mainPanel = new JPanel();
		mainPanel.setLayout(null);

		JLabel title = new JLabel("Member Checkout Record");
		title.setBounds(39, 22, 237, 16);
		mainPanel.add(title);
		updateData();
		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		checkoutStatusTable = new JTable(model);
		checkoutStatusTable.setFillsViewportHeight(true);
		checkoutStatusTable.setBackground(SystemColor.textHighlight);
		checkoutStatusTable.setBounds(17, 166, 416, 128);
		// checkoutStatusTable.setDefaultRenderer(new CustomTableRenderer());
		for (int i = 0; i < model.getColumnCount(); i++) {
			checkoutStatusTable.setDefaultRenderer(checkoutStatusTable.getColumnClass(i), new CustomTableRenderer());
		}

		JScrollPane scroll_table = new JScrollPane(checkoutStatusTable); // add table to scroll panel
		scroll_table.setBounds(6, 98, 438, 196);
		scroll_table.setVisible(true);

		mainPanel.add(scroll_table);

		JTextArea searchTextArea = new JTextArea();
		searchTextArea.setBounds(34, 60, 172, 26);
		mainPanel.add(searchTextArea);

		JButton btnNewButton_1 = new JButton("Search By Mrmber ID");
		btnNewButton_1.setBounds(218, 60, 182, 26);
		mainPanel.add(btnNewButton_1);

	}

	@Override
	public void updateData() {
		columnNames = new String[] { "First Name", "Last Name", "Sport", "Years", "Vegetarian" };
		data = new Object[][] { { "Kathy", "Smith", "Snowboarding", 5, false }, { "John", "Doe", "Rowing", 3, true },
				{ "John", "Doe", "Rowing", 4, true }, { "Sue", "Black", "Knitting", 2, false },
				{ "Jane", "White", "Speed reading", -2, true }, { "Sue", "Black", "Knitting", 2, false },
				{ "Jane", "White", "Speed reading", 20, true }, { "Sue", "Black", "Knitting", -2, false },
				{ "John", "Doe", "Rowing", 4, true }, { "Sue", "Black", "Knitting", 2, false },
				{ "Jane", "White", "Speed reading", 210, true }, { "Sue", "Black", "Knitting", 2, false },
				{ "Jane", "White", "Speed reading", 20, true }, { "Sue", "Black", "Knitting", 2, false },
				{ "John", "Doe", "Rowing", -4, true }, { "Sue", "Black", "Knitting", 2, false },
				{ "Jane", "White", "Speed reading", 210, false }, { "Sue", "Black", "Knitting", 2, false },
				{ "Jane", "White", "Speed reading", 20, false }, { "Sue", "Black", "Knitting", 2, false },
				{ "Jane", "White", "Speed reading", 210, true }, { "Sue", "Black", "Knitting", 2, false },
				{ "Jane", "White", "Speed reading", 20, true }, { "Joe", "Brown", "Pool", 10, false } };

	}
}

class CustomTableRenderer extends DefaultTableCellRenderer {

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		{

			// Check the column name, if it is "version"
			if (table.getColumnName(column).compareToIgnoreCase("Years") == 0) {
				// You know version column includes string
				int versionVal = (int) value;

				if (versionVal < 0) {
					// set to red bold font
					c.setForeground(Color.RED);
					c.setFont(new Font("Dialog", Font.BOLD, 12));
				} else {
					// stay at default
					c.setForeground(Color.BLACK);
					c.setFont(new Font("Dialog", Font.PLAIN, 12));
				}
			} else {
				// Here you should also stay at default
				// stay at default
				c.setForeground(Color.BLACK);
				c.setFont(new Font("Dialog", Font.PLAIN, 12));
			}
			return c;
		}
	}
}
