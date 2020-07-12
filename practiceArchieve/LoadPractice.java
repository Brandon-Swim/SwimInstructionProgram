package practiceArchieve;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import mainpage.SidePane;

public class LoadPractice {
  private Button selector;
  private String date;
  private Border blueBorder = new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID,
      CornerRadii.EMPTY, BorderWidths.DEFAULT));
  private final int WIDTH = 500;
  private final Font TEST_FONT = new Font("Arial", 18);
  private final Border RED_BORDER = new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID,
      CornerRadii.EMPTY, BorderWidths.DEFAULT));

  private HBox layout;
  private ArrayList<Region> spacers = new ArrayList<Region>();
  private Label type;
  private Label name;
  private Label distance;

  private ArrayList<Label> info; // All of the information selected in the settings

  public LoadPractice() {
    date = null;
    selector = new Button();
    layout = new HBox();
    type = new Label("N/A");
    type.setBorder(blueBorder);
    type.setFont(Font.font("Arial", 20));
    type.setPrefSize(100, 50);
    name = new Label("N/A");
    name.setBorder(blueBorder);
    name.setFont(Font.font("Arial", 20));
    name.setPrefSize(100, 50);
    distance = new Label("0 yds");
    distance.setBorder(blueBorder);
    distance.setFont(Font.font("Arial", 20));
    distance.setPrefSize(100, 50);
    
    info = new ArrayList<Label>();
    for (int i = 0; i < SidePane.getNodeSize(); i++) {
      info.add(new Label("Label: " + i));
      info.get(i).setFont(TEST_FONT);
      info.get(i).setBorder(RED_BORDER);
      info.get(i).setPrefSize(WIDTH, 40);
    }
    updateLayout();
  }

  private Region addSpacer(int width, int height) {
    Region spacer = new Region();
    spacer.setPrefSize(width, height);
    return spacer;
  }

  public HBox getLayout() {
    return layout;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
    selector = new Button("Date: " + this.date);
    selector.setPrefSize(150, 50);
    selector.setFont(Font.font("Arial", 20));
    selector.setBorder(blueBorder);
    selector.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e) {
        System.out.println(date);
        SelectionList.setInfo(info);
      }
    });
    updateLayout();
  }

  public void updateLayout() {
    layout.getChildren().clear();
    layout.getChildren().addAll(selector, addSpacer(2, 50), name, addSpacer(2, 50), type,
        addSpacer(2, 50), distance);
  }

  public Label getType() {
    return type;
  }

  public void setType(String newType) {
    type.setText(newType);
    updateLayout();
  }

  public Label getName() {
    return name;
  }

  public void setName(String newName) {
    name.setText(newName);
    updateLayout();
  }
  
  public void addInfo(Label newInfo) {
    info.add(newInfo);
  }
  
  public void remInfo(int index) {
    info.remove(index);
  }
  
  public ArrayList<Label> getInfo() {
    return info;
  }

}
