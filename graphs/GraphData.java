package graphs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

public class GraphData extends PieChart {
    private ObservableList<PieChart.Data> data = 
        FXCollections.observableArrayList(
        new PieChart.Data("Warm Up", 0));
    
    
    public GraphData() {
        
    }
    
    public GraphData(int ID) {

    }
    
    //public ObservableList<PieChart.Data> getData() {
    //    return data;
    //}
}
