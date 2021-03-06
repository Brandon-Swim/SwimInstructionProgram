package practiceArchieve;

import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;

public class DistanceGraph {
  private final String DEST = "C:\\Users\\Brandon\\.eclipse\\Practice_Builder\\";
  private final CategoryAxis xAxis;
  private final NumberAxis yAxis;
  private final Random RAND_GEN = new Random(5777);
  private final LineChart<String, Number> chart;
  private ObservableList<XYChart.Series<String, Number>> series;

  @SuppressWarnings("unchecked")
  public DistanceGraph() {
    xAxis = new CategoryAxis();
    yAxis = new NumberAxis();
    series =
        FXCollections.observableArrayList(new LineChart.Series<String, Number>("Total Distance",
            FXCollections.observableArrayList()));
    chart = new LineChart<String, Number>(xAxis, yAxis, series);
    try {
      addData(series);
    } catch (IOException e) {
      e.printStackTrace();
    }
    chart.setTitle("Total Distance for the Season");
    chart.setPrefSize(1000, 600);
    chart.setLegendVisible(false);
    xAxis.setLabel("Workouts");
    yAxis.setLabel("Distance (yds");
  }

  public void updateDataRange(String lowerBound, String upperBound) {
    try {
      if (lowerBound != null && !lowerBound.contentEquals("") && upperBound != null
          && !upperBound.contentEquals("") && lowerBound.compareTo(upperBound) >= 0) {
        System.out.println("Error");
        throw new InputMismatchException();
      }
    } catch (InputMismatchException e) {
      Alert alert = new Alert(AlertType.ERROR);
      Label text = new Label("Error: Lower bound of " + lowerBound + " and upper bound of "
          + upperBound + "\n are not acceptable bounds. Please selected acceptable bounds");
      text.setWrapText(true);
      alert.setTitle("Date Range Error");
      alert.getDialogPane().setContent(text);
      alert.showAndWait();
      return;
    }
    chart.setAnimated(true);
    series.get(0).getData().clear();
    String date = null;
    Scanner fileInput = null;
    File dates = new File(DEST + "dates.txt");
    try {
      fileInput = new Scanner(dates);
      while (fileInput.hasNextLine()) {
        date = fileInput.nextLine();
        if (lowerBound != null && !lowerBound.contentEquals("") && upperBound != null
            && !upperBound.contentEquals("")) {
          if (date.compareTo(lowerBound) >= 0 && date.compareTo(upperBound) <= 0) {
            series.get(0).getData()
                .add(new XYChart.Data<String, Number>(date, RAND_GEN.nextInt(5777)));
          }
        } else if (lowerBound != null && !lowerBound.contentEquals("")
            && (upperBound == null || upperBound.contentEquals(""))) {
          if (date.compareTo(lowerBound) >= 0) {
            series.get(0).getData()
                .add(new XYChart.Data<String, Number>(date, RAND_GEN.nextInt(5777)));
          }
        } else if (upperBound != null && upperBound.contentEquals("")
            && (lowerBound == null || !lowerBound.contentEquals(""))) {
          if (date.compareTo(upperBound) <= 0) {
            series.get(0).getData()
                .add(new XYChart.Data<String, Number>(date, RAND_GEN.nextInt(5777)));
          }
        }
      }
    } catch (IOException e) {
      System.out.println("Erroe opening the file");
      e.printStackTrace();
    } finally {
      fileInput.close();
    }
    chart.setData(series);
    chart.setAnimated(false);
  }


  private void addData(ObservableList<XYChart.Series<String, Number>> series) throws IOException {
    String date = null;
    Scanner fileInput = null;
    File dates = new File(DEST + "dates.txt");
    fileInput = new Scanner(dates);
    while (fileInput.hasNextLine()) {
      date = fileInput.nextLine();
      series.get(0).getData().add(new XYChart.Data<String, Number>(date, RAND_GEN.nextInt(5000)));
    }
    fileInput.close();
  }

  public LineChart<String, Number> getChart() {
    return chart;
  }
}
