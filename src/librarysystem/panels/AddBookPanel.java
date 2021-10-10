package librarysystem.panels;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import business.SystemController;

import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.AbstractListModel;
import javax.swing.ButtonGroup;

import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JSeparator;

public class AddBookPanel implements MessageableWindow {
private JPanel mainPanel;
private JTextField titleTextField;
private JTextField authorTextField1;
private JTextField authorTextField2;
private JTextField authorTextField3;
private JTextField isbnTextField;
private JLabel bookTitleLabel;
private JButton addButton;
private JRadioButton dayLimit7Button;
private JRadioButton dayLimit21Button;
private ButtonGroup G1;

public JPanel getMainPanel() {
	return mainPanel;
}

	/**
	 * Create the panel.
	 */
	public AddBookPanel() {
		mainPanel=new JPanel();
		mainPanel.setLayout(null);
		
		JLabel title = new JLabel("Add Book");
		title.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		title.setBounds(19, 16, 99, 16);
		mainPanel.add(title);
		
		bookTitleLabel = new JLabel("Book Title");
		bookTitleLabel.setBounds(50, 44, 75, 16);
		mainPanel.add(bookTitleLabel);
		
		JLabel maxCheckoutTimeLabel = new JLabel("Max Checkout Time");
		maxCheckoutTimeLabel.setBounds(50, 103, 141, 16);
		mainPanel.add(maxCheckoutTimeLabel);
		
		JLabel authorIdLabel1 = new JLabel("1. Author ID");
		authorIdLabel1.setBounds(50, 170, 87, 16);
		mainPanel.add(authorIdLabel1);
		
		titleTextField = new JTextField();
		titleTextField.setBounds(154, 39, 130, 26);
		mainPanel.add(titleTextField);
		titleTextField.setColumns(10);
		
		JLabel authorIdLabel2 = new JLabel("2. Author ID");
		authorIdLabel2.setBounds(50, 199, 87, 16);
		mainPanel.add(authorIdLabel2);
		
		JLabel authorIdLabel3 = new JLabel("3. Author ID");
		authorIdLabel3.setBounds(50, 227, 87, 16);
		mainPanel.add(authorIdLabel3);
		
		authorTextField1 = new JTextField();
		authorTextField1.setBounds(154, 165, 130, 26);
		mainPanel.add(authorTextField1);
		authorTextField1.setColumns(10);
		
		authorTextField2 = new JTextField();
		authorTextField2.setBounds(154, 194, 130, 26);
		mainPanel.add(authorTextField2);
		authorTextField2.setColumns(10);
		
		authorTextField3 = new JTextField();
		authorTextField3.setBounds(154, 222, 130, 26);
		mainPanel.add(authorTextField3);
		authorTextField3.setColumns(10);
		
		JLabel isbnLabel = new JLabel("ISBN");
		isbnLabel.setBounds(50, 75, 75, 16);
		mainPanel.add(isbnLabel);
		
		isbnTextField = new JTextField();
		isbnTextField.setBounds(154, 65, 130, 26);
		mainPanel.add(isbnTextField);
		isbnTextField.setColumns(10);
		
		dayLimit7Button = new JRadioButton("7 days", true);
		dayLimit7Button.setBounds(190, 99, 141, 23);
		mainPanel.add(dayLimit7Button);
		
		dayLimit21Button = new JRadioButton("21 days");
		dayLimit21Button.setBounds(190, 130, 141, 23);
		mainPanel.add(dayLimit21Button);
		
		G1 = new ButtonGroup();
		G1.add(dayLimit7Button);
		G1.add(dayLimit21Button);
		
		addButton = new JButton("Add");
		addButton.setBounds(292, 248, 117, 29);
		attachAddButtonListener(addButton);
		mainPanel.add(addButton);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(29, 25, 372, 26);
		mainPanel.add(separator);

	}
	
	private void attachAddButtonListener(JButton btn) {
		btn.addActionListener(evt -> {
			String isbn = isbnTextField.getText().trim();
			String title = titleTextField.getText().trim();

			int maxCheckoutDays = dayLimit7Button.isSelected()? 7:21;
			String authorIds[] = {authorTextField1.getText().trim(), authorTextField2.getText().trim(), authorTextField3.getText().trim()};
			
			boolean done=new SystemController().addBook(isbn, title, maxCheckoutDays, authorIds);
			if(!done) {
				displayError("Book not added!!");
				return;
			}
			displayInfo("Book added");
			updateData();
		});
	}
	
	@Override
	public void updateData() {
		isbnTextField.setText("");
		titleTextField.setText("");
		dayLimit7Button.setSelected(true);
		authorTextField1.setText("");
		authorTextField2.setText("");
		authorTextField3.setText("");
	}
}
