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
    static Object[][] storage = 
        new Object[MainPage.data.length][MainPage.data[0].length]; 
    {
        for (int i = 0; i < storage.length; i++) {   //Sets all to 0.
            for (int j = 0; j < storage[i].length; j++) {
                storage[i][j] = 0;
            }
        }
    }
    static TableModelListener TableChange = new TableModelListener() {
        public void tableChanged(TableModelEvent e) {
            int row = e.getFirstRow();
            int column = e.getColumn();
            int tempInt = 0;
            int tempInt2 = 0;
            int count = 1;
            TableModel model = (TableModel)e.getSource();
            Object dataPoint = model.getValueAt(row, column);
            MainPage.data[row][column] = dataPoint;  //updates Data
            //System.out.println(Arrays.deepToString(MainPage.data));    //Debug
            //System.out.println(Arrays.deepToString(storage));
            
                for (int i = 0; i < MainPage.data.length; i++) {     //Update storage array
                    for (int j = 0; j < MainPage.data[i].length; j++) {
                        storage[i][j] = MainPage.data[i][j];
                        if (storage[i][j] == null) {
                            storage[i][j] = 0;
                        }
                    }
                }
                for (int i = 0; i < storage.length; i++) {
                    if (i != 0 && storage[i][0].equals(storage[i-1][0])) {
                        storage[i][1] = storage[i-1][1];
                    } 
                }
            
            //Update Total Distance
            for (int i = 0; i < MainPage.data.length; i++) {
                if (MainPage.data[i][0] != null && MainPage.data[i][2] != null && 
                    MainPage.data[i][3] != null && !MainPage.data[i][0].equals("") && 
                    !MainPage.data[i][2].equals("") & !MainPage.data[i][3].equals("")) {
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
            MainPage.ttlDistance = Integer.toString(tempInt);
            tempInt = 0;
            
            //Update Total Time
                for (int i = 0; i < MainPage.data.length; i++) {
                    if (MainPage.data[i][6] != null && !MainPage.data[i][6].equals("")) {
                        tempInt += Integer.valueOf(MainPage.data[i][6].toString());
                    }
                    if (MainPage.data[i][7] != null && !MainPage.data[i][7].equals("")) {
                        tempInt2 += Integer.valueOf(MainPage.data[i][7].toString());
                        if (tempInt2 >= 60) {
                            tempInt2 -= 60;
                            tempInt += 1;
                        }
                    }
                }
                MainPage.ttlTimeMin = Integer.toString(tempInt);
                MainPage.ttlTimeSec = Integer.toString(tempInt2);
            tempInt= 0;
            tempInt2 = 0;
            
            //Update Average Intensity
                for (int i = 0; i < MainPage.data.length; i++) {
                    if (MainPage.data[i][8] != null && !MainPage.data[i][8].equals("")) {
                        tempInt += Integer.valueOf(MainPage.data[i][8].toString());
                        tempInt2 += 1;
                    }
                }
                if (tempInt2 != 0) {
                    tempInt /= tempInt2;
                }

                MainPage.avgIntensity = Integer.toString(tempInt);
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
            System.out.println("A:" + Arrays.deepToString(MainPage.data));
            System.out.println("B:" + Arrays.deepToString(storage));
            MainPage.ttlDistancePanel.setText("Total Distance: " + MainPage.ttlDistance 
                + " yds");
            if (Integer.valueOf(MainPage.ttlTimeSec) == 0) {
                MainPage.ttlTimePanel.setText("Total Time: " + MainPage.ttlTimeMin + 
                    " min "); 
            } else {
                MainPage.ttlTimePanel.setText("Total Time: " + MainPage.ttlTimeMin + 
                    " min " + MainPage.ttlTimeSec + " Sec"); 
            }
            MainPage.avgIntensityPanel.setText("Intensity: " + 
                MainPage.avgIntensity + "%");
        }
    };
    //table addition and subtraction listener
    static int currentTable = 0;
    static ActionListener AddGroup = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            currentTable += 1;
            switch (currentTable) {
                case 1: 
                    MainPage.tableHolder.add(MainPage.group2);
                    MainPage.tableHolder.add(MainPage.group2Pane);
                    SwingUtilities.updateComponentTreeUI(MainPage.mainWindow);
                    break;
                case 2:
                    MainPage.tableHolder.add(MainPage.group3);
                    MainPage.tableHolder.add(MainPage.group3Pane);
                    SwingUtilities.updateComponentTreeUI(MainPage.mainWindow);
                    break;
                case 3:
                    MainPage.tableHolder.add(MainPage.group4);
                    MainPage.tableHolder.add(MainPage.group4Pane);
                    SwingUtilities.updateComponentTreeUI(MainPage.mainWindow);
                    break;
                case 4:
                    MainPage.tableHolder.add(MainPage.group5);
                    MainPage.tableHolder.add(MainPage.group5Pane);
                    SwingUtilities.updateComponentTreeUI(MainPage.mainWindow);
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
                    SwingUtilities.updateComponentTreeUI(MainPage.mainWindow);
                    break;
                case 1: 
                    MainPage.tableHolder.remove(MainPage.group3);
                    MainPage.tableHolder.remove(MainPage.group3Pane);
                    SwingUtilities.updateComponentTreeUI(MainPage.mainWindow);
                    break;
                case 2: 
                    MainPage.tableHolder.remove(MainPage.group4);
                    MainPage.tableHolder.remove(MainPage.group4Pane);
                    SwingUtilities.updateComponentTreeUI(MainPage.mainWindow);
                    break;
                case 3: 
                    MainPage.tableHolder.remove(MainPage.group5);
                    MainPage.tableHolder.remove(MainPage.group5Pane);
                    SwingUtilities.updateComponentTreeUI(MainPage.mainWindow);
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
               MainPage.mainWindow.setVisible(true);
               WelcomeFrame.introPage.dispose();
           }
     };   
}




