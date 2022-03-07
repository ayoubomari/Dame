package com.Dame.Concepts;

import java.util.Vector;


import com.Dame.Constances.DefaultVBoard;
import com.Dame.GUI.PlayFrame;

public class Game {
    private PlayFrame playFrame;
    private String rule;
    private char[][] VBoard = new DefaultVBoard().getDefaultVboad();
    private String playerOne;
    private String PlayerTwo;
    private int carrentPlayer;
    private int carrentSlotIndex;
    private char carrentSlotChar;
    private Vector<int[]> listChooses; 
    
    public Game(PlayFrame playFrame, String rule,String playerOne, String PlayerTwo){
        this.playFrame = playFrame;
        this.rule = rule;
        this.playerOne = playerOne;
        this.PlayerTwo = PlayerTwo;


        VBoard = movePiecebyIndex(VBoard, 17, 24);
        drawBoard(VBoard);

        VBoard = removePiecebyIndex(VBoard, 40);
        drawBoard(VBoard);
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

    //get the number of pieces of a specific player that still on the board
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
        else if(player == 2){
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
    //get the number of pieces of the two players that still on the board
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

    //get the slot index of all pieces of one specific player
    public Vector<int[]> getAllPieceSlotIndex(char[][] board, int player){
        int [] slot = new int[2];
        Vector<int[]> some = new Vector<int[]>();

        if(player == 1){
            for(int i = 0; i < 8; i++){
                for(int j = 0; i < 8; j++){
                    if(board[i][j] == 'a' || board[i][j] == 'A'){
                        slot[0] = i; slot[1] = j;
                        some.addElement(slot);
                    }
                }
            }
        }
        else if(player == 2){
            for(int i = 0; i < 8; i++){
                for(int j = 0; i < 8; j++){
                    if(board[i][j] == 'b' || board[i][j] == 'B'){
                        slot[0] = i; slot[1] = j;
                        some.addElement(slot);
                    }
                }
            }
        }

        return some;
    }
    //get the slot index of all pieces of the two players
    public Vector<int[][]> getAllPieceSlotIndexOfTwoPlayer(char[][] board){
        int [][] slot = new int[2][2];
        Vector<int[][]> some = new Vector<int[][]>();

        for(int i = 0; i < 8; i++){
            for(int j = 0; i < 8; j++){
                if(board[i][j] == 'a' || board[i][j] == 'A'){
                    slot[0][0] = i; slot[0][1] = j;
                    some.addElement(slot);
                }else if(board[i][j] == 'b' || board[i][j] == 'B'){
                    slot[1][0] = i; slot[1][1] = j;
                    some.addElement(slot);
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

    //move piece in the virtual board by it's index
    public char[][] movePiecebyIndex(char[][] board, int carrentSlotIndex, int newSlotIndex){
        if((isEmtySlot(carrentSlotIndex, board)) || !(isEmtySlot(newSlotIndex, board)) || (newSlotIndex % 2 != 0)){
            System.err.println("this move is Ilegal because the carrentSlotIndex is empty or the newSlotIndex isn't empty or available.");
            return board;
        }

        char [][] newBoard = cloneBoard(board);

        int carrentRow = getRowOfSlot(carrentSlotIndex);
        int carrentColumn = getColumnOfSlot(carrentSlotIndex);
        char carrentpieceChar = getSlotChar(newBoard, carrentSlotIndex);

        int newRow = getRowOfSlot(newSlotIndex);
        int newColumn = getColumnOfSlot(newSlotIndex);

        newBoard[carrentRow][carrentColumn] = ' ';
        newBoard[newRow][newColumn] = carrentpieceChar;

        return newBoard;
    }

    //remove piece in the vitual board by it's index
    public char[][] removePiecebyIndex(char[][] board, int slot){
        if((isEmtySlot(slot, board)) || (slot % 2 != 0)){
            System.err.println("this remove is Ilegal because the slot is empty or available.");
            return board;
        }

        char[][] newBoard = cloneBoard(board);

        int row = getRowOfSlot(slot);
        int column = getColumnOfSlot(slot);

        newBoard[row][column] = ' ';

        return newBoard;
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

    //get the list of chooses available in the board of a specific player
    public Vector<int[]> getListofChooses(char[][] board, int player){
        Vector<int[]> listChooses = new Vector<int[]>();
        

        return listChooses;
    }
}