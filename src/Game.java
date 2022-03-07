package com.Dame.Concepts;

import java.util.Vector;


import com.Dame.Constances.DefaultVBoard;
import com.Dame.GUI.PlayFrame;

public class Game {
    private PlayFrame playFrame;
    private char[][] VBoard = new DefaultVBoard().getDefaultVboad();
    private String playerOne;
    private String PlayerTwo;
    private int carrentPlayer;
    private int carrentSlotIndex;
    private char carrentSlotChar;
    private Vector<int[]> listChooses; 
    
    public Game(PlayFrame playFrame, String playerOne, String PlayerTwo){
        this.playFrame = playFrame;
        this.playerOne = playerOne;
        this.PlayerTwo = PlayerTwo;

        drawBoard(move(VBoard, 17, 24));
    }


    //draw the board
    public void drawBoard(char [][] VBoard){
        this.playFrame.getBoard().charsToPieces(VBoard);
    }

    //get the row and the column number of the slot using it's index
    public  int[] getRowAndColumnByIndex(int index){
        int[] result = {(index / 8), (index % 8)};
        return result;
    }
    public int getRowOfSlot(int index){
        return index / 8;
    }
    public int getColumnOfSlot(int index){
        return index % 8;
    }

    //check if the slot is empty
    public boolean isEmtySlot(int slot, char[][] board){
        if(slot < 0 || slot > 63){
            return false;
        }

        int row = getRowOfSlot(slot);
        int column = getColumnOfSlot(slot);
        
        if(board[row][column] == ' '){
            return true;
        }
        return false;
    }

    //set value on the carrentSlot
    public void setCarrentSlotIndex(int carrentSlotIndex){
        this.carrentSlotIndex = carrentSlotIndex;
    }

    //swap the carrentPlayer value
    public int SwapCarrentPlayer(int _carrentPlayer){
        if(_carrentPlayer == 1){
            return 2;
        }
        else{
            return 1;
        }
    }

    //get char of slot by it's index
    public char getSlotChar(char[][] VBoard, int slot){
        if(slot < 0 || slot > 63){
            return ' ';
        }

        int row = getRowOfSlot(slot);
        int column = getColumnOfSlot(slot);

        return VBoard[row][column];
    }

    //get the number of piece of one player that still on the board
    public int getNumberOfPieceOfOnePlayer(char[][] board, int player){
        if(player != 1 && player != 2){
            return 0;
        }
        
        int some = 0;
        if(player == 1){
            for(int i = 0; i < 8; i++){
                for(int j = 0; i < 8; j++){
                    if(board[i][j] == 'a' || board[i][j] == 'A'){
                        some++;
                    }
                }
            }
        }
        else if(player == 1){
            for(int i = 0; i < 8; i++){
                for(int j = 0; i < 8; j++){
                    if(board[i][j] == 'b' || board[i][j] == 'B'){
                        some++;
                    }
                }
            }
        }

        return some;
    }
    //get the number of piece of the two players that still on the board
    public int[] getNumberOfPieceOfTwoPlayer(char[][] board){
        int[] some = {0, 0};
        for(int i = 0; i < 8; i++){
            for(int j = 0; i < 8; j++){
                if(board[i][j] == 'a' || board[i][j] == 'A'){
                    some[0]++;
                }else if(board[i][j] == 'b' || board[i][j] == 'B'){
                    some[1]++;
                }
            }
        }

        return some;
    }

    //clone virtual board
    public char[][] cloneBoard(char[][] board){
        char[][] newBoard = new char[8][8];

        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                newBoard[i][j] = board[i][j];
            }
        }

        return newBoard;
    }

    //move piece in the virtual board
    public char[][] move(char[][] board, int carrentSlotIndex, int newSlotIndex){
        if((isEmtySlot(carrentSlotIndex, board)) || !(isEmtySlot(newSlotIndex, board)) || (newSlotIndex % 2 != 0)){
            System.err.println("The move is Ilegal because the carrentSlotIndex is empty or the newSlotIndex isn't empty or available.");
            return board;
        }

        int carrentRow = getRowOfSlot(carrentSlotIndex);
        int carrentColumn = getColumnOfSlot(carrentSlotIndex);
        char carrentpieceChar = getSlotChar(board, carrentSlotIndex);

        int newRow = getRowOfSlot(newSlotIndex);
        int newColumn = getColumnOfSlot(newSlotIndex);

        board[carrentRow][carrentColumn] = ' ';
        board[newRow][newColumn] = carrentpieceChar;

        return board;
    }


    //check if someone win and return his carrentPlayer value or return 0 if no one win yet
    public int winner(char[][] board){
        int[] score = getNumberOfPieceOfTwoPlayer(board);
        
        if(score[0] > score[1]){
            return 1;
        }else if(score[0] < score[1]){
            return 2;
        }else {
            return 0;
        }
    }
}