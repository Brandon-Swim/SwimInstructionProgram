package background;

import java.util.ArrayList;

public class SwimWorkout {
  private static int nextID = -1;
  private ArrayList<Group> workout = new ArrayList<Group>();
  private int month;
  private int day;
  private int year;
  private String name;
  private String description;
  private boolean gameday;
  
  public SwimWorkout() {
    month = 0;
    day = 0;
    year = 2020;
    name = "";
    description = "";
    gameday = false;
  }
  //TODO dont think this is needed
  public SwimWorkout(int month, int day, int year, boolean gameday) {
    this();
    this.day = day;
    this.month = month;
    this.year = year; 
    this.gameday = gameday;
    addGroup(new Group(getNextID()));
  }
  //TODO don't know if this is needed except for files
  public SwimWorkout(int month, int day, int year, boolean gameday, Object[][][] data) {
    this.day = day;
    this.month = month;
    this.year = year; 
    this.gameday = gameday;
    for (int i = 0; i < data.length; i++) {
      addGroup(new Group(data[i]));
    }
  }
  
  public void addGroup(Group g) {
    workout.add(g);
  }
  
  public void remGroup(int index) {
    workout.remove(index);
    nextID--;
  }
  
  public static int getNextID() {
    nextID++;
    return nextID;
  }
  
  public void print(int index) {
    System.out.println("Group " + (index + 1) + ": ");
    System.out.println(workout.get(index).toString());
    System.out.println("_________________________");
  }
  
  
  public String dataDump() {
    String toReturn = "";
    toReturn += month + "/" + day + "/" + year + "\n";
    toReturn += name + "\n";
    toReturn += description + "\n";
    toReturn += gameday + "\n";
    for (int i = 0; i < workout.size(); i++) {
      toReturn += workout.get(i).dataDump();
    }
    return toReturn;
  }
  
  @Override
  public String toString() {
    String toReturn = "";
    toReturn += "Date: " + month + "/" + day + "/" + year + "\n";
    toReturn += "Name: " + name + "\n";
    toReturn += "Description: " + description + "\n";
    for (int i = 0; i < workout.size(); i++) {
      toReturn += "Group " + (i +1) + "\n";
      toReturn += workout.get(i).toString();
    }
    return toReturn;
  }
  
  
  public void clear() {
    for (int i = workout.size() - 1; i > 0; i--) {
      remGroup(i);
    }
  }
  
  public int size() {
    return workout.size();
  }
  
  public Group getGroup(int index) {
    return workout.get(index);
  }
  
  public int getMonth() {
    return month;
  }

  public void setMonth(int month) {
    this.month = month;
  }

  public int getDay() {
    return day;
  }

  public void setDay(int day) {
    this.day = day;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean isGameday() {
    return gameday;
  }

  public void setGameday(boolean gameday) {
    this.gameday = gameday;
  }
    
}
