package inpracpage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import background.Group;
import background.SwimWorkout;

public class XlsConverter {
  private static File xls;
  private static FileInputStream input;
  private static XSSFWorkbook workbook;

  // Process: Sheet iterator -> Row iterator -> Cell iterator
  public XlsConverter() {
    XSSFWorkbook workbook;
    try {
      File xls = new File("XlsFiles//Swim_Workout_Examples.xlsx");
      FileInputStream input = new FileInputStream(xls);
      workbook = new XSSFWorkbook(input);
      XSSFSheet sheet = workbook.getSheetAt(0);
      Iterator<Row> iterator = sheet.iterator();
      while (iterator.hasNext()) {
        Row row = iterator.next();
        Iterator<Cell> cellIterator = row.cellIterator();
        while (cellIterator.hasNext()) {
          Cell cell = cellIterator.next();
          switch (cell.getCellTypeEnum()) {
            case BLANK:
              System.out.print("");
              break;
            case BOOLEAN:
              break;
            case ERROR:
              break;
            case FORMULA:
              break;
            case NUMERIC:
              System.out.print((int) cell.getNumericCellValue() + " ");
              break;
            case STRING:
              System.out.print(cell.getStringCellValue() + " ");
              break;
            case _NONE:
              break;
            default:
              break;
          }
        }
        System.out.println("");
      }
      workbook.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static ArrayList<SwimWorkout> XlsToSwimWorkout(File file) throws IOException {
    ArrayList<SwimWorkout> workouts = new ArrayList<SwimWorkout>();
    xls = file;
    input = new FileInputStream(xls);
    workbook = new XSSFWorkbook(input);
    int sheetIndex = 0;
    while (hasNextSheet(sheetIndex)) {
      SwimWorkout workout = new SwimWorkout();
      XSSFSheet sheet = workbook.getSheetAt(sheetIndex);
      sheetIndex++; // TODO fix
      workout.setName(ReadStringCellData(0, 0, sheet));
      workout.setDay(ReadIntCellData(1, 4, sheet));
      workout.setDescription(ReadStringCellData(2, 1, sheet));
      // TODO add group names to One group Template and example
      // Use for running through table
      Iterator<Row> rowIterator = sheet.iterator();
      int tableStart = 0;
      int currentGroup = 0;
      ArrayList<Group> group = new ArrayList<Group>();
      group.add(new Group());
      int runtime = 0;
      while (rowIterator.hasNext()) {
        // 5 is the start of the first row of the table
        if (tableStart < 5) {
          tableStart++;
          // Advances the iterator
          Row row = rowIterator.next();
          continue;
        }
        // First Row of the Group
        Row row = rowIterator.next();
        Iterator<Cell> cellIterator = row.cellIterator();
        ArrayList<Object> rowValues = new ArrayList<Object>();
        while (cellIterator.hasNext()) {
          Cell cell = cellIterator.next();
          switch (cell.getCellTypeEnum()) {
            case BLANK:
              rowValues.add(0);
              break;
            case BOOLEAN:
              break;
            case ERROR:
              break;
            case FORMULA:
              break;
            case NUMERIC:
              rowValues.add((int) cell.getNumericCellValue());
              break;
            case STRING:
              rowValues.add(cell.getStringCellValue());
              break;
            case _NONE:
              break;
            default:
              break;
          }
        } //End of Cell Iterator
        if (checkEndRow(rowValues)) {
          currentGroup++;
          group.add(new Group());
          row = rowIterator.next();
          row = rowIterator.next();
          continue;
        }
        addRow(rowValues, group.get(currentGroup));
      } //End of Row iterator
      for (int i = 0; i < group.size(); i++) {
        workout.addGroup(group.get(i));
      }
      workouts.add(workout);
    } //End of Sheet iterator
    return workouts;
  }

  private static boolean checkEndRow(ArrayList<Object> values) {
    int counter = 0;
    for (int i = 0; i < values.size(); i++) {
      if (values.get(i).equals(0)) {
        counter++;
      }
    }
    if (counter == 9) {
      return true;
    }
    return false;
  }
  
  private static void addRow(ArrayList<Object> values, Group group) {
    try {
      group.addRow((int) values.get(0), (int) values.get(1), (int) values.get(2), (int) values.get(3),
          (String) values.get(4), (String) values.get(5), (int) values.get(6), (int) values.get(7),
          (int) values.get(8));
    } catch (ClassCastException e) {
      for (int i = 0; i < values.size(); i++) {
        System.out.println(values.get(i));
      }
      System.out.println(group);
      e.printStackTrace();
      System.exit(0);
    }

  }

  public static String ReadStringCellData(int Row, int Column, XSSFSheet sheet) {
    Row row = sheet.getRow(Row); // returns the logical row
    Cell cell = row.getCell(Column); // getting the cell representing the given column
    String value = cell.getStringCellValue(); // getting cell value
    return value; // returns the cell value
  }

  public static int ReadIntCellData(int Row, int Column, XSSFSheet sheet) {
    Row row = sheet.getRow(Row); // returns the logical row
    Cell cell = row.getCell(Column); // getting the cell representing the given column
    int value = (int) cell.getNumericCellValue(); // getting cell value
    return value; // returns the cell value
  }

  private static boolean hasNextSheet(int sheetIndex) {
    try {
      workbook.getSheetAt(sheetIndex);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public static void main(String[] args) throws IOException {
    File file = new File("XlsFiles//Swim_Workout_Examples.xlsx");
    XlsConverter.XlsToSwimWorkout(file);
  }

}
