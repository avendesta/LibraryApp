package librarysystem.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.SwingConstants;

import com.sun.jdi.event.EventQueue;

import business.Item;
import business.LoginException;
import business.SystemController;
import business.Util;
import dataaccess.Auth;
import dataaccess.Data;
import dataaccess.User;

public class LoginPanel implements MessageableWindow {
	BookClub bookClub;
    public void setBookClub(BookClub club) {
    	bookClub = club;
    }
	public JPanel getMainPanel() {
		return mainPanel;
	}
	private JPanel mainPanel;
	private JPanel upperHalf;
	private JPanel middleHalf;
	//private JPanel lowerHalf;
	private JPanel container;
	
	private JPanel topPanel;
	private JPanel middlePanel;
	private JPanel lowerPanel;
	private JPanel leftTextPanel;
	private JPanel rightTextPanel;
	
	private JTextField username;
	private JTextField password;
	private JLabel label;
	private JButton loginButton;
	private JButton logoutButton;
	public LoginPanel() {
		
		mainPanel = new JPanel();
		defineUpperHalf();
		defineMiddleHalf();
		//defineLowerHalf();
		BorderLayout bl = new BorderLayout();
		bl.setVgap(30);
		mainPanel.setLayout(bl);
					
		mainPanel.add(upperHalf, BorderLayout.NORTH);
		mainPanel.add(middleHalf, BorderLayout.CENTER);
		//mainPanel.add(lowerHalf, BorderLayout.SOUTH);

	}
	private void defineUpperHalf() {
		
		upperHalf = new JPanel();
		upperHalf.setLayout(new BorderLayout());
		defineTopPanel();
		defineMiddlePanel();
		defineLowerPanel();
		upperHalf.add(topPanel, BorderLayout.NORTH);
		upperHalf.add(middlePanel, BorderLayout.CENTER);
		upperHalf.add(lowerPanel, BorderLayout.SOUTH);
		
	}
	private void defineMiddleHalf() {
		middleHalf = new JPanel();
		middleHalf.setLayout(new BorderLayout());
		JSeparator s = new JSeparator();
		s.setOrientation(SwingConstants.HORIZONTAL);
		//middleHalf.add(Box.createRigidArea(new Dimension(0,50)));
		middleHalf.add(s, BorderLayout.SOUTH);
		
	}

	private void defineTopPanel() {
		topPanel = new JPanel();
		
		JLabel loginLabel = new JLabel("Login");
		Util.adjustLabelFont(loginLabel, Color.BLUE.darker(), true);
		
		topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		topPanel.add(loginLabel);
		
	}
	
	
	
	private void defineMiddlePanel() {
		middlePanel=new JPanel();
		middlePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		defineLeftTextPanel();
		defineRightTextPanel();
		middlePanel.add(leftTextPanel);
		middlePanel.add(rightTextPanel);
	}
	private void defineLowerPanel() {
		lowerPanel = new JPanel();
		loginButton = new JButton("Login");
		addLoginButtonListener(loginButton);
		lowerPanel.add(loginButton);
	}

	private void defineLeftTextPanel() {
		
		JPanel topText = new JPanel();
		JPanel bottomText = new JPanel();
		topText.setLayout(new FlowLayout(FlowLayout.LEFT,5,0));
		bottomText.setLayout(new FlowLayout(FlowLayout.LEFT,5,0));		
		
		username = new JTextField(10);
		label = new JLabel("Username");
		label.setFont(Util.makeSmallFont(label.getFont()));
		topText.add(username);
		bottomText.add(label);
		
		leftTextPanel = new JPanel();
		leftTextPanel.setLayout(new BorderLayout());
		leftTextPanel.add(topText,BorderLayout.NORTH);
		leftTextPanel.add(bottomText,BorderLayout.CENTER);
	}
	private void defineRightTextPanel() {
		
		JPanel topText = new JPanel();
		JPanel bottomText = new JPanel();
		topText.setLayout(new FlowLayout(FlowLayout.LEFT,5,0));
		bottomText.setLayout(new FlowLayout(FlowLayout.LEFT,5,0));		
		
		password = new JPasswordField(10);
		label = new JLabel("Password");
		label.setFont(Util.makeSmallFont(label.getFont()));
		topText.add(password);
		bottomText.add(label);
		
		rightTextPanel = new JPanel();
		rightTextPanel.setLayout(new BorderLayout());
		rightTextPanel.add(topText,BorderLayout.NORTH);
		rightTextPanel.add(bottomText,BorderLayout.CENTER);
	}
	
	private void addLoginButtonListener(JButton butn) {
		butn.addActionListener(evt -> {
			
				String user = username.getText().trim();
				String pwd = password.getText().trim();
//				if(user.length() == 0 || pwd.length() == 0) {
//					displayError("Id and Password fields must be nonempty");
//				}
//				else { 
//					User login = new User(user,pwd, null);
//					
//					List<User> list = Data.logins;
//					User u = Util.findUser(list, login);
//					if(u == null) {
//						displayError("Login failed!");
//					}
//					else {
//						Data.currentAuth = u.getAuthorization();
//						displayInfo("Login successful");
//						updateLeftPanel(Data.currentAuth);
//						bookClub.repaint();
//					}
//					
//				}
				try {
					new SystemController().login(user, pwd);
					displayInfo("Login successful!");
					updateLeftPanel(SystemController.currentAuth);
					bookClub.repaint();
				} catch(LoginException e){
					displayError(e.getMessage());
				}

		});
	}
	private void updateLeftPanel(Auth auth) {
		if(auth == Auth.LIBRARIAN) librarianItems();
		else if(auth == Auth.ADMIN) adminItems();
		else if(auth == Auth.BOTH) bothItems();
		
	}
	
	private void librarianItems() {
		Item[] librarianItems = bookClub.getLibrarianItems();
		updateList(librarianItems);
	}
	private void adminItems() {
		Item[] adminItems = bookClub.getAdminItems();
		updateList(adminItems);
	}
	private void bothItems() {
		updateList(null);
	}
	
	@SuppressWarnings({"unchecked", "rawtypes"})
	private void updateList(Item[] items) {
		JList<Item> linkList = bookClub.getLinkList();
		DefaultListModel<Item> model = (DefaultListModel)linkList.getModel();
		int size = model.getSize();
		if(items != null) {	
			java.util.List<Integer> indices = new ArrayList<>();
			for(Item item : items) {
				int index = model.indexOf(item);
				indices.add(index);
				Item next = (Item)model.get(index);
				next.setHighlight(true);
				
			}
			for(int i = 0; i <size; ++i) {
				if(!indices.contains(i)) {
					Item next = (Item)model.get(i);
					next.setHighlight(false);
				}
			}
		} else {
			for(int i = 0; i <size; ++i) {
				Item next = (Item)model.get(i);
				next.setHighlight(true);	
			}
			
		}
	}
	
	@Override
	public void updateData() {
		// nothing to do
		
	}
	
	
	private static final long serialVersionUID = 3618976789175941432L;
	
	
}


