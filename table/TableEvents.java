package table;

import general.Storage;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellEditEvent;
import mainpage.MainPage;

public class TableEvents{

        private EventHandler<CellEditEvent<Set, String>> set;
        private EventHandler<CellEditEvent<Set, String>> round;
        private EventHandler<CellEditEvent<Set, String>> rep;
        private EventHandler<CellEditEvent<Set, String>> distance;
        private EventHandler<CellEditEvent<Set, String>> description;
        private EventHandler<CellEditEvent<Set, String>> type;
        private EventHandler<CellEditEvent<Set, String>> min;
        private EventHandler<CellEditEvent<Set, String>> sec;
        private EventHandler<CellEditEvent<Set, String>> intensity;
        
    public TableEvents(int ID) {
        /*
         * Set Event Handler
         */
        EventHandler<CellEditEvent<Set, String>> setColEvent = 
            new EventHandler<CellEditEvent<Set, String>>() {
            public void handle(CellEditEvent<Set, String> t) {
                // Checks to see if the input is a number
                if (isNumeric(t.getNewValue()) && 
                    isValid(t.getNewValue(), 0, -1)) {    
                    //if true then the value is accepted
                    ((Set) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setSetCol(t.getNewValue());
                    ttlDistance(ID);
                    ttlTime(ID);
                    ttlIntensity(ID);
                    workingDistance(ID);
                    workingTime(ID);
                    workingIntensity(ID);
                    MainPage.getSide().updateSelectedData(ID);
                    MainPage.getTable(ID).getTableView().refresh();
                } else {
                    MainPage.getTable(ID).getTableView().refresh();
                }
            }
        };
        this.set = setColEvent;
        /* 
         * Round Event Handler
         */
        EventHandler<CellEditEvent<Set,String>> roundColEvent = 
            new EventHandler<CellEditEvent<Set, String>>() {
            @Override
            public void handle(CellEditEvent<Set, String> t) {
                // Checks to see if the input is a number
                if (isNumeric(t.getNewValue()) && 
                    isValid(t.getNewValue(), 0, -1)) {    
                    //if true then the value is accepted
                    ((Set) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setRoundsCol(t.getNewValue());
                    ttlDistance(ID);
                    workingDistance(ID);
                    MainPage.getSide().updateSelectedData(ID);
                    MainPage.getTable(ID).getTableView().refresh();
                } else {
                    MainPage.getTable(ID).getTableView().refresh();
                }
            }
        };
        this.round = roundColEvent;
        /*
         * Repetion Event Handler
         */
        EventHandler<CellEditEvent<Set,String>> repColEvent = 
            new EventHandler<CellEditEvent<Set, String>>() {
            @Override
            public void handle(CellEditEvent<Set, String> t) {
                // Checks to see if the input is a number
                if (isNumeric(t.getNewValue()) && 
                    isValid(t.getNewValue(), 0, -1)) {    
                    //if true then the value is accepted
                    ((Set) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setRepsCol(t.getNewValue());
                    ttlDistance(ID);
                    workingDistance(ID);
                    MainPage.getSide().updateSelectedData(ID);
                    MainPage.getTable(ID).getTableView().refresh();
                } else {
                    MainPage.getTable(ID).getTableView().refresh();
                }
            }
        };
        this.rep = repColEvent;
        /* 
         * Distance Event Handler
         */
        EventHandler<CellEditEvent<Set,String>> distanceColEvent = 
            new EventHandler<CellEditEvent<Set, String>>() {
            @Override
            public void handle(CellEditEvent<Set, String> t) {
                // Checks to see if the input is a number
                if (isNumeric(t.getNewValue()) && 
                    isValid(t.getNewValue(), 0, -1)) {    
                    //if true then the value is accepted
                    ((Set) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setDistanceCol(t.getNewValue());
                    ttlDistance(ID);
                    workingDistance(ID);
                    MainPage.getSide().updateSelectedData(ID);
                    MainPage.getTable(ID).getTableView().refresh();
                } else {
                    MainPage.getTable(ID).getTableView().refresh();
                }
            }
        };
        this.distance = distanceColEvent;
        /* 
         * Description Event Handler
         */
        EventHandler<CellEditEvent<Set,String>> descriptionColEvent = 
            new EventHandler<CellEditEvent<Set, String>>() {
            @Override
            public void handle(CellEditEvent<Set, String> t) {
                    ((Set) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setDescriptionCol(t.getNewValue()); 
                    MainPage.getTable(ID).getTableView().refresh();
            }
        };
        this.description = descriptionColEvent;
        /*
         * Type Event Handler
         */
        EventHandler<CellEditEvent<Set,String>> typeColEvent = 
            new EventHandler<CellEditEvent<Set, String>>() {
            @Override
            public void handle(CellEditEvent<Set, String> t) {
                    ((Set) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setTypeCol(t.getNewValue()); 
                    workingDistance(ID);
                    workingTime(ID);
                    workingIntensity(ID);
                    MainPage.getSide().updateSelectedData(ID);
                    MainPage.getTable(ID).getTableView().refresh();
            }
        };
        this.type = typeColEvent;
        /*
         * Minute Event Handler
         */
        EventHandler<CellEditEvent<Set,String>> minColEvent = 
            new EventHandler<CellEditEvent<Set, String>>() {
            @Override
            public void handle(CellEditEvent<Set, String> t) {
                // Checks to see if the input is a number
                if (isNumeric(t.getNewValue()) && 
                    isValid(t.getNewValue(), 0, -1)) {    
                    //if true then the value is accepted
                    ((Set) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setMinCol(t.getNewValue());
                    ttlTime(ID);
                    workingTime(ID);
                    MainPage.getSide().updateSelectedData(ID);
                    MainPage.getTable(ID).getTableView().refresh();
                } else {
                    MainPage.getTable(ID).getTableView().refresh();
                }
            }
        };
        this.min = minColEvent;
        /*
         * Second Event Handler
         */
        EventHandler<CellEditEvent<Set,String>> secColEvent = 
            new EventHandler<CellEditEvent<Set, String>>() {
            @Override
            public void handle(CellEditEvent<Set, String> t) {
                // Checks to see if the input is a number
                if (isNumeric(t.getNewValue()) && 
                    isValid(t.getNewValue(), 0, -1)) {    
                    //if true then the value is accepted
                    ((Set) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setSecCol(t.getNewValue());
                    ttlTime(ID);
                    workingTime(ID);
                    MainPage.getSide().updateSelectedData(ID);
                    MainPage.getTable(ID).getTableView().refresh();
                } else {
                    MainPage.getTable(ID).getTableView().refresh();
                }
            }
        };
        this.sec = secColEvent;
        /*
         * Intensity Event Handler
         */
            EventHandler<CellEditEvent<Set,String>> intensityColEvent = 
            new EventHandler<CellEditEvent<Set, String>>() {
            @Override
            public void handle(CellEditEvent<Set, String> t) {
                // Checks to see if the input is a number
                if (isNumeric(t.getNewValue()) && 
                    isValid(t.getNewValue(), 0, 100)) {    
                    //if true then the value is accepted
                    ((Set) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setIntensityCol(t.getNewValue());
                    ttlIntensity(ID);
                    workingIntensity(ID);
                    MainPage.getSide().updateSelectedData(ID);  
                    MainPage.getTable(ID).getTableView().refresh();
                } else {
                    MainPage.getTable(ID).getTableView().refresh();
                }
            }
        };
        this.intensity = intensityColEvent;
    }
    /*
     * Calculates the total distance in the chart 
     */
    private static void ttlDistance(int ID) {
        int dataSize = MainPage.getTable(ID).getData().size();
        int amtDistanceColumns = 4;
        boolean[] ttlDistance = new boolean[] {true, true, true, true, false, 
            false, false, false, false};//only for set, round, rep, and distance
        int[][] temp = new int[dataSize][amtDistanceColumns];
        try {
            temp = storeData(ID, dataSize, amtDistanceColumns, ttlDistance);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Incorrect amount of variables selected");
        }
        //System.out.println(Arrays.deepToString(temp));  //Checks returned array
        Storage.ttlDistanceAmts[ID - 1] = 0;
        for (int i = 0; i < dataSize; i++) {
                //Checks for a set Number to be present
                if (temp[i][0] != 0) {
                    //Checks for a round Number
                    if (temp[i][1] != 0) {
                        Storage.ttlDistanceAmts[ID - 1] += temp[i][1] * temp[i][2] 
                            * temp[i][3];
                    } else if (temp[i][1] == 0) {
                        Storage.ttlDistanceAmts[ID - 1] += temp[i][2] * temp[i][3];
                    }
                }
        }
    }
    
    private static void ttlTime(int ID) {
        int dataSize = MainPage.getTable(ID).getData().size();
        int amtDistanceColumns = 3;
        boolean[] ttlTime = new boolean[] {true, false, false, false, false, 
            false, true, true, false};//only for set, round, rep, and distance
        int[][] temp = new int[dataSize][amtDistanceColumns];
        try {
            temp = storeData(ID, dataSize, amtDistanceColumns, ttlTime);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Incorrect amount of variables selected");
        }
        double tempMin = 0;
        for (int i = 0; i < dataSize; i++) {
            //Checks for a set Number to be present
            if (temp[i][0] != 0) {
                //Checks for a round Number
                if (temp[i][1] != 0) {
                    tempMin += temp[i][1];
                }
                if (temp[i][2] != 0) {
                    tempMin += ((double)temp[i][2] / 60.0);
                }
            }
        }
        Storage.ttlTimeAmts[ID - 1] = tempMin;
    }
    
    private static void ttlIntensity(int ID) {
        int dataSize = MainPage.getTable(ID).getData().size();
        int amtDistanceColumns = 2;
        boolean[] ttlTime = new boolean[] {true, false, false, false, false, 
            false, false, false, true};//only for set, round, rep, and distance
        int[][] temp = new int[dataSize][amtDistanceColumns];
        try {
            temp = storeData(ID, dataSize, amtDistanceColumns, ttlTime);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Incorrect amount of variables selected");
        }
        int tempIntensity = 0;
        int denom = 0;
        for (int i = 0; i < dataSize; i++) {
            //Checks for a set to be present
            if (temp[i][0] != 0) {
                if (temp[i][1] != 0) {
                    denom += 1;
                    tempIntensity += temp[i][1];
                }
            } 
        }
        if (denom != 0) {
            tempIntensity = tempIntensity/denom;
        }
        Storage.ttlIntensity[ID - 1] = tempIntensity;
        System.out.println("Intensity: " + Storage.ttlIntensity[ID - 1]);
    }
    
    private static void workingDistance(int ID) {
        int dataSize = MainPage.getTable(ID).getData().size();
        int amtDistanceColumns = 5;
        boolean[] workingDistance = new boolean[] {true, true, true, true, false, 
            true, false, false, false};//only for set, round, rep, and distance
        int[][] temp = new int[dataSize][amtDistanceColumns];
        try {
            temp = storeData(ID, dataSize, amtDistanceColumns, workingDistance);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Incorrect amount of variables selected");
        }
        //System.out.println(Arrays.deepToString(temp));  //Checks returned array
        Storage.workingDistanceAmts[ID - 1] = 0;
        for (int i = 0; i < dataSize; i++) {
                //Checks for a set Number to be present
                if (temp[i][0] != 0 && temp[i][4] != 1) {
                    //Checks for a round Number
                    if (temp[i][1] != 0) {
                        Storage.workingDistanceAmts[ID - 1] += temp[i][1] 
                            * temp[i][2] * temp[i][3];
                    } else if (temp[i][1] == 0) {
                        Storage.workingDistanceAmts[ID - 1] += temp[i][2] 
                            * temp[i][3];
                    }
                }
        }
    }
    
    private static void workingTime(int ID) {
        int dataSize = MainPage.getTable(ID).getData().size();
        int amtDistanceColumns = 4;
        boolean[] ttlTime = new boolean[] {true, false, false, false, false, 
            true, true, true, false};//only for set, round, rep, and distance
        int[][] temp = new int[dataSize][amtDistanceColumns];
        try {
            temp = storeData(ID, dataSize, amtDistanceColumns, ttlTime);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Incorrect amount of variables selected");
        }
        double tempMin = 0;
        for (int i = 0; i < dataSize; i++) {
            //Checks for a set Number to be present
            if (temp[i][0] != 0 && temp[i][1] != 1) {
                //Checks for a round Number
                if (temp[i][2] != 0) {
                    tempMin += temp[i][2];
                }
                if (temp[i][3] != 0) {
                    tempMin += ((double)temp[i][3] / 60.0);
                }
            }
        }
        Storage.workningTimeAmts[ID - 1] = tempMin;
    }
    
    private static void workingIntensity(int ID) {
        int dataSize = MainPage.getTable(ID).getData().size();
        int amtDistanceColumns = 3;
        boolean[] ttlTime = new boolean[] {true, false, false, false, false, 
            true, false, false, true};//only for set, round, rep, and distance
        int[][] temp = new int[dataSize][amtDistanceColumns];
        try {
            temp = storeData(ID, dataSize, amtDistanceColumns, ttlTime);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Incorrect amount of variables selected");
        }
        int tempIntensity = 0;
        int denom = 0;
        for (int i = 0; i < dataSize; i++) {
            //Checks for a set to be present
            if (temp[i][0] != 0 && temp[i][1] != 1) {
                if (temp[i][2] != 0) {
                    denom += 1;
                    tempIntensity += temp[i][2];
                }
            } 
        }
        if (denom != 0) {
            tempIntensity = tempIntensity/denom;
        }
        Storage.workingIntensity[ID - 1] = tempIntensity; 
    }
    
    /*
     * 
     */
    private static int[][] storeData(int ID, int rows, int columns, boolean[] check) throws Exception{
        int[][] temp = new int[rows][columns];
        Set tempSet;
        //Checks to see if each column in null, if it is null, it is stored 
        //as 0
        int amtSelected = 0;
        for (int i = 0; i < check.length; i++) {
            if (check[i]) {
                amtSelected += 1;
            }
        }
        if (amtSelected != columns) {
            throw new Exception();
        }
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                temp[i][j] = 0;
            }
        }
        for (int i = 0; i < rows; i++) {
            tempSet = MainPage.getTable(ID).getData().get(i);
            int indexCount = -1;
            if (check[0]) {
                indexCount += 1;
                if (tempSet.getSetCol() != null &&
                    !tempSet.getSetCol().equals("")) {
                temp[i][indexCount] = Integer.valueOf(tempSet.getSetCol());
                }
            }
            if (check[1]) {
                indexCount += 1;
                if ( tempSet.getRoundsCol() != null &&
                    !tempSet.getRoundsCol().equals("")) {
                temp[i][indexCount] = Integer.valueOf(tempSet.getRoundsCol());
                }
            }
            if (check[2]) {
                indexCount += 1; 
                if (tempSet.getRepsCol() != null &&
                    !tempSet.getRepsCol().equals("")) {
                temp[i][indexCount] = Integer.valueOf(tempSet.getRepsCol());
                }
            }
            if (check[3]) {
                indexCount +=1;
                if (tempSet.getDistanceCol() != null &&
                    !tempSet.getDistanceCol().contentEquals("")) {
                temp[i][indexCount] = Integer.valueOf(tempSet.getDistanceCol());
                }
            }
            //Default returns -1 because it will always be a string
            if (check[4]) {
                indexCount += 1;
                temp[i][indexCount] = -1;
            }
            if (check[5]) {
                indexCount += 1;
                if (tempSet.getTypeCol() != null &&
                    !tempSet.getTypeCol().contentEquals("") &&
                    (tempSet.getTypeCol() == "Warm Up" ||
                    tempSet.getTypeCol() == "Loosen")) {
                    temp[i][indexCount] = 1;
                }
            }
            if (check[6]) {
                indexCount += 1;
                if (tempSet.getMinCol() != null &&
                    !tempSet.getMinCol().contentEquals("")) {
                temp[i][indexCount] = Integer.valueOf(tempSet.getMinCol());
                }
            }
            if (check[7]) {
                indexCount += 1;
                if (tempSet.getSecCol() != null &&
                !tempSet.getSecCol().contentEquals("")) {
                temp[i][indexCount] = Integer.valueOf(tempSet.getSecCol());
                }
            }
            if (check[8]) {
                indexCount += 1;
                if (tempSet.getIntensityCol() != null &&
                !tempSet.getIntensityCol().contentEquals("")) {
                temp[i][indexCount] = Integer.valueOf(tempSet.getIntensityCol());
                }
            }
        }
        return temp;
    }
    
    /*
     * Checks to see if the input string is a number
     */
    private boolean isNumeric(String value) {
        if (value.equals(null) || value.isEmpty()) {
            return false;
        }
        
        try {
            @SuppressWarnings("unused")
            double d = Integer.parseInt(value);
        } catch (NumberFormatException nfe) {
            Alert alert =  new Alert(AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setContentText("Error: " + value + " is not an accepted input."
                + " Input must be a whole number.");
            alert.showAndWait();
            return false;
        } 
        return true;
    }
    
    private boolean isValid(String value, int lowerBound, int upperBound) {
        if (value.equals(null) || value.isEmpty()) {
            return false;
        }
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Input Error");
        if (upperBound == -1) {
            if (Integer.parseInt(value) < lowerBound) {
                alert.setContentText("Error: " + value + " is not an accepted input."
                    + " Input is less than " + lowerBound);
                alert.showAndWait();
                return false;
            }
        } else {
            if (Integer.parseInt(value) > upperBound) {
                alert.setContentText("Error: " + value + " is not an accepted input."
                    + " Input is over " + upperBound);
                alert.showAndWait();
                return false;
            }
            if (Integer.parseInt(value) < lowerBound) {
                alert.setContentText("Error: " + value + " is not an accepted input."
                    + " Input is less than " + lowerBound);
                alert.showAndWait();
                return false;
            }
        }
        return true;
    }

    public EventHandler<CellEditEvent<Set, String>> getSet() {
        return set;
    }
    
    public EventHandler<CellEditEvent<Set, String>> getRound() {
        return round;
    }
    
    public EventHandler<CellEditEvent<Set, String>> getRep() {
        return rep;
    }
    
    public EventHandler<CellEditEvent<Set, String>> getDistance() {
        return distance;
    }
    
    public EventHandler<CellEditEvent<Set, String>> getDescription() {
        return description;
    }
    
    public EventHandler<CellEditEvent<Set, String>> getType() {
        return type;
    }
    
    public EventHandler<CellEditEvent<Set, String>> getMin() {
        return min;
    }
    
    public EventHandler<CellEditEvent<Set, String>> getSec() {
        return sec;
    }
    
    public EventHandler<CellEditEvent<Set, String>> getIntensity() {
        return intensity;
    }
    
    public static void refreshSideData(int ID) {
            ttlDistance(ID);
            ttlTime(ID);
            ttlIntensity(ID);
            workingDistance(ID);
            workingTime(ID);
            workingIntensity(ID);
    }
}
