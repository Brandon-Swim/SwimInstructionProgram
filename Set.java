import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Set {
    private final SimpleIntegerProperty setCol;
    private final SimpleIntegerProperty  roundsCol;
    private final SimpleIntegerProperty  repsCol;
    private final SimpleIntegerProperty  distanceCol;
    private final SimpleStringProperty  descriptionCol;;
    private final SimpleIntegerProperty  typeCol;  
    private final SimpleIntegerProperty  minCol;
    private final SimpleIntegerProperty  secCol;
    private final SimpleIntegerProperty  intensityCol;
    
    public Set(Integer col1, Integer col2, Integer col3, Integer col4, 
        String col5, Integer col6, Integer col7,Integer col8,Integer col9) {
        this.setCol = new SimpleIntegerProperty(col1);
        this.roundsCol = new SimpleIntegerProperty(col2);
        this.repsCol = new SimpleIntegerProperty(col3);
        this.distanceCol = new SimpleIntegerProperty(col4);
        this.descriptionCol = new SimpleStringProperty(col5);
        this.typeCol = new SimpleIntegerProperty(col6);
        this.minCol = new SimpleIntegerProperty(col7);
        this.secCol = new SimpleIntegerProperty(col8);
        this.intensityCol = new SimpleIntegerProperty(col9);
    }

        //Editing the Set Column
        public Integer getSet() {
            return setCol.get();
        }
        public void setSet(Integer col1) {
            setCol.set(col1);   
        }
        //Editing the Rounds Column
        public Integer getRounds() {
            return roundsCol.get();
        }
        public void setRounds(Integer col2) {
            roundsCol.set(col2);   
        }
        //Editing the Rep Column
        public Integer getReps() {
            return repsCol.get();
        }
        public void setReps(Integer col3) {
            repsCol.set(col3);
        }
        //Editing the Distance Column
        public Integer getDistance() {
            return distanceCol.get();
        }
        public void setDistance(Integer col4) {
            distanceCol.set(col4);
        }
        //Editing the Description Column
        public String getDescription() {
            return descriptionCol.get();
        }
        public void setDescription(String col5) {
            descriptionCol.set(col5);
        }
        //Editing the Integer Column
        public Integer getType() {
            return typeCol.get();
        }
        public void setType(Integer col6) {
            typeCol.set(col6);
        }
        //Editing the Minute Column
        public Integer getMinutes() {
            return minCol.get();
        }
        public void setMinutes(Integer col7) {
            minCol.set(col7);
        }
        //Editing the Seconds Column
        public Integer getSeconds() {
            return secCol.get();
        }
        public void setSeconds(Integer col8) {
            secCol.set(col8);
        }
        //Editing the Intensity Column
        public Integer getIntensity() {
            return intensityCol.get();
        }
        public void setIntensity(Integer col9) {
            intensityCol.set(col9);
        }
}
