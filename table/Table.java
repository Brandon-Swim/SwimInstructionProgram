package table;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import mainpage.SidePane;
import java.util.ArrayList;
import background.Group;
import graphs.Graphs;

public class Table {
  private static int ID = 0;
  private static final String[] columnNames = new String[] {"Set", "Rounds", "Reps", "Distance",
      "Description", "Type", "Minutes", "Seconds", "Intensity"};
  private ObservableList<Set> data = FXCollections.observableArrayList();

  private TableView<Set> table = new TableView<>();
  private Label header;

  @SuppressWarnings("unchecked")
  public Table(Group group, ArrayList<Graphs> graphs) {
    header = new Label();
    table.setPrefSize(1000, 400);
    table.setEditable(true);
    table.setPlaceholder(new Label("Table is empty, the + button to add a row.")); // TODO IMAGE

    populateTable(group);

    TableColumn<Set, String> setNum = new TableColumn<>(columnNames[0]);
    setNum.setPrefWidth(50); // TODO move background 5% of table
    setNum.setMinWidth(50);
    setNum.setMaxWidth(50); // Total the three stops from resizing
    setNum.setCellValueFactory(new PropertyValueFactory<Set, String>("setCol"));
    setNum.setCellFactory(TextFieldTableCell.forTableColumn());
    setNum.setOnEditCommit(new EventHandler<CellEditEvent<Set, String>>() {
      public void handle(CellEditEvent<Set, String> t) {
        // Checks to see if the input is a number
        if (isNumeric(t.getNewValue()) && isValid(t.getNewValue(), 0, -1)) {
          setValue(t, group);
          SidePane.getNode(0).setData(group.getID(), group.totalDistance());
          SidePane.getNode(1).setData(group.getID(), group.totalTime());
          SidePane.getNode(2).setData(group.getID(), group.avgIntensity());
          SidePane.getNode(3).setData(group.getID(), group.workingDistance());
          SidePane.getNode(4).setData(group.getID(), group.workingTime());
          SidePane.getNode(5).setData(group.getID(), group.workingIntensity());
          SidePane.updateNodes();
          populateTable(group);
          graphs.get(group.getID()).populateGraphs(group);
        }
      }
    });

    TableColumn<Set, String> roundNum = new TableColumn<>(columnNames[1]);
    roundNum.setPrefWidth(50); // TODO move background 5% of table
    roundNum.setMaxWidth(50);
    roundNum.setMinWidth(50);
    roundNum.setCellValueFactory(new PropertyValueFactory<Set, String>("roundsCol"));
    roundNum.setCellFactory(TextFieldTableCell.forTableColumn());
    roundNum.setOnEditCommit(new EventHandler<CellEditEvent<Set, String>>() {
      public void handle(CellEditEvent<Set, String> t) {
        // Checks to see if the input is a number
        if (isNumeric(t.getNewValue()) && isValid(t.getNewValue(), 0, -1)) {
          setValue(t, group);
          SidePane.getNode(0).setData(group.getID(), group.totalDistance());
          SidePane.getNode(1).setData(group.getID(), group.totalTime());
          SidePane.getNode(3).setData(group.getID(), group.workingDistance());
          SidePane.getNode(4).setData(group.getID(), group.workingTime());
          SidePane.updateNodes();
          populateTable(group);
          graphs.get(group.getID()).populateGraphs(group);
        }
      }
    });

    TableColumn<Set, String> repNum = new TableColumn<>(columnNames[2]);
    repNum.setPrefWidth(50); // TODO move background 5% of table
    repNum.setMaxWidth(50);
    repNum.setMinWidth(50);
    repNum.setCellValueFactory(new PropertyValueFactory<Set, String>("repsCol"));
    repNum.setCellFactory(TextFieldTableCell.forTableColumn());
    repNum.setOnEditCommit(new EventHandler<CellEditEvent<Set, String>>() {
      public void handle(CellEditEvent<Set, String> t) {
        // Checks to see if the input is a number
        if (isNumeric(t.getNewValue()) && isValid(t.getNewValue(), 0, -1)) {
          setValue(t, group);
          SidePane.getNode(0).setData(group.getID(), group.totalDistance());
          SidePane.getNode(1).setData(group.getID(), group.totalTime());
          SidePane.getNode(3).setData(group.getID(), group.workingDistance());
          SidePane.getNode(4).setData(group.getID(), group.workingTime());
          SidePane.updateNodes();
          populateTable(group);
          graphs.get(group.getID()).populateGraphs(group);
        }
      }
    });

    TableColumn<Set, String> distanceNum = new TableColumn<>(columnNames[3]);
    distanceNum.setPrefWidth(150); // TODO move background 15% of table
    distanceNum.setMinWidth(150);
    distanceNum.setMaxWidth(150);
    distanceNum.setCellValueFactory(new PropertyValueFactory<Set, String>("distanceCol"));
    distanceNum.setCellFactory(TextFieldTableCell.forTableColumn());
    distanceNum.setOnEditCommit(new EventHandler<CellEditEvent<Set, String>>() {
      public void handle(CellEditEvent<Set, String> t) {
        // Checks to see if the input is a number
        if (isNumeric(t.getNewValue()) && isValid(t.getNewValue(), 0, -1)) {
          setValue(t, group);
          SidePane.getNode(0).setData(group.getID(), group.totalDistance());
          SidePane.getNode(3).setData(group.getID(), group.workingDistance());
          SidePane.updateNodes();
          populateTable(group);
          graphs.get(group.getID()).populateGraphs(group);
        }
      }
    });

    TableColumn<Set, String> description = new TableColumn<>(columnNames[4]);
    description.setPrefWidth(300); // TODO move background 30% of table
    description.setMaxWidth(300);
    description.setMinWidth(300);
    description.setCellValueFactory(new PropertyValueFactory<Set, String>("descriptionCol"));
    description.setCellFactory(TextFieldTableCell.forTableColumn());
    description.setOnEditCommit(new EventHandler<CellEditEvent<Set, String>>() {
      public void handle(CellEditEvent<Set, String> t) {
        // Checks to see if the input is a number
          setValue(t, group);
          SidePane.updateNodes();
          populateTable(group);
      }
    });

    ObservableList<String> typeSelector = 
        FXCollections.observableArrayList();
    for (int i = 0; i < group.typeSize(); i++) {
      typeSelector.add(group.getTypeOptions().get(i));
    }
    TableColumn<Set, String> type = new TableColumn<>(columnNames[5]);
    type.setPrefWidth(100); // TODO move background 10% of table
    type.setMaxWidth(100);
    type.setMinWidth(100);
    type.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
    type.setCellFactory(ComboBoxTableCell.forTableColumn(typeSelector));
    type.setOnEditCommit(new EventHandler<CellEditEvent<Set, String>>() {
      public void handle(CellEditEvent<Set, String> t) {
        // Checks to see if the input is a number
          setValue(t, group);
          SidePane.getNode(3).setData(group.getID(), group.workingDistance());
          SidePane.getNode(4).setData(group.getID(), group.workingTime());
          SidePane.getNode(5).setData(group.getID(), group.workingIntensity());
          SidePane.updateNodes();
          populateTable(group);
          graphs.get(group.getID()).populateGraphs(group);
      }
    });


    TableColumn<Set, String> minNum = new TableColumn<>(columnNames[6]);
    minNum.setPrefWidth(100); // TODO move background 10% of table
    minNum.setMaxWidth(100);
    minNum.setMinWidth(100);
    minNum.setCellValueFactory(new PropertyValueFactory<Set, String>("minCol"));
    minNum.setCellFactory(TextFieldTableCell.forTableColumn());
    minNum.setOnEditCommit(new EventHandler<CellEditEvent<Set, String>>() {
      public void handle(CellEditEvent<Set, String> t) {
        // Checks to see if the input is a number
        if (isNumeric(t.getNewValue()) && isValid(t.getNewValue(), 0, -1)) {
          setValue(t, group);
          SidePane.getNode(1).setData(group.getID(), group.totalTime());
          SidePane.getNode(4).setData(group.getID(), group.workingTime());
          SidePane.updateNodes();
          populateTable(group);
        }
      }
    });

    TableColumn<Set, String> secNum = new TableColumn<>(columnNames[7]);
    secNum.setPrefWidth(100); // TODO move background 10% of table
    secNum.setMaxWidth(100);
    secNum.setMinWidth(100);
    secNum.setCellValueFactory(new PropertyValueFactory<Set, String>("secCol"));
    secNum.setCellFactory(TextFieldTableCell.forTableColumn());
    secNum.setOnEditCommit(new EventHandler<CellEditEvent<Set, String>>() {
      public void handle(CellEditEvent<Set, String> t) {
        // Checks to see if the input is a number
        if (isNumeric(t.getNewValue()) && isValid(t.getNewValue(), 0, -1)) {
          setValue(t, group);
          SidePane.getNode(1).setData(group.getID(), group.totalTime());
          SidePane.getNode(4).setData(group.getID(), group.workingTime());
          SidePane.updateNodes();
          populateTable(group);
        }
      }
    });

    TableColumn<Set, String> IntensityNum = new TableColumn<>(columnNames[8]);
    IntensityNum.setPrefWidth(95); // TODO move background 10% of table
    IntensityNum.setMaxWidth(95);
    IntensityNum.setMinWidth(95);
    IntensityNum.setCellValueFactory(new PropertyValueFactory<Set, String>("intensityCol"));
    IntensityNum.setCellFactory(TextFieldTableCell.forTableColumn());
    IntensityNum.setOnEditCommit(new EventHandler<CellEditEvent<Set, String>>() {
      public void handle(CellEditEvent<Set, String> t) {
        // Checks to see if the input is a number
        if (isNumeric(t.getNewValue()) && isValid(t.getNewValue(), 0, 100)) {
          setValue(t, group);
          SidePane.getNode(2).setData(group.getID(), group.avgIntensity());
          SidePane.getNode(5).setData(group.getID(), group.workingIntensity());
          SidePane.updateNodes();
          populateTable(group);
          graphs.get(group.getID()).populateGraphs(group);
        }
      }
    });

    TableColumn<Set, Integer> time = new TableColumn<>("Time");
    time.getColumns().addAll(minNum, secNum);
    table.getColumns().addAll(setNum, roundNum, repNum, distanceNum, description, type, time,
        IntensityNum);
    table.setItems(data);
    // Set Up Labels
    header.setText("Group: " + (group.getID() + 1));
    header.setPrefSize(1000, 50);
    header.setTextAlignment(TextAlignment.CENTER);
    header.setAlignment(Pos.CENTER);
    header.setFont(Font.font("Arial", 32));
    header.setTextFill(Color.RED);
  }

  private void setValue(CellEditEvent<Set, String> t, Group group) {
    int row = t.getTablePosition().getRow();
    switch (t.getTablePosition().getColumn()) {
      case 0:
        group.editCell(row, Group.Name.set, Integer.parseInt(t.getNewValue()));
        break;
      case 1:
        group.editCell(row, Group.Name.rounds, Integer.parseInt(t.getNewValue()));
        break;
      case 2:
        group.editCell(row, Group.Name.reps, Integer.parseInt(t.getNewValue()));
        break;
      case 3:
        group.editCell(row, Group.Name.distance, Integer.parseInt(t.getNewValue()));
        break;
      case 4:
        group.editCell(row, Group.Name.description, t.getNewValue());
        break;
      case 5:
        group.editCell(row, Group.Name.type, t.getNewValue());
        break;
      case 6:
        group.editCell(row, Group.Name.minutes, Integer.parseInt(t.getNewValue()));
        break;
      case 7:
        group.editCell(row, Group.Name.seconds, Integer.parseInt(t.getNewValue()));
        break;
      case 8:
        group.editCell(row, Group.Name.intensity, Integer.parseInt(t.getNewValue()));
        break;
    }
  }

  public void populateTable(Group group) {
    data.clear();
    Set tempSet;
    for (int i = 0; i < group.size(); i++) {
      tempSet = new Set();
      tempSet.setSetCol(group.getCell(i, Group.Name.set).toString());
      tempSet.setRoundsCol(group.getCell(i, Group.Name.rounds).toString());
      tempSet.setRepsCol(group.getCell(i, Group.Name.reps).toString());
      tempSet.setDistanceCol(group.getCell(i, Group.Name.distance).toString());
      tempSet.setDescriptionCol(group.getCell(i, Group.Name.description).toString());
      tempSet.setTypeCol(group.getCell(i, Group.Name.type).toString());
      tempSet.setMinCol(group.getCell(i, Group.Name.minutes).toString());
      tempSet.setSecCol(group.getCell(i, Group.Name.seconds).toString());
      tempSet.setIntensityCol(group.getCell(i, Group.Name.intensity).toString());
      for (int j = 0; j < tempSet.size(); j++) {
        if (j != 4 && j != 5 && Integer.parseInt(tempSet.get(j)) == 0) {
          tempSet.set(j, "");
        }
      }
      data.add(tempSet);
    }
  }

  /*
   * Checks to see if the input string is a number
   */
  private boolean isNumeric(String value) {
    if (value.equals(null) || value.isEmpty()) {
      return false;
    }

    try {
      @SuppressWarnings("unused")
      double d = Integer.parseInt(value);
    } catch (NumberFormatException nfe) {
      Alert alert = new Alert(AlertType.ERROR);
      Text text = new Text(
          "Error: " + value + " is not an accepted input." + " Input must be a whole number.");
      text.setWrappingWidth(300);
      alert.getDialogPane().setContent(text);
      alert.setTitle("Input Error");
      alert.showAndWait();
      return false;
    }
    return true;
  }

  private boolean isValid(String value, int lowerBound, int upperBound) {
    if (value.equals(null) || value.isEmpty()) {
      return false;
    }
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Input Error");
    if (upperBound == -1) {
      if (Integer.parseInt(value) < lowerBound) {
        Text text = new Text(
            "Error: " + value + " is not an accepted input." + " Input is less than " + lowerBound);
        text.setWrappingWidth(300);
        alert.getDialogPane().setContent(text);
        alert.showAndWait();
        return false;
      }
    } else {
      if (Integer.parseInt(value) > upperBound) {
        Text text = new Text(
            "Error: " + value + " is not an accepted input." + " Input is over " + upperBound);
        text.setWrappingWidth(300);
        alert.getDialogPane().setContent(text);
        alert.showAndWait();
        return false;
      }
      if (Integer.parseInt(value) < lowerBound) {
        Text text = new Text(
            "Error: " + value + " is not an accepted input." + " Input is less than " + lowerBound);
        text.setWrappingWidth(300);
        alert.getDialogPane().setContent(text);
        alert.showAndWait();
        return false;
      }
    }
    return true;
  }

  public TableView<Set> getTableView() {
    return this.table;
  }

  public Label getHeader() {
    return header;
  }

  public void addTable(Pane tablePane) {
    tablePane.getChildren().addAll(header, table);
  }

  public void remTable(Pane tablePane) {
    tablePane.getChildren().removeAll(header, table);
  }

  public void setData(ObservableList<Set> data) {
    table.setItems(data);
  }

  public ObservableList<Set> getData() {
    return data;
  }

  public Set getCell(int index) {
    return data.get(index);
  }

}

