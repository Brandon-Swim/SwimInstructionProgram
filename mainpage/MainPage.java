package mainpage;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
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
import java.util.ArrayList;
import background.Group;
import background.SwimWorkout;
import graphs.GraphPane;
import graphs.Graphs;
import table.Table;

public class MainPage {
  private final Border mainBorder = new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID,
      CornerRadii.EMPTY, BorderWidths.DEFAULT));
  private final Border layoutBorder = new Border(new BorderStroke(Color.BLACK,
      BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
  private final Border upperLayoutBorder = new Border(new BorderStroke(Color.BLUE,
      BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
  private static final int HEIGHT = 2400;
  private static final int WIDTH = 1525;
  private final Background BACKGROUND =
      new Background(new BackgroundFill(Color.rgb(255, 255, 255), CornerRadii.EMPTY, Insets.EMPTY));


  private static Pane mainPane;
  // Method Variables
  /*
   * Panes for laying out in the main Pane 0-Upper Pane 1-Middle Pane 2-Lower Pane 3-Side Pane
   * within the upper pane 4-Table Pane within the upper pane 5-Control Pane within the upper pane
   */
  // Getter Variables
  private static ControlPane controlPane;
  private static SidePane sideP;
  private static GraphPane midPane;
  static ScrollPane mainScene;
  protected ArrayList<Graphs> graphs = new ArrayList<Graphs>();
  protected ArrayList<Table> tables = new ArrayList<Table>();
  protected VBox tableLayout = new VBox();
  private final int MULTIPLIER = 4;
  protected SwimWorkout workout;

  public MainPage() {

  }


  public MainPage(SwimWorkout workout) {
    this();
    for (int i = 0; i < workout.size(); i++) {
      addGraph(workout.getGroup(i));
      addTable(workout.getGroup(i));
      tableLayout.getChildren().addAll(tables.get(i).getHeader(), tables.get(i).getTableView());
    }
    this.workout = workout;
    mainPane = new Pane();
    mainPane.setBorder(mainBorder);
    mainPane.setPrefSize(1800, HEIGHT); // TODO fix
    mainPane.setBackground(BACKGROUND);

    mainScene = new ScrollPane(mainPane);
    mainScene.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
    mainScene.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
    mainPane.setOnScroll(new EventHandler<ScrollEvent>() {
      @Override
      public void handle(ScrollEvent event) {
        double deltaY = event.getDeltaY() * MULTIPLIER; // *6 to make the scrolling a bit faster
        double width = mainScene.getContent().getBoundsInLocal().getWidth();
        double vvalue = mainScene.getVvalue();
        mainScene.setVvalue(vvalue + -deltaY / width); // deltaY/width to make the scrolling equally
                                                       // fast regardless of the actual width of the
                                                       // component
      }
    });
    mainScene.setFitToWidth(true);

    // General Pane Set Up (good)
    Pane topPane = new Pane();
    topPane.setPrefSize(WIDTH, 700);
    topPane.setBorder(layoutBorder);
    midPane = new GraphPane(graphs);
    Pane lowerPane = new Pane();
    lowerPane.setPrefSize(WIDTH, 700);
    lowerPane.setBorder(layoutBorder);
    VBox generalLayout = new VBox();
    generalLayout.setSpacing(10);

    Region spacer1 = new Region();
    spacer1.setBorder(mainBorder);
    spacer1.setPrefSize(10, 690);

    ScrollPane tableScrollPane = new ScrollPane(tableLayout);
    tableScrollPane.setPrefSize(1000, 690);
    tableScrollPane.setMaxHeight(690);
    tableScrollPane.setBorder(upperLayoutBorder);
    tableScrollPane.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
    tableScrollPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
    tableLayout.setOnScroll(new EventHandler<ScrollEvent>() {
      @Override
      public void handle(ScrollEvent event) {
        double deltaY = event.getDeltaY() * MULTIPLIER; // *6 to make the scrolling a bit faster
        double width = tableScrollPane.getContent().getBoundsInLocal().getWidth();
        double vvalue = tableScrollPane.getVvalue();
        tableScrollPane.setVvalue(vvalue + -deltaY / width); // deltaY/width to make the scrolling
                                                             // equally
        // fast regardless of the actual width of the
        // component
      }
    });
    Region spacer2 = new Region();
    spacer2.setPrefSize(10, 690);
    // TODO ControlPane
    HBox topLayout = new HBox();

    controlPane = new ControlPane(workout, tables, graphs, tableLayout);
    sideP = new SidePane();
    topLayout.getChildren().addAll(sideP.getPane(), spacer1, tableScrollPane, spacer2,
        controlPane.getPane());
    topPane.getChildren().add(topLayout);
    generalLayout.getChildren().addAll(topPane, midPane.getPane(), lowerPane);
    mainPane.getChildren().add(generalLayout);

  }
  
  // Getters
  // Gets the controlPane
  public static ControlPane getControl() {
    return controlPane;
  }

  public SwimWorkout getWorkout() {
    return workout;
  }
  
  public static GraphPane getGraphPane() {
    return midPane;
  }

  // gets the sidePane
  public static SidePane getSide() {
    return sideP;
  }

  // Returns the overall pane for the scene
  public ScrollPane getScrollPane() {
    return mainScene;
  }

  public void addGraph(Group g) {
    graphs.add(new Graphs(g));
  }
  
  public Graphs getGraph(int index) {
    return graphs.get(index);
  }

  public void remGraph(int index) {
    graphs.remove(index);
  }
  
  public int getGraphSize() {
    return graphs.size();
  }
  
  // Returns table based on index
  public Table getTable(int index) {
    return tables.get(index);
  }

  public void addTable(Group g) {
    tables.add(new Table(g, graphs));
  }

  public void remTable(int index) {
    tables.remove(index);
  }

  public int getTablesSize() {
    return tables.size();
  }

  // Returns table layout
  public VBox getTableLayout() {
    return tableLayout;
  }
}
