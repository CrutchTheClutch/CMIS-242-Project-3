package Sequence;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;

/**
 * File: Sequence.App.java
 * Author: William Crutchfield
 * Date: February 18, 2017
 * Description: This program allows you to calculate the sequence of (n-1)*2 + (n-2) both Iteratively and Recursively
 */
public class App extends JFrame {

    // Variables
    private int n;
    private int result;
    private int efficiency;

    /**
     * Constructs the Sequence.App for the program
     */
    private App() {
        // Set Title
        super("Project 3");

        // Create Main Panel
        JPanel main = new JPanel();

        // Set Layout
        main.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // Create Components
        JRadioButton iterativeRBtn = new JRadioButton("Iterative");
        JRadioButton recursiveRBtn = new JRadioButton("Recursive");
        JLabel inputLabel = new JLabel("Enter n:");
        JTextField inputTxt = new JTextField("");
        JButton computeBtn = new JButton("Compute");
        JLabel resultLabel = new JLabel("Result:");
        JTextField resultTxt = new JTextField("");
        JLabel efficiencyLabel = new JLabel("Efficiency:");
        JTextField efficiencyTxt = new JTextField("");

        // Set Editable
        resultTxt.setEditable(false);
        efficiencyTxt.setEditable(false);

        // Add Components
        c.fill = GridBagConstraints.HORIZONTAL;

        c.insets = new Insets(0,5,5,0);
        c.gridx = 1;
        c.gridy = 0;
        main.add(iterativeRBtn, c);

        c.insets = new Insets(5,5,5,0);
        c.gridx = 1;
        c.gridy = 1;
        main.add(recursiveRBtn, c);

        c.insets = new Insets(5,0,5,5);
        c.gridx = 0;
        c.gridy = 2;
        main.add(inputLabel, c);

        c.insets = new Insets(5,5,5,0);
        c.gridx = 1;
        c.gridy = 2;
        main.add(inputTxt, c);

        c.insets = new Insets(5,5,5,0);
        c.gridx = 1;
        c.gridy = 3;
        main.add(computeBtn, c);

        c.insets = new Insets(5,0,5,5);
        c.gridx = 0;
        c.gridy = 4;
        main.add(resultLabel, c);

        c.insets = new Insets(5,5,5,0);
        c.gridx = 1;
        c.gridy = 4;
        main.add(resultTxt, c);

        c.insets = new Insets(5,0,0,5);
        c.gridx = 0;
        c.gridy = 5;
        main.add(efficiencyLabel, c);

        c.insets = new Insets(5,5,0,0);
        c.gridx = 1;
        c.gridy = 5;
        main.add(efficiencyTxt, c);

        add(main);

        // Action Event Handlers
        computeBtn.addActionListener(e -> {
            try {
                n = Integer.parseInt(inputTxt.getText());
                if (iterativeRBtn.isSelected()) {
                    result = Sequence.computeIterative(n);
                    efficiency = Sequence.getEfficiency();
                } else {
                    result = Sequence.computeRecursive(n);
                    efficiency = Sequence.getEfficiency();
                }
                resultTxt.setText("" + result);
                efficiencyTxt.setText("" + efficiency);
            } catch (NumberFormatException d) {
                JOptionPane.showMessageDialog(null, "Error! You failed to enter a number!");
            }
        });

        // Exit Handler
        new write();

        // Radio Button Group
        ButtonGroup group = new ButtonGroup();
        group.add(iterativeRBtn);
        group.add(recursiveRBtn);

        // Set Default Radio Button
        iterativeRBtn.doClick();

        // JFrame Settings
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(250, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    /**
     * Write data to a file upon Exit
     */
    class write extends WindowAdapter {
        write() {
            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e){
                    try {
                        FileWriter writer = new FileWriter("efficiency-values");

                        writer.append("n");
                        writer.append(",");
                        writer.append("Iterative");
                        writer.append(",");
                        writer.append("Recursive");
                        writer.append(",\n");

                        for (n = 0; n <= 10; n++) {
                            writer.append(Integer.toString(n));
                            writer.append(",");
                            Sequence.computeIterative(n);
                            writer.append(String.valueOf(Integer.toString(Sequence.getEfficiency())));
                            writer.append(",");
                            Sequence.computeRecursive(n);
                            writer.append(String.valueOf(Integer.toString(Sequence.getEfficiency())));
                            writer.append(",\n");
                        }

                        writer.flush();
                        writer.close();

                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            });
        }
    }

    /**
     * Creates the Sequence.App
     * @param args  :   arguments (does not effect program)
     */
    public static void main(String[] args) {
        new App();
    }
}