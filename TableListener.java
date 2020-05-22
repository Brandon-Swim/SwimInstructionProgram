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
    static String[] ttlDistance = new String[5];
    static String[] ttlTimeMin = new String[5];
    static String[] ttlTimeSec = new String[5];
    static String[] avgIntensity = new String[5];

    TableListener(int groupType) {
        this.groupType = groupType;
    }

    static Object[][][] storage =
        new Object[MainPage.data.length][MainPage.data[0].length][MainPage.data[0][0].length];
    {
        for (int i = 0; i < storage.length; i++) { // Sets all to 0.
            for (int j = 0; j < storage[i].length; j++) {
                for (int k = 0; k < storage[i][j].length; k++) {
                    storage[i][j][k] = 0;
                }
            }
            ttlDistance[i] = "0";
            ttlTimeMin[i] = "0";
            ttlTimeSec[i] = "0";
            avgIntensity[i] = "0";
        }
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        int row = e.getFirstRow();
        int column = e.getColumn();
        TableModel model = (TableModel) e.getSource();
        Object dataPoint = model.getValueAt(row, column);
        MainPage.data[groupType][row][column] = dataPoint; // updates Data
        // System.out.println(Arrays.deepToString(MainPage.data)); //Debug
        // System.out.println(Arrays.deepToString(storage));

        for (int i = 0; i < MainPage.data[groupType].length; i++) { // Update storage array
            for (int j = 0; j < MainPage.data[groupType][i].length; j++) {
                storage[groupType][i][j] = MainPage.data[groupType][i][j];
                if (storage[groupType][i][j] == null) {
                    storage[groupType][i][j] = 0;
                }
            }
        }
        for (int i = 0; i < storage[groupType].length; i++) { // Sets rounds for all of set
            if (i != 0 && storage[groupType][i][0].equals(storage[groupType][i - 1][0])) {
                storage[groupType][i][1] = storage[groupType][i - 1][1];
            }
        }
        GetDataChange();
        switch (Listeners.amtGroups) { 
            case 1:
                MainPage.sideData[0].setText("Total Distance: " + ttlDistance[0] + " yds");
                if (Integer.valueOf(ttlTimeSec[0]) == 0) {
                    MainPage.sideData[1].setText("Total Time: " + ttlTimeMin[0] + " min ");
                } else {
                    MainPage.sideData[1].setText(
                        "Total Time: " + ttlTimeMin[0] + " min " + ttlTimeSec[0] + " Sec");
                }
                MainPage.sideData[2].setText("Avg Intensity: " + avgIntensity[0] + "%");
                break;
            case 2:
                MainPage.sideData[0].setText("<html> Group Distance (yds): <br/>" 
                    + "<center>" + ttlDistance[0]+ " | " + ttlDistance[1] + "<center></html>");
                MainPage.sideData[1].setText("<html> Group Time (Mins): <br/>" 
                    + "<center>" + ttlTimeMin[0]+ " | " + ttlTimeMin[1] + "<center></html>");
                MainPage.sideData[2].setText("<html> Group Intensity (%): <br/>" 
                    + "<center>" + avgIntensity[0] + " | " + avgIntensity[1] + "<center></html>");
                break;
            case 3: 
                MainPage.sideData[0].setText("<html> Group Distance (yds): <br/>" 
                    + "<center>" + ttlDistance[0] + " | " + ttlDistance[1] 
                    + " | " + ttlDistance[2]  + "<center></html>");
                MainPage.sideData[1].setText("<html> Group Time (Mins): <br/>" 
                    + "<center>" + ttlTimeMin[0]+ " | " + ttlTimeMin[1] 
                    + " | " + ttlTimeMin[2] + "<center></html>");
                MainPage.sideData[2].setText("<html> Group Intensity (%): <br/>" 
                    + "<center>" + avgIntensity[0] + " | " + avgIntensity[1] 
                    + " | " + avgIntensity[2] + "<center></html>");
                break;
            case 4:
                MainPage.sideData[0].setText("<html> Group Distance (yds): <br/>" 
                    + "<center>" + ttlDistance[0] + " | " + ttlDistance[1] 
                    + " | " + ttlDistance[2] + " | " + ttlDistance[3] + "<center></html>");
                MainPage.sideData[1].setText("<html> Group Time (Mins): <br/>" 
                    + "<center>" + ttlTimeMin[0]+ " | " + ttlTimeMin[1] 
                    + " | " + ttlTimeMin[2] + " | " + ttlTimeMin[3] + "<center></html>");
                MainPage.sideData[2].setText("<html> Group Intensity (%): <br/>" 
                    + "<center>" + avgIntensity[0] + " | " + avgIntensity[1] 
                    + " | " + avgIntensity[2] + " | " + avgIntensity[3] + "<center></html>");
                break;
            case 5:
                MainPage.sideData[0].setText("<html> Group Distance (yds): <br/>" 
                    + "<center>" + ttlDistance[0] + " | " + ttlDistance[1] 
                    + " | " + ttlDistance[2] + " | " + ttlDistance[3] + " | " 
                    + ttlDistance[4] + "<center></html>");
                MainPage.sideData[1].setText("<html> Group Time (Mins): <br/>" 
                    + "<center>" + ttlTimeMin[0]+ " | " + ttlTimeMin[1] 
                    + " | " + ttlTimeMin[2] + " | " + ttlTimeMin[3] 
                    + " | " + ttlTimeMin[4] + "<center></html>");
                MainPage.sideData[2].setText("<html> Group Intensity (%): <br/>" 
                    + "<center>" + avgIntensity[0] + " | " + avgIntensity[1] 
                    + " | " + avgIntensity[2] + " | " + avgIntensity[3]
                    + " | " + avgIntensity[4] + "<center></html>");
                break;
            default:
                break;
        }
        // System.out.println("A:" + Arrays.deepToString(MainPage.data));
        // System.out.println("B:" + Arrays.deepToString(storage));
        }
    public void GetDataChange() {
        int tempInt = 0;
        int tempInt2 = 0;
        for (int i = 0; i < MainPage.data[groupType].length; i++) {
            if (MainPage.data[groupType][i][0] != null && MainPage.data[groupType][i][2] != null
                && MainPage.data[groupType][i][3] != null
                && !MainPage.data[groupType][i][0].equals("")
                && !MainPage.data[groupType][i][2].equals("")
                    & !MainPage.data[groupType][i][3].equals("")) {
                if (storage[groupType][i][1] != null && !storage[groupType][i][1].equals(0)
                    && !storage[groupType][i][1].equals("")) {
                    tempInt += Integer.valueOf(storage[groupType][i][3].toString())
                        * Integer.valueOf(storage[groupType][i][2].toString())
                        * Integer.valueOf(storage[groupType][i][1].toString());
                } else if (storage[groupType][i][1] == null
                    || storage[groupType][i][1].equals(0)
                    || storage[groupType][i][1].equals("")) {
                    tempInt += Integer.valueOf(storage[groupType][i][3].toString())
                        * Integer.valueOf(storage[groupType][i][2].toString());
                }
            }
        }
        ttlDistance[groupType] = Integer.toString(tempInt); //OUTPUT
        tempInt = 0;

        // Update Total Time
        for (int i = 0; i < MainPage.data[groupType].length; i++) {
            if (MainPage.data[groupType][i][0] != null
                && !MainPage.data[groupType][i][0].equals("")) { // Checks for set
                if (storage[groupType][i][1] != null && !storage[groupType][i][1].equals("")) { // Checks for round, enter if = #
                    if (MainPage.data[groupType][i][6] != null
                        && !MainPage.data[groupType][i][6].equals("")
                        && MainPage.data[groupType][i][2] != null
                        && !MainPage.data[groupType][i][2].equals("")) {
                        tempInt += Integer.valueOf(storage[groupType][i][1].toString())
                            * Integer.valueOf(storage[groupType][i][2].toString())
                            * Integer.valueOf(MainPage.data[groupType][i][6].toString()); // non-normal
                                                                                          // calc
                    }
                    if (MainPage.data[groupType][i][7] != null
                        && !MainPage.data[groupType][i][7].equals("")
                        && MainPage.data[groupType][i][2] != null
                        && !MainPage.data[groupType][i][2].equals("")) {
                        tempInt2 += Integer.valueOf(storage[groupType][i][1].toString())
                            * Integer.valueOf(storage[groupType][i][2].toString())
                            * Integer.valueOf(MainPage.data[groupType][i][7].toString()); // non-normal
                                                                                          // calc
                        while (tempInt2 >= 60) {
                            tempInt2 -= 60;
                            tempInt += 1;
                        }
                    }
                } else if (storage[groupType][i][1] == null
                    || storage[groupType][i][1].equals("") // enter if round # = null, 0, or
                                                           // nothing
                    || storage[groupType][i][1].equals(0)) {
                    if (MainPage.data[groupType][i][6] != null
                        && !MainPage.data[groupType][i][6].equals("")
                        && MainPage.data[groupType][i][2] != null
                        && !MainPage.data[groupType][i][2].equals("")) {
                        tempInt += Integer.valueOf(MainPage.data[groupType][i][6].toString())
                            * Integer.valueOf(storage[groupType][i][2].toString()); // normal
                                                                                    // calc
                    }
                    if (MainPage.data[groupType][i][7] != null
                        && !MainPage.data[groupType][i][7].equals("")
                        && MainPage.data[groupType][i][2] != null
                        && !MainPage.data[groupType][i][2].equals("")) {
                        tempInt2 += Integer.valueOf(MainPage.data[groupType][i][7].toString())
                            * Integer.valueOf(storage[groupType][i][2].toString()); // normal
                                                                                    // calc
                        if (tempInt2 >= 60) {
                            tempInt2 -= 60;
                            tempInt += 1;
                        }
                    }
                }
            }
        }
        ttlTimeMin[groupType] = Integer.toString(tempInt);  //OUTPUT
        ttlTimeSec[groupType] = Integer.toString(tempInt2); //OUTPUT
        tempInt = 0;
        tempInt2 = 0;

        // Update Average Intensity
        for (int i = 0; i < MainPage.data[groupType].length; i++) {
            if (MainPage.data[groupType][i][8] != null
                && !MainPage.data[groupType][i][8].equals("")) {
                tempInt += Integer.valueOf(MainPage.data[groupType][i][8].toString());
                tempInt2 += 1;
            }
        }
        if (tempInt2 != 0) {
            tempInt /= tempInt2;
        }

        avgIntensity[groupType] = Integer.toString(tempInt);    //OUTPUT
        tempInt = 0;
        tempInt2 = 0;
        
    }
}

