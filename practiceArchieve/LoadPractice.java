package practiceArchieve;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class LoadPractice {
  private Button selector;
  private String date;
  private Border blueBorder = new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID,
      CornerRadii.EMPTY, BorderWidths.DEFAULT));
  private VBox layout;
  
  public LoadPractice() {
    date = null;
    selector = new Button();
    layout = new VBox();
  }
  
  public LoadPractice(String date) {
    this.date = date;
    selector = new Button(date);
    selector.setPrefSize(300, 50);
    selector.setFont(Font.font("Arial", 24));
    selector.setBorder(blueBorder);
    selector.setOnAction(new EventHandler<ActionEvent>() {
    public void handle(ActionEvent e) {
      System.out.println(date);
      }
    });
    
    layout = new VBox();
    layout.getChildren().add(selector);
  }
  
  @Override
  public String toString() {
    String temp = date;
    return temp;
  }
  
  public VBox getLayout() {
    return layout;
  }
  
}
