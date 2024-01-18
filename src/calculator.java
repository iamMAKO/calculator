import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class calculator implements ActionListener, KeyListener {

    JFrame frame;
    JTextField textField;
    JButton[] numberButtons = new JButton[10];
    JButton[] actionButtons = new JButton[10];
    JButton addButton, subButton, multButton, divButton, deciButton, equButton, delButton, cButton, negButton, percButton;
    JPanel panel;

    Font fontButton = new Font(null, Font.BOLD, 15 );
    Font fontText = new Font(null, Font.PLAIN, 30);
    double num1 = 0, num2 = 0, res = 0;
    char operator;
    calculator(){
        //Set Frame
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,530);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setFocusable(true);
        frame.addKeyListener(this);

        //Set TextField
        textField = new JTextField();
        textField.setBounds(20,20,360,100);
        textField.setFont(fontText);
        textField.setEditable(false);
        textField.setBackground(Color.BLACK);
        textField.setForeground(Color.WHITE);
//        textField.setCaret();

        //Add and set Buttons
        addButton = new JButton("+");
        subButton = new JButton("-");
        multButton= new JButton("*");
        divButton = new JButton("/");
        deciButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("DEL");
        cButton = new JButton("C");
        negButton = new JButton("+/-");
        percButton = new JButton("%");

        actionButtons[0] = addButton;
        actionButtons[1] = subButton;
        actionButtons[2] = multButton;
        actionButtons[3] = divButton;
        actionButtons[4] = deciButton;
        actionButtons[5] = equButton;
        actionButtons[6] = delButton;
        actionButtons[7] = cButton;
        actionButtons[8] = negButton;
        actionButtons[9] = percButton;

        for(int i = 0; i < 10; i++){
            actionButtons[i].addActionListener(this);
            actionButtons[i].setFont(fontButton);
            actionButtons[i].setFocusable(false);
            actionButtons[i].setForeground(Color.WHITE);
            actionButtons[i].setOpaque(true);
            actionButtons[i].setBackground(Color.ORANGE);
            actionButtons[i].setBorderPainted(false);
        }

        for(int i = 0; i < 10; i++){
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(fontButton);
            numberButtons[i].setFocusable(false);
            numberButtons[i].setBackground(new Color(0x444444));
            numberButtons[i].setForeground(Color.WHITE);
            numberButtons[i].setOpaque(true);
            numberButtons[i].setBorderPainted(false);
        }

        //set Panel and add buttons to panel
        panel = new JPanel();
        panel.setBounds(20, 140, 360, 340);
        panel.setLayout(new GridLayout(5, 4, 10, 10));
        panel.setBackground(Color.BLACK);

        panel.add(actionButtons[7]);
        panel.add(actionButtons[6]);
        panel.add(actionButtons[9]);
        panel.add(actionButtons[3]);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(actionButtons[2]);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(actionButtons[1]);
        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(actionButtons[0]);
        panel.add(numberButtons[0]);
        panel.add(actionButtons[8]);
        panel.add(actionButtons[4]);
        panel.add(actionButtons[5]);

        frame.add(panel);
        frame.add(textField);
        frame.setVisible(true);
    }


    //Calculator functions
    @Override
    public void actionPerformed(ActionEvent e) {

        for(int i = 0; i < 10; i++){
            if(e.getSource() == numberButtons[i]) {
                textField.setText(textField.getText().concat(String.valueOf(i)));
            }
        }

        if(e.getSource() == deciButton) {
            if (deciButton.isEnabled()) {
                textField.setText(textField.getText().concat("."));
                deciButton.setEnabled(false);
            }
        }

        if(e.getSource() == addButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '+';
            textField.setText("");
            deciButton.setEnabled(true);
        }

        if(e.getSource() == subButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '-';
            textField.setText("");
            deciButton.setEnabled(true);
        }

        if(e.getSource() == multButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '*';
            textField.setText("");
            deciButton.setEnabled(true);
        }

        if(e.getSource() == divButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '/';
            textField.setText("");
            deciButton.setEnabled(true);
        }

        if(e.getSource() == equButton) {
            num2 = Double.parseDouble(textField.getText());

            switch (operator) {
                case '+':
                    res = num1 + num2;
                    break;
                case '-':
                    res = num1 - num2;
                    break;
                case '*':
                    res = num1 * num2;
                    break;
                case '/':
                    res = num1 / num2;
                    break;
            }
            textField.setText(String.valueOf(res));
            num1 = res;
            deciButton.setEnabled(true);
        }
        if(e.getSource() == cButton) {
            textField.setText("");
            deciButton.setEnabled(true);
        }

        if(e.getSource() == delButton) {
            String temp = textField.getText();
            textField.setText("");
            for(int i = 0; i < temp.length()-1; i++){
                if (temp.charAt(temp.length()-1) == '.')
                    deciButton.setEnabled(true);
                textField.setText(textField.getText()+temp.charAt(i));
            }
        }

        if(e.getSource() == negButton) {
            double temp = Double.parseDouble(textField.getText());
            temp *= -1;
            textField.setText(String.valueOf(temp));
        }

        if(e.getSource() == percButton) {
            double temp = Double.parseDouble(textField.getText());
            temp /= 100;
            textField.setText(String.valueOf(temp));
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

        switch (e.getKeyChar()){
            case '0':
                textField.setText(textField.getText().concat(numberButtons[0].getText()));
                break;
            case '1':
                textField.setText(textField.getText().concat(String.valueOf(numberButtons[1].getText())));
                break;
            case '2':
                textField.setText(textField.getText().concat(String.valueOf(numberButtons[2].getText())));
                break;
            case '3':
                textField.setText(textField.getText().concat(String.valueOf(numberButtons[3].getText())));
                break;
            case '4':
                textField.setText(textField.getText().concat(String.valueOf(numberButtons[4].getText())));
                break;
            case '5':
                textField.setText(textField.getText().concat(String.valueOf(numberButtons[5].getText())));
                break;
            case '6':
                textField.setText(textField.getText().concat(String.valueOf(numberButtons[6].getText())));
                break;
            case '7':
                textField.setText(textField.getText().concat(String.valueOf(numberButtons[7].getText())));
                break;
            case '8':
                textField.setText(textField.getText().concat(String.valueOf(numberButtons[8].getText())));
                break;
            case '9':
                textField.setText(textField.getText().concat(String.valueOf(numberButtons[9].getText())));
                break;
            case '.':
                if (deciButton.isEnabled()) {
                    textField.setText(textField.getText().concat("."));
                    deciButton.setEnabled(false);
                }
                break;
            case '+':
                num1 = Double.parseDouble(textField.getText());
                operator = '+';
                textField.setText("");
                deciButton.setEnabled(true);
                break;
            case '-':
                num1 = Double.parseDouble(textField.getText());
                operator = '-';
                textField.setText("");
                deciButton.setEnabled(true);
                break;
            case '*':
                num1 = Double.parseDouble(textField.getText());
                operator = '*';
                textField.setText("");
                deciButton.setEnabled(true);
                break;
            case '/':
                num1 = Double.parseDouble(textField.getText());
                operator = '/';
                textField.setText("");
                deciButton.setEnabled(true);
                break;
            case '=':
                num2 = Double.parseDouble(textField.getText());

                switch (operator) {
                    case '+':
                        res = num1 + num2;
                        break;
                    case '-':
                        res = num1 - num2;
                        break;
                    case '*':
                        res = num1 * num2;
                        break;
                    case '/':
                        res = num1 / num2;
                        break;
                }
                textField.setText(String.valueOf(res));
                num1 = res;
                deciButton.setEnabled(true);
                break;
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}