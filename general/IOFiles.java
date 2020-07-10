/*
 * contains functions to look up and save workouts to files
 */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
public class IOFiles {

	public static void saveWorkout(SwimWorkout toSave) {
		try {
			File data = new File("dataFile.txt");
			if(!data.exists()) {
				data.createNewFile();
				System.out.println("we created new file");
			}
			FileWriter fw = new FileWriter(data, true);
			fw.write(toSave.dataDump());
			fw.close();
			System.out.println("done writing to file");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
// testing some functions in IOfiles, Group, and SwimWorkout
//	public static void main(String[] args) {
//		SwimWorkout s = new SwimWorkout(7, 9, 2020, false);
//		s.getGroup(0).addRow(1, 2, 2, 200, "", Group.Type.Warm_Up, 1, 30, 50);
//		s.getGroup(0).addRow(1, 3, 3, 300, "", Group.Type.Warm_Up, 1, 30, 50);
//		s.getGroup(0).addRow(1, 4, 4, 400, "", Group.Type.Warm_Up, 1, 30, 50);
//		s.getGroup(0).addRow(1, 5, 5, 500, "", Group.Type.Warm_Up, 1, 30, 50);
//		s.getGroup(0).addRow(1, 6, 6, 600, "", Group.Type.Warm_Up, 1, 30, 50);
//		s.getGroup(0).addRow(1, 7, 7, 700, "", Group.Type.Warm_Up, 1, 30, 50);
//		System.out.println(s.getGroup(0).dataDump());
//		System.out.println();
//		System.out.println(s.dataDump());
//		saveWorkout(s);
//
//	}
}
