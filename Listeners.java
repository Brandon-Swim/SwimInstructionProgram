import javax.jws.soap.SOAPBinding.Style;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.table.*;
import javax.swing.event.*;


public class Listeners {
    
    //Header Name Listeners
    static MouseAdapter HeaderNameMouse = new MouseAdapter(){
        @Override
        public void mouseClicked(MouseEvent e){
                MainPage.headerNameChange.setText("");
                MainPage.headerNameChange.setForeground(Color.black);
        }
    };
    static ActionListener HeaderNameText = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            MainPage.headerName = MainPage.headerNameChange.getText();
            MainPage.headerFinal = MainPage.headerMonth + "/" + 
            MainPage.headerDay + ": " + MainPage.headerName;
            MainPage.header.setText(MainPage.headerFinal);
            MainPage.header.setForeground(Color.black);
        }
    };
    static FocusListener HeaderNameClick = new FocusListener() {
        public void focusGained(FocusEvent e) {
            
        }
        public void focusLost(FocusEvent e) {
            MainPage.headerName = MainPage.headerNameChange.getText();
            MainPage.headerFinal = MainPage.headerMonth + "/" + 
            MainPage.headerDay + ": " + MainPage.headerName;
            MainPage.header.setText(MainPage.headerFinal);
            MainPage.header.setForeground(Color.black);
        }
    };
    
    // Header Month Listeners
    static MouseAdapter HeaderMonthMouse = new MouseAdapter(){
        @Override
        public void mouseClicked(MouseEvent e){
                MainPage.headerMonthChange.setText("");
                MainPage.headerMonthChange.setForeground(Color.black);
        }
    };
    static ActionListener HeaderMonthText = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            MainPage.headerMonth = MainPage.headerMonthChange.getText();
            MainPage.headerFinal = MainPage.headerMonth + "/" + 
            MainPage.headerDay + ": " + MainPage.headerName;
            MainPage.header.setText(MainPage.headerFinal);
            MainPage.header.setForeground(Color.black);
        }
    };
    static FocusListener HeaderMonthClick = new FocusListener() {
        public void focusGained(FocusEvent e) {
            
        }
        public void focusLost(FocusEvent e) {
            MainPage.headerMonth = MainPage.headerMonthChange.getText();
            MainPage.headerFinal = MainPage.headerMonth + "/" + 
            MainPage.headerDay + ": " + MainPage.headerName;
            MainPage.header.setText(MainPage.headerFinal);
            MainPage.header.setForeground(Color.black);
        }
    };
    
    
    //Header Day Listeners
    static MouseAdapter HeaderDayMouse = new MouseAdapter(){
        @Override
        public void mouseClicked(MouseEvent e){
                MainPage.headerDayChange.setText("");
                MainPage.headerDayChange.setForeground(Color.black);
        }
    };
    static ActionListener HeaderDayText = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            MainPage.headerDay = MainPage.headerDayChange.getText();
            MainPage.headerFinal = MainPage.headerMonth + "/" + 
            MainPage.headerDay + ": " + MainPage.headerName;
            MainPage.header.setText(MainPage.headerFinal);
            MainPage.header.setForeground(Color.black);
        }
    };
    static FocusListener HeaderDayClick = new FocusListener() {
        public void focusGained(FocusEvent e) {
            
        }
        public void focusLost(FocusEvent e) {
            MainPage.headerDay = MainPage.headerDayChange.getText();
            MainPage.headerFinal = MainPage.headerMonth + "/" + 
            MainPage.headerDay + ": " + MainPage.headerName;
            MainPage.header.setText(MainPage.headerFinal);
            MainPage.header.setForeground(Color.black);
        }
    };
    //Description Listeners
    static MouseAdapter descriptionMouse = new MouseAdapter(){
        @Override
        public void mouseClicked(MouseEvent e){
                MainPage.description.setText("");
                MainPage.description.setForeground(Color.black);
        }
    };
    
    //Table listener
    static TableListener group1 = new TableListener(0);
    static TableListener group2 = new TableListener(1);
    static TableListener group3 = new TableListener(2);
    static TableListener group4 = new TableListener(3);
    static TableListener group5 = new TableListener(4);
    static TableModelListener group1Change = new TableModelListener() {
        public void tableChanged(TableModelEvent e) {
            group1.tableChanged(e);
        }
    };
    static TableModelListener group2Change = new TableModelListener() {
        public void tableChanged(TableModelEvent e) {
            group2.tableChanged(e);
        }
    };
    static TableModelListener group3Change = new TableModelListener() {
        public void tableChanged(TableModelEvent e) {
            group3.tableChanged(e);
        }
    };
    static TableModelListener group4Change = new TableModelListener() {
        public void tableChanged(TableModelEvent e) {
            group4.tableChanged(e);
        }
    };
    static TableModelListener group5Change = new TableModelListener() {
        public void tableChanged(TableModelEvent e) {
            group5.tableChanged(e);
        }
    };
    
    //Clear cell with delete key

    static KeyListener delCell1 = new KeyListener() {   //TODO condense
        public void keyTyped(KeyEvent e) {
        }
        public synchronized void keyPressed(KeyEvent e) {   
            int ID = e.getKeyCode();
                    if (ID == KeyEvent.VK_DELETE) {
                        e.consume();
                        int column =  MainPage.mainTableGroup1.getSelectedColumn();
                        int row = MainPage.mainTableGroup1.getSelectedRow();
                        MainPage.mainTableGroup5.setValueAt(null, row, column); 
                    }

        }
        public void keyReleased(KeyEvent e) {
        }
    };
    static KeyListener delCell2 = new KeyListener() {
        public void keyTyped(KeyEvent e) {
        }
        public synchronized void keyPressed(KeyEvent e) {   
            int ID = e.getKeyCode();
                    if (ID == KeyEvent.VK_DELETE) {
                        e.consume();
                        int column =  MainPage.mainTableGroup2.getSelectedColumn();
                        int row = MainPage.mainTableGroup2.getSelectedRow();
                        MainPage.mainTableGroup2.setValueAt(null, row, column);
                    }

        }
        public void keyReleased(KeyEvent e) {
        }
    };
    static KeyListener delCell3 = new KeyListener() {
        public void keyTyped(KeyEvent e) {
        }
        public synchronized void keyPressed(KeyEvent e) {   
            int ID = e.getKeyCode();
                    if (ID == KeyEvent.VK_DELETE) {
                        e.consume();
                        int column =  MainPage.mainTableGroup3.getSelectedColumn();
                        int row = MainPage.mainTableGroup3.getSelectedRow();
                        MainPage.mainTableGroup3.setValueAt(null, row, column);
                    }

        }
        public void keyReleased(KeyEvent e) {
        }
    };
    static KeyListener delCell4 = new KeyListener() {
        public void keyTyped(KeyEvent e) {
        }
        public synchronized void keyPressed(KeyEvent e) {   
            int ID = e.getKeyCode();
                    if (ID == KeyEvent.VK_DELETE) {
                        e.consume();
                        int column =  MainPage.mainTableGroup4.getSelectedColumn();
                        int row = MainPage.mainTableGroup4.getSelectedRow();
                        MainPage.mainTableGroup4.setValueAt(null, row, column);
                    }

        }
        public void keyReleased(KeyEvent e) {
        }
    };
    static KeyListener delCell5 = new KeyListener() {
        public void keyTyped(KeyEvent e) {
        }
        public synchronized void keyPressed(KeyEvent e) {   
            int ID = e.getKeyCode();
                    if (ID == KeyEvent.VK_DELETE) {
                        e.consume();
                        int column =  MainPage.mainTableGroup5.getSelectedColumn();
                        int row = MainPage.mainTableGroup5.getSelectedRow();
                        if (column == 5) {
                            MainPage.mainTableGroup5.setValueAt("", row, column);
                         } else {
                             MainPage.mainTableGroup5.setValueAt(null, row, column); 
                         }
                    }

        }
        public void keyReleased(KeyEvent e) {
        }
    };
    //table addition and subtraction listener
    static Dimension dTable = new Dimension(1200,460); //Base Size
    static int amtGroups = 1;    //Indicates the amount of groups
    static ActionListener AddGroup = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            amtGroups += 1;
            switch (amtGroups) {
                case 2:
                    for (int i = 0; i < MainPage.data[0].length; i++) {
                        for (int j = 0; j < MainPage.data[0][i].length; j++) {
                            MainPage.mainTableGroup2.setValueAt(MainPage.data[0][i][j], i, j);
                        }
                    }
                    MainPage.tableHolder.add(MainPage.group2);
                    MainPage.tableHolder.add(MainPage.group2Pane);
                    dTable.setSize(1200,960);
                    MainPage.tableHolder.setPreferredSize(dTable);
                    SwingUtilities.updateComponentTreeUI(finalHolder.mainWindow);
                    break;
                case 3:
                    for (int i = 0; i < MainPage.data[0].length; i++) {
                        for (int j = 0; j < MainPage.data[0][i].length; j++) {
                            MainPage.mainTableGroup3.setValueAt(MainPage.data[0][i][j], i, j);
                        }
                    }
                    MainPage.tableHolder.add(MainPage.group3);
                    MainPage.tableHolder.add(MainPage.group3Pane);
                    dTable.setSize(1200,1440);
                    MainPage.tableHolder.setPreferredSize(dTable);
                    SwingUtilities.updateComponentTreeUI(finalHolder.mainWindow);
                    break;
                case 4:
                    for (int i = 0; i < MainPage.data[0].length; i++) {
                        for (int j = 0; j < MainPage.data[0][i].length; j++) {
                            MainPage.mainTableGroup4.setValueAt(MainPage.data[0][i][j], i, j);
                        }
                    }
                    MainPage.tableHolder.add(MainPage.group4);
                    MainPage.tableHolder.add(MainPage.group4Pane);
                    dTable.setSize(1200,1920);
                    MainPage.tableHolder.setPreferredSize(dTable);
                    SwingUtilities.updateComponentTreeUI(finalHolder.mainWindow);
                    break;
                case 5:
                    for (int i = 0; i < MainPage.data[0].length; i++) {
                        for (int j = 0; j < MainPage.data[0][i].length; j++) {
                            MainPage.mainTableGroup5.setValueAt(MainPage.data[0][i][j], i, j);
                        }
                    }
                    MainPage.tableHolder.add(MainPage.group5);
                    MainPage.tableHolder.add(MainPage.group5Pane);
                    dTable.setSize(1200,2400);
                    MainPage.tableHolder.setPreferredSize(dTable);
                    SwingUtilities.updateComponentTreeUI(finalHolder.mainWindow);
                    break;
                default:
                    if (amtGroups == 6) {
                        amtGroups = 5;
                    }
                    break;
            }
        }
       };
    static ActionListener RemGroup = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            amtGroups -= 1;
            switch (amtGroups) {
                case 1:
                    for (int i = 0; i < MainPage.data[0].length; i++) {
                        for (int j = 0; j < MainPage.data[0][i].length; j++) {
                            MainPage.mainTableGroup2.setValueAt(null, i, j);
                        }
                    }
                    MainPage.tableHolder.remove(MainPage.group2);
                    MainPage.tableHolder.remove(MainPage.group2Pane);
                    dTable.setSize(1200,480);
                    MainPage.tableHolder.setPreferredSize(dTable);
                    SwingUtilities.updateComponentTreeUI(finalHolder.mainWindow);
                    break;
                case 2:
                    for (int i = 0; i < MainPage.data[0].length; i++) {
                        for (int j = 0; j < MainPage.data[0][i].length; j++) {
                            MainPage.mainTableGroup3.setValueAt(null, i, j);
                        }
                    }
                    MainPage.tableHolder.remove(MainPage.group3);
                    MainPage.tableHolder.remove(MainPage.group3Pane);
                    dTable.setSize(1200,960);
                    MainPage.tableHolder.setPreferredSize(dTable);
                    SwingUtilities.updateComponentTreeUI(finalHolder.mainWindow);
                    break;
                case 3:
                    for (int i = 0; i < MainPage.data[0].length; i++) {
                        for (int j = 0; j < MainPage.data[0][i].length; j++) {
                            MainPage.mainTableGroup4.setValueAt(null, i, j);
                        }
                    }
                    MainPage.tableHolder.remove(MainPage.group4);
                    MainPage.tableHolder.remove(MainPage.group4Pane);
                    dTable.setSize(1200,1440);
                    MainPage.tableHolder.setPreferredSize(dTable);
                    SwingUtilities.updateComponentTreeUI(finalHolder.mainWindow);
                    break;
                case 4: 
                    for (int i = 0; i < MainPage.data[0].length; i++) {
                        for (int j = 0; j < MainPage.data[0][i].length; j++) {
                            MainPage.mainTableGroup5.setValueAt(null, i, j);
                        }
                    }
                    MainPage.tableHolder.remove(MainPage.group5);
                    MainPage.tableHolder.remove(MainPage.group5Pane);
                    dTable.setSize(1200,1920);
                    MainPage.tableHolder.setPreferredSize(dTable);
                    SwingUtilities.updateComponentTreeUI(finalHolder.mainWindow);
                    break;
                default:
                    if (amtGroups == 0) {
                        amtGroups = 1;
                    } 
                    break;
            }
        }
       }; 
    //Welcome Button Listener
    static ActionListener mainButton1Press = new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               WelcomeFrame.introPage.setVisible(false);
               finalHolder.mainWindow.setVisible(true);
               WelcomeFrame.introPage.dispose();
           }
     };   
   //Radio Button Listeners
    static ItemListener graph1Display = new ItemListener() {
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                MainPage.charts[0].setBackgroundPaint(Color.red);
                MainPage.charts[1].setBackgroundPaint(Color.red);
                MainPage.charts[2].setBackgroundPaint(Color.red);
                for (int i = 0; i < MainPage.groupGraph.length; i++) {
                    if (i != 0) {
                        MainPage.groupGraph[i].setSelected(false);
                    }
                }
            } else {
                int tempInt = 0;
                for (int i = 0; i < MainPage.groupGraph.length; i++) {
                    if (MainPage.groupGraph[i].isSelected()) {
                        tempInt = 1;
                    }
                }
                if (tempInt != 1) {
                    MainPage.groupGraph[0].setSelected(true);
                } else {
                    System.out.println("Graph 1 unselected");
                }
            }
        }
    };
    static ItemListener graph2Display = new ItemListener() {
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                MainPage.charts[0].setBackgroundPaint(Color.blue);
                MainPage.charts[1].setBackgroundPaint(Color.blue);
                MainPage.charts[2].setBackgroundPaint(Color.blue);
                for (int i = 0; i < MainPage.groupGraph.length; i++) {
                    if (i != 1) {
                        MainPage.groupGraph[i].setSelected(false);
                    }
                }
            } else {
                int tempInt = 0;
                for (int i = 0; i < MainPage.groupGraph.length; i++) {
                    if (MainPage.groupGraph[i].isSelected()) {
                        tempInt = 1;
                    }
                }
                if (tempInt != 1) {
                    MainPage.groupGraph[1].setSelected(true);
                } else { 
                    System.out.println("Graph 2 unselected");
                }
            }
        }
    };
    static ItemListener graph3Display = new ItemListener() {
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                MainPage.charts[0].setBackgroundPaint(Color.green);
                MainPage.charts[1].setBackgroundPaint(Color.green);
                MainPage.charts[2].setBackgroundPaint(Color.green);
                for (int i = 0; i < MainPage.groupGraph.length; i++) {
                    if (i != 2) {
                        MainPage.groupGraph[i].setSelected(false);
                    }
                }
            } else {
                int tempInt = 0;
                for (int i = 0; i < MainPage.groupGraph.length; i++) {
                    if (MainPage.groupGraph[i].isSelected()) {
                        tempInt = 1;
                    }
                }
                if (tempInt != 1) {
                    MainPage.groupGraph[2].setSelected(true);
                } else {
                    System.out.println("Graph 3 unselected");
                }
            }
        }
    };
    static ItemListener graph4Display = new ItemListener() {
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                MainPage.charts[0].setBackgroundPaint(Color.pink);
                MainPage.charts[1].setBackgroundPaint(Color.pink);
                MainPage.charts[2].setBackgroundPaint(Color.pink);
                for (int i = 0; i < MainPage.groupGraph.length; i++) {
                    if (i != 3) {
                        MainPage.groupGraph[i].setSelected(false);
                    }
                }
            } else {
                int tempInt = 0;
                for (int i = 0; i < MainPage.groupGraph.length; i++) {
                    if (MainPage.groupGraph[i].isSelected()) {
                        tempInt = 1;
                    }
                }
                if (tempInt != 1) {
                    MainPage.groupGraph[3].setSelected(true);
                } else {
                    System.out.println("Graph 4 unselected");
                }
            }
        }
    };
    static ItemListener graph5Display = new ItemListener() {
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                MainPage.charts[0].setBackgroundPaint(Color.cyan);
                MainPage.charts[1].setBackgroundPaint(Color.cyan);
                MainPage.charts[2].setBackgroundPaint(Color.cyan);
                for (int i = 0; i < MainPage.groupGraph.length; i++) {
                    if (i != 4) {
                        MainPage.groupGraph[i].setSelected(false);
                    }
                }
            } else {
                int tempInt = 0;
                for (int i = 0; i < MainPage.groupGraph.length; i++) {
                    if (MainPage.groupGraph[i].isSelected()) {
                        tempInt = 1;
                    }
                }
                if (tempInt != 1) {
                    MainPage.groupGraph[4].setSelected(true);
                } else {
                    System.out.println("Graph 5 unselected");
                }
            }
        }
    };
}




