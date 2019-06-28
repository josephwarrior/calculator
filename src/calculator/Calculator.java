package calculator;

import java.awt.Container;

import java.awt.Font;

import java.awt.GridLayout;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JButton;

import javax.swing.JFrame;

import javax.swing.JPanel;

import javax.swing.JTextField;

enum Operation {
    ADD, SUBTRACT, MULTIPLY, DIVIDE, CLEAR
}

enum NewNumber {
    YES, NO
}

enum AlreadyDecimalDot {
    YES, NO
}

public class Calculator extends JFrame {

    private JTextField resultTextField;
    private JButton add, subtract, multiply, divide, compute, clear;
    private JButton numbers[];
    private Font font;
    double first = 0;
    double second = 0;

    AlreadyDecimalDot dot = AlreadyDecimalDot.NO;
    NewNumber number = NewNumber.YES;
    Operation o = Operation.ADD;

    class OperationListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            first = Double.parseDouble(resultTextField.getText());
            number = NewNumber.YES;
            dot = AlreadyDecimalDot.NO;

            if (e.getSource() == add) {
                o = Operation.ADD;
            }

            if (e.getSource() == subtract) {
                o = Operation.SUBTRACT;
            }

            if (e.getSource() == multiply) {
                o = Operation.MULTIPLY;
            }

            if (e.getSource() == divide) {
                o = Operation.DIVIDE;
            }

            if (e.getSource() == clear) {
                first = 0;
                second = 0;
                resultTextField.setText("0");
                o = Operation.CLEAR;
            }
        }
    }

    class ComputeListener implements ActionListener {

        @Override

        public void actionPerformed(ActionEvent e) {
            second = Double.parseDouble(resultTextField.getText());
            double result = 0;

            if (o == Operation.ADD) {
                result = first + second;
            }

            if (o == Operation.SUBTRACT) {
                result = first - second;
            }

            if (o == Operation.DIVIDE) {
                result = first / second;
            }

            if (o == Operation.MULTIPLY) {
                result = first * second;
            }

            if (o == Operation.CLEAR) {
                result = 0;
            }
            DecimalFormat df = new DecimalFormat("###.####");
            resultTextField.setText(df.format(result));
            number = NewNumber.YES;
            dot = AlreadyDecimalDot.NO;
        }

    }

    class KeysListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String previousInput;
            if (resultTextField.getText().equals("0") || number == NewNumber.YES) {
                previousInput = "";
            } else {
                previousInput = resultTextField.getText();
            }
            JButton button = (JButton) e.getSource();
            String buttonString = button.getText();

            if (buttonString.equals(".")) {
                if (dot==AlreadyDecimalDot.NO) {
                    dot = AlreadyDecimalDot.YES;
                }else{
                    buttonString="";
                }
                if (number == NewNumber.YES&&dot==AlreadyDecimalDot.NO) {
                    buttonString = "0.";
                }
            }
            resultTextField.setText(previousInput + buttonString);
            number = NewNumber.NO;

        }

    }

    public Calculator() {

        createComponents();

        setLayout();

        addComponents();

        addBehaviors();

        display();

    }

    private void addBehaviors() {

        ComputeListener cl = new ComputeListener();
        compute.addActionListener(cl);

        OperationListener ol = new OperationListener();
        add.addActionListener(ol);
        subtract.addActionListener(ol);
        multiply.addActionListener(ol);
        divide.addActionListener(ol);
        clear.addActionListener(ol);

        KeysListener kl = new KeysListener();
        for (int i = 0; i < numbers.length; i++) {
            numbers[i].addActionListener(kl);
        }

    }

    private void display() {
        setSize(600, 600);
        setVisible(true);
    }

    private void addComponents() {

        Container c = getContentPane();

        c.add(resultTextField);

        JPanel panel = new JPanel();
        panel.add(add);
        panel.add(subtract);
        panel.add(multiply);
        panel.add(divide);
        c.add(panel);

        panel = new JPanel();
        panel.add(compute);
        panel.add(clear);
        c.add(panel);

        JPanel p = new JPanel();
        p.add(numbers[1]);
        p.add(numbers[2]);
        p.add(numbers[3]);
        p.add(numbers[4]);
        c.add(p);

        p = new JPanel();
        p.add(numbers[5]);
        p.add(numbers[6]);
        p.add(numbers[7]);
        p.add(numbers[8]);
        c.add(p);

        p = new JPanel();
        p.add(numbers[9]);
        p.add(numbers[0]);
        p.add(numbers[10]);
        c.add(p);

    }

    private void setLayout() {

        GridLayout gl = new GridLayout(6, 1);

        Container c = this.getContentPane();

        c.setLayout(gl);

    }

    private void createComponents() {

        font = new Font("TimesRoman", Font.PLAIN, 36);

        resultTextField = new JTextField(10);
        resultTextField.setFont(font);

        add = new JButton("+");
        add.setFont(font);

        subtract = new JButton("-");
        subtract.setFont(font);

        multiply = new JButton("x");
        multiply.setFont(font);

        divide = new JButton("/");
        divide.setFont(font);

        compute = new JButton("=");
        compute.setFont(font);

        clear = new JButton("C");
        clear.setFont(font);

        numbers = new JButton[11];

        for (int i = 0; i < 10; i++) {

            numbers[i] = new JButton(i + "");

            numbers[i].setFont(font);

        }
        numbers[10] = new JButton(".");
        numbers[10].setFont(font);

    }

    public static void main(String[] args) {

        Calculator c = new Calculator();

    }

}
