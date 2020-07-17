package mainpage;

import java.util.ArrayList;
import background.SwimWorkout;
// import java.util.ArrayList;
import graphs.Graphs;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
// import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import table.Table;

public class ControlPane {

  private Pane controlPane = new Pane();
  private static final Border testBorder = new Border(new BorderStroke(Color.BLACK,
      BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
  private final Border upperLayoutBorder = new Border(new BorderStroke(Color.BLUE,
      BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
  // Getter variables
  private FlowPane graphPane = new FlowPane();
  private VBox rowPane = new VBox();
  private TextArea descriptionField = new TextArea();
  private ArrayList<HBox> tableControlLayout = new ArrayList<HBox>();
  private ArrayList<RadioButton> graphSelector = new ArrayList<RadioButton>();
  private ArrayList<TableModifiers> tableControls = new ArrayList<TableModifiers>();
  private ToggleGroup RBLayout = new ToggleGroup();

  public ControlPane(SwimWorkout workout, ArrayList<Table> tables, ArrayList<Graphs> graphs,
      VBox tablelayout) {
    VBox layoutGeneral = new VBox(); // Contains everything vertically
    FlowPane layoutTitle = new FlowPane(); // layout for name, month, day
    VBox layoutRows = new VBox(); // layout for groups
    FlowPane layoutButton = new FlowPane();
    // ArrayList<Region> spacers = new ArrayList<>(); //TODO

    // 1. Set up the Header, added with out a layout.
    Label header = new Label();
    header.setText("Control Panel");
    header.setFont(Font.font("Arial", 24));
    header.setBorder(testBorder);
    header.setPrefSize(250, 40);
    header.setTextAlignment(TextAlignment.CENTER);
    header.setAlignment(Pos.CENTER);

    // 2. Set up Layout for the title
    NameDescription name = new NameDescription(workout);
    MonthDescription month = new MonthDescription(workout);
    DayDescription day = new DayDescription(workout);

    layoutTitle.setPrefSize(250, 80);
    layoutTitle.setMaxWidth(250);
    layoutTitle.getChildren().addAll(name.getLabel(), name.getTextField(), month.getLabel(),
        month.getTextField(), day.getLabel(), day.getTextField());

    // 3. Description set up
    DescriptionArea description = new DescriptionArea(workout);

    // 4. Row and Graph set Up
    for (int i = 0; i < workout.size(); i++) {
      tableControlLayout.add(new HBox());
      tableControlLayout.get(i).setPrefSize(250, 40);
      tableControlLayout.get(i).setMaxWidth(250);
      tableControlLayout.get(i).setMaxHeight(50);
      tableControls.add(new TableModifiers(workout.getGroup(i), tables.get(i)));
      tableControlLayout.get(i).getChildren().addAll(tableControls.get(i).getAddRow(),
          tableControls.get(i).getRemRow(), tableControls.get(i).getCopyRow(),
          tableControls.get(i).getPasteRow(), tableControls.get(i).getClearRow());

      graphSelector.add(new RadioButton("Graph " + (i + 1)));
      graphSelector.get(i).setUserData(i + 1);
      graphSelector.get(i).setToggleGroup(RBLayout);
    }

    RBLayout.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
      public void changed(ObservableValue<? extends Toggle> ob, Toggle o, Toggle n) {
        RadioButton radioButton = (RadioButton) RBLayout.getSelectedToggle();
        for (int i = 0; i < graphSelector.size(); i++) {
          if (radioButton != null
              && radioButton.getText().contentEquals(graphSelector.get(i).getText())) {
            MainPage.getGraphPane().getPane().getChildren().clear();
            MainPage.getGraphPane().getLayout().getChildren().clear();
            MainPage.getGraphPane().setLayout(graphs.get(i));
          }
        }
      }
    });

    graphSelector.get(0).setSelected(true);

    layoutRows.setPrefSize(250, 150);
    layoutRows.setBorder(testBorder);

    layoutRows.getChildren().add(tableControlLayout.get(0));
    this.rowPane = layoutRows;

    graphPane.setPrefSize(250, 40);
    graphPane.setMaxWidth(250);
    graphPane.getChildren().add(graphSelector.get(0));

    // 5. Table button set up
    WorkoutControls buttons = new WorkoutControls(workout, tables, graphs, tablelayout,
        tableControls, tableControlLayout, graphSelector, RBLayout, graphPane);

    layoutButton.setPrefWidth(250);
    layoutButton.setMaxWidth(250);
    layoutButton.setMaxHeight(100);
    layoutButton.setBorder(testBorder);
    for (int i = 0; i < buttons.size(); i++) {
      layoutButton.getChildren().add(buttons.getButton(i));
    }

    layoutGeneral.getChildren().addAll(header, layoutTitle, description.getLabel(),
        description.getTextArea(), layoutButton, layoutRows, graphPane);
    controlPane.getChildren().add(layoutGeneral);
    controlPane.setPrefSize(250, 690);
    controlPane.setBorder(upperLayoutBorder);
  }

  // Getters for action events
  // Returns the radio button at the selected index for the graph selector

  public Pane getPane() {
    return controlPane;
  }

  public ToggleGroup getToggle() {
    return RBLayout;
  }

  // Returns the layout for the graph selector radio buttons
  public FlowPane getGraphLayout() {
    return graphPane;
  }

  // Returns the entire controlPane
  public Pane getControlPane() {
    return controlPane;
  }

  // Returns HBox layout for the selected table. Layout has add row, rem row
  // copy row, paste row, and clear row buttons.
  public HBox getTableController(int index) {
    return tableControlLayout.get(index);
  }

  // Returns the layout for the tableControls
  public VBox getRowLayout() {
    return rowPane;
  }

  /*
   * // Returns the TextField for the workout name public TextField getWorkoutName() { return
   * name.getTextField(); } // Returns the TextField for the month public TextField getMonth() {
   * return month.getTextField(); } //Returns the TextField for the day public TextField getDay() {
   * return day.getTextField(); }
   */
  // Returns the TextArea for the description
  public TextArea getDescription() {
    return descriptionField;
  }
}
