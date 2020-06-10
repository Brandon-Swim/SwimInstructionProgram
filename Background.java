import java.util.Optional;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;

public class Background {
 
    
  /*
   * Control Panel Listeners
   * For every action
   *  -adds radiobutton for graph
   *  -adds table 
   *  -changes side data for multiple groups
   *  -adds row options for the table
   *  -TODO problably some file output stuff   
   */
    static EventHandler<ActionEvent> addControls = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent e) {
            if (Storage.currentGroup < 5) {
                Storage.currentGroup += 1;
                MainPage.getControl().getGraphLayout().getChildren()
                .add(MainPage.getControl().getGraph(Storage.currentGroup - 1));
                MainPage.getControl().getRowLayout().getChildren()
                .add(MainPage.getControl().getTableController(Storage.currentGroup -1));
                MainPage.getTable(Storage.currentGroup).addTable(MainPage.getTableLayout());
            }
            switch (Storage.currentGroup) {
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
            }
        }
    };

    /*
     * For every action
     *  -removes graph selector
     *  -removes table
     *  -changes side data layout
     *  -adds row options to the table
     *  -TODO probably some file output stuff
     */
    static EventHandler<ActionEvent> remControls = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent e) {
            if (Storage.currentGroup > 1) {
                Storage.currentGroup -= 1;
                MainPage.getControl().getGraphLayout().getChildren()
                .remove(MainPage.getControl().getGraph(Storage.currentGroup));
                MainPage.getControl().getRowLayout().getChildren()
                .remove(MainPage.getControl().getTableController(Storage.currentGroup));
                MainPage.getTable(Storage.currentGroup + 1)
                .remTable(MainPage.getTableLayout());

            }
            switch (Storage.currentGroup) {
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
            }
        }
    };
    /*
     * TODO Set up gameDay layout
     */
    static EventHandler<ActionEvent> gameDay = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent e) {
            Alert confirmation = new Alert(AlertType.CONFIRMATION);
            confirmation.setTitle("Game Day");
            confirmation.setContentText("Are you sure you want to switch to "
                + "game day layout?");
            Optional<ButtonType> result = confirmation.showAndWait();
            if (result.get() == ButtonType.OK){
                System.out.println("Ok");   //TODO
            } else {
                System.out.println("Cancled");  //TODO
            }
        }
    };
    /*
     * For every action
     * -Sets side data back to default 
     * -Clears table
     * -Clears header name, month, and date
     * -Clears description
     * -sets table control back to 1
     * -sets graph control back to 1
     */

    static EventHandler<ActionEvent> reset = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent e) {
            Storage.currentGroup = 1;
            MainPage.getControl().getGraphLayout().getChildren().clear();
            MainPage.getControl().getGraphLayout().getChildren().add(
                MainPage.getControl().getGraph(Storage.currentGroup - 1));
            MainPage.getControl().getRowLayout().getChildren().clear();
            MainPage.getControl().getRowLayout().getChildren().add(
                MainPage.getControl().getTableController(Storage.currentGroup - 1));
            MainPage.getTableLayout().getChildren().clear();
            MainPage.getTable(Storage.currentGroup)
            .addTable(MainPage.getTableLayout());
            TableDataToDefault(Storage.resetData);
        }
    };
    /*
     * on mouse click, clears the textFeild and sets text to black 
     * on edit, stores the string in the workoutname for file output
     */
    static EventHandler<MouseEvent> clearWorkoutText= new EventHandler<MouseEvent>() {
        public void handle(MouseEvent t) {
            Storage.workoutName = "";
            MainPage.getControl().getWorkoutName().setText(Storage.workoutName);
        }
    };
    static ChangeListener<String> storeWorkoutText = new ChangeListener<String>() {
        public void changed(ObservableValue<? extends String> observable,
                String oldValue, String newValue) {
            Storage.workoutName = newValue;
        }
    };
    static ChangeListener<Boolean> defaultWorkoutText = new ChangeListener<Boolean>() {
        public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
        {
            if (newPropertyValue) {
                Storage.workoutName = "";
                MainPage.getControl().getWorkoutName().setText(Storage.workoutName);
            } else {
                if (Storage.workoutName == "") {
                    Storage.workoutName = "Workout Name";
                    MainPage.getControl().getWorkoutName().setText(Storage.workoutName);
                }
            }
        }
    };
    /*
     * on mouse click, clears the textFeild and sets text to black 
     * on edit, stores the string in the workoutname for file output
     */
    static EventHandler<MouseEvent> clearMonthText= new EventHandler<MouseEvent>() {
        public void handle(MouseEvent t) {
            Storage.monthName = "";
            MainPage.getControl().getMonth().setText(Storage.monthName);
        }
    };
    static ChangeListener<String> storeMonth = new ChangeListener<String>() {
        public void changed(ObservableValue<? extends String> observable,
                String oldValue, String newValue) {
            Storage.monthName = newValue;
        }
    };
    static ChangeListener<Boolean> defaultMonthText = new ChangeListener<Boolean>() {
        public void changed(ObservableValue<? extends Boolean> arg0, 
            Boolean oldPropertyValue, Boolean newPropertyValue)
        {
            if (newPropertyValue) {
                Storage.monthName = "";
                MainPage.getControl().getMonth().setText(Storage.monthName);
            } else {
                if (Storage.monthName == "") {
                    Storage.monthName = "MM";
                    MainPage.getControl().getMonth().setText(Storage.monthName);
                }
            }
        }
    };
    /*
     * on mouse click, clears the textFeild and sets text to black 
     * on edit, stores the string in the workoutname for file output
     * if the text was not edited, it is set back to the default text
     */
    static EventHandler<MouseEvent> clearDayText= new EventHandler<MouseEvent>() {
        public void handle(MouseEvent t) {
            Storage.dayName = "";
            MainPage.getControl().getDay().setText(Storage.dayName);
        }
    };
    static ChangeListener<String> storeDay = new ChangeListener<String>() {
        public void changed(ObservableValue<? extends String> observable,
                String oldValue, String newValue) {
            Storage.dayName = newValue;
        }
    };
    static ChangeListener<Boolean> defaultDayText = new ChangeListener<Boolean>() {
        public void changed(ObservableValue<? extends Boolean> arg0, 
            Boolean oldPropertyValue, Boolean newPropertyValue)
        {
            if (newPropertyValue) {
                Storage.dayName = "";
                MainPage.getControl().getDay().setText(Storage.dayName);
            } else {
                if (Storage.dayName == "") {
                    Storage.dayName = "DD";
                    MainPage.getControl().getDay().setText(Storage.dayName);
                }
            }
        }
    };
    /*
     * on mouse click, clears the textFeild and sets text to black 
     * on edit, stores the string in the workoutname for file output
     * if the text was not edited, it is set back to the default text
     */
    static EventHandler<MouseEvent> clearDescription= new EventHandler<MouseEvent>() {
        public void handle(MouseEvent t) {
            Storage.descriptionText = "";
            MainPage.getControl().getDescription().setText(Storage.descriptionText);
        }
    };
    static ChangeListener<String> storeDescription = new ChangeListener<String>() {
        public void changed(ObservableValue<? extends String> observable,
                String oldValue, String newValue) {
            Storage.descriptionText = newValue;
        }
    };
    static ChangeListener<Boolean> defaultDescription = new ChangeListener<Boolean>() {
        public void changed(ObservableValue<? extends Boolean> arg0, 
            Boolean oldPropertyValue, Boolean newPropertyValue)
        {
            if (newPropertyValue) {
                Storage.descriptionText = "";
                MainPage.getControl().getDescription().setText(Storage.descriptionText);
            } else {
                if (Storage.descriptionText == "") {
                    Storage.descriptionText = "Description";
                    MainPage.getControl().getDescription().setText(Storage.descriptionText);
                }
            }
        }
    };
    
    /*
     * Misc Methods
     */
     public static boolean isNumeric(String value) {
        if (value.equals(null) || value.isEmpty()) {
            return false;
        }
        
        try {
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
     //Resets all the data in the table 
     private static void TableDataToDefault(boolean check) {
         if (check) {
             ObservableList<Set> defaultData = FXCollections.observableArrayList(
                 new Set("1", "", "", "", "", "", "", "", ""),
                 new Set("1", "", "", "", "", "", "", "", ""),
                 new Set("1", "", "", "", "", "", "", "", ""),
                 new Set("1", "", "", "", "", "", "", "", ""),
                 new Set("1", "", "", "", "", "", "", "", ""));   
              Storage.datagroup1 = defaultData;
              Storage.datagroup2 = defaultData;
              Storage.datagroup3 = defaultData;
              Storage.datagroup4 = defaultData;
              Storage.datagroup5 = defaultData;
              for (int i = 1; i <= Storage.AMT_GROUPS; i++) {
                  MainPage.getTable(i).setData(defaultData);
              }
         }
     }
}

