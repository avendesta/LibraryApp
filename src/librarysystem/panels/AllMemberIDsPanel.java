package librarysystem.panels;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import business.ControllerInterface;
import business.SystemController;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.SystemColor;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;

public class AllMemberIDsPanel implements MessageableWindow {
	private JPanel mainPanel;
	private JTable checkoutStatusTable;
	private Object[][] dataTable;

	ControllerInterface ci = new SystemController();
	private DefaultTableModel model;

	public JPanel getMainPanel() {
		return mainPanel;
	}

	public AllMemberIDsPanel() {
		mainPanel = new JPanel();
		mainPanel.setLayout(null);

		JLabel title = new JLabel("All Member IDs");
		title.setBounds(39, 22, 237, 16);
		mainPanel.add(title);
		updateData();
		model = new DefaultTableModel(dataTable, new String[] { "Member IDs", "First Name" });
		checkoutStatusTable = new JTable(model);
		checkoutStatusTable.setFillsViewportHeight(true);
		checkoutStatusTable.setBackground(SystemColor.textHighlight);
		checkoutStatusTable.setBounds(17, 166, 416, 128);
		updateData();
		DefaultTableModel dm = (DefaultTableModel)checkoutStatusTable.getModel();
		dm.fireTableDataChanged();

		JScrollPane scroll_table = new JScrollPane(checkoutStatusTable); // add table to scroll panel
		scroll_table.setBounds(6, 98, 438, 196);
		scroll_table.setVisible(true);

		mainPanel.add(scroll_table);

	}

	@Override
	public void updateData() {
		List<String> ids = new SystemController().allMemberIds();
		Collections.sort(ids);
		dataTable = new Object[ids.size()][2];
		Object[] data = new Object[ids.size()];
		data = ids.toArray(data);
		for (int i = 0; i < ids.size(); i++) {
			dataTable[i][0] = ids.get(i);
			dataTable[i][1] = ids.get(i);
		}

	}
}
