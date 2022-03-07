package com.Dame.GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.Dame.Constances.PieceImage;

public class Tile{
    private int row;
    private int column;
    private JLabel label = new JLabel();
    private char carrentPieceChar = ' ';

    public Tile(int row, int column){
        this.row = row;
        this.column = column;

        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);

        label.setIcon(null);
    }

    //translate char to piece in the label of the tile
    public void chatToPiece(char pieceName){
        if(pieceName == carrentPieceChar){
            return;
        }

        if(pieceName == ' '){
            carrentPieceChar = ' ';
            label.setIcon(null);
        }else if(pieceName == 'a'){
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

    //getting the index of the tile from 0 to 63
    public int getTileIndex(){
        return ((column * 8) + row);
    }

    //check if the tile is empty (the tile did not contain any piece)
    public boolean isEmpty(){
        if(carrentPieceChar == ' '){
            return true;
        }
        return false;
    }


    //getters
    public JLabel getLabel(){
        return label;
    }
    public char getCarrentPieceChar(){
        return carrentPieceChar;
    }
    public int getRow(){
        return row;
    }
    public int getColumn(){
        return column;
    }
}