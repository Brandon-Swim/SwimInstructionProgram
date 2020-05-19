/*
 * TODO
 * Warp table text
 *  -Clear selected Cell
 * Put all data into seperate file
 *  -privitize that file
 * Add Graphs
 *  -XChart
 * Add Side information
 *  -Set up side information
 *  -Set up options (might need its own class)
 *  -Opens on Default
 *  
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

public class MainPage implements TableModelListener{

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
     static Object[][] storage = new Object[30][9];
     
     static Font tableFont = new Font("Arial", Font.PLAIN, 16);
     static JPanel tableHolder;
     static JScrollPane table1Pane;
     static JScrollPane group2Pane;
     static JScrollPane group3Pane;
     static JScrollPane group4Pane;
     static JScrollPane group5Pane;
     
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
     
     //Special
     static JScrollPane mainScrollArea;
     static Font labels = new Font("Fuse", Font.BOLD, 48); //TODO
     static JTabbedPane tabbedPane;
    
    public MainPage() {
        mainWindow = new JFrame("Swim Program");
        mainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainWindow.setMinimumSize(new Dimension(1650,800));
        mainWindow.setLayout(new BorderLayout());
        mainWindow.setVisible(false);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        for (int i = 0; i < storage.length; i++) {   //Sets all to 0.
            for (int j = 0; j < storage[i].length; j++) {
                storage[i][j] = 0;
            }
        }
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
        
    }
    
    public void TableSetUp() {
        
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        model.addTableModelListener(this);
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

    public void tableChanged(TableModelEvent e) {	
        int row = e.getFirstRow();
        int column = e.getColumn();
        int tempInt = 0;
        int tempInt2 = 0;
        int count = 1;
        TableModel model = (TableModel)e.getSource();
        Object dataPoint = model.getValueAt(row, column);
        data[row][column] = dataPoint;  //updates Data
        System.out.println(Arrays.deepToString(data));    //Debug
        System.out.println(Arrays.deepToString(storage));
        
            for (int i = 0; i < data.length; i++) {     //Update storage array
                for (int j = 0; j < data[i].length; j++) {
                    storage[i][j] = data[i][j];
                    if (storage[i][j] == null) {
                        storage[i][j] = 0;
                    }
                }
            }
            System.out.println(Arrays.deepToString(storage));
            for (int i = 0; i < storage.length; i++) {
                if (i != 0 && storage[i][0].equals(storage[i-1][0])) {
                    storage[i][1] = storage[i-1][1];
                } 
            }
            System.out.println(Arrays.deepToString(storage));
        
        //Update Total Distance
        for (int i = 0; i < data.length; i++) {
            if (data[i][0] != null && data[i][2] != null && 
                data[i][3] != null && !data[i][0].equals("") && 
                !data[i][2].equals("") & !data[i][3].equals("")) {
                if (storage[i][1] != null && !storage[i][1].equals(0) && 
                    !storage[i][1].equals("")) {    
                    tempInt += Integer.valueOf(storage[i][3].toString()) * 
                        Integer.valueOf(storage[i][2].toString()) *
                        Integer.valueOf(storage[i][1].toString());
                } else if (storage[i][1] == null || storage[i][1].equals(0) || 
                    storage[i][1].equals("")) {
                            tempInt += Integer.valueOf(storage[i][3].toString()) * 
                            Integer.valueOf(storage[i][2].toString());
                    }
            }
        }
        ttlDistance = Integer.toString(tempInt);
        tempInt = 0;
        
        //Update Total Time
            for (int i = 0; i < data.length; i++) {
                if (data[i][6] != null && !data[i][6].equals("")) {
                    tempInt += Integer.valueOf(data[i][6].toString());
                }
                if (data[i][7] != null && !data[i][7].equals("")) {
                    tempInt2 += Integer.valueOf(data[i][7].toString());
                    if (tempInt2 >= 60) {
                        tempInt2 -= 60;
                        tempInt += 1;
                    }
                }
            }
        ttlTimeMin = Integer.toString(tempInt);
        ttlTimeSec = Integer.toString(tempInt2);
        tempInt= 0;
        tempInt2 = 0;
        
        //Update Average Intensity
            for (int i = 0; i < data.length; i++) {
                if (data[i][8] != null && !data[i][8].equals("")) {
                    tempInt += Integer.valueOf(data[i][8].toString());
                    tempInt2 += 1;
                }
            }
            if (tempInt2 != 0) {
                tempInt /= tempInt2;
            }

        avgIntensity = Integer.toString(tempInt);
        tempInt = 0;
        tempInt2 = 0;
        /*
        for (int i = 0; i < data.length; i++) {     //Update storage array
            for (int j = 0; j < data[i].length; j++) {
                storage[i][j] = data[i][j];
                if (storage[i][j] == null) {
                    storage[i][j] = 0;
                }
            }
        }
        */
        System.out.println(Arrays.deepToString(data));
        System.out.println(Arrays.deepToString(storage));
        ttlDistancePanel.setText("Total Distance: " + ttlDistance 
            + " yds");
        if (Integer.valueOf(ttlTimeSec) == 0) {
            ttlTimePanel.setText("Total Time: " + ttlTimeMin + 
                " min "); 
        } else {
            ttlTimePanel.setText("Total Time: " + ttlTimeMin + 
                " min " + ttlTimeSec + " Sec"); 
        }
        avgIntensityPanel.setText("Intensity: " + 
        avgIntensity + "%");
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
        avgIntensityPanel.setText("Intensity: " + avgIntensity + "%");
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
        addGroup.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
             currentTable += 1;
             switch (currentTable) {
                 case 1: 
                     tableHolder.add(group2);
                     tableHolder.add(group2Pane);
                     SwingUtilities.updateComponentTreeUI(mainWindow);
                     break;
                 case 2:
                     tableHolder.add(group3);
                     tableHolder.add(group3Pane);
                     SwingUtilities.updateComponentTreeUI(mainWindow);
                     break;
                 case 3:
                     tableHolder.add(group4);
                     tableHolder.add(group4Pane);
                     SwingUtilities.updateComponentTreeUI(mainWindow);
                     break;
                 case 4:
                     tableHolder.add(group5);
                     tableHolder.add(group5Pane);
                     SwingUtilities.updateComponentTreeUI(mainWindow);
                     break;
                 default:
                     if (currentTable == 5) {
                         currentTable = 4;
                     }
                     break;
             }
         }
        });
        
        remGroup = new JButton("Remove Group");
        remGroup.setFont(buttonFont);
        remGroup.setSize(buttonDimension);
        remGroup.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
             currentTable -= 1;
             switch (currentTable) {
                 case 0: 
                     tableHolder.remove(group2);
                     tableHolder.remove(group2Pane);
                     SwingUtilities.updateComponentTreeUI(mainWindow);
                     break;
                 case 1: 
                     tableHolder.remove(group3);
                     tableHolder.remove(group3Pane);
                     SwingUtilities.updateComponentTreeUI(mainWindow);
                     break;
                 case 2: 
                     tableHolder.remove(group4);
                     tableHolder.remove(group4Pane);
                     SwingUtilities.updateComponentTreeUI(mainWindow);
                     break;
                 case 3: 
                     tableHolder.remove(group5);
                     tableHolder.remove(group5Pane);
                     SwingUtilities.updateComponentTreeUI(mainWindow);
                     break;
                 default:
                     if (currentTable == -1) {
                         currentTable = 0;
                     } 
                     break;
             }
         }
        });
        graphPanel.add(addGroup);
        graphPanel.add(remGroup);
        mainTab.add(graphPanel);    
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



