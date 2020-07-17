package mainpage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import com.itextpdf.kernel.pdf.PdfDocument;
import background.SwimWorkout;
import javax.activation.*;


// Email: ProjectBuilder57@gmail.com
// password: 3#5aQ%95V1re29&na
public class EmailFile {
  private static final String DEST = "C:\\Users\\Brandon\\.eclipse\\Practice_Builder\\TxtFiles\\";
  private static String recipient;
  private static final String user = "ProjectBuilder57@gmail.com";
  private static final String password = "3#5aQ%95V1re29&na";
  private static final String host = "smtp.gmail.com";

  private String subjectLine;
  private String body;
  private File attachmentTxt;
  private PdfDocument attachmentPdf;


  public EmailFile() {
    recipient = "bchelstrom@wisc.edu";
    subjectLine = "Add Subject Line";
    body = "body";
    try {
      this.attachmentTxt = null;
      this.attachmentPdf = null;
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public EmailFile(File attachment) {
    this();
    this.attachmentTxt = attachment;
    try {
      this.attachmentPdf = GeneratePDF.createPdf(this.attachmentTxt);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public boolean sendEmail() {
    if (attachmentTxt == null || attachmentPdf == null) {
      System.out.println(
          "No file to send. Txt: " + (attachmentTxt == null) + ", Pdf: " + (attachmentPdf == null));
      return false;
    }
    // 1) get the session object
    Properties properties = System.getProperties();
    properties.put("mail.smtp.host", host);
    properties.put("mail.smtp.port", "465");
    properties.put("mail.smtp.ssl.enable", "true");
    properties.put("mail.smtp.auth", "true");

    Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(user, password);
      }
    });

    // 2) compose message
    try {
      MimeMessage message = new MimeMessage(session);
      message.setFrom(new InternetAddress(user));
      message.addRecipient(Message.RecipientType.TO
          , new InternetAddress(recipient));
      message.setSubject(subjectLine);

      // 3) create MimeBodyPart object and set your message text
      BodyPart messageBodyPart1 = new MimeBodyPart();
      messageBodyPart1.setText(body);

      // 4) create new MimeBodyPart object and set DataHandler object to this object
      MimeBodyPart messageBodyPart2 = new MimeBodyPart();

      String filename = findFileName(attachmentTxt.getName());// change accordingly
      DataSource source = new FileDataSource("PdfFiles\\" + filename);
      messageBodyPart2.setDataHandler(new DataHandler(source));
      messageBodyPart2.setFileName(filename);


      // 5) create Multipart object and add MimeBodyPart objects to this object
      Multipart multipart = new MimeMultipart();
      multipart.addBodyPart(messageBodyPart1);
      multipart.addBodyPart(messageBodyPart2);

      // 6) set the multiplart object to the message object
      message.setContent(multipart);

      // 7) send message
      Transport.send(message);
      return true;
    } catch (MessagingException ex) {
      ex.printStackTrace();
      return false;
    }
  }
 
  private static String findFileName(String fileName) {
    String[] address = fileName.split("//");
    String[] rawAddress = address[address.length - 1].split(".txt");
    return rawAddress[0] + ".pdf";
  }

  public void setSubjectLine(String str) {
    subjectLine = str;
  }

  public void addToSubjectLine(String str) {
    subjectLine += str;
  }

  public void clearSubjectLine() {
    subjectLine = null;
  }

  public String getSubjectLine() {
    return subjectLine;
  }

  public void setBody(String str) {
    body = str;
  }

  public void addToBody(String str) {
    body += str;
  }

  public void clearBody() {
    body = null;
  }

  public String getRecipient() {
    return recipient;
  }

  public void setRecipient(String str) {
    recipient = str;
  }
}
