package librarysystem.panels;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

import business.Item;
import business.Util;


@SuppressWarnings("serial")
public class MainFrame extends JFrame implements MessageableWindow {

   String[] links;
   JList<Item> linkList;
   JPanel cards;
   public static JTextArea statusBar = new JTextArea("Welcome to the Library!");
   
   LoginPanel lp;
   AllBookIDsPanel abip;
   SearchMemberPanel smp;
   AddBookCopyPanel abcp;
   AddMemberPanel amp;
   AddAuthorPanel aap;
   MemberCheckoutRecordPanel mcrp;
   AllMemberIDsPanel amip;
   CheckoutBookPanel cbp;
   BookCopyStatusPanel bcs;
   //boolean startup = true;
   
   //list items
   		//librarian previlages
   Item loginItem = new Item("Login", true);
   Item searchMemberItem = new Item("Search Member", false);
   Item checkoutBookItem = new Item("Checkout Book", false);
   Item checkStatusItem = new Item("Check Status Of Book Copy", false);
   Item allBookIDsItem = new Item("All Book IDs", false);
   Item memberCheckoutRecordItem = new Item("Member Checkout Record", false);
   		// admin previlages
   Item addMemberItem = new Item("Add Member", false);
   Item addAuthorItem = new Item("Add Author", false);
   Item addBookItem = new Item("Add Book", false);
   Item addBookCopyItem = new Item("Add Book Copy", false);
   Item allMemberIDsItem = new Item("All Member IDs", false);
   
   Item[] librarianItems = {loginItem, searchMemberItem, checkoutBookItem, checkStatusItem, allBookIDsItem, memberCheckoutRecordItem, allMemberIDsItem};
   Item[] adminItems = {loginItem, addMemberItem, addBookItem, addBookCopyItem, addAuthorItem};
   Item[] bothItems = {loginItem, searchMemberItem, checkoutBookItem, checkStatusItem, addAuthorItem, allBookIDsItem, memberCheckoutRecordItem, allMemberIDsItem,
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
      setSize(700, 450);
      
      createLinkLabels();
      createMainPanels();
      CardLayout cl = (CardLayout)(cards.getLayout());
      linkList.addListSelectionListener(event ->
      	{
    	  String value = linkList.getSelectedValue().getItemName();
    	  boolean allowed = linkList.getSelectedValue().highlight();
    	  System.out.println(value + " " + allowed);
    	  
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
	      if(value.equals("Add Author")) aap.updateData();
	      if(value.equals("Member Checkout Record")) mcrp.updateData();
	      if(value.equals("All Member IDs")) amip.updateData();
	      if(value.equals("Checkout Book")) cbp.updateData();
	      if(value.equals("Check Status Of Book Copy")) bcs.updateData();
	      
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

	   // add author
	   aap = new AddAuthorPanel();
	   JPanel addAuthorPanel = aap.getMainPanel();
	   
	   // member checkout record
	   mcrp = new MemberCheckoutRecordPanel();
	   JPanel memberCheckoutRecord = mcrp.getMainPanel();
	   
	   //all member IDs
	   amip = new AllMemberIDsPanel();
	   JPanel allMemberIDsPanel = amip.getMainPanel();
	   
	   //checkout book
	   cbp = new CheckoutBookPanel();
	   JPanel checkoutBookPanel = cbp.getMainPanel();

	   //book copy status
	   bcs = new BookCopyStatusPanel();
	   JPanel bookCopyStatusPanel = bcs.getMainPanel();
	   
	   cards = new JPanel(new CardLayout());
       cards.add(loginPanel, loginItem.getItemName());
       cards.add(allBookIDsPanel, allBookIDsItem.getItemName());
       cards.add(addBookPanel, addBookItem.getItemName());
       cards.add(searchMemberIdPanel, searchMemberItem.getItemName());
       cards.add(addBookCopyPanel, addBookCopyItem.getItemName());
       cards.add(addMemberPanel, addMemberItem.getItemName());
       cards.add(addAuthorPanel, addAuthorItem.getItemName());
       cards.add(memberCheckoutRecord, memberCheckoutRecordItem.getItemName());
       cards.add(allMemberIDsPanel, allMemberIDsItem.getItemName());
       cards.add(checkoutBookPanel, checkoutBookItem.getItemName());
       cards.add(bookCopyStatusPanel, checkStatusItem.getItemName());
   }
   @Override
   public void updateData() {
	// nothing to do
	
}
}