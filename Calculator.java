import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {
    JTextField display;
    String operator = "";
    double num1 = 0, num2 = 0;

    Calculator() {
        setTitle("Hesap Makinesi");
        setSize(300, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 

        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 24));
        add(display, BorderLayout.NORTH);

        // Buton paneli
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 5, 5));

        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "C", "0", "=", "+"
        };

        for (String text : buttons) {
            JButton btn = new JButton(text);
            btn.setFont(new Font("Arial", Font.BOLD, 20));
            btn.addActionListener(this);
            panel.add(btn);
        }

        add(panel);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (cmd.charAt(0) >= '0' && cmd.charAt(0) <= '9') {
            display.setText(display.getText() + cmd);
        } else if (cmd.equals("C")) {
            display.setText("");
            num1 = 0;
            num2 = 0;
            operator = "";
        } else if (cmd.equals("=")) {
            num2 = Double.parseDouble(display.getText());
            double result = 0;

            switch (operator) {
                case "+": result = num1 + num2; break;
                case "-": result = num1 - num2; break;
                case "*": result = num1 * num2; break;
                case "/": 
                    if (num2 == 0) {
                        display.setText("Tanımsız");
                        return;
                    }
                    result = num1 / num2;
                    break;
            }

            display.setText(String.valueOf(result));
            operator = "";
        } else {
            // işlem operatörü tıklanmışsa
            operator = cmd;
            num1 = Double.parseDouble(display.getText());
            display.setText("");
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
