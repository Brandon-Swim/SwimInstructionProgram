package mainpage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
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
  private File attachment;


  public static void main(String[] args) {
    EmailFile testFile = new EmailFile(new SwimWorkout());
    testFile.sendEmail();
  }

  public EmailFile() {
    recipient = "bchelstrom@wisc.edu";
    subjectLine = "Add Subject Line";
    body = "body";
    try {
      this.attachment = null;
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public EmailFile(File attachment) {
    this.attachment = attachment;
  }

  // TODO Temporary
  public EmailFile(SwimWorkout workout) {
    this();
    try {
      attachment = new File(DEST + "TestWorkout.txt");
      System.out.println(attachment.getName());
      FileOutputStream output = new FileOutputStream(attachment);
      PrintWriter fileOutput = new PrintWriter(output, true);
      fileOutput.println("This is sent to the file");
      fileOutput.flush();
      fileOutput.close();
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  public void sendEmail() {
    if (attachment == null) {
      System.out.println("No file to send");
      return;
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
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
      message.setSubject(subjectLine);

      // 3) create MimeBodyPart object and set your message text
      BodyPart messageBodyPart1 = new MimeBodyPart();
      messageBodyPart1.setText(body);

      // 4) create new MimeBodyPart object and set DataHandler object to this object
      MimeBodyPart messageBodyPart2 = new MimeBodyPart();
      
      String filename = attachment.getName();// change accordingly
      DataSource source = new FileDataSource("TxtFiles\\" + filename);
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
      System.out.println("message sent....");
    } catch (MessagingException ex) {
      ex.printStackTrace();
    }
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
