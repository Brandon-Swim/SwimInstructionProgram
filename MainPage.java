/*
 * TODO
 * 1.finish layout
 * 2.make main window scrollable
 * 3.Add tabs JTabbedPane
 */


import javax.jws.soap.SOAPBinding.Style;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.io.*;
import java.awt.event.*;

public class MainPage {
     static JFrame mainWindow;
     //Labels
     static JLabel table1; //TODO
     static JLabel panel1; //TODO
     static JLabel panel2; //TODO
     static JLabel panel3; //TODO
     static JLabel panel4; //TODO
     //Panels
     static JPanel tab1;
     static JPanel tab2;
     //Buttons
     //Special
     static JScrollPane scrollBar;
     static Font labels = new Font("Fuse", Font.BOLD, 32); //TODO
    
    public static void main(String args[]) {
        MainPage program = new MainPage();
        program.PrepareGUI();
        WelcomeFrame introduction = new WelcomeFrame();
        introduction.Initialize();
    }
    
    public MainPage() {
        mainWindow = new JFrame("Swim Program");
        mainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainWindow.setMinimumSize(new Dimension(1650,800));
        mainWindow.setLayout(null);
        mainWindow.setVisible(false);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void PrepareGUI() {
        table1 = new JLabel("Main Table Goes Here");
        table1.setHorizontalAlignment(SwingConstants.CENTER);
        table1.setVerticalAlignment(SwingConstants.CENTER);
        table1.setFont(labels);
        table1.setSize(1200,700);
        table1.setLocation(300,100);
        table1.setBorder(BorderFactory.createLineBorder(Color.black, 2));   
        
        tab1 = new JPanel();
        tab1.setLayout(null);
        tab1.setPreferredSize(new Dimension(2000,2000));
        //tab1.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
       
        panel1 = new JLabel("<html>" + "Side Information" + "</html>");
        panel1.setHorizontalAlignment(SwingConstants.CENTER);
        panel1.setVerticalAlignment(SwingConstants.CENTER);
        panel1.setFont(labels);
        panel1.setSize(250,700);
        panel1.setLocation(25,100);
        panel1.setBorder(BorderFactory.createLineBorder(Color.black, 2));

        panel2 = new JLabel("<html>" + "Graphs" + "</html>");
        panel2.setHorizontalAlignment(SwingConstants.CENTER);
        panel2.setVerticalAlignment(SwingConstants.CENTER);
        panel2.setFont(labels);
        panel2.setSize(800,1000);
        panel2.setLocation(1550,100);
        panel2.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        
        panel3 = new JLabel("<html>" + "Text" + "</html>");
        panel3.setHorizontalAlignment(SwingConstants.CENTER);
        panel3.setVerticalAlignment(SwingConstants.CENTER);
        panel3.setFont(labels);
        panel3.setSize(1450,1000);
        panel3.setLocation(50,850);
        panel3.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        
        panel4 = new JLabel("<html>" + "Header" + "</html>");
        panel4.setHorizontalAlignment(SwingConstants.CENTER);
        panel4.setVerticalAlignment(SwingConstants.CENTER);
        panel4.setFont(labels);
        panel4.setSize(1450,75);
        panel4.setLocation(50,0);
        panel4.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        
        tab1.add(table1);
        tab1.add(panel1);
        tab1.add(panel2);
        tab1.add(panel3);
        
        scrollBar = new JScrollPane(tab1, 
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
            JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollBar.setSize(1650,800);
        scrollBar.setLocation(0,100);
        scrollBar.setBorder(BorderFactory.createLineBorder(Color.PINK, 2));
        
        mainWindow.getContentPane().add(scrollBar);
        mainWindow.add(panel4);
    }
    
}


