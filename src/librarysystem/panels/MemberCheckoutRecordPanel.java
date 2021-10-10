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

import business.SystemController;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import javax.swing.JScrollBar;

public class MemberCheckoutRecordPanel implements MessageableWindow {
	private JPanel mainPanel;
	private JTable checkoutStatusTable;
	private String[] columnNames;
	private Object[][] data;
	private JTextField searchTextField;
	private JButton searchButton;

	public JPanel getMainPanel() {
		return mainPanel;
	}

	public MemberCheckoutRecordPanel() {

		mainPanel = new JPanel();
		mainPanel.setLayout(null);

		JLabel title = new JLabel("Member Checkout Record");
		title.setBounds(39, 22, 237, 16);
		mainPanel.add(title);

		searchTextField = new JTextField();
		searchTextField.setText("1001");
		searchTextField.setBounds(34, 60, 172, 26);
		mainPanel.add(searchTextField);
		
		searchButton = new JButton("Search By Member ID");
		searchButton.setBounds(218, 60, 182, 26);
		attachSearchButtonListener(searchButton);
		updateData();
		mainPanel.add(searchButton);

	}

	private void attachSearchButtonListener(JButton btn) {
		btn.addActionListener(evt -> {
			String id = searchTextField.getText().trim();
			List<String[]> recordsInfo = new ArrayList<String[]>();
			recordsInfo = new SystemController().getMemberRecords(id);

//			recordsInfo.forEach(a -> System.out.println("x "+Arrays.toString(a)));

			String[][] info = new String[recordsInfo.size()][];
			info = recordsInfo.toArray(info);
			data = info;
			if(data.length == 0)
				displayError("No checkout Entry found");
			updateData();
		});
	}
	
	@Override
	public void updateData() {
		columnNames = new String[] { "ISBN", "Title", "CopyNumber", "checkoutDate", "isOverDue" };

		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		checkoutStatusTable = new JTable(model);
		checkoutStatusTable.setFillsViewportHeight(true);
		checkoutStatusTable.setBackground(SystemColor.LIGHT_GRAY);
		checkoutStatusTable.setBounds(17, 166, 416, 128);
		// checkoutStatusTable.setDefaultRenderer(new CustomTableRenderer());
		for (int i = 0; i < model.getColumnCount(); i++) {
			checkoutStatusTable.setDefaultRenderer(checkoutStatusTable.getColumnClass(i), new CustomTableRenderer());
		}

		JScrollPane scroll_table = new JScrollPane(checkoutStatusTable); // add table to scroll panel
		scroll_table.setBounds(6, 98, 438, 196);
		scroll_table.setVisible(true);

		mainPanel.add(scroll_table);
		
	}
}

class CustomTableRenderer extends DefaultTableCellRenderer {

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isOverdue, boolean hasFocus,
			int row, int column) {

		Component c = super.getTableCellRendererComponent(table, value, isOverdue, hasFocus, row, column);
		{

			// Check the column name, if it is "version"
			if (table.getColumnName(column).compareToIgnoreCase("isOverDue") == 0) {
				// You know version column includes string
				String isOverDue = (String) value;

				if (isOverDue == "OverDue") {
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
