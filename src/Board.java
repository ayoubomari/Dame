package com.Dame.GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.Dame.Constances.Colors;
import com.Dame.Constances.DefaultVBoard;
import com.Dame.GUI.Tile;

public class Board extends JPanel{
    //create cases
    private Tile[][] tiles = new Tile[8][8];
    private char[][] VBoard = new DefaultVBoard().getDefaultVboad();

    public Board(){
        setLayout(new GridLayout(8, 8));
        setSize(600,600); 
        
        //fill cases
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                tiles[i][j] = new Tile(i, j);

                //choose case color
                if((i + j) % 2 == 0){
                    tiles[i][j].getLabel().setBackground(Colors.WHITE);
                }else {
                    tiles[i][j].getLabel().setBackground(Colors.BROWN);
                }
                tiles[i][j].getLabel().setOpaque(true);
                tiles[i][j].getLabel().setSize(75, 75);

                //choose default piece
                tiles[i][j].chatToPiece(VBoard[i][j]);

                //add to it the board
                add(tiles[i][j].getLabel());
            }
        } 
    }

    //translate a char[][] to piece in all tiles of the board
    public void charsToPieces(char[][] boardChars){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                tiles[i][j].chatToPiece(boardChars[i][j]);
            }
        } 
    }

    //getters
    public Tile[][] getTile(){
        return tiles;
    }
    public Tile getTile(int i, int j){
        return tile[i][j];
    }
    public char[][] getBoard(){
        return VBoard;
    }
}