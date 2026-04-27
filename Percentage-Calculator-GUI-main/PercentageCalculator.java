import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PercentageCalculator extends JFrame {
    
    private JTextField number1Field, number2Field, resultField;
    private JComboBox<String> operationBox;
    private JButton calculateButton;

    public PercentageCalculator() {
        setTitle("Percentage Calculator");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        
        JLabel label1 = new JLabel("Enter First Number:");
        label1.setBounds(20, 30, 150, 30);
        add(label1);

        number1Field = new JTextField();
        number1Field.setBounds(170, 30, 150, 30);
        add(number1Field);

        JLabel label2 = new JLabel("Enter Second Number:");
        label2.setBounds(20, 70, 150, 30);
        add(label2);

        number2Field = new JTextField();
        number2Field.setBounds(170, 70, 150, 30);
        add(number2Field);

        String[] operations = {"Percentage", "Increase/Decrease", "Find Whole"};
        operationBox = new JComboBox<>(operations);
        operationBox.setBounds(170, 110, 150, 30);
        add(operationBox);

        calculateButton = new JButton("Calculate");
        calculateButton.setBounds(150, 160, 100, 30);
        add(calculateButton);

        JLabel resultLabel = new JLabel("Result:");
        resultLabel.setBounds(20, 200, 100, 30);
        add(resultLabel);

        resultField = new JTextField();
        resultField.setBounds(170, 200, 150, 30);
        resultField.setEditable(false);
        add(resultField);

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculate();
            }
        });
    }

    private void calculate() {
        try {
            double number1 = Double.parseDouble(number1Field.getText());
            double number2 = Double.parseDouble(number2Field.getText());
            String operation = (String) operationBox.getSelectedItem();
            double result = 0;

            switch (operation) {
                case "Percentage":
                    result = (number1 / number2) * 100;
                    break;
                case "Increase/Decrease":
                    result = ((number2 - number1) / number1) * 100;
                    break;
                case "Find Whole":
                    result = (number1 * 100) / number2;
                    break;
            }

            resultField.setText(String.format("%.2f", result));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid Input! Please enter numbers.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "An error occurred: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        PercentageCalculator calculator = new PercentageCalculator();
        calculator.setVisible(true);
    }
}
