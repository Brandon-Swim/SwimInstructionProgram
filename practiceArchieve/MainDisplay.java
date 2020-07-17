package practiceArchieve;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
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

public class MainDisplay {
  private final Border RED_BORDER = new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID,
      CornerRadii.EMPTY, BorderWidths.DEFAULT));
  private final Border BLACK_BORDER = new Border(new BorderStroke(Color.BLACK,
      BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
  private final String DEST = "C:\\Users\\Brandon\\.eclipse\\Practice_Builder\\";

  private Pane graphPane;
  private Pane graphInfoPane;
  private HBox graphInfoLayout;
  private VBox mainDisplayLayout;
  private ArrayList<String> dates;
  private String lowerBound;
  private String upperBound;

  public MainDisplay() {
    graphPane = new Pane();
    graphInfoPane = new Pane();
    mainDisplayLayout = new VBox();
    graphInfoLayout = new HBox();
    lowerBound = "";
    upperBound = "";
    dates = new ArrayList<String>();
    try {
      createDatesArray();
    } catch (IOException e) {
      e.printStackTrace();
    }

    DistanceGraph distance = new DistanceGraph();
    IntensityGraph intensity = new IntensityGraph();
    TypeGraph type = new TypeGraph();
    graphPane.setPrefSize(1000, 600);
    graphPane.setBorder(BLACK_BORDER);
    graphPane.getChildren().add(distance.getChart());

    // Graph Info Pane Setup
    graphInfoPane.setPrefSize(1000, 50);
    graphInfoPane.setBorder(RED_BORDER);
    Region sp1 = new Region();
    sp1.setPrefSize(10, 50);

    ComboBox<String> lowerDate = new ComboBox<String>();
    for (String str : dates) {
      lowerDate.getItems().add(str);
    }
    ChangeListener<String> lowerDateEvent = new ChangeListener<String>() {
      @Override
      public void changed(ObservableValue ov, String oldStr, String newStr) {
        lowerBound = newStr;
        try {
          distance.updateDataRange(lowerBound, upperBound);
          intensity.updateDataRange(lowerBound, upperBound);
          type.updateDataRange(lowerBound, upperBound);
        } catch (InputMismatchException ex) {
          Alert alert = new Alert(AlertType.ERROR);
          Label text = new Label("Error: Lower bound of " + lowerBound + " and upper bound of "
              + upperBound + "\n are not acceptable bounds. Please selected acceptable bounds");
          text.setWrapText(true);
          alert.setTitle("Date Range Error");
          alert.getDialogPane().setContent(text);
          alert.showAndWait();
        }
      }
    };
    lowerDate.valueProperty().addListener(lowerDateEvent);

    Label dateLabel = new Label("To");
    dateLabel.setPrefSize(40, 50);
    dateLabel.setAlignment(Pos.CENTER);
    dateLabel.setFont(Font.font("Arial", 14));

    ComboBox<String> upperDate = new ComboBox<String>();
    for (String str : dates) {
      upperDate.getItems().add(str);
    }
    ChangeListener<String> upperDateEvent = new ChangeListener<String>() {
      @Override
      public void changed(ObservableValue ov, String oldStr, String newStr) {
        upperBound = newStr;
        try {
          distance.updateDataRange(lowerBound, upperBound);
          intensity.updateDataRange(lowerBound, upperBound);
          type.updateDataRange(lowerBound, upperBound);
        } catch (InputMismatchException ex) {
          Alert alert = new Alert(AlertType.ERROR);
          Label text = new Label("Error: Lower bound of " + lowerBound + " and upper bound of "
              + upperBound + "\n are not acceptable bounds. Please selected acceptable bounds");
          text.setWrapText(true);
          alert.setTitle("Date Range Error");
          alert.getDialogPane().setContent(text);
          alert.showAndWait();
        }
      }
    };
    upperDate.valueProperty().addListener(upperDateEvent);

    Region sp2 = new Region();
    sp2.setPrefSize(5, 5);

    Button refreshDates = new Button("Show All");
    refreshDates.setPrefSize(100, 25);
    refreshDates.setFont(Font.font("Arial", 12));
    refreshDates.setAlignment(Pos.CENTER);
    refreshDates.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {
        try {
          distance.updateDataRange("00/00", "99/99");
          intensity.updateDataRange("00/00", "99/99");
          type.updateDataRange("00/00", "99/99");
        } catch (InputMismatchException ex) {
          Alert alert = new Alert(AlertType.ERROR);
          Label text = new Label("Error: Lower bound of " + lowerBound + " and upper bound of "
              + upperBound + "\n are not acceptable bounds. Please selected acceptable bounds");
          text.setWrapText(true);
          alert.setTitle("Date Range Error");
          alert.getDialogPane().setContent(text);
          alert.showAndWait();
        }
        lowerDate.valueProperty().removeListener(lowerDateEvent);
        lowerDate.getSelectionModel().selectFirst();
        lowerDate.valueProperty().addListener(lowerDateEvent);
        upperDate.valueProperty().removeListener(upperDateEvent);
        upperDate.getSelectionModel().selectLast();
        upperDate.valueProperty().addListener(upperDateEvent);

      }
    });

    Region sp3 = new Region();
    sp3.setPrefSize(5, 5);

    Button displayDistanceGraph = new Button("Distance Graph");
    displayDistanceGraph.setPrefSize(100, 25);
    displayDistanceGraph.setFont(Font.font("Arial", 12));
    displayDistanceGraph.setAlignment(Pos.CENTER);
    displayDistanceGraph.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {
        graphPane.getChildren().clear();
        graphPane.getChildren().add(distance.getChart());
      }
    });


    Region sp4 = new Region();
    sp4.setPrefSize(5, 5);

    Button displayIntensityGraph = new Button("Intensity Graph");
    displayIntensityGraph.setPrefSize(100, 25);
    displayIntensityGraph.setFont(Font.font("Arial", 12));
    displayIntensityGraph.setAlignment(Pos.CENTER);
    displayIntensityGraph.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {
        graphPane.getChildren().clear();
        graphPane.getChildren().add(intensity.getChart());
      }
    });

    Region sp5 = new Region();
    sp5.setPrefSize(5, 5);

    Button displayTypeGraph = new Button("Type Graph");
    displayTypeGraph.setPrefSize(100, 25);
    displayTypeGraph.setFont(Font.font("Arial", 12));
    displayTypeGraph.setAlignment(Pos.CENTER);
    displayTypeGraph.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {
        graphPane.getChildren().clear();
        graphPane.getChildren().add(type.getChart());
      }
    });

    graphInfoLayout.getChildren().addAll(sp1, lowerDate, dateLabel, upperDate, sp2, refreshDates,
        sp3, displayDistanceGraph, sp4, displayIntensityGraph, sp5, displayTypeGraph);
    graphInfoLayout.setAlignment(Pos.CENTER);
    graphInfoPane.getChildren().add(graphInfoLayout);
    mainDisplayLayout.getChildren().addAll(graphPane, graphInfoPane);
  }

  private void createDatesArray() throws IOException {
    String date = null;
    Scanner fileInput = null;
    File d = new File("TxtFiles//dates.txt");
    fileInput = new Scanner(d);
    while (fileInput.hasNextLine()) {
      date = fileInput.nextLine();
      dates.add(date);
    }
    fileInput.close();
  }

  public VBox getLayout() {
    return mainDisplayLayout;
  }
}
