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
            sideData[i].setCache(false);
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
        int[] min = new int[Storage.AMT_GROUPS];
        int[] sec = new int[Storage.AMT_GROUPS];
        double[] tempMin = new double[Storage.AMT_GROUPS];
        double[] tempSec = new double[Storage.AMT_GROUPS];
        for (int i = 0; i < Storage.AMT_GROUPS; i++) {
            tempMin[i] = Storage.ttlTimeAmts[i];
            tempSec[i] = (tempMin[i] - Math.floor(tempMin[i])) * 60;
            tempMin[i] = Math.floor(tempMin[i]);
            sec[i] = (int)tempSec[i];
            min[i] = (int)tempMin[i];
        }
        
        int[] minW = new int[Storage.AMT_GROUPS];
        int[] secW = new int[Storage.AMT_GROUPS];
        double[] tempMinW = new double[Storage.AMT_GROUPS];
        double[] tempSecW = new double[Storage.AMT_GROUPS];
        for (int i = 0; i < Storage.AMT_GROUPS; i++) {
            tempMinW[i] = Storage.workningTimeAmts[i];
            tempSecW[i] = (tempMinW[i] - Math.floor(tempMinW[i])) * 60;
            tempMinW[i] = Math.floor(tempMinW[i]);
            secW[i] = (int)tempSecW[i];
            minW[i] = (int)tempMinW[i];
        }
        //System.out.println("Min:" + Arrays.toString(minW));
        //System.out.println("Sec" + Arrays.toString(secW));
        
        switch (Storage.currentGroup) {
            case 1:
                Storage.sideLabel[0] = "TTL Distance: " + 
                    Storage.ttlDistanceAmts[0] + " yds";
                
                Storage.sideLabel[1] = "TTL Time: " + min[0] + " mins " + 
                    sec[0] + " sec";
                
                Storage.sideLabel[2] = "Avg Intensity: " 
                    + Storage.ttlIntensity[0] + "%";
                
                Storage.sideLabel[3] = "Working Distance: " + 
                    Storage.workingDistanceAmts[0] + " yds";
                
                Storage.sideLabel[4] = "Working Time: " + minW[0] + " mins " + 
                    secW[0] + " sec";
                
                Storage.sideLabel[5] = "Working Intensity: " 
                    + Storage.workingIntensity[0] + "%";
                
                Storage.sideLabel[6] = "Workout Type: " + Storage.workoutType[0];
                break;
            case 2:
                Storage.sideLabel[0] = "TTL Distance: " + 
            Storage.ttlDistanceAmts[0] + "|" + Storage.ttlDistanceAmts[1]
                + " yds";
                
                Storage.sideLabel[1] = "TTL Time: " + min[0] + "|" + min[1] 
            + " min";
                
                Storage.sideLabel[2] = "Avg Intensity: " 
            + Storage.ttlIntensity[0] + "|" + Storage.ttlIntensity[1] + "%";
                
                Storage.sideLabel[3] = "Working Distance: " + 
                    Storage.workingDistanceAmts[0] + "|" + 
                    Storage.workingDistanceAmts[1] + " yds";
                
                Storage.sideLabel[4] = "Working Time: " + minW[0] + "|" + minW[1] 
            + " min";
                
                Storage.sideLabel[5] = "Working Intensity: " 
                    + Storage.workingIntensity[0] + "|" 
                    + Storage.workingIntensity[1] + "%";
                
                Storage.sideLabel[6] = "Workout Type: " + Storage.workoutType[0] + "|"
                    + Storage.workoutType[1];
                break;
            case 3:
                Storage.sideLabel[0] = "TTL Distance: " + 
            Storage.ttlDistanceAmts[0] + "|" + Storage.ttlDistanceAmts[1]
            + "|" + Storage.ttlDistanceAmts[2] + " yds";
                
                Storage.sideLabel[1] = "TTL Time: " + min[0] + "|" + min[1] 
            + "|" + min[2] + " min";
                
                Storage.sideLabel[2] = "Avg Intensity: " 
            + Storage.ttlIntensity[0] + "|" + Storage.ttlIntensity[1] 
            + "|" + Storage.ttlIntensity[2] + "%";
                
                Storage.sideLabel[3] = "Working Distance: " + 
                    Storage.workingDistanceAmts[0] + "|" + 
                    Storage.workingDistanceAmts[1] + "|" + 
                    Storage.workingDistanceAmts[2] + "yds";

                Storage.sideLabel[4] = "Working Time: " + minW[0] + "|" + minW[1] 
            + "|" + minW[2] + " min";

                Storage.sideLabel[5] = "Working Intensity: " 
                    + Storage.workingIntensity[0] + "|" 
                    + Storage.workingIntensity[1] + "|" 
                    + Storage.workingIntensity[2] + "|" + "%";
                
                
                Storage.sideLabel[6] = "Workout Type: " 
                + Storage.workoutType[0] + "|"
                + Storage.workoutType[1] + "|"
                + Storage.workoutType[2];
                break;
            case 4:
                Storage.sideLabel[0] = "TTL Distance: " + 
                    Storage.ttlDistanceAmts[0] + "|" + Storage.ttlDistanceAmts[1]
                    + "|" + Storage.ttlDistanceAmts[2] + "|" 
                    + Storage.ttlDistanceAmts[3] + " yds";
                
                Storage.sideLabel[1] = "TTL Time: " + min[0] + "|" + min[1] 
                    + "|" + min[2] + "|" + min[3] + "min";
                
                Storage.sideLabel[2] = "Avg Intensity: " 
                    + Storage.ttlIntensity[0] + "|" + Storage.ttlIntensity[1] 
                    + "|" + Storage.ttlIntensity[2] + "|" + Storage.ttlIntensity[3] 
                    + "%";
                
                Storage.sideLabel[3] = "Working Distance: " + 
                    Storage.workingDistanceAmts[0] + "|" + 
                    Storage.workingDistanceAmts[1] + "|" + 
                    Storage.workingDistanceAmts[2] + "|" +
                    Storage.workingDistanceAmts[3] + "yds";
                
                Storage.sideLabel[4] = "Working Time: " + minW[0] + "|" + minW[1] 
                    + "|" + minW[2] + "|" + minW[3] + "min";
                
                Storage.sideLabel[5] = "Working Intensity: " 
                    + Storage.workingIntensity[0] + "|" 
                    + Storage.workingIntensity[1] + "|" 
                    + Storage.workingIntensity[2] + "|" 
                    + Storage.workingIntensity[3] + "|" + "%";
                
                Storage.sideLabel[6] = "Workout Type: " 
                + Storage.workoutType[0] + "|"
                + Storage.workoutType[1] + "|"
                + Storage.workoutType[2] + "|"
                + Storage.workoutType[3];
                break;
            case 5:
                Storage.sideLabel[0] = "TTL Distance: " + 
            Storage.ttlDistanceAmts[0] + "|" + Storage.ttlDistanceAmts[1]
                + "|" + Storage.ttlDistanceAmts[2] + "|" 
            + Storage.ttlDistanceAmts[3] + "|" + Storage.ttlDistanceAmts[4]
                + " yds";
                
                Storage.sideLabel[1] = "TTL Time: " + min[0] + "|" + min[1] 
            + "|" + min[2] + "|" + min[3] + "|" + min[4] + "min";
                
                Storage.sideLabel[2] = "Avg Intensity: " 
            + Storage.ttlIntensity[0] + "|" + Storage.ttlIntensity[1] 
            + "|" + Storage.ttlIntensity[2] + "|" + Storage.ttlIntensity[3] 
            + "|" + Storage.ttlIntensity[4] + "%";
                
                Storage.sideLabel[3] = "Working Distance: " + 
                    Storage.workingDistanceAmts[0] + "|" + 
                    Storage.workingDistanceAmts[1] + "|" + 
                    Storage.workingDistanceAmts[2] + "|" +
                    Storage.workingDistanceAmts[3] + "|" + 
                    Storage.workingDistanceAmts[4] + "yds";
                
                Storage.sideLabel[4] = "Working Time: " + minW[0] + "|" + minW[1] 
            + "|" + minW[2] + "|" + minW[3] + "|" + minW[4] + "min";
                
                Storage.sideLabel[5] = "Working Intensity: " 
                    + Storage.workingIntensity[0] + "|" 
                    + Storage.workingIntensity[1] + "|" 
                    + Storage.workingIntensity[2] + "|" 
                    + Storage.workingIntensity[3] + "|" 
                    + Storage.workingIntensity[4] + "|" + "%";
                
                Storage.sideLabel[6] = "Workout Type: " 
                + Storage.workoutType[0] + "|"
                + Storage.workoutType[1] + "|"
                + Storage.workoutType[2] + "|"
                + Storage.workoutType[3] + "|"
                + Storage.workoutType[4];
                break;
        }
        for (int i = 0; i < Storage.sideLabel.length; i++) {
            MainPage.getSide().getSideData(i).setText(Storage.sideLabel[i]);
        }
    }
}
