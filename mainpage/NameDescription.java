package mainpage;

import background.SwimWorkout;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class NameDescription extends Descriptions {

  public NameDescription(SwimWorkout workout) {
    super(80, 40, 170, 40, "Name: ");
    editor.setText("Workout Name");
    // Stores the value in the workout
    editor.textProperty().addListener(new ChangeListener<String>() {
      public void changed(ObservableValue<? extends String> observable, String oldValue,
          String newValue) {
        workout.setName(newValue);
      }
    });
    editor.focusedProperty().addListener(new ChangeListener<Boolean>() {
      public void changed(ObservableValue<? extends Boolean> arg0, Boolean lostFocus,
          Boolean gainedFocus) {
        if (gainedFocus && editor.getText().contentEquals("Workout Name")) {
          editor.setText("");
          workout.setName("");
        } else if (lostFocus && workout.getName().contentEquals("")) {
          editor.setText("Workout Name");
          workout.setName("Workout Name");
        }
      }
    });

    // Resets the textfield to default text if empty
  }
}
