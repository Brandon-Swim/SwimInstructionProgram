package graphs;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * Overall class that declares all other graphs
 * @author Brandon
 *
 */

public class Graphs {
    private final Border testBorder = new Border(new BorderStroke(Color.BLACK, 
        BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
    
    
    public Graphs(Pane pane) {
        //ObservableList<PieChart.Data> pieChartData = createData();
        //DistanceGraph distance = new DistanceGraph(pieChartData);
        //distance.setPrefSize(600, 550);
        //distance.setBorder(testBorder);
        
        HBox layout = new HBox();
        layout.setPrefSize(2000, 600);  //Might not need
        layout.setBorder(testBorder);
        //layout.getChildren().addAll(distance);
        pane.getChildren().add(layout);
        
        ArrayList<ObservableList<PieChart.Data>> test = 
            new ArrayList<ObservableList<PieChart.Data>>();
    }
    
}
