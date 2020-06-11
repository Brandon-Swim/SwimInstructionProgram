package mainpage;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TableView;
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
import javafx.scene.text.TextAlignment;
import general.Storage;
import javafx.geometry.Pos;
import table.Set;
import table.Table;

public class MainPage {
    
    static Pane mainPane;
    //Method Variables
    /*
     * Panes for laying out in the main Pane
     * 0-Upper Pane
     * 1-Middle Pane
     * 2-Lower Pane
     * 3-Side Pane within the upper pane
     * 4-Table Pane within the upper pane
     * 5-Control Pane within the upper pane
     */
    //Getter Variables 
    private static ControlPane controlP = new ControlPane();
    private static SidePane sideP = new SidePane();
    static ScrollPane mainScene;
    static Table table1 = new Table();
    static Table table2 = new Table();
    static Table table3 = new Table();
    static Table table4 = new Table();
    static Table table5 = new Table();
    private static VBox tablePane = new VBox();
    
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
        VBox tableHolder = new VBox();
        this.tablePane = tableHolder;
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
        
        SidePane information =  new SidePane(sideData);
        sideP = information;
         
        table1 = new Table(new TableView<>(), Storage.datagroup1, 1);
        table2 = new Table(new TableView<>(), Storage.datagroup2, 2);
        table3 = new Table(new TableView<>(), Storage.datagroup3, 3);
        table4 = new Table(new TableView<>(), Storage.datagroup4, 4);
        table5 = new Table(new TableView<>(), Storage.datagroup5, 5);
        
        ControlPane controller = new ControlPane(controlPane);
        controlP = controller;
        
        table1.addTable(tableHolder);   //Adds the first table to the pane
    }
    
    //Getters 
    // Gets the controlPane 
    public static ControlPane getControl() {
        return controlP;
    }
    // gets the sidePane
    public static SidePane getSide() {
        return sideP;
    }
    //Returns the overall pane for the scene
    public ScrollPane getPane() {
        return mainScene;
    }
    
    //Returns table based on index
    public static Table getTable(int ID) {
        switch (ID) {
            case 1:
                return table1;
            case 2:
                return table2;
            case 3:
                return table3;
            case 4:
                return table4;
            case 5:
                return table5;
            default:
                return null;
        }
    }
    //Returns table layout
    public static VBox getTableLayout() {
        return tablePane;
    }
}
