import javax.jws.soap.SOAPBinding.Style;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.table.*;
import javax.swing.event.*;

public class TableListener implements TableModelListener {
    Integer groupType = 0;
    final static int AMT_TABLES = 5;
    static String[] ttlDistance = new String[AMT_TABLES];
    static String[] ttlTimeMin = new String[AMT_TABLES];
    static String[] ttlTimeSec = new String[AMT_TABLES];
    static String[] avgIntensity = new String[AMT_TABLES];
    static String[] workingDistance = new String[AMT_TABLES];
    static String[] workingTimeMin = new String[AMT_TABLES];
    static String[] workingTimeSec = new String[AMT_TABLES];
    static String[] workingIntensity = new String[AMT_TABLES];

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
            workingDistance[i] = "0";
            workingTimeMin[i] = "0";
            workingTimeSec[i] = "0";
            workingIntensity[i] = "0";
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
                MainPage.sideData[3].setText("<html> Working Distance: <br/>" + 
                "<center>" + workingDistance[0] + " yds<center></html>");
                if (Integer.valueOf(workingTimeSec[0]) == 0) {
                    MainPage.sideData[4].setText("Working Time: " 
                    + workingTimeMin[0] + " min ");
                } else {
                    MainPage.sideData[4].setText(
                    "Working Time: " + workingTimeMin[0] + " min " 
                     + workingTimeSec[0] + " Sec");
                }
                MainPage.sideData[5].setText("Working Intensity: " + workingIntensity[0] + "%");
                break;
            case 2:
                MainPage.sideData[0].setText("<html> Group Distance (yds): <br/>" 
                    + "<center>" + ttlDistance[0]+ " | " + ttlDistance[1] + "<center></html>");
                MainPage.sideData[1].setText("<html> Group Time (Mins): <br/>" 
                    + "<center>" + ttlTimeMin[0]+ " | " + ttlTimeMin[1] + "<center></html>");
                MainPage.sideData[2].setText("<html> Group Intensity (%): <br/>" 
                    + "<center>" + avgIntensity[0] + " | " + avgIntensity[1] + "<center></html>");
                MainPage.sideData[3].setText("<html> Working Distance (yds): <br/>" + 
                "<center>" + workingDistance[0] + " | " + workingDistance[1] + "<center></html>");
                MainPage.sideData[4].setText("<html> Working Time (Mins): <br/>" 
                    + "<center>" + workingTimeMin[0]+ " | " + workingTimeMin[1] + "<center></html>");
                MainPage.sideData[5].setText("<html> Working Intensity (%): <br/>" 
                    + "<center>" + workingIntensity[0] + " | " + workingIntensity[1] + "<center></html>");
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
                MainPage.sideData[3].setText("<html> Working Distance (yds): <br/>" + 
                "<center>" + workingDistance[0] + " | " + workingDistance[1] + 
                " | " + workingDistance[2] + "<center></html>");
                MainPage.sideData[4].setText("<html> Working Time (Mins): <br/>" 
                    + "<center>" + workingTimeMin[0]+ " | " + workingTimeMin[1] + 
                    " | " + workingTimeMin[2] + "<center></html>");
                MainPage.sideData[5].setText("<html> Working Intensity (%): <br/>" 
                    + "<center>" + workingIntensity[0] + " | " + workingIntensity[1] 
                        + " | " + workingIntensity[2] + "<center></html>");
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
                MainPage.sideData[3].setText("<html> Working Distance (yds): <br/>" + 
                "<center>" + workingDistance[0] + " | " + workingDistance[1] + 
                " | " + workingDistance[2] + " | " + workingDistance[3] + "<center></html>");
                MainPage.sideData[4].setText("<html> Working Time (Mins): <br/>" 
                    + "<center>" + workingTimeMin[0]+ " | " + workingTimeMin[1] + 
                    " | " + workingTimeMin[2] + " | " + workingTimeMin[3] + "<center></html>");
                MainPage.sideData[5].setText("<html> Working Intensity (%): <br/>" 
                    + "<center>" + workingIntensity[0] + " | " + workingIntensity[1] 
                        + " | " + workingIntensity[2] + " | " + workingIntensity[3] + "<center></html>");
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
                MainPage.sideData[3].setText("<html> Working Distance (yds): <br/>" + 
                "<center>" + workingDistance[0] + " | " + workingDistance[1] + 
                " | " + workingDistance[2] + " | " + workingDistance[3] + 
                " | " + workingDistance[4] + "<center></html>");
                MainPage.sideData[4].setText("<html> Working Time (Mins): <br/>" 
                    + "<center>" + workingTimeMin[0]+ " | " + workingTimeMin[1] + 
                    " | " + workingTimeMin[2] + " | " + workingTimeMin[3] + 
                    " | " + workingTimeMin[4] + "<center></html>");
                MainPage.sideData[5].setText("<html> Working Intensity (%): <br/>" 
                    + "<center>" + workingIntensity[0] + " | " + workingIntensity[1] 
                        + " | " + workingIntensity[2] + " | " + workingIntensity[3] 
                            + " | " + workingIntensity[4] + "<center></html>");
                break;
            default:
                break;
        }
         //System.out.println("A:" + Arrays.deepToString(MainPage.data));
         //System.out.println("B:" + Arrays.deepToString(storage));
        }
    public void GetDataChange() {
        TotalDistanceChange();
        TotalTimeChange();
        AverageIntensityChange();
        WorkingDistanceChagne();
        WorkingTimeChange();
        WorkingIntensityChange();
        TypeChartChange();
    }
    public void TotalDistanceChange() { //Update Total Distance
        int tempInt = 0;
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
    }
    
    public void TotalTimeChange() {
        int tempInt = 0;
        int tempInt2 = 0;
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
    }
    
    public void AverageIntensityChange() {
        int tempInt = 0;
        int tempInt2 = 0;
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
    }
    
    public void WorkingDistanceChagne() {
        int tempInt = 0;
        for (int i = 0; i < MainPage.data[groupType].length; i++) {
            if (MainPage.data[groupType][i][0] != null 
                && MainPage.data[groupType][i][2] != null
                && MainPage.data[groupType][i][3] != null
                && !MainPage.data[groupType][i][0].equals("")
                && !MainPage.data[groupType][i][2].equals("")
                && !MainPage.data[groupType][i][3].equals("")) {
                if (!storage[groupType][i][5].toString().contentEquals("Warm Up") &&
                    !storage[groupType][i][5].toString().contentEquals("Loosen")) {
                    if (storage[groupType][i][1] != null && !storage[groupType][i][1].equals(0)
                        && !storage[groupType][i][1].equals("")) {
                        tempInt += Integer.valueOf(storage[groupType][i][3].toString())
                            * Integer.valueOf(storage[groupType][i][2].toString())
                            * Integer.valueOf(storage[groupType][i][1].toString());
                        System.out.println(storage[groupType][i][5]);
                    } else if (storage[groupType][i][1] == null
                        || storage[groupType][i][1].equals(0)
                        || storage[groupType][i][1].equals("")) {
                        tempInt += Integer.valueOf(storage[groupType][i][3].toString())
                            * Integer.valueOf(storage[groupType][i][2].toString());
                    }
                }
            }
        }
        workingDistance[groupType] = Integer.toString(tempInt); //OUTPUT
    }
    
    public void WorkingTimeChange() {
        int tempInt = 0;
        int tempInt2 = 0;
        for (int i = 0; i < MainPage.data[groupType].length; i++) {
            if (!storage[groupType][i][5].toString().contentEquals("Warm Up") &&
                    !storage[groupType][i][5].toString().contentEquals("Loosen")) {
                if (MainPage.data[groupType][i][0] != null
                    && !MainPage.data[groupType][i][0].equals("")) { // Checks for set
                    if (storage[groupType][i][1] != null && !storage[groupType][i][1].equals("")) { // Checks for round, enter if = #
                        if (MainPage.data[groupType][i][6] != null
                            && !MainPage.data[groupType][i][6].equals("")
                            && MainPage.data[groupType][i][2] != null
                            && !MainPage.data[groupType][i][2].equals("")) {
                            tempInt += Integer.valueOf(storage[groupType][i][1].toString())
                                * Integer.valueOf(storage[groupType][i][2].toString())
                                * Integer.valueOf(MainPage.data[groupType][i][6].toString()); // non-normal                                                                       // calc
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
        }
        workingTimeMin[groupType] = Integer.toString(tempInt);  //OUTPUT
        workingTimeSec[groupType] = Integer.toString(tempInt2); //OUTPUT
    }
    
    public void WorkingIntensityChange() {
        int tempInt = 0;
        int tempInt2 = 0;
        for (int i = 0; i < MainPage.data[groupType].length; i++) {
            if (MainPage.data[groupType][i][8] != null
                && !MainPage.data[groupType][i][8].equals("") 
                && !storage[groupType][i][5].toString().contentEquals("Warm Up") 
                && !storage[groupType][i][5].toString().contentEquals("Loosen")) {
                tempInt += Integer.valueOf(MainPage.data[groupType][i][8].toString());
                tempInt2 += 1;
            }
        }
        if (tempInt2 != 0) {
            tempInt /= tempInt2;
        }

        workingIntensity[groupType] = Integer.toString(tempInt);    //OUTPUT
    }
    
    public void TypeChartChange() {
        int[] tempInt = new int[MainPage.types.length];
        for (int i = 0; i < MainPage.data[groupType].length; i++) {
            if (MainPage.data[groupType][i][0] != null  //set,reps,distance,type 
                && MainPage.data[groupType][i][2] != null
                && MainPage.data[groupType][i][3] != null
                && MainPage.data[groupType][i][5] != null
                && !MainPage.data[groupType][i][0].equals("")
                && !MainPage.data[groupType][i][2].equals("")
                && !MainPage.data[groupType][i][3].equals("")
                && !MainPage.data[groupType][i][5].equals("")) {
                for (int j = 0; j < MainPage.types.length; j++) {
                     if (MainPage.data[groupType][i][5]
                         .toString().equals(MainPage.types[j])) {
                            if (storage[groupType][i][1] != null && !storage[groupType][i][1].equals(0) //non 0 distance
                                && !storage[groupType][i][1].equals("")) {
                                tempInt[j] += Integer.valueOf(storage[groupType][i][3].toString())
                                    * Integer.valueOf(storage[groupType][i][2].toString())
                                    * Integer.valueOf(storage[groupType][i][1].toString());
                            } else if (storage[groupType][i][1] == null //0 distance
                                || storage[groupType][i][1].equals(0)
                                || storage[groupType][i][1].equals("")) {
                                tempInt[j] += Integer.valueOf(storage[groupType][i][3].toString())
                                    * Integer.valueOf(storage[groupType][i][2].toString());
                            }
                        }
                    }
            }
        }
        for (int i = 0; i < MainPage.types.length; i++) {
            MainPage.ringChartData[0].setValue(MainPage.types[i], tempInt[i]);
        }
    }
    
    public void SetIntensityChartChange() {
        
    }
    
    public void SetDistanceChartChange() {
        
    }
}

