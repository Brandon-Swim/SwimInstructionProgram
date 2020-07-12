package mainpage;

import background.SwimWorkout;
import general.Storage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class Descriptions {
  private Label label;
  protected TextField editor;
  private static final Border Border = new Border(new BorderStroke(Color.BLACK,
      BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));


  public Descriptions() {
    label = new Label();
    editor = new TextField();
  }

  public Descriptions(int labelX, int labelY, int fieldX, int fieldY, String text) {
    this();
    label.setText(text);
    label.setFont(Font.font("Arial", 20));
    label.setBorder(Border);
    label.setPrefSize(labelX, labelY);
    label.setTextAlignment(TextAlignment.CENTER);
    label.setAlignment(Pos.CENTER_RIGHT);

    editor.setFont(Font.font("Arial", 20));
    editor.setBorder(Border);
    editor.setPrefSize(fieldX, fieldY);
    editor.setAlignment(Pos.CENTER);
  }
  
  public Label getLabel() {
    return label;
  }
  
  public TextField getTextField() {
    return editor;
  }
}
