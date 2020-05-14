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
     static JTable mainTable;
     static JScrollPane tableScrollArea;
     String[] columnNames = {"Set","Rounds","Reps","Distance",  //TODO
         "Description","Type","Minutes","Seconds", "Intensity"}; 
     static Object[][] data = new Object[30][9];
     static Object[][] storage = new Object[30][9];
     
     static Font tableFont = new Font("Arial", Font.PLAIN, 16);
     
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
     
     //Special
     static JScrollPane mainScrollArea;
     static Font labels = new Font("Fuse", Font.BOLD, 24); //TODO
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
        for(int x = 0 ;x < columnNames.length; x++){
            mainTable.getColumnModel().getColumn(x).setCellRenderer( centerRenderer );
           }    //TODO for each data type
        
        JTextField textField = new JTextField();
        textField.setFont(tableFont);
        textField.setBorder(BorderFactory.createLineBorder(Color.red, 2));
        DefaultCellEditor dce = new DefaultCellEditor( textField );
        for(int i = 0; i < columnNames.length; i++) {
            mainTable.getColumnModel().getColumn(i).setCellEditor(dce);
        }

        model.addTableModelListener(this);
        //model.fireTableChanged(this);
        //mainTableR.setFocusable(true);
       // mainTableR.addKeyListener(null);
        
        tableScrollArea = new JScrollPane(mainTable);
        tableScrollArea.setPreferredSize(new Dimension(1250,800));
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



