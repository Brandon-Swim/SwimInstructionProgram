package inpracpage;

import javafx.scene.layout.Pane;

public class ImportedPractices {

    private Pane mainP = new Pane();
    
    public ImportedPractices() {
        Pane mainPane = new Pane();
        this.mainP = mainPane;
    }
    
    public Pane getPane() {
        return mainP;
    }
}
