package com.dame.gui;

import java.awt.*;
import javax.swing.*;

import com.dame.concepts.Game;
import com.dame.constances.DefaultBGColorVBoard;
import com.dame.constances.DefaultVBoard;

public class Board extends JPanel{
    //create cases
    private Tile[][] tiles = new Tile[8][8];
    private char[][] VBoard = new DefaultVBoard().getDefaultVboad();
    private char[][] BGColorBoard = new DefaultBGColorVBoard().getDefaultBGColorVBoard();

    public Board(Game game){
        setLayout(new GridLayout(8, 8));
        setSize(600,600); 
        
        //fill cases
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                tiles[i][j] = new Tile(game, i, j);

                tiles[i][j].getLabel().setOpaque(true);
                tiles[i][j].getLabel().setSize(75, 75);

                //choose default piece
                tiles[i][j].chatToPiece(VBoard[i][j]);

                //choose case color
                tiles[i][j].chatToBGColor(BGColorBoard[i][j]);

                //add to it the board
                add(tiles[i][j].getLabel());
            }
        } 
    }

    //translate a char[][] to piece on all tiles of the board
    public void charsToPieces(char[][] boardChars){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                tiles[i][j].chatToPiece(boardChars[i][j]);
            }
        } 
    }

    //translate a char[][] to background color on all tiles of the board
    public void charsToBGColor(char[][] BGColorBoardChars){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                tiles[i][j].chatToBGColor(BGColorBoardChars[i][j]);
            }
        } 
    }

    //getters
    public Tile[][] getTile(){
        return tiles;
    }
    public Tile getTile(int i, int j){
        return tiles[i][j];
    }
    public char[][] getBoard(){
        return VBoard;
    }
    public char[][] BGColorBoard(){
        return BGColorBoard;
    }
}