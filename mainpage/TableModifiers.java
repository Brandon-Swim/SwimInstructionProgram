package mainpage;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import table.Set;
import table.Table;
import table.TableEvents;
import table.TableUtils;

/*
 * add Row
 * remove Row
 * copy Row
 * paste Row
 * clear Row
 */
public class TableModifiers {
    
    private Button addRow = new Button();
    private Button remRow = new Button();
    private Button copyRow = new Button();
    private Button pasteRow = new Button();
    private Button clearRow = new Button();
    private Set tempSet = new Set(null, null, null, null, null, 
        null, null, null, null);
    
    
    public TableModifiers(Table table) {
        Button addRow = new Button("+");
        Button remRow = new Button("-");
        Button copyRow = new Button("C");
        Button pasteRow = new Button("P");
        Button clearRow = new Button("Cl");
        //add Row
        buttonSetUp(addRow);
        addRow.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                table.getData().add(new Set("", "", "", "", "New Row", "", "", "", ""));
                table.getTableView().refresh();
            }
        });
        this.addRow = addRow;
        //remove Row
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
        //copy Row
        buttonSetUp(copyRow);
        copyRow.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                tempSet = table.getTableView().getSelectionModel().getSelectedItem();
                TableUtils.copySelectionToClipboard(table.getTableView());
            }
        });
        this.copyRow = copyRow;
        //paste Row
        buttonSetUp(pasteRow);
        pasteRow.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                int temp = table.getTableView().getSelectionModel().getSelectedIndex();
                table.getData().remove(temp);
                table.getData().add(temp, tempSet);
                table.getTableView().refresh();
            }
        });
        this.pasteRow = pasteRow;
        //clear Row
        buttonSetUp(clearRow);
        clearRow.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                int tempSet = table.getTableView()
                    .getSelectionModel().getSelectedIndex();
                table.getData().set(tempSet, 
                    new Set("1", "", "", "", "", "", "", "", ""));
                TableEvents.refreshSideData(table.getID());
                MainPage.getSide().updateSelectedData(table.getID());  
                table.getTableView().refresh();
            }
        });
        this.clearRow = clearRow;
    }
    private void buttonSetUp(Button but) {
        but.setPrefSize(30, 30);
        but.setWrapText(true);
        but.setFont(Font.font("Arial", 18));
        but.setAlignment(Pos.CENTER);
    }
    
    //Getters 
    
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
