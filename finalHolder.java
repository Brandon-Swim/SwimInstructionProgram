import javax.swing.*;
import java.awt.*;


//Creates GUI 
public class finalHolder {
    static JFrame mainWindow = new JFrame("Swim Program");
    public static void main(String args[]) {
        //Welcome Frame
        WelcomeFrame introduction = new WelcomeFrame();
        introduction.Initialize();
        
        //Main page Setup
        //Frame Setup
        mainWindow.setMinimumSize(new Dimension(1650,800));
        mainWindow.setLayout(new BorderLayout());
        mainWindow.setVisible(false);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Tab Setup
        MainPage program = new MainPage();  
        SettingsPage settings = new SettingsPage();
        PracticeArchieve archieve = new PracticeArchieve();
        ImportedPractices user = new ImportedPractices();
        JTabbedPane tabs = new JTabbedPane();
        tabs.setPreferredSize(new Dimension(1000,800));
        program.PrepareGUI();
        settings.PrepareGUI();
        archieve.PrepareGUI();
        user.PrepareGUI();
        tabs.add("Practice Builder", MainPage.mainScrollArea);   //TODO figure out 
        tabs.add("Practice Archieve", PracticeArchieve.practiceArchieveTab);
        tabs.add("Imported Practices", ImportedPractices.importTab);
        tabs.add("Settings", SettingsPage.settingsTab);
        mainWindow.getContentPane().add(tabs, BorderLayout.CENTER); 
        mainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
}
