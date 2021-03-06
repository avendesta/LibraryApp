package business;

import java.awt.Color;
import java.awt.Font;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JComponent;

import dataaccess.User;



public class Util {
	public static final Color DARK_BLUE = Color.BLUE.darker();
	public static final Color ERROR_MESSAGE_COLOR = Color.RED.darker(); //dark red
	public static final Color INFO_MESSAGE_COLOR = new Color(24, 98, 19); //dark green
	public static final Color LINK_AVAILABLE = DARK_BLUE;
	public static final Color LINK_NOT_AVAILABLE = Color.gray;
	//rgb(18, 75, 14)
	
	public static Font makeSmallFont(Font f) {
        return new Font(f.getName(), f.getStyle(), (f.getSize()-2));
    }
	
	public static void adjustLabelFont(JComponent label, Color color, boolean bigger) {
		if(bigger) {
			Font f = new Font(label.getFont().getName(), 
					label.getFont().getStyle(), (label.getFont().getSize()+2));
			label.setFont(f);
		} else {
			Font f = new Font(label.getFont().getName(), 
					label.getFont().getStyle(), (label.getFont().getSize()-2));
			label.setFont(f);
		}
		label.setForeground(color);
		
	}
	/** Sorts a list of numeric strings in natural number order */
	public static List<String> numericSort(List<String> list) {
		Collections.sort(list, new NumericSortComparator());
		return list;
	}
	
	static class NumericSortComparator implements Comparator<String>{
		@Override
		public int compare(String s, String t) {
			if(!isNumeric(s) || !isNumeric(t)) 
				throw new IllegalArgumentException("Input list has non-numeric characters");
			int sInt = Integer.parseInt(s);
			int tInt = Integer.parseInt(t);
			if(sInt < tInt) return -1;
			else if(sInt == tInt) return 0;
			else return 1;
		}
	}
	
	public static boolean isNumeric(String s) {
		if(s==null) return false;
		return s.matches("\\d+");
	}
	
	public static User findUser(List<User> list, User user) {
		for(User u : list) {
			if(u.equals(user)) return u;
		}
		return null;
		
	}
	public static boolean isValidISBN(String s) {
		String regex = "\\d{2}-\\d{5}";
		return s.matches(regex);
	}
	public static boolean isNonEmpty(String s) {
		if(s == null) return false;
		if(s.length()==0) return false;
		return true;
	}

	public static boolean allNonEmpty(String[] arr) {
		if(arr == null) return false;
		for(String s: arr)
			if(s.length() == 0)
				return false;
		return true;
	}
}
