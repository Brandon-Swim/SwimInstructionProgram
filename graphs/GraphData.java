package graphs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import mainpage.MainPage;
import table.Set;

public class GraphData {
  private ObservableList<PieChart.Data> data =
      FXCollections.observableArrayList();


  public GraphData() {

  }

  public ObservableList<PieChart.Data> getData() {
    return data;
  }

  // adds new Data to the list
  public void naiveAddData(String name, double value) {
    data.add(new PieChart.Data(name, value));
  }

  // updates existing Data-Object if name matches
  public void addData(String name, double value) {
    
    for (int i = 0; i < data.size(); i++){
      PieChart.Data d = data.get(i);
      if (d.getName().equals(name)) {
        d.setPieValue(value);
        return;
      }
    }
    naiveAddData(name, value);
  }

  public void remData(String name) {
    for (int i = 0; i < data.size(); i++) {
      if (name.contentEquals(data.get(i).getName())) {
        data.remove(i);
      }
    }
  }

  public void printData() {
    String temp = "";
    for (int i = 0; i < data.size(); i++) {
      temp += "Row (" + i + ") name: " + data.get(i).getName() + ". Value: "
          + data.get(i).getPieValue() + "\n";
    }
    System.out.println(temp);
  }
  
  
  public void refreshPane() {
    
  }
}
