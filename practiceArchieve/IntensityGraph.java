package practiceArchieve;

import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class IntensityGraph {
  private final String DEST = "C:\\Users\\Brandon\\.eclipse\\Practice_Builder\\";
  private final int SEED = 1000;
  private final int RANGE = 100;
  private final CategoryAxis xAxis;
  private final NumberAxis yAxis;
  private final Random RAND_GEN = new Random(SEED);
  private final LineChart<String, Number> chart;
  private ObservableList<XYChart.Series<String, Number>> series;
  
  @SuppressWarnings("unchecked")
  public IntensityGraph() {
    xAxis = new CategoryAxis();
    yAxis = new NumberAxis();
    series = FXCollections.observableArrayList(new LineChart.Series<String, Number>("Total Distance",
            FXCollections.observableArrayList()));
    chart = new LineChart<String, Number>(xAxis, yAxis, series);
    try {
      addData(series);
    } catch (IOException e) {
      e.printStackTrace();
    }
    chart.setTitle("Total Intensity for the Season");
    chart.setPrefSize(1000, 600);
    chart.setLegendVisible(false);
    xAxis.setLabel("Workouts");
    yAxis.setLabel("Intensity (%)");
  }
  
  public void updateDataRange(String lowerBound, String upperBound) throws InputMismatchException {
      if (lowerBound != null && !lowerBound.contentEquals("") && upperBound != null
          && !upperBound.contentEquals("") && lowerBound.compareTo(upperBound) >= 0) {
        throw new InputMismatchException();
      }
    chart.setAnimated(true);
    series.get(0).getData().clear();
    String date = null;
    Scanner fileInput = null;
    File dates = new File("TxtFiles//dates.txt");
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
    File dates = new File("TxtFiles//dates.txt");
    fileInput = new Scanner(dates);
    while (fileInput.hasNextLine()) {
      date = fileInput.nextLine();
      series.get(0).getData().add(new XYChart.Data<String, Number>(date, RAND_GEN.nextInt(RANGE)));
    }
    fileInput.close();
  }

  public LineChart<String, Number> getChart() {
    return chart;
  }
}
