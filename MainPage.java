/*
 * TODO
 * Warp table text
 *  -Clear selected Cell
 * Put all data into seperate file
 *  -privitize that file
 * Add Graphs
 * Add Side information
 *  -Set up side information
 *  -Set up options (might need its own class)
 *  -Opens on Default
 *  
 * Add File memory
 * Update welcome Frame
 * Finalize loadout
 * 
 */


import javax.jws.soap.SOAPBinding.Style;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.table.*;

public class MainPage {
     
    public static void main(String args[]) {
        //MainPage program = new MainPage();
        //program.PrepareGUI();
        //WelcomeFrame introduction = new WelcomeFrame();
        //introduction.Initialize();
    }

    static JFrame mainWindow;
     //Labels
     static JLabel panel1; //TODO
     static JLabel panel2; //TODO
     static JLabel panel3; //TODO
     static JLabel panel4; //TODO
     //Panels
     static JPanel mainTab;
     static JPanel pastPracticeTab;
     static JPanel settingsTab;
     //Buttons
     //Main Table
     Dimension T = new Dimension(1650,800);
     static JTable mainTable;
     static JScrollPane tableScrollArea;
     String[] columnNames = {"Set","Rounds","Reps","Distance",  //TODO
         "Description","Minutes","Seconds","Total Distance", "Total Time"}; 
     Object[][] data = new Object[30][9];
     static Font tableFont = new Font("Arial", Font.PLAIN, 16);
     //Side Panel
     static JPanel sidePanel;
     static JLabel side1;
     static JLabel side2;
     static JLabel side3;
     static JLabel side4;
     static String side1Data;
     static String side2Data;
     static String side3Data;
     static String side4Data;
     
     //Special
     static JScrollPane mainScrollArea;
     static Font labels = new Font("Fuse", Font.BOLD, 32); //TODO
     static JTabbedPane tabbedPane;
    
    public MainPage() {
        mainWindow = new JFrame("Swim Program");
        mainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainWindow.setMinimumSize(new Dimension(1650,800));
        mainWindow.setLayout(new BorderLayout());
        mainWindow.setVisible(false);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void setJTableColumnsWidth(JTable table, int tablePreferredWidth,
        double... percentages) {
        double total = 0;
        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            total += percentages[i];
        }
 
        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth((int)
                (tablePreferredWidth * (percentages[i] / total)));
        }
    }
    
    public void clearRow(KeyEvent e) {  //TODO does not work
        int column = 0;
        int row = 0;
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_DELETE) {
            column = mainTable.getSelectedColumn();
            row = mainTable.getSelectedRow();
            mainTable.setValueAt(null, row, column);
        }
    }
    
    public void TableSetUp() {
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        mainTable = new JTable(model);
        mainTable.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        mainTable.setRowHeight(40);    //individual is (row # (from 0), height
        setJTableColumnsWidth(mainTable, 1250, 5, 5, 5, 15, 30, 10, 10, 10, 10);
        mainTable.setFont(tableFont);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        mainTable.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        for(int x = 0 ;x < columnNames.length - 1; x++){
            mainTable.getColumnModel().getColumn(x).setCellRenderer( centerRenderer );
           }    //TODO for each data type
        
        JTextField textField = new JTextField();
        textField.setFont(tableFont);
        textField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        DefaultCellEditor dce = new DefaultCellEditor( textField );
        for(int i = 0; i < columnNames.length - 1; i++) {
            mainTable.getColumnModel().getColumn(i).setCellEditor(dce);
        }

        //mainTableR.setFocusable(true);
       // mainTableR.addKeyListener(null);
        
        tableScrollArea = new JScrollPane(mainTable);
        tableScrollArea.setPreferredSize(new Dimension(1250,800));
        mainTab.add(tableScrollArea);
    }

    public void SidePanelLabels(JLabel sideName) {
        sideName.setFont(labels);
        sideName.setBorder(BorderFactory.createLineBorder(Color.red, 2));
        sideName.setHorizontalAlignment(SwingConstants.CENTER);
        sideName.setVerticalAlignment(SwingConstants.CENTER);
        sideName.setPreferredSize(new Dimension(280,100));
        sidePanel.add(sideName);
    }
    
    public void SidePanelSetUp() {
        sidePanel = new JPanel();
        sidePanel.setPreferredSize(new Dimension(300,800));
        sidePanel.setLayout(new FlowLayout());
        sidePanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        side1 = new JLabel();
        side2 = new JLabel();
        side3 = new JLabel();
        side4 = new JLabel();
        SidePanelLabels(side1);
        SidePanelLabels(side2);
        SidePanelLabels(side3);
        SidePanelLabels(side4);
        
        side1.setText(side1Data);
        
        mainTab.add(sidePanel);
    }
    
    public void SidePanelSetUpOld() {
        panel1 = new JLabel("<html>" + "Side Information Panel" + "</html>");
        panel1.setHorizontalAlignment(SwingConstants.CENTER);
        panel1.setVerticalAlignment(SwingConstants.CENTER);
        panel1.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        panel1.setFont(labels);
        panel1.setPreferredSize(new Dimension(300,800));    //TODO
        mainTab.add(panel1);
    }
    
    public void GraphSetUp() {
        panel2 = new JLabel("<html>" + "Graphs" + "</html>");
        panel2.setHorizontalAlignment(SwingConstants.CENTER);
        panel2.setVerticalAlignment(SwingConstants.CENTER);
        panel2.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        panel2.setFont(labels);
        panel2.setPreferredSize(new Dimension(300,800));    //TODO
        mainTab.add(panel2);
    }
    
    public void HeaderSetUp() {
        panel4 = new JLabel("Header");
        panel4.setFont(labels);
        panel4.setHorizontalAlignment(SwingConstants.CENTER);
        panel4.setVerticalAlignment(SwingConstants.CENTER);
        panel4.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        mainWindow.add(panel4, BorderLayout.NORTH);
    }
    
    public void PrepareGUI() {    
        
        mainTab = new JPanel();
        mainTab.setLayout(new FlowLayout());
        mainTab.setPreferredSize(new Dimension(1650,2000));  //TODO
        tabbedPane = new JTabbedPane();
        tabbedPane.setPreferredSize(new Dimension(1000,800));   //TDOD
        //SidePanelSetUpOld();
        SidePanelSetUp();
        TableSetUp();
        GraphSetUp();
        
        pastPracticeTab =  new JPanel();
        settingsTab = new JPanel();
    
        mainScrollArea = new JScrollPane(mainTab,       //TODO Set layout for multiple panels
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        mainScrollArea.setSize(1650,800);
        mainScrollArea.setLocation(0,100);
        mainScrollArea.setBorder(BorderFactory.createLineBorder(Color.PINK, 10));
        mainScrollArea.getVerticalScrollBar().setUnitIncrement(25);
        
        tabbedPane.addTab("Practice", mainScrollArea);
        tabbedPane.addTab("Meet Times", pastPracticeTab);
        tabbedPane.addTab("Settings", settingsTab);
        
        mainWindow.getContentPane().add(tabbedPane, BorderLayout.CENTER);
        HeaderSetUp();
    }
    
}


