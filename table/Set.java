package table;
//import javafx.beans.property.SimpleIntegerProperty; not sure if switch back
import javafx.beans.property.SimpleStringProperty;

public class Set {
    private final SimpleStringProperty setCol;
    private final SimpleStringProperty  roundsCol;
    private final SimpleStringProperty  repsCol;
    private final SimpleStringProperty  distanceCol;
    private final SimpleStringProperty  descriptionCol;;
    private final SimpleStringProperty  typeCol;  
    private final SimpleStringProperty  minCol;
    private final SimpleStringProperty  secCol;
    private final SimpleStringProperty  intensityCol;
    
    public Set(String col1, String col2, String col3, String col4, 
        String col5, String col6, String col7, String col8,String col9) {
        this.setCol = new SimpleStringProperty(col1);
        this.roundsCol = new SimpleStringProperty(col2);
        this.repsCol = new SimpleStringProperty(col3);
        this.distanceCol = new SimpleStringProperty(col4);
        this.descriptionCol = new SimpleStringProperty(col5);
        this.typeCol = new SimpleStringProperty(col6);
        this.minCol = new SimpleStringProperty(col7);
        this.secCol = new SimpleStringProperty(col8);
        this.intensityCol = new SimpleStringProperty(col9);
    }
        
    
        public String printSet() {
            String temp = "";
            temp += "Set: " + setCol.get() + "\n";
            temp += "Round: " + roundsCol.get() + "\n";
            temp += "Rep: " + repsCol.get() + "\n";
            temp += "Distance: " + distanceCol.get() + "\n";
            temp += "Description: " + descriptionCol.get() + "\n";
            temp += "Type: " + typeCol.get() + "\n";
            temp += "Min: " + minCol.get() + "\n";
            temp += "Sec: " + secCol.get() + "\n";
            temp += "Intensity: " + intensityCol.get() + "\n";
            System.out.println(temp);
            return temp;
        }
        //Editing the Set Column
        public String getSetCol() {
            return setCol.get();
        }
        public void setSetCol(String col1) {
            setCol.set(col1);   
        }
        //Editing the Rounds Column
        public String getRoundsCol() {
            return roundsCol.get();
        }
        public void setRoundsCol(String col2) {
            roundsCol.set(col2);   
        }
        //Editing the Rep Column
        public String getRepsCol() {
            return repsCol.get();
        }
        public void setRepsCol(String col3) {
            repsCol.set(col3);
        }
        //Editing the Distance Column
        public String getDistanceCol() {
            return distanceCol.get();
        }
        public void setDistanceCol(String col4) {
            distanceCol.set(col4);
        }
        //Editing the Description Column
        public String getDescriptionCol() {
            return descriptionCol.get();
        }
        public void setDescriptionCol(String col5) {
            descriptionCol.set(col5);
        }
        //Editing the Integer Column
        public SimpleStringProperty typeProperty() {
            return this.typeCol;
        }

        public String getTypeCol() {
            return this.typeProperty().get();
        }

        public void setTypeCol(final String type) {
            this.typeProperty().set(type);
        }
        //Editing the Minute Column
        public String getMinCol() {
            return minCol.get();
        }
        public void setMinCol(String col7) {
            minCol.set(col7);
        }
        //Editing the Seconds Column
        public String getSecCol() {
            return secCol.get();
        }
        public void setSecCol(String col8) {
            secCol.set(col8);
        }
        //Editing the Intensity Column
        public String getIntensityCol() {
            return intensityCol.get();
        }
        public void setIntensityCol(String col9) {
            intensityCol.set(col9);
        }
}
