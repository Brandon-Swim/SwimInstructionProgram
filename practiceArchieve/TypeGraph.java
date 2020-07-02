package practiceArchieve;

import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import general.Storage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;

public class TypeGraph {

  private final String DEST = "C:\\Users\\Brandon\\.eclipse\\Practice_Builder\\";
  private final int SEED = 1459;
  private final int RANGE = 10;
  private final CategoryAxis xAxis;
  private final NumberAxis yAxis;
  private final Random RAND_GEN = new Random(SEED);
  private final BarChart<String, Number> chart;
  private ObservableList<XYChart.Series<String, Number>> series;
  
  @SuppressWarnings("unchecked")
  public TypeGraph() {
    xAxis = new CategoryAxis();
    yAxis = new NumberAxis();
    series = FXCollections.observableArrayList(new BarChart.Series<String, Number>("Types",
            FXCollections.observableArrayList()));
    chart = new BarChart<String, Number>(xAxis, yAxis, series);
    try {
      addData(series);
    } catch (IOException e) {
      e.printStackTrace();
    }
    chart.setTitle("Types per practice for the season");
    chart.setPrefSize(1000, 600);
    chart.setLegendVisible(false);
    xAxis.setLabel("Types");
    yAxis.setLabel("Frequency");
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
                .add(new XYChart.Data<String, Number>(date, RAND_GEN.nextInt(RANGE)));
          }
        } else if (lowerBound != null && !lowerBound.contentEquals("")
            && (upperBound == null || upperBound.contentEquals(""))) {
          if (date.compareTo(lowerBound) >= 0) {
            series.get(0).getData()
                .add(new XYChart.Data<String, Number>(date, RAND_GEN.nextInt(RANGE)));
          }
        } else if (upperBound != null && upperBound.contentEquals("")
            && (lowerBound == null || !lowerBound.contentEquals(""))) {
          if (date.compareTo(upperBound) <= 0) {
            series.get(0).getData()
                .add(new XYChart.Data<String, Number>(date, RAND_GEN.nextInt(RANGE)));
          }
        }
      }
    } catch (IOException e) {
      System.out.println("Error opening the file");
      e.printStackTrace();
    } finally {
      fileInput.close();
    }
    chart.setData(series);
    chart.setAnimated(false);
  }


  private void addData(ObservableList<XYChart.Series<String, Number>> series) throws IOException {
    for (String str: Storage.typeSelector) {
      series.get(0).getData().add(new XYChart.Data<String, Number>(str, RAND_GEN.nextInt(RANGE)));
    }
  }

  public BarChart<String, Number> getChart() {
    return chart;
  }
}
