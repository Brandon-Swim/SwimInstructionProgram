package background;

/*
 * contains functions to look up and save workouts to files
 */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class IOFiles {

  public static boolean saveWorkout(SwimWorkout toSave) {
    try {
      File data = new File("TxtFiles//dataFile.txt");
      if(!data.exists()) {
        data.createNewFile();
        //System.out.println("we created new file");
      }
      FileWriter fw = new FileWriter(data, true);
      fw.write(toSave.dataDump() + "EOW\n");
      fw.close();
      return true;
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return false;

  }
  
  public static boolean saveDate(SwimWorkout toSave) {
    try {
      File date = new File("TxtFiles//dates.txt");
      if(!date.exists()) {
        date.createNewFile();
        //System.out.println("we created new file");
      }
      FileWriter fw = new FileWriter(date, true);
      //Formats dates
      int l1 = (int)(Math.log10(toSave.getMonth())+1);
      int l2 = (int)(Math.log10(toSave.getMonth())+1);
      if (l1 == 0 && l2 == 0) {
        fw.write("00/00\n");
      } else if (l1 == 0 && l2 == 1) {
        fw.write("00/0" + toSave.getDay() + "\n");
      } else if (l1 == 1 && l2 == 0) {
        fw.write("0" + toSave.getMonth() + "/00\n");
      } else if (l1 == 1 && l2 == 1) {
        fw.write("0" + toSave.getMonth() + "/0" + toSave.getDay() + "\n");
      } else if (l1 == 2 && l2 == 1) {
        fw.write(toSave.getMonth() + "/0" + toSave.getDay() + "\n");
      } else if (l1 == 1 && l2 == 2) {
        fw.write("0" + toSave.getMonth() + "/" + toSave.getDay() + "\n");
      } else if (l1 == 2 && l2 == 2) {
        fw.write(toSave.getMonth() + "/" + toSave.getDay() + "\n");
      } else {
        fw.write("13/32\n");
      }
      fw.close();
      return true;
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return false;
  }
  
  public static boolean saveToFiles(SwimWorkout toSave) {
    return saveDate(toSave) && saveWorkout(toSave);
  }
  
  public static File readableWorkout(SwimWorkout toSave) {
    try {
      String filename = "TxtFiles//workout." + toSave.getMonth() + "." + toSave.getDay() + "." + toSave.getYear() + ".txt";
      File data = new File(filename);
      if(!data.exists()) {
        data.createNewFile();
        //System.out.println("we created new file");
      }
      FileWriter fw = new FileWriter(data);
      fw.write(toSave.toString());
      fw.close();
      return data;
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
  }
// testing some functions in IOfiles, Group, and SwimWorkout
  public static void main(String[] args) {
    SwimWorkout s = new SwimWorkout(7, 9, 2020, false);
    s.getGroup(0).addRow(1, 2, 2, 200, "", Group.defaultTypes[0], 1, 30, 50);
    s.getGroup(0).addRow(1, 0, 3, 300, "", Group.defaultTypes[0], 1, 30, 50);
    s.getGroup(0).addRow(1, 0, 4, 400, "", Group.defaultTypes[0], 1, 30, 50);
    s.getGroup(0).addRow(1, 0, 5, 500, "", Group.defaultTypes[0], 1, 30, 50);
    s.getGroup(0).addRow(1, 0, 6, 600, "", Group.defaultTypes[0], 1, 30, 50);
    s.getGroup(0).addRow(1, 0, 7, 700, "", Group.defaultTypes[0], 1, 30, 50);
    saveToFiles(s);
    readableWorkout(s);
    
    SwimWorkout s2 = new SwimWorkout(7, 10, 2020, false);
    s2.getGroup(0).addRow(1, 2, 2, 100, "", Group.defaultTypes[0], 1, 30, 50);
    s2.getGroup(0).addRow(1, 0, 3, 300, "", Group.defaultTypes[0], 1, 30, 80);
    s2.getGroup(0).addRow(1, 0, 4, 50, "", Group.defaultTypes[0], 1, 30, 50);
    s2.getGroup(0).addRow(1, 0, 5, 500, "", Group.defaultTypes[4], 1, 30, 69);
    s2.getGroup(0).addRow(1, 0, 6, 400, "", Group.defaultTypes[4], 2, 30, 50);
    s2.getGroup(0).addRow(1, 0, 7, 300, "", Group.defaultTypes[3], 3, 30, 70);
    saveToFiles(s2);
    readableWorkout(s2);
    
    SwimWorkout s3 = new SwimWorkout(7, 12, 2020, false);
    s3.getGroup(0).addRow(1, 2, 2, 100, "", Group.defaultTypes[0], 1, 30, 50);
    s3.getGroup(0).addRow(1, 0, 3, 300, "", Group.defaultTypes[0], 1, 30, 80);
    s3.getGroup(0).addRow(1, 0, 4, 50, "", Group.defaultTypes[0], 1, 30, 50);
    s3.getGroup(0).addRow(2, 2, 5, 100, "", Group.defaultTypes[4], 6, 30, 100);
    s3.getGroup(0).addRow(2, 0, 6, 100, "", Group.defaultTypes[4], 2, 30, 50);
    s3.getGroup(0).addRow(2, 0, 7, 200, "", Group.defaultTypes[3], 3, 30, 70);
    saveToFiles(s3);
    readableWorkout(s3);
    
//    System.out.println(s.getGroup(0).dataDump());
//    System.out.println();
//    System.out.println(s.dataDump());
  }
}