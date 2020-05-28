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


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.table.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class MainPage {
     
    public static void main(String args[]) { 
        finalHolder.main(args);
        
    }
    
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
     static String[] types = {"Warm Up","Fly","Free","Back","Breast",
         "IM","Drill","Kick","Starts","Sprint","Loosen"};
     static JComboBox<String> typeSelector = new JComboBox<>(types);
     static TableColumn typeColumn;
     static int GroupNum = 1;
     
     //Side Panel
     /* 
      * Displays total distance
      * Displays toal time
      * Displays Intensity
      * Working Distance (not in warm up or easy)
      * Working Time (not in warm up or easy)
      * Working Intensity (not in warm up or easy)
      * Displays total amount of practice types for the season
      * Displays total season distance
      * Displays practices left in the week
      * Displays practices left to next competition
      * Displays practices since last game day
      * Displays practices until taper
      * Displays Individual Set distances
      * Displays amount of working yards
      */
     static String[] sideLabel = new String[] {"Total Distance: 0 yds", 
         "Total Time: 0 min", "Avg Intensity: 0%", "<html> Working Distance: "
             + "<br/><center>0 yds<center></html>", 
         "Working Time: 0 min","Working Intensity: 0%","T Type Counter", 
         "T Season Distance","T Practices left in the week", "T Practices til Comp", 
         "T Game Day Counter","T Practices until Taper", "Set distance"};
     static JLabel[] sideData =  new JLabel[sideLabel.length]; //TODO nice
     static JPanel sidePanel;
     static Integer[] side1Column;
     static Dimension sidePanelSize = new Dimension(250,100);
     static JScrollPane sideScroll;
     
     //Control Panel
     static JPanel controlPanel;
     static JButton addGroup;
     static JButton remGroup;
     static Font buttonFont = new Font("Arial", Font.PLAIN, 12);
     static Dimension buttonDimension = new Dimension(120,20);
     static int currentTable = 0;
     static JTextField headerNameChange;
     static JTextField headerMonthChange;
     static JTextField headerDayChange;
     static JLabel headerNameLabel;
     static JLabel headerMonthLabel;
     static JLabel headerDayLabel;
     static JLabel controlPanelLabel;
     static JTextArea description;
     static String descriptionText = "Description";
     static Font smallLabels = new Font("Arial", Font.PLAIN, 20);
     static JLabel descriptionLabel;
     static JButton gameDay;
     static JPanel graphSelector;
     static JRadioButton[] groupGraph = new JRadioButton[5];
     static JLabel[] groupGraphLabels = new JLabel[5];
     static Font graphLabels = new Font("Arial", Font.PLAIN, 14);
     
     //Graphs
     static JPanel graphHolder;
     final static int amtCharts = 3;
     static ChartPanel[] chartHolders = new ChartPanel[amtCharts];
     static JFreeChart[] charts = new JFreeChart[amtCharts];
     static DefaultPieDataset[] ringChartData = new DefaultPieDataset[amtCharts];
     static String[] setDataLabels = new String[] {"Set 1", "Set 2","3"
         ,"Set 4","Set 5","Set 6","Set 7","Set 8","Set 9","Set 10",};
     static Dimension chartSize = new Dimension(600,600);
     
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
        table.setFocusable(true);
        table.addKeyListener(Listeners.delCell1);
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

        mainTableGroup1 = new JTable(modelG1);  //TODO make into array
        mainTableGroup2 = new JTable(modelG2);
        mainTableGroup3 = new JTable(modelG3);
        mainTableGroup4 = new JTable(modelG4);
        mainTableGroup5 = new JTable(modelG5);
        TableCreation(mainTableGroup1, modelG1, centerRenderer, cellEditor);
        TableCreation(mainTableGroup2, modelG2, centerRenderer, cellEditor);
        TableCreation(mainTableGroup3, modelG3, centerRenderer, cellEditor);
        TableCreation(mainTableGroup4, modelG4, centerRenderer, cellEditor);
        TableCreation(mainTableGroup5, modelG5, centerRenderer, cellEditor);
        mainTableGroup1.addKeyListener(Listeners.delCell1); //TODO clear row CTRL + DEL
        mainTableGroup2.addKeyListener(Listeners.delCell2);
        mainTableGroup3.addKeyListener(Listeners.delCell3);
        mainTableGroup4.addKeyListener(Listeners.delCell4);
        mainTableGroup5.addKeyListener(Listeners.delCell5);
   
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
      
    public void SidePanelSetUp() {
        sidePanel = new JPanel();
        sidePanel.setPreferredSize(new Dimension(260,1500));
        sidePanel.setLayout(new FlowLayout());
        for (int i = 0; i < sideData.length; i++) {
            sideData[i] = new JLabel(sideLabel[i]);
            PanelSetUp(sideData[i], sidePanelSize);
            sidePanel.add(sideData[i]);
        }

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
        addGroup.setPreferredSize(buttonDimension);
        addGroup.addActionListener(Listeners.AddGroup);
        remGroup = new JButton("Remove Group");
        remGroup.setFont(buttonFont);
        remGroup.setPreferredSize(buttonDimension);
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
        
        controlPanelLabel = new JLabel("Control Panel");
        PanelSetUp(controlPanelLabel, new Dimension(280,50));
        
        description = new JTextArea(descriptionText);
        description.setForeground(Color.LIGHT_GRAY);
        description.setFont(smallLabels);
        description.setPreferredSize(new Dimension(200,250));
        description.setLineWrap(true);
        description.addMouseListener(Listeners.descriptionMouse);

        descriptionLabel = new JLabel("Description: ");
        PanelSetUp(descriptionLabel, new Dimension(200,50));
        descriptionLabel.setBorder(null);
        
        gameDay = new JButton("Game Day");
        gameDay.setFont(buttonFont);
        gameDay.setPreferredSize(buttonDimension);
        //gameDay.addActionListener(); TODO
        
        groupGraph[0] = new JRadioButton();
        groupGraph[0].setSelected(true);
        groupGraph[0].setMnemonic(KeyEvent.VK_1);
        groupGraph[0].addItemListener(Listeners.graph1Display);
        groupGraph[1] =  new JRadioButton();
        groupGraph[1].setMnemonic(KeyEvent.VK_2);
        groupGraph[1].addItemListener(Listeners.graph2Display);
        groupGraph[2] =  new JRadioButton();
        groupGraph[2].setMnemonic(KeyEvent.VK_3);
        groupGraph[2].addItemListener(Listeners.graph3Display);
        groupGraph[3] =  new JRadioButton();
        groupGraph[3].setMnemonic(KeyEvent.VK_4);
        groupGraph[3].addItemListener(Listeners.graph4Display);
        groupGraph[4] =  new JRadioButton();
        groupGraph[4].setMnemonic(KeyEvent.VK_5);
        groupGraph[4].addItemListener(Listeners.graph5Display);
        
        groupGraphLabels[0] = new JLabel("Graph 1:");
        groupGraphLabels[0].setFont(graphLabels);
        groupGraphLabels[0].setPreferredSize(new Dimension(80,50));
        groupGraphLabels[0].setForeground(Color.black);
        
        groupGraphLabels[1] = new JLabel("Graph 2:");
        groupGraphLabels[1].setFont(graphLabels);
        groupGraphLabels[1].setPreferredSize(new Dimension(80,50));
        groupGraphLabels[1].setForeground(Color.black);

        groupGraphLabels[2] = new JLabel("Graph 3:");
        groupGraphLabels[2].setFont(graphLabels);
        groupGraphLabels[2].setPreferredSize(new Dimension(80,50));
        groupGraphLabels[2].setForeground(Color.black);
        
        groupGraphLabels[3] = new JLabel("Graph 4:");
        groupGraphLabels[3].setFont(graphLabels);
        groupGraphLabels[3].setPreferredSize(new Dimension(80,50));
        groupGraphLabels[3].setForeground(Color.black);
        
        groupGraphLabels[4] = new JLabel("Graph 5:");
        groupGraphLabels[4].setFont(graphLabels);
        groupGraphLabels[4].setPreferredSize(new Dimension(80,50));
        groupGraphLabels[4].setForeground(Color.black);
        
        graphSelector = new JPanel();
        graphSelector.setPreferredSize(new Dimension(280,200));
        graphSelector.setLayout(new FlowLayout(FlowLayout.LEFT));
        graphSelector.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        for (int i = 0; i < groupGraph.length; i++) {
            graphSelector.add(groupGraphLabels[i]);
            graphSelector.add(groupGraph[i]);
        }
        
        controlPanel.add(controlPanelLabel);
        controlPanel.add(headerNameLabel);
        controlPanel.add(headerNameChange);
        controlPanel.add(headerMonthLabel);
        controlPanel.add(headerMonthChange);
        controlPanel.add(headerDayLabel);
        controlPanel.add(headerDayChange);
        controlPanel.add(addGroup);
        controlPanel.add(remGroup);
        controlPanel.add(descriptionLabel);
        controlPanel.add(description);
        controlPanel.add(gameDay);
        controlPanel.add(graphSelector);
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

    public void ChartCreation(JFreeChart chart, ChartPanel panel) {
        chart.setBackgroundPaint(Color.white);
        chart.setBorderPaint(Color.red);
        panel = new ChartPanel(chart);
        panel.setPreferredSize(chartSize);
        graphHolder.add(panel);
    }
    
    public void GraphSetUp() {
        graphHolder = new JPanel();
        graphHolder.setPreferredSize(new Dimension(1860,630));
        graphHolder.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        for (int i = 0; i < amtCharts; i++) {
            ringChartData[i] = new DefaultPieDataset();
        } 
           
        //Chart 1 set up, type counter
        charts[0] = ChartFactory.createRingChart  //TODO
            ("Type Split", ringChartData[0], false, true, false);
        //chart 2 set up, set Intensity
        charts[1] = ChartFactory.createRingChart  //TODO
            ("Set Intensity", ringChartData[1], false, true, false);
        //chart 3 set up, set Distance
        charts[2] = ChartFactory.createRingChart  //TODO
            ("Set Distance", ringChartData[2], false, true, false);
        
        //Visual chart formatting and addition
        for (int i = 0; i < amtCharts; i++) {
            ChartCreation(charts[i],chartHolders[i]);
        }
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



