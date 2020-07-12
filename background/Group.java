package background;

import java.util.ArrayList;

public class Group {
  private static ArrayList<String> typeOptions = new ArrayList<String>();
  private static final String[] defaultTypes = new String[] {"Warm Up", "Loosen", "Free", "Back",
      "Breast", "Fly", "IM", "Kick", "Pull", "Sprint"};

  // private static final int AMT_COLUMNS = 9;
  private ArrayList<Integer> set = new ArrayList<Integer>();
  private ArrayList<Integer> rounds = new ArrayList<Integer>();
  private ArrayList<Integer> reps = new ArrayList<Integer>();
  private ArrayList<Integer> distance = new ArrayList<Integer>();
  private ArrayList<String> description = new ArrayList<String>();
  private ArrayList<String> type = new ArrayList<String>();
  private ArrayList<Integer> minutes = new ArrayList<Integer>();
  private ArrayList<Integer> seconds = new ArrayList<Integer>();
  private ArrayList<Integer> intensity = new ArrayList<Integer>();
  private ArrayList<Integer> roundsCalc = new ArrayList<Integer>();
  private int size = 0;
  private int ID = -1;

  public enum Name {
    set, rounds, reps, distance, description, type, minutes, seconds, intensity
  }

  // TODO probably not needed
  public Group(Object[][] data) {
    for (int i = 0; i < data[0].length; i++) {
      set.add((Integer) data[0][i]);
    }
    for (int i = 0; i < data[1].length; i++) {
      rounds.add((Integer) data[1][i]);
    }
    for (int i = 0; i < data[2].length; i++) {
      reps.add((Integer) data[2][i]);
    }
    for (int i = 0; i < data[3].length; i++) {
      distance.add((Integer) data[3][i]);
    }
    for (int i = 0; i < data[4].length; i++) {
      description.add((String) data[4][i]);
    }
    for (int i = 0; i < data[5].length; i++) {
      type.add((String) data[5][i]);
    }
    for (int i = 0; i < data[6].length; i++) {
      minutes.add((Integer) data[6][i]);
    }
    for (int i = 0; i < data[7].length; i++) {
      seconds.add((Integer) data[7][i]);
    }
    for (int i = 0; i < data[8].length; i++) {
      intensity.add((Integer) data[8][i]);
    }
  }
  public Group() {
    for (String str : defaultTypes) {
      typeOptions.add(str);
    }
  }
  
  
  public Group(int ID) {
    this();
    this.ID = ID;
    size = 0;
    addRow(1,0,2,200,"Default Row", "Free", 0, 0, 50);
    addRow(2,0,2,0,"Default Row", "Back", 0, 0, 100);
    addRow(0, 0, 0, 0, "New Row", "", 0, 0, 0);
  }

  public void addRow(int set, int rounds, int reps, int distance, String description, String type,
      int minutes, int seconds, int intensity) {
    this.set.add(set);
    this.rounds.add(rounds);
    this.roundsCalc.add(1);
    this.reps.add(reps);
    this.distance.add(distance);
    this.description.add(description);
    this.type.add(type);
    this.minutes.add(minutes);
    this.seconds.add(seconds);
    this.intensity.add(intensity);
    size++;
  }


  @Override
  public String toString() {
    String str = "";
    for (int i = 0; i < size; i++) {
      str += this.set.get(i).toString() + ", ";
      str += this.rounds.get(i).toString() + ", ";
      str += this.reps.get(i).toString() + ", ";
      str += this.distance.get(i).toString() + ", ";
      str += this.description.get(i).toString() + ", ";
      str += this.type.get(i).toString() + ", ";
      str += this.minutes.get(i).toString() + ", ";
      str += this.seconds.get(i).toString() + ", ";
      str += this.intensity.get(i).toString() + "\n";
    }
    return str;
  }

  public void remRow() {
    if (size() != 0) {
      this.set.remove(size - 1);
      this.rounds.remove(size - 1);
      this.roundsCalc.remove(size - 1);
      this.reps.remove(size - 1);
      this.distance.remove(size - 1);
      this.description.remove(size - 1);
      this.type.remove(size - 1);
      this.minutes.remove(size - 1);
      this.seconds.remove(size - 1);
      this.intensity.remove(size - 1);
      size--;
    }
  }

  public void createRow() {
    addRow(0, 0, 0, 0, "New Row", "", 0, 0, 0);
  }

  public void createRows(int numRows) {
    for (int i = 0; i < numRows; i++) {
      addRow(0, 0, 0, 0, "", "", 0, 0, 0);
    }
  }
  
  public int typeSize() {
    return typeOptions.size();
  }
  
  public ArrayList<String> getTypeOptions() {
    return typeOptions;
  }
  
  public String getType(int index) {
    return type.get(index);
  }

  public int getID() {
    return ID;
  }

  public int size() {
    return size;
  }

  public void editCell(int row, Name column, Object element) throws ArrayIndexOutOfBoundsException {
    // If the called row is larger the length of the table, throw an exception
    if (row >= intensity.size()) {
      throw new ArrayIndexOutOfBoundsException();
    }
    switch (column) {
      case set:
        set.set(row, (Integer) element);
        updateRoundsCalc();
        break;
      case rounds:
        rounds.set(row, (Integer) element);
        roundsCalc.set(row, (Integer) element);
        updateRoundsCalc();
        break;
      case reps:
        reps.set(row, (Integer) element);
        break;
      case distance:
        distance.set(row, (Integer) element);
        break;
      case description:
        description.set(row, (String) element);
        break;
      case type:
        type.set(row, (String) element);
        break;
      case minutes:
        minutes.set(row, (Integer) element);
        break;
      case seconds:
        seconds.set(row, (Integer) element);
        break;
      case intensity:
        intensity.set(row, (Integer) element);
        break;
    }
  }

  public Object[] getRow(int row) {
    Object[] tempRow = new Object[9];
    for (int i = 0; i < 9; i++) {
      switch (i) {
        case 0:
          tempRow[i] = set.get(row);
          break;
        case 1:
          tempRow[i] = rounds.get(row);
          break;
        case 2:
          tempRow[i] = reps.get(row);
          break;
        case 3:
          tempRow[i] = distance.get(row);
          break;
        case 4:
          tempRow[i] = description.get(row);
          break;
        case 5:
          tempRow[i] = type.get(row);
          break;
        case 6:
          tempRow[i] = minutes.get(row);
          break;
        case 7:
          tempRow[i] = seconds.get(row);
          break;
        case 8:
          tempRow[i] = intensity.get(row);
          break;
      }
    }
    return tempRow;
  }
  
  public void setRow(int row, Object[] values) throws ArrayIndexOutOfBoundsException {
    if (row >= intensity.size()) {
      throw new ArrayIndexOutOfBoundsException();
    }
    for (int i = 0; i < 9; i++) {
      switch (i) {
        case 0:
          set.set(row, (Integer) values[i]);
          break;
        case 1:
          rounds.set(row, (Integer) values[i]);
          break;
        case 2:
          reps.set(row, (Integer) values[i]);
          break;
        case 3:
          distance.set(row, (Integer) values[i]);
          break;
        case 4:
          description.set(row, (String) values[i]);
          break;
        case 5:
          type.set(row, (String) values[i]);
          break;
        case 6:
          minutes.set(row, (Integer) values[i]);
          break;
        case 7:
          seconds.set(row, (Integer) values[i]);
          break;
        case 8:
          intensity.set(row, (Integer) values[i]);
          break;
      }
    }
  }

  public Object getCell(int row, Name column) throws ArrayIndexOutOfBoundsException {
    // If the called row is larger the length of the table, throw an exception
    if (row >= intensity.size()) {
      throw new ArrayIndexOutOfBoundsException();
    }
    switch (column) {
      case set:
        return set.get(row);
      case rounds:
        return rounds.get(row);
      case reps:
        return reps.get(row);
      case distance:
        return distance.get(row);
      case description:
        return description.get(row);
      case type:
        return type.get(row);
      case minutes:
        return minutes.get(row);
      case seconds:
        return seconds.get(row);
      case intensity:
        return intensity.get(row);
      default:
        return null;
    }
  }

  public static void addType(String type) {
    typeOptions.add(type);
  }

  public static void remType(String type) {
    typeOptions.remove(type);
  }

  private void updateRoundsCalc() {
    for (int i = 0; i < set.size(); i++) {
      for (int j = i + 1; j < set.size(); j++) {
        if (set.get(j) == set.get(i)) {
          roundsCalc.set(j, roundsCalc.get(i));
        }
      }
      if (roundsCalc.get(i) == 0) {
        roundsCalc.set(i, 1);
      }
    }
  }

  public int setDistance(int setNum) {
    int sum = 0;
    int tempSetNum = -1;
    for (int i = 0; i < set.size(); i++) {
      tempSetNum = set.get(i);
      if (setNum == tempSetNum) {
        sum += roundsCalc.get(i).intValue() * reps.get(i).intValue() * distance.get(i).intValue();
      }
    }
    return sum;
  }
  
  public int typeDistance(String type) {
    int sum = 0;
    String tempType = "";
    if (type.contentEquals("")) {
      return sum;
    }
    for (int i = 0; i < size(); i++) {
      tempType = this.type.get(i);
      if (tempType.contentEquals(type)) {
        sum += roundsCalc.get(i).intValue() * reps.get(i).intValue() * distance.get(i).intValue();
      }
    }
    return sum;
  }
  
  public int setIntensity(int setNum) {
    int sum = 0;
    int amount = -1;
    int tempSet = -1;
    for (int i = 0; i < size(); i++) {
      tempSet = set.get(i);
      if (tempSet == setNum) {
        sum += intensity.get(i);
        amount++;
      }
    }
    if (amount > 0) {
      return sum/amount;
    } else {
      return sum;
    }
  }
    
  
  public int totalDistance() {
    int sum = 0;
    for (int i = 0; i < intensity.size(); i++) {
      if (set.get(i) != 0) {
        sum += roundsCalc.get(i).intValue() * reps.get(i).intValue() * distance.get(i).intValue();
      }
    }
    return sum;
  }

  public int totalTime() {
    int[] time = new int[] {0, 0};
    for (int i = 0; i < intensity.size(); i++) {
      if (set.get(i) != 0) {
        time[0] +=
            roundsCalc.get(i).intValue() * reps.get(i).intValue() * minutes.get(i).intValue();
        time[1] +=
            roundsCalc.get(i).intValue() * reps.get(i).intValue() * seconds.get(i).intValue();
      }
    }
    time[0] += time[1] / 60;
    time[1] = time[1] % 60;
    return time[0];
  }

  public int CalcAvgIntensity() {
    int avg = 0;
    int sum = 0;
    for (int i = 0; i < intensity.size(); i++) {
      avg += rounds.get(i).intValue() * reps.get(i).intValue() * intensity.get(i).intValue();
      sum += rounds.get(i).intValue() * reps.get(i).intValue() * distance.get(i).intValue();
    }
    return avg / sum;
  }

  public int avgIntensity() {
    int sum = 0;
    int amount = 0;
    for (int i = 0; i < intensity.size(); i++) {
      if (set.get(i) != 0 && intensity.get(i) != 0) {
        sum += intensity.get(i);
        amount++;
      }
    }
    if (amount != 0) {
      return sum / amount;
    } else {
      return sum;
    }
  }

  public int workingDistance() {
    int sum = 0;
    for (int i = 0; i < intensity.size(); i++) {
      if (set.get(i) != 0 && type.get(i) != defaultTypes[0] && type.get(i) != defaultTypes[1])
        sum += roundsCalc.get(i).intValue() * reps.get(i).intValue() * distance.get(i).intValue();
    }
    return sum;
  }

  public int workingTime() {
    int[] time = new int[] {0, 0};
    for (int i = 0; i < intensity.size(); i++) {
      if (set.get(i) != 0 && type.get(i) != defaultTypes[0] && type.get(i) != defaultTypes[1]) {
        time[0] +=
            roundsCalc.get(i).intValue() * reps.get(i).intValue() * minutes.get(i).intValue();
        time[1] +=
            roundsCalc.get(i).intValue() * reps.get(i).intValue() * seconds.get(i).intValue();
      }
    }
    time[0] += time[1] / 60;
    time[1] = time[1] % 60;

    return time[0];
  }

  public int CalcWorkingIntensity() {
    int avg = 0;
    int sum = 0;
    for (int i = 0; i < intensity.size(); i++) {
      if (set.get(i) != 0 && type.get(i) != defaultTypes[0] && type.get(i) != defaultTypes[1]) {
        avg += rounds.get(i).intValue() * reps.get(i).intValue() * intensity.get(i).intValue();
        sum += rounds.get(i).intValue() * reps.get(i).intValue() * distance.get(i).intValue();
      }

    }
    return avg / sum;
  }

  public int workingIntensity() {
    int sum = 0;
    int amount = 0;
    for (int i = 0; i < intensity.size(); i++) {
      if (set.get(i) != 0 && intensity.get(i) != 0 && type.get(i) != "Warm Up"
          && type.get(i) != "Loosen") {
        sum += intensity.get(i).intValue();
        amount++;
      }
    }
    if (amount != 0) {
      return sum / amount;
    } else {
      return sum;
    }

  }

}
