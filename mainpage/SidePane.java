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
    
    private Label[] labels = new Label[Storage.sideLabel.length];
    
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
            this.labels[i] = sideData[i];
        }
        sideLayout.setAlignment(Pos.TOP_CENTER);
        sideDataHolder.getChildren().add(sideLayout);
    } 
    
    public Label getSideData(int ID) {
        return labels[ID];
    }
    
    public void updateSelectedData(int ID) {
        switch (ID) {
            case 1:
                Storage.sideLabel[0] = "TTL Distance: " + 
            Storage.ttlDistanceAmts[0] + " yds";
                break;
            case 2:
                Storage.sideLabel[0] = "TTL Distance: " + 
            Storage.ttlDistanceAmts[0] + "|" + Storage.ttlDistanceAmts[1]
                + " yds";
                break;
            case 3:
                Storage.sideLabel[0] = "TTL Distance: " + 
            Storage.ttlDistanceAmts[0] + "|" + Storage.ttlDistanceAmts[1]
                + "|" + Storage.ttlDistanceAmts[2] + " yds";
                break;
            case 4:
                Storage.sideLabel[0] = "TTL Distance: " + 
            Storage.ttlDistanceAmts[0] + "|" + Storage.ttlDistanceAmts[1]
                + "|" + Storage.ttlDistanceAmts[2] + "|" 
            + Storage.ttlDistanceAmts[3] + " yds";
                break;
            case 5:
                Storage.sideLabel[0] = "TTL Distance: " + 
            Storage.ttlDistanceAmts[0] + "|" + Storage.ttlDistanceAmts[1]
                + "|" + Storage.ttlDistanceAmts[2] + "|" 
            + Storage.ttlDistanceAmts[3] + "|" + Storage.ttlDistanceAmts[4]
                + " yds";
                break;
        }
        MainPage.getSide().getSideData(0).setText(Storage.sideLabel[0]);
    }
}
