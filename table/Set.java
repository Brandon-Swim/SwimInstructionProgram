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
  private SimpleStringProperty[] columns;

  public Set() {
    columns = new SimpleStringProperty[AMT_COLUMNS];
    for (int i = 0; i < AMT_COLUMNS; i++) {
      columns[i] = new SimpleStringProperty();
    }
  }

  public Set(String col1, String col2, String col3, String col4, String col5, String col6,
      String col7, String col8, String col9) {
    this();
    String[] columnNames = new String[] {col1, col2, col3, col4, col5, col6, col7, col8, col9};
    for (int i = 0; i < AMT_COLUMNS; i++) {
      columns[i].set(columnNames[i]);
    }
  }


  public String printSet() {
    String temp = "";
    temp += "Set: " + columns[SET].get() + "\n";
    temp += "Round: " + columns[ROUNDS].get() + "\n";
    temp += "Rep: " + columns[REPS].get() + "\n";
    temp += "Distance: " + columns[DISTANCE].get() + "\n";
    temp += "Description: " + columns[DESCRIPTION].get() + "\n";
    temp += "Type: " + columns[TYPE].get() + "\n";
    temp += "Min: " + columns[MINUTES].get() + "\n";
    temp += "Sec: " + columns[SECONDS].get() + "\n";
    temp += "Intensity: " + columns[INTENSITY].get() + "\n";
    System.out.println(temp);
    return temp;
  }
  
  @Override
  public String toString() {
    String output = new String();
    String temp = new String();
    for (int i = 0; i < columns.length; i++) {
      if (i != DESCRIPTION) {
        if (i != columns.length - 1) {
          temp += columns[i].get() + " | ";
        } else {
          temp += columns[i].get() + "\n";
        }
      } else {
        temp += cutOff(columns[i].get());
      }
    }
    System.out.println(temp);
    output += isEmptyRow(temp);
    return output;
  }
  
  private String isEmptyRow(String str) {
    String row = str;
    boolean check = false;
    char[] chars = str.toCharArray();
    for (int i = 0; i < chars.length; i++) {
      if(Character.isDigit(chars[i])){
        check = true;
        break;
      }
    }
    if (check) {
      return row;
    } else {
      return "";
    }
  }
  
  public int size() {
    return AMT_COLUMNS;
  }
  
  private String cutOff(String str) {
    String temp = str;
    char[] chars = temp.toCharArray();
    temp = "";
    if (chars.length > 10) {
      for (int i = 0; i < 10; i++) {
        temp += chars[i];
      }
      temp += "... |";
    }
    return temp;
  }

  public String get(int index) {
    for (int i = 0; i < AMT_COLUMNS; i++) {
      if (index == i) {
        return columns[i].get();
      }
    }
    return "";
  }
  
  public void set(int index, String value) {
    for (int i = 0; i < AMT_COLUMNS; i++) {
      if (index == i) {
        columns[i].set(value);
      }
    }
  }

  // Editing the Set Column
  public String getSetCol() {
    return columns[SET].get();
  }

  public void setSetCol(String col1) {
    columns[SET].set(col1);
  }

  // Editing the Rounds Column
  public String getRoundsCol() {
    return columns[ROUNDS].get();
  }

  public void setRoundsCol(String col2) {
    columns[ROUNDS].set(col2);
  }

  // Editing the Rep Column
  public String getRepsCol() {
    return columns[REPS].get();
  }

  public void setRepsCol(String col3) {
    columns[REPS].set(col3);
  }

  // Editing the Distance Column
  public String getDistanceCol() {
    return columns[DISTANCE].get();
  }

  public void setDistanceCol(String col4) {
    columns[DISTANCE].set(col4);
  }

  // Editing the Description Column
  public String getDescriptionCol() {
    return columns[DESCRIPTION].get();
  }

  public void setDescriptionCol(String col5) {
    columns[DESCRIPTION].set(col5);
  }

  // Editing the Integer Column
  public SimpleStringProperty typeProperty() {
    return this.columns[TYPE];
  }

  public String getTypeCol() {
    return this.typeProperty().get();
  }

  public void setTypeCol(String type) {
    this.typeProperty().set(type);
  }

  // Editing the Minute Column
  public String getMinCol() {
    return columns[MINUTES].get();
  }

  public void setMinCol(String col7) {
    columns[MINUTES].set(col7);
  }

  // Editing the Seconds Column
  public String getSecCol() {
    return columns[SECONDS].get();
  }

  public void setSecCol(String col8) {
    columns[SECONDS].set(col8);
  }

  // Editing the Intensity Column
  public String getIntensityCol() {
    return columns[INTENSITY].get();
  }

  public void setIntensityCol(String col9) {
    columns[INTENSITY].set(col9);
  }
}
