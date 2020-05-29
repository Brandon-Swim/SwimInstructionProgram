/*TODO
    -add type selection options
*/
import javax.swing.*;
import java.awt.*;

public class SettingsPage {
    final static int amtSettings = 10;
    final static int settingsTabLength = 900;
    static JPanel settingsTab;
    static JLabel[] layoutLabels = new JLabel[amtSettings];
    static JButton[] settingSelector = new JButton[amtSettings];
    static JPanel[] selectionPanels = new JPanel[amtSettings + 2];
    static JScrollPane settingsScrollArea;
    static Font labelFont = new Font("Arial", Font.PLAIN, 24);
    //sp
    static JCheckBox[] spSelector = new JCheckBox[MainPage.sideLabel.length];
    static String[] spLabel = new String[]{"Total Distance", 
        "Total Time", "Avg Intensity", "Working Distance", 
        "Working Time","Working Intensity","Type Counter", 
        "Season Distance","Practices left in the week", "Practices til Comp", 
        "Game Day Counter","Practices until Taper", "Set distance"};
    static JCheckBoxMenuItem spHolder;
    static JLabel spSelectorHeader;
    static JPanel spOrder;
    static JLabel spOrderHeader;
    
    public void PrepareGUI() {
        settingsTab = new JPanel();
        settingsTab.setLayout(new FlowLayout());
        settingsTab.setMinimumSize(new Dimension(1000, settingsTabLength));
        settingsTab.setMaximumSize(new Dimension(1000, settingsTabLength));
        settingsTab.setBorder(BorderFactory.createLineBorder(Color.blue, 2));
      
        selectionPanels[0] = new JPanel();  //Holder for button Selector
        selectionPanels[0].setLayout(new FlowLayout());
        selectionPanels[0].setPreferredSize(new Dimension(400,settingsTabLength - 50));
        selectionPanels[0].setBorder(BorderFactory.createLineBorder(Color.red, 2));
        
        selectionPanels[1] = new JPanel();  //Holder for workspace
        selectionPanels[1].setLayout(new FlowLayout());
        selectionPanels[1].setPreferredSize(new Dimension(1450, 2000));
        selectionPanels[1].setBorder(BorderFactory.createLineBorder(Color.red, 2));
        
        GeneralSetUp();
        GraphLayoutSetUp();
        ControlLayoutSetUp();
        SidePanelSetUp();
        FileSetUp();
        GraphSetUp();

        settingsScrollArea = new JScrollPane(selectionPanels[1], //Just incase
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        settingsScrollArea.getVerticalScrollBar().setUnitIncrement(25);
        settingsScrollArea.setPreferredSize(new Dimension(1490,settingsTabLength - 50));
        
        settingsTab.add(selectionPanels[0]);    //TODO
        settingsTab.add(settingsScrollArea);  //TODO
        selectionPanels[1].add(selectionPanels[2]); //Default Panel:General
        
    }

    public void LabelSetUp(JLabel labelName, Dimension size) {
        labelName.setFont(labelFont);
        labelName.setBorder(BorderFactory.createLineBorder(Color.red, 2));
        labelName.setHorizontalAlignment(SwingConstants.CENTER);
        labelName.setVerticalAlignment(SwingConstants.CENTER);
        labelName.setPreferredSize(size);
    }
    
    public void ButtonSetUp(JButton buttonName) {
        buttonName.setFont(labelFont);
        buttonName.setBorder(BorderFactory.createLineBorder(Color.gray, 2));
        buttonName.setHorizontalAlignment(SwingConstants.CENTER);
        buttonName.setVerticalAlignment(SwingConstants.CENTER);
        buttonName.setPreferredSize(new Dimension(350,100));
    }
    
    public void GeneralSetUp() {
        selectionPanels[2] = new JPanel();  //General
        selectionPanels[2].setLayout(new FlowLayout());
        selectionPanels[2].setPreferredSize(new Dimension(1450, 500));
        selectionPanels[2].setBorder(BorderFactory.createLineBorder(Color.green, 2));
        settingSelector[0] = new JButton("General");
        ButtonSetUp(settingSelector[0]);
        settingSelector[0].addActionListener(Listeners.settingButton1);
        selectionPanels[0].add(settingSelector[0]);
        
    }
    
    public void GraphLayoutSetUp() {
        selectionPanels[3] = new JPanel();  //Graph Selector
        selectionPanels[3].setLayout(new FlowLayout());
        selectionPanels[3].setPreferredSize(new Dimension(1450, 500));
        selectionPanels[3].setBorder(BorderFactory.createLineBorder(Color.blue, 2));
        settingSelector[1] = new JButton("Graph Selector");
        ButtonSetUp(settingSelector[1]);
        settingSelector[1].addActionListener(Listeners.settingButton2);
        selectionPanels[0].add(settingSelector[1]);
    }
    
    public void ControlLayoutSetUp() {
        selectionPanels[4] = new JPanel();  //Control Panel Setup
        selectionPanels[4].setLayout(new FlowLayout());
        selectionPanels[4].setPreferredSize(new Dimension(1450, 500));
        selectionPanels[4].setBorder(BorderFactory.createLineBorder(Color.cyan, 2));
        settingSelector[2] = new JButton("Control Panel Setup");
        ButtonSetUp(settingSelector[2]);
        settingSelector[2].addActionListener(Listeners.settingButton3);
        selectionPanels[0].add(settingSelector[2]);
    }
    
    public void SidePanelSetUp() {
        selectionPanels[5] = new JPanel();  //side Panel Selector
        selectionPanels[5].setLayout(new FlowLayout());
        selectionPanels[5].setPreferredSize(new Dimension(1450, 700));
        selectionPanels[5].setBorder(BorderFactory.createLineBorder(Color.magenta, 2));
        
        spHolder = new JCheckBoxMenuItem();
        spHolder.setPreferredSize(new Dimension(300,600));
        spHolder.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        spHolder.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        spSelectorHeader = new JLabel("Side Panel Data Selector");
        LabelSetUp(spSelectorHeader, new Dimension(280,50));
        spSelectorHeader.setFont(new Font("Arial", Font.PLAIN, 24));
        spHolder.add(spSelectorHeader);
        
        for (int i = 0; i <MainPage.sideLabel.length; i++) {
            spSelector[i] = new JCheckBox(spLabel[i]);
            spSelector[i].setPreferredSize(new Dimension(280,30));
            spSelector[i].addItemListener(Listeners.spSelector);
            if (i < 6) {    //Default Selected
                spSelector[i].setSelected(true);
            }
            spHolder.add(spSelector[i]);
        }
        selectionPanels[5].add(spHolder);
        
        spOrder = new JPanel();
        spOrder.setLayout(new FlowLayout());
        spOrder.setPreferredSize(new Dimension(300, 600));
        spOrder.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        spOrderHeader = new JLabel("Side Panel Data Order");
        LabelSetUp(spOrderHeader, new Dimension(280,50));
        spOrderHeader.setFont(new Font("Arial", Font.PLAIN, 24));
        spOrder.add(spOrderHeader);
        selectionPanels[5].add(spOrder);
        
        settingSelector[3] = new JButton("Side Panel Selector");
        ButtonSetUp(settingSelector[3]);
        settingSelector[3].addActionListener(Listeners.settingButton4);
        selectionPanels[0].add(settingSelector[3]); //add Button to left panel
    }
    
    public void FileSetUp() {
        selectionPanels[6] = new JPanel();  //File output selector
        selectionPanels[6].setLayout(new FlowLayout());
        selectionPanels[6].setPreferredSize(new Dimension(1450, 500));
        selectionPanels[6].setBorder(BorderFactory.createLineBorder(Color.yellow, 2));
        settingSelector[4] = new JButton("File Output Selector");
        ButtonSetUp(settingSelector[4]);
        settingSelector[4].addActionListener(Listeners.settingButton5);
        selectionPanels[0].add(settingSelector[4]);
    }
    
    public void GraphSetUp() {
        selectionPanels[7] = new JPanel();  //graph Setup
        selectionPanels[7].setLayout(new FlowLayout());
        selectionPanels[7].setPreferredSize(new Dimension(1450, 500));
        selectionPanels[7].setBorder(BorderFactory.createLineBorder(Color.lightGray, 2));
        settingSelector[5] = new JButton("Graph Setup");
        ButtonSetUp(settingSelector[5]);
        settingSelector[5].addActionListener(Listeners.settingButton6);
        selectionPanels[0].add(settingSelector[5]);
    }
}
