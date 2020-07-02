package general;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.text.Text;
import table.Set;

public class Storage {
    //Amount of groups allowed
    public static final int AMT_GROUPS = 5;
    // Current Group
    public static int currentGroup = 1;
    
    //MAIN PAGE VARIABLES
    //Side Data Labels
    public static int[] ttlDistanceAmts = new int[AMT_GROUPS];
    public static double[] ttlTimeAmts = new double[AMT_GROUPS];
    public static int[] ttlIntensity = new int[AMT_GROUPS];
    public static int[] workingDistanceAmts = new int[AMT_GROUPS];
    public static double[] workningTimeAmts = new double[AMT_GROUPS];
    public static int[] workingIntensity = new int[AMT_GROUPS];
    public static String[] workoutType = new String[] {"None","None","None","None","None"};
    /* 
     * Displays total distance
     * Displays total time
     * Displays Intensity
     * Working Distance (not in warm up or easy)
     * Working Time (not in warm up or easy)
     * Working Intensity (not in warm up or easy)
     * Displays total amount of practice types for the season
     * Displays total season distance
     * Displays practices left in the week
     * Displays practices left to next competition
     * Displays practices since last game day
     * Displays practices until taper
     * Displays Individual Set distances
     * Displays amount of working yards
     */
    public static String[] sideLabel = new String[] {
        "TTL Distance: " + ttlDistanceAmts[0] + " yds", 
        "TTL Time: 0 mins 0 sec",
        "Avg Intensity: " + ttlIntensity[0] + "%", 
        "Working Distance: " + workingDistanceAmts[0] + " yds",
        "Working Time: 0 mins 0 sec",
        "Working Intensity: " + workingIntensity[0] + "%",
        "Workout Type: None", 
        "T Season Distance",
        "T Practices left in the week", 
        "T Practices til Comp", 
        "T Game Day Counter",
        "T Practices until Taper", 
        "Set distance"};

 
    
    public static String workoutName = "Workout Name";
    public static Text nameStyle = new Text();
    public static String monthName = "MM";
    public static Text monthStyle = new Text();
    public static String dayName = "DD";
    public static Text dayStyle = new Text();
    public static String descriptionText = "Description";
    //TABLE VARIABLES
    //Defualt Set
    //public static final Set DEFAULT_SET = new Set("1", "", "", "", "", "", "", "", "");
    //Data stored in the first table
    public static ObservableList<Set> datagroup1 = FXCollections.observableArrayList(
        new Set("1", "2", "3", "", "", "Free", "", "", ""),
        new Set("2", "1", "2", "", "", "Back", "", "", ""),
        new Set("", "", "", "", "", "", "", "", ""),
        new Set("", "", "", "", "", "", "", "", ""),
        new Set("", "", "", "", "", "", "", "", ""));   // start with 5 blank rows
   
    public static ObservableList<Set> datagroup2 = FXCollections.observableArrayList(
        new Set("2", "2", "2", "", "", "Back", "", "", ""),
        new Set("", "", "", "", "", "", "", "", ""),
        new Set("", "", "", "", "", "", "", "", ""),
        new Set("", "", "", "", "", "", "", "", ""),
        new Set("", "", "", "", "", "", "", "", ""));   // start with 5 blank rows
    
    public static ObservableList<Set> datagroup3 = FXCollections.observableArrayList(
        new Set("3", "", "", "", "", "", "", "", ""),
        new Set("1", "", "", "", "", "", "", "", ""),
        new Set("1", "", "", "", "", "", "", "", ""),
        new Set("1", "", "", "", "", "", "", "", ""),
        new Set("1", "", "", "", "", "", "", "", ""));   // start with 5 blank rows
    
    public static ObservableList<Set> datagroup4 = FXCollections.observableArrayList(
        new Set("4", "", "", "", "", "", "", "", ""),
        new Set("1", "", "", "", "", "", "", "", ""),
        new Set("1", "", "", "", "", "", "", "", ""),
        new Set("1", "", "", "", "", "", "", "", ""),
        new Set("1", "", "", "", "", "", "", "", ""));   // start with 5 blank rows
    
    public static ObservableList<Set> datagroup5 = FXCollections.observableArrayList(
        new Set("5", "", "", "", "", "", "", "", ""),
        new Set("1", "", "", "", "", "", "", "", ""),
        new Set("1", "", "", "", "", "", "", "", ""),
        new Set("1", "", "", "", "", "", "", "", ""),
        new Set("1", "", "", "", "", "", "", "", ""));   // start with 5 blank rows
    
    public static ObservableList<Set> getSet(int ID) {
      switch (ID) {
        case 1: 
          return datagroup1;
        case 2:
          return datagroup2;
        case 3:
          return datagroup3;
        case 4: 
          return datagroup4;
        case 5:
          return datagroup5;
      }
      return null;
    }
    
    // Labels for each group
    public static final String[] TABLE_HEADERS = new String[]{"Group 1",
        "Group 2","Group 3","Group 4","Group 5"};
    public static ObservableList<String> typeSelector = 
        FXCollections.observableArrayList(
        "Warm Up",
        "Free",
        "Back",
        "Breast",
        "Fly",
        "IM",
        "Kick",
        "Pull",
        "Sprint",
        "Loosen" 
        );
    
    
    //SETTINGS VARIABLES
    public static boolean resetData = true;
}
