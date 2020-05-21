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
     static JPanel importedPracticesTab;
     static JPanel settingsTab;
     
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
     static String[] columnNames = {"Set","Rounds","Reps","Distance",  //TODO
         "Description","Type","Minutes","Seconds", "Intensity"}; 
     static Object[][][] data = new Object[5][30][9];
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
     static int GroupNum = 1;
     
     //Side Panel
     static JPanel sidePanel;
     static JLabel ttlDistancePanel;
     static JLabel ttlTimePanel;
     static JLabel avgIntensityPanel;
     static JLabel side4;
     static JLabel side5;
     static JLabel side6;
     static JLabel side7;
     static JLabel side8;
     static JLabel side9;
     static JLabel side10;
     static JLabel side11;
     static String ttlDistance = "0";
     static String ttlTimeMin = "0";
     static String ttlTimeSec = "0";
     static String avgIntensity = "0";
     static String side4Data = "Type Counter";  //Displays total amount of practice types for the season
     static String side5Data = "Season Distance";   //Displays total season distance
     static String side6Data = "Practices left in the week";  //Displays practices left in the week
     static String side7Data = "Type Counter";  //Displays practices left to next competition
     static String side8Data = "Game Day Counter";  //Displays practices since last game day
     static String side9Data = "Practices until Taper";  //Displays practices until taper
     static String side10Data = "Set distance"; //Displays Individual Set distances
     static String side11Data = "Working Distance"; //Displays amount of working yards
     static Integer[] side1Column;
     static Dimension sidePanelSize = new Dimension(250,100);
     static JScrollPane sideScroll;
     
     //Control Panel
     static JPanel controlPanel;
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
     
     //Graphs
     static JLabel graph1;
     static JLabel graph2;
     static JLabel graph3;
     static JPanel graphHolder;
     static Dimension graphLabelSize =  new Dimension(600,600);
     
     //past practice viewer
     static JPanel ppHolder;
     static JPanel ppInfo;
     static JLabel ppSelector;
     static JLabel ppSummary;
     static JLabel ppTable;
     
     //Special
     static JScrollPane mainScrollArea;
     static Font labels = new Font("Fuse", Font.BOLD, 22); //TODO
     static JTabbedPane tabbedPane;
    
    
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
         DefaultTableModel modelG1 = new DefaultTableModel(data[0], columnNames);
         DefaultTableModel modelG2 = new DefaultTableModel(data[1], columnNames);
         DefaultTableModel modelG3 = new DefaultTableModel(data[2], columnNames); 
         DefaultTableModel modelG4 = new DefaultTableModel(data[3], columnNames); 
         DefaultTableModel modelG5 = new DefaultTableModel(data[4], columnNames);
        
        modelG1.addTableModelListener(Listeners.group1Change);  
        modelG2.addTableModelListener(Listeners.group2Change);  
        modelG3.addTableModelListener(Listeners.group3Change);  
        modelG4.addTableModelListener(Listeners.group4Change);  
        modelG5.addTableModelListener(Listeners.group5Change);  
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        JTextField textField = new JTextField();
        textField.setFont(tableFont);
        textField.setBorder(BorderFactory.createLineBorder(Color.red, 2));
        DefaultCellEditor cellEditor = new DefaultCellEditor( textField );
        
        mainTableGroup1 = new JTable(modelG1);
        mainTableGroup2 = new JTable(modelG2);
        mainTableGroup3 = new JTable(modelG3);
        mainTableGroup4 = new JTable(modelG4);
        mainTableGroup5 = new JTable(modelG5);
        TableCreation(mainTableGroup1, modelG1, centerRenderer, cellEditor);
        TableCreation(mainTableGroup2, modelG2, centerRenderer, cellEditor);
        TableCreation(mainTableGroup3, modelG3, centerRenderer, cellEditor);
        TableCreation(mainTableGroup4, modelG4, centerRenderer, cellEditor);
        TableCreation(mainTableGroup5, modelG5, centerRenderer, cellEditor);
   
        tableHolder = new JPanel();
        tableHolder.setLayout(new FlowLayout());
        tableHolder.setPreferredSize(new Dimension(1200,460));
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
        tableScrollArea.setBorder(BorderFactory.createEmptyBorder());
        mainTab.add(tableScrollArea);
    }

    public void TableLabels(JLabel group) {
        group.setForeground(Color.red);
        group.setPreferredSize(labelSize);
        group.setHorizontalAlignment(SwingConstants.CENTER);
        group.setVerticalAlignment(SwingConstants.CENTER);
        group.setFont(tableHeader);
    }
    
    public void PanelSetUp(JLabel sideName, Dimension size) {
        sideName.setFont(labels);
        sideName.setBorder(BorderFactory.createLineBorder(Color.red, 2));
        sideName.setHorizontalAlignment(SwingConstants.CENTER);
        sideName.setVerticalAlignment(SwingConstants.CENTER);
        sideName.setPreferredSize(size);
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
        sidePanel.setPreferredSize(new Dimension(260,1500));
        sidePanel.setLayout(new FlowLayout());
        ttlDistancePanel = new JLabel("Total Distance: " + ttlDistance + "yds");
        ttlTimePanel = new JLabel();
        avgIntensityPanel = new JLabel();
        side4 = new JLabel();
        side5 = new JLabel();
        side6 = new JLabel();
        side7 = new JLabel();
        side8 = new JLabel();
        side9 = new JLabel();
        side10 = new JLabel();
        side11= new JLabel();
        PanelSetUp(ttlDistancePanel, sidePanelSize);
        PanelSetUp(ttlTimePanel, sidePanelSize);
        PanelSetUp(avgIntensityPanel, sidePanelSize);
        PanelSetUp(side4, sidePanelSize);
        PanelSetUp(side5, sidePanelSize);
        PanelSetUp(side6, sidePanelSize);
        PanelSetUp(side7, sidePanelSize);
        PanelSetUp(side8, sidePanelSize);
        PanelSetUp(side9, sidePanelSize);
        PanelSetUp(side10, sidePanelSize);
        PanelSetUp(side11, sidePanelSize);
        sidePanel.add(ttlDistancePanel);
        sidePanel.add(ttlTimePanel);
        sidePanel.add(avgIntensityPanel);
        sidePanel.add(side4);
        sidePanel.add(side5);
        sidePanel.add(side6);
        sidePanel.add(side7);
        sidePanel.add(side8);
        sidePanel.add(side9);
        sidePanel.add(side10);
        sidePanel.add(side11);
        
        ttlDistancePanel.setText("Total Distance: " + ttlDistance + " yds");
        ttlTimePanel.setText("Total Time: " + ttlTimeMin + " min ");
        avgIntensityPanel.setText("Avg Intensity: " + avgIntensity + "%");
        side4.setText(side4Data);
        side5.setText(side5Data);
        side6.setText(side6Data);
        side7.setText(side7Data);
        side8.setText(side8Data);
        side9.setText(side9Data);
        side10.setText(side10Data);
        side11.setText(side11Data);
        sideScroll = new JScrollPane(sidePanel,       //TODO Set layout for multiple panels
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sideScroll.setPreferredSize(new Dimension(300,800));
        sideScroll.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        sideScroll.getVerticalScrollBar().setUnitIncrement(25);
        mainTab.add(sideScroll);
    }

    public void ControlPanelSetUp() {
        controlPanel = new JPanel();
        controlPanel.setPreferredSize(new Dimension(300,800));  
        controlPanel.setLayout(new FlowLayout());
        controlPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
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
        
        controlPanel.add(addGroup);
        controlPanel.add(remGroup);
        controlPanel.add(headerNameLabel);
        controlPanel.add(headerNameChange);
        controlPanel.add(headerMonthLabel);
        controlPanel.add(headerMonthChange);
        controlPanel.add(headerDayLabel);
        controlPanel.add(headerDayChange);
        mainTab.add(controlPanel);    
    }
 
    public void HeaderSetUp() {
        header = new JLabel(headerFinal);
        header.setLayout(new FlowLayout());
        header.setFont(headerFont);
        header.setVerticalAlignment(SwingConstants.CENTER);
        header.setHorizontalAlignment(SwingConstants.CENTER);
        header.setForeground(Color.LIGHT_GRAY);
        header.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        finalHolder.mainWindow.add(header, BorderLayout.NORTH);
    }
    
    public void GraphSetUp() {
        graphHolder = new JPanel();
        graphHolder.setLayout(new FlowLayout());
        graphHolder.setPreferredSize(new Dimension(1860,630));
        graphHolder.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        graph1 = new JLabel("Graph 1");
        graph2 = new JLabel("Graph 2");
        graph3 = new JLabel("Graph 3");
        PanelSetUp(graph1, graphLabelSize);
        PanelSetUp(graph2, graphLabelSize);
        PanelSetUp(graph3, graphLabelSize);
        
        graphHolder.add(graph1);
        graphHolder.add(graph2);
        graphHolder.add(graph3);
        mainTab.add(graphHolder); 
    }

    public void PastPracticeSetUp() {
        ppHolder = new JPanel();
        ppHolder.setLayout(new FlowLayout());
        ppHolder.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        ppHolder.setPreferredSize(new Dimension(1860,850));
        
        ppInfo =  new JPanel();
        ppInfo.setLayout(new FlowLayout());
        ppInfo.setBorder(BorderFactory.createLineBorder(Color.gray, 2));
        ppInfo.setPreferredSize(new Dimension(590,820));
        
        ppSelector = new JLabel("Past Practice Selector");
        ppTable = new JLabel("Past Practice Table");
        ppSummary = new JLabel("Past Practice Summary");
        PanelSetUp(ppSelector, new Dimension(550,400));
        PanelSetUp(ppTable, new Dimension(1250,800));
        PanelSetUp(ppSummary, new Dimension(550,400));
        ppInfo.add(ppSelector);
        ppInfo.add(ppSummary);
        ppHolder.add(ppInfo);
        ppHolder.add(ppTable);
        mainTab.add(ppHolder);
        
    }
    
    public void PrepareGUI() {    

        mainTab = new JPanel();
        mainTab.setLayout(new FlowLayout());
        mainTab.setPreferredSize(new Dimension(1650,2400));  //TODO
        SidePanelSetUp();
        TableSetUp();
        ControlPanelSetUp();
        GraphSetUp();
        PastPracticeSetUp();
        HeaderSetUp();
    
        mainScrollArea = new JScrollPane(mainTab,       //TODO Set layout for multiple panels
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        mainScrollArea.setBorder(BorderFactory.createLineBorder(Color.green, 2));
        mainScrollArea.getVerticalScrollBar().setUnitIncrement(25);
    }
    
}



