//package librarysystem.panels;
//
//import java.awt.BorderLayout;
//import java.awt.FlowLayout;
//import java.awt.TextArea;
//import java.util.Collections;
//import java.util.List;
//
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//
//import business.SystemController;
//import business.Util;
//
//public class AllBookIDsPanel implements MessageableWindow {
//	
//	public JPanel getMainPanel() {
//		return mainPanel;
//	}
//	private JPanel mainPanel;
//	private JPanel topPanel;
//	private JPanel middlePanel;
//	private TextArea textArea;
//	
//
//	
//	public AllBookIDsPanel() {
//		mainPanel = new JPanel();
//		mainPanel.setLayout(new BorderLayout());
//		defineTopPanel();
//		defineMiddlePanel();
//		mainPanel.add(topPanel, BorderLayout.NORTH);
//		mainPanel.add(middlePanel, BorderLayout.CENTER);
//		
//	}
//	
//	public void defineTopPanel() {
//		topPanel = new JPanel();
//		JLabel titlesLabel = new JLabel("View Titles");
//		Util.adjustLabelFont(titlesLabel, Util.DARK_BLUE, true);
//		topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
//		topPanel.add(titlesLabel);
//	}
//	
//	public void defineMiddlePanel() {
//		middlePanel = new JPanel();
//		FlowLayout fl = new FlowLayout(FlowLayout.CENTER, 25, 25);
//		middlePanel.setLayout(fl);
//		textArea = new TextArea(8, 20);
//		updateData();
//		middlePanel.add(textArea);
//		
//	}
//	
//	public void updateData() {
//		String titles = new SystemController().getAllBookInfo();
//		textArea.setText(titles);
//		mainPanel.repaint();
//	}
//	
//}

package librarysystem.panels;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import business.Book;
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

public class AllBookIDsPanel implements MessageableWindow {
	private JPanel mainPanel;
	private JTable bookTable;
	private Object[][] dataTable;

	ControllerInterface ci = new SystemController();
	private DefaultTableModel model;
	private JScrollPane scroll_table;

	public JPanel getMainPanel() {
		return mainPanel;
	}

	public AllBookIDsPanel() {
		mainPanel = new JPanel();
		mainPanel.setLayout(null);

		JLabel title = new JLabel("All Book IDs");
		title.setBounds(39, 22, 237, 16);
		mainPanel.add(title);
		updateData();
	}

	@Override
	public void updateData() {
		List<Book> ids = new SystemController().getAllBooks();
		Collections.sort(ids, new Comparator<Book>() {

	        public int compare(Book o1, Book o2) {
	            return o1.getIsbn().compareTo(o2.getIsbn());
	        }
	    });
		dataTable = new Object[ids.size()][2];
		Object[] data = new Object[ids.size()];
		data = ids.toArray(data);
		for (int i = 0; i < ids.size(); i++) {
			dataTable[i][0] = ids.get(i).getIsbn();
			dataTable[i][1] = ids.get(i).getTitle();
		}

		model = new DefaultTableModel(dataTable, new String[] { "ISBN", "Title" });
		bookTable = new JTable(model);
		bookTable.setDefaultEditor(Object.class, null);
		bookTable.setFocusable(false);
		bookTable.setEnabled(false);
		bookTable.setFillsViewportHeight(true);
		bookTable.setBackground(SystemColor.LIGHT_GRAY);
		bookTable.setBounds(17, 166, 416, 128);

		scroll_table = new JScrollPane(bookTable); // add table to scroll panel
		scroll_table.setBounds(6, 98, 438, 196);
		scroll_table.setVisible(true);

		mainPanel.add(scroll_table);

	}
}
