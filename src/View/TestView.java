package View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TestView extends JFrame{
    private JButton button1;
    private JButton button2;
    private JTextArea textArea;
    private JTextField textField;

    public TestView(){
        super();

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());

        add(mainPanel);

        textArea = new JTextArea();
        textArea.setEditable(false);

        textField = new JTextField();
        button1 = new JButton("Run Tests");
        button2 = new JButton("Clear");


        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        topPanel.add(button1, BorderLayout.EAST);
        topPanel.add(textField, BorderLayout.CENTER);
        centerPanel.add(textArea, BorderLayout.CENTER);
        bottomPanel.add(button2, BorderLayout.CENTER);


    }

    public void addRunTestListener(ActionListener listener) {
        button1.addActionListener(listener);
    }

    public void clearTextListener(ActionListener listener) {
        button2.addActionListener(listener);
    }

    public void clearText(){

        textArea.setText("");
    }

    public String getTextField(){

        return textField.getText();
    }

    public void addText(ArrayList<String> result){

        for(String s : result){

            textArea.append(s);
        }

    }
}
