package com.company;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GTN implements ActionListener{

    JFrame frame;
    JTextField input;
    ImageIcon frameBackground, frameIcon;
    JLabel guess, status,nrOfTries;
    JButton submit;
    Random rand;
    String inputText;
    int tries;
    int luckyNumber;
    int retry = 0;

    GTN(){
        rand = new Random();
        luckyNumber = rand.nextInt(101);


        //Buttons
        submit = new JButton("Submit");
        submit.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black), BorderFactory.createLoweredBevelBorder()));
        submit.setBackground(Color.white);
        submit.setForeground(Color.black);
        submit.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,24));
        submit.setBounds(165, 270, 150, 50);
        submit.setFocusPainted(false); // removes annoying border around text
        submit.addActionListener(this);

        guess = new JLabel("Guess the number!");
        guess.setBounds(80, 5, 400, 40);
        guess.setForeground(Color.white);
        guess.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,32));

        status = new JLabel();
        status.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,32));
        status.setForeground(Color.white);
        status.setBounds(170, 150, 500, 80);

        nrOfTries = new JLabel();
        nrOfTries.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,32));
        nrOfTries.setForeground(Color.white);
        nrOfTries.setBounds(170, 150, 500, 80);

        //Input
        input = new JTextField();
        input.setHorizontalAlignment(JTextField.CENTER);
        input.setBounds(40,60,400,70);
        input.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,32));
        input.setBackground(Color.black);
        input.setForeground(Color.white);

        //Frame
        frame = new JFrame("Guess it!");
        frame.setSize(500,500);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null); // centers frame on screen

        frame.add(nrOfTries);
        frame.add(status);
        frame.add(submit);
        frame.add(guess);
        frame.add(input);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==submit) {

            try{
                Integer number = Integer.parseInt(inputText = input.getText());
                status.setText(number.toString());
                if(number == luckyNumber) {
                    status.setText("You guessed correctly!");
                    status.setLocation(40, 150);

                    nrOfTries.setText("Number of tries : " + tries);
                    nrOfTries.setLocation(55,200);

                    submit.setText("Retry");
                    retry++; // retry will be 1 if you guess correctly, but if you click the button again then it will restart the game.

                }
                if(number < luckyNumber) {
                    status.setText("Higher.");
                    status.setLocation(175, 150);
                    tries++;
                }
                if(number > luckyNumber) {
                    status.setText("Lower.");
                    status.setLocation(185, 150);
                    tries++;
                }
            }
            catch (NumberFormatException ex){
                status.setText("Insert a valid number.");
                status.setLocation(40, 150);
            }
            if(retry>1) {
                frame.dispose();
                GTN game = new GTN();
            }
        }
    }
}