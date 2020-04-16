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
     static JPanel tab3;
     //Buttons
     //Special
     static JScrollPane scrollBar;
     static Font labels = new Font("Fuse", Font.BOLD, 32); //TODO
     static JTabbedPane tabbedPane;
    
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
        mainWindow.setLayout(new BorderLayout());
        mainWindow.setVisible(false);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void PrepareGUI() { 
        table1 = new JLabel("Main Table");
        table1.setFont(labels);
        table1.setHorizontalAlignment(SwingConstants.CENTER);
        table1.setVerticalAlignment(SwingConstants.CENTER);
        table1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        table1.setPreferredSize(new Dimension(1250,800)); //TODO
        
        panel1 = new JLabel("<html>" + "Side Information Panel" + "</html>");
        panel1.setHorizontalAlignment(SwingConstants.CENTER);
        panel1.setVerticalAlignment(SwingConstants.CENTER);
        panel1.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        panel1.setFont(labels);
        panel1.setPreferredSize(new Dimension(300,800));    //TODO
        
        panel2 = new JLabel("<html>" + "Graphs" + "</html>");
        panel2.setHorizontalAlignment(SwingConstants.CENTER);
        panel2.setVerticalAlignment(SwingConstants.CENTER);
        panel2.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        panel2.setFont(labels);
        panel2.setPreferredSize(new Dimension(300,800));    //TODO
        
        panel4 = new JLabel("Header");
        panel4.setFont(labels);
        panel4.setHorizontalAlignment(SwingConstants.CENTER);
        panel4.setVerticalAlignment(SwingConstants.CENTER);
        panel4.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        
        tabbedPane = new JTabbedPane();
        tabbedPane.setPreferredSize(new Dimension(1000,800));   //TDOD
        
        tab1 = new JPanel();
        tab1.setLayout(new FlowLayout());
        tab1.setPreferredSize(new Dimension(1650,2000));    //TODO
        tab1.add(panel1);
        tab1.add(table1);  
        tab1.add(panel2);
        tab2 =  new JPanel();
        tab3 = new JPanel();
    
        scrollBar = new JScrollPane(tab1,       //TODO Set layout for multiple panels
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollBar.setSize(1650,800);
        scrollBar.setLocation(0,100);
        scrollBar.setBorder(BorderFactory.createLineBorder(Color.PINK, 10));
        scrollBar.getVerticalScrollBar().setUnitIncrement(25);
        
        tabbedPane.addTab("Practice", scrollBar);
        tabbedPane.addTab("Meet Times", tab2);
        tabbedPane.addTab("Settings", tab3);
        
        mainWindow.getContentPane().add(tabbedPane, BorderLayout.CENTER);
        mainWindow.add(panel4, BorderLayout.NORTH);
    }
    
}


