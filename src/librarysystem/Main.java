package librarysystem;

import java.awt.*;
import javax.swing.*;

import librarysystem.panels.MainFrame;


public class Main {
   public static void main(String[] args) {
      EventQueue.invokeLater(() -> 
         {
            JFrame frame = new MainFrame();
            frame.setTitle("MPP LibraryAPP");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            centerFrameOnDesktop(frame);
            frame.setVisible(true);
         });
   }
   
   public static void centerFrameOnDesktop(Component f) {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		int height = toolkit.getScreenSize().height;
		int width = toolkit.getScreenSize().width;
		int frameHeight = f.getSize().height;
		int frameWidth = f.getSize().width;
		f.setLocation(((width - frameWidth) / 2), (height - frameHeight) / 3);
	}
}