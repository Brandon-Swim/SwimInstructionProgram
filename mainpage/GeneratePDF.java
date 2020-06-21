package mainpage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import general.Storage;

public class GeneratePDF {

  private static final String DEST = "C:\\Users\\Brandon\\.eclipse\\Practice_Builder\\TxtFiles\\";
  private final String NAME = "Workout Name: ";
  private final String DATE = "Date: ";
  private final String DESCRIPTION = "Notes: ";
  private final String GROUPS = "Groups: ";
  private final String DISTANCE = "Total Distance: ";
  private final String TIME = "Total Time: ";
  private final String INTENSITY = "Total Intensity: ";
  private final String WDISTANCE = "Working Distance: ";
  private final String WTIME = "Working Time: ";
  private final String WINTENSITY = "Working Intensity: ";
  public static void main(String[] args) {
    new GeneratePDF();
  }

  public GeneratePDF() {
    
  }
  
  /**
   * Creates a text file with all releveant information for the pdf file
   * TODO update when settings page is created for specfic outputs
   */
  public void createTxtFile() {
    try {
      File infoFile =
          new File(DEST + "Workout-" + Storage.monthName + "." + Storage.dayName + ".txt");
      FileOutputStream  output = new FileOutputStream(infoFile);
      PrintWriter fileOutput = new PrintWriter(output, true);
      addHeaderInfo(fileOutput);
      addGroups(fileOutput);
      if (infoFile.createNewFile()) {
        System.out.println("File created: " + infoFile.getName());
      } else {
        System.out.println("File already exists.");
      }
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  private void addHeaderInfo(PrintWriter fileOutput) throws IOException {
    fileOutput.println(NAME + Storage.workoutName);
    fileOutput.println(DATE + Storage.monthName + "/" + Storage.dayName);
    fileOutput.println(DESCRIPTION + Storage.descriptionText);
    fileOutput.println(GROUPS + Storage.currentGroup);
  }
  
  private void addGroups(PrintWriter fileOutput) throws IOException {
    for (int i = 0; i < Storage.currentGroup; i++) {
      fileOutput.println("Group: " + (i + 1));
      fileOutput.println(DISTANCE + Storage.ttlDistanceAmts[i]);
      fileOutput.println(TIME + Storage.ttlTimeAmts[i]);
      fileOutput.println(INTENSITY + Storage.ttlIntensity[i]);
      fileOutput.println(WDISTANCE + Storage.workingDistanceAmts[i]);
      fileOutput.println(WTIME + Storage.workningTimeAmts[i]);
      fileOutput.println(WINTENSITY + Storage.workingIntensity[i] + "\n");
      for (int j = 0; j < Storage.getSet(i + 1).size(); j++) {
        System.out.println(j);
        fileOutput.print(Storage.getSet(i + 1).get(j));
      }
      fileOutput.flush();
    }
  }
}
