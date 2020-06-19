package table;

import java.util.Arrays;
import general.Storage;
import graphs.GraphData;
import graphs.Graphs;
import mainpage.MainPage;

public class GraphEvents extends TableEvents{
  private int ID;
  private int index;
  private final int AMT_COLUMNS = 9;
  
  public GraphEvents() {
    ID = -1;
    index = -1;
  }
  
  public GraphEvents(int ID) {
    this.ID = ID;
    index = ID - 1;
  }
  /**
   * Distance for each type
   */
  public void getDistanceGraphEvent() {
    GraphData graphData = MainPage.getGraphs().getData("distance", index);
    int dataSize = MainPage.getTable(ID).getData().size();
    // Set, Rounds, Reps, Distance, Type
    boolean[] ttlDistance =
        new boolean[] {true, true, true, true, false, true, false, false, false};                                                                     // distance
    int[][] temp = new int[dataSize][AMT_COLUMNS];
    // Parrellel array to type selector names
    int[] tempValue = new int[Storage.typeSelector.size()];
    try {
      temp = ListToArray(ID, dataSize, ttlDistance);
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Incorrect amount of variables selected");
    }
    //Modifies temp array
    for (int i = 0; i < temp.length; i++) {
      Set tempSet = MainPage.getTable(ID).getData().get(i);
      if (tempSet.getTypeCol() != null && !tempSet.getTypeCol().contentEquals("")) {
        temp[i][TYPE] = getTypeIndex(tempSet);
      }
      if (temp[i][SET] != 0 && temp[i][ROUNDS] != 0) {
        for (int j = i + 1; j < temp.length; j++) {
          if (temp[j][SET] == temp[i][SET]) {
            temp[j][ROUNDS] = temp[i][ROUNDS];
          }
        }
      }
    }
    // Solves for distance
    for (int i = 0; i < dataSize; i++) {
      Set tempSet = MainPage.getTable(ID).getData().get(i);
      // Checks for a set Number to be present
      if (temp[i][SET] != 0) {
        // Checks for a round Number
        if (temp[i][ROUNDS] != 0) {
          tempValue[temp[i][TYPE]] += temp[i][ROUNDS] * temp[i][REPS] * temp[i][DISTANCE];
        } else if (temp[i][ROUNDS] == 0) {
          tempValue[temp[i][TYPE]] += temp[i][REPS] * temp[i][DISTANCE];
        }
      }
      // Checks for a calculated distance and for a type present
      if (tempValue[temp[i][TYPE]] != 0 && tempSet.getTypeCol() != null 
          && !tempSet.getTypeCol().contentEquals("")) {
        graphData.addData(tempSet.getTypeCol(), tempValue[temp[i][TYPE]]);
      }
    }
    boolean present = false;
    // Checks for if something needs to be removed
    for (int i = 0; i < graphData.getData().size(); i++) {
      for (int j = 0; j < Storage.getSet(ID).size(); j++) {
        present = false;
        if (Storage.getSet(ID).get(j).getTypeCol() != null && 
            !Storage.getSet(ID).get(j).getTypeCol().contentEquals("") &&
            graphData.getData().get(i).getName() 
            == Storage.getSet(ID).get(j).getTypeCol()) {
          present = true;
          break;
        } 
      }
      if (!present) {
        graphData.getData().remove(i);
      }
    }
  }
  
  /**
   * Intensity per set
   */
  public void getIntensityGraphEvent() {
    int index = ID - 1;
    GraphData graphData = MainPage.getGraphs().getData("intensity", index);
    int dataSize = MainPage.getTable(ID).getData().size();
    int amtDistanceColumns = 3;
    // ony for set, intensiy, and type
    boolean[] ttlIntensity = new boolean[] {true, false, false, false, false, false, false, false, true};
    int[][] temp = new int[dataSize][amtDistanceColumns];
    int[] tempIntensity = new int[20];
    int[] denom = new int[20];
    try {
      temp = ListToArray(ID, dataSize, ttlIntensity);
      //System.out.println(Arrays.deepToString(temp));
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Incorrect amount of variables selected");
    }
    // Calculated Intensity
    for (int i = 0; i < dataSize; i++) {
      // Checks for a set to be present
      if (temp[i][SET] != 0) {
        if (temp[i][INTENSITY] != 0) {
          denom[temp[i][SET]] += 1;
          tempIntensity[temp[i][SET]] += temp[i][INTENSITY];
        }
      }
      if (denom[temp[i][SET]] != 0) {
        tempIntensity[temp[i][SET]]  = tempIntensity[temp[i][SET]] / denom[temp[i][SET]] ;
      }
    }
    
    for (int i = 0; i < dataSize; i++) {
      Set tempSet = MainPage.getTable(ID).getData().get(i);
      // Checks for a calculated distance and for a type present
      if (tempIntensity[temp[i][SET]] != 0 && tempSet.getSetCol() != null 
          && !tempSet.getSetCol().contentEquals("")) {
        graphData.addData(tempSet.getSetCol(), tempIntensity[temp[i][SET]]);
      }
    }
    boolean present = false;
    // Checks for if something needs to be removed
    for (int i = 0; i < graphData.getData().size(); i++) {
      for (int j = 0; j < Storage.getSet(ID).size(); j++) {
        present = false;
        if (Storage.getSet(ID).get(j).getSetCol() != null && 
            !Storage.getSet(ID).get(j).getSetCol().contentEquals("") &&
            graphData.getData().get(i).getName() 
            == Storage.getSet(ID).get(j).getSetCol()) {
          present = true;
          break;
        } 
      }
      if (!present) {
        graphData.getData().remove(i);
      }
    }
    graphData.printData();
  }
  
  private int getTypeIndex(Set set) {
    for (int i = 0; i < Storage.typeSelector.size(); i++) {
      if (set.getTypeCol() == Storage.typeSelector.get(i) ) {
        return i;
      }
    }
    return -1;
  }
  
}