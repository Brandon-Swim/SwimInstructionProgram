package mainpage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import org.apache.log4j.BasicConfigurator;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.draw.DottedLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.UnitValue;

public class GeneratePDF {

  private static final String DEST = "C:\\Users\\Brandon\\.eclipse\\Practice_Builder\\PdfFiles\\";
  private static PdfWriter writer;
  private static PdfDocument pdf;
  private static Document document;


  public static void main(String[] args) {
    BasicConfigurator.configure();
    try {
      new GeneratePDF();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  // TODO add File to contructor
  public GeneratePDF() throws IOException {
    // Creates the pdf Writer with destionation constructor
    PdfWriter writer = new PdfWriter("PdfFiles//TEST.pdf");
    PdfDocument pdf = new PdfDocument(writer);
    Document document = new Document(pdf, PageSize.LETTER);
    document.setMargins(50f, 37.5f, 37.5f, 37.5f);
    PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
    PdfFont bold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
    Paragraph header = new Paragraph().setFontSize(18);
    header.add("Workout Title");
    header.setFont(bold);
    header.setHorizontalAlignment(HorizontalAlignment.LEFT);
    header.setMultipliedLeading(0);
    document.add(header);
    Paragraph headerInfo = new Paragraph().setFontSize(10);
    headerInfo.add("This is where the author, date, and season will go");
    headerInfo.setHorizontalAlignment(HorizontalAlignment.LEFT);
    headerInfo.addStyle(new Style().setFont(font).setFontColor(ColorConstants.GRAY));
    document.add(headerInfo);
    DottedLine seperator = new DottedLine();
    seperator.setGap(0);
    document.add(new LineSeparator(seperator));
    
    Paragraph Description = new Paragraph();
    Description.add("This is where the description of the workout will go");
    Description.setHorizontalAlignment(HorizontalAlignment.LEFT);
    Description.addStyle(new Style().setFont(font));
    document.add(Description);
    document.add(new LineSeparator(seperator));
    
    //run for the amount of groups
    for (int i = 0; i < 2; i++) {
      createTable(document, font, bold);
    }
    document.close();
  }

  private void createTable(Document doc, PdfFont font, PdfFont bold) throws IOException {
    Table infoTable = new Table(UnitValue.createPercentArray(new float[] {1, 2})).setWidth(200);
    infoTable.setHorizontalAlignment(HorizontalAlignment.LEFT);
    infoTable.addCell(
        new Cell().setBorder(Border.NO_BORDER).setFont(bold).add(new Paragraph("Group: ")));
    infoTable.addCell(
        new Cell().setBorder(Border.NO_BORDER).setFont(font).add(new Paragraph("Get Group Name")));
    
    infoTable.addCell(
        new Cell().setBorder(Border.NO_BORDER).setFont(bold).add(new Paragraph("Type: ")));
    infoTable.addCell(
        new Cell().setBorder(Border.NO_BORDER).setFont(font).add(new Paragraph("Get Type")));
    
    // should run for all of the provided information in the workout
    for (int i = 0; i < 3; i++) {
      infoTable.addCell(
          new Cell().setBorder(Border.NO_BORDER).setFont(bold).add(new Paragraph("Data: ")));
      infoTable.addCell(
          new Cell().setBorder(Border.NO_BORDER).setFont(font).add(new Paragraph("Data Value")));
    }
    infoTable.setVerticalBorderSpacing(0);
    
    Table table = new Table(UnitValue.createPercentArray(new float[] {2, 2, 2, 2, 10, 3, 2, 2, 2}))
        .setWidth(500);
    table.setHorizontalAlignment(HorizontalAlignment.CENTER);
    BufferedReader br = new BufferedReader(new FileReader("data.txt"));
    String line = br.readLine();
    process(table, line, font, bold, true);
    while ((line = br.readLine()) != null) {
      process(table, line, font, bold, false);
    }
    br.close();
    doc.add(infoTable);
    doc.add(table);
  }

  public void process(Table table, String line, PdfFont font, PdfFont bold, boolean isHeader) {
    StringTokenizer tokenizer = new StringTokenizer(line, ";");
    while (tokenizer.hasMoreTokens()) {
      if (isHeader) {
        String value = tokenizer.nextToken();
        if (!value.contentEquals("N/A")) {
          table.addHeaderCell(new Cell().add(new Paragraph(value).setFont(bold)));
        } else {
          table.addHeaderCell(new Cell().add(new Paragraph("").setFont(bold)));
        }
      } else {
        String value = tokenizer.nextToken();
        if (!value.contentEquals("N/A")) {
          table.addCell(new Cell().add(new Paragraph(value).setFont(font)));
        } else {
          table.addCell(new Cell().add(new Paragraph("").setFont(font)));
        }

      }
    }
  }

  public static PdfDocument createPdf(File file) throws IOException {
    String pdfName = findFileName(file.getName());
    writer = new PdfWriter("PdfFiles//" + pdfName);
    pdf = new PdfDocument(writer);
    document = new Document(pdf);
    document.add(new Paragraph("Implement Txt to workout converter"));
    document.close();
    return pdf;
  }

  private static String findFileName(String fileName) {
    String[] address = fileName.split("//");
    String[] rawAddress = address[address.length - 1].split(".txt");
    return rawAddress[0] + ".pdf";
  }

}
