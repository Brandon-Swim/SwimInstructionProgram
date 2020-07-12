package mainpage;

import background.Group;
import graphs.GraphData;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import table.Table;

/*
 * add Row remove Row copy Row paste Row clear Row
 */
public class TableModifiers {

  private Button addRow;
  private Button remRow;
  private Button copyRow;
  private Button pasteRow;
  private Button clearRow;
  private Object[] tempRow;

  public TableModifiers() {
    addRow = new Button("+");
    remRow = new Button("-");
    copyRow = new Button("C");
    pasteRow = new Button("P");
    clearRow = new Button("Cl");
    tempRow = new Object[] {0, 0, 0, 0, "New Row", "", 0, 0, 0};
  }


  public TableModifiers(Group group, Table table) {
    // add Row
    this();
    buttonSetUp(addRow);
    addRow.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e) {
        group.createRow();
        table.populateTable(group);
        table.getTableView().refresh();
      }
    });
    // remove Row
    buttonSetUp(remRow);
    remRow.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e) {
        if (group.size() != 0) {
          group.remRow();
          table.populateTable(group);
          table.getTableView().refresh();
          // TODO new TableEvents().refreshSideData(group.getID());
        }
      }
    });
    // copy Row
    buttonSetUp(copyRow);
    copyRow.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e) {
        int index = table.getTableView().getSelectionModel().getSelectedIndex();
        tempRow = group.getRow(index);
      }
    });
    // paste Row
    buttonSetUp(pasteRow);
    pasteRow.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e) {
        int index = table.getTableView().getSelectionModel().getSelectedIndex();
        group.setRow(index, tempRow);
        table.populateTable(group);
        table.getTableView().refresh();
      }
    });
    // clear Row
    buttonSetUp(clearRow);
    clearRow.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e) {
        Object[] clearRow = new Object[] {0, 0, 0, 0, "", "", 0, 0, 0};
        int index = table.getTableView().getSelectionModel().getSelectedIndex();
        group.setRow(index, clearRow);
        table.populateTable(group);
        table.getTableView().refresh();
      }
    });
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
