import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Storage {
    
    public static ObservableList<Set> data = FXCollections.observableArrayList(
        new Set("0", "0", "0", "0", "0", "0", "0", "0", "0"));   // example rounds
    public static void addData() {
        data.add(new Set("", "", "", "", "", "", "", "", ""));
    }
}
