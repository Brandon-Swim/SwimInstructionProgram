package settingspage;

import javafx.scene.layout.Pane;

public class Settings {

    private Pane mainP = new Pane();
    
    public Settings() {
        Pane mainPane = new Pane();
        this.mainP = mainPane;
    }
    
    public Pane getPane() {
        return mainP;
    }
}
