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
import javafx.geometry.Pos;

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
    private static Label[] sideData = new Label[Storage.sideLabel.length];
    //Getter Variables 
    private static ControlPanel controlP = new ControlPanel();
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
        
        SidePaneSetUp(sideData);
        ControlPanel controller = new ControlPanel(controlPane);
        controlP = controller;
         
        table1 = new Table(new TableView<>(), Storage.datagroup1, 1);
        table2 = new Table(new TableView<>(), Storage.datagroup2, 2);
        table3 = new Table(new TableView<>(), Storage.datagroup3, 3);
        table4 = new Table(new TableView<>(), Storage.datagroup4, 4);
        table5 = new Table(new TableView<>(), Storage.datagroup5, 5);
        table1.addTable(tableHolder);   //Adds the first table to the pane
    }
    
    //Methods
    public void SidePaneSetUp(Pane sideDataHolder) {
        Border sideDataBorder = new Border(new BorderStroke(Color.RED, 
            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
        VBox sideLayout = new VBox();
        //ScrollPane sideHolder = new ScrollPane(layoutPanes[3]);
        for (int i = 0; i < sideData.length; i++) {
            sideData[i] = new Label(Storage.sideLabel[i]);
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
   
    //Getters 
    // Gets the controlPane 
    public static ControlPanel getControl() {
        return controlP;
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
