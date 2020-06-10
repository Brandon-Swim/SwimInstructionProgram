import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Storage {
    //Amount of groups allowed
    public static final int AMT_GROUPS = 5;
    // Current Group
    public static int currentGroup = 1;
    
    //MAIN PAGE VARIABLES
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
    public static String[] sideLabel = new String[] {"Total Distance: 0 yds", 
        "Total Time: 0 min", "Avg Intensity: 0%", "Working Distance: 0 yds", 
        "Working Time: 0 min","Working Intensity: 0%","T Type Counter", 
        "T Season Distance","T Practices left in the week", "T Practices til Comp", 
        "T Game Day Counter","T Practices until Taper", "Set distance"};
    public static String workoutName = "Workout Name";
    public static Text nameStyle = new Text();
    public static String monthName = "MM";
    public static Text monthStyle = new Text();
    public static String dayName = "DD";
    public static Text dayStyle = new Text();
    public static String descriptionText = "Description";
    //TABLE VARIABLES
    //Data stored in the first table
    public static ObservableList<Set> datagroup1 = FXCollections.observableArrayList(
        new Set("1", "", "", "", "", "", "", "", ""),
        new Set("1", "", "", "", "", "", "", "", ""),
        new Set("1", "", "", "", "", "", "", "", ""),
        new Set("1", "", "", "", "", "", "", "", ""),
        new Set("1", "", "", "", "", "", "", "", ""));   // start with 5 blank rows
    
    public static ObservableList<Set> datagroup2 = FXCollections.observableArrayList(
        new Set("1", "", "", "", "", "", "", "", ""),
        new Set("1", "", "", "", "", "", "", "", ""),
        new Set("1", "", "", "", "", "", "", "", ""),
        new Set("1", "", "", "", "", "", "", "", ""),
        new Set("1", "", "", "", "", "", "", "", ""));   // start with 5 blank rows
    
    public static ObservableList<Set> datagroup3 = FXCollections.observableArrayList(
        new Set("1", "", "", "", "", "", "", "", ""),
        new Set("1", "", "", "", "", "", "", "", ""),
        new Set("1", "", "", "", "", "", "", "", ""),
        new Set("1", "", "", "", "", "", "", "", ""),
        new Set("1", "", "", "", "", "", "", "", ""));   // start with 5 blank rows
    
    public static ObservableList<Set> datagroup4 = FXCollections.observableArrayList(
        new Set("1", "", "", "", "", "", "", "", ""),
        new Set("1", "", "", "", "", "", "", "", ""),
        new Set("1", "", "", "", "", "", "", "", ""),
        new Set("1", "", "", "", "", "", "", "", ""),
        new Set("1", "", "", "", "", "", "", "", ""));   // start with 5 blank rows
    
    public static ObservableList<Set> datagroup5 = FXCollections.observableArrayList(
        new Set("1", "", "", "", "", "", "", "", ""),
        new Set("1", "", "", "", "", "", "", "", ""),
        new Set("1", "", "", "", "", "", "", "", ""),
        new Set("1", "", "", "", "", "", "", "", ""),
        new Set("1", "", "", "", "", "", "", "", ""));   // start with 5 blank rows
    
    // Labels for each group
    public static final String[] TABLE_HEADERS = new String[]{"Group 1",
        "Group 2","Group 3","Group 4","Group 5"};
    
    //SETTINGS VARIABLES
    public static boolean resetData = true;
}
