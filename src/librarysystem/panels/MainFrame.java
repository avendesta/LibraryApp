package librarysystem.panels;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.ListModel;

import business.Item;
import business.SystemController;
import business.Util;


@SuppressWarnings("serial")
public class MainFrame extends JFrame implements MessageableWindow {

   String[] links;
   JList<Item> linkList;
   JPanel cards;
   public static JTextArea statusBar = new JTextArea("Welcome to the Book Club!");
   
   LoginPanel lp;
   AllBookIDsPanel abip;
   SearchMemberPanel smp;
   AddBookCopyPanel abcp;
   AddMemberPanel amp;
   //boolean startup = true;
   
   //list items
   		//librarian previlages
   Item loginItem = new Item("Login", true);
   Item searchMemberItem = new Item("Search Member", false);
   Item checkoutBookItem = new Item("Checkout Book", false);
   Item checkStatusItem = new Item("Check Status Of Book Copy", false);
   Item addMemberIDsItem = new Item("Add Member IDs", false);
   Item allBookIDsItem = new Item("All Book IDs", false);
   		// admin previlages
   Item addMemberItem = new Item("Add Member", false);
   Item addBookItem = new Item("Add Book", false);
   Item addBookCopyItem = new Item("Add Book Copy", false);
   
   Item[] librarianItems = {loginItem, searchMemberItem, checkoutBookItem, checkStatusItem, addMemberIDsItem, allBookIDsItem};
   Item[] adminItems = {loginItem, addMemberItem, addBookItem, addBookCopyItem};
   Item[] bothItems = {loginItem, searchMemberItem, checkoutBookItem, checkStatusItem, addMemberIDsItem, allBookIDsItem, 
		   						addMemberItem, addBookItem, addBookCopyItem};
   Item[] commonItems = {loginItem};
   
   public Item[] getLibrarianItems() {
	   return librarianItems;
   }
   public Item[] getAdminItems() {
	   return adminItems;
   }
   
   public JList<Item> getLinkList() {
	   return linkList;
   }

   
   public MainFrame() {
	  Util.adjustLabelFont(statusBar, Util.DARK_BLUE, true);
      setSize(600, 450);
      
      createLinkLabels();
      createMainPanels();
      CardLayout cl = (CardLayout)(cards.getLayout());
      linkList.addListSelectionListener(event ->
      	{
    	  String value = linkList.getSelectedValue().getItemName();
    	  boolean allowed = linkList.getSelectedValue().highlight();
    	  System.out.println(value + " " + allowed);
    	  
    	  //System.out.println("selected = " + value);
    	  //cl = (CardLayout)(cards.getLayout());
	      statusBar.setText("");
	      if(!allowed) {
    		  value = loginItem.getItemName();
    		  linkList.setSelectedIndex(0);
    	  }
	      // this needs to be done using loop after all the Panels are completed
	      if(value.equals("Login")) lp.updateData();
	      if(value.equals("All Book IDs")) abip.updateData();
	      if(value.equals("search Member")) smp.updateData();
	      if(value.equals("Add Book Copy")) abcp.updateData();
	      if(value.equals("Add Member")) amp.updateData();
	      
	      cl.show(cards,value);
      	});
      

      // set up split panes

      JSplitPane innerPane 
          = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, linkList, cards);
      innerPane.setDividerLocation(180);
      JSplitPane outerPane 
          = new JSplitPane(JSplitPane.VERTICAL_SPLIT, innerPane, statusBar);
      outerPane.setDividerLocation(350);
      add(outerPane, BorderLayout.CENTER);
      lp.setBookClub(this);
  
   }
   
   public JTextArea getStatusBar() {
 	  return statusBar;
   }
   
   public void createLinkLabels() {
	    DefaultListModel<Item> model = new DefaultListModel<>();
//		model.addElement(loginListItem);
//		model.addElement(viewTitlesItem);
//		model.addElement(addBookItem);
	    for(Item li: bothItems)
	    	model.addElement(li);
	
		linkList = new JList<Item>(model);
	    linkList.setCellRenderer(new DefaultListCellRenderer() {

			@SuppressWarnings("rawtypes")
			@Override
			public Component getListCellRendererComponent(JList list, 
					Object value, int index,
					boolean isSelected, boolean cellHasFocus) {
				Component c = super.getListCellRendererComponent(list, 
						value, index, isSelected, cellHasFocus);
				if (value instanceof Item) {
					Item nextItem = (Item) value;
					setText(nextItem.getItemName());
					if (nextItem.highlight()) {
						setForeground(Util.LINK_AVAILABLE);
					} else {
						setForeground(Util.LINK_NOT_AVAILABLE);
					}
					if (isSelected) {
						setForeground(Color.BLACK);
						setBackground(new Color(236,243,245));
						//setBackground(Color.WHITE);
					}
				} 
				return c;
			}

		});
   }
 
   public void createMainPanels() {
	   //login 
	   lp = new LoginPanel();
	   JPanel loginPanel = lp.getMainPanel();
	   
	   
	   //add book
	   AddBookPanel abp = new AddBookPanel();
	   JPanel addBookPanel = abp.getMainPanel();
	   
	   
	   //all book IDs
	   abip = new AllBookIDsPanel();
	   JPanel allBookIDsPanel = abip.getMainPanel();
	   
	   // search member
	   smp = new SearchMemberPanel();
	   JPanel searchMemberIdPanel = smp.getMainPanel();

	   // add book copy
	   abcp = new AddBookCopyPanel();
	   JPanel addBookCopyPanel = abcp.getMainPanel();

	   // add member
	   amp = new AddMemberPanel();
	   JPanel addMemberPanel = amp.getMainPanel();
	   
	   cards = new JPanel(new CardLayout());
       cards.add(loginPanel, loginItem.getItemName());
       cards.add(allBookIDsPanel, allBookIDsItem.getItemName());
       cards.add(addBookPanel, addBookItem.getItemName());
       cards.add(searchMemberIdPanel, searchMemberItem.getItemName());
       cards.add(addBookCopyPanel, addBookCopyItem.getItemName());
       cards.add(addMemberPanel, addMemberItem.getItemName());
   }
   @Override
   public void updateData() {
	// nothing to do
	
}
}