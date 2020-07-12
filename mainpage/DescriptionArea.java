package mainpage;

import background.SwimWorkout;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class DescriptionArea {
  private static final String defaultText = new String("Description ");
  private Label label;
  protected TextArea editor;
  private static final Border Border = new Border(new BorderStroke(Color.BLACK,
      BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));

  public DescriptionArea(SwimWorkout workout) {
    label = new Label();
    editor = new TextArea();
    label.setText("Description: ");
    label.setFont(Font.font("Arial", 20));
    label.setBorder(Border);
    label.setPrefSize(250, 40);
    label.setTextAlignment(TextAlignment.CENTER);
    label.setAlignment(Pos.CENTER_LEFT);

    editor.setText(defaultText);
    editor.setFont(Font.font("Arial", 20));
    editor.setBorder(Border);
    editor.setPrefSize(250, 150);
    editor.setMaxWidth(250);
    editor.setWrapText(true);
    editor.textProperty().addListener(new ChangeListener<String>() {
      public void changed(ObservableValue<? extends String> observable, String oldValue,
          String newValue) {
        workout.setDescription(newValue);
      }
    });
    editor.focusedProperty().addListener(new ChangeListener<Boolean>() {
      public void changed(ObservableValue<? extends Boolean> arg0, Boolean lostFocus,
          Boolean gainedFocus) {
        if (gainedFocus && editor.getText().contentEquals(defaultText)) {
          editor.setText("");
          workout.setDescription("");
        } else if (lostFocus && workout.getDescription().contentEquals("")) {
          editor.setText(defaultText);
          workout.setDescription(defaultText);
        }
      }
    });
  }
  
  public Label getLabel() {
    return label;
  }
  
  public TextArea getTextArea() {
    return editor;
  }
  
}
