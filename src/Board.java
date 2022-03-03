package com.Dama.GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Board extends JFrame{
    final Color brown = new Color(126, 63, 32);
    final Color white = new Color(228, 179, 138);

    JLabel[][] arrLabel = new JLabel[8][8];

    public Board(){
        super("Dame");

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                arrLabel[i][j] = new JLabel();

                if((i + j) % 2 == 0){
                    //arrLabel[i][j].setIcon(new ImageIcon("./../src/images/brown.png"));
                    arrLabel[i][j].setBackground(brown);
                }else {
                    //arrLabel[i][j].setIcon(new ImageIcon("./../src/images/white.png"));
                    arrLabel[i][j].setBackground(white);
                }
                arrLabel[i][j].setOpaque(true);
                arrLabel[i][j].setSize(75, 75);
                add(arrLabel[i][j]);
            }
        }

        setLayout(new GridLayout(8, 8));
        setSize(600,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);  
    }
}