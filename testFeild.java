
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
        test.setMinimumSize(new Dimension(200,200));
        test.setLayout(new FlowLayout());
        test.setVisible(true);
        test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel Pane = new JPanel();
        Pane.setPreferredSize(new Dimension(200,700));
        Pane.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        Pane.setLayout(new FlowLayout());
        
        JTextField field1 = new JTextField("This is a Test");
        field1.setDragEnabled(true);
        field1.setPreferredSize(new Dimension(150,50));
        field1.setEditable(false);
        
        JTextField field2 = new JTextField("Test Goes Here");
        field2.setEnabled(true);
        field2.setPreferredSize(new Dimension(150,50));
        field2.setTransferHandler(new TransferHandler("text"));
        field2.setEditable(false);
        
        Pane.add(field1);
        Pane.add(field2);
        
        test.add(Pane);
        test.setSize(new Dimension(1650,800));
       
        
        
    }
    public static Object[] getColumn(Object[][] array, int index){
        Object[] column = new Object[array.length];
        for(int i = 0; i < column.length; i++){
               column[i] = array[i][index];
        }
        return column;
    }
    
    public static Object average(Object[] array) {
        Object sum = 0;
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] != null && 
                array[i].getClass().equals(String.class) && 
                array[i].toString().length() != 0) {
                sum = Integer.valueOf(sum.toString()) + 
                    Integer.valueOf(array[i].toString());
            }
        }
        return sum;
    }
}






