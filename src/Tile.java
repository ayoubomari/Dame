package com.Dame.GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.Dame.Constances.PieceImage;

public class Tile{
    public int x,y;
    public JLabel label = new JLabel();
    public char carrentPieceChar = ' ';

    public Tile(int x, int y){
        this.x = x;
        this.y = y;

        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);

        label.setIcon(PieceImage.empty);
    }

    public void chatToPiece(char pieceName){
        if(pieceName == carrentPieceChar){
            return;
        }

        if(pieceName == 'a'){
            label.setIcon(PieceImage.normalYellow);
        }else if(pieceName == 'b'){
            label.setIcon(PieceImage.normalBlack);
        }else if(pieceName == 'A'){
            label.setIcon(PieceImage.kingYellow);
        }else if(pieceName == 'B'){
            label.setIcon(PieceImage.kingBlack);
        }
    }
}