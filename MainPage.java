/*
 * TODO
 * Wrap table text
 *  -Clear selected Cell
 * Put all data into seperate file
 *  -privitize that file
 * Add Graphs
 *  -XChart
 * Add Side information
 *  -Set up side information
 *  -Set up options (might need its own class)
 *  -Opens on Default
 * Put all listeners in their own class
 * Add File memory
 * Update welcome Frame
 * Finalize loadout
 * Bus Break Line in table, changeable
 */


import javax.jws.soap.SOAPBinding.Style;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.table.*;
import javax.swing.event.*;

public class MainPage {

     static JFrame mainWindow;
     
     //Header
     static JLabel header; 
     static Font headerFont = new Font("Arial", Font.BOLD, 48);
     static String headerName = "Workout";
     static String headerMonth = "MM";
     static String headerDay = "DD";
     static String headerFinal = headerMonth + "/" + headerDay + ": " + headerName;
     
     //Tabs
     static JPanel mainTab;
     static JPanel pastPracticeTab;
     static JPanel settingsTab;
     //Buttons
     
     //Main Table
     Dimension T = new Dimension(1650,800);
     static Font tableHeader = new Font("Arial", Font.BOLD, 32);
     static Dimension labelSize = new Dimension(1220,50);
     static Dimension tableSize = new Dimension(1220,400);
     static JTable mainTableGroup1;
     static JLabel group1;
     static JLabel group2;
     static JLabel group3;
     static JLabel group4;
     static JLabel group5;
     static JTable mainTableGroup2;
     static JTable mainTableGroup3;
     static JTable mainTableGroup4;
     static JTable mainTableGroup5; //TODO maybe?
     static JScrollPane tableScrollArea;
     String[] columnNames = {"Set","Rounds","Reps","Distance",  //TODO
         "Description","Type","Minutes","Seconds", "Intensity"}; 
     static Object[][] data = new Object[30][9];
     static Font tableFont = new Font("Arial", Font.PLAIN, 16);
     static JPanel tableHolder;
     static JScrollPane table1Pane;
     static JScrollPane group2Pane;
     static JScrollPane group3Pane;
     static JScrollPane group4Pane;
     static JScrollPane group5Pane;
     static String[] types = {"","Warm Up","Fly","Free","Back","Breast",
         "IM","Drill","Kick","Starts","Sprint"};
     static JComboBox<String> typeSelector = new JComboBox<>(types);
     static TableColumn typeColumn;
     
     //Side Panel
     static JPanel sidePanel;
     static JLabel ttlDistancePanel;
     static JLabel ttlTimePanel;
     static JLabel avgIntensityPanel;
     static JLabel side4;
     static String ttlDistance = "0";
     static String ttlTimeMin = "0";
     static String ttlTimeSec = "0";
     static String avgIntensity = "0";
     static String side4Data = "0";
     static Integer[] side1Column;
     
     //Graph Panel
     static JPanel graphPanel;
     static JButton addGroup;
     static JButton remGroup;
     static Font buttonFont = new Font("Arial", Font.PLAIN, 12);
     static Dimension buttonDimension = new Dimension(150,100);
     static int currentTable = 0;
     static JTextField headerNameChange;
     static JTextField headerMonthChange;
     static JTextField headerDayChange;
     static JLabel headerNameLabel;
     static JLabel headerMonthLabel;
     static JLabel headerDayLabel;
     
     //Special
     static JScrollPane mainScrollArea;
     static Font labels = new Font("Fuse", Font.BOLD, 22); //TODO
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
            column = mainTableGroup1.getSelectedColumn();
            row = mainTableGroup1.getSelectedRow();
            mainTableGroup1.setValueAt(null, row, column);
        }
    }
    
    public void TableCreation(JTable table, DefaultTableModel model, 
        DefaultTableCellRenderer render, DefaultCellEditor editor) { //TODO update renderer and editor
       
        table.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        table.setRowHeight(40);    //individual is (row # (from 0), height
        setJTableColumnsWidth(table, 1250, 5, 5, 5, 15, 30, 10, 10, 10, 10);
        table.setFont(tableFont);
        table.getColumnModel().getColumn(0).setCellRenderer( render );
        for(int x = 0 ;x < columnNames.length; x++){
            table.getColumnModel().getColumn(x).setCellRenderer( render );
           }
        for(int i = 0; i < columnNames.length; i++) {
            table.getColumnModel().getColumn(i).setCellEditor(editor);
        }
        typeColumn = table.getColumnModel().getColumn(5);
        typeColumn.setCellEditor(new DefaultCellEditor(typeSelector)); 
    }
    
    public void TableSetUp() {
        
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        model.addTableModelListener(Listeners.TableChange);  //TODO
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        JTextField textField = new JTextField();
        textField.setFont(tableFont);
        textField.setBorder(BorderFactory.createLineBorder(Color.red, 2));
        DefaultCellEditor cellEditor = new DefaultCellEditor( textField );
        
        mainTableGroup1 = new JTable(model);
        mainTableGroup2 = new JTable(model);
        mainTableGroup3 = new JTable(model);
        mainTableGroup4 = new JTable(model);
        mainTableGroup5 = new JTable(model);
        TableCreation(mainTableGroup1, model, centerRenderer, cellEditor);
        TableCreation(mainTableGroup2, model, centerRenderer, cellEditor);
        TableCreation(mainTableGroup3, model, centerRenderer, cellEditor);
        TableCreation(mainTableGroup4, model, centerRenderer, cellEditor);
        TableCreation(mainTableGroup5, model, centerRenderer, cellEditor);
   
        tableHolder = new JPanel();
        tableHolder.setLayout(new FlowLayout());
        tableHolder.setPreferredSize(new Dimension(1200,2400));
        table1Pane = new JScrollPane(mainTableGroup1);
        table1Pane.setPreferredSize(tableSize);
        group2Pane = new JScrollPane(mainTableGroup2);
        group2Pane.setPreferredSize(tableSize);
        group3Pane = new JScrollPane(mainTableGroup3);
        group3Pane.setPreferredSize(tableSize);
        group4Pane = new JScrollPane(mainTableGroup4);
        group4Pane.setPreferredSize(tableSize);
        group5Pane = new JScrollPane(mainTableGroup5);
        group5Pane.setPreferredSize(tableSize);
        
        group1 = new JLabel("Group 1 Workout");
        group2 = new JLabel("Group 2 Workout");
        group3 = new JLabel("Group 3 Workout");
        group4 = new JLabel("Group 4 Workout");
        group5 = new JLabel("Group 5 Workout");
        TableLabels(group1);
        TableLabels(group2);
        TableLabels(group3);
        TableLabels(group4);
        TableLabels(group5);
        
        tableHolder.add(group1);
        tableHolder.add(table1Pane);
        tableScrollArea = new JScrollPane(tableHolder);
        tableScrollArea.setPreferredSize(new Dimension(1250,800));
        tableScrollArea.getVerticalScrollBar().setUnitIncrement(50);
        mainTab.add(tableScrollArea);
    }

    public void TableLabels(JLabel group) {
        group.setForeground(Color.red);
        group.setPreferredSize(labelSize);
        group.setHorizontalAlignment(SwingConstants.CENTER);
        group.setVerticalAlignment(SwingConstants.CENTER);
        group.setFont(tableHeader);
    }
    
    public void SidePanelLabels(JLabel sideName) {
        sideName.setFont(labels);
        sideName.setBorder(BorderFactory.createLineBorder(Color.red, 2));
        sideName.setHorizontalAlignment(SwingConstants.CENTER);
        sideName.setVerticalAlignment(SwingConstants.CENTER);
        sideName.setPreferredSize(new Dimension(280,100));
        sidePanel.add(sideName);
    }
    
    public static Object[] getColumn(Object[][] array, int index){
        Object[] column = new Object[array.length];
        for(int i = 0; i < column.length; i++){
               column[i] = array[i][index];
        }
        return column;
    }
    
    public static Object average(Object[] array) {
        Object sum = 0;
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] != null && 
                array[i].getClass().equals(String.class) && 
                array[i].toString().length() != 0) {
                sum = Integer.valueOf(sum.toString()) + 
                    Integer.valueOf(array[i].toString());
            }
        }
        return sum;
    }
    
    public void SidePanelSetUp() {
        sidePanel = new JPanel();
        sidePanel.setPreferredSize(new Dimension(300,800));
        sidePanel.setLayout(new FlowLayout());
        sidePanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        ttlDistancePanel = new JLabel("Total Distance: " + ttlDistance + "yds");
        ttlTimePanel = new JLabel();
        avgIntensityPanel = new JLabel();
        side4 = new JLabel();
        SidePanelLabels(ttlDistancePanel);
        SidePanelLabels(ttlTimePanel);
        SidePanelLabels(avgIntensityPanel);
        SidePanelLabels(side4);

        ttlDistancePanel.setText("Total Distance: " + ttlDistance + " yds");
        ttlTimePanel.setText("Total Time: " + ttlTimeMin + " min ");
        avgIntensityPanel.setText("Avg Intensity: " + avgIntensity + "%");
        side4.setText(side4Data);
        mainTab.add(sidePanel);
    }

    public void GraphSetUp() {
        graphPanel = new JPanel();
        graphPanel.setPreferredSize(new Dimension(300,800));  
        graphPanel.setLayout(new FlowLayout());
        graphPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        addGroup = new JButton("Add Group");
        addGroup.setFont(buttonFont);
        addGroup.setSize(buttonDimension);
        addGroup.addActionListener(Listeners.AddGroup);
        remGroup = new JButton("Remove Group");
        remGroup.setFont(buttonFont);
        remGroup.setSize(buttonDimension);
        remGroup.addActionListener(Listeners.RemGroup);
        
        headerNameChange = new JTextField(headerName);
        headerNameChange.setForeground(Color.LIGHT_GRAY);
        headerNameChange.setFont(labels);
        headerNameChange.setPreferredSize(new Dimension(200,50));
        headerNameChange.setHorizontalAlignment(SwingConstants.CENTER);
        headerNameChange.addMouseListener(Listeners.HeaderNameMouse);
        headerNameChange.addActionListener(Listeners.HeaderNameText);   //hitting Enter
        headerNameChange.addFocusListener(Listeners.HeaderNameClick);  //Clicking off
        
        headerMonthChange = new JTextField(headerMonth);
        headerMonthChange.setForeground(Color.LIGHT_GRAY);
        headerMonthChange.setFont(labels);
        headerMonthChange.setPreferredSize(new Dimension(50,50));
        headerMonthChange.setHorizontalAlignment(SwingConstants.CENTER);
        headerMonthChange.addMouseListener(Listeners.HeaderMonthMouse);
        headerMonthChange.addActionListener(Listeners.HeaderMonthText);
        headerMonthChange.addFocusListener(Listeners.HeaderMonthClick);
        
        headerDayChange = new JTextField(headerDay);
        headerDayChange.setForeground(Color.LIGHT_GRAY);
        headerDayChange.setFont(labels);
        headerDayChange.setPreferredSize(new Dimension(50,50));
        headerDayChange.setHorizontalAlignment(SwingConstants.CENTER);
        headerDayChange.addMouseListener(Listeners.HeaderDayMouse);
        headerDayChange.addActionListener(Listeners.HeaderDayText);
        headerDayChange.addFocusListener(Listeners.HeaderDayClick);
        
        headerNameLabel = new JLabel("Name:");
        headerNameLabel.setFont(labels);
        headerNameLabel.setPreferredSize(new Dimension(80,50));
        headerNameLabel.setForeground(Color.black);
        
        headerMonthLabel = new JLabel("Month:");
        headerMonthLabel.setFont(labels);
        headerMonthLabel.setPreferredSize(new Dimension(80,50));
        headerMonthLabel.setForeground(Color.black);
        
        headerDayLabel = new JLabel("  Day:");
        headerDayLabel.setFont(labels);
        headerDayLabel.setPreferredSize(new Dimension(80,50));
        headerDayLabel.setForeground(Color.black);
        
        graphPanel.add(addGroup);
        graphPanel.add(remGroup);
        graphPanel.add(headerNameLabel);
        graphPanel.add(headerNameChange);
        graphPanel.add(headerMonthLabel);
        graphPanel.add(headerMonthChange);
        graphPanel.add(headerDayLabel);
        graphPanel.add(headerDayChange);
        mainTab.add(graphPanel);    
    }
 
    public void HeaderSetUp() {
        header = new JLabel(headerFinal);
        header.setLayout(new FlowLayout());
        header.setFont(headerFont);
        header.setVerticalAlignment(SwingConstants.CENTER);
        header.setHorizontalAlignment(SwingConstants.CENTER);
        header.setForeground(Color.LIGHT_GRAY);
        header.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        mainWindow.add(header, BorderLayout.NORTH);
    }
    
    public void PrepareGUI() {    
        
        mainTab = new JPanel();
        mainTab.setLayout(new FlowLayout());
        mainTab.setPreferredSize(new Dimension(1650,2000));  //TODO
        tabbedPane = new JTabbedPane();
        tabbedPane.setPreferredSize(new Dimension(1000,800));   //TDOD
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



