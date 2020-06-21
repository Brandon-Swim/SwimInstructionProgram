package table;

import java.util.Arrays;
import general.Storage;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.text.Text;
import mainpage.MainPage;

public class TableEvents {

  private EventHandler<CellEditEvent<Set, String>> set;
  private EventHandler<CellEditEvent<Set, String>> round;
  private EventHandler<CellEditEvent<Set, String>> rep;
  private EventHandler<CellEditEvent<Set, String>> distance;
  private EventHandler<CellEditEvent<Set, String>> description;
  private EventHandler<CellEditEvent<Set, String>> type;
  private EventHandler<CellEditEvent<Set, String>> min;
  private EventHandler<CellEditEvent<Set, String>> sec;
  private EventHandler<CellEditEvent<Set, String>> intensity;
  protected static final int SET = 0;
  protected static final int ROUNDS = 1;
  protected static final int REPS = 2;
  protected static final int DISTANCE = 3;
  protected static final int DESCRIPTION = 4;
  protected static final int TYPE = 5;
  protected static final int MINUTES = 6;
  protected static final int SECONDS = 7;
  protected static final int INTENSITY = 8;

  public TableEvents() {
    
  }
  
  public TableEvents(int ID) {
    GraphEvents g = new GraphEvents(ID);
    
    /*
     * Set Event Handler
     */
    EventHandler<CellEditEvent<Set, String>> setColEvent =
        new EventHandler<CellEditEvent<Set, String>>() {
          public void handle(CellEditEvent<Set, String> t) {
            // Checks to see if the input is a number
            if (isNumeric(t.getNewValue()) && isValid(t.getNewValue(), 0, -1)) {
              // if true then the value is accepted
              ((Set) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                  .setSetCol(t.getNewValue());
              ttlDistance(ID);
              ttlTime(ID);
              ttlIntensity(ID);
              workingDistance(ID);
              workingTime(ID);
              workingIntensity(ID);
              g.getDistanceGraphEvent();
              g.getIntensityGraphEvent();
              g.getTypeGraphEvent();
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
    EventHandler<CellEditEvent<Set, String>> roundColEvent =
        new EventHandler<CellEditEvent<Set, String>>() {
          @Override
          public void handle(CellEditEvent<Set, String> t) {
            // Checks to see if the input is a number
            if (isNumeric(t.getNewValue()) && isValid(t.getNewValue(), 0, -1)) {
              // if true then the value is accepted
              ((Set) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                  .setRoundsCol(t.getNewValue());
              ttlDistance(ID);
              ttlTime(ID);
              workingDistance(ID);
              workingTime(ID);
              g.getDistanceGraphEvent();
              g.getTypeGraphEvent();
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
    EventHandler<CellEditEvent<Set, String>> repColEvent =
        new EventHandler<CellEditEvent<Set, String>>() {
          @Override
          public void handle(CellEditEvent<Set, String> t) {
            // Checks to see if the input is a number
            if (isNumeric(t.getNewValue()) && isValid(t.getNewValue(), 0, -1)) {
              // if true then the value is accepted
              ((Set) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                  .setRepsCol(t.getNewValue());
              ttlDistance(ID);
              workingDistance(ID);
              g.getDistanceGraphEvent();
              g.getTypeGraphEvent();
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
    EventHandler<CellEditEvent<Set, String>> distanceColEvent =
        new EventHandler<CellEditEvent<Set, String>>() {
          @Override
          public void handle(CellEditEvent<Set, String> t) {
            // Checks to see if the input is a number
            if (isNumeric(t.getNewValue()) && isValid(t.getNewValue(), 0, -1)) {
              // if true then the value is accepted
              ((Set) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                  .setDistanceCol(t.getNewValue());
              ttlDistance(ID);
              workingDistance(ID);
              g.getDistanceGraphEvent();
              g.getTypeGraphEvent();
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
    EventHandler<CellEditEvent<Set, String>> descriptionColEvent =
        new EventHandler<CellEditEvent<Set, String>>() {
          @Override
          public void handle(CellEditEvent<Set, String> t) {
            ((Set) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                .setDescriptionCol(t.getNewValue());
            MainPage.getTable(ID).getTableView().refresh();
          }
        };
    this.description = descriptionColEvent;
    /*
     * Type Event Handler
     */
    EventHandler<CellEditEvent<Set, String>> typeColEvent =
        new EventHandler<CellEditEvent<Set, String>>() {
          @Override
          public void handle(CellEditEvent<Set, String> t) {
            ((Set) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                .setTypeCol(t.getNewValue());
            workingDistance(ID);
            workingTime(ID);
            workingIntensity(ID);
            g.getDistanceGraphEvent();  //TODO
            g.getIntensityGraphEvent();
            g.getTypeGraphEvent();
            MainPage.getSide().updateSelectedData(ID);
            MainPage.getTable(ID).getTableView().refresh();
          }
        };
    this.type = typeColEvent;
    /*
     * Minute Event Handler
     */
    EventHandler<CellEditEvent<Set, String>> minColEvent =
        new EventHandler<CellEditEvent<Set, String>>() {
          @Override
          public void handle(CellEditEvent<Set, String> t) {
            // Checks to see if the input is a number
            if (isNumeric(t.getNewValue()) && isValid(t.getNewValue(), 0, -1)) {
              // if true then the value is accepted
              ((Set) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                  .setMinCol(t.getNewValue());
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
    EventHandler<CellEditEvent<Set, String>> secColEvent =
        new EventHandler<CellEditEvent<Set, String>>() {
          @Override
          public void handle(CellEditEvent<Set, String> t) {
            // Checks to see if the input is a number
            if (isNumeric(t.getNewValue()) && isValid(t.getNewValue(), 0, -1)) {
              // if true then the value is accepted
              ((Set) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                  .setSecCol(t.getNewValue());
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
    EventHandler<CellEditEvent<Set, String>> intensityColEvent =
        new EventHandler<CellEditEvent<Set, String>>() {
          @Override
          public void handle(CellEditEvent<Set, String> t) {
            // Checks to see if the input is a number
            if (isNumeric(t.getNewValue()) && isValid(t.getNewValue(), 0, 100)) {
              // if true then the value is accepted
              ((Set) t.getTableView().getItems().get(t.getTablePosition().getRow()))
                  .setIntensityCol(t.getNewValue());
              ttlIntensity(ID);
              workingIntensity(ID);
              g.getIntensityGraphEvent();
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
    int index = ID - 1;
    int dataSize = MainPage.getTable(ID).getData().size();
    int amtDistanceColumns = 4;
    boolean[] ttlDistance =
        new boolean[] {true, true, true, true, false, false, false, false, false};// only for set,
                                                                                  // round, rep, and                                                                          // distance
    int[][] temp = new int[dataSize][amtDistanceColumns];
    try {
      temp = ListToArray(ID, dataSize, ttlDistance);
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Incorrect amount of variables selected");
    }
    for (int i = 0; i < temp.length; i++) {
      if (temp[i][SET] != 0 && temp[i][ROUNDS] != 0) {
        for (int j = i + 1; j < temp.length; j++) {
          if (temp[j][SET] == temp[i][SET]) {
            temp[j][ROUNDS] = temp[i][ROUNDS];
          }
        }
      }
    }
    //System.out.println(Arrays.deepToString(temp)); //Checks returned array
    // Update Storage data
    Storage.ttlDistanceAmts[index] = 0;
    for (int i = 0; i < dataSize; i++) {
      // Checks for a set Number to be present
      if (temp[i][SET] != 0) {
        // Checks for a round Number
        if (temp[i][ROUNDS] != 0) {
          Storage.ttlDistanceAmts[index] += temp[i][ROUNDS] * temp[i][REPS] * temp[i][DISTANCE];
        } else if (temp[i][ROUNDS] == 0) {
          Storage.ttlDistanceAmts[index] += temp[i][REPS] * temp[i][DISTANCE];
        }
      }
    }
  }

  private static void ttlTime(int ID) {
    int index = ID - 1;
    int dataSize = MainPage.getTable(ID).getData().size();
    int amtDistanceColumns = 3;
    // only for set, min, and sec
    boolean[] ttlTime = new boolean[] {true, true, false, false, false, false, true, true, false};
    int[][] temp = new int[dataSize][amtDistanceColumns];
    try {
      temp = ListToArray(ID, dataSize, ttlTime);
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Incorrect amount of variables selected");
    }
    for (int i = 0; i < temp.length; i++) {
      if (temp[i][SET] != 0 && temp[i][ROUNDS] != 0) {
        for (int j = i + 1; j < temp.length; j++) {
          if (temp[j][SET] == temp[i][SET]) {
            temp[j][ROUNDS] = temp[i][ROUNDS];
          }
        }
      }
    }
    double tempMin = 0;
    for (int i = 0; i < dataSize; i++) {
      // Checks for a set Number to be present
      if (temp[i][SET] != 0) {
        // Checks for a round Number
        if (temp[i][ROUNDS] != 0) {
          if (temp[i][MINUTES] != 0) {
            tempMin += temp[i][MINUTES] * temp[i][ROUNDS];
          }
          if (temp[i][SECONDS] != 0) {
            tempMin += ((double)(temp[i][SECONDS] * temp[i][ROUNDS])/ 60.0);
          }
        } else if (temp[i][ROUNDS] == 0) {
          if (temp[i][MINUTES] != 0) {
            tempMin += temp[i][MINUTES];
          }
          if (temp[i][SECONDS] != 0) {
            tempMin += ((double) temp[i][SECONDS] / 60.0);
          }
        }
      }
    }
    Storage.ttlTimeAmts[index] = tempMin;
  }

  private static void ttlIntensity(int ID) {
    int index = ID - 1;
    int dataSize = MainPage.getTable(ID).getData().size();
    int amtDistanceColumns = 2;
    // ony for set and intensiy
    boolean[] ttlIntensity = new boolean[] {true, false, false, false, false, false, false, false, true};
    int[][] temp = new int[dataSize][amtDistanceColumns];
    try {
      temp = ListToArray(ID, dataSize, ttlIntensity);
      //System.out.println(Arrays.deepToString(temp));
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Incorrect amount of variables selected");
    }
    int tempIntensity = 0;
    int denom = 0;
    for (int i = 0; i < dataSize; i++) {
      // Checks for a set to be present
      if (temp[i][SET] != 0) {
        if (temp[i][INTENSITY] != 0) {
          denom += 1;
          tempIntensity += temp[i][INTENSITY];
        }
      }
    }
    if (denom != 0) {
      tempIntensity = tempIntensity / denom;
    }
    Storage.ttlIntensity[index] = tempIntensity;
  }

  private static void workingDistance(int ID) {
    int index = ID - 1;
    int dataSize = MainPage.getTable(ID).getData().size();
    int amtDistanceColumns = 5;
    // only for set, round, rep, distance, and type
    boolean[] workingDistance =
        new boolean[] {true, true, true, true, false, true, false, false, false};
    int[][] temp = new int[dataSize][amtDistanceColumns];
    try {
      temp = ListToArray(ID, dataSize, workingDistance);
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Incorrect amount of variables selected");
    }
    for (int i = 0; i < temp.length; i++) {
      if (temp[i][SET] != 0 && temp[i][ROUNDS] != 0) {
        for (int j = i + 1; j < temp.length; j++) {
          if (temp[j][SET] == temp[i][SET]) {
            temp[j][ROUNDS] = temp[i][ROUNDS];
          }
        }
      }
    }
     //System.out.println(Arrays.deepToString(temp)); //Checks returned array
    Storage.workingDistanceAmts[index] = 0;
    for (int i = 0; i < dataSize; i++) {
      // Checks for a set Number to be present
      if (temp[i][SET] != 0 && temp[i][TYPE] != 1) {
        // Checks for a round Number
        if (temp[i][ROUNDS] != 0) {
          Storage.workingDistanceAmts[index] += temp[i][ROUNDS] * temp[i][REPS] * temp[i][DISTANCE];
        } else if (temp[i][ROUNDS] == 0) {
          Storage.workingDistanceAmts[index] += temp[i][REPS] * temp[i][DISTANCE];
        }
      }
    }
  }

  private static void workingTime(int ID) {
    int index = ID - 1;
    int dataSize = MainPage.getTable(ID).getData().size();
    int amtDistanceColumns = 4;
    // only for set, type, min, and sec
    boolean[] workingTime = new boolean[] {true, true, false, false, false, true, true, true, false};
    int[][] temp = new int[dataSize][amtDistanceColumns];
    try {
      temp = ListToArray(ID, dataSize, workingTime);
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Incorrect amount of variables selected");
    }
    for (int i = 0; i < temp.length; i++) {
      if (temp[i][SET] != 0 && temp[i][ROUNDS] != 0) {
        for (int j = i + 1; j < temp.length; j++) {
          if (temp[j][SET] == temp[i][SET]) {
            temp[j][ROUNDS] = temp[i][ROUNDS];
          }
        }
      }
    }
    //System.out.println(Arrays.deepToString(temp));
    double tempMin = 0;
    for (int i = 0; i < dataSize; i++) {
      // Checks for a set Number to be present
      if (temp[i][SET] != 0 && temp[i][TYPE] != 1) {
        // Checks for a round Number
        if (temp[i][ROUNDS] != 0) {
          if (temp[i][MINUTES] != 0) {
            tempMin += temp[i][MINUTES] * temp[i][ROUNDS];
          }
          if (temp[i][SECONDS] != 0) {
            tempMin += ((double)(temp[i][SECONDS] * temp[i][ROUNDS])/ 60.0);
          }
        } else if (temp[i][ROUNDS] == 0) {
          if (temp[i][MINUTES] != 0) {
            tempMin += temp[i][MINUTES];
          }
          if (temp[i][SECONDS] != 0) {
            tempMin += ((double) temp[i][SECONDS] / 60.0);
          }
        }
      }
    }
    Storage.workningTimeAmts[index] = tempMin;
  }

  private static void workingIntensity(int ID) {
    int index = ID - 1;
    int dataSize = MainPage.getTable(ID).getData().size();
    int amtDistanceColumns = 3;
    // only for set, type, and intensity
    boolean[] workingIntensity = new boolean[] {true, false, false, false, false, true, false, false, true};
    int[][] temp = new int[dataSize][amtDistanceColumns];
    try {
      temp = ListToArray(ID, dataSize, workingIntensity);
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Incorrect amount of variables selected");
    }
    int tempIntensity = 0;
    int denom = 0;
    for (int i = 0; i < dataSize; i++) {
      // Checks for a set to be present
      if (temp[i][SET] != 0 && temp[i][TYPE] != 1) {
        if (temp[i][INTENSITY] != 0) {
          denom += 1;
          tempIntensity += temp[i][INTENSITY];
        }
      }
    }
    if (denom != 0) {
      tempIntensity = tempIntensity / denom;
    }
    Storage.workingIntensity[index] = tempIntensity;
  }
  
  public static void refreshSideData(int ID) {
    ttlDistance(ID);
    ttlTime(ID);
    ttlIntensity(ID);
    workingDistance(ID);
    workingTime(ID);
    workingIntensity(ID);
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
      Alert alert = new Alert(AlertType.ERROR);
      Text text = new Text(
          "Error: " + value + " is not an accepted input." + " Input must be a whole number.");
      text.setWrappingWidth(300);
      alert.getDialogPane().setContent(text);
      alert.setTitle("Input Error");
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
        Text text = new Text(
            "Error: " + value + " is not an accepted input." + " Input is less than " + lowerBound);
        text.setWrappingWidth(300);
        alert.getDialogPane().setContent(text);
        alert.showAndWait();
        return false;
      }
    } else {
      if (Integer.parseInt(value) > upperBound) {
        Text text = new Text(
            "Error: " + value + " is not an accepted input." + " Input is over " + upperBound);
        text.setWrappingWidth(300);
        alert.getDialogPane().setContent(text);
        alert.showAndWait();
        return false;
      }
      if (Integer.parseInt(value) < lowerBound) {
        Text text = new Text(
            "Error: " + value + " is not an accepted input." + " Input is less than " + lowerBound);
        text.setWrappingWidth(300);
        alert.getDialogPane().setContent(text);
        alert.showAndWait();
        return false;
      }
    }
    return true;
  }
    
  public static int[][] ListToArray(int ID, int rows, boolean[] check) {
    int[][] temp = new int[rows][9];
    Set tempSet;
    //Initailizes array for all values to be 0;
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < temp[i].length; j++) {
        temp[i][j] = 0;
      }
    }
    
    for (int i = 0; i < rows; i++) {
      tempSet = MainPage.getTable(ID).getData().get(i);
      int column = -1;
      for (int j = 0; j < check.length; j++) {
        if (check[j]) {
          column = j; 
          if (j == tempSet.DESCRIPTION) {
            temp[i][j] = -1;
            continue;
          } else if (column == tempSet.TYPE)  {
            if ((tempSet.get(tempSet.TYPE) == "Warm Up" 
                || tempSet.get(tempSet.TYPE) == "Loosen")) {
              temp[i][j] = 1;
              continue;
            }
          } else if (tempSet.get(j) != null && !tempSet.get(j).equals("" )) {
              temp[i][j] = Integer.parseInt(tempSet.get(j));
          }
        }
      }
    }
    return temp;
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
