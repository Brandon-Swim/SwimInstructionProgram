import javax.jws.soap.SOAPBinding.Style;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.io.*;
import java.awt.event.*;
import java.util.*;



public class finalHolder {
    static Scanner userInput;
    public static void main(String args[]) {
        MainPage program = new MainPage();
        program.PrepareGUI();
        WelcomeFrame introduction = new WelcomeFrame();
        introduction.Initialize();
        userInput = new Scanner(System.in);
        
}
}
