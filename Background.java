import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Background {
    
    static EventHandler<KeyEvent> addRow = new EventHandler<KeyEvent>(){
        public void handle(KeyEvent e){
            if (e.getCode() == KeyCode.TAB) {
                }
        }
    };
    
    public static boolean isNumeric(String value) {
        if (value.equals(null) || value.isEmpty()) {
            return false;
        }
        
        try {
            double d = Double.parseDouble(value);
        } catch (NumberFormatException nfe) {
            Alert alert =  new Alert(AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setContentText("Error: " + value + " is not an accepted input."
                + " Input must be a number.");
            alert.showAndWait();
            return false;
        }
        return true;
    }
}

