import javax.jws.soap.SOAPBinding.Style;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.table.*;
import javax.swing.event.*;

public class TableListener implements TableModelListener {
    int groupType = 0;
    
    TableListener(int groupType) {
        this.groupType = groupType;
    }
    static Object[][][] storage = 
        new Object[MainPage.data.length][MainPage.data[0].length][MainPage.data[0][0].length]; 
    {
        for (int i = 0; i < storage.length; i++) {   //Sets all to 0.
            for (int j = 0; j < storage[i].length; j++) {
                for (int k = 0; k < storage[i][j].length; k++) {
                    storage[i][j][k] = 0;
                }
            }
        }
    }
    
    @Override
    public void tableChanged(TableModelEvent e) {
                int row = e.getFirstRow();
                int column = e.getColumn();
                int tempInt = 0;
                int tempInt2 = 0;
                TableModel model = (TableModel)e.getSource();
                Object dataPoint = model.getValueAt(row, column);
                MainPage.data[groupType][row][column] = dataPoint;  //updates Data
                //System.out.println(Arrays.deepToString(MainPage.data));    //Debug
                //System.out.println(Arrays.deepToString(storage));
                
                    for (int i = 0; i < MainPage.data[groupType].length; i++) {     //Update storage array
                        for (int j = 0; j < MainPage.data[groupType][i].length; j++) {
                            storage[groupType][i][j] = MainPage.data[groupType][i][j];
                            if (storage[groupType][i][j] == null) {
                                storage[groupType][i][j] = 0;
                            }
                        }
                    }
                    for (int i = 0; i < storage[groupType].length; i++) {  //Sets rounds for all of set
                        if (i != 0 && storage[groupType][i][0].equals(storage[groupType][i-1][0])) {
                            storage[groupType][i][1] = storage[groupType][i-1][1];
                        } 
                    }
                
                //Update Total Distance
                for (int i = 0; i < MainPage.data[groupType].length; i++) {
                    if (MainPage.data[groupType][i][0] != null && MainPage.data[groupType][i][2] != null && 
                        MainPage.data[groupType][i][3] != null && !MainPage.data[groupType][i][0].equals("") && 
                        !MainPage.data[groupType][i][2].equals("") & !MainPage.data[groupType][i][3].equals("")) {
                        if (storage[groupType][i][1] != null && !storage[groupType][i][1].equals(0) && 
                            !storage[groupType][i][1].equals("")) {    
                            tempInt += Integer.valueOf(storage[groupType][i][3].toString()) * 
                                Integer.valueOf(storage[groupType][i][2].toString()) *
                                Integer.valueOf(storage[groupType][i][1].toString());
                        } else if (storage[groupType][i][1] == null || storage[groupType][i][1].equals(0) || 
                            storage[groupType][i][1].equals("")) {
                                    tempInt += Integer.valueOf(storage[groupType][i][3].toString()) * 
                                    Integer.valueOf(storage[groupType][i][2].toString());
                            }
                    }
                }
                MainPage.ttlDistance = Integer.toString(tempInt);
                tempInt = 0;
                
                //Update Total Time
                    for (int i = 0; i < MainPage.data[groupType].length; i++) {
                       if (MainPage.data[groupType][i][0] != null && !MainPage.data[groupType][i][0].equals("")) {    //Checks for set
                           if (storage[groupType][i][1] != null && !storage[groupType][i][1].equals("")) {  //Checks for round, enter if round = #
                               if (MainPage.data[groupType][i][6] != null && !MainPage.data[groupType][i][6].equals("") &&
                                   MainPage.data[groupType][i][2] != null && !MainPage.data[groupType][i][2].equals("")) { 
                                   tempInt += Integer.valueOf(storage[groupType][i][1].toString()) 
                                       * Integer.valueOf(storage[groupType][i][2].toString())
                                       * Integer.valueOf(MainPage.data[groupType][i][6].toString());  //non-normal calc
                               }
                               if (MainPage.data[groupType][i][7] != null && !MainPage.data[groupType][i][7].equals("") &&
                                   MainPage.data[groupType][i][2] != null && !MainPage.data[groupType][i][2].equals("")) {
                                   tempInt2 += Integer.valueOf(storage[groupType][i][1].toString()) 
                                       * Integer.valueOf(storage[groupType][i][2].toString())
                                       * Integer.valueOf(MainPage.data[groupType][i][7].toString()); //non-normal calc
                                   while (tempInt2 >= 60) {
                                       tempInt2 -= 60;
                                       tempInt += 1;
                                   }
                               }    
                           } else if (storage[groupType][i][1] == null || storage[groupType][i][1].equals("") //enter if round # = null, 0, or nothing
                               || storage[groupType][i][1].equals(0)) {
                               if (MainPage.data[groupType][i][6] != null && !MainPage.data[groupType][i][6].equals("") &&
                                   MainPage.data[groupType][i][2] != null && !MainPage.data[groupType][i][2].equals("")) { 
                                   tempInt += Integer.valueOf(MainPage.data[groupType][i][6].toString()) 
                                       * Integer.valueOf(storage[groupType][i][2].toString());  //normal calc
                               }
                               if (MainPage.data[groupType][i][7] != null && !MainPage.data[groupType][i][7].equals("") &&
                                   MainPage.data[groupType][i][2] != null && !MainPage.data[groupType][i][2].equals("")) {
                                   tempInt2 += Integer.valueOf(MainPage.data[groupType][i][7].toString())
                                       * Integer.valueOf(storage[groupType][i][2].toString()); ////normal calc
                                   if (tempInt2 >= 60) {
                                       tempInt2 -= 60;
                                       tempInt += 1;
                                   }
                               }
                           }
                       }
                    }
                    MainPage.ttlTimeMin = Integer.toString(tempInt);
                    MainPage.ttlTimeSec = Integer.toString(tempInt2);
                tempInt= 0;
                tempInt2 = 0;
                
                //Update Average Intensity
                    for (int i = 0; i < MainPage.data[groupType].length; i++) {
                        if (MainPage.data[groupType][i][8] != null && !MainPage.data[groupType][i][8].equals("")) {
                            tempInt += Integer.valueOf(MainPage.data[groupType][i][8].toString());
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
                MainPage.avgIntensityPanel.setText("Avg Intensity: " + 
                    MainPage.avgIntensity + "%");
            }
    }
    
