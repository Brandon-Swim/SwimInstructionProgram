package mainpage;

import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class SideNode {

  private static final int WIDTH = 230;
  private static final int HEIGHT = 75;
  private int nameIndex;
  private String defaultName;
  private String unit;
  private String value;
  private ArrayList<Object> data = new ArrayList<Object>();
  private Label label;
  Border border = new Border(new BorderStroke(Color.RED, 
      BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
  
  public SideNode() {
    nameIndex = -1;
    defaultName = "";
    unit = "units";
    data.add(0);
    value = getData(0).toString();
    label = new Label();
  }
  
  public SideNode(int nameIndex) {
    this();
    this.nameIndex = nameIndex;
    defaultName = SidePane.DEFAULT_NAMES[this.nameIndex];
    if (!SidePane.UNTS[this.nameIndex].contentEquals("N/A")) {
      unit = SidePane.UNTS[this.nameIndex];
    } else {
      unit = "";
    }
    label.setText(defaultName + " " + value + " " + unit);
    label.setFont(Font.font("Arial", 20));
    label.setBorder(border);
    label.setTextAlignment(TextAlignment.CENTER);
    label.setAlignment(Pos.CENTER);
    label.setPrefSize(WIDTH,HEIGHT);
    label.setWrapText(true);
    label.setCache(false);
  }
  
  public void addData(Object value) {
    data.add(value);
    updateLabel();
  }
  
  public void setData(int index, Object value) {
    data.set(index, value);
    updateLabel();
  }
  
  public Object getData(int index) {
    if (index >= data.size()) {
      return null;
    } else {
      return data.get(index);
    }
  }
  
  public int size() {
    return data.size();
  }
  
  public void remData(int index) {
    data.remove(index);
    updateLabel();
  }
  
  public void updateLabel() {
    String tempName = "";
    for (int i = 0; i < data.size(); i++) {
      if (data.size() == 1) {
        tempName += data.get(i);
      } else {
        if (i != data.size() - 1) {
          tempName += data.get(i) + "|";
        } else {
          tempName += data.get(i);
        }
      }
    }
    value = tempName;
    label.setText(defaultName + " " + value + " " + unit);
  }
  
  public Label getLabel() {
    return label;
  }
}
