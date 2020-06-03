import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javax.swing.BorderFactory;
import javax.swing.SwingConstants;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Dimension2D;
import javafx.geometry.Pos;

public class MainPage {
    
    static Pane mainPane;
    /*
     * Panes for laying out in the main Pane
     * 0-Upper Pane
     * 1-Middle Pane
     * 2-Lower Pane
     * 3-Side Pane within the upper pane
     * 4-Table Pane within the upper pane
     * 5-Control Pane within the upper pane
     */
    static Pane[] layoutPanes = new Pane[6];
    static ScrollPane mainScene;
    //Side Panel
    /* 
     * Displays total distance
     * Displays toal time
     * Displays Intensity
     * Working Distance (not in warm up or easy)
     * Working Time (not in warm up or easy)
     * Working Intensity (not in warm up or easy)
     * Displays total amount of practice types for the season
     * Displays total season distance
     * Displays practices left in the week
     * Displays practices left to next competition
     * Displays practices since last game day
     * Displays practices until taper
     * Displays Individual Set distances
     * Displays amount of working yards
     */
    static String[] sideLabel = new String[] {"Total Distance: 0 yds", 
        "Total Time: 0 min", "Avg Intensity: 0%", "Working Distance: 0 yds", 
        "Working Time: 0 min","Working Intensity: 0%","T Type Counter", 
        "T Season Distance","T Practices left in the week", "T Practices til Comp", 
        "T Game Day Counter","T Practices until Taper", "Set distance"};
    static Label[] sideData = new Label[sideLabel.length];
    public MainPage() {
        int height = 2400;
        Border mainBorder = new Border(new BorderStroke(Color.RED, 
            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
        Border layoutBorder = new Border(new BorderStroke(Color.BLACK, 
            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
        Border upperLayoutBorder = new Border(new BorderStroke(Color.BLUE, 
            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
 
        mainPane = new Pane();
        mainPane.setBorder(mainBorder);
        mainPane.setPrefSize(1800,height);    //TODO fix
        
        mainScene = new ScrollPane(mainPane);
        mainScene.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
        mainScene.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
        mainScene.setFitToWidth(true);
        
        //General Pane Set Up
        Pane infoPane = new Pane();
        infoPane.setPrefSize(2000, 800);
        infoPane.setBorder(layoutBorder);
        Pane graphPane = new Pane();
        graphPane.setPrefSize(2000, 600);
        graphPane.setBorder(layoutBorder);
        Pane ppPane = new Pane();
        ppPane.setPrefSize(2000, 800);
        ppPane.setBorder(layoutBorder);
        VBox generalLayout = new VBox();
        generalLayout.setSpacing(10);
        
        //Side Pane Set Up
        Pane sideData = new Pane();
        ScrollPane sidePane = new ScrollPane(sideData);
        sidePane.setPrefSize(250, 790);
        sidePane.setBorder(upperLayoutBorder);
        sidePane.setHbarPolicy(ScrollBarPolicy.NEVER);
        sidePane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
        
        Region spacer1 = new Region();
        spacer1.setBorder(mainBorder);
        Pane tableHolder = new Pane();
        ScrollPane tablePane = new ScrollPane(tableHolder);
        //tableHolder.setPrefSize(1500, 1000); sets the panes scrollable size
        tablePane.setPrefSize(1380, 790);   
        tablePane.setBorder(upperLayoutBorder);
        tablePane.setHbarPolicy(ScrollBarPolicy.NEVER);
        tablePane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
        Region spacer2 =  new Region();
        spacer2.setPrefSize(10, 790);
        Pane controlPane = new Pane();
        controlPane.setPrefSize(250, 790);
        controlPane.setBorder(upperLayoutBorder);
        HBox sideLayout = new HBox();
        
        generalLayout.getChildren().addAll(infoPane, graphPane, ppPane);
        sideLayout.getChildren().addAll(sidePane, spacer1, tablePane, spacer2, controlPane);
        mainPane.getChildren().add(generalLayout);  //Sets main layout
        infoPane.getChildren().add(sideLayout); //Sets side data layout
        SidePaneSetUp(sideData);
        
        TableView<Set> table1 = new TableView<>();
        Table tableBuilder = new Table(table1);
        tableBuilder.addTable(tableHolder);
    }
    
    public ScrollPane getPane() {
        return mainScene;
    }
    
    public void SidePaneSetUp(Pane sideDataHolder) {
        Border sideDataBorder = new Border(new BorderStroke(Color.RED, 
            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
        VBox sideLayout = new VBox();
        //ScrollPane sideHolder = new ScrollPane(layoutPanes[3]);
        for (int i = 0; i < sideData.length; i++) {
            sideData[i] = new Label(sideLabel[i]);
            sideData[i].setFont(Font.font("Arial", 20));
            sideData[i].setBorder(sideDataBorder);
            sideData[i].setTextAlignment(TextAlignment.CENTER);
            sideData[i].setAlignment(Pos.CENTER);
            sideData[i].setPrefSize(230,75);
            sideData[i].setWrapText(true);
            sideLayout.getChildren().add(sideData[i]);
        }
        sideLayout.setAlignment(Pos.TOP_CENTER);
        sideDataHolder.getChildren().add(sideLayout);
    }    
}
