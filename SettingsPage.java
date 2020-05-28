/*TODO
    -add type selection options
*/
import javax.jws.soap.SOAPBinding.Style;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.table.*;
import javax.swing.event.*;

public class SettingsPage {
    static JPanel settingsTab;
    static JLabel[] layoutLabels = new JLabel[10];
    static JScrollPane settingsScrollArea;
    static Font labelFont = new Font("Arial", Font.PLAIN, 24);
    static int settingsTabLength = 900;
    
    public void PrepareGUI() {
        settingsTab = new JPanel();
        settingsTab.setLayout(new BorderLayout());
        settingsTab.setPreferredSize(new Dimension(1000, settingsTabLength));
        settingsTab.setBorder(BorderFactory.createLineBorder(Color.blue, 2));
      
        layoutLabels[0] = new JLabel("Settings Selector");
        layoutLabels[1] = new JLabel("Selected Settings Decison");
        
        LabelSetUp(layoutLabels[0], new Dimension(400,800));
        LabelSetUp(layoutLabels[1], new Dimension(1490,settingsTabLength - 50));
        
        settingsTab.add(layoutLabels[0], BorderLayout.WEST);    //TODO
        settingsTab.add(layoutLabels[1], BorderLayout.CENTER);  //TODO
        
        settingsScrollArea = new JScrollPane(settingsTab, 
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        settingsScrollArea.setBorder(BorderFactory.createLineBorder(Color.blue, 2));
        settingsScrollArea.getVerticalScrollBar().setUnitIncrement(25);
        
    }

    public void LabelSetUp(JLabel labelName, Dimension size) {
        labelName.setFont(labelFont);
        labelName.setBorder(BorderFactory.createLineBorder(Color.red, 2));
        labelName.setHorizontalAlignment(SwingConstants.CENTER);
        labelName.setVerticalAlignment(SwingConstants.CENTER);
        labelName.setMaximumSize(size);
    }
}
