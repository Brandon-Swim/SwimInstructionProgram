package mainpage;

import general.Storage;
import graphs.GraphData;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import table.GraphEvents;
import table.Set;
import table.Table;
import table.TableEvents;

/*
 * add Row remove Row copy Row paste Row clear Row
 */
public class TableModifiers {

  private Button addRow = new Button();
  private Button remRow = new Button();
  private Button copyRow = new Button();
  private Button pasteRow = new Button();
  private Button clearRow = new Button();
  private Set tempSet = new Set(null, null, null, null, null, null, null, null, null);


  public TableModifiers(Table table) {
    Button addRow = new Button("+");
    Button remRow = new Button("-");
    Button copyRow = new Button("C");
    Button pasteRow = new Button("P");
    Button clearRow = new Button("Cl");
    // add Row
    buttonSetUp(addRow);
    addRow.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e) {
        table.getData().add(new Set("", "", "", "", "New Row", "", "", "", ""));
        table.getTableView().refresh();
      }
    });
    this.addRow = addRow;
    // remove Row
    buttonSetUp(remRow);
    remRow.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e) {
        if (table.getData().size() != 0) {
          table.getData().remove(table.getData().size() - 1);
          table.getTableView().refresh();
          TableEvents.refreshSideData(table.getID());
        }
        if (table.getData().size() == 0) {
          TableEvents.refreshSideData(table.getID());
          table.getTableView().refresh();
        }
      }
    });
    this.remRow = remRow;
    // copy Row
    buttonSetUp(copyRow);
    copyRow.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e) {
        tempSet = table.getTableView().getSelectionModel().getSelectedItem();
      }
    });
    this.copyRow = copyRow;
    // paste Row
    buttonSetUp(pasteRow);
    pasteRow.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e) {
        int index = table.getTableView().getSelectionModel().getSelectedIndex();
        table.getData().remove(index);
        Set tempSet2 = new Set("", "", "", "", "", "", "", "", "");
        for (int i = 0; i < tempSet2.AMT_COLUMNS; i++) {
          if (i != tempSet2.ROUNDS) {
            tempSet2.set(i, tempSet.get(i));
          } else if (i == tempSet2.ROUNDS) {
            tempSet2.set(i, "");
          }
        }
        table.getData().add(index, tempSet2);
        TableEvents.refreshSideData(table.getID());
        MainPage.getSide().updateSelectedData(table.getID());
        table.getTableView().refresh();
      }
    });
    this.pasteRow = pasteRow;
    // clear Row
    buttonSetUp(clearRow);
    clearRow.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e) {
        //Table Updates
        int tempSet = table.getTableView().getSelectionModel().getSelectedIndex();
        table.getData().set(tempSet, new Set("1", "", "", "", "", "", "", "", ""));
        TableEvents.refreshSideData(table.getID());
        MainPage.getSide().updateSelectedData(table.getID());;
        table.getTableView().refresh();
        //Graph Updates
          updateGraphs("distance", table.getID(), 0);
          updateGraphs("intensity", table.getID(), 0);
          updateGraphs("type", table.getID(), 5);
      }
    });
    this.clearRow = clearRow;
  }
  //TODO bug where if you clear the last row it crashes
  public static void updateGraphs(String name, int ID, int colID) {
   int index = ID - 1;
    GraphData graphData = MainPage.getGraphs().getData(name, index);
    boolean present = false;
    // Checks for if something needs to be removed'
    System.out.println(Storage.getSet(ID).size());
    for (int i = 0; i < graphData.getData().size(); i++) {
      for (int j = 0; j < Storage.getSet(ID).size(); j++) {
        present = false;
        System.out.println(name + ": " + i + " " + j);
        if (Storage.getSet(ID).get(j).get(colID) != null && 
            !Storage.getSet(ID).get(j).get(colID).contentEquals("") &&
            graphData.getData().get(i).getName().equals(Storage.getSet(ID).get(j).get(colID))) {
          present = true;
          break;
        } 
      }
      if (!present) {
        graphData.getData().remove(i);
      }
    }
  }

  private void buttonSetUp(Button but) {
    but.setPrefSize(30, 30);
    but.setWrapText(true);
    but.setFont(Font.font("Arial", 18));
    but.setAlignment(Pos.CENTER);
  }

  // Getters

  public Button getAddRow() {
    return this.addRow;
  }

  public Button getRemRow() {
    return this.remRow;
  }

  public Button getCopyRow() {
    return this.copyRow;
  }

  public Button getPasteRow() {
    return this.pasteRow;
  }

  public Button getClearRow() {
    return this.clearRow;
  }
}
