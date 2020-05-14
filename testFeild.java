import javax.jws.soap.SOAPBinding.Style;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.io.*;
import java.awt.event.*;


public class testFeild {
    
    public static void main(String args[]) {
        JFrame test = new JFrame();
        test.setSize(new Dimension(1650,800));
        test.setMinimumSize(new Dimension(200,200));
        test.setLayout(null);
        test.setVisible(true);
        test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel container2 = new JPanel();
        container2.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
        container2.setLayout(null);
        container2.setSize(200,500);
        container2.setLocation(200,200);
        
        JPanel container = new JPanel();
        container.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
        container.setLayout(null);
        container.setSize(400,400);
        container.setLocation(500,200);
        
        JLabel input = new JLabel("Test");
        input.setSize(new Dimension(450,400));
        input.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        input.setHorizontalAlignment(SwingConstants.CENTER);
        input.setVerticalAlignment(SwingConstants.CENTER);
        //input.setLocation(100,100);
        
        container.add(input);
        test.add(container);
        test.add(container2);
    }
}


