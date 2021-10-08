package librarysystem.panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JSeparator;
import javax.swing.JTextArea;

import java.awt.Color;
import javax.swing.SwingConstants;

import business.SystemController;

public class SearchMemberPanel implements MessageableWindow {
	
	private JPanel mainPanel;
	private JTextField searchMemberTextField;
	private JTextArea infoTextArea;
	private JButton findButton;
	
	public JPanel getMainPanel() {
		return mainPanel;
	}
	
	public SearchMemberPanel() {

		mainPanel=new JPanel();
		mainPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Search Member");
		lblNewLabel.setBounds(25, 19, 105, 16);
		mainPanel.add(lblNewLabel);
		
		searchMemberTextField = new JTextField();
		searchMemberTextField.setToolTipText("");
		searchMemberTextField.setBounds(35, 47, 243, 29);
		mainPanel.add(searchMemberTextField);
		searchMemberTextField.setColumns(10);
		
		findButton = new JButton("Find");
		findButton.setBounds(303, 47, 117, 29);
		findButton.addActionListener(e -> updateData());
		mainPanel.add(findButton);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.GRAY);
		separator.setForeground(Color.LIGHT_GRAY);
		separator.setBounds(35, 111, 385, 11);
		mainPanel.add(separator);
		
//		resultLabel = new JLabel("Result");
//		resultLabel.setVerticalAlignment(SwingConstants.TOP);
//		resultLabel.setBounds(53, 128, 356, 116);
//		mainPanel.add(resultLabel);
		infoTextArea = new JTextArea();
		infoTextArea.setBounds(45, 134, 358, 114);
		infoTextArea.setColumns(10);
		mainPanel.add(infoTextArea);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(Color.BLACK);
		separator_1.setBounds(35, 248, 385, 11);
		mainPanel.add(separator_1);
		

	}

	@Override
	public void updateData() {
		String memberId = searchMemberTextField.getText().trim();
		String memberInfo = new SystemController().getMemberInfo(memberId);
		System.out.println(memberId + " " + memberInfo);
		if(memberInfo != "") {
			infoTextArea.setText(memberInfo);
			displayInfo("Member Found");
		}
		else {
			infoTextArea.setText("Member Not Found");
			displayError("Member Not Found");
		}
		mainPanel.repaint();		
	}
}
