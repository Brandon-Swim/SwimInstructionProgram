package mainpage;

import background.SwimWorkout;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class MonthDescription extends Descriptions {

  public MonthDescription(SwimWorkout workout) {
    super(80, 40, 60, 40, "Month: ");
    editor.setText("MM");
    // Stores the value in the workout
    editor.textProperty().addListener(new ChangeListener<String>() {
      public void changed(ObservableValue<? extends String> observable, String oldValue,
          String newValue) {
        try {
          workout.setMonth(Integer.parseInt(newValue));
        } catch (Exception e) {
          workout.setMonth(0);
        }
      }
    });
    // Resets the textfield to default text if empty
    editor.focusedProperty().addListener(new ChangeListener<Boolean>() {
      public void changed(ObservableValue<? extends Boolean> arg0, Boolean lostFocus,
          Boolean gainedFocus) {
        if (gainedFocus && editor.getText().contentEquals("MM")) {
          editor.setText("");
          workout.setMonth(0);
        } else if (lostFocus && workout.getMonth() == 0) {
          editor.setText("MM");
          workout.setMonth(0);
        }
      }
    });
  }
}

