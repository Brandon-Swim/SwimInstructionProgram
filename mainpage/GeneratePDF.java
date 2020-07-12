package mainpage;

import java.io.IOException;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

public class GeneratePDF {

  private static final String DEST = "C:\\Users\\Brandon\\.eclipse\\Practice_Builder\\PdfFiles\\";
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
    try {
      GeneratePDF test = new GeneratePDF();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  //TODO add File to contructor
  public GeneratePDF() throws IOException {
    //Creates the pdf Writer with destionation constructor
    PdfWriter writer = new PdfWriter("hello_world.pdf");
    
    PdfDocument pdf = new PdfDocument(writer);
    
    Document document = new Document(pdf);
    
    document.add(new Paragraph("Hello World!"));
    
    document.close();
  }

}
