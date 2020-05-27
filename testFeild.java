/*
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
 */
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.util.Random;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * @see https://stackoverflow.com/a/21267585/230513
 */
public class testFeild {

    private static final String ROW_KEY = "Values";
    private static final Random r = new Random();

    private void display() {
        JFrame f = new JFrame("Test");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final DefaultCategoryDataset model = new DefaultCategoryDataset();
        model.setValue(1, ROW_KEY, "1");
        model.setValue(2, ROW_KEY, "2");
        model.setValue(3, ROW_KEY, "3");
        JFreeChart chart = ChartFactory.createBarChart("Proxi", "Sensors",
            "Value", model, PlotOrientation.VERTICAL, false, true, false);
        ChartPanel barPanel = new ChartPanel(chart) {

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(320, 240);
            }
        };
        f.add(barPanel);
        f.add(new JButton(new AbstractAction("Update") {

            @Override
            public void actionPerformed(ActionEvent e) {
                model.setValue(r.nextDouble() * 3, ROW_KEY, "1");
            }
        }), BorderLayout.SOUTH);

        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new testFeild().display();
            }
        });
    }
}



