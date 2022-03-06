package com.Dame.GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.Dame.Constances.Colors;
import com.Dame.Constances.DefaultVBoard;
import com.Dame.GUI.Tile;

public class Board extends JPanel{
    //create cases
    public Tile[][] tiles = new Tile[8][8];
    public char[][] VBoard = new DefaultVBoard().VBoard;

    public Board(){
        setLayout(new GridLayout(8, 8));
        setSize(600,600); 
        
        //fill cases
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                tiles[i][j] = new Tile(i, j);

                //choose case color
                if((i + j) % 2 == 0){
                    tiles[i][j].label.setBackground(Colors.WHITE);
                }else {
                    tiles[i][j].label.setBackground(Colors.BROWN);
                }
                tiles[i][j].label.setOpaque(true);
                tiles[i][j].label.setSize(75, 75);

                //choose default piece
                tiles[i][j].chatToPiece(VBoard.VBoard[i][j]);

                //add to it the board
                add(tiles[i][j].label);
            }
        } 
    }
}