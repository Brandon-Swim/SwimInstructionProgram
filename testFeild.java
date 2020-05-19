import javax.jws.soap.SOAPBinding.Style;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.table.*;
import javax.swing.event.*;


public class testFeild {
    
    public static void main(String args[]) {
        JFrame test = new JFrame();
        test.setSize(new Dimension(1650,800));
        test.setMinimumSize(new Dimension(200,200));
        test.setLayout(new BorderLayout());
        test.setVisible(true);
        test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel Pane = new JPanel();
        JTable table1 =  new JTable(100,10);
        JTable table2 = new JTable(100,10);
        Pane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        table1.setBorder(BorderFactory.createLineBorder(Color.green, 2));
        table2.setBorder(BorderFactory.createLineBorder(Color.green, 2));
        Pane.setLayout(new FlowLayout());
        Pane.add(table1);
        
        JPanel Pane2 = new JPanel();
        Pane2.setLayout(new BorderLayout());
        JButton but1 = new JButton("Add Row");
        but1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Pane.add(table2);
            }
        });
        Pane2.add(but1, BorderLayout.CENTER);
        
        JScrollPane Over = new JScrollPane(Pane);
        Over.setBorder(BorderFactory.createLineBorder(Color.red, 2));
        test.add(Over);
        //test.add(Pane2);
       
        
        
    }
}
    



