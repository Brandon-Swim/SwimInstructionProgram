package inpracpage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import background.SwimWorkout;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Layout1 {

  private Pane layout;
  private Pane selectionField;
  private VBox selectionFieldLayout;
  private HBox topLevelLayout;
  private VBox innerLayout;
  private Label header;
  private TextField fileName;
  private Button submit;
  private final Border BLACK_BORDER = new Border(new BorderStroke(Color.BLACK,
      BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
  private final Font headerFont = Font.font("Arial", 18);
  private final Font fileFont = Font.font("Arial", 16);

  private Region leftSpacer;
  private Region rightSpacer;
  private Region topSpacer;
  private Region bottomSpacer;

  public Layout1() {
    layout = new Pane();
    selectionField = new Pane();
    selectionFieldLayout = new VBox();
    topLevelLayout = new HBox();
    innerLayout = new VBox();
    header = new Label("Please put the name of the file below: ");
    fileName = new TextField();
    submit = new Button("Submit");

    regionSetUp();

    header.setFont(headerFont);
    header.setWrapText(true);
    header.setPrefHeight(40);

    fileName.setPrefHeight(40);
    fileName.setMaxWidth(300);
    fileName.setMinWidth(300);
    fileName.setBorder(BLACK_BORDER);
    fileName.setFont(fileFont);
    fileName.setAlignment(Pos.CENTER);
    fileName.setOnKeyPressed(new EventHandler<KeyEvent>() {
      @Override
      public void handle(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
          if (fileName.getText() != null && !fileName.getText().contentEquals("")) {
            try {
              File file = new File("XlsFiles\\" + fileName.getText() + ".xlsx");
              ArrayList<SwimWorkout> workouts = XlsConverter.XlsToSwimWorkout(file);
              ImportedPractices.setPane(new Layout2(workouts).getLayout());
            } catch (IOException ex) {
              displayAlert();
            }
          } else {
            displayAlert();
          }
        }
      }
    });

    submit.setFont(fileFont);
    submit.setPrefSize(80, 40);
    submit.setBorder(BLACK_BORDER);
    submit.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {
        if (fileName.getText() != null && !fileName.getText().contentEquals("")) {
          try {
            File file = new File("XlsFiles\\" + fileName.getText() + ".xlsx");
            ArrayList<SwimWorkout> workouts = XlsConverter.XlsToSwimWorkout(file);
            ImportedPractices.setPane(new Layout2(workouts).getLayout());
          } catch (IOException ex) {
            displayAlert();
          }
        } else {
          displayAlert();
        }
      }
    });

    Region innerSpacer = new Region();
    innerSpacer.setPrefHeight(5);
    Region outerSpacer = new Region();
    outerSpacer.setPrefHeight(5);

    selectionFieldLayout.getChildren().addAll(header, fileName, innerSpacer, submit, outerSpacer);
    // selectionFieldLayout.setPrefSize(350, 150);
    selectionFieldLayout.setAlignment(Pos.CENTER);
    selectionFieldLayout.setBorder(BLACK_BORDER);
    selectionField.getChildren().add(selectionFieldLayout);
    selectionField.setBorder(BLACK_BORDER);
    innerLayout.getChildren().addAll(topSpacer, selectionFieldLayout, bottomSpacer);
    topLevelLayout.getChildren().addAll(leftSpacer, innerLayout, rightSpacer);
    layout.getChildren().add(topLevelLayout);
  }

  private void displayAlert() {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setHeaderText("Incorrect File Name");
    Label content = new Label("The file name you have entered can not be found in the "
        + "selected folder or is incorrect.");
    content.setWrapText(true);
    alert.getDialogPane().setContent(content);
    alert.showAndWait();
  }

  private void regionSetUp() {
    leftSpacer = new Region();
    leftSpacer.setPrefSize(500, 800);
    rightSpacer = new Region();
    rightSpacer.setPrefSize(500, 800);
    topSpacer = new Region();
    topSpacer.setPrefSize(525, 250);
    bottomSpacer = new Region();
    bottomSpacer.setPrefSize(525, 200);
  }

  public HBox getLayout() {
    return topLevelLayout;
  }

}
