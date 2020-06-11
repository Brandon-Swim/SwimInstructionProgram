package table;

import java.util.Arrays;
import general.Storage;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellEditEvent;
import mainpage.MainPage;

public class TableEvents{

        private int ID;
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
        this.ID = ID;
        /*
         * Set Event Handler
         */
        EventHandler<CellEditEvent<Set, String>> setColEvent = 
            new EventHandler<CellEditEvent<Set, String>>() {
            public void handle(CellEditEvent<Set, String> t) {
                // Checks to see if the input is a number
                if (isNumeric(t.getNewValue())) {    
                    //if true then the value is accepted
                    ((Set) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setSetCol(t.getNewValue());
                    ttlDistance(ID);
                } else {
                    //if false, the old value remains    
                    //Lazy but it works
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
                if (isNumeric(t.getNewValue())) {    
                    //if true then the value is accepted
                    ((Set) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setRoundsCol(t.getNewValue());
                    ttlDistance(ID);
                } else {
                    //if false, the old value remains    
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
                if (isNumeric(t.getNewValue())) {    
                    //if true then the value is accepted
                    ((Set) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setRepsCol(t.getNewValue());
                    ttlDistance(ID);
                } else {
                    //if false, the old value remains    
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
                if (isNumeric(t.getNewValue())) {    
                    //if true then the value is accepted
                    ((Set) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setDistanceCol(t.getNewValue());
                    ttlDistance(ID);
                } else {
                    //if false, the old value remains    
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
                // Checks to see if the input is a number
                if (isNumeric(t.getNewValue())) {    
                    //if true then the value is accepted
                    ((Set) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setTypeCol(t.getNewValue());
                } else {
                    //if false, the old value remains    
                    MainPage.getTable(ID).getTableView().refresh();
                }
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
                if (isNumeric(t.getNewValue())) {    
                    //if true then the value is accepted
                    ((Set) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setMinCol(t.getNewValue());
                } else {
                    //if false, the old value remains    
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
                if (isNumeric(t.getNewValue())) {    
                    //if true then the value is accepted
                    ((Set) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setSecCol(t.getNewValue());
                } else {
                    //if false, the old value remains    
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
                if (isNumeric(t.getNewValue())) {    
                    //if true then the value is accepted
                    ((Set) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setIntensityCol(t.getNewValue());
                } else {
                    //if false, the old value remains    
                    MainPage.getTable(ID).getTableView().refresh();
                }
            }
        };
        this.intensity = intensityColEvent;
    }
    
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
        System.out.println(Arrays.deepToString(temp));  //Checks returned array
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
                } else {
                    Storage.ttlDistanceAmts[ID] += 0;
                }
                MainPage.getSide().updateSelectedData(ID);
        }
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
            tempSet = MainPage.getTable(ID).getData().get(i);
            if (check[0] && tempSet.getSetCol() != null &&
                !tempSet.getSetCol().equals("")) {
                temp[i][0] = Integer.valueOf(tempSet.getSetCol());
            } else if (!check[0]  && columns >= 0) {
                temp[i][0] = 0;
            }
            if (check[1] && tempSet.getRoundsCol() != null &&
                !tempSet.getRoundsCol().equals("")) {
                temp[i][1] = Integer.valueOf(tempSet.getRoundsCol());
            } else if (!check[2]  && columns >= 2) {
                temp[i][1] = 0;
            }
            
            if (check[2] && tempSet.getRepsCol() != null &&
                !tempSet.getRepsCol().equals("")) {
                temp[i][2] = Integer.valueOf(tempSet.getRepsCol());
            } else if (!check[2]  && columns >= 1) {
                temp[i][2] = 0;
            }
            if (check[3] && tempSet.getDistanceCol() != null &&
                !tempSet.getDistanceCol().contentEquals("")) {
                temp[i][3] = Integer.valueOf(tempSet.getDistanceCol());
            } else if (!check[3]  && columns >= 3) {
                temp[i][3] = 0;
            }
            //Default returns -1 because it will always be a string
            if (check[4] && columns >= 4) {
                temp[i][4] = -1;
            }
            if (check[5] && columns >= 5) {
                temp[i][5] = -1;
            }
            if (check[6] && tempSet.getMinCol() != null &&
                !tempSet.getMinCol().contentEquals("")) {
                temp[i][6] = Integer.valueOf(tempSet.getMinCol());;
            } else if (!check[6]  && columns >= 6) {
                temp[i][6] = 0;
            }
            if (check[7] && tempSet.getSecCol() != null &&
                !tempSet.getSecCol().contentEquals("")) {
                temp[i][7] = Integer.valueOf(tempSet.getSecCol());
            } else if (!check[7]  && columns >= 7) {
                temp[i][7] = 0;
            }
            if (check[8] && tempSet.getIntensityCol() != null &&
                !tempSet.getIntensityCol().contentEquals("")) {
                temp[i][8] = Integer.valueOf(tempSet.getIntensityCol());
            } else if (!check[8]  && columns >= 8) {
                temp[i][8] = 0;
            }
        }
        return temp;
    }
    
    /*
     * Checks to see if the input string is a number
     */
    private static boolean isNumeric(String value) {
        if (value.equals(null) || value.isEmpty()) {
            return false;
        }
        
        try {
            @SuppressWarnings("unused")
            double d = Double.parseDouble(value);
        } catch (NumberFormatException nfe) {
            Alert alert =  new Alert(AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setContentText("Error: " + value + " is not an accepted input."
                + " Input must be a number.");
            alert.showAndWait();
            return false;
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
}
