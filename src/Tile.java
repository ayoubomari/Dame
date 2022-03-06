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

        label.setIcon(null);
    }


    public void chatToPiece(char pieceName){
        if(pieceName == carrentPieceChar){
            return;
        }

        if(pieceName == 'a'){
            carrentPieceChar = 'a';
            label.setIcon(PieceImage.NORMALYELLOW);
        }else if(pieceName == 'b'){
            carrentPieceChar = 'b';
            label.setIcon(PieceImage.NORMALBLACK);
        }else if(pieceName == 'A'){
            carrentPieceChar = 'A';
            label.setIcon(PieceImage.KINGYELLOW);
        }else if(pieceName == 'B'){
            carrentPieceChar = 'B';
            label.setIcon(PieceImage.KINGBLACK);
        }
    }

    public int getTileIndex(){
        return ((y * 8) + x);
    }

    public boolean isEmpty(){
        if(carrentPieceChar == ' '){
            return true;
        }
        return false;
    }

    public char getCarrentPieceChar(){
        return carrentPieceChar;
    }


    //getters
    public JLabel getLabel(){
        return label;
    }
}