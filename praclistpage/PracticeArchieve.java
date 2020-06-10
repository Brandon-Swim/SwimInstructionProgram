package praclistpage;

import javafx.scene.layout.Pane;

public class PracticeArchieve {
    
    private Pane mainP = new Pane();
    
    public PracticeArchieve() {
        Pane mainPane = new Pane();
        this.mainP = mainPane;
    }
    
    public Pane getPane() {
        return mainP;
    }
}
