package mainpage;

import general.Storage;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class SidePane {
    
    public SidePane() {
        
    }
    
    public SidePane(Pane sideDataHolder) {
        Label[] sideData = new Label[Storage.sideLabel.length];
        Border sideDataBorder = new Border(new BorderStroke(Color.RED, 
            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
        VBox sideLayout = new VBox();
        //ScrollPane sideHolder = new ScrollPane(layoutPanes[3]);
        for (int i = 0; i < sideData.length; i++) {
            sideData[i] = new Label(Storage.sideLabel[i]);
            sideData[i].setFont(Font.font("Arial", 20));
            sideData[i].setBorder(sideDataBorder);
            sideData[i].setTextAlignment(TextAlignment.CENTER);
            sideData[i].setAlignment(Pos.CENTER);
            sideData[i].setPrefSize(230,75);
            sideData[i].setWrapText(true);
            sideLayout.getChildren().add(sideData[i]);
        }
        sideLayout.setAlignment(Pos.TOP_CENTER);
        sideDataHolder.getChildren().add(sideLayout);
    } 
}
