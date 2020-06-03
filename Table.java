import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class Table {
    
    public ObservableList<Set> data = FXCollections.observableArrayList(
        new Set(5, 0, 0, 0, "0", 0, 0, 0, 0));
    private TableView<Set> TABLE = new TableView<>();
    
    public Table(TableView<Set> table) {
        table.setPrefSize(1350, 400);
        table.setEditable(true);
        String[] columnNames = new String[] {"Set","Rounds","Reps","Distance",
            "Description","Type","Minutes","Seconds", "Intensity"};
        
        TableColumn<Set, Integer> setNum = new TableColumn<Set, Integer>(columnNames[0]);
        setNum.setPrefWidth(67.5);    //TODO move background 5% of table
        setNum.setCellValueFactory(new PropertyValueFactory<>(null));
        setNum.setOnEditCommit(
            new EventHandler<CellEditEvent<Set, Integer>>() {
            public void handle(CellEditEvent<Set, Integer> t) {
                t.getTableView().getItems()
                    .get(t.getTablePosition().getRow()).setSet(t.getNewValue());
            }
         });
        
        TableColumn<Set, Integer> roundNum = new TableColumn<Set, Integer>(columnNames[1]);
        roundNum.setPrefWidth(67.5);    //TODO move background 5% of table
        roundNum.setCellValueFactory(new PropertyValueFactory<>(null));
        roundNum.setOnEditCommit(
            new EventHandler<CellEditEvent<Set, Integer>>() {
            public void handle(CellEditEvent<Set, Integer> t) {
                System.out.println("Round Change");
            }
         });
        
        TableColumn<Set, Integer> repNum = new TableColumn<Set, Integer>(columnNames[2]);
        repNum.setPrefWidth(67.5);    //TODO move background 5% of table
        repNum.setCellValueFactory(new PropertyValueFactory<>(null));
        repNum.setOnEditCommit(
            new EventHandler<CellEditEvent<Set, Integer>>() {
            public void handle(CellEditEvent<Set, Integer> t) {
                System.out.println("Rep Change");
            }
         });
        
        TableColumn<Set, Integer> distanceNum = new TableColumn<Set, Integer>(columnNames[3]);
        distanceNum.setPrefWidth(202.5);    //TODO move background 15% of table
        distanceNum.setCellValueFactory(new PropertyValueFactory<>(null));
        distanceNum.setOnEditCommit(
            new EventHandler<CellEditEvent<Set, Integer>>() {
            public void handle(CellEditEvent<Set, Integer> t) {
                System.out.println("Distnace Change");
            }
         });
        
        TableColumn<Set, String> description = new TableColumn<Set, String>(columnNames[4]);
        description.setPrefWidth(405);    //TODO move background 30% of table
        description.setCellValueFactory(new PropertyValueFactory<>(null));
        description.setOnEditCommit(
            new EventHandler<CellEditEvent<Set, String>>() {
            public void handle(CellEditEvent<Set, String> t) {
                System.out.println("description Change");
            }
         });
        
        TableColumn<Set, Integer> type = new TableColumn<Set, Integer>(columnNames[5]);
        type.setPrefWidth(135);    //TODO move background 10% of table
        type.setCellValueFactory(new PropertyValueFactory<>(null));
        type.setOnEditCommit(
            new EventHandler<CellEditEvent<Set, Integer>>() {
            public void handle(CellEditEvent<Set, Integer> t) {
                System.out.println("Type Change");
            }
         });
        
        TableColumn<Set, Integer> minNum = new TableColumn<Set, Integer>(columnNames[6]);
        minNum.setPrefWidth(135);    //TODO move background 10% of table
        minNum.setCellValueFactory(new PropertyValueFactory<>(null));
        minNum.setOnEditCommit(
            new EventHandler<CellEditEvent<Set, Integer>>() {
            public void handle(CellEditEvent<Set, Integer> t) {
                System.out.println("Minutes Change");
            }
         });
        
        TableColumn<Set, Integer> secNum = new TableColumn<Set, Integer>(columnNames[7]);
        secNum.setPrefWidth(135);    //TODO move background 10% of table
        secNum.setCellValueFactory(new PropertyValueFactory<>(null));
        secNum.setOnEditCommit(
            new EventHandler<CellEditEvent<Set, Integer>>() {
            public void handle(CellEditEvent<Set, Integer> t) {
                System.out.println("Seconds Change");
            }
         });
        
        TableColumn<Set, Integer> IntensityNum = new TableColumn<Set, Integer>(columnNames[8]);
        IntensityNum.setPrefWidth(130);    //TODO move background 10% of table
        IntensityNum.setCellValueFactory(new PropertyValueFactory<>(null));
        IntensityNum.setOnEditCommit(
            new EventHandler<CellEditEvent<Set, Integer>>() {
            public void handle(CellEditEvent<Set, Integer> t) {
                System.out.println("Intensity Change");
            }
         });
        
        table.setItems(data);
        table.getColumns().addAll(setNum, roundNum, repNum, distanceNum, 
            description, type, minNum, secNum, IntensityNum);
        this.TABLE = table;
    }
    public TableView<Set> getTable() {
        return TABLE;
    }
    
    public void addTable(Pane tablePane) {
        tablePane.getChildren().add(TABLE);
    }
}

