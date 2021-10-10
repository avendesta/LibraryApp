package librarysystem.panels;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import business.ControllerInterface;
import business.LibraryMember;
import business.SystemController;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.SystemColor;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;

public class AllMemberIDsPanel implements MessageableWindow {
	private JPanel mainPanel;
	private JTable checkoutStatusTable;
	private Object[][] dataTable;

	ControllerInterface ci = new SystemController();
	private DefaultTableModel model;
	private JScrollPane scroll_table;

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
	}

	@Override
	public void updateData() {
		List<LibraryMember> ids = new SystemController().getAllMembers();
		Collections.sort(ids, new Comparator<LibraryMember>() {

	        public int compare(LibraryMember o1, LibraryMember o2) {
	            return o1.getMemberId().compareTo(o2.getMemberId());
	        }
	    });
		dataTable = new Object[ids.size()][2];
		Object[] data = new Object[ids.size()];
		data = ids.toArray(data);
		for (int i = 0; i < ids.size(); i++) {
			dataTable[i][0] = ids.get(i).getMemberId();
			dataTable[i][1] = ids.get(i).getFirstName()+" "+ids.get(i).getLastName();
		}

		model = new DefaultTableModel(dataTable, new String[] { "Member IDs", "Name" });
		checkoutStatusTable = new JTable(model);
		checkoutStatusTable.setFillsViewportHeight(true);
		checkoutStatusTable.setBackground(SystemColor.textHighlight);
		checkoutStatusTable.setBounds(17, 166, 416, 128);

		scroll_table = new JScrollPane(checkoutStatusTable); // add table to scroll panel
		scroll_table.setBounds(6, 98, 438, 196);
		scroll_table.setVisible(true);

		mainPanel.add(scroll_table);

	}
}
