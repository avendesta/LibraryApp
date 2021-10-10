package librarysystem.panels;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import business.Address;
import business.LibraryMember;
import business.SystemController;

import javax.swing.JTable;
import javax.swing.JTextArea;

public class MemberCheckoutRecordPanel  implements MessageableWindow {
	private JPanel mainPanel;
	private JTable checkoutStatusTable;
	private JTextField searchTextField;
	
	public JPanel getMainPanel() {
		return mainPanel;
	}
	
	public MemberCheckoutRecordPanel() {

		mainPanel=new JPanel();
		mainPanel.setLayout(null);
		
		JLabel title = new JLabel("Member Checkout Record");
		title.setBounds(39, 22, 237, 16);
		mainPanel.add(title);
		
		///
        // Data to be displayed in the JTable
        String[][] data = {
            { "-", "-", "-", "-" }
        };
  
        // Column Names
        String[] columnNames = { "BookTitle", "BookCopyNumber", "CheckoutDate", "DueDate" };
		///
		checkoutStatusTable = new JTable(data,columnNames);
		checkoutStatusTable.setBounds(17, 142, 416, 152);
//		mainPanel.add(new JScrollPane(checkoutStatusTable));
		mainPanel.add(checkoutStatusTable);
		
		searchTextField = new JTextField();
		searchTextField.setBounds(39, 53, 159, 33);
		mainPanel.add(searchTextField);
		
		JLabel lblNewLabel = new JLabel("User History");
		lblNewLabel.setBounds(39, 114, 110, 16);
		mainPanel.add(lblNewLabel);
		
		JButton searchButton = new JButton("Search By Member ID");
		searchButton.setBounds(210, 53, 182, 33);
		attachSearchButtonListener(searchButton);
		mainPanel.add(searchButton);

	}
	
	private void attachSearchButtonListener(JButton btn) {
		btn.addActionListener(evt -> {
			String memberId = searchTextField.getText().trim();
			System.out.println("xxxxxx "+memberId);
			String found = new SystemController().getMemberCheckoutEntry(memberId);
			if(found == null) {
				displayError("No member checkout Entry found!!");
				return;
			}
			displayInfo("member checkout Entry found");
			displayInfo(found);
			updateData();
		});
	}


	@Override
	public void updateData() {
		
		mainPanel=new JPanel();
		mainPanel.repaint();
	}
}
