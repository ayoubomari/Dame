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

    public JLabel getLabel(){
        return label;
    }

    public void chatToPiece(char pieceName){
        if(pieceName == carrentPieceChar){
            return;
        }

        if(pieceName == 'a'){
            carrentPieceChar = 'a';
            label.setIcon(PieceImage.normalYellow);
        }else if(pieceName == 'b'){
            carrentPieceChar = 'b';
            label.setIcon(PieceImage.normalBlack);
        }else if(pieceName == 'A'){
            carrentPieceChar = 'A';
            label.setIcon(PieceImage.kingYellow);
        }else if(pieceName == 'B'){
            carrentPieceChar = 'B';
            label.setIcon(PieceImage.kingBlack);
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
}