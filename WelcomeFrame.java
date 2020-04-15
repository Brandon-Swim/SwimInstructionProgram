/*
 * Displays Welcome message, contains file reader method
 * 
 */

import javax.jws.soap.SOAPBinding.Style;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.io.*;
import java.awt.event.*;

public class WelcomeFrame {
    JFrame introPage;
    JLabel mainHeader1;
    JLabel mainText1;
    JButton mainButton1;
    final Font header = new Font("Arial", Font.BOLD, 80);
    final Font body = new Font("Arial", Font.PLAIN, 24);
    
    ActionListener mainButton1Press = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            introPage.setVisible(false);
            MainPage.mainWindow.setVisible(true);
            introPage.dispose();
        }
  };

    
    
    public WelcomeFrame() {
        introPage = new JFrame("Swim Program");
        introPage.setSize(1000,800);
        introPage.setLayout(null);
        introPage.setLocationRelativeTo(null);
        introPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String arg[]) {
        //Test:
        //WelcomeFrame Intro = new WelcomeFrame();
        //Intro.Initialize();
    }
    
    public void Initialize() {
        mainHeader1 = new JLabel("Welcome");
        mainHeader1.setFont(header);
        mainHeader1.setHorizontalAlignment(SwingConstants.CENTER);
        mainHeader1.setVerticalAlignment(SwingConstants.CENTER);
        mainHeader1.setSize(400,100);
        mainHeader1.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        mainHeader1.setLocation(300,0);
        mainHeader1.setForeground(Color.red);
        
        mainText1 = new JLabel("<html>" + fileReader("WelcomeText.txt") + "</html>");
        mainText1.setFont(body);
        mainText1.setSize(500,400);
        mainText1.setBorder(BorderFactory.createLineBorder(Color.black,1));
        mainText1.setLocation(250,150);
        mainText1.setForeground(Color.DARK_GRAY);
        
        mainButton1 = new JButton("Click to Continue");
        mainButton1.setSize(300,100);
        mainButton1.setLocation(350,600);
        mainButton1.addActionListener(mainButton1Press);
        
        introPage.add(mainHeader1);
        introPage.add(mainText1);
        introPage.add(mainButton1);
        introPage.setVisible(true);
    }
    

    public static String fileReader(String fileName) {
        String fileContents = "";
        File inputFile = new File(fileName);
        try {
            Scanner fileInput = new Scanner(inputFile);
            while (fileInput.hasNextLine()) {
                fileContents = fileContents + fileInput.nextLine();
                }
            fileInput.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return fileContents;
    }
    
}
