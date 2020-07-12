package mainpage;

import background.SwimWorkout;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class DayDescription extends Descriptions {

  public DayDescription(SwimWorkout workout) {
    super(50, 40, 60, 40, "Day: ");
    editor.setText("DD");
    // Stores the value in the workout
    editor.textProperty().addListener(new ChangeListener<String>() {
      public void changed(ObservableValue<? extends String> observable, String oldValue,
          String newValue) {
        try {
          workout.setDay(Integer.parseInt(newValue));
        } catch (Exception e) {
          workout.setDay(0);
        }
      }
    });
    // Resets the textfield to default text if empty
    editor.focusedProperty().addListener(new ChangeListener<Boolean>() {
      public void changed(ObservableValue<? extends Boolean> arg0, Boolean lostFocus,
          Boolean gainedFocus) {
        if (gainedFocus && editor.getText().contentEquals("DD")) {
          editor.setText("");
          workout.setDay(0);
        } else if (lostFocus && workout.getDay() == 0) {
          editor.setText("DD");
          workout.setDay(0);
        }
      }
    });
  }
}
