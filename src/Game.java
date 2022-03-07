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
    private Vector<Vector<int[]>> listChooses; 
    
    public Game(PlayFrame playFrame, String rule,String playerOne, String PlayerTwo){
        this.playFrame = playFrame;
        this.rule = rule;
        this.playerOne = playerOne;
        this.PlayerTwo = PlayerTwo;


        VBoard = movePiecebyRowAndColumn(VBoard, 2, 1, 3, 0);
        drawBoard(VBoard);

        VBoard = removePiecebyRowAndColumn(VBoard, 5, 0);
        drawBoard(VBoard);
    }


    //draw the board
    public void drawBoard(char [][] VBoard){
        this.playFrame.getBoard().charsToPieces(VBoard);
    }

    //get the row and the column number of the slot using it's index
    public  int[] getRowAndColumnByIndex(int slot){
        int[] result = {(slot / 8), (slot % 8)};
        return result;
    }
    public int getRowOfSlot(int slot){
        return slot / 8;
    }
    public int getColumnOfSlot(int slot){
        return slot % 8;
    }

    //get the slot index by row and column
    public int getIndexByRowAndColumn(int row, int column){
        return ((row * 8) + column);
    }

    //check if the slot is empty
    public boolean isEmtySlot(char[][] board, int row, int column){
        if((row < 0 || row > 7) || (column < 0 || column > 7)){
            return false;
        }
        
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
    public char getSlotChar(char[][] VBoard, int row, int column){
        if((row < 0 || row > 7) || (column < 0 || column > 7)){
            return ' ';
        }

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

    //get the number of kings of a specific player that still on the board
    public int getNumberOfKingOfOnePlayer(char[][] board, int player){
        if(player != 1 && player != 2){
            return 0;
        }
        
        int some = 0;
        if(player == 1){
            for(int i = 0; i < 8; i++){
                for(int j = 0; i < 8; j++){
                    if(board[i][j] == 'A'){
                        some++;
                    }
                }
            }
        }
        else if(player == 2){
            for(int i = 0; i < 8; i++){
                for(int j = 0; i < 8; j++){
                    if(board[i][j] == 'B'){
                        some++;
                    }
                }
            }
        }

        return some;
    }
    //get the number of pieces of the two players that still on the board
    public int[] getNumberOfKingsOfTwoPlayer(char[][] board){
        int[] some = {0, 0};
        for(int i = 0; i < 8; i++){
            for(int j = 0; i < 8; j++){
                if(board[i][j] == 'A'){
                    some[0]++;
                }else if(board[i][j] == 'B'){
                    some[1]++;
                }
            }
        }

        return some;
    }

    //get the slot index of all pieces of one specific player
    public Vector<int[]> getAllPieceRowAndColumn(char[][] board, int player){
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
    public Vector<int[][]> getAllPieceRowAndColumnOfTwoPlayer(char[][] board){
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
    public char[][] movePiecebyRowAndColumn(char[][] board, int carrentRow, int carrentColumn, int newRow, int newColumn){
        if((isEmtySlot(board, carrentRow, carrentColumn)) || !(isEmtySlot(board, newRow, newColumn)) || ((newRow + newColumn) % 2 == 0)){
            System.err.println("this move is Ilegal because the carrentSlotIndex is empty or the newSlotIndex isn't empty or unavailable.");
            return board;
        }

        char [][] newBoard = cloneBoard(board);

        char carrentpieceChar = getSlotChar(newBoard, carrentRow, carrentColumn);

        newBoard[carrentRow][carrentColumn] = ' ';

        if((newRow == 0 && carrentpieceChar == 'a')){
            newBoard[newRow][newColumn] = 'A';
        }else if((newRow == 7 && carrentpieceChar == 'b')){
            newBoard[newRow][newColumn] = 'B';
        }else {
            newBoard[newRow][newColumn] = carrentpieceChar;
        }

        return newBoard;
    }

    //remove piece in the vitual board by it's index
    public char[][] removePiecebyRowAndColumn(char[][] board, int row, int column){
        if((isEmtySlot(board, row, column)) || ((row + column) % 2 == 0)){
            System.err.println("this remove is Ilegal because the slot is empty or unavailable.");
            return board;
        }

        char[][] newBoard = cloneBoard(board);

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

    //----------------------------------------------------- rules ------------------------------------------------------------//
    //get the all list of chooses in the board of a specific player in one specific piece recursively
    public void getListOfChoosesOfPieceSpain(char[][] board, int player, Vector<int[]> listChooses, int row, int column){

    }
    
    //get the list of chooses available in the board of a specific player in one specific piece (spain's rules)
    public Vector<int[]> getListofChoosesAvailableOfOnePieceSpain(char[][] board, int player, int row, int column){
        Vector<int[]> listChooses = new Vector<int[]>();
        
        getListOfChoosesOfPieceSpain(board, player, listChooses, row, column);

        //filtering the longeest path

        return listChooses;
    }

    //get the list of chooses available in the board of a specific player (spain's rules)
    public Vector<Vector<int[]>> getListofChoosesSpain(char[][] board, int player){
        Vector<Vector<int[]>> listChooses = new Vector<Vector<int[]>>();
        Vector<int[]> AllPlayerPieces = getAllPieceRowAndColumn(board, player);

        for(int i = 0; i < AllPlayerPieces.size(); i++){
            listChooses.addElement(getListofChoosesAvailableOfOnePieceSpain(board, player, AllPlayerPieces.elementAt(i)[0], AllPlayerPieces.elementAt(i)[1]));
        }

        //filtering the longeest paths for every piece

        return listChooses;
    }
}