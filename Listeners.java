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
    //table addition and subtraction listener
    static Dimension dTable = new Dimension(1200,460); //Base Size
    static int currentTable = 0;
    static ActionListener AddGroup = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            currentTable += 1;
            switch (currentTable) {
                case 1: 
                    MainPage.tableHolder.add(MainPage.group2);
                    MainPage.tableHolder.add(MainPage.group2Pane);
                    dTable.setSize(1200,960);
                    MainPage.tableHolder.setPreferredSize(dTable);
                    SwingUtilities.updateComponentTreeUI(finalHolder.mainWindow);
                    break;
                case 2:
                    MainPage.tableHolder.add(MainPage.group3);
                    MainPage.tableHolder.add(MainPage.group3Pane);
                    dTable.setSize(1200,1440);
                    MainPage.tableHolder.setPreferredSize(dTable);
                    SwingUtilities.updateComponentTreeUI(finalHolder.mainWindow);
                    break;
                case 3:
                    MainPage.tableHolder.add(MainPage.group4);
                    MainPage.tableHolder.add(MainPage.group4Pane);
                    dTable.setSize(1200,1920);
                    MainPage.tableHolder.setPreferredSize(dTable);
                    SwingUtilities.updateComponentTreeUI(finalHolder.mainWindow);
                    break;
                case 4:
                    MainPage.tableHolder.add(MainPage.group5);
                    MainPage.tableHolder.add(MainPage.group5Pane);
                    dTable.setSize(1200,2400);
                    MainPage.tableHolder.setPreferredSize(dTable);
                    SwingUtilities.updateComponentTreeUI(finalHolder.mainWindow);
                    break;
                default:
                    if (currentTable == 5) {
                        currentTable = 4;
                    }
                    break;
            }
        }
       };
    static ActionListener RemGroup = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            currentTable -= 1;
            switch (currentTable) {
                case 0: 
                    MainPage.tableHolder.remove(MainPage.group2);
                    MainPage.tableHolder.remove(MainPage.group2Pane);
                    dTable.setSize(1200,480);
                    MainPage.tableHolder.setPreferredSize(dTable);
                    SwingUtilities.updateComponentTreeUI(finalHolder.mainWindow);
                    break;
                case 1: 
                    MainPage.tableHolder.remove(MainPage.group3);
                    MainPage.tableHolder.remove(MainPage.group3Pane);
                    dTable.setSize(1200,960);
                    MainPage.tableHolder.setPreferredSize(dTable);
                    SwingUtilities.updateComponentTreeUI(finalHolder.mainWindow);
                    break;
                case 2: 
                    MainPage.tableHolder.remove(MainPage.group4);
                    MainPage.tableHolder.remove(MainPage.group4Pane);
                    dTable.setSize(1200,1440);
                    MainPage.tableHolder.setPreferredSize(dTable);
                    SwingUtilities.updateComponentTreeUI(finalHolder.mainWindow);
                    break;
                case 3: 
                    MainPage.tableHolder.remove(MainPage.group5);
                    MainPage.tableHolder.remove(MainPage.group5Pane);
                    dTable.setSize(1200,1920);
                    MainPage.tableHolder.setPreferredSize(dTable);
                    SwingUtilities.updateComponentTreeUI(finalHolder.mainWindow);
                    break;
                default:
                    if (currentTable == -1) {
                        currentTable = 0;
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
}




