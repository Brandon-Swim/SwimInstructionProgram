package mainpage;

import java.util.Optional;
import general.Storage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import table.Set;
import table.TableEvents;

public class CPListeners {
    
      /*
     * Control Panel Listeners
     * For every action
     *  -adds radiobutton for graph
     *  -adds table 
     *  -changes side data for multiple groups
     *  -adds row options for the table
     *  -TODO problably some file output stuff   
     */
      public static EventHandler<ActionEvent> addControls = new EventHandler<ActionEvent>() {
          public void handle(ActionEvent e) {
              if (Storage.currentGroup < 5) {
                  Storage.currentGroup += 1;
                  MainPage.getControl().getGraphLayout().getChildren()
                  .add(MainPage.getControl().getGraph(Storage.currentGroup - 1));
                  MainPage.getControl().getRowLayout().getChildren()
                  .add(MainPage.getControl().getTableController(Storage.currentGroup -1));
                  MainPage.getTable(Storage.currentGroup).addTable(MainPage.getTableLayout());
              }
              MainPage.getSide().updateSelectedData(Storage.currentGroup);
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
      public static EventHandler<ActionEvent> remControls = new EventHandler<ActionEvent>() {
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
              MainPage.getSide().updateSelectedData(Storage.currentGroup);
          }
      };
      /*
       * TODO Set up gameDay layout
       */
      public static EventHandler<ActionEvent> gameDay = new EventHandler<ActionEvent>() {
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

      public static EventHandler<ActionEvent> reset = new EventHandler<ActionEvent>() {
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
      public static EventHandler<MouseEvent> clearWorkoutText= new EventHandler<MouseEvent>() {
          public void handle(MouseEvent t) {
              Storage.workoutName = "";
              MainPage.getControl().getWorkoutName().setText(Storage.workoutName);
          }
      };
      public static ChangeListener<String> storeWorkoutText = new ChangeListener<String>() {
          public void changed(ObservableValue<? extends String> observable,
                  String oldValue, String newValue) {
              Storage.workoutName = newValue;
          }
      };
      public static ChangeListener<Boolean> defaultWorkoutText = new ChangeListener<Boolean>() {
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
      public static EventHandler<MouseEvent> clearMonthText= new EventHandler<MouseEvent>() {
          public void handle(MouseEvent t) {
              Storage.monthName = "";
              MainPage.getControl().getMonth().setText(Storage.monthName);
          }
      };
      public static ChangeListener<String> storeMonth = new ChangeListener<String>() {
          public void changed(ObservableValue<? extends String> observable,
                  String oldValue, String newValue) {
              Storage.monthName = newValue;
          }
      };
      public static ChangeListener<Boolean> defaultMonthText = new ChangeListener<Boolean>() {
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
      public static EventHandler<MouseEvent> clearDayText= new EventHandler<MouseEvent>() {
          public void handle(MouseEvent t) {
              Storage.dayName = "";
              MainPage.getControl().getDay().setText(Storage.dayName);
          }
      };
      public static ChangeListener<String> storeDay = new ChangeListener<String>() {
          public void changed(ObservableValue<? extends String> observable,
                  String oldValue, String newValue) {
              Storage.dayName = newValue;
          }
      };
      public static ChangeListener<Boolean> defaultDayText = new ChangeListener<Boolean>() {
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
      public static EventHandler<MouseEvent> clearDescription= new EventHandler<MouseEvent>() {
          public void handle(MouseEvent t) {
              Storage.descriptionText = "";
              MainPage.getControl().getDescription().setText(Storage.descriptionText);
          }
      };
      public static ChangeListener<String> storeDescription = new ChangeListener<String>() {
          public void changed(ObservableValue<? extends String> observable,
                  String oldValue, String newValue) {
              Storage.descriptionText = newValue;
          }
      };
      public static ChangeListener<Boolean> defaultDescription = new ChangeListener<Boolean>() {
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
      //Resets all the data in the table 
      private static void TableDataToDefault(boolean check) {
          if (check) {
               for (int i = 1; i <= Storage.AMT_GROUPS; i++) {
                   Storage.datagroup1.clear();
                   Storage.datagroup2.clear();
                   Storage.datagroup3.clear();
                   Storage.datagroup4.clear();
                   Storage.datagroup5.clear();
                   for (int j = 0; j < 5; j++) {
                       Storage.datagroup1.add(
                           new Set("1", "", "", "", "", "", "", "", ""));
                       Storage.datagroup2.add(
                           new Set("1", "", "", "", "", "", "", "", ""));
                       Storage.datagroup3.add(
                           new Set("1", "", "", "", "", "", "", "", ""));
                       Storage.datagroup4.add(
                           new Set("1", "", "", "", "", "", "", "", ""));
                       Storage.datagroup5.add(
                           new Set("1", "", "", "", "", "", "", "", ""));
                   }
                   TableEvents.refreshSideData(i);
                   MainPage.getSide().updateSelectedData(i);
               }
          }
      }
}
