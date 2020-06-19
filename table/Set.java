package table;


// import javafx.beans.property.SimpleIntegerProperty; not sure if switch back
import javafx.beans.property.SimpleStringProperty;
import java.util.ArrayList;

public class Set {
  public final int AMT_COLUMNS = 9;
  public final int SET = 0;
  public final int ROUNDS = 1;
  public final int REPS = 2;
  public final int DISTANCE = 3;
  public final int DESCRIPTION = 4;
  public final int TYPE = 5;
  public final int MINUTES = 6;
  public final int SECONDS = 7;
  public final int INTENSITY = 8;
  /*
   * Array list of the columns index: 0 - Set 1 - Rounds 2 - Reps 3 - Distance 4 - Description 5 -
   * Type 6 - Minutes 7 - Seconds 8 - Intensity
   */
  private ArrayList<SimpleStringProperty> columns;

  public Set() {

  }

  public Set(String col1, String col2, String col3, String col4, String col5, String col6,
      String col7, String col8, String col9) {
    String[] columnNames = new String[] {col1, col2, col3, col4, col5, col6, col7, col8, col9};
    columns = new ArrayList<SimpleStringProperty>();
    for (int i = 0; i < AMT_COLUMNS; i++) {
      columns.add(new SimpleStringProperty(columnNames[i]));
    }
  }


  public String printSet() {
    String temp = "";
    temp += "Set: " + columns.get(SET).get() + "\n";
    temp += "Round: " + columns.get(ROUNDS).get() + "\n";
    temp += "Rep: " + columns.get(REPS).get() + "\n";
    temp += "Distance: " + columns.get(DISTANCE).get() + "\n";
    temp += "Description: " + columns.get(DESCRIPTION).get() + "\n";
    temp += "Type: " + columns.get(TYPE).get() + "\n";
    temp += "Min: " + columns.get(MINUTES).get() + "\n";
    temp += "Sec: " + columns.get(SECONDS).get() + "\n";
    temp += "Intensity: " + columns.get(INTENSITY).get() + "\n";
    System.out.println(temp);
    return temp;
  }

  public String get(int index) {
    for (int i = 0; i < AMT_COLUMNS; i++) {
      if (index == i) {
        return columns.get(i).get();
      }
    }
    return "";
  }
  
  public void set(int index, String value) {
    for (int i = 0; i < AMT_COLUMNS; i++) {
      if (index == i) {
        columns.get(i).set(value);
      }
    }
  }

  // Editing the Set Column
  public String getSetCol() {
    return columns.get(SET).get();
  }

  public void setSetCol(String col1) {
    columns.get(SET).set(col1);
  }

  // Editing the Rounds Column
  public String getRoundsCol() {
    return columns.get(ROUNDS).get();
  }

  public void setRoundsCol(String col2) {
    columns.get(ROUNDS).set(col2);
  }

  // Editing the Rep Column
  public String getRepsCol() {
    return columns.get(REPS).get();
  }

  public void setRepsCol(String col3) {
    columns.get(REPS).set(col3);
  }

  // Editing the Distance Column
  public String getDistanceCol() {
    return columns.get(DISTANCE).get();
  }

  public void setDistanceCol(String col4) {
    columns.get(DISTANCE).set(col4);
  }

  // Editing the Description Column
  public String getDescriptionCol() {
    return columns.get(DESCRIPTION).get();
  }

  public void setDescriptionCol(String col5) {
    columns.get(DESCRIPTION).set(col5);
  }

  // Editing the Integer Column
  public SimpleStringProperty typeProperty() {
    return this.columns.get(TYPE);
  }

  public String getTypeCol() {
    return this.typeProperty().get();
  }

  public void setTypeCol(String type) {
    this.typeProperty().set(type);
  }

  // Editing the Minute Column
  public String getMinCol() {
    return columns.get(MINUTES).get();
  }

  public void setMinCol(String col7) {
    columns.get(MINUTES).set(col7);
  }

  // Editing the Seconds Column
  public String getSecCol() {
    return columns.get(SECONDS).get();
  }

  public void setSecCol(String col8) {
    columns.get(SECONDS).set(col8);
  }

  // Editing the Intensity Column
  public String getIntensityCol() {
    return columns.get(INTENSITY).get();
  }

  public void setIntensityCol(String col9) {
    columns.get(INTENSITY).set(col9);
  }
}
