package com.Dame.Concepts;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.Vector;
import java.util.concurrent.TimeUnit;

import com.Dame.Constances.DefaultVBoard;
import com.Dame.Constances.DefaultBGColorVBoard;
import com.Dame.GUI.Board;
import com.Dame.GUI.PlayFrame;

public class Game {
    private PlayFrame playFrame;
    private String rule;
    private char[][] board = new DefaultVBoard().getDefaultVboad();
    private char[][] BGColorBoard = new DefaultBGColorVBoard().getDefaultBGColorVBoard();
    private String playerOne;
    private String playerTwo;
    private int carrentPlayer;
    private String carrentPlayerName;
    private int[] carrentSlotIndex = new int[2];
    private char carrentSlotChar = ' ';
    private int maxNullPlay = 50;
    private Vector<Vector<int[]>> listChooses; 
    
    public Game(){
        // drawBoard(board);
        // coloringBoard(coloringListOfTilesCanMove(board, 2));
        // coloringBoard(coloringFullEattingPaths(board, 2, 5, 2));

        // coloringBoard(new DefaultBGColorVBoard().getDefaultBGColorVBoard());
        // Vector<Vector<int[]>> fullPaths = traceFullPaths(board, 2, 5, 2);
        // movePiecebyRowAndColumnSlowlyWithDisplay(board, 2, 5, 2, 7, 0);

        // Vector<Vector<Vector<int[]>>> listofChooses = getListofChooses(board, 2);
        // System.out.println("---------- list of paths can choose ----------");
        // for(int i = 0; i < listofChooses.size(); i++){
        //     System.out.println("##############################");
        //     if(listofChooses.get(i).size() > 0){
        //         for(int j = 0; j < listofChooses.get(i).size(); j++){
        //             System.out.println("---------------------------------");
        //             for(int k  = 0; k < listofChooses.get(i).get(j).size(); k++){
        //                 System.out.println("row: " + listofChooses.get(i).get(j).get(k)[0]);;
        //                 System.out.println("column: " + listofChooses.get(i).get(j).get(k)[1]);
        //             }
        //         }
        //     }
        // }

        // Vector<int []> listOfPieceCanMove = getListOfPieceCanMove(board, 1);
        // System.out.println("---------- list of piece can move ----------");
        // for(int i = 0; i < listOfPieceCanMove.size(); i++){
        //     System.out.println("##############################");
        //     System.out.println("row: " + listOfPieceCanMove.get(i)[0]);;
        //     System.out.println("column: " + listOfPieceCanMove.get(i)[1]);
        // }

        // Vector<Vector<int[]>> fullPaths = traceFullPaths(board, 1, 1, 2);
        // System.out.println("---------- trace the full paths of a piece can move ----------");
        // for(int i = 0; i < fullPaths.size(); i++){
        //     System.out.println("\n\n\n------\n\n\n");
        //     for(int j = 0; j < fullPaths.get(i).size(); j++){
        //         System.out.println("##############################");
        //         System.out.println("row: " + fullPaths.get(i).get(j)[0]);;
        //         System.out.println("column: " + fullPaths.get(i).get(j)[1]);
        //     }
        // }
    }
    // public Game(String rule, String playerOne, String playerTwo){
    //     play(rule, playerOne, playerTwo);
    // }

    public PlayFrame getPlayFrame(){
        return playFrame;
    }
    public String getRule(){
        return rule;
    }
    public char[][] getBoard(){
        return board;
    }
    public char[][] getBGColorBoard(){
        return BGColorBoard;
    }
    public String getPlayerOne(){
        return playerOne;
    }
    public String getPlayerTwo(){
        return playerTwo;
    }
    public int getCarrentPlayer(){
        return carrentPlayer;
    }
    public String getCarrentPlayerName(){
        return carrentPlayerName;
    }
    public int[] getCarrentSlotIndex(){
        return carrentSlotIndex;
    }
    public int getMaxNullPlay(){
        return maxNullPlay;
    }


    public void setPlayFrame(PlayFrame playFrame){
        this.playFrame = playFrame;
    }


    //draw the board
    public void drawBoard(char [][] board){
        this.playFrame.getBoard().charsToPieces(board);
    }

    //coloring the board
    public void coloringBoard(char [][] BGColorboard){
        this.playFrame.getBoard().charsToBGColor(BGColorboard);
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


    //set value on the carrentSlotIndex
    public void setCarrentSlotIndex(int[] carrentSlotIndex){
        this.carrentSlotIndex[0] = carrentSlotIndex[0];
        this.carrentSlotIndex[1] = carrentSlotIndex[1];
    }
    public void setCarrentSlotIndex(int row, int column){
        this.carrentSlotIndex[0] = row;
        this.carrentSlotIndex[1] = column;
    }

    //set value on the carrentSlotChar
    public void setCarrentSlotChar(char carrentSlotChar){
        this.carrentSlotChar = carrentSlotChar;
    }
    //get the value of the carrentSlotChar
    public char getCarrentSlotChar(){
        return carrentSlotChar;
    }

    //swap the carrentPlayer value
    public int getSwapCarrentPlayer(int carrentPlayer){
        if(carrentPlayer == 1){
            return 2;
        }
        else{
            return 1;
        }
    }
    //swap the carrentPlayer value
    public String getSwapCarrentPlayerName(int carrentPlayer){
        if(carrentPlayer == 1){
            return playerTwo;
        }
        else{
            return playerOne;
        }
    }

    //swap the carrentPlayer value
    public void swapCarrentPlayer(int carrentPlayer){
        if(carrentPlayer == 1){
            this.carrentPlayer = 2;
        }
        else{
            this.carrentPlayer = 1;
        }
    }
    //swap the carrentPlayer value
    public void swapCarrentPlayerName(int carrentPlayer){
        if(carrentPlayer == 1){
            this.carrentPlayerName = playerTwo;
        }
        else{
            this.carrentPlayerName = playerOne;
        }
    }

    //get char of slot by it's index
    public char getSlotChar(char[][] board, int row, int column){
        if((row < 0 || row > 7) || (column < 0 || column > 7)){
            return '!';
        }

        return board[row][column];
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
                for(int j = 0; j < 8; j++){
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
                for(int j = 0; j < 8; j++){
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
        Vector<int[]> some = new Vector<int[]>();

        if(player == 1){
            for(int i = 0; i < 8; i++){
                for(int j = 0; j < 8; j++){
                    if(board[i][j] == 'a' || board[i][j] == 'A'){
                        int[] slot = new int[2];
                        slot[0] = i; slot[1] = j;
                        some.addElement(slot);
                    }
                }
            }
        }
        else if(player == 2){
            for(int i = 0; i < 8; i++){
                for(int j = 0; j < 8; j++){
                    if(board[i][j] == 'b' || board[i][j] == 'B'){
                        int[] slot = new int[2];
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
        Vector<int[][]> some = new Vector<int[][]>();

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(board[i][j] == 'a' || board[i][j] == 'A'){
                    int[][] slot = new int[2][2];
                    slot[0][0] = i; slot[0][1] = j;
                    some.addElement(slot);
                }else if(board[i][j] == 'b' || board[i][j] == 'B'){
                    int[][] slot = new int[2][2];
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

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                newBoard[i][j] = board[i][j];
            }
        }

        return newBoard;
    }

    //move piece in the virtual board by it's index
    public char[][] movePiecebyRowAndColumn(char[][] board, int carrentRow, int carrentColumn, int newRow, int newColumn){
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

    //clone path
    public Vector<int[]> clonePath(Vector<int[]> path){
        Vector<int[]> newPath = new Vector<int[]>();

        for(int i = 0; i < path.size(); i++){
            int[] rowAndColumn = new int[2];
            rowAndColumn[0] = path.get(i)[0]; rowAndColumn[1] = path.get(i)[1];

            newPath.addElement(rowAndColumn);
        }

        return newPath;
    }

    //################################################################ rules ################################################################//
    //============================================= spain's rules =============================================//
    public void pieceAfterEattingSpain(char[][] board, int player, Vector<Vector<int[]>> listChooses, Vector<int[]> comulPath, int row, int column, int rowDifferent, int columnDifferent, char slotChar){
        char lastSlotChar = ' ';
        char nextSlotChar;
        char[] carrentPlayerSlotChar = new char[2]; 
        char[] otherPlayerSlotChar = new char[2]; 
        if(slotChar == 'B'){
            carrentPlayerSlotChar[0] = 'b';
            carrentPlayerSlotChar[1] = 'B';

            otherPlayerSlotChar[0] = 'a';
            otherPlayerSlotChar[1] = 'A';
        }else{
            carrentPlayerSlotChar[0] = 'a';
            carrentPlayerSlotChar[1] = 'A';

            otherPlayerSlotChar[0] = 'b';
            otherPlayerSlotChar[1] = 'B';
        }
        
        if(rowDifferent == 1 && columnDifferent == 1){
            for(int i = 1; i < 8; i++){
                nextSlotChar = getSlotChar(board, row + i, column + i);
    
                if((lastSlotChar == otherPlayerSlotChar[0] || lastSlotChar == otherPlayerSlotChar[1]) && (nextSlotChar == otherPlayerSlotChar[0] || nextSlotChar == otherPlayerSlotChar[1])){
                    break;
                }
    
                if(nextSlotChar == carrentPlayerSlotChar[0] || nextSlotChar == carrentPlayerSlotChar[1] || nextSlotChar == '!'){
                    break;
                }else if(nextSlotChar == ' '){
                    lastSlotChar = nextSlotChar; continue;
                }else if(nextSlotChar == otherPlayerSlotChar[0] || nextSlotChar == otherPlayerSlotChar[1]){
                    char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + i, column + i);
    
                    Vector<int[]> newComulPath = new Vector<int[]>();
                    newComulPath = clonePath(comulPath);
                    //add the carrent piece to the path (from)
                    int [] newRowAndColumn = new int[2];
                    newRowAndColumn[0] = row; newRowAndColumn[1] = column; 
                    newComulPath.addElement(newRowAndColumn);
                
                    getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + i, column + i, false, true, false);
                }
    
                lastSlotChar = nextSlotChar;
            }
        }else if(rowDifferent == 1 && columnDifferent == -1){
            for(int i = 1; i < 8; i++){
                nextSlotChar = getSlotChar(board, row + i, column - i);
    
                if((lastSlotChar == otherPlayerSlotChar[0] || lastSlotChar == otherPlayerSlotChar[1]) && (nextSlotChar == otherPlayerSlotChar[0] || nextSlotChar == otherPlayerSlotChar[1])){
                    break;
                }
    
                if(nextSlotChar == carrentPlayerSlotChar[0] || nextSlotChar == carrentPlayerSlotChar[1] || nextSlotChar == '!'){
                    break;
                }else if(nextSlotChar == ' '){
                    lastSlotChar = nextSlotChar; continue;
                }else if(nextSlotChar == otherPlayerSlotChar[0] || nextSlotChar == otherPlayerSlotChar[1]){
                    char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + i, column - i);
    
                    Vector<int[]> newComulPath = new Vector<int[]>();
                    newComulPath = clonePath(comulPath);
                    //add the carrent piece to the path (from)
                    int [] newRowAndColumn = new int[2];
                    newRowAndColumn[0] = row; newRowAndColumn[1] = column; 
                    newComulPath.addElement(newRowAndColumn);
                
                    getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + i, column - i, false, true, false);
                }
    
                lastSlotChar = nextSlotChar;
            }
        }else if(rowDifferent == -1 && columnDifferent == 1){
            for(int i = 1; i < 8; i++){
                nextSlotChar = getSlotChar(board, row - i, column + i);
    
                if((lastSlotChar == otherPlayerSlotChar[0] || lastSlotChar == otherPlayerSlotChar[1]) && (nextSlotChar == otherPlayerSlotChar[0] || nextSlotChar == otherPlayerSlotChar[1])){
                    break;
                }
    
                if(nextSlotChar == carrentPlayerSlotChar[0] || nextSlotChar == carrentPlayerSlotChar[1] || nextSlotChar == '!'){
                    break;
                }else if(nextSlotChar == ' '){
                    lastSlotChar = nextSlotChar; continue;
                }else if(nextSlotChar == otherPlayerSlotChar[0] || nextSlotChar == otherPlayerSlotChar[1]){
                    char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - i, column + i);
    
                    Vector<int[]> newComulPath = new Vector<int[]>();
                    newComulPath = clonePath(comulPath);
                    //add the carrent piece to the path (from)
                    int [] newRowAndColumn = new int[2];
                    newRowAndColumn[0] = row; newRowAndColumn[1] = column; 
                    newComulPath.addElement(newRowAndColumn);
                
                    getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - i, column + i, false, true, false);
                }
    
                lastSlotChar = nextSlotChar;
            }
        }else if(rowDifferent == -1 && columnDifferent == -1){
            for(int i = 1; i < 8; i++){
                nextSlotChar = getSlotChar(board, row - i, column - i);
    
                if((lastSlotChar == otherPlayerSlotChar[0] || lastSlotChar == otherPlayerSlotChar[1]) && (nextSlotChar == otherPlayerSlotChar[0] || nextSlotChar == otherPlayerSlotChar[1])){
                    break;
                }
    
                if(nextSlotChar == carrentPlayerSlotChar[0] || nextSlotChar == carrentPlayerSlotChar[1] || nextSlotChar == '!'){
                    break;
                }else if(nextSlotChar == ' '){
                    lastSlotChar = nextSlotChar; continue;
                }else if(nextSlotChar == otherPlayerSlotChar[0] || nextSlotChar == otherPlayerSlotChar[1]){
                    char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - i, column - i);
    
                    Vector<int[]> newComulPath = new Vector<int[]>();
                    newComulPath = clonePath(comulPath);
                    //add the carrent piece to the path (from)
                    int [] newRowAndColumn = new int[2];
                    newRowAndColumn[0] = row; newRowAndColumn[1] = column; 
                    newComulPath.addElement(newRowAndColumn);
                
                    getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - i, column - i, false, true, false);
                }
    
                lastSlotChar = nextSlotChar;
            }
        }
    }
    
    
    //get the all list of chooses in the board of a specific player in one specific piece recursively
    public void getListOfChoosesOfOnePieceSpain(char[][] board, int player, Vector<Vector<int[]>> listChooses, char lastCarrentSlot, Vector<int[]> comulPath, int row, int column, boolean isFirstStep, boolean eatting, boolean eated){
        char pieceChar = getSlotChar(board, row, column);
        //Vector<int[]> path = new Vector<int[]>();
        //int [] rowAndColumn = new int[2];
        if(isFirstStep){
            if(pieceChar == 'b'){
                if(getSlotChar(board, row + 1, column + 1) == ' '){
                    Vector<int[]> path = new Vector<int[]>();
                    path = clonePath(comulPath);
                    //add the carrent piece to the path (from)
                    int [] rowAndColumn = new int[2];
                    rowAndColumn[0] = row; rowAndColumn[1] = column; 
                    path.addElement(rowAndColumn);

                    //add the next empty piece to the path (to)
                    int [] rowAndColumn2 = new int[2];
                    rowAndColumn2[0] = row + 1; rowAndColumn2[1] = column + 1; 
                    path.addElement(rowAndColumn2);

                    //add the path to the list of chooses
                    listChooses.addElement(path);
                }else if(getSlotChar(board, row + 1, column + 1) == 'a' || getSlotChar(board, row + 1, column + 1) == 'A'){
                    char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + 1, column + 1);

                    Vector<int[]> newComulPath = new Vector<int[]>();
                    newComulPath = clonePath(comulPath);
                    //add the carrent piece to the path (from)
                    int [] rowAndColumn = new int[2];
                    rowAndColumn[0] = row; rowAndColumn[1] = column; 
                    newComulPath.addElement(rowAndColumn);
                    
                    getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + 1, column + 1, false, true, false);
                }
            }else if(pieceChar == 'B'){
                char lastSlotChar = ' ';
                char nextSlotChar;
                for(int i = 1; i < 8; i++){
                    nextSlotChar = getSlotChar(board, row + i, column + i);

                    if((lastSlotChar == 'a' || lastSlotChar == 'A') && (nextSlotChar == 'a' || nextSlotChar == 'A')){
                        break;
                    }

                    if(nextSlotChar == 'b' || nextSlotChar == 'B' || nextSlotChar == '!'){
                        break;
                    }else if(nextSlotChar == ' '){
                        Vector<int[]> path = new Vector<int[]>();
                        path = clonePath(comulPath);
                        //add the carrent piece to the path (from)
                        int [] rowAndColumn = new int[2];
                        rowAndColumn[0] = row; rowAndColumn[1] = column; 
                        path.addElement(rowAndColumn);

                        //add the next empty piece to the path (to)
                        int [] rowAndColumn2 = new int[2];
                        rowAndColumn2[0] = row + i; rowAndColumn2[1] = column + i; 
                        path.addElement(rowAndColumn2);

                        //add the path to the list of chooses
                        listChooses.addElement(path);
                    }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
                        char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + i, column + i);

                        Vector<int[]> newComulPath = new Vector<int[]>();
                        newComulPath = clonePath(comulPath);
                        //add the carrent piece to the path (from)
                        int [] rowAndColumn = new int[2];
                        rowAndColumn[0] = row; rowAndColumn[1] = column; 
                        newComulPath.addElement(rowAndColumn);
                    
                        getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + i, column + i, false, true, false);
                    }

                    lastSlotChar = nextSlotChar;
                }
            }else if(pieceChar == 'A'){
                char lastSlotChar = ' ';
                char nextSlotChar;
                for(int i = 1; i < 8; i++){
                    nextSlotChar = getSlotChar(board, row + i, column + i);

                    if((lastSlotChar == 'b' || lastSlotChar == 'B') && (nextSlotChar == 'b' || nextSlotChar == 'B')){
                        break;
                    }

                    if(nextSlotChar == 'a' || nextSlotChar == 'A' || nextSlotChar == '!'){
                        break;
                    }else if(nextSlotChar == ' '){
                        Vector<int[]> path = new Vector<int[]>();
                        path = clonePath(comulPath);
                        //add the carrent piece to the path (from)
                        int [] rowAndColumn = new int[2];
                        rowAndColumn[0] = row; rowAndColumn[1] = column; 
                        path.addElement(rowAndColumn);

                        //add the next empty piece to the path (to)
                        int [] rowAndColumn2 = new int[2];
                        rowAndColumn2[0] = row + i; rowAndColumn2[1] = column + i; 
                        path.addElement(rowAndColumn2);

                        //add the path to the list of chooses
                        listChooses.addElement(path);
                    }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
                        char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + i, column + i);

                        Vector<int[]> newComulPath = new Vector<int[]>();
                        newComulPath = clonePath(comulPath);
                        //add the carrent piece to the path (from)
                        int [] rowAndColumn = new int[2];
                        rowAndColumn[0] = row; rowAndColumn[1] = column; 
                        newComulPath.addElement(rowAndColumn);
                    
                        getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + i, column + i, false, true, false);
                    }

                    lastSlotChar = nextSlotChar;
                }
            }
            //----------------------------------------------------------------------------------------------------------
            if(pieceChar == 'b'){
                if(getSlotChar(board, row + 1, column - 1) == ' '){
                    Vector<int[]> path = new Vector<int[]>();
                    path = clonePath(comulPath);
                    //add the carrent piece to the path (from)
                    int [] rowAndColumn = new int[2];
                    rowAndColumn[0] = row; rowAndColumn[1] = column; 
                    path.addElement(rowAndColumn);

                    //add the next empty piece to the path (to)
                    int [] rowAndColumn2 = new int[2];
                    rowAndColumn2[0] = row + 1; rowAndColumn2[1] = column - 1; 
                    path.addElement(rowAndColumn2);

                    //add the path to the list of chooses
                    listChooses.addElement(path);
                }else if(getSlotChar(board, row + 1, column - 1) == 'a' || getSlotChar(board, row + 1, column - 1) == 'A'){
                    char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + 1, column - 1);

                    Vector<int[]> newComulPath = new Vector<int[]>();
                    //add the carrent piece to the path (from)
                    int [] rowAndColumn = new int[2];
                    rowAndColumn[0] = row; rowAndColumn[1] = column; 
                    newComulPath.addElement(rowAndColumn);
                    
                    getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + 1, column - 1, false, true, false);
                }
            }else if(pieceChar == 'B'){
                char lastSlotChar = ' ';
                char nextSlotChar;
                for(int i = 1; i < 8; i++){
                    nextSlotChar = getSlotChar(board, row + i, column - i);

                    if((lastSlotChar == 'a' || lastSlotChar == 'A') && (nextSlotChar == 'a' || nextSlotChar == 'A')){
                        break;
                    }

                    if(nextSlotChar == 'b' || nextSlotChar == 'B' || nextSlotChar == '!'){
                        break;
                    }else if(nextSlotChar == ' '){
                        Vector<int[]> path = new Vector<int[]>();
                        path = clonePath(comulPath);
                        //add the carrent piece to the path (from)
                        int [] rowAndColumn = new int[2];
                        rowAndColumn[0] = row; rowAndColumn[1] = column; 
                        path.addElement(rowAndColumn);

                        //add the next empty piece to the path (to)
                        int [] rowAndColumn2 = new int[2];
                        rowAndColumn2[0] = row + i; rowAndColumn2[1] = column - i; 
                        path.addElement(rowAndColumn2);

                        //add the path to the list of chooses
                        listChooses.addElement(path);
                    }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
                        char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + i, column - i);

                        Vector<int[]> newComulPath = new Vector<int[]>();
                        newComulPath = clonePath(comulPath);
                        //add the carrent piece to the path (from)
                        int [] rowAndColumn = new int[2];
                        rowAndColumn[0] = row; rowAndColumn[1] = column; 
                        newComulPath.addElement(rowAndColumn);
                    
                        getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + i, column - i, false, true, false);
                    }

                    lastSlotChar = nextSlotChar;
                }
            }else if(pieceChar == 'A'){
                char lastSlotChar = ' ';
                char nextSlotChar;
                for(int i = 1; i < 8; i++){
                    nextSlotChar = getSlotChar(board, row + i, column - i);

                    if((lastSlotChar == 'b' || lastSlotChar == 'B') && (nextSlotChar == 'b' || nextSlotChar == 'B')){
                        break;
                    }

                    if(nextSlotChar == 'a' || nextSlotChar == 'A' || nextSlotChar == '!'){
                        break;
                    }else if(nextSlotChar == ' '){
                        Vector<int[]> path = new Vector<int[]>();
                        path = clonePath(comulPath);
                        //add the carrent piece to the path (from)
                        int [] rowAndColumn = new int[2];
                        rowAndColumn[0] = row; rowAndColumn[1] = column; 
                        path.addElement(rowAndColumn);

                        //add the next empty piece to the path (to)
                        int [] rowAndColumn2 = new int[2];
                        rowAndColumn2[0] = row + i; rowAndColumn2[1] = column - i; 
                        path.addElement(rowAndColumn2);

                        //add the path to the list of chooses
                        listChooses.addElement(path);
                    }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
                        char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + i, column - i);

                        Vector<int[]> newComulPath = new Vector<int[]>();
                        newComulPath = clonePath(comulPath);
                        //add the carrent piece to the path (from)
                        int [] rowAndColumn = new int[2];
                        rowAndColumn[0] = row; rowAndColumn[1] = column; 
                        newComulPath.addElement(rowAndColumn);
                    
                        getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + i, column - i, false, true, false);
                    }

                    lastSlotChar = nextSlotChar;
                }
            }
            //--------------------------------------------------------------------------------------------------------------
            if(pieceChar == 'a'){
                if(getSlotChar(board, row - 1, column + 1) == ' '){
                    Vector<int[]> path = new Vector<int[]>();
                    path = clonePath(comulPath);
                    //add the carrent piece to the path (from)
                    int [] rowAndColumn = new int[2];
                    rowAndColumn[0] = row; rowAndColumn[1] = column; 
                    path.addElement(rowAndColumn);

                    //add the next empty piece to the path (to)
                    int [] rowAndColumn2 = new int[2];
                    rowAndColumn2[0] = row - 1; rowAndColumn2[1] = column + 1; 
                    path.addElement(rowAndColumn2);

                    //add the path to the list of chooses
                    listChooses.addElement(path);
                }else if(getSlotChar(board, row - 1, column + 1) == 'b' || getSlotChar(board, row - 1, column + 1) == 'B'){
                    char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - 1, column + 1);

                    Vector<int[]> newComulPath = new Vector<int[]>();
                    //add the carrent piece to the path (from)
                    int [] rowAndColumn = new int[2];
                    rowAndColumn[0] = row; rowAndColumn[1] = column; 
                    newComulPath.addElement(rowAndColumn);
                    
                    getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - 1, column + 1, false, true, false);
                }
            }else if(pieceChar == 'A'){
                char lastSlotChar = ' ';
                char nextSlotChar;
                for(int i = 1; i < 8; i++){
                    nextSlotChar = getSlotChar(board, row - i, column + i);

                    if((lastSlotChar == 'b' || lastSlotChar == 'B') && (nextSlotChar == 'b' || nextSlotChar == 'B')){
                        break;
                    }

                    if(nextSlotChar == 'a' || nextSlotChar == 'A' || nextSlotChar == '!'){
                        break;
                    }else if(nextSlotChar == ' '){
                        Vector<int[]> path = new Vector<int[]>();
                        path = clonePath(comulPath);
                        //add the carrent piece to the path (from)
                        int [] rowAndColumn = new int[2];
                        rowAndColumn[0] = row; rowAndColumn[1] = column; 
                        path.addElement(rowAndColumn);

                        //add the next empty piece to the path (to)
                        int [] rowAndColumn2 = new int[2];
                        rowAndColumn2[0] = row - i; rowAndColumn2[1] = column + i; 
                        path.addElement(rowAndColumn2);

                        //add the path to the list of chooses
                        listChooses.addElement(path);
                    }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
                        char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - i, column + i);

                        Vector<int[]> newComulPath = new Vector<int[]>();
                        newComulPath = clonePath(comulPath);
                        //add the carrent piece to the path (from)
                        int [] rowAndColumn = new int[2];
                        rowAndColumn[0] = row; rowAndColumn[1] = column; 
                        newComulPath.addElement(rowAndColumn);
                    
                        getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - i, column + i, false, true, false);
                    }

                    lastSlotChar = nextSlotChar;
                }
            }else if(pieceChar == 'B'){
                char lastSlotChar = ' ';
                char nextSlotChar;
                for(int i = 1; i < 8; i++){
                    nextSlotChar = getSlotChar(board, row - i, column + i);

                    if((lastSlotChar == 'a' || lastSlotChar == 'A') && (nextSlotChar == 'a' || nextSlotChar == 'A')){
                        break;
                    }

                    if(nextSlotChar == 'b' || nextSlotChar == 'B' || nextSlotChar == '!'){
                        break;
                    }else if(nextSlotChar == ' '){
                        Vector<int[]> path = new Vector<int[]>();
                        path = clonePath(comulPath);
                        //add the carrent piece to the path (from)
                        int [] rowAndColumn = new int[2];
                        rowAndColumn[0] = row; rowAndColumn[1] = column; 
                        path.addElement(rowAndColumn);

                        //add the next empty piece to the path (to)
                        int [] rowAndColumn2 = new int[2];
                        rowAndColumn2[0] = row - i; rowAndColumn2[1] = column + i; 
                        path.addElement(rowAndColumn2);

                        //add the path to the list of chooses
                        listChooses.addElement(path);
                    }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
                        char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - i, column + i);

                        Vector<int[]> newComulPath = new Vector<int[]>();
                        newComulPath = clonePath(comulPath);
                        //add the carrent piece to the path (from)
                        int [] rowAndColumn = new int[2];
                        rowAndColumn[0] = row; rowAndColumn[1] = column; 
                        newComulPath.addElement(rowAndColumn);
                    
                        getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - i, column + i, false, true, false);
                    }

                    lastSlotChar = nextSlotChar;
                }
            }
            //--------------------------------------------------------------------------------------------------------------------
            if(pieceChar == 'a'){
                if(getSlotChar(board, row - 1, column - 1) == ' '){
                    Vector<int[]> path = new Vector<int[]>();
                    path = clonePath(comulPath);
                    //add the carrent piece to the path (from)
                    int [] rowAndColumn = new int[2];
                    rowAndColumn[0] = row; rowAndColumn[1] = column; 
                    path.addElement(rowAndColumn);

                    //add the next empty piece to the path (to)
                    int [] rowAndColumn2 = new int[2];
                    rowAndColumn2[0] = row - 1; rowAndColumn2[1] = column - 1; 
                    path.addElement(rowAndColumn2);

                    //add the path to the list of chooses
                    listChooses.addElement(path);
                }else if(getSlotChar(board, row - 1, column - 1) == 'b' || getSlotChar(board, row - 1, column - 1) == 'B'){
                    char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - 1, column - 1);

                    Vector<int[]> newComulPath = new Vector<int[]>();
                    //add the carrent piece to the path (from)
                    int [] rowAndColumn = new int[2];
                    rowAndColumn[0] = row; rowAndColumn[1] = column; 
                    newComulPath.addElement(rowAndColumn);
                    
                    getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - 1, column - 1, false, true, false);
                }
            }else if(pieceChar == 'A'){
                char lastSlotChar = ' ';
                char nextSlotChar;
                for(int i = 1; i < 8; i++){
                    nextSlotChar = getSlotChar(board, row - i, column - i);

                    if((lastSlotChar == 'b' || lastSlotChar == 'B') && (nextSlotChar == 'b' || nextSlotChar == 'B')){
                        break;
                    }

                    if(nextSlotChar == 'a' || nextSlotChar == 'A' || nextSlotChar == '!'){
                        break;
                    }else if(nextSlotChar == ' '){
                        Vector<int[]> path = new Vector<int[]>();
                        path = clonePath(comulPath);
                        //add the carrent piece to the path (from)
                        int [] rowAndColumn = new int[2];
                        rowAndColumn[0] = row; rowAndColumn[1] = column; 
                        path.addElement(rowAndColumn);

                        //add the next empty piece to the path (to)
                        int [] rowAndColumn2 = new int[2];
                        rowAndColumn2[0] = row - i; rowAndColumn2[1] = column - i; 
                        path.addElement(rowAndColumn2);

                        //add the path to the list of chooses
                        listChooses.addElement(path);
                    }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
                        char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - i, column - i);

                        Vector<int[]> newComulPath = new Vector<int[]>();
                        newComulPath = clonePath(comulPath);
                        //add the carrent piece to the path (from)
                        int [] rowAndColumn = new int[2];
                        rowAndColumn[0] = row; rowAndColumn[1] = column; 
                        newComulPath.addElement(rowAndColumn);
                    
                        getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - i, column - i, false, true, false);
                    }

                    lastSlotChar = nextSlotChar;
                }
            }else if(pieceChar == 'B'){
                char lastSlotChar = ' ';
                char nextSlotChar;
                for(int i = 1; i < 8; i++){
                    nextSlotChar = getSlotChar(board, row - i, column - i);

                    if((lastSlotChar == 'a' || lastSlotChar == 'A') && (nextSlotChar == 'a' || nextSlotChar == 'A')){
                        break;
                    }

                    if(nextSlotChar == 'b' || nextSlotChar == 'B' || nextSlotChar == '!'){
                        break;
                    }else if(nextSlotChar == ' '){
                        Vector<int[]> path = new Vector<int[]>();
                        path = clonePath(comulPath);
                        //add the carrent piece to the path (from)
                        int [] rowAndColumn = new int[2];
                        rowAndColumn[0] = row; rowAndColumn[1] = column; 
                        path.addElement(rowAndColumn);

                        //add the next empty piece to the path (to)
                        int [] rowAndColumn2 = new int[2];
                        rowAndColumn2[0] = row - i; rowAndColumn2[1] = column - i; 
                        path.addElement(rowAndColumn2);

                        //add the path to the list of chooses
                        listChooses.addElement(path);
                    }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
                        char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - i, column - i);

                        Vector<int[]> newComulPath = new Vector<int[]>();
                        newComulPath = clonePath(comulPath);
                        //add the carrent piece to the path (from)
                        int [] rowAndColumn = new int[2];
                        rowAndColumn[0] = row; rowAndColumn[1] = column; 
                        newComulPath.addElement(rowAndColumn);
                    
                        getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - i, column - i, false, true, false);
                    }

                    lastSlotChar = nextSlotChar;
                }
            }
            
            
            
            
            // //---------------- corners ----------------//-----------------------------------------------------------------------------------
            // if(row == 0 && column == 0){
            //     if(pieceChar == 'b'){
            //         if(getSlotChar(board, row + 1, column + 1) == ' '){
            //             Vector<int[]> path = new Vector<int[]>();
            //             path = clonePath(comulPath);
            //             //add the carrent piece to the path (from)
            //             int [] rowAndColumn = new int[2];
            //             rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //             path.addElement(rowAndColumn);

            //             //add the next empty piece to the path (to)
            //             int [] rowAndColumn2 = new int[2];
            //             rowAndColumn2[0] = row + 1; rowAndColumn2[1] = column + 1; 
            //             path.addElement(rowAndColumn2);

            //             //add the path to the list of chooses
            //             listChooses.addElement(path);
            //         }else if(getSlotChar(board, row + 1, column + 1) == 'a' || getSlotChar(board, row + 1, column + 1) == 'A'){
            //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + 1, column + 1);

            //             Vector<int[]> newComulPath = new Vector<int[]>();
            //             newComulPath = clonePath(comulPath);
            //             //add the carrent piece to the path (from)
            //             int [] rowAndColumn = new int[2];
            //             rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //             newComulPath.addElement(rowAndColumn);
                        
            //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + 1, column + 1, false, true, false);
            //         }
            //     }else if(pieceChar == 'B'){
            //         char lastSlotChar = ' ';
            //         char nextSlotChar;
            //         for(int i = 1; i < 8; i++){
            //             nextSlotChar = getSlotChar(board, row + i, column + i);

            //             if((lastSlotChar == 'a' || lastSlotChar == 'A') && (nextSlotChar == 'a' || nextSlotChar == 'A')){
            //                 break;
            //             }

            //             if(nextSlotChar == 'b' || nextSlotChar == 'B' || nextSlotChar == '!'){
            //                 break;
            //             }else if(nextSlotChar == ' '){
            //                 Vector<int[]> path = new Vector<int[]>();
            //                 path = clonePath(comulPath);
            //                 //add the carrent piece to the path (from)
            //                 int [] rowAndColumn = new int[2];
            //                 rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //                 path.addElement(rowAndColumn);

            //                 //add the next empty piece to the path (to)
            //                 int [] rowAndColumn2 = new int[2];
            //                 rowAndColumn2[0] = row + i; rowAndColumn2[1] = column + i; 
            //                 path.addElement(rowAndColumn2);

            //                 //add the path to the list of chooses
            //                 listChooses.addElement(path);
            //             }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
            //                 char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + i, column + i);

            //                 Vector<int[]> newComulPath = new Vector<int[]>();
            //                 newComulPath = clonePath(comulPath);
            //                 //add the carrent piece to the path (from)
            //                 int [] rowAndColumn = new int[2];
            //                 rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //                 newComulPath.addElement(rowAndColumn);
                        
            //                 getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + i, column + i, false, true, false);
            //             }

            //             lastSlotChar = nextSlotChar;
            //         }
            //     }else if(pieceChar == 'A'){
            //         char lastSlotChar = ' ';
            //         char nextSlotChar;
            //         for(int i = 1; i < 8; i++){
            //             nextSlotChar = getSlotChar(board, row + i, column + i);

            //             if((lastSlotChar == 'b' || lastSlotChar == 'B') && (nextSlotChar == 'b' || nextSlotChar == 'B')){
            //                 break;
            //             }

            //             if(nextSlotChar == 'a' || nextSlotChar == 'A' || nextSlotChar == '!'){
            //                 break;
            //             }else if(nextSlotChar == ' '){
            //                 Vector<int[]> path = new Vector<int[]>();
            //                 path = clonePath(comulPath);
            //                 //add the carrent piece to the path (from)
            //                 int [] rowAndColumn = new int[2];
            //                 rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //                 path.addElement(rowAndColumn);

            //                 //add the next empty piece to the path (to)
            //                 int [] rowAndColumn2 = new int[2];
            //                 rowAndColumn2[0] = row + i; rowAndColumn2[1] = column + i; 
            //                 path.addElement(rowAndColumn2);

            //                 //add the path to the list of chooses
            //                 listChooses.addElement(path);
            //             }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
            //                 char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + i, column + i);

            //                 Vector<int[]> newComulPath = new Vector<int[]>();
            //                 newComulPath = clonePath(comulPath);
            //                 //add the carrent piece to the path (from)
            //                 int [] rowAndColumn = new int[2];
            //                 rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //                 newComulPath.addElement(rowAndColumn);
                        
            //                 getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + i, column + i, false, true, false);
            //             }

            //             lastSlotChar = nextSlotChar;
            //         }
            //     }
            // }
            // else if(row == 0 && column == 7){
            //     if(pieceChar == 'b'){
            //         if(getSlotChar(board, row + 1, column - 1) == ' '){
            //             Vector<int[]> path = new Vector<int[]>();
            //             path = clonePath(comulPath);
            //             //add the carrent piece to the path (from)
            //             int [] rowAndColumn = new int[2];
            //             rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //             path.addElement(rowAndColumn);

            //             //add the next empty piece to the path (to)
            //             int [] rowAndColumn2 = new int[2];
            //             rowAndColumn2[0] = row + 1; rowAndColumn2[1] = column - 1; 
            //             path.addElement(rowAndColumn2);

            //             //add the path to the list of chooses
            //             listChooses.addElement(path);
            //         }else if(getSlotChar(board, row + 1, column - 1) == 'a' || getSlotChar(board, row + 1, column - 1) == 'A'){
            //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + 1, column - 1);

            //             Vector<int[]> newComulPath = new Vector<int[]>();
            //             //add the carrent piece to the path (from)
            //             int [] rowAndColumn = new int[2];
            //             rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //             newComulPath.addElement(rowAndColumn);
                        
            //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + 1, column - 1, false, true, false);
            //         }
            //     }else if(pieceChar == 'B'){
            //         char lastSlotChar = ' ';
            //         char nextSlotChar;
            //         for(int i = 1; i < 8; i++){
            //             nextSlotChar = getSlotChar(board, row + i, column - i);

            //             if((lastSlotChar == 'a' || lastSlotChar == 'A') && (nextSlotChar == 'a' || nextSlotChar == 'A')){
            //                 break;
            //             }

            //             if(nextSlotChar == 'b' || nextSlotChar == 'B' || nextSlotChar == '!'){
            //                 break;
            //             }else if(nextSlotChar == ' '){
            //                 Vector<int[]> path = new Vector<int[]>();
            //                 path = clonePath(comulPath);
            //                 //add the carrent piece to the path (from)
            //                 int [] rowAndColumn = new int[2];
            //                 rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //                 path.addElement(rowAndColumn);

            //                 //add the next empty piece to the path (to)
            //                 int [] rowAndColumn2 = new int[2];
            //                 rowAndColumn2[0] = row + i; rowAndColumn2[1] = column - i; 
            //                 path.addElement(rowAndColumn2);

            //                 //add the path to the list of chooses
            //                 listChooses.addElement(path);
            //             }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
            //                 char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + i, column - i);

            //                 Vector<int[]> newComulPath = new Vector<int[]>();
            //                 newComulPath = clonePath(comulPath);
            //                 //add the carrent piece to the path (from)
            //                 int [] rowAndColumn = new int[2];
            //                 rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //                 newComulPath.addElement(rowAndColumn);
                        
            //                 getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + i, column - i, false, true, false);
            //             }

            //             lastSlotChar = nextSlotChar;
            //         }
            //     }else if(pieceChar == 'A'){
            //         char lastSlotChar = ' ';
            //         char nextSlotChar;
            //         for(int i = 1; i < 8; i++){
            //             nextSlotChar = getSlotChar(board, row + i, column - i);

            //             if((lastSlotChar == 'b' || lastSlotChar == 'B') && (nextSlotChar == 'b' || nextSlotChar == 'B')){
            //                 break;
            //             }

            //             if(nextSlotChar == 'a' || nextSlotChar == 'A' || nextSlotChar == '!'){
            //                 break;
            //             }else if(nextSlotChar == ' '){
            //                 Vector<int[]> path = new Vector<int[]>();
            //                 path = clonePath(comulPath);
            //                 //add the carrent piece to the path (from)
            //                 int [] rowAndColumn = new int[2];
            //                 rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //                 path.addElement(rowAndColumn);

            //                 //add the next empty piece to the path (to)
            //                 int [] rowAndColumn2 = new int[2];
            //                 rowAndColumn2[0] = row + i; rowAndColumn2[1] = column - i; 
            //                 path.addElement(rowAndColumn2);

            //                 //add the path to the list of chooses
            //                 listChooses.addElement(path);
            //             }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
            //                 char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + i, column - i);

            //                 Vector<int[]> newComulPath = new Vector<int[]>();
            //                 newComulPath = clonePath(comulPath);
            //                 //add the carrent piece to the path (from)
            //                 int [] rowAndColumn = new int[2];
            //                 rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //                 newComulPath.addElement(rowAndColumn);
                        
            //                 getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + i, column - i, false, true, false);
            //             }

            //             lastSlotChar = nextSlotChar;
            //         }
            //     }
            // }
            // else if(row == 7 && column == 0){
            //     if(pieceChar == 'a'){
            //         if(getSlotChar(board, row - 1, column + 1) == ' '){
            //             Vector<int[]> path = new Vector<int[]>();
            //             path = clonePath(comulPath);
            //             //add the carrent piece to the path (from)
            //             int [] rowAndColumn = new int[2];
            //             rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //             path.addElement(rowAndColumn);

            //             //add the next empty piece to the path (to)
            //             int [] rowAndColumn2 = new int[2];
            //             rowAndColumn2[0] = row - 1; rowAndColumn2[1] = column + 1; 
            //             path.addElement(rowAndColumn2);

            //             //add the path to the list of chooses
            //             listChooses.addElement(path);
            //         }else if(getSlotChar(board, row - 1, column + 1) == 'b' || getSlotChar(board, row - 1, column + 1) == 'B'){
            //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - 1, column + 1);

            //             Vector<int[]> newComulPath = new Vector<int[]>();
            //             //add the carrent piece to the path (from)
            //             int [] rowAndColumn = new int[2];
            //             rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //             newComulPath.addElement(rowAndColumn);
                        
            //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - 1, column + 1, false, true, false);
            //         }
            //     }else if(pieceChar == 'A'){
            //         char lastSlotChar = ' ';
            //         char nextSlotChar;
            //         for(int i = 1; i < 8; i++){
            //             nextSlotChar = getSlotChar(board, row - i, column + i);

            //             if((lastSlotChar == 'b' || lastSlotChar == 'B') && (nextSlotChar == 'b' || nextSlotChar == 'B')){
            //                 break;
            //             }

            //             if(nextSlotChar == 'a' || nextSlotChar == 'A' || nextSlotChar == '!'){
            //                 break;
            //             }else if(nextSlotChar == ' '){
            //                 Vector<int[]> path = new Vector<int[]>();
            //                 path = clonePath(comulPath);
            //                 //add the carrent piece to the path (from)
            //                 int [] rowAndColumn = new int[2];
            //                 rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //                 path.addElement(rowAndColumn);

            //                 //add the next empty piece to the path (to)
            //                 int [] rowAndColumn2 = new int[2];
            //                 rowAndColumn2[0] = row - i; rowAndColumn2[1] = column + i; 
            //                 path.addElement(rowAndColumn2);

            //                 //add the path to the list of chooses
            //                 listChooses.addElement(path);
            //             }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
            //                 char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - i, column + i);

            //                 Vector<int[]> newComulPath = new Vector<int[]>();
            //                 newComulPath = clonePath(comulPath);
            //                 //add the carrent piece to the path (from)
            //                 int [] rowAndColumn = new int[2];
            //                 rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //                 newComulPath.addElement(rowAndColumn);
                        
            //                 getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - i, column + i, false, true, false);
            //             }

            //             lastSlotChar = nextSlotChar;
            //         }
            //     }else if(pieceChar == 'B'){
            //         char lastSlotChar = ' ';
            //         char nextSlotChar;
            //         for(int i = 1; i < 8; i++){
            //             nextSlotChar = getSlotChar(board, row - i, column + i);

            //             if((lastSlotChar == 'a' || lastSlotChar == 'A') && (nextSlotChar == 'a' || nextSlotChar == 'A')){
            //                 break;
            //             }

            //             if(nextSlotChar == 'b' || nextSlotChar == 'B' || nextSlotChar == '!'){
            //                 break;
            //             }else if(nextSlotChar == ' '){
            //                 Vector<int[]> path = new Vector<int[]>();
            //                 path = clonePath(comulPath);
            //                 //add the carrent piece to the path (from)
            //                 int [] rowAndColumn = new int[2];
            //                 rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //                 path.addElement(rowAndColumn);

            //                 //add the next empty piece to the path (to)
            //                 int [] rowAndColumn2 = new int[2];
            //                 rowAndColumn2[0] = row - i; rowAndColumn2[1] = column + i; 
            //                 path.addElement(rowAndColumn2);

            //                 //add the path to the list of chooses
            //                 listChooses.addElement(path);
            //             }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
            //                 char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - i, column + i);

            //                 Vector<int[]> newComulPath = new Vector<int[]>();
            //                 newComulPath = clonePath(comulPath);
            //                 //add the carrent piece to the path (from)
            //                 int [] rowAndColumn = new int[2];
            //                 rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //                 newComulPath.addElement(rowAndColumn);
                        
            //                 getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - i, column + i, false, true, false);
            //             }

            //             lastSlotChar = nextSlotChar;
            //         }
            //     }
            // }
            // else if(row == 7 && column == 7){
            //     if(pieceChar == 'a'){
            //         if(getSlotChar(board, row - 1, column - 1) == ' '){
            //             Vector<int[]> path = new Vector<int[]>();
            //             path = clonePath(comulPath);
            //             //add the carrent piece to the path (from)
            //             int [] rowAndColumn = new int[2];
            //             rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //             path.addElement(rowAndColumn);

            //             //add the next empty piece to the path (to)
            //             int [] rowAndColumn2 = new int[2];
            //             rowAndColumn2[0] = row - 1; rowAndColumn2[1] = column - 1; 
            //             path.addElement(rowAndColumn2);

            //             //add the path to the list of chooses
            //             listChooses.addElement(path);
            //         }else if(getSlotChar(board, row - 1, column - 1) == 'b' || getSlotChar(board, row - 1, column - 1) == 'B'){
            //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - 1, column - 1);

            //             Vector<int[]> newComulPath = new Vector<int[]>();
            //             //add the carrent piece to the path (from)
            //             int [] rowAndColumn = new int[2];
            //             rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //             newComulPath.addElement(rowAndColumn);
                        
            //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - 1, column - 1, false, true, false);
            //         }
            //     }else if(pieceChar == 'A'){
            //         char lastSlotChar = ' ';
            //         char nextSlotChar;
            //         for(int i = 1; i < 8; i++){
            //             nextSlotChar = getSlotChar(board, row - i, column - i);

            //             if((lastSlotChar == 'b' || lastSlotChar == 'B') && (nextSlotChar == 'b' || nextSlotChar == 'B')){
            //                 break;
            //             }

            //             if(nextSlotChar == 'a' || nextSlotChar == 'A' || nextSlotChar == '!'){
            //                 break;
            //             }else if(nextSlotChar == ' '){
            //                 Vector<int[]> path = new Vector<int[]>();
            //                 path = clonePath(comulPath);
            //                 //add the carrent piece to the path (from)
            //                 int [] rowAndColumn = new int[2];
            //                 rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //                 path.addElement(rowAndColumn);

            //                 //add the next empty piece to the path (to)
            //                 int [] rowAndColumn2 = new int[2];
            //                 rowAndColumn2[0] = row - i; rowAndColumn2[1] = column - i; 
            //                 path.addElement(rowAndColumn2);

            //                 //add the path to the list of chooses
            //                 listChooses.addElement(path);
            //             }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
            //                 char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - i, column - i);

            //                 Vector<int[]> newComulPath = new Vector<int[]>();
            //                 newComulPath = clonePath(comulPath);
            //                 //add the carrent piece to the path (from)
            //                 int [] rowAndColumn = new int[2];
            //                 rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //                 newComulPath.addElement(rowAndColumn);
                        
            //                 getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - i, column - i, false, true, false);
            //             }

            //             lastSlotChar = nextSlotChar;
            //         }
            //     }else if(pieceChar == 'B'){
            //         char lastSlotChar = ' ';
            //         char nextSlotChar;
            //         for(int i = 1; i < 8; i++){
            //             nextSlotChar = getSlotChar(board, row - i, column - i);

            //             if((lastSlotChar == 'a' || lastSlotChar == 'A') && (nextSlotChar == 'a' || nextSlotChar == 'A')){
            //                 break;
            //             }

            //             if(nextSlotChar == 'b' || nextSlotChar == 'B' || nextSlotChar == '!'){
            //                 break;
            //             }else if(nextSlotChar == ' '){
            //                 Vector<int[]> path = new Vector<int[]>();
            //                 path = clonePath(comulPath);
            //                 //add the carrent piece to the path (from)
            //                 int [] rowAndColumn = new int[2];
            //                 rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //                 path.addElement(rowAndColumn);

            //                 //add the next empty piece to the path (to)
            //                 int [] rowAndColumn2 = new int[2];
            //                 rowAndColumn2[0] = row - i; rowAndColumn2[1] = column - i; 
            //                 path.addElement(rowAndColumn2);

            //                 //add the path to the list of chooses
            //                 listChooses.addElement(path);
            //             }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
            //                 char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - i, column - i);

            //                 Vector<int[]> newComulPath = new Vector<int[]>();
            //                 newComulPath = clonePath(comulPath);
            //                 //add the carrent piece to the path (from)
            //                 int [] rowAndColumn = new int[2];
            //                 rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //                 newComulPath.addElement(rowAndColumn);
                        
            //                 getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - i, column - i, false, true, false);
            //             }

            //             lastSlotChar = nextSlotChar;
            //         }
            //     }
            // }

            // //---------------- touch lines ----------------//
            // else if(row == 0){
            //     if(pieceChar == 'b'){
            //         if(getSlotChar(board, row + 1, column + 1) == ' '){
            //             Vector<int[]> path = new Vector<int[]>();
            //             path = clonePath(comulPath);
            //             //add the carrent piece to the path (from)
            //             int [] rowAndColumn = new int[2];
            //             rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //             path.addElement(rowAndColumn);

            //             //add the next empty piece to the path (to)
            //             int [] rowAndColumn2 = new int[2];
            //             rowAndColumn2[0] = row + 1; rowAndColumn2[1] = column + 1; 
            //             path.addElement(rowAndColumn2);

            //             //add the path to the list of chooses
            //             listChooses.addElement(path);
            //         }else if(getSlotChar(board, row + 1, column + 1) == 'a' || getSlotChar(board, row + 1, column + 1) == 'A'){
            //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + 1, column + 1);

            //             Vector<int[]> newComulPath = new Vector<int[]>();
            //             //add the carrent piece to the path (from)
            //             int [] rowAndColumn = new int[2];
            //             rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //             newComulPath.addElement(rowAndColumn);
                        
            //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + 1, column + 1, false, true, false);
            //         }
            //     }else if(pieceChar == 'B'){
            //         char lastSlotChar = ' ';
            //         char nextSlotChar;
            //         for(int i = 1; i < 8; i++){
            //             nextSlotChar = getSlotChar(board, row + i, column + i);

            //             if((lastSlotChar == 'a' || lastSlotChar == 'A') && (nextSlotChar == 'a' || nextSlotChar == 'A')){
            //                 break;
            //             }

            //             if(nextSlotChar == 'b' || nextSlotChar == 'B' || nextSlotChar == '!'){
            //                 break;
            //             }else if(nextSlotChar == ' '){
            //                 Vector<int[]> path = new Vector<int[]>();
            //                 path = clonePath(comulPath);
            //                 //add the carrent piece to the path (from)
            //                 int [] rowAndColumn = new int[2];
            //                 rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //                 path.addElement(rowAndColumn);

            //                 //add the next empty piece to the path (to)
            //                 int [] rowAndColumn2 = new int[2];
            //                 rowAndColumn2[0] = row + i; rowAndColumn2[1] = column + i; 
            //                 path.addElement(rowAndColumn2);

            //                 //add the path to the list of chooses
            //                 listChooses.addElement(path);
            //             }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
            //                 char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + i, column + i);

            //                 Vector<int[]> newComulPath = new Vector<int[]>();
            //                 newComulPath = clonePath(comulPath);
            //                 //add the carrent piece to the path (from)
            //                 int [] rowAndColumn = new int[2];
            //                 rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //                 newComulPath.addElement(rowAndColumn);
                        
            //                 getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + i, column + i, false, true, false);
            //             }

            //             lastSlotChar = nextSlotChar;
            //         }
            //     }else if(pieceChar == 'A'){
            //         char lastSlotChar = ' ';
            //         char nextSlotChar;
            //         for(int i = 1; i < 8; i++){
            //             nextSlotChar = getSlotChar(board, row + i, column + i);

            //             if((lastSlotChar == 'b' || lastSlotChar == 'B') && (nextSlotChar == 'b' || nextSlotChar == 'B')){
            //                 break;
            //             }

            //             if(nextSlotChar == 'a' || nextSlotChar == 'A' || nextSlotChar == '!'){
            //                 break;
            //             }else if(nextSlotChar == ' '){
            //                 Vector<int[]> path = new Vector<int[]>();
            //                 path = clonePath(comulPath);
            //                 //add the carrent piece to the path (from)
            //                 int [] rowAndColumn = new int[2];
            //                 rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //                 path.addElement(rowAndColumn);

            //                 //add the next empty piece to the path (to)
            //                 int [] rowAndColumn2 = new int[2];
            //                 rowAndColumn2[0] = row + i; rowAndColumn2[1] = column + i; 
            //                 path.addElement(rowAndColumn2);

            //                 //add the path to the list of chooses
            //                 listChooses.addElement(path);
            //             }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
            //                 char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + i, column + i);

            //                 Vector<int[]> newComulPath = new Vector<int[]>();
            //                 newComulPath = clonePath(comulPath);
            //                 //add the carrent piece to the path (from)
            //                 int [] rowAndColumn = new int[2];
            //                 rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //                 newComulPath.addElement(rowAndColumn);
                        
            //                 getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + i, column + i, false, true, false);
            //             }

            //             lastSlotChar = nextSlotChar;
            //         }
            //     }

            //     //------------------------------------------------

            //     if(pieceChar == 'b'){
            //         if(getSlotChar(board, row + 1, column - 1) == ' '){
            //             Vector<int[]> path = new Vector<int[]>();
            //             path = clonePath(comulPath);
            //             //add the carrent piece to the path (from)
            //             int [] rowAndColumn = new int[2];
            //             rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //             path.addElement(rowAndColumn);

            //             //add the next empty piece to the path (to)
            //             int [] rowAndColumn2 = new int[2];
            //             rowAndColumn2[0] = row + 1; rowAndColumn2[1] = column - 1; 
            //             path.addElement(rowAndColumn2);

            //             //add the path to the list of chooses
            //             listChooses.addElement(path);
            //         }else if(getSlotChar(board, row + 1, column - 1) == 'a' || getSlotChar(board, row + 1, column - 1) == 'A'){
            //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + 1, column - 1);

            //             Vector<int[]> newComulPath = new Vector<int[]>();
            //             //add the carrent piece to the path (from)
            //             int [] rowAndColumn = new int[2];
            //             rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //             newComulPath.addElement(rowAndColumn);
                        
            //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + 1, column - 1, false, true, false);
            //         }
            //     }else if(pieceChar == 'B'){
            //         char lastSlotChar = ' ';
            //         char nextSlotChar;
            //         for(int i = 1; i < 8; i++){
            //             nextSlotChar = getSlotChar(board, row + i, column - i);

            //             if((lastSlotChar == 'a' || lastSlotChar == 'A') && (nextSlotChar == 'a' || nextSlotChar == 'A')){
            //                 break;
            //             }

            //             if(nextSlotChar == 'b' || nextSlotChar == 'B' || nextSlotChar == '!'){
            //                 break;
            //             }else if(nextSlotChar == ' '){
            //                 Vector<int[]> path = new Vector<int[]>();
            //                 path = clonePath(comulPath);
            //                 //add the carrent piece to the path (from)
            //                 int [] rowAndColumn = new int[2];
            //                 rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //                 path.addElement(rowAndColumn);

            //                 //add the next empty piece to the path (to)
            //                 int [] rowAndColumn2 = new int[2];
            //                 rowAndColumn2[0] = row + i; rowAndColumn2[1] = column - i; 
            //                 path.addElement(rowAndColumn2);

            //                 //add the path to the list of chooses
            //                 listChooses.addElement(path);
            //             }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
            //                 char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + i, column - i);

            //                 Vector<int[]> newComulPath = new Vector<int[]>();
            //                 newComulPath = clonePath(comulPath);
            //                 //add the carrent piece to the path (from)
            //                 int [] rowAndColumn = new int[2];
            //                 rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //                 newComulPath.addElement(rowAndColumn);
                        
            //                 getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + i, column - i, false, true, false);
            //             }

            //             lastSlotChar = nextSlotChar;
            //         }
            //     }else if(pieceChar == 'A'){
            //         char lastSlotChar = ' ';
            //         char nextSlotChar;
            //         for(int i = 1; i < 8; i++){
            //             nextSlotChar = getSlotChar(board, row + i, column - i);

            //             if((lastSlotChar == 'b' || lastSlotChar == 'B') && (nextSlotChar == 'b' || nextSlotChar == 'B')){
            //                 break;
            //             }

            //             if(nextSlotChar == 'a' || nextSlotChar == 'A' || nextSlotChar == '!'){
            //                 break;
            //             }else if(nextSlotChar == ' '){
            //                 Vector<int[]> path = new Vector<int[]>();
            //                 path = clonePath(comulPath);
            //                 //add the carrent piece to the path (from)
            //                 int [] rowAndColumn = new int[2];
            //                 rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //                 path.addElement(rowAndColumn);

            //                 //add the next empty piece to the path (to)
            //                 int [] rowAndColumn2 = new int[2];
            //                 rowAndColumn2[0] = row + i; rowAndColumn2[1] = column - i; 
            //                 path.addElement(rowAndColumn2);

            //                 //add the path to the list of chooses
            //                 listChooses.addElement(path);
            //             }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
            //                 char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + i, column - i);

            //                 Vector<int[]> newComulPath = new Vector<int[]>();
            //                 newComulPath = clonePath(comulPath);
            //                 //add the carrent piece to the path (from)
            //                 int [] rowAndColumn = new int[2];
            //                 rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //                 newComulPath.addElement(rowAndColumn);
                        
            //                 getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + i, column - i, false, true, false);
            //             }

            //             lastSlotChar = nextSlotChar;
            //         }
            //     }
            // } else if(column == 0){
            //     if(pieceChar == 'b'){
            //         if(getSlotChar(board, row + 1, column + 1) == ' '){
            //             Vector<int[]> path = new Vector<int[]>();
            //             path = clonePath(comulPath);
            //             //add the carrent piece to the path (from)
            //             int [] rowAndColumn = new int[2];
            //             rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //             path.addElement(rowAndColumn);

            //             //add the next empty piece to the path (to)
            //             int [] rowAndColumn2 = new int[2];
            //             rowAndColumn2[0] = row + 1; rowAndColumn2[1] = column + 1; 
            //             path.addElement(rowAndColumn2);

            //             //add the path to the list of chooses
            //             listChooses.addElement(path);
            //         }else if(getSlotChar(board, row + 1, column + 1) == 'a' || getSlotChar(board, row + 1, column + 1) == 'A'){
            //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + 1, column + 1);

            //             Vector<int[]> newComulPath = new Vector<int[]>();
            //             //add the carrent piece to the path (from)
            //             int [] rowAndColumn = new int[2];
            //             rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //             newComulPath.addElement(rowAndColumn);
                        
            //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + 1, column + 1, false, true, false);
            //         }
            //     }else if(pieceChar == 'B'){
            //         char lastSlotChar = ' ';
            //         char nextSlotChar;
            //         for(int i = 1; i < 8; i++){
            //             nextSlotChar = getSlotChar(board, row + i, column + i);

            //             if((lastSlotChar == 'a' || lastSlotChar == 'A') && (nextSlotChar == 'a' || nextSlotChar == 'A')){
            //                 break;
            //             }

            //             if(nextSlotChar == 'b' || nextSlotChar == 'B' || nextSlotChar == '!'){
            //                 break;
            //             }else if(nextSlotChar == ' '){
            //                 Vector<int[]> path = new Vector<int[]>();
            //                 path = clonePath(comulPath);
            //                 //add the carrent piece to the path (from)
            //                 int [] rowAndColumn = new int[2];
            //                 rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //                 path.addElement(rowAndColumn);

            //                 //add the next empty piece to the path (to)
            //                 int [] rowAndColumn2 = new int[2];
            //                 rowAndColumn2[0] = row + i; rowAndColumn2[1] = column + i; 
            //                 path.addElement(rowAndColumn2);

            //                 //add the path to the list of chooses
            //                 listChooses.addElement(path);
            //             }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
            //                 char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + i, column + i);

            //                 Vector<int[]> newComulPath = new Vector<int[]>();
            //                 newComulPath = clonePath(comulPath);
            //                 //add the carrent piece to the path (from)
            //                 int [] rowAndColumn = new int[2];
            //                 rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //                 newComulPath.addElement(rowAndColumn);
                        
            //                 getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + i, column + i, false, true, false);
            //             }

            //             lastSlotChar = nextSlotChar;
            //         }
            //     }else if(pieceChar == 'A'){
            //         char lastSlotChar = ' ';
            //         char nextSlotChar;
            //         for(int i = 1; i < 8; i++){
            //             nextSlotChar = getSlotChar(board, row + i, column + i);

            //             if((lastSlotChar == 'b' || lastSlotChar == 'B') && (nextSlotChar == 'b' || nextSlotChar == 'B')){
            //                 break;
            //             }

            //             if(nextSlotChar == 'a' || nextSlotChar == 'A' || nextSlotChar == '!'){
            //                 break;
            //             }else if(nextSlotChar == ' '){
            //                 Vector<int[]> path = new Vector<int[]>();
            //                 path = clonePath(comulPath);
            //                 //add the carrent piece to the path (from)
            //                 int [] rowAndColumn = new int[2];
            //                 rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //                 path.addElement(rowAndColumn);

            //                 //add the next empty piece to the path (to)
            //                 int [] rowAndColumn2 = new int[2];
            //                 rowAndColumn2[0] = row + i; rowAndColumn2[1] = column + i; 
            //                 path.addElement(rowAndColumn2);

            //                 //add the path to the list of chooses
            //                 listChooses.addElement(path);
            //             }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
            //                 char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + i, column + i);

            //                 Vector<int[]> newComulPath = new Vector<int[]>();
            //                 newComulPath = clonePath(comulPath);
            //                 //add the carrent piece to the path (from)
            //                 int [] rowAndColumn = new int[2];
            //                 rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //                 newComulPath.addElement(rowAndColumn);
                        
            //                 getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + i, column + i, false, true, false);
            //             }

            //             lastSlotChar = nextSlotChar;
            //         }
            //     }

            //     //-------------------------------------------------

            //     if(pieceChar == 'a'){
            //         if(getSlotChar(board, row - 1, column + 1) == ' '){
            //             Vector<int[]> path = new Vector<int[]>();
            //             path = clonePath(comulPath);
            //             //add the carrent piece to the path (from)
            //             int [] rowAndColumn = new int[2];
            //             rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //             path.addElement(rowAndColumn);

            //             //add the next empty piece to the path (to)
            //             int [] rowAndColumn2 = new int[2];
            //             rowAndColumn2[0] = row - 1; rowAndColumn2[1] = column + 1; 
            //             path.addElement(rowAndColumn2);

            //             //add the path to the list of chooses
            //             listChooses.addElement(path);
            //         }else if(getSlotChar(board, row - 1, column + 1) == 'b' || getSlotChar(board, row - 1, column + 1) == 'B'){
            //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - 1, column + 1);

            //             Vector<int[]> newComulPath = new Vector<int[]>();
            //             //add the carrent piece to the path (from)
            //             int [] rowAndColumn = new int[2];
            //             rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //             newComulPath.addElement(rowAndColumn);
                        
            //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - 1, column + 1, false, true, false);
            //         }
            //     }else if(pieceChar == 'A'){
            //         char lastSlotChar = ' ';
            //         char nextSlotChar;
            //         for(int i = 1; i < 8; i++){
            //             nextSlotChar = getSlotChar(board, row - i, column + i);

            //             if((lastSlotChar == 'b' || lastSlotChar == 'B') && (nextSlotChar == 'b' || nextSlotChar == 'B')){
            //                 break;
            //             }

            //             if(nextSlotChar == 'a' || nextSlotChar == 'A' || nextSlotChar == '!'){
            //                 break;
            //             }else if(nextSlotChar == ' '){
            //                 Vector<int[]> path = new Vector<int[]>();
            //                 path = clonePath(comulPath);
            //                 //add the carrent piece to the path (from)
            //                 int [] rowAndColumn = new int[2];
            //                 rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //                 path.addElement(rowAndColumn);

            //                 //add the next empty piece to the path (to)
            //                 int [] rowAndColumn2 = new int[2];
            //                 rowAndColumn2[0] = row - i; rowAndColumn2[1] = column + i; 
            //                 path.addElement(rowAndColumn2);

            //                 //add the path to the list of chooses
            //                 listChooses.addElement(path);
            //             }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
            //                 char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - i, column + i);

            //                 Vector<int[]> newComulPath = new Vector<int[]>();
            //                 newComulPath = clonePath(comulPath);
            //                 //add the carrent piece to the path (from)
            //                 int [] rowAndColumn = new int[2];
            //                 rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //                 newComulPath.addElement(rowAndColumn);
                        
            //                 getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - i, column + i, false, true, false);
            //             }

            //             lastSlotChar = nextSlotChar;
            //         }
            //     }else if(pieceChar == 'B'){
            //         char lastSlotChar = ' ';
            //         char nextSlotChar;
            //         for(int i = 1; i < 8; i++){
            //             nextSlotChar = getSlotChar(board, row - i, column + i);

            //             if((lastSlotChar == 'a' || lastSlotChar == 'A') && (nextSlotChar == 'a' || nextSlotChar == 'A')){
            //                 break;
            //             }

            //             if(nextSlotChar == 'b' || nextSlotChar == 'B' || nextSlotChar == '!'){
            //                 break;
            //             }else if(nextSlotChar == ' '){
            //                 Vector<int[]> path = new Vector<int[]>();
            //                 path = clonePath(comulPath);
            //                 //add the carrent piece to the path (from)
            //                 int [] rowAndColumn = new int[2];
            //                 rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //                 path.addElement(rowAndColumn);

            //                 //add the next empty piece to the path (to)
            //                 int [] rowAndColumn2 = new int[2];
            //                 rowAndColumn2[0] = row - i; rowAndColumn2[1] = column + i; 
            //                 path.addElement(rowAndColumn2);

            //                 //add the path to the list of chooses
            //                 listChooses.addElement(path);
            //             }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
            //                 char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - i, column + i);

            //                 Vector<int[]> newComulPath = new Vector<int[]>();
            //                 newComulPath = clonePath(comulPath);
            //                 //add the carrent piece to the path (from)
            //                 int [] rowAndColumn = new int[2];
            //                 rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //                 newComulPath.addElement(rowAndColumn);
                        
            //                 getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - i, column + i, false, true, false);
            //             }

            //             lastSlotChar = nextSlotChar;
            //         }
            //     }
            // } else if (row == 7){
            //     if(pieceChar == 'a'){
            //         if(getSlotChar(board, row - 1, column + 1) == ' '){
            //             Vector<int[]> path = new Vector<int[]>();
            //             path = clonePath(comulPath);
            //             //add the carrent piece to the path (from)
            //             int [] rowAndColumn = new int[2];
            //             rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //             path.addElement(rowAndColumn);

            //             //add the next empty piece to the path (to)
            //             int [] rowAndColumn2 = new int[2];
            //             rowAndColumn2[0] = row - 1; rowAndColumn2[1] = column + 1; 
            //             path.addElement(rowAndColumn2);

            //             //add the path to the list of chooses
            //             listChooses.addElement(path);
            //         }else if(getSlotChar(board, row - 1, column + 1) == 'b' || getSlotChar(board, row - 1, column + 1) == 'B'){
            //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - 1, column + 1);

            //             Vector<int[]> newComulPath = new Vector<int[]>();
            //             //add the carrent piece to the path (from)
            //             int [] rowAndColumn = new int[2];
            //             rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //             newComulPath.addElement(rowAndColumn);
                        
            //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - 1, column + 1, false, true, false);
            //         }
            //     }else if(pieceChar == 'A'){
            //         char lastSlotChar = ' ';
            //         char nextSlotChar;
            //         for(int i = 1; i < 8; i++){
            //             nextSlotChar = getSlotChar(board, row - i, column + i);

            //             if((lastSlotChar == 'b' || lastSlotChar == 'B') && (nextSlotChar == 'b' || nextSlotChar == 'B')){
            //                 break;
            //             }

            //             if(nextSlotChar == 'a' || nextSlotChar == 'A' || nextSlotChar == '!'){
            //                 break;
            //             }else if(nextSlotChar == ' '){
            //                 Vector<int[]> path = new Vector<int[]>();
            //                 path = clonePath(comulPath);
            //                 //add the carrent piece to the path (from)
            //                 int [] rowAndColumn = new int[2];
            //                 rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //                 path.addElement(rowAndColumn);

            //                 //add the next empty piece to the path (to)
            //                 int [] rowAndColumn2 = new int[2];
            //                 rowAndColumn2[0] = row - i; rowAndColumn2[1] = column + i; 
            //                 path.addElement(rowAndColumn2);

            //                 //add the path to the list of chooses
            //                 listChooses.addElement(path);
            //             }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
            //                 char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - i, column + i);

            //                 Vector<int[]> newComulPath = new Vector<int[]>();
            //                 newComulPath = clonePath(comulPath);
            //                 //add the carrent piece to the path (from)
            //                 int [] rowAndColumn = new int[2];
            //                 rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //                 newComulPath.addElement(rowAndColumn);
                        
            //                 getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - i, column + i, false, true, false);
            //             }

            //             lastSlotChar = nextSlotChar;
            //         }
            //     }else if(pieceChar == 'B'){
            //         char lastSlotChar = ' ';
            //         char nextSlotChar;
            //         for(int i = 1; i < 8; i++){
            //             nextSlotChar = getSlotChar(board, row - i, column + i);

            //             if((lastSlotChar == 'a' || lastSlotChar == 'A') && (nextSlotChar == 'a' || nextSlotChar == 'A')){
            //                 break;
            //             }

            //             if(nextSlotChar == 'b' || nextSlotChar == 'B' || nextSlotChar == '!'){
            //                 break;
            //             }else if(nextSlotChar == ' '){
            //                 Vector<int[]> path = new Vector<int[]>();
            //                 path = clonePath(comulPath);
            //                 //add the carrent piece to the path (from)
            //                 int [] rowAndColumn = new int[2];
            //                 rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //                 path.addElement(rowAndColumn);

            //                 //add the next empty piece to the path (to)
            //                 int [] rowAndColumn2 = new int[2];
            //                 rowAndColumn2[0] = row - i; rowAndColumn2[1] = column + i; 
            //                 path.addElement(rowAndColumn2);

            //                 //add the path to the list of chooses
            //                 listChooses.addElement(path);
            //             }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
            //                 char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - i, column + i);

            //                 Vector<int[]> newComulPath = new Vector<int[]>();
            //                 newComulPath = clonePath(comulPath);
            //                 //add the carrent piece to the path (from)
            //                 int [] rowAndColumn = new int[2];
            //                 rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //                 newComulPath.addElement(rowAndColumn);
                        
            //                 getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - i, column + i, false, true, false);
            //             }

            //             lastSlotChar = nextSlotChar;
            //         }
            //     }

            //     //----------------------------------------------

            //     if(pieceChar == 'a'){
            //         if(getSlotChar(board, row - 1, column - 1) == ' '){
            //             Vector<int[]> path = new Vector<int[]>();
            //             path = clonePath(comulPath);
            //             //add the carrent piece to the path (from)
            //             int [] rowAndColumn = new int[2];
            //             rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //             path.addElement(rowAndColumn);

            //             //add the next empty piece to the path (to)
            //             int [] rowAndColumn2 = new int[2];
            //             rowAndColumn2[0] = row - 1; rowAndColumn2[1] = column - 1; 
            //             path.addElement(rowAndColumn2);

            //             //add the path to the list of chooses
            //             listChooses.addElement(path);
            //         }else if(getSlotChar(board, row - 1, column - 1) == 'b' || getSlotChar(board, row - 1, column - 1) == 'B'){
            //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - 1, column - 1);

            //             Vector<int[]> newComulPath = new Vector<int[]>();
            //             //add the carrent piece to the path (from)
            //             int [] rowAndColumn = new int[2];
            //             rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //             newComulPath.addElement(rowAndColumn);
                        
            //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - 1, column - 1, false, true, false);
            //         }
            //     }else if(pieceChar == 'A'){
            //         char lastSlotChar = ' ';
            //         char nextSlotChar;
            //         for(int i = 1; i < 8; i++){
            //             nextSlotChar = getSlotChar(board, row - i, column - i);

            //             if((lastSlotChar == 'b' || lastSlotChar == 'B') && (nextSlotChar == 'b' || nextSlotChar == 'B')){
            //                 break;
            //             }

            //             if(nextSlotChar == 'a' || nextSlotChar == 'A' || nextSlotChar == '!'){
            //                 break;
            //             }else if(nextSlotChar == ' '){
            //                 Vector<int[]> path = new Vector<int[]>();
            //                 path = clonePath(comulPath);
            //                 //add the carrent piece to the path (from)
            //                 int [] rowAndColumn = new int[2];
            //                 rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //                 path.addElement(rowAndColumn);

            //                 //add the next empty piece to the path (to)
            //                 int [] rowAndColumn2 = new int[2];
            //                 rowAndColumn2[0] = row - i; rowAndColumn2[1] = column - i; 
            //                 path.addElement(rowAndColumn2);

            //                 //add the path to the list of chooses
            //                 listChooses.addElement(path);
            //             }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
            //                 char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - i, column - i);

            //                 Vector<int[]> newComulPath = new Vector<int[]>();
            //                 newComulPath = clonePath(comulPath);
            //                 //add the carrent piece to the path (from)
            //                 int [] rowAndColumn = new int[2];
            //                 rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //                 newComulPath.addElement(rowAndColumn);
                        
            //                 getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - i, column - i, false, true, false);
            //             }

            //             lastSlotChar = nextSlotChar;
            //         }
            //     }else if(pieceChar == 'B'){
            //         char lastSlotChar = ' ';
            //         char nextSlotChar;
            //         for(int i = 1; i < 8; i++){
            //             nextSlotChar = getSlotChar(board, row - i, column - i);

            //             if((lastSlotChar == 'a' || lastSlotChar == 'A') && (nextSlotChar == 'a' || nextSlotChar == 'A')){
            //                 break;
            //             }

            //             if(nextSlotChar == 'b' || nextSlotChar == 'B' || nextSlotChar == '!'){
            //                 break;
            //             }else if(nextSlotChar == ' '){
            //                 Vector<int[]> path = new Vector<int[]>();
            //                 path = clonePath(comulPath);
            //                 //add the carrent piece to the path (from)
            //                 int [] rowAndColumn = new int[2];
            //                 rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //                 path.addElement(rowAndColumn);

            //                 //add the next empty piece to the path (to)
            //                 int [] rowAndColumn2 = new int[2];
            //                 rowAndColumn2[0] = row - i; rowAndColumn2[1] = column - i; 
            //                 path.addElement(rowAndColumn2);

            //                 //add the path to the list of chooses
            //                 listChooses.addElement(path);
            //             }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
            //                 char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - i, column - i);

            //                 Vector<int[]> newComulPath = new Vector<int[]>();
            //                 newComulPath = clonePath(comulPath);
            //                 //add the carrent piece to the path (from)
            //                 int [] rowAndColumn = new int[2];
            //                 rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //                 newComulPath.addElement(rowAndColumn);
                        
            //                 getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - i, column - i, false, true, false);
            //             }

            //             lastSlotChar = nextSlotChar;
            //         }
            //     }
            // } else if(column == 7){
            //     if(pieceChar == 'b'){
            //         if(getSlotChar(board, row + 1, column - 1) == ' '){
            //             Vector<int[]> path = new Vector<int[]>();
            //             path = clonePath(comulPath);
            //             //add the carrent piece to the path (from)
            //             int [] rowAndColumn = new int[2];
            //             rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //             path.addElement(rowAndColumn);

            //             //add the next empty piece to the path (to)
            //             int [] rowAndColumn2 = new int[2];
            //             rowAndColumn2[0] = row + 1; rowAndColumn2[1] = column - 1; 
            //             path.addElement(rowAndColumn2);

            //             //add the path to the list of chooses
            //             listChooses.addElement(path);
            //         }else if(getSlotChar(board, row + 1, column - 1) == 'a' || getSlotChar(board, row + 1, column - 1) == 'A'){
            //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + 1, column - 1);

            //             Vector<int[]> newComulPath = new Vector<int[]>();
            //             //add the carrent piece to the path (from)
            //             int [] rowAndColumn = new int[2];
            //             rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //             newComulPath.addElement(rowAndColumn);
                        
            //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + 1, column - 1, false, true, false);
            //         }
            //     }else if(pieceChar == 'B'){
            //         char lastSlotChar = ' ';
            //         char nextSlotChar;
            //         for(int i = 1; i < 8; i++){
            //             nextSlotChar = getSlotChar(board, row + i, column - i);

            //             if((lastSlotChar == 'a' || lastSlotChar == 'A') && (nextSlotChar == 'a' || nextSlotChar == 'A')){
            //                 break;
            //             }

            //             if(nextSlotChar == 'b' || nextSlotChar == 'B' || nextSlotChar == '!'){
            //                 break;
            //             }else if(nextSlotChar == ' '){
            //                 Vector<int[]> path = new Vector<int[]>();
            //                 path = clonePath(comulPath);
            //                 //add the carrent piece to the path (from)
            //                 int [] rowAndColumn = new int[2];
            //                 rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //                 path.addElement(rowAndColumn);

            //                 //add the next empty piece to the path (to)
            //                 int [] rowAndColumn2 = new int[2];
            //                 rowAndColumn2[0] = row + i; rowAndColumn2[1] = column - i; 
            //                 path.addElement(rowAndColumn2);

            //                 //add the path to the list of chooses
            //                 listChooses.addElement(path);
            //             }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
            //                 char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + i, column - i);

            //                 Vector<int[]> newComulPath = new Vector<int[]>();
            //                 newComulPath = clonePath(comulPath);
            //                 //add the carrent piece to the path (from)
            //                 int [] rowAndColumn = new int[2];
            //                 rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //                 newComulPath.addElement(rowAndColumn);
                        
            //                 getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + i, column - i, false, true, false);
            //             }

            //             lastSlotChar = nextSlotChar;
            //         }
            //     }else if(pieceChar == 'A'){
            //         char lastSlotChar = ' ';
            //         char nextSlotChar;
            //         for(int i = 1; i < 8; i++){
            //             nextSlotChar = getSlotChar(board, row + i, column - i);

            //             if((lastSlotChar == 'b' || lastSlotChar == 'B') && (nextSlotChar == 'b' || nextSlotChar == 'B')){
            //                 break;
            //             }

            //             if(nextSlotChar == 'a' || nextSlotChar == 'A' || nextSlotChar == '!'){
            //                 break;
            //             }else if(nextSlotChar == ' '){
            //                 Vector<int[]> path = new Vector<int[]>();
            //                 path = clonePath(comulPath);
            //                 //add the carrent piece to the path (from)
            //                 int [] rowAndColumn = new int[2];
            //                 rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //                 path.addElement(rowAndColumn);

            //                 //add the next empty piece to the path (to)
            //                 int [] rowAndColumn2 = new int[2];
            //                 rowAndColumn2[0] = row + i; rowAndColumn2[1] = column - i; 
            //                 path.addElement(rowAndColumn2);

            //                 //add the path to the list of chooses
            //                 listChooses.addElement(path);
            //             }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
            //                 char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + i, column - i);

            //                 Vector<int[]> newComulPath = new Vector<int[]>();
            //                 newComulPath = clonePath(comulPath);
            //                 //add the carrent piece to the path (from)
            //                 int [] rowAndColumn = new int[2];
            //                 rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //                 newComulPath.addElement(rowAndColumn);
                        
            //                 getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + i, column - i, false, true, false);
            //             }

            //             lastSlotChar = nextSlotChar;
            //         }
            //     }

            //     //--------------------------------------------------------

            //     if(pieceChar == 'a'){
            //         if(getSlotChar(board, row - 1, column - 1) == ' '){
            //             Vector<int[]> path = new Vector<int[]>();
            //             path = clonePath(comulPath);
            //             //add the carrent piece to the path (from)
            //             int [] rowAndColumn = new int[2];
            //             rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //             path.addElement(rowAndColumn);

            //             //add the next empty piece to the path (to)
            //             int [] rowAndColumn2 = new int[2];
            //             rowAndColumn2[0] = row - 1; rowAndColumn2[1] = column - 1; 
            //             path.addElement(rowAndColumn2);

            //             //add the path to the list of chooses
            //             listChooses.addElement(path);
            //         }else if(getSlotChar(board, row - 1, column - 1) == 'b' || getSlotChar(board, row - 1, column - 1) == 'B'){
            //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - 1, column - 1);

            //             Vector<int[]> newComulPath = new Vector<int[]>();
            //             //add the carrent piece to the path (from)
            //             int [] rowAndColumn = new int[2];
            //             rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //             newComulPath.addElement(rowAndColumn);
                        
            //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - 1, column - 1, false, true, false);
            //         }
            //     }else if(pieceChar == 'A'){
            //         char lastSlotChar = ' ';
            //         char nextSlotChar;
            //         for(int i = 1; i < 8; i++){
            //             nextSlotChar = getSlotChar(board, row - i, column - i);

            //             if((lastSlotChar == 'b' || lastSlotChar == 'B') && (nextSlotChar == 'b' || nextSlotChar == 'B')){
            //                 break;
            //             }

            //             if(nextSlotChar == 'a' || nextSlotChar == 'A' || nextSlotChar == '!'){
            //                 break;
            //             }else if(nextSlotChar == ' '){
            //                 Vector<int[]> path = new Vector<int[]>();
            //                 path = clonePath(comulPath);
            //                 //add the carrent piece to the path (from)
            //                 int [] rowAndColumn = new int[2];
            //                 rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //                 path.addElement(rowAndColumn);

            //                 //add the next empty piece to the path (to)
            //                 int [] rowAndColumn2 = new int[2];
            //                 rowAndColumn2[0] = row - i; rowAndColumn2[1] = column - i; 
            //                 path.addElement(rowAndColumn2);

            //                 //add the path to the list of chooses
            //                 listChooses.addElement(path);
            //             }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
            //                 char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - i, column - i);

            //                 Vector<int[]> newComulPath = new Vector<int[]>();
            //                 newComulPath = clonePath(comulPath);
            //                 //add the carrent piece to the path (from)
            //                 int [] rowAndColumn = new int[2];
            //                 rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //                 newComulPath.addElement(rowAndColumn);
                        
            //                 getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - i, column - i, false, true, false);
            //             }

            //             lastSlotChar = nextSlotChar;
            //         }
            //     }else if(pieceChar == 'B'){
            //         char lastSlotChar = ' ';
            //         char nextSlotChar;
            //         for(int i = 1; i < 8; i++){
            //             nextSlotChar = getSlotChar(board, row - i, column - i);

            //             if((lastSlotChar == 'a' || lastSlotChar == 'A') && (nextSlotChar == 'a' || nextSlotChar == 'A')){
            //                 break;
            //             }

            //             if(nextSlotChar == 'b' || nextSlotChar == 'B' || nextSlotChar == '!'){
            //                 break;
            //             }else if(nextSlotChar == ' '){
            //                 Vector<int[]> path = new Vector<int[]>();
            //                 path = clonePath(comulPath);
            //                 //add the carrent piece to the path (from)
            //                 int [] rowAndColumn = new int[2];
            //                 rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //                 path.addElement(rowAndColumn);

            //                 //add the next empty piece to the path (to)
            //                 int [] rowAndColumn2 = new int[2];
            //                 rowAndColumn2[0] = row - i; rowAndColumn2[1] = column - i; 
            //                 path.addElement(rowAndColumn2);

            //                 //add the path to the list of chooses
            //                 listChooses.addElement(path);
            //             }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
            //                 char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - i, column - i);

            //                 Vector<int[]> newComulPath = new Vector<int[]>();
            //                 newComulPath = clonePath(comulPath);
            //                 //add the carrent piece to the path (from)
            //                 int [] rowAndColumn = new int[2];
            //                 rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //                 newComulPath.addElement(rowAndColumn);
                        
            //                 getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - i, column - i, false, true, false);
            //             }

            //             lastSlotChar = nextSlotChar;
            //         }
            //     }
            // }

            // //the hart of the board
            // else {
            //     if(pieceChar == 'b'){
            //         if(getSlotChar(board, row + 1, column + 1) == ' '){
            //             Vector<int[]> path = new Vector<int[]>();
            //             path = clonePath(comulPath);
            //             //add the carrent piece to the path (from)
            //             int [] rowAndColumn = new int[2];
            //             rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //             path.addElement(rowAndColumn);

            //             //add the next empty piece to the path (to)
            //             int [] rowAndColumn2 = new int[2];
            //             rowAndColumn2[0] = row + 1; rowAndColumn2[1] = column + 1; 
            //             path.addElement(rowAndColumn2);

            //             //add the path to the list of chooses
            //             listChooses.addElement(path);
            //         }else if(getSlotChar(board, row + 1, column + 1) == 'a' || getSlotChar(board, row + 1, column + 1) == 'A'){
            //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + 1, column + 1);

            //             Vector<int[]> newComulPath = new Vector<int[]>();
            //             //add the carrent piece to the path (from)
            //             int [] rowAndColumn = new int[2];
            //             rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //             newComulPath.addElement(rowAndColumn);
                        
            //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + 1, column + 1, false, true, false);
            //         }
            //     }else if(pieceChar == 'B'){
            //         char lastSlotChar = ' ';
            //         char nextSlotChar;
            //         for(int i = 1; i < 8; i++){
            //             nextSlotChar = getSlotChar(board, row + i, column + i);

            //             if((lastSlotChar == 'a' || lastSlotChar == 'A') && (nextSlotChar == 'a' || nextSlotChar == 'A')){
            //                 break;
            //             }

            //             if(nextSlotChar == 'b' || nextSlotChar == 'B' || nextSlotChar == '!'){
            //                 break;
            //             }else if(nextSlotChar == ' '){
            //                 Vector<int[]> path = new Vector<int[]>();
            //                 path = clonePath(comulPath);
            //                 //add the carrent piece to the path (from)
            //                 int [] rowAndColumn = new int[2];
            //                 rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //                 path.addElement(rowAndColumn);

            //                 //add the next empty piece to the path (to)
            //                 int [] rowAndColumn2 = new int[2];
            //                 rowAndColumn2[0] = row + i; rowAndColumn2[1] = column + i; 
            //                 path.addElement(rowAndColumn2);

            //                 //add the path to the list of chooses
            //                 listChooses.addElement(path);
            //             }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
            //                 char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + i, column + i);

            //                 Vector<int[]> newComulPath = new Vector<int[]>();
            //                 newComulPath = clonePath(comulPath);
            //                 //add the carrent piece to the path (from)
            //                 int [] rowAndColumn = new int[2];
            //                 rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //                 newComulPath.addElement(rowAndColumn);
                        
            //                 getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + i, column + i, false, true, false);
            //             }

            //             lastSlotChar = nextSlotChar;
            //         }
            //     }else if(pieceChar == 'A'){
            //         char lastSlotChar = ' ';
            //         char nextSlotChar;
            //         for(int i = 1; i < 8; i++){
            //             nextSlotChar = getSlotChar(board, row + i, column + i);

            //             if((lastSlotChar == 'b' || lastSlotChar == 'B') && (nextSlotChar == 'b' || nextSlotChar == 'B')){
            //                 break;
            //             }

            //             if(nextSlotChar == 'a' || nextSlotChar == 'A' || nextSlotChar == '!'){
            //                 break;
            //             }else if(nextSlotChar == ' '){
            //                 Vector<int[]> path = new Vector<int[]>();
            //                 path = clonePath(comulPath);
            //                 //add the carrent piece to the path (from)
            //                 int [] rowAndColumn = new int[2];
            //                 rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //                 path.addElement(rowAndColumn);

            //                 //add the next empty piece to the path (to)
            //                 int [] rowAndColumn2 = new int[2];
            //                 rowAndColumn2[0] = row + i; rowAndColumn2[1] = column + i; 
            //                 path.addElement(rowAndColumn2);

            //                 //add the path to the list of chooses
            //                 listChooses.addElement(path);
            //             }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
            //                 char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + i, column + i);

            //                 Vector<int[]> newComulPath = new Vector<int[]>();
            //                 newComulPath = clonePath(comulPath);
            //                 //add the carrent piece to the path (from)
            //                 int [] rowAndColumn = new int[2];
            //                 rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //                 newComulPath.addElement(rowAndColumn);
                        
            //                 getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + i, column + i, false, true, false);
            //             }

            //             lastSlotChar = nextSlotChar;
            //         }
            //     }


            //     //-----------------------------------------------------

            //     if(pieceChar == 'b'){
            //         if(getSlotChar(board, row + 1, column - 1) == ' '){
            //             Vector<int[]> path = new Vector<int[]>();
            //             path = clonePath(comulPath);
            //             //add the carrent piece to the path (from)
            //             int [] rowAndColumn = new int[2];
            //             rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //             path.addElement(rowAndColumn);

            //             //add the next empty piece to the path (to)
            //             int [] rowAndColumn2 = new int[2];
            //             rowAndColumn2[0] = row + 1; rowAndColumn2[1] = column - 1; 
            //             path.addElement(rowAndColumn2);

            //             //add the path to the list of chooses
            //             listChooses.addElement(path);
            //         }else if(getSlotChar(board, row + 1, column - 1) == 'a' || getSlotChar(board, row + 1, column - 1) == 'A'){
            //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + 1, column - 1);

            //             Vector<int[]> newComulPath = new Vector<int[]>();
            //             //add the carrent piece to the path (from)
            //             int [] rowAndColumn = new int[2];
            //             rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //             newComulPath.addElement(rowAndColumn);
                        
            //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + 1, column - 1, false, true, false);
            //         }
            //     }else if(pieceChar == 'B'){
            //         char lastSlotChar = ' ';
            //         char nextSlotChar;
            //         for(int i = 1; i < 8; i++){
            //             nextSlotChar = getSlotChar(board, row + i, column - i);

            //             if((lastSlotChar == 'a' || lastSlotChar == 'A') && (nextSlotChar == 'a' || nextSlotChar == 'A')){
            //                 break;
            //             }

            //             if(nextSlotChar == 'b' || nextSlotChar == 'B' || nextSlotChar == '!'){
            //                 break;
            //             }else if(nextSlotChar == ' '){
            //                 Vector<int[]> path = new Vector<int[]>();
            //                 path = clonePath(comulPath);
            //                 //add the carrent piece to the path (from)
            //                 int [] rowAndColumn = new int[2];
            //                 rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //                 path.addElement(rowAndColumn);

            //                 //add the next empty piece to the path (to)
            //                 int [] rowAndColumn2 = new int[2];
            //                 rowAndColumn2[0] = row + i; rowAndColumn2[1] = column - i; 
            //                 path.addElement(rowAndColumn2);

            //                 //add the path to the list of chooses
            //                 listChooses.addElement(path);
            //             }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
            //                 char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + i, column - i);

            //                 Vector<int[]> newComulPath = new Vector<int[]>();
            //                 newComulPath = clonePath(comulPath);
            //                 //add the carrent piece to the path (from)
            //                 int [] rowAndColumn = new int[2];
            //                 rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //                 newComulPath.addElement(rowAndColumn);
                        
            //                 getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + i, column - i, false, true, false);
            //             }

            //             lastSlotChar = nextSlotChar;
            //         }
            //     }else if(pieceChar == 'A'){
            //         char lastSlotChar = ' ';
            //         char nextSlotChar;
            //         for(int i = 1; i < 8; i++){
            //             nextSlotChar = getSlotChar(board, row + i, column - i);

            //             if((lastSlotChar == 'b' || lastSlotChar == 'B') && (nextSlotChar == 'b' || nextSlotChar == 'B')){
            //                 break;
            //             }

            //             if(nextSlotChar == 'a' || nextSlotChar == 'A' || nextSlotChar == '!'){
            //                 break;
            //             }else if(nextSlotChar == ' '){
            //                 Vector<int[]> path = new Vector<int[]>();
            //                 path = clonePath(comulPath);
            //                 //add the carrent piece to the path (from)
            //                 int [] rowAndColumn = new int[2];
            //                 rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //                 path.addElement(rowAndColumn);

            //                 //add the next empty piece to the path (to)
            //                 int [] rowAndColumn2 = new int[2];
            //                 rowAndColumn2[0] = row + i; rowAndColumn2[1] = column - i; 
            //                 path.addElement(rowAndColumn2);

            //                 //add the path to the list of chooses
            //                 listChooses.addElement(path);
            //             }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
            //                 char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + i, column - i);

            //                 Vector<int[]> newComulPath = new Vector<int[]>();
            //                 newComulPath = clonePath(comulPath);
            //                 //add the carrent piece to the path (from)
            //                 int [] rowAndColumn = new int[2];
            //                 rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //                 newComulPath.addElement(rowAndColumn);
                        
            //                 getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + i, column - i, false, true, false);
            //             }

            //             lastSlotChar = nextSlotChar;
            //         }
            //     }


            //     //-----------------------------------------------------------------------

            //     if(pieceChar == 'a'){
            //         if(getSlotChar(board, row - 1, column + 1) == ' '){
            //             Vector<int[]> path = new Vector<int[]>();
            //             path = clonePath(comulPath);
            //             //add the carrent piece to the path (from)
            //             int [] rowAndColumn = new int[2];
            //             rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //             path.addElement(rowAndColumn);

            //             //add the next empty piece to the path (to)
            //             int [] rowAndColumn2 = new int[2];
            //             rowAndColumn2[0] = row - 1; rowAndColumn2[1] = column + 1; 
            //             path.addElement(rowAndColumn2);

            //             //add the path to the list of chooses
            //             listChooses.addElement(path);
            //         }else if(getSlotChar(board, row - 1, column + 1) == 'b' || getSlotChar(board, row - 1, column + 1) == 'B'){
            //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - 1, column + 1);

            //             Vector<int[]> newComulPath = new Vector<int[]>();
            //             //add the carrent piece to the path (from)
            //             int [] rowAndColumn = new int[2];
            //             rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //             newComulPath.addElement(rowAndColumn);
                        
            //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - 1, column + 1, false, true, false);
            //         }
            //     }else if(pieceChar == 'A'){
            //         char lastSlotChar = ' ';
            //         char nextSlotChar;
            //         for(int i = 1; i < 8; i++){
            //             nextSlotChar = getSlotChar(board, row - i, column + i);

            //             if((lastSlotChar == 'b' || lastSlotChar == 'B') && (nextSlotChar == 'b' || nextSlotChar == 'B')){
            //                 break;
            //             }

            //             if(nextSlotChar == 'a' || nextSlotChar == 'A' || nextSlotChar == '!'){
            //                 break;
            //             }else if(nextSlotChar == ' '){
            //                 Vector<int[]> path = new Vector<int[]>();
            //                 path = clonePath(comulPath);
            //                 //add the carrent piece to the path (from)
            //                 int [] rowAndColumn = new int[2];
            //                 rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //                 path.addElement(rowAndColumn);

            //                 //add the next empty piece to the path (to)
            //                 int [] rowAndColumn2 = new int[2];
            //                 rowAndColumn2[0] = row - i; rowAndColumn2[1] = column + i; 
            //                 path.addElement(rowAndColumn2);

            //                 //add the path to the list of chooses
            //                 listChooses.addElement(path);
            //             }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
            //                 char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - i, column + i);

            //                 Vector<int[]> newComulPath = new Vector<int[]>();
            //                 newComulPath = clonePath(comulPath);
            //                 //add the carrent piece to the path (from)
            //                 int [] rowAndColumn = new int[2];
            //                 rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //                 newComulPath.addElement(rowAndColumn);
                        
            //                 getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - i, column + i, false, true, false);
            //             }

            //             lastSlotChar = nextSlotChar;
            //         }
            //     }else if(pieceChar == 'B'){
            //         char lastSlotChar = ' ';
            //         char nextSlotChar;
            //         for(int i = 1; i < 8; i++){
            //             nextSlotChar = getSlotChar(board, row - i, column + i);

            //             if((lastSlotChar == 'a' || lastSlotChar == 'A') && (nextSlotChar == 'a' || nextSlotChar == 'A')){
            //                 break;
            //             }

            //             if(nextSlotChar == 'b' || nextSlotChar == 'B' || nextSlotChar == '!'){
            //                 break;
            //             }else if(nextSlotChar == ' '){
            //                 Vector<int[]> path = new Vector<int[]>();
            //                 path = clonePath(comulPath);
            //                 //add the carrent piece to the path (from)
            //                 int [] rowAndColumn = new int[2];
            //                 rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //                 path.addElement(rowAndColumn);

            //                 //add the next empty piece to the path (to)
            //                 int [] rowAndColumn2 = new int[2];
            //                 rowAndColumn2[0] = row - i; rowAndColumn2[1] = column + i; 
            //                 path.addElement(rowAndColumn2);

            //                 //add the path to the list of chooses
            //                 listChooses.addElement(path);
            //             }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
            //                 char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - i, column + i);

            //                 Vector<int[]> newComulPath = new Vector<int[]>();
            //                 newComulPath = clonePath(comulPath);
            //                 //add the carrent piece to the path (from)
            //                 int [] rowAndColumn = new int[2];
            //                 rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //                 newComulPath.addElement(rowAndColumn);
                        
            //                 getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - i, column + i, false, true, false);
            //             }

            //             lastSlotChar = nextSlotChar;
            //         }
            //     }

            //     //-----------------------------------------------------------


            //     if(pieceChar == 'a'){
            //         if(getSlotChar(board, row - 1, column - 1) == ' '){
            //             Vector<int[]> path = new Vector<int[]>();
            //             path = clonePath(comulPath);
            //             //add the carrent piece to the path (from)
            //             int [] rowAndColumn = new int[2];
            //             rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //             path.addElement(rowAndColumn);

            //             //add the next empty piece to the path (to)
            //             int [] rowAndColumn2 = new int[2];
            //             rowAndColumn2[0] = row - 1; rowAndColumn2[1] = column - 1; 
            //             path.addElement(rowAndColumn2);

            //             //add the path to the list of chooses
            //             listChooses.addElement(path);
            //         }else if(getSlotChar(board, row - 1, column - 1) == 'b' || getSlotChar(board, row - 1, column - 1) == 'B'){
            //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - 1, column - 1);

            //             Vector<int[]> newComulPath = new Vector<int[]>();
            //             //add the carrent piece to the path (from)
            //             int [] rowAndColumn = new int[2];
            //             rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //             newComulPath.addElement(rowAndColumn);
                        
            //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - 1, column - 1, false, true, false);
            //         }
            //     }else if(pieceChar == 'A'){
            //         char lastSlotChar = ' ';
            //         char nextSlotChar;
            //         for(int i = 1; i < 8; i++){
            //             nextSlotChar = getSlotChar(board, row - i, column - i);

            //             if((lastSlotChar == 'b' || lastSlotChar == 'B') && (nextSlotChar == 'b' || nextSlotChar == 'B')){
            //                 break;
            //             }

            //             if(nextSlotChar == 'a' || nextSlotChar == 'A' || nextSlotChar == '!'){
            //                 break;
            //             }else if(nextSlotChar == ' '){
            //                 Vector<int[]> path = new Vector<int[]>();
            //                 path = clonePath(comulPath);
            //                 //add the carrent piece to the path (from)
            //                 int [] rowAndColumn = new int[2];
            //                 rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //                 path.addElement(rowAndColumn);

            //                 //add the next empty piece to the path (to)
            //                 int [] rowAndColumn2 = new int[2];
            //                 rowAndColumn2[0] = row - i; rowAndColumn2[1] = column - i; 
            //                 path.addElement(rowAndColumn2);

            //                 //add the path to the list of chooses
            //                 listChooses.addElement(path);
            //             }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
            //                 char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - i, column - i);

            //                 Vector<int[]> newComulPath = new Vector<int[]>();
            //                 newComulPath = clonePath(comulPath);
            //                 //add the carrent piece to the path (from)
            //                 int [] rowAndColumn = new int[2];
            //                 rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //                 newComulPath.addElement(rowAndColumn);
                        
            //                 getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - i, column - i, false, true, false);
            //             }

            //             lastSlotChar = nextSlotChar;
            //         }
            //     }else if(pieceChar == 'B'){
            //         char lastSlotChar = ' ';
            //         char nextSlotChar;
            //         for(int i = 1; i < 8; i++){
            //             nextSlotChar = getSlotChar(board, row - i, column - i);

            //             if((lastSlotChar == 'a' || lastSlotChar == 'A') && (nextSlotChar == 'a' || nextSlotChar == 'A')){
            //                 break;
            //             }

            //             if(nextSlotChar == 'b' || nextSlotChar == 'B' || nextSlotChar == '!'){
            //                 break;
            //             }else if(nextSlotChar == ' '){
            //                 Vector<int[]> path = new Vector<int[]>();
            //                 path = clonePath(comulPath);
            //                 //add the carrent piece to the path (from)
            //                 int [] rowAndColumn = new int[2];
            //                 rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //                 path.addElement(rowAndColumn);

            //                 //add the next empty piece to the path (to)
            //                 int [] rowAndColumn2 = new int[2];
            //                 rowAndColumn2[0] = row - i; rowAndColumn2[1] = column - i; 
            //                 path.addElement(rowAndColumn2);

            //                 //add the path to the list of chooses
            //                 listChooses.addElement(path);
            //             }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
            //                 char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - i, column - i);

            //                 Vector<int[]> newComulPath = new Vector<int[]>();
            //                 newComulPath = clonePath(comulPath);
            //                 //add the carrent piece to the path (from)
            //                 int [] rowAndColumn = new int[2];
            //                 rowAndColumn[0] = row; rowAndColumn[1] = column; 
            //                 newComulPath.addElement(rowAndColumn);
                        
            //                 getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - i, column - i, false, true, false);
            //             }

            //             lastSlotChar = nextSlotChar;
            //         }
            //     }
            // }
        }else if(eatting){
            if(!(row == 0 || row == 7 || column == 0 || column == 7)){//the player can't eat piece in corner or touch line
                //processing the hart of the board
                if(pieceChar == 'b'){
                    int rowDifferent = row - comulPath.lastElement()[0];
                    int columnDifferent = column - comulPath.lastElement()[1];

                    if(rowDifferent >= 1 && columnDifferent >= 1){//the backward is top left
                        if(getSlotChar(board, row + 1, column + 1) == ' '){
                            char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + 1, column + 1);
    
                            Vector<int[]> newComulPath = new Vector<int[]>();
                            newComulPath = clonePath(comulPath);
                            //add the carrent piece to the path (from)
                            int [] rowAndColumn = new int[2];
                            rowAndColumn[0] = row; rowAndColumn[1] = column; 
                            newComulPath.addElement(rowAndColumn);
                            
                            getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + 1, column + 1, false, false, true);
                        }   
                    }else if(rowDifferent >= 1 && columnDifferent <= -1){//the backward is top right
                        if(getSlotChar(board, row + 1, column - 1) == ' '){
                            char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + 1, column - 1);
    
                            Vector<int[]> newComulPath = new Vector<int[]>();
                            newComulPath = clonePath(comulPath);
                            //add the carrent piece to the path (from)
                            int [] rowAndColumn = new int[2];
                            rowAndColumn[0] = row; rowAndColumn[1] = column; 
                            newComulPath.addElement(rowAndColumn);
                            
                            getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + 1, column - 1, false, false, true);
                        }
                    }
                }
                else if(pieceChar == 'a'){
                    int rowDifferent = row - comulPath.lastElement()[0];
                    int columnDifferent = column - comulPath.lastElement()[1];

                    if(rowDifferent <= -1 && columnDifferent <= -1){//the backward is buttom right
                        if(getSlotChar(board, row - 1, column - 1) == ' '){
                            char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - 1, column - 1);
    
                            Vector<int[]> newComulPath = new Vector<int[]>();
                            newComulPath = clonePath(comulPath);
                            //add the carrent piece to the path (from)
                            int [] rowAndColumn = new int[2];
                            rowAndColumn[0] = row; rowAndColumn[1] = column; 
                            newComulPath.addElement(rowAndColumn);
                            
                            getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - 1, column - 1, false, false, true);
                        }
                    }else if(rowDifferent <= -1 && columnDifferent >= 1){//the backward is buttom left
                        if(getSlotChar(board, row - 1, column + 1) == ' '){
                            char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - 1, column + 1);
    
                            Vector<int[]> newComulPath = new Vector<int[]>();
                            newComulPath = clonePath(comulPath);
                            //add the carrent piece to the path (from)
                            int [] rowAndColumn = new int[2];
                            rowAndColumn[0] = row; rowAndColumn[1] = column; 
                            newComulPath.addElement(rowAndColumn);
                            
                            getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - 1, column + 1, false, false, true);
                        }   
                    }
                }
                else if(pieceChar == 'B'){
                    int rowDifferent = row - comulPath.lastElement()[0];
                    int columnDifferent = column - comulPath.lastElement()[1];

                    //the piece can't go backward
                    if(rowDifferent >= 1 && columnDifferent >= 1){//the backward is top left
                        if(!(getSlotChar(board, row + 1, column + 1) != ' ')){
                            char lastSlotChar = 'a';
                            char nextSlotChar;
                            for(int i = 1; i < 8; i++){
                                nextSlotChar = getSlotChar(board, row + i, column + i);

                                if(lastSlotChar != ' ' && nextSlotChar != ' '){
                                    break;
                                }

                                if(nextSlotChar == 'b' || nextSlotChar == 'B' || nextSlotChar == '!'){
                                    break;
                                }else if(nextSlotChar == ' '){
                                    char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + i, column + i);

                                    Vector<int[]> newComulPath = new Vector<int[]>();
                                    newComulPath = clonePath(comulPath);
                                    //add the carrent piece to the path (from)
                                    int [] rowAndColumn = new int[2];
                                    rowAndColumn[0] = row; rowAndColumn[1] = column; 
                                    newComulPath.addElement(rowAndColumn);

                                    getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + i, column + i, false, false, true);
                                }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
                                    char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + i, column + i);

                                    Vector<int[]> newComulPath = new Vector<int[]>();
                                    newComulPath = clonePath(comulPath);
                                    //add the carrent piece to the path (from)
                                    int [] rowAndColumn = new int[2];
                                    rowAndColumn[0] = row; rowAndColumn[1] = column; 
                                    newComulPath.addElement(rowAndColumn);
                                
                                    getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + i, column + i, false, true, false);
                                }

                                lastSlotChar = nextSlotChar;
                            }
                        }
                    }
                    ////////////
                    else if(rowDifferent >= 1 && columnDifferent <= -1){//the backward is top right
                        if(!(getSlotChar(board, row + 1, column - 1) != ' ')){
                            char lastSlotChar = 'a';
                            char nextSlotChar;
                            for(int i = 1; i < 8; i++){
                                nextSlotChar = getSlotChar(board, row + i, column - i);

                                if(lastSlotChar != ' ' && nextSlotChar != ' '){
                                    break;
                                }

                                if(nextSlotChar == 'b' || nextSlotChar == 'B' || nextSlotChar == '!'){
                                    break;
                                }else if(nextSlotChar == ' '){
                                    char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + i, column - i);

                                    Vector<int[]> newComulPath = new Vector<int[]>();
                                    newComulPath = clonePath(comulPath);
                                    //add the carrent piece to the path (from)
                                    int [] rowAndColumn = new int[2];
                                    rowAndColumn[0] = row; rowAndColumn[1] = column; 
                                    newComulPath.addElement(rowAndColumn);

                                    getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + i, column - i, false, false, true);
                                }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
                                    char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + i, column - i);

                                    Vector<int[]> newComulPath = new Vector<int[]>();
                                    newComulPath = clonePath(comulPath);
                                    //add the carrent piece to the path (from)
                                    int [] rowAndColumn = new int[2];
                                    rowAndColumn[0] = row; rowAndColumn[1] = column; 
                                    newComulPath.addElement(rowAndColumn);
                                
                                    getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + i, column - i, false, true, false);
                                }

                                lastSlotChar = nextSlotChar;
                            }
                        }
                    }
                    ////////////
                    else if(rowDifferent <= -1 && columnDifferent >= 1){//the backward is buttom left
                        if(!(getSlotChar(board, row - 1, column + 1) != ' ')){
                            char lastSlotChar = 'a';
                            char nextSlotChar;
                            for(int i = 1; i < 8; i++){
                                nextSlotChar = getSlotChar(board, row - i, column + i);

                                if(lastSlotChar != ' ' && nextSlotChar != ' '){
                                    break;
                                }

                                if(nextSlotChar == 'b' || nextSlotChar == 'B' || nextSlotChar == '!'){
                                    break;
                                }else if(nextSlotChar == ' '){
                                    char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - i, column + i);

                                    Vector<int[]> newComulPath = new Vector<int[]>();
                                    newComulPath = clonePath(comulPath);
                                    //add the carrent piece to the path (from)
                                    int [] rowAndColumn = new int[2];
                                    rowAndColumn[0] = row; rowAndColumn[1] = column; 
                                    newComulPath.addElement(rowAndColumn);

                                    getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - i, column + i, false, false, true);
                                }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
                                    char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - i, column + i);

                                    Vector<int[]> newComulPath = new Vector<int[]>();
                                    newComulPath = clonePath(comulPath);
                                    //add the carrent piece to the path (from)
                                    int [] rowAndColumn = new int[2];
                                    rowAndColumn[0] = row; rowAndColumn[1] = column; 
                                    newComulPath.addElement(rowAndColumn);
                                
                                    getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - i, column + i, false, true, false);
                                }

                                lastSlotChar = nextSlotChar;
                            }
                        }
                    }
                    ////////////
                    else if(rowDifferent <= -1 && columnDifferent <= -1){//the backward is buttom right
                        if(!(getSlotChar(board, row - 1, column - 1) != ' ')){
                            char lastSlotChar = 'a';
                            char nextSlotChar;
                            for(int i = 1; i < 8; i++){
                                nextSlotChar = getSlotChar(board, row - i, column - i);

                                if(lastSlotChar != ' ' && nextSlotChar != ' '){
                                    break;
                                }

                                if(nextSlotChar == 'b' || nextSlotChar == 'B' || nextSlotChar == '!'){
                                    break;
                                }else if(nextSlotChar == ' '){
                                    char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - i, column - i);

                                    Vector<int[]> newComulPath = new Vector<int[]>();
                                    newComulPath = clonePath(comulPath);
                                    //add the carrent piece to the path (from)
                                    int [] rowAndColumn = new int[2];
                                    rowAndColumn[0] = row; rowAndColumn[1] = column; 
                                    newComulPath.addElement(rowAndColumn);

                                    getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - i, column - i, false, false, true);
                                }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
                                    char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - i, column - i);

                                    Vector<int[]> newComulPath = new Vector<int[]>();
                                    newComulPath = clonePath(comulPath);
                                    //add the carrent piece to the path (from)
                                    int [] rowAndColumn = new int[2];
                                    rowAndColumn[0] = row; rowAndColumn[1] = column; 
                                    newComulPath.addElement(rowAndColumn);
                                
                                    getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - i, column - i, false, true, false);
                                }

                                lastSlotChar = nextSlotChar;
                            }
                        }
                    }
                }
                else if(pieceChar == 'A'){
                    int rowDifferent = row - comulPath.lastElement()[0];
                    int columnDifferent = column - comulPath.lastElement()[1];

                    
                    //the piece can't go backward
                    if(rowDifferent >= 1 && columnDifferent >= 1){//the backward is top left
                        if(!(getSlotChar(board, row + 1, column + 1) != ' ')){
                            char lastSlotChar = 'a';
                            char nextSlotChar;
                            for(int i = 1; i < 8; i++){
                                nextSlotChar = getSlotChar(board, row + i, column + i);

                                if(lastSlotChar != ' ' && nextSlotChar != ' '){
                                    break;
                                }

                                if(nextSlotChar == 'a' || nextSlotChar == 'A' || nextSlotChar == '!'){
                                    break;
                                }else if(nextSlotChar == ' '){
                                    char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + i, column + i);

                                    Vector<int[]> newComulPath = new Vector<int[]>();
                                    newComulPath = clonePath(comulPath);
                                    //add the carrent piece to the path (from)
                                    int [] rowAndColumn = new int[2];
                                    rowAndColumn[0] = row; rowAndColumn[1] = column; 
                                    newComulPath.addElement(rowAndColumn);

                                    getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + i, column + i, false, false, true);
                                }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
                                    char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + i, column + i);

                                    Vector<int[]> newComulPath = new Vector<int[]>();
                                    newComulPath = clonePath(comulPath);
                                    //add the carrent piece to the path (from)
                                    int [] rowAndColumn = new int[2];
                                    rowAndColumn[0] = row; rowAndColumn[1] = column; 
                                    newComulPath.addElement(rowAndColumn);
                                
                                    getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + i, column + i, false, true, false);
                                }

                                lastSlotChar = nextSlotChar;
                            }
                        }
                    }
                    ////////////
                    else if(rowDifferent >= 1 && columnDifferent <= -1){//the backward is top right
                        if(!(getSlotChar(board, row + 1, column - 1) != ' ')){
                            char lastSlotChar = 'a';
                            char nextSlotChar;
                            for(int i = 1; i < 8; i++){
                                nextSlotChar = getSlotChar(board, row + i, column - i);

                                if(lastSlotChar != ' ' && nextSlotChar != ' '){
                                    break;
                                }

                                if(nextSlotChar == 'a' || nextSlotChar == 'A' || nextSlotChar == '!'){
                                    break;
                                }else if(nextSlotChar == ' '){
                                    char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + i, column - i);

                                    Vector<int[]> newComulPath = new Vector<int[]>();
                                    newComulPath = clonePath(comulPath);
                                    //add the carrent piece to the path (from)
                                    int [] rowAndColumn = new int[2];
                                    rowAndColumn[0] = row; rowAndColumn[1] = column; 
                                    newComulPath.addElement(rowAndColumn);

                                    getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + i, column - i, false, false, true);
                                }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
                                    char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + i, column - i);

                                    Vector<int[]> newComulPath = new Vector<int[]>();
                                    newComulPath = clonePath(comulPath);
                                    //add the carrent piece to the path (from)
                                    int [] rowAndColumn = new int[2];
                                    rowAndColumn[0] = row; rowAndColumn[1] = column; 
                                    newComulPath.addElement(rowAndColumn);
                                
                                    getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + i, column - i, false, true, false);
                                }

                                lastSlotChar = nextSlotChar;
                            }
                        }
                    }
                    ////////////
                    else if(rowDifferent <= -1 && columnDifferent >= 1){//the backward is buttom left
                        if(!(getSlotChar(board, row - 1, column + 1) != ' ')){
                            char lastSlotChar = 'a';
                            char nextSlotChar;
                            for(int i = 1; i < 8; i++){
                                nextSlotChar = getSlotChar(board, row - i, column + i);

                                if(lastSlotChar != ' ' && nextSlotChar != ' '){
                                    break;
                                }

                                if(nextSlotChar == 'a' || nextSlotChar == 'A' || nextSlotChar == '!'){
                                    break;
                                }else if(nextSlotChar == ' '){
                                    char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - i, column + i);

                                    Vector<int[]> newComulPath = new Vector<int[]>();
                                    newComulPath = clonePath(comulPath);
                                    //add the carrent piece to the path (from)
                                    int [] rowAndColumn = new int[2];
                                    rowAndColumn[0] = row; rowAndColumn[1] = column; 
                                    newComulPath.addElement(rowAndColumn);

                                    getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - i, column + i, false, false, true);
                                }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
                                    char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - i, column + i);

                                    Vector<int[]> newComulPath = new Vector<int[]>();
                                    newComulPath = clonePath(comulPath);
                                    //add the carrent piece to the path (from)
                                    int [] rowAndColumn = new int[2];
                                    rowAndColumn[0] = row; rowAndColumn[1] = column; 
                                    newComulPath.addElement(rowAndColumn);
                                
                                    getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - i, column + i, false, true, false);
                                }

                                lastSlotChar = nextSlotChar;
                            }
                        }
                    }
                    ////////////
                    else if(rowDifferent <= -1 && columnDifferent <= -1){//the backward is buttom right
                        if(!(getSlotChar(board, row - 1, column - 1) != ' ')){
                            char lastSlotChar = 'a';
                            char nextSlotChar;
                            for(int i = 1; i < 8; i++){
                                nextSlotChar = getSlotChar(board, row - i, column - i);

                                if(lastSlotChar != ' ' && nextSlotChar != ' '){
                                    break;
                                }

                                if(nextSlotChar == 'a' || nextSlotChar == 'A' || nextSlotChar == '!'){
                                    break;
                                }else if(nextSlotChar == ' '){
                                    char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - i, column - i);

                                    Vector<int[]> newComulPath = new Vector<int[]>();
                                    newComulPath = clonePath(comulPath);
                                    //add the carrent piece to the path (from)
                                    int [] rowAndColumn = new int[2];
                                    rowAndColumn[0] = row; rowAndColumn[1] = column; 
                                    newComulPath.addElement(rowAndColumn);

                                    getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - i, column - i, false, false, true);
                                }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
                                    char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - i, column - i);

                                    Vector<int[]> newComulPath = new Vector<int[]>();
                                    newComulPath = clonePath(comulPath);
                                    //add the carrent piece to the path (from)
                                    int [] rowAndColumn = new int[2];
                                    rowAndColumn[0] = row; rowAndColumn[1] = column; 
                                    newComulPath.addElement(rowAndColumn);
                                
                                    getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - i, column - i, false, true, false);
                                }

                                lastSlotChar = nextSlotChar;
                            }
                        }
                    }
                }
            }
        }else if(eated){
            Vector<int[]> path = new Vector<int[]>();
            path = clonePath(comulPath);
            //add the carrent piece to the path (from)
            int [] rowAndColumn = new int[2];
            rowAndColumn[0] = row; rowAndColumn[1] = column; 
            path.addElement(rowAndColumn);

            listChooses.addElement(path);


            if(!((row == 0 && column == 0) || (row == 0 && column == 7) || (row == 7 && column == 0) || (row == 7 && column == 7))){//no corners
                if(pieceChar == 'b'){
                    char nextSlotChar1 = getSlotChar(board, row + 1, column + 1);
                    if(nextSlotChar1 == 'a' || nextSlotChar1 == 'A'){
                        char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + 1, column + 1);

                        Vector<int[]> newComulPath = new Vector<int[]>();
                        newComulPath = clonePath(comulPath);
                        //add the carrent piece to the path (from)
                        int [] rowAndColumn1 = new int[2];
                        rowAndColumn1[0] = row; rowAndColumn1[1] = column; 
                        newComulPath.addElement(rowAndColumn1);
                    
                        getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + 1, column + 1, false, true, false);
                    }
                    //------------------
                    char nextSlotChar2 = getSlotChar(board, row + 1, column - 1);
                    if(nextSlotChar2 == 'a' || nextSlotChar2 == 'A'){
                        char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + 1, column - 1);

                        Vector<int[]> newComulPath = new Vector<int[]>();
                        newComulPath = clonePath(comulPath);
                        //add the carrent piece to the path (from)
                        int [] rowAndColumn1 = new int[2];
                        rowAndColumn1[0] = row; rowAndColumn1[1] = column; 
                        newComulPath.addElement(rowAndColumn1);
                    
                        getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + 1, column - 1, false, true, false);
                    }
                }else if(pieceChar == 'a'){
                    char nextSlotChar1 = getSlotChar(board, row - 1, column + 1);
                    if(nextSlotChar1 == 'b' || nextSlotChar1 == 'B'){
                        char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - 1, column + 1);

                        Vector<int[]> newComulPath = new Vector<int[]>();
                        newComulPath = clonePath(comulPath);
                        //add the carrent piece to the path (from)
                        int [] rowAndColumn1 = new int[2];
                        rowAndColumn1[0] = row; rowAndColumn1[1] = column; 
                        newComulPath.addElement(rowAndColumn1);
                    
                        getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - 1, column + 1, false, true, false);
                    }
                    //------------------
                    char nextSlotChar2 = getSlotChar(board, row - 1, column - 1);
                    if(nextSlotChar2 == 'b' || nextSlotChar2 == 'B'){
                        char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - 1, column - 1);

                        Vector<int[]> newComulPath = new Vector<int[]>();
                        newComulPath = clonePath(comulPath);
                        //add the carrent piece to the path (from)
                        int [] rowAndColumn1 = new int[2];
                        rowAndColumn1[0] = row; rowAndColumn1[1] = column; 
                        newComulPath.addElement(rowAndColumn1);
                    
                        getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - 1, column - 1, false, true, false);
                    }
                }else if(pieceChar == 'B'){
                    int rowDifferent = row - comulPath.lastElement()[0];
                    int columnDifferent = column - comulPath.lastElement()[1];

                    //the piece can't go backward
                    if(rowDifferent >= 1 && columnDifferent >= 1){//the backward is top right
                        pieceAfterEattingSpain(board, player, listChooses, comulPath, row, column, 1, 1, 'B');
                        pieceAfterEattingSpain(board, player, listChooses, comulPath, row, column, 1, -1, 'B');
                        pieceAfterEattingSpain(board, player, listChooses, comulPath, row, column, -1, 1, 'B');
                        


                        // //---------------------------------------------------------------------------------------
                        // {
                        //     char lastSlotChar = ' ';
                        //     char nextSlotChar;
                        //     for(int i = 1; i < 8; i++){
                        //         nextSlotChar = getSlotChar(board, row + i, column + i);

                        //         if((lastSlotChar == 'a' || lastSlotChar == 'A') && (nextSlotChar == 'a' || nextSlotChar == 'A')){
                        //             break;
                        //         }

                        //         if(nextSlotChar == 'b' || nextSlotChar == 'B' || nextSlotChar == '!'){
                        //             break;
                        //         }else if(nextSlotChar == ' '){
                        //             lastSlotChar = nextSlotChar; continue;
                        //         }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
                        //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + i, column + i);

                        //             Vector<int[]> newComulPath = new Vector<int[]>();
                        //             newComulPath = clonePath(comulPath);
                        //             //add the carrent piece to the path (from)
                        //             int [] newRowAndColumn = new int[2];
                        //             newRowAndColumn[0] = row; newRowAndColumn[1] = column; 
                        //             newComulPath.addElement(newRowAndColumn);
                                
                        //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + i, column + i, false, true, false);
                        //         }

                        //         lastSlotChar = nextSlotChar;
                        //     }
                        // }
                        // {
                        //     char lastSlotChar = ' ';
                        //     char nextSlotChar;
                        //     for(int i = 1; i < 8; i++){
                        //         nextSlotChar = getSlotChar(board, row + i, column - i);

                        //         if((lastSlotChar == 'a' || lastSlotChar == 'A') && (nextSlotChar == 'a' || nextSlotChar == 'A')){
                        //             break;
                        //         }

                        //         if(nextSlotChar == 'b' || nextSlotChar == 'B' || nextSlotChar == '!'){
                        //             break;
                        //         }else if(nextSlotChar == ' '){
                        //             lastSlotChar = nextSlotChar; continue;
                        //         }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
                        //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + i, column - i);

                        //             Vector<int[]> newComulPath = new Vector<int[]>();
                        //             newComulPath = clonePath(comulPath);
                        //             //add the carrent piece to the path (from)
                        //             int [] newRowAndColumn = new int[2];
                        //             newRowAndColumn[0] = row; newRowAndColumn[1] = column; 
                        //             newComulPath.addElement(newRowAndColumn);
                                
                        //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + i, column - i, false, true, false);
                        //         }

                        //         lastSlotChar = nextSlotChar;
                        //     }
                        // }
                        // {
                        //     char lastSlotChar = ' ';
                        //     char nextSlotChar;
                        //     for(int i = 1; i < 8; i++){
                        //         nextSlotChar = getSlotChar(board, row - i, column + i);

                        //         if((lastSlotChar == 'a' || lastSlotChar == 'A') && (nextSlotChar == 'a' || nextSlotChar == 'A')){
                        //             break;
                        //         }

                        //         if(nextSlotChar == 'b' || nextSlotChar == 'B' || nextSlotChar == '!'){
                        //             break;
                        //         }else if(nextSlotChar == ' '){
                        //             lastSlotChar = nextSlotChar; continue;
                        //         }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
                        //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - i, column + i);

                        //             Vector<int[]> newComulPath = new Vector<int[]>();
                        //             newComulPath = clonePath(comulPath);
                        //             //add the carrent piece to the path (from)
                        //             int [] newRowAndColumn = new int[2];
                        //             newRowAndColumn[0] = row; newRowAndColumn[1] = column; 
                        //             newComulPath.addElement(newRowAndColumn);
                                
                        //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - i, column + i, false, true, false);
                        //         }

                        //         lastSlotChar = nextSlotChar;
                        //     }
                        // }
                    } 
                    else if(rowDifferent >= 1 && columnDifferent <= -1){//the backward is top left
                        pieceAfterEattingSpain(board, player, listChooses, comulPath, row, column, 1, 1, 'B');
                        pieceAfterEattingSpain(board, player, listChooses, comulPath, row, column, 1, -1, 'B');
                        pieceAfterEattingSpain(board, player, listChooses, comulPath, row, column, -1, -1, 'B');



                        // //-----------------------------------------------------------------------------------
                        // {
                        //     char lastSlotChar = ' ';
                        //     char nextSlotChar;
                        //     for(int i = 1; i < 8; i++){
                        //         nextSlotChar = getSlotChar(board, row + i, column + i);

                        //         if((lastSlotChar == 'a' || lastSlotChar == 'A') && (nextSlotChar == 'a' || nextSlotChar == 'A')){
                        //             break;
                        //         }

                        //         if(nextSlotChar == 'b' || nextSlotChar == 'B' || nextSlotChar == '!'){
                        //             break;
                        //         }else if(nextSlotChar == ' '){
                        //             lastSlotChar = nextSlotChar; continue;
                        //         }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
                        //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + i, column + i);

                        //             Vector<int[]> newComulPath = new Vector<int[]>();
                        //             newComulPath = clonePath(comulPath);
                        //             //add the carrent piece to the path (from)
                        //             int [] newRowAndColumn = new int[2];
                        //             newRowAndColumn[0] = row; newRowAndColumn[1] = column; 
                        //             newComulPath.addElement(newRowAndColumn);
                                
                        //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + i, column + i, false, true, false);
                        //         }

                        //         lastSlotChar = nextSlotChar;
                        //     }
                        // }
                        // {
                        //     char lastSlotChar = ' ';
                        //     char nextSlotChar;
                        //     for(int i = 1; i < 8; i++){
                        //         nextSlotChar = getSlotChar(board, row + i, column - i);

                        //         if((lastSlotChar == 'a' || lastSlotChar == 'A') && (nextSlotChar == 'a' || nextSlotChar == 'A')){
                        //             break;
                        //         }

                        //         if(nextSlotChar == 'b' || nextSlotChar == 'B' || nextSlotChar == '!'){
                        //             break;
                        //         }else if(nextSlotChar == ' '){
                        //             lastSlotChar = nextSlotChar; continue;
                        //         }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
                        //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + i, column - i);

                        //             Vector<int[]> newComulPath = new Vector<int[]>();
                        //             newComulPath = clonePath(comulPath);
                        //             //add the carrent piece to the path (from)
                        //             int [] newRowAndColumn = new int[2];
                        //             newRowAndColumn[0] = row; newRowAndColumn[1] = column; 
                        //             newComulPath.addElement(newRowAndColumn);
                                
                        //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + i, column - i, false, true, false);
                        //         }

                        //         lastSlotChar = nextSlotChar;
                        //     }
                        // }
                        // {
                        //     char lastSlotChar = ' ';
                        //     char nextSlotChar;
                        //     for(int i = 1; i < 8; i++){
                        //         nextSlotChar = getSlotChar(board, row - i, column - i);

                        //         if((lastSlotChar == 'a' || lastSlotChar == 'A') && (nextSlotChar == 'a' || nextSlotChar == 'A')){
                        //             break;
                        //         }

                        //         if(nextSlotChar == 'b' || nextSlotChar == 'B' || nextSlotChar == '!'){
                        //             break;
                        //         }else if(nextSlotChar == ' '){
                        //             lastSlotChar = nextSlotChar; continue;
                        //         }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
                        //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - i, column - i);

                        //             Vector<int[]> newComulPath = new Vector<int[]>();
                        //             newComulPath = clonePath(comulPath);
                        //             //add the carrent piece to the path (from)
                        //             int [] newRowAndColumn = new int[2];
                        //             newRowAndColumn[0] = row; newRowAndColumn[1] = column; 
                        //             newComulPath.addElement(newRowAndColumn);
                                
                        //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - i, column - i, false, true, false);
                        //         }

                        //         lastSlotChar = nextSlotChar;
                        //     }
                        // }
                    } 
                    else if(rowDifferent <= -1 && columnDifferent >= 1){//the backward is buttom right
                        pieceAfterEattingSpain(board, player, listChooses, comulPath, row, column, 1, 1, 'B');
                        pieceAfterEattingSpain(board, player, listChooses, comulPath, row, column, -1, 1, 'B');
                        pieceAfterEattingSpain(board, player, listChooses, comulPath, row, column, -1, -1, 'B');


                        // //-------------------------------------------------------------------------
                        // {
                        //     char lastSlotChar = ' ';
                        //     char nextSlotChar;
                        //     for(int i = 1; i < 8; i++){
                        //         nextSlotChar = getSlotChar(board, row + i, column + i);

                        //         if((lastSlotChar == 'a' || lastSlotChar == 'A') && (nextSlotChar == 'a' || nextSlotChar == 'A')){
                        //             break;
                        //         }

                        //         if(nextSlotChar == 'b' || nextSlotChar == 'B' || nextSlotChar == '!'){
                        //             break;
                        //         }else if(nextSlotChar == ' '){
                        //             lastSlotChar = nextSlotChar; continue;
                        //         }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
                        //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + i, column + i);

                        //             Vector<int[]> newComulPath = new Vector<int[]>();
                        //             newComulPath = clonePath(comulPath);
                        //             //add the carrent piece to the path (from)
                        //             int [] newRowAndColumn = new int[2];
                        //             newRowAndColumn[0] = row; newRowAndColumn[1] = column; 
                        //             newComulPath.addElement(newRowAndColumn);
                                
                        //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + i, column + i, false, true, false);
                        //         }

                        //         lastSlotChar = nextSlotChar;
                        //     }
                        // }
                        // {
                        //     char lastSlotChar = ' ';
                        //     char nextSlotChar;
                        //     for(int i = 1; i < 8; i++){
                        //         nextSlotChar = getSlotChar(board, row - i, column + i);

                        //         if((lastSlotChar == 'a' || lastSlotChar == 'A') && (nextSlotChar == 'a' || nextSlotChar == 'A')){
                        //             break;
                        //         }

                        //         if(nextSlotChar == 'b' || nextSlotChar == 'B' || nextSlotChar == '!'){
                        //             break;
                        //         }else if(nextSlotChar == ' '){
                        //             lastSlotChar = nextSlotChar; continue;
                        //         }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
                        //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - i, column + i);

                        //             Vector<int[]> newComulPath = new Vector<int[]>();
                        //             newComulPath = clonePath(comulPath);
                        //             //add the carrent piece to the path (from)
                        //             int [] newRowAndColumn = new int[2];
                        //             newRowAndColumn[0] = row; newRowAndColumn[1] = column; 
                        //             newComulPath.addElement(newRowAndColumn);
                                
                        //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - i, column + i, false, true, false);
                        //         }

                        //         lastSlotChar = nextSlotChar;
                        //     }
                        // }
                        // {
                        //     char lastSlotChar = ' ';
                        //     char nextSlotChar;
                        //     for(int i = 1; i < 8; i++){
                        //         nextSlotChar = getSlotChar(board, row - i, column - i);

                        //         if((lastSlotChar == 'a' || lastSlotChar == 'A') && (nextSlotChar == 'a' || nextSlotChar == 'A')){
                        //             break;
                        //         }

                        //         if(nextSlotChar == 'b' || nextSlotChar == 'B' || nextSlotChar == '!'){
                        //             break;
                        //         }else if(nextSlotChar == ' '){
                        //             lastSlotChar = nextSlotChar; continue;
                        //         }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
                        //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - i, column - i);

                        //             Vector<int[]> newComulPath = new Vector<int[]>();
                        //             newComulPath = clonePath(comulPath);
                        //             //add the carrent piece to the path (from)
                        //             int [] newRowAndColumn = new int[2];
                        //             newRowAndColumn[0] = row; newRowAndColumn[1] = column; 
                        //             newComulPath.addElement(newRowAndColumn);
                                
                        //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - i, column - i, false, true, false);
                        //         }

                        //         lastSlotChar = nextSlotChar;
                        //     }
                        // }
                    } 
                    else if(rowDifferent <= -1 && columnDifferent <= -1){//the backward is buttom left
                        pieceAfterEattingSpain(board, player, listChooses, comulPath, row, column, 1, -1, 'B');
                        pieceAfterEattingSpain(board, player, listChooses, comulPath, row, column, -1, 1, 'B');
                        pieceAfterEattingSpain(board, player, listChooses, comulPath, row, column, -1, -1, 'B');



                        // //-------------------------------------------------------------------------------
                        // {
                        //     char lastSlotChar = ' ';
                        //     char nextSlotChar;
                        //     for(int i = 1; i < 8; i++){
                        //         nextSlotChar = getSlotChar(board, row + i, column - i);

                        //         if((lastSlotChar == 'a' || lastSlotChar == 'A') && (nextSlotChar == 'a' || nextSlotChar == 'A')){
                        //             break;
                        //         }

                        //         if(nextSlotChar == 'b' || nextSlotChar == 'B' || nextSlotChar == '!'){
                        //             break;
                        //         }else if(nextSlotChar == ' '){
                        //             lastSlotChar = nextSlotChar; continue;
                        //         }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
                        //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + i, column - i);

                        //             Vector<int[]> newComulPath = new Vector<int[]>();
                        //             newComulPath = clonePath(comulPath);
                        //             //add the carrent piece to the path (from)
                        //             int [] newRowAndColumn = new int[2];
                        //             newRowAndColumn[0] = row; newRowAndColumn[1] = column; 
                        //             newComulPath.addElement(newRowAndColumn);
                                
                        //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + i, column - i, false, true, false);
                        //         }

                        //         lastSlotChar = nextSlotChar;
                        //     }
                        // }
                        // {
                        //     char lastSlotChar = ' ';
                        //     char nextSlotChar;
                        //     for(int i = 1; i < 8; i++){
                        //         nextSlotChar = getSlotChar(board, row - i, column + i);

                        //         if((lastSlotChar == 'a' || lastSlotChar == 'A') && (nextSlotChar == 'a' || nextSlotChar == 'A')){
                        //             break;
                        //         }

                        //         if(nextSlotChar == 'b' || nextSlotChar == 'B' || nextSlotChar == '!'){
                        //             break;
                        //         }else if(nextSlotChar == ' '){
                        //             lastSlotChar = nextSlotChar; continue;
                        //         }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
                        //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - i, column + i);

                        //             Vector<int[]> newComulPath = new Vector<int[]>();
                        //             newComulPath = clonePath(comulPath);
                        //             //add the carrent piece to the path (from)
                        //             int [] newRowAndColumn = new int[2];
                        //             newRowAndColumn[0] = row; newRowAndColumn[1] = column; 
                        //             newComulPath.addElement(newRowAndColumn);
                                
                        //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - i, column + i, false, true, false);
                        //         }

                        //         lastSlotChar = nextSlotChar;
                        //     }
                        // }
                        // {
                        //     char lastSlotChar = ' ';
                        //     char nextSlotChar;
                        //     for(int i = 1; i < 8; i++){
                        //         nextSlotChar = getSlotChar(board, row - i, column - i);

                        //         if((lastSlotChar == 'a' || lastSlotChar == 'A') && (nextSlotChar == 'a' || nextSlotChar == 'A')){
                        //             break;
                        //         }

                        //         if(nextSlotChar == 'b' || nextSlotChar == 'B' || nextSlotChar == '!'){
                        //             break;
                        //         }else if(nextSlotChar == ' '){
                        //             lastSlotChar = nextSlotChar; continue;
                        //         }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
                        //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - i, column - i);

                        //             Vector<int[]> newComulPath = new Vector<int[]>();
                        //             newComulPath = clonePath(comulPath);
                        //             //add the carrent piece to the path (from)
                        //             int [] newRowAndColumn = new int[2];
                        //             newRowAndColumn[0] = row; newRowAndColumn[1] = column; 
                        //             newComulPath.addElement(newRowAndColumn);
                                
                        //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - i, column - i, false, true, false);
                        //         }

                        //         lastSlotChar = nextSlotChar;
                        //     }
                        // }
                    }
                }else if(pieceChar == 'A'){
                    int rowDifferent = row - comulPath.lastElement()[0];
                    int columnDifferent = column - comulPath.lastElement()[1];

                    //the piece can't go backward
                    if(rowDifferent >= 1 && columnDifferent >= 1){//the backward is top right
                        pieceAfterEattingSpain(board, player, listChooses, comulPath, row, column, 1, 1, 'A');
                        pieceAfterEattingSpain(board, player, listChooses, comulPath, row, column, 1, -1, 'A');
                        pieceAfterEattingSpain(board, player, listChooses, comulPath, row, column, -1, 1, 'A');



                        // //---------------------------------------------------------------------------
                        // {
                        //     char lastSlotChar = ' ';
                        //     char nextSlotChar;
                        //     for(int i = 1; i < 8; i++){
                        //         nextSlotChar = getSlotChar(board, row + i, column + i);

                        //         if((lastSlotChar == 'b' || lastSlotChar == 'B') && (nextSlotChar == 'b' || nextSlotChar == 'B')){
                        //             break;
                        //         }

                        //         if(nextSlotChar == 'a' || nextSlotChar == 'A' || nextSlotChar == '!'){
                        //             break;
                        //         }else if(nextSlotChar == ' '){
                        //             lastSlotChar = nextSlotChar; continue;
                        //         }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
                        //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + i, column + i);

                        //             Vector<int[]> newComulPath = new Vector<int[]>();
                        //             newComulPath = clonePath(comulPath);
                        //             //add the carrent piece to the path (from)
                        //             int [] newRowAndColumn = new int[2];
                        //             newRowAndColumn[0] = row; newRowAndColumn[1] = column; 
                        //             newComulPath.addElement(newRowAndColumn);
                                
                        //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + i, column + i, false, true, false);
                        //         }

                        //         lastSlotChar = nextSlotChar;
                        //     }
                        // }
                        // {
                        //     char lastSlotChar = ' ';
                        //     char nextSlotChar;
                        //     for(int i = 1; i < 8; i++){
                        //         nextSlotChar = getSlotChar(board, row + i, column - i);

                        //         if((lastSlotChar == 'b' || lastSlotChar == 'B') && (nextSlotChar == 'b' || nextSlotChar == 'B')){
                        //             break;
                        //         }

                        //         if(nextSlotChar == 'a' || nextSlotChar == 'A' || nextSlotChar == '!'){
                        //             break;
                        //         }else if(nextSlotChar == ' '){
                        //             lastSlotChar = nextSlotChar; continue;
                        //         }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
                        //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + i, column - i);

                        //             Vector<int[]> newComulPath = new Vector<int[]>();
                        //             newComulPath = clonePath(comulPath);
                        //             //add the carrent piece to the path (from)
                        //             int [] newRowAndColumn = new int[2];
                        //             newRowAndColumn[0] = row; newRowAndColumn[1] = column; 
                        //             newComulPath.addElement(newRowAndColumn);
                                
                        //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + i, column - i, false, true, false);
                        //         }

                        //         lastSlotChar = nextSlotChar;
                        //     }
                        // }
                        // {
                        //     char lastSlotChar = ' ';
                        //     char nextSlotChar;
                        //     for(int i = 1; i < 8; i++){
                        //         nextSlotChar = getSlotChar(board, row - i, column + i);

                        //         if((lastSlotChar == 'b' || lastSlotChar == 'B') && (nextSlotChar == 'b' || nextSlotChar == 'B')){
                        //             break;
                        //         }

                        //         if(nextSlotChar == 'a' || nextSlotChar == 'A' || nextSlotChar == '!'){
                        //             break;
                        //         }else if(nextSlotChar == ' '){
                        //             lastSlotChar = nextSlotChar; continue;
                        //         }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
                        //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - i, column + i);

                        //             Vector<int[]> newComulPath = new Vector<int[]>();
                        //             newComulPath = clonePath(comulPath);
                        //             //add the carrent piece to the path (from)
                        //             int [] newRowAndColumn = new int[2];
                        //             newRowAndColumn[0] = row; newRowAndColumn[1] = column; 
                        //             newComulPath.addElement(newRowAndColumn);
                                
                        //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - i, column + i, false, true, false);
                        //         }

                        //         lastSlotChar = nextSlotChar;
                        //     }
                        // }
                    } 
                    else if(rowDifferent >= 1 && columnDifferent <= -1){//the backward is top left
                        pieceAfterEattingSpain(board, player, listChooses, comulPath, row, column, 1, 1, 'A');
                        pieceAfterEattingSpain(board, player, listChooses, comulPath, row, column, 1, -1, 'A');
                        pieceAfterEattingSpain(board, player, listChooses, comulPath, row, column, -1, -1, 'A');


                        // //--------------------------------------------------------------------------------
                        // {
                        //     char lastSlotChar = ' ';
                        //     char nextSlotChar;
                        //     for(int i = 1; i < 8; i++){
                        //         nextSlotChar = getSlotChar(board, row + i, column + i);

                        //         if((lastSlotChar == 'b' || lastSlotChar == 'B') && (nextSlotChar == 'b' || nextSlotChar == 'B')){
                        //             break;
                        //         }

                        //         if(nextSlotChar == 'a' || nextSlotChar == 'A' || nextSlotChar == '!'){
                        //             break;
                        //         }else if(nextSlotChar == ' '){
                        //             lastSlotChar = nextSlotChar; continue;
                        //         }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
                        //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + i, column + i);

                        //             Vector<int[]> newComulPath = new Vector<int[]>();
                        //             newComulPath = clonePath(comulPath);
                        //             //add the carrent piece to the path (from)
                        //             int [] newRowAndColumn = new int[2];
                        //             newRowAndColumn[0] = row; newRowAndColumn[1] = column; 
                        //             newComulPath.addElement(newRowAndColumn);
                                
                        //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + i, column + i, false, true, false);
                        //         }

                        //         lastSlotChar = nextSlotChar;
                        //     }
                        // }
                        // {
                        //     char lastSlotChar = ' ';
                        //     char nextSlotChar;
                        //     for(int i = 1; i < 8; i++){
                        //         nextSlotChar = getSlotChar(board, row + i, column - i);

                        //         if((lastSlotChar == 'b' || lastSlotChar == 'B') && (nextSlotChar == 'b' || nextSlotChar == 'B')){
                        //             break;
                        //         }

                        //         if(nextSlotChar == 'a' || nextSlotChar == 'A' || nextSlotChar == '!'){
                        //             break;
                        //         }else if(nextSlotChar == ' '){
                        //             lastSlotChar = nextSlotChar; continue;
                        //         }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
                        //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + i, column - i);

                        //             Vector<int[]> newComulPath = new Vector<int[]>();
                        //             newComulPath = clonePath(comulPath);
                        //             //add the carrent piece to the path (from)
                        //             int [] newRowAndColumn = new int[2];
                        //             newRowAndColumn[0] = row; newRowAndColumn[1] = column; 
                        //             newComulPath.addElement(newRowAndColumn);
                                
                        //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + i, column - i, false, true, false);
                        //         }

                        //         lastSlotChar = nextSlotChar;
                        //     }
                        // }
                        // {
                        //     char lastSlotChar = ' ';
                        //     char nextSlotChar;
                        //     for(int i = 1; i < 8; i++){
                        //         nextSlotChar = getSlotChar(board, row - i, column - i);

                        //         if((lastSlotChar == 'b' || lastSlotChar == 'B') && (nextSlotChar == 'b' || nextSlotChar == 'B')){
                        //             break;
                        //         }

                        //         if(nextSlotChar == 'a' || nextSlotChar == 'A' || nextSlotChar == '!'){
                        //             break;
                        //         }else if(nextSlotChar == ' '){
                        //             lastSlotChar = nextSlotChar; continue;
                        //         }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
                        //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - i, column - i);

                        //             Vector<int[]> newComulPath = new Vector<int[]>();
                        //             newComulPath = clonePath(comulPath);
                        //             //add the carrent piece to the path (from)
                        //             int [] newRowAndColumn = new int[2];
                        //             newRowAndColumn[0] = row; newRowAndColumn[1] = column; 
                        //             newComulPath.addElement(newRowAndColumn);
                                
                        //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - i, column - i, false, true, false);
                        //         }

                        //         lastSlotChar = nextSlotChar;
                        //     }
                        // }
                    } 
                    else if(rowDifferent <= -1 && columnDifferent >= 1){//the backward is buttom left
                        pieceAfterEattingSpain(board, player, listChooses, comulPath, row, column, 1, 1, 'A');
                        pieceAfterEattingSpain(board, player, listChooses, comulPath, row, column, -1, 1, 'A');
                        pieceAfterEattingSpain(board, player, listChooses, comulPath, row, column, -1, -1, 'A');



                        // //-------------------------------------------------------------------------
                        // {
                        //     char lastSlotChar = ' ';
                        //     char nextSlotChar;
                        //     for(int i = 1; i < 8; i++){
                        //         nextSlotChar = getSlotChar(board, row + i, column + i);

                        //         if((lastSlotChar == 'b' || lastSlotChar == 'B') && (nextSlotChar == 'b' || nextSlotChar == 'B')){
                        //             break;
                        //         }

                        //         if(nextSlotChar == 'a' || nextSlotChar == 'A' || nextSlotChar == '!'){
                        //             break;
                        //         }else if(nextSlotChar == ' '){
                        //             lastSlotChar = nextSlotChar; continue;
                        //         }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
                        //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + i, column + i);

                        //             Vector<int[]> newComulPath = new Vector<int[]>();
                        //             newComulPath = clonePath(comulPath);
                        //             //add the carrent piece to the path (from)
                        //             int [] newRowAndColumn = new int[2];
                        //             newRowAndColumn[0] = row; newRowAndColumn[1] = column; 
                        //             newComulPath.addElement(newRowAndColumn);
                                
                        //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + i, column + i, false, true, false);
                        //         }

                        //         lastSlotChar = nextSlotChar;
                        //     }
                        // }
                        // {
                        //     char lastSlotChar = ' ';
                        //     char nextSlotChar;
                        //     for(int i = 1; i < 8; i++){
                        //         nextSlotChar = getSlotChar(board, row - i, column + i);

                        //         if((lastSlotChar == 'b' || lastSlotChar == 'B') && (nextSlotChar == 'b' || nextSlotChar == 'B')){
                        //             break;
                        //         }

                        //         if(nextSlotChar == 'a' || nextSlotChar == 'A' || nextSlotChar == '!'){
                        //             break;
                        //         }else if(nextSlotChar == ' '){
                        //             lastSlotChar = nextSlotChar; continue;
                        //         }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
                        //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - i, column + i);

                        //             Vector<int[]> newComulPath = new Vector<int[]>();
                        //             newComulPath = clonePath(comulPath);
                        //             //add the carrent piece to the path (from)
                        //             int [] newRowAndColumn = new int[2];
                        //             newRowAndColumn[0] = row; newRowAndColumn[1] = column; 
                        //             newComulPath.addElement(newRowAndColumn);
                                
                        //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - i, column + i, false, true, false);
                        //         }

                        //         lastSlotChar = nextSlotChar;
                        //     }
                        // }
                        // {
                        //     char lastSlotChar = ' ';
                        //     char nextSlotChar;
                        //     for(int i = 1; i < 8; i++){
                        //         nextSlotChar = getSlotChar(board, row - i, column - i);

                        //         if((lastSlotChar == 'b' || lastSlotChar == 'B') && (nextSlotChar == 'b' || nextSlotChar == 'B')){
                        //             break;
                        //         }

                        //         if(nextSlotChar == 'a' || nextSlotChar == 'A' || nextSlotChar == '!'){
                        //             break;
                        //         }else if(nextSlotChar == ' '){
                        //             lastSlotChar = nextSlotChar; continue;
                        //         }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
                        //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - i, column - i);

                        //             Vector<int[]> newComulPath = new Vector<int[]>();
                        //             newComulPath = clonePath(comulPath);
                        //             //add the carrent piece to the path (from)
                        //             int [] newRowAndColumn = new int[2];
                        //             newRowAndColumn[0] = row; newRowAndColumn[1] = column; 
                        //             newComulPath.addElement(newRowAndColumn);
                                
                        //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - i, column - i, false, true, false);
                        //         }

                        //         lastSlotChar = nextSlotChar;
                        //     }
                        // }
                    } 
                    else if(rowDifferent <= -1 && columnDifferent <= -1){//the backward is buttom right
                        pieceAfterEattingSpain(board, player, listChooses, comulPath, row, column, 1, -1, 'A');
                        pieceAfterEattingSpain(board, player, listChooses, comulPath, row, column, -1, 1, 'A');
                        pieceAfterEattingSpain(board, player, listChooses, comulPath, row, column, -1, -1, 'A');



                        // //-------------------------------------------------------------------------------
                        // {
                        //     char lastSlotChar = ' ';
                        //     char nextSlotChar;
                        //     for(int i = 1; i < 8; i++){
                        //         nextSlotChar = getSlotChar(board, row + i, column - i);

                        //         if((lastSlotChar == 'b' || lastSlotChar == 'B') && (nextSlotChar == 'b' || nextSlotChar == 'B')){
                        //             break;
                        //         }

                        //         if(nextSlotChar == 'a' || nextSlotChar == 'A' || nextSlotChar == '!'){
                        //             break;
                        //         }else if(nextSlotChar == ' '){
                        //             lastSlotChar = nextSlotChar; continue;
                        //         }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
                        //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + i, column - i);

                        //             Vector<int[]> newComulPath = new Vector<int[]>();
                        //             newComulPath = clonePath(comulPath);
                        //             //add the carrent piece to the path (from)
                        //             int [] newRowAndColumn = new int[2];
                        //             newRowAndColumn[0] = row; newRowAndColumn[1] = column; 
                        //             newComulPath.addElement(newRowAndColumn);
                                
                        //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + i, column - i, false, true, false);
                        //         }

                        //         lastSlotChar = nextSlotChar;
                        //     }
                        // }
                        // {
                        //     char lastSlotChar = ' ';
                        //     char nextSlotChar;
                        //     for(int i = 1; i < 8; i++){
                        //         nextSlotChar = getSlotChar(board, row - i, column + i);

                        //         if((lastSlotChar == 'b' || lastSlotChar == 'B') && (nextSlotChar == 'b' || nextSlotChar == 'B')){
                        //             break;
                        //         }

                        //         if(nextSlotChar == 'a' || nextSlotChar == 'A' || nextSlotChar == '!'){
                        //             break;
                        //         }else if(nextSlotChar == ' '){
                        //             lastSlotChar = nextSlotChar; continue;
                        //         }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
                        //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - i, column + i);

                        //             Vector<int[]> newComulPath = new Vector<int[]>();
                        //             newComulPath = clonePath(comulPath);
                        //             //add the carrent piece to the path (from)
                        //             int [] newRowAndColumn = new int[2];
                        //             newRowAndColumn[0] = row; newRowAndColumn[1] = column; 
                        //             newComulPath.addElement(newRowAndColumn);
                                
                        //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - i, column + i, false, true, false);
                        //         }

                        //         lastSlotChar = nextSlotChar;
                        //     }
                        // }
                        // {
                        //     char lastSlotChar = ' ';
                        //     char nextSlotChar;
                        //     for(int i = 1; i < 8; i++){
                        //         nextSlotChar = getSlotChar(board, row - i, column - i);

                        //         if((lastSlotChar == 'b' || lastSlotChar == 'B') && (nextSlotChar == 'b' || nextSlotChar == 'B')){
                        //             break;
                        //         }

                        //         if(nextSlotChar == 'a' || nextSlotChar == 'A' || nextSlotChar == '!'){
                        //             break;
                        //         }else if(nextSlotChar == ' '){
                        //             lastSlotChar = nextSlotChar; continue;
                        //         }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
                        //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - i, column - i);

                        //             Vector<int[]> newComulPath = new Vector<int[]>();
                        //             newComulPath = clonePath(comulPath);
                        //             //add the carrent piece to the path (from)
                        //             int [] newRowAndColumn = new int[2];
                        //             newRowAndColumn[0] = row; newRowAndColumn[1] = column; 
                        //             newComulPath.addElement(newRowAndColumn);
                                
                        //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - i, column - i, false, true, false);
                        //         }

                        //         lastSlotChar = nextSlotChar;
                        //     }
                        // }
                    }
                }
                //-------------------------------------------------------------------------------------------------------------                







                // //the top and the buttom touch lines without corners----------------------------------------------------------
                // if(row == 0){
                //     if(pieceChar == 'B'){
                //         int rowDifferent = row - comulPath.lastElement()[0];
                //         int columnDifferent = column - comulPath.lastElement()[1];

                //         //the piece can't go backward
                //         if(columnDifferent >= 1){//the backward is left
                //             char lastSlotChar = ' ';
                //             char nextSlotChar;
                //             for(int i = 1; i < 8; i++){
                //                 nextSlotChar = getSlotChar(board, row + i, column + i);

                //                 if((lastSlotChar == 'a' || lastSlotChar == 'A') && (nextSlotChar == 'a' || nextSlotChar == 'A')){
                //                     break;
                //                 }

                //                 if(nextSlotChar == 'b' || nextSlotChar == 'B' || nextSlotChar == '!'){
                //                     break;
                //                 }else if(nextSlotChar == ' '){
                //                     lastSlotChar = nextSlotChar; continue;
                //                 }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
                //                     char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + i, column + i);

                //                     Vector<int[]> newComulPath = new Vector<int[]>();
                //                     newComulPath = clonePath(comulPath);
                //                     //add the carrent piece to the path (from)
                //                     int [] newRowAndColumn = new int[2];
                //                     newRowAndColumn[0] = row; newRowAndColumn[1] = column; 
                //                     newComulPath.addElement(newRowAndColumn);
                                
                //                     getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + i, column + i, false, true, false);
                //                 }

                //                 lastSlotChar = nextSlotChar;
                //             }
                //         }else if(columnDifferent <= -1){
                //             char lastSlotChar = ' ';
                //             char nextSlotChar;
                //             for(int i = 1; i < 8; i++){
                //                 nextSlotChar = getSlotChar(board, row + i, column - i);

                //                 if((lastSlotChar == 'a' || lastSlotChar == 'A') && (nextSlotChar == 'a' || nextSlotChar == 'A')){
                //                     break;
                //                 }

                //                 if(nextSlotChar == 'b' || nextSlotChar == 'B' || nextSlotChar == '!'){
                //                     break;
                //                 }else if(nextSlotChar == ' '){
                //                     lastSlotChar = nextSlotChar; continue;
                //                 }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
                //                     char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + i, column - i);

                //                     Vector<int[]> newComulPath = new Vector<int[]>();
                //                     newComulPath = clonePath(comulPath);
                //                     //add the carrent piece to the path (from)
                //                     int [] newRowAndColumn = new int[2];
                //                     newRowAndColumn[0] = row; newRowAndColumn[1] = column; 
                //                     newComulPath.addElement(newRowAndColumn);
                                
                //                     getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + i, column - i, false, true, false);
                //                 }

                //                 lastSlotChar = nextSlotChar;
                //             }
                //         }
                //     }else if(pieceChar == 'A'){
                //         int rowDifferent = row - comulPath.lastElement()[0];
                //         int columnDifferent = column - comulPath.lastElement()[1];

                //         //the piece can't go backward
                //         if(columnDifferent >= 1){//the backward is left
                //             char lastSlotChar = ' ';
                //             char nextSlotChar;
                //             for(int i = 1; i < 8; i++){
                //                 nextSlotChar = getSlotChar(board, row + i, column + i);

                //                 if((lastSlotChar == 'b' || lastSlotChar == 'B') && (nextSlotChar == 'b' || nextSlotChar == 'B')){
                //                     break;
                //                 }

                //                 if(nextSlotChar == 'a' || nextSlotChar == 'A' || nextSlotChar == '!'){
                //                     break;
                //                 }else if(nextSlotChar == ' '){
                //                     lastSlotChar = nextSlotChar; continue;
                //                 }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
                //                     char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + i, column + i);

                //                     Vector<int[]> newComulPath = new Vector<int[]>();
                //                     newComulPath = clonePath(comulPath);
                //                     //add the carrent piece to the path (from)
                //                     int [] newRowAndColumn = new int[2];
                //                     newRowAndColumn[0] = row; newRowAndColumn[1] = column; 
                //                     newComulPath.addElement(newRowAndColumn);
                                
                //                     getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + i, column + i, false, true, false);
                //                 }

                //                 lastSlotChar = nextSlotChar;
                //             }
                //         }else if(columnDifferent <= -1){
                //             char lastSlotChar = ' ';
                //             char nextSlotChar;
                //             for(int i = 1; i < 8; i++){
                //                 nextSlotChar = getSlotChar(board, row + i, column - i);

                //                 if((lastSlotChar == 'b' || lastSlotChar == 'B') && (nextSlotChar == 'b' || nextSlotChar == 'B')){
                //                     break;
                //                 }

                //                 if(nextSlotChar == 'a' || nextSlotChar == 'A' || nextSlotChar == '!'){
                //                     break;
                //                 }else if(nextSlotChar == ' '){
                //                     lastSlotChar = nextSlotChar; continue;
                //                 }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
                //                     char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + i, column - i);

                //                     Vector<int[]> newComulPath = new Vector<int[]>();
                //                     newComulPath = clonePath(comulPath);
                //                     //add the carrent piece to the path (from)
                //                     int [] newRowAndColumn = new int[2];
                //                     newRowAndColumn[0] = row; newRowAndColumn[1] = column; 
                //                     newComulPath.addElement(newRowAndColumn);
                                
                //                     getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + i, column - i, false, true, false);
                //                 }

                //                 lastSlotChar = nextSlotChar;
                //             }
                //         }
                //     }
                // }else if(row == 7){
                //     if(pieceChar == 'B'){
                //         int rowDifferent = row - comulPath.lastElement()[0];
                //         int columnDifferent = column - comulPath.lastElement()[1];

                //         //the piece can't go backward
                //         if(columnDifferent >= 1){//the backward is left
                //             char lastSlotChar = ' ';
                //             char nextSlotChar;
                //             for(int i = 1; i < 8; i++){
                //                 nextSlotChar = getSlotChar(board, row - i, column + i);

                //                 if((lastSlotChar == 'a' || lastSlotChar == 'A') && (nextSlotChar == 'a' || nextSlotChar == 'A')){
                //                     break;
                //                 }

                //                 if(nextSlotChar == 'b' || nextSlotChar == 'B' || nextSlotChar == '!'){
                //                     break;
                //                 }else if(nextSlotChar == ' '){
                //                     lastSlotChar = nextSlotChar; continue;
                //                 }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
                //                     char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - i, column + i);

                //                     Vector<int[]> newComulPath = new Vector<int[]>();
                //                     newComulPath = clonePath(comulPath);
                //                     //add the carrent piece to the path (from)
                //                     int [] newRowAndColumn = new int[2];
                //                     newRowAndColumn[0] = row; newRowAndColumn[1] = column; 
                //                     newComulPath.addElement(newRowAndColumn);
                                
                //                     getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - i, column + i, false, true, false);
                //                 }

                //                 lastSlotChar = nextSlotChar;
                //             }
                //         }else if(columnDifferent <= -1){
                //             char lastSlotChar = ' ';
                //             char nextSlotChar;
                //             for(int i = 1; i < 8; i++){
                //                 nextSlotChar = getSlotChar(board, row - i, column - i);

                //                 if((lastSlotChar == 'a' || lastSlotChar == 'A') && (nextSlotChar == 'a' || nextSlotChar == 'A')){
                //                     break;
                //                 }

                //                 if(nextSlotChar == 'b' || nextSlotChar == 'B' || nextSlotChar == '!'){
                //                     break;
                //                 }else if(nextSlotChar == ' '){
                //                     lastSlotChar = nextSlotChar; continue;
                //                 }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
                //                     char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - i, column - i);

                //                     Vector<int[]> newComulPath = new Vector<int[]>();
                //                     newComulPath = clonePath(comulPath);
                //                     //add the carrent piece to the path (from)
                //                     int [] newRowAndColumn = new int[2];
                //                     newRowAndColumn[0] = row; newRowAndColumn[1] = column; 
                //                     newComulPath.addElement(newRowAndColumn);
                                
                //                     getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - i, column - i, false, true, false);
                //                 }

                //                 lastSlotChar = nextSlotChar;
                //             }
                //         }
                //     }else if(pieceChar == 'A'){
                //         int rowDifferent = row - comulPath.lastElement()[0];
                //         int columnDifferent = column - comulPath.lastElement()[1];

                //         //the piece can't go backward
                //         if(columnDifferent >= 1){//the backward is left
                //             char lastSlotChar = ' ';
                //             char nextSlotChar;
                //             for(int i = 1; i < 8; i++){
                //                 nextSlotChar = getSlotChar(board, row - i, column + i);

                //                 if((lastSlotChar == 'b' || lastSlotChar == 'B') && (nextSlotChar == 'b' || nextSlotChar == 'B')){
                //                     break;
                //                 }

                //                 if(nextSlotChar == 'a' || nextSlotChar == 'A' || nextSlotChar == '!'){
                //                     break;
                //                 }else if(nextSlotChar == ' '){
                //                     lastSlotChar = nextSlotChar; continue;
                //                 }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
                //                     char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - i, column + i);

                //                     Vector<int[]> newComulPath = new Vector<int[]>();
                //                     newComulPath = clonePath(comulPath);
                //                     //add the carrent piece to the path (from)
                //                     int [] newRowAndColumn = new int[2];
                //                     newRowAndColumn[0] = row; newRowAndColumn[1] = column; 
                //                     newComulPath.addElement(newRowAndColumn);
                                
                //                     getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - i, column + i, false, true, false);
                //                 }

                //                 lastSlotChar = nextSlotChar;
                //             }
                //         }else if(columnDifferent <= -1){
                //             char lastSlotChar = ' ';
                //             char nextSlotChar;
                //             for(int i = 1; i < 8; i++){
                //                 nextSlotChar = getSlotChar(board, row - i, column - i);

                //                 if((lastSlotChar == 'b' || lastSlotChar == 'B') && (nextSlotChar == 'b' || nextSlotChar == 'B')){
                //                     break;
                //                 }

                //                 if(nextSlotChar == 'a' || nextSlotChar == 'A' || nextSlotChar == '!'){
                //                     break;
                //                 }else if(nextSlotChar == ' '){
                //                     lastSlotChar = nextSlotChar; continue;
                //                 }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
                //                     char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - i, column - i);

                //                     Vector<int[]> newComulPath = new Vector<int[]>();
                //                     newComulPath = clonePath(comulPath);
                //                     //add the carrent piece to the path (from)
                //                     int [] newRowAndColumn = new int[2];
                //                     newRowAndColumn[0] = row; newRowAndColumn[1] = column; 
                //                     newComulPath.addElement(newRowAndColumn);
                                
                //                     getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - i, column - i, false, true, false);
                //                 }

                //                 lastSlotChar = nextSlotChar;
                //             }
                //         }
                //     }
                // }else {
                //     if(pieceChar == 'b'){
                //         char nextSlotChar1 = getSlotChar(board, row + 1, column + 1);
                //         if(nextSlotChar1 == 'a' || nextSlotChar1 == 'A'){
                //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + 1, column + 1);

                //             Vector<int[]> newComulPath = new Vector<int[]>();
                //             newComulPath = clonePath(comulPath);
                //             //add the carrent piece to the path (from)
                //             int [] rowAndColumn1 = new int[2];
                //             rowAndColumn1[0] = row; rowAndColumn1[1] = column; 
                //             newComulPath.addElement(rowAndColumn1);
                        
                //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + 1, column + 1, false, true, false);
                //         }
                //         //------------------
                //         char nextSlotChar2 = getSlotChar(board, row + 1, column - 1);
                //         if(nextSlotChar2 == 'a' || nextSlotChar2 == 'A'){
                //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + 1, column - 1);

                //             Vector<int[]> newComulPath = new Vector<int[]>();
                //             newComulPath = clonePath(comulPath);
                //             //add the carrent piece to the path (from)
                //             int [] rowAndColumn1 = new int[2];
                //             rowAndColumn1[0] = row; rowAndColumn1[1] = column; 
                //             newComulPath.addElement(rowAndColumn1);
                        
                //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + 1, column - 1, false, true, false);
                //         }
                //     }else if(pieceChar == 'a'){
                //         char nextSlotChar1 = getSlotChar(board, row - 1, column + 1);
                //         if(nextSlotChar1 == 'b' || nextSlotChar1 == 'B'){
                //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - 1, column + 1);

                //             Vector<int[]> newComulPath = new Vector<int[]>();
                //             newComulPath = clonePath(comulPath);
                //             //add the carrent piece to the path (from)
                //             int [] rowAndColumn1 = new int[2];
                //             rowAndColumn1[0] = row; rowAndColumn1[1] = column; 
                //             newComulPath.addElement(rowAndColumn1);
                        
                //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - 1, column + 1, false, true, false);
                //         }
                //         //------------------
                //         char nextSlotChar2 = getSlotChar(board, row - 1, column - 1);
                //         if(nextSlotChar2 == 'b' || nextSlotChar2 == 'B'){
                //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - 1, column - 1);

                //             Vector<int[]> newComulPath = new Vector<int[]>();
                //             newComulPath = clonePath(comulPath);
                //             //add the carrent piece to the path (from)
                //             int [] rowAndColumn1 = new int[2];
                //             rowAndColumn1[0] = row; rowAndColumn1[1] = column; 
                //             newComulPath.addElement(rowAndColumn1);
                        
                //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - 1, column - 1, false, true, false);
                //         }
                //     }else if(pieceChar == 'B'){
                //         int rowDifferent = row - comulPath.lastElement()[0];
                //         int columnDifferent = column - comulPath.lastElement()[1];

                //         //the piece can't go backward
                //         if(rowDifferent >= 1 && columnDifferent >= 1){//the backward is top right
                //             {
                //                 char lastSlotChar = ' ';
                //                 char nextSlotChar;
                //                 for(int i = 1; i < 8; i++){
                //                     nextSlotChar = getSlotChar(board, row + i, column + i);

                //                     if((lastSlotChar == 'a' || lastSlotChar == 'A') && (nextSlotChar == 'a' || nextSlotChar == 'A')){
                //                         break;
                //                     }

                //                     if(nextSlotChar == 'b' || nextSlotChar == 'B' || nextSlotChar == '!'){
                //                         break;
                //                     }else if(nextSlotChar == ' '){
                //                         lastSlotChar = nextSlotChar; continue;
                //                     }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
                //                         char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + i, column + i);

                //                         Vector<int[]> newComulPath = new Vector<int[]>();
                //                         newComulPath = clonePath(comulPath);
                //                         //add the carrent piece to the path (from)
                //                         int [] newRowAndColumn = new int[2];
                //                         newRowAndColumn[0] = row; newRowAndColumn[1] = column; 
                //                         newComulPath.addElement(newRowAndColumn);
                                    
                //                         getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + i, column + i, false, true, false);
                //                     }

                //                     lastSlotChar = nextSlotChar;
                //                 }
                //             }
                //             {
                //                 char lastSlotChar = ' ';
                //                 char nextSlotChar;
                //                 for(int i = 1; i < 8; i++){
                //                     nextSlotChar = getSlotChar(board, row + i, column - i);

                //                     if((lastSlotChar == 'a' || lastSlotChar == 'A') && (nextSlotChar == 'a' || nextSlotChar == 'A')){
                //                         break;
                //                     }

                //                     if(nextSlotChar == 'b' || nextSlotChar == 'B' || nextSlotChar == '!'){
                //                         break;
                //                     }else if(nextSlotChar == ' '){
                //                         lastSlotChar = nextSlotChar; continue;
                //                     }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
                //                         char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + i, column - i);

                //                         Vector<int[]> newComulPath = new Vector<int[]>();
                //                         newComulPath = clonePath(comulPath);
                //                         //add the carrent piece to the path (from)
                //                         int [] newRowAndColumn = new int[2];
                //                         newRowAndColumn[0] = row; newRowAndColumn[1] = column; 
                //                         newComulPath.addElement(newRowAndColumn);
                                    
                //                         getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + i, column - i, false, true, false);
                //                     }

                //                     lastSlotChar = nextSlotChar;
                //                 }
                //             }
                //             {
                //                 char lastSlotChar = ' ';
                //                 char nextSlotChar;
                //                 for(int i = 1; i < 8; i++){
                //                     nextSlotChar = getSlotChar(board, row - i, column + i);

                //                     if((lastSlotChar == 'a' || lastSlotChar == 'A') && (nextSlotChar == 'a' || nextSlotChar == 'A')){
                //                         break;
                //                     }

                //                     if(nextSlotChar == 'b' || nextSlotChar == 'B' || nextSlotChar == '!'){
                //                         break;
                //                     }else if(nextSlotChar == ' '){
                //                         lastSlotChar = nextSlotChar; continue;
                //                     }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
                //                         char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - i, column + i);

                //                         Vector<int[]> newComulPath = new Vector<int[]>();
                //                         newComulPath = clonePath(comulPath);
                //                         //add the carrent piece to the path (from)
                //                         int [] newRowAndColumn = new int[2];
                //                         newRowAndColumn[0] = row; newRowAndColumn[1] = column; 
                //                         newComulPath.addElement(newRowAndColumn);
                                    
                //                         getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - i, column + i, false, true, false);
                //                     }

                //                     lastSlotChar = nextSlotChar;
                //                 }
                //             }
                //         } 
                //         else if(rowDifferent >= 1 && columnDifferent <= -1){//the backward is top left
                //             {
                //                 char lastSlotChar = ' ';
                //                 char nextSlotChar;
                //                 for(int i = 1; i < 8; i++){
                //                     nextSlotChar = getSlotChar(board, row + i, column + i);

                //                     if((lastSlotChar == 'a' || lastSlotChar == 'A') && (nextSlotChar == 'a' || nextSlotChar == 'A')){
                //                         break;
                //                     }

                //                     if(nextSlotChar == 'b' || nextSlotChar == 'B' || nextSlotChar == '!'){
                //                         break;
                //                     }else if(nextSlotChar == ' '){
                //                         lastSlotChar = nextSlotChar; continue;
                //                     }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
                //                         char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + i, column + i);

                //                         Vector<int[]> newComulPath = new Vector<int[]>();
                //                         newComulPath = clonePath(comulPath);
                //                         //add the carrent piece to the path (from)
                //                         int [] newRowAndColumn = new int[2];
                //                         newRowAndColumn[0] = row; newRowAndColumn[1] = column; 
                //                         newComulPath.addElement(newRowAndColumn);
                                    
                //                         getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + i, column + i, false, true, false);
                //                     }

                //                     lastSlotChar = nextSlotChar;
                //                 }
                //             }
                //             {
                //                 char lastSlotChar = ' ';
                //                 char nextSlotChar;
                //                 for(int i = 1; i < 8; i++){
                //                     nextSlotChar = getSlotChar(board, row + i, column - i);

                //                     if((lastSlotChar == 'a' || lastSlotChar == 'A') && (nextSlotChar == 'a' || nextSlotChar == 'A')){
                //                         break;
                //                     }

                //                     if(nextSlotChar == 'b' || nextSlotChar == 'B' || nextSlotChar == '!'){
                //                         break;
                //                     }else if(nextSlotChar == ' '){
                //                         lastSlotChar = nextSlotChar; continue;
                //                     }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
                //                         char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + i, column - i);

                //                         Vector<int[]> newComulPath = new Vector<int[]>();
                //                         newComulPath = clonePath(comulPath);
                //                         //add the carrent piece to the path (from)
                //                         int [] newRowAndColumn = new int[2];
                //                         newRowAndColumn[0] = row; newRowAndColumn[1] = column; 
                //                         newComulPath.addElement(newRowAndColumn);
                                    
                //                         getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + i, column - i, false, true, false);
                //                     }

                //                     lastSlotChar = nextSlotChar;
                //                 }
                //             }
                //             {
                //                 char lastSlotChar = ' ';
                //                 char nextSlotChar;
                //                 for(int i = 1; i < 8; i++){
                //                     nextSlotChar = getSlotChar(board, row - i, column - i);

                //                     if((lastSlotChar == 'a' || lastSlotChar == 'A') && (nextSlotChar == 'a' || nextSlotChar == 'A')){
                //                         break;
                //                     }

                //                     if(nextSlotChar == 'b' || nextSlotChar == 'B' || nextSlotChar == '!'){
                //                         break;
                //                     }else if(nextSlotChar == ' '){
                //                         lastSlotChar = nextSlotChar; continue;
                //                     }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
                //                         char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - i, column - i);

                //                         Vector<int[]> newComulPath = new Vector<int[]>();
                //                         newComulPath = clonePath(comulPath);
                //                         //add the carrent piece to the path (from)
                //                         int [] newRowAndColumn = new int[2];
                //                         newRowAndColumn[0] = row; newRowAndColumn[1] = column; 
                //                         newComulPath.addElement(newRowAndColumn);
                                    
                //                         getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - i, column - i, false, true, false);
                //                     }

                //                     lastSlotChar = nextSlotChar;
                //                 }
                //             }
                //         } 
                //         else if(rowDifferent <= -1 && columnDifferent >= 1){//the backward is buttom right
                //             {
                //                 char lastSlotChar = ' ';
                //                 char nextSlotChar;
                //                 for(int i = 1; i < 8; i++){
                //                     nextSlotChar = getSlotChar(board, row + i, column + i);

                //                     if((lastSlotChar == 'a' || lastSlotChar == 'A') && (nextSlotChar == 'a' || nextSlotChar == 'A')){
                //                         break;
                //                     }

                //                     if(nextSlotChar == 'b' || nextSlotChar == 'B' || nextSlotChar == '!'){
                //                         break;
                //                     }else if(nextSlotChar == ' '){
                //                         lastSlotChar = nextSlotChar; continue;
                //                     }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
                //                         char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + i, column + i);

                //                         Vector<int[]> newComulPath = new Vector<int[]>();
                //                         newComulPath = clonePath(comulPath);
                //                         //add the carrent piece to the path (from)
                //                         int [] newRowAndColumn = new int[2];
                //                         newRowAndColumn[0] = row; newRowAndColumn[1] = column; 
                //                         newComulPath.addElement(newRowAndColumn);
                                    
                //                         getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + i, column + i, false, true, false);
                //                     }

                //                     lastSlotChar = nextSlotChar;
                //                 }
                //             }
                //             {
                //                 char lastSlotChar = ' ';
                //                 char nextSlotChar;
                //                 for(int i = 1; i < 8; i++){
                //                     nextSlotChar = getSlotChar(board, row - i, column + i);

                //                     if((lastSlotChar == 'a' || lastSlotChar == 'A') && (nextSlotChar == 'a' || nextSlotChar == 'A')){
                //                         break;
                //                     }

                //                     if(nextSlotChar == 'b' || nextSlotChar == 'B' || nextSlotChar == '!'){
                //                         break;
                //                     }else if(nextSlotChar == ' '){
                //                         lastSlotChar = nextSlotChar; continue;
                //                     }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
                //                         char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - i, column + i);

                //                         Vector<int[]> newComulPath = new Vector<int[]>();
                //                         newComulPath = clonePath(comulPath);
                //                         //add the carrent piece to the path (from)
                //                         int [] newRowAndColumn = new int[2];
                //                         newRowAndColumn[0] = row; newRowAndColumn[1] = column; 
                //                         newComulPath.addElement(newRowAndColumn);
                                    
                //                         getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - i, column + i, false, true, false);
                //                     }

                //                     lastSlotChar = nextSlotChar;
                //                 }
                //             }
                //             {
                //                 char lastSlotChar = ' ';
                //                 char nextSlotChar;
                //                 for(int i = 1; i < 8; i++){
                //                     nextSlotChar = getSlotChar(board, row - i, column - i);

                //                     if((lastSlotChar == 'a' || lastSlotChar == 'A') && (nextSlotChar == 'a' || nextSlotChar == 'A')){
                //                         break;
                //                     }

                //                     if(nextSlotChar == 'b' || nextSlotChar == 'B' || nextSlotChar == '!'){
                //                         break;
                //                     }else if(nextSlotChar == ' '){
                //                         lastSlotChar = nextSlotChar; continue;
                //                     }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
                //                         char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - i, column - i);

                //                         Vector<int[]> newComulPath = new Vector<int[]>();
                //                         newComulPath = clonePath(comulPath);
                //                         //add the carrent piece to the path (from)
                //                         int [] newRowAndColumn = new int[2];
                //                         newRowAndColumn[0] = row; newRowAndColumn[1] = column; 
                //                         newComulPath.addElement(newRowAndColumn);
                                    
                //                         getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - i, column - i, false, true, false);
                //                     }

                //                     lastSlotChar = nextSlotChar;
                //                 }
                //             }
                //         } 
                //         else if(rowDifferent <= -1 && columnDifferent <= -1){//the backward is buttom left
                //             {
                //                 char lastSlotChar = ' ';
                //                 char nextSlotChar;
                //                 for(int i = 1; i < 8; i++){
                //                     nextSlotChar = getSlotChar(board, row + i, column - i);

                //                     if((lastSlotChar == 'a' || lastSlotChar == 'A') && (nextSlotChar == 'a' || nextSlotChar == 'A')){
                //                         break;
                //                     }

                //                     if(nextSlotChar == 'b' || nextSlotChar == 'B' || nextSlotChar == '!'){
                //                         break;
                //                     }else if(nextSlotChar == ' '){
                //                         lastSlotChar = nextSlotChar; continue;
                //                     }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
                //                         char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + i, column - i);

                //                         Vector<int[]> newComulPath = new Vector<int[]>();
                //                         newComulPath = clonePath(comulPath);
                //                         //add the carrent piece to the path (from)
                //                         int [] newRowAndColumn = new int[2];
                //                         newRowAndColumn[0] = row; newRowAndColumn[1] = column; 
                //                         newComulPath.addElement(newRowAndColumn);
                                    
                //                         getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + i, column - i, false, true, false);
                //                     }

                //                     lastSlotChar = nextSlotChar;
                //                 }
                //             }
                //             {
                //                 char lastSlotChar = ' ';
                //                 char nextSlotChar;
                //                 for(int i = 1; i < 8; i++){
                //                     nextSlotChar = getSlotChar(board, row - i, column + i);

                //                     if((lastSlotChar == 'a' || lastSlotChar == 'A') && (nextSlotChar == 'a' || nextSlotChar == 'A')){
                //                         break;
                //                     }

                //                     if(nextSlotChar == 'b' || nextSlotChar == 'B' || nextSlotChar == '!'){
                //                         break;
                //                     }else if(nextSlotChar == ' '){
                //                         lastSlotChar = nextSlotChar; continue;
                //                     }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
                //                         char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - i, column + i);

                //                         Vector<int[]> newComulPath = new Vector<int[]>();
                //                         newComulPath = clonePath(comulPath);
                //                         //add the carrent piece to the path (from)
                //                         int [] newRowAndColumn = new int[2];
                //                         newRowAndColumn[0] = row; newRowAndColumn[1] = column; 
                //                         newComulPath.addElement(newRowAndColumn);
                                    
                //                         getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - i, column + i, false, true, false);
                //                     }

                //                     lastSlotChar = nextSlotChar;
                //                 }
                //             }
                //             {
                //                 char lastSlotChar = ' ';
                //                 char nextSlotChar;
                //                 for(int i = 1; i < 8; i++){
                //                     nextSlotChar = getSlotChar(board, row - i, column - i);

                //                     if((lastSlotChar == 'a' || lastSlotChar == 'A') && (nextSlotChar == 'a' || nextSlotChar == 'A')){
                //                         break;
                //                     }

                //                     if(nextSlotChar == 'b' || nextSlotChar == 'B' || nextSlotChar == '!'){
                //                         break;
                //                     }else if(nextSlotChar == ' '){
                //                         lastSlotChar = nextSlotChar; continue;
                //                     }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
                //                         char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - i, column - i);

                //                         Vector<int[]> newComulPath = new Vector<int[]>();
                //                         newComulPath = clonePath(comulPath);
                //                         //add the carrent piece to the path (from)
                //                         int [] newRowAndColumn = new int[2];
                //                         newRowAndColumn[0] = row; newRowAndColumn[1] = column; 
                //                         newComulPath.addElement(newRowAndColumn);
                                    
                //                         getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - i, column - i, false, true, false);
                //                     }

                //                     lastSlotChar = nextSlotChar;
                //                 }
                //             }
                //         }
                //     }else if(pieceChar == 'A'){
                //         int rowDifferent = row - comulPath.lastElement()[0];
                //         int columnDifferent = column - comulPath.lastElement()[1];

                //         //the piece can't go backward
                //         if(rowDifferent >= 1 && columnDifferent >= 1){//the backward is top right
                //             {
                //                 char lastSlotChar = ' ';
                //                 char nextSlotChar;
                //                 for(int i = 1; i < 8; i++){
                //                     nextSlotChar = getSlotChar(board, row + i, column + i);

                //                     if((lastSlotChar == 'b' || lastSlotChar == 'B') && (nextSlotChar == 'b' || nextSlotChar == 'B')){
                //                         break;
                //                     }

                //                     if(nextSlotChar == 'a' || nextSlotChar == 'A' || nextSlotChar == '!'){
                //                         break;
                //                     }else if(nextSlotChar == ' '){
                //                         lastSlotChar = nextSlotChar; continue;
                //                     }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
                //                         char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + i, column + i);

                //                         Vector<int[]> newComulPath = new Vector<int[]>();
                //                         newComulPath = clonePath(comulPath);
                //                         //add the carrent piece to the path (from)
                //                         int [] newRowAndColumn = new int[2];
                //                         newRowAndColumn[0] = row; newRowAndColumn[1] = column; 
                //                         newComulPath.addElement(newRowAndColumn);
                                    
                //                         getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + i, column + i, false, true, false);
                //                     }

                //                     lastSlotChar = nextSlotChar;
                //                 }
                //             }
                //             {
                //                 char lastSlotChar = ' ';
                //                 char nextSlotChar;
                //                 for(int i = 1; i < 8; i++){
                //                     nextSlotChar = getSlotChar(board, row + i, column - i);

                //                     if((lastSlotChar == 'b' || lastSlotChar == 'B') && (nextSlotChar == 'b' || nextSlotChar == 'B')){
                //                         break;
                //                     }

                //                     if(nextSlotChar == 'a' || nextSlotChar == 'A' || nextSlotChar == '!'){
                //                         break;
                //                     }else if(nextSlotChar == ' '){
                //                         lastSlotChar = nextSlotChar; continue;
                //                     }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
                //                         char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + i, column - i);

                //                         Vector<int[]> newComulPath = new Vector<int[]>();
                //                         newComulPath = clonePath(comulPath);
                //                         //add the carrent piece to the path (from)
                //                         int [] newRowAndColumn = new int[2];
                //                         newRowAndColumn[0] = row; newRowAndColumn[1] = column; 
                //                         newComulPath.addElement(newRowAndColumn);
                                    
                //                         getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + i, column - i, false, true, false);
                //                     }

                //                     lastSlotChar = nextSlotChar;
                //                 }
                //             }
                //             {
                //                 char lastSlotChar = ' ';
                //                 char nextSlotChar;
                //                 for(int i = 1; i < 8; i++){
                //                     nextSlotChar = getSlotChar(board, row - i, column + i);

                //                     if((lastSlotChar == 'b' || lastSlotChar == 'B') && (nextSlotChar == 'b' || nextSlotChar == 'B')){
                //                         break;
                //                     }

                //                     if(nextSlotChar == 'a' || nextSlotChar == 'A' || nextSlotChar == '!'){
                //                         break;
                //                     }else if(nextSlotChar == ' '){
                //                         lastSlotChar = nextSlotChar; continue;
                //                     }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
                //                         char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - i, column + i);

                //                         Vector<int[]> newComulPath = new Vector<int[]>();
                //                         newComulPath = clonePath(comulPath);
                //                         //add the carrent piece to the path (from)
                //                         int [] newRowAndColumn = new int[2];
                //                         newRowAndColumn[0] = row; newRowAndColumn[1] = column; 
                //                         newComulPath.addElement(newRowAndColumn);
                                    
                //                         getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - i, column + i, false, true, false);
                //                     }

                //                     lastSlotChar = nextSlotChar;
                //                 }
                //             }
                //         } 
                //         else if(rowDifferent >= 1 && columnDifferent <= -1){//the backward is top left
                //             {
                //                 char lastSlotChar = ' ';
                //                 char nextSlotChar;
                //                 for(int i = 1; i < 8; i++){
                //                     nextSlotChar = getSlotChar(board, row + i, column + i);

                //                     if((lastSlotChar == 'b' || lastSlotChar == 'B') && (nextSlotChar == 'b' || nextSlotChar == 'B')){
                //                         break;
                //                     }

                //                     if(nextSlotChar == 'a' || nextSlotChar == 'A' || nextSlotChar == '!'){
                //                         break;
                //                     }else if(nextSlotChar == ' '){
                //                         lastSlotChar = nextSlotChar; continue;
                //                     }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
                //                         char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + i, column + i);

                //                         Vector<int[]> newComulPath = new Vector<int[]>();
                //                         newComulPath = clonePath(comulPath);
                //                         //add the carrent piece to the path (from)
                //                         int [] newRowAndColumn = new int[2];
                //                         newRowAndColumn[0] = row; newRowAndColumn[1] = column; 
                //                         newComulPath.addElement(newRowAndColumn);
                                    
                //                         getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + i, column + i, false, true, false);
                //                     }

                //                     lastSlotChar = nextSlotChar;
                //                 }
                //             }
                //             {
                //                 char lastSlotChar = ' ';
                //                 char nextSlotChar;
                //                 for(int i = 1; i < 8; i++){
                //                     nextSlotChar = getSlotChar(board, row + i, column - i);

                //                     if((lastSlotChar == 'b' || lastSlotChar == 'B') && (nextSlotChar == 'b' || nextSlotChar == 'B')){
                //                         break;
                //                     }

                //                     if(nextSlotChar == 'a' || nextSlotChar == 'A' || nextSlotChar == '!'){
                //                         break;
                //                     }else if(nextSlotChar == ' '){
                //                         lastSlotChar = nextSlotChar; continue;
                //                     }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
                //                         char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + i, column - i);

                //                         Vector<int[]> newComulPath = new Vector<int[]>();
                //                         newComulPath = clonePath(comulPath);
                //                         //add the carrent piece to the path (from)
                //                         int [] newRowAndColumn = new int[2];
                //                         newRowAndColumn[0] = row; newRowAndColumn[1] = column; 
                //                         newComulPath.addElement(newRowAndColumn);
                                    
                //                         getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + i, column - i, false, true, false);
                //                     }

                //                     lastSlotChar = nextSlotChar;
                //                 }
                //             }
                //             {
                //                 char lastSlotChar = ' ';
                //                 char nextSlotChar;
                //                 for(int i = 1; i < 8; i++){
                //                     nextSlotChar = getSlotChar(board, row - i, column - i);

                //                     if((lastSlotChar == 'b' || lastSlotChar == 'B') && (nextSlotChar == 'b' || nextSlotChar == 'B')){
                //                         break;
                //                     }

                //                     if(nextSlotChar == 'a' || nextSlotChar == 'A' || nextSlotChar == '!'){
                //                         break;
                //                     }else if(nextSlotChar == ' '){
                //                         lastSlotChar = nextSlotChar; continue;
                //                     }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
                //                         char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - i, column - i);

                //                         Vector<int[]> newComulPath = new Vector<int[]>();
                //                         newComulPath = clonePath(comulPath);
                //                         //add the carrent piece to the path (from)
                //                         int [] newRowAndColumn = new int[2];
                //                         newRowAndColumn[0] = row; newRowAndColumn[1] = column; 
                //                         newComulPath.addElement(newRowAndColumn);
                                    
                //                         getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - i, column - i, false, true, false);
                //                     }

                //                     lastSlotChar = nextSlotChar;
                //                 }
                //             }
                //         } 
                //         else if(rowDifferent <= -1 && columnDifferent >= 1){//the backward is buttom left
                //             {
                //                 char lastSlotChar = ' ';
                //                 char nextSlotChar;
                //                 for(int i = 1; i < 8; i++){
                //                     nextSlotChar = getSlotChar(board, row + i, column + i);

                //                     if((lastSlotChar == 'b' || lastSlotChar == 'B') && (nextSlotChar == 'b' || nextSlotChar == 'B')){
                //                         break;
                //                     }

                //                     if(nextSlotChar == 'a' || nextSlotChar == 'A' || nextSlotChar == '!'){
                //                         break;
                //                     }else if(nextSlotChar == ' '){
                //                         lastSlotChar = nextSlotChar; continue;
                //                     }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
                //                         char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + i, column + i);

                //                         Vector<int[]> newComulPath = new Vector<int[]>();
                //                         newComulPath = clonePath(comulPath);
                //                         //add the carrent piece to the path (from)
                //                         int [] newRowAndColumn = new int[2];
                //                         newRowAndColumn[0] = row; newRowAndColumn[1] = column; 
                //                         newComulPath.addElement(newRowAndColumn);
                                    
                //                         getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + i, column + i, false, true, false);
                //                     }

                //                     lastSlotChar = nextSlotChar;
                //                 }
                //             }
                //             {
                //                 char lastSlotChar = ' ';
                //                 char nextSlotChar;
                //                 for(int i = 1; i < 8; i++){
                //                     nextSlotChar = getSlotChar(board, row - i, column + i);

                //                     if((lastSlotChar == 'b' || lastSlotChar == 'B') && (nextSlotChar == 'b' || nextSlotChar == 'B')){
                //                         break;
                //                     }

                //                     if(nextSlotChar == 'a' || nextSlotChar == 'A' || nextSlotChar == '!'){
                //                         break;
                //                     }else if(nextSlotChar == ' '){
                //                         lastSlotChar = nextSlotChar; continue;
                //                     }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
                //                         char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - i, column + i);

                //                         Vector<int[]> newComulPath = new Vector<int[]>();
                //                         newComulPath = clonePath(comulPath);
                //                         //add the carrent piece to the path (from)
                //                         int [] newRowAndColumn = new int[2];
                //                         newRowAndColumn[0] = row; newRowAndColumn[1] = column; 
                //                         newComulPath.addElement(newRowAndColumn);
                                    
                //                         getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - i, column + i, false, true, false);
                //                     }

                //                     lastSlotChar = nextSlotChar;
                //                 }
                //             }
                //             {
                //                 char lastSlotChar = ' ';
                //                 char nextSlotChar;
                //                 for(int i = 1; i < 8; i++){
                //                     nextSlotChar = getSlotChar(board, row - i, column - i);

                //                     if((lastSlotChar == 'b' || lastSlotChar == 'B') && (nextSlotChar == 'b' || nextSlotChar == 'B')){
                //                         break;
                //                     }

                //                     if(nextSlotChar == 'a' || nextSlotChar == 'A' || nextSlotChar == '!'){
                //                         break;
                //                     }else if(nextSlotChar == ' '){
                //                         lastSlotChar = nextSlotChar; continue;
                //                     }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
                //                         char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - i, column - i);

                //                         Vector<int[]> newComulPath = new Vector<int[]>();
                //                         newComulPath = clonePath(comulPath);
                //                         //add the carrent piece to the path (from)
                //                         int [] newRowAndColumn = new int[2];
                //                         newRowAndColumn[0] = row; newRowAndColumn[1] = column; 
                //                         newComulPath.addElement(newRowAndColumn);
                                    
                //                         getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - i, column - i, false, true, false);
                //                     }

                //                     lastSlotChar = nextSlotChar;
                //                 }
                //             }
                //         } 
                //         else if(rowDifferent <= -1 && columnDifferent <= -1){//the backward is buttom right
                //             {
                //                 char lastSlotChar = ' ';
                //                 char nextSlotChar;
                //                 for(int i = 1; i < 8; i++){
                //                     nextSlotChar = getSlotChar(board, row + i, column - i);

                //                     if((lastSlotChar == 'b' || lastSlotChar == 'B') && (nextSlotChar == 'b' || nextSlotChar == 'B')){
                //                         break;
                //                     }

                //                     if(nextSlotChar == 'a' || nextSlotChar == 'A' || nextSlotChar == '!'){
                //                         break;
                //                     }else if(nextSlotChar == ' '){
                //                         lastSlotChar = nextSlotChar; continue;
                //                     }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
                //                         char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + i, column - i);

                //                         Vector<int[]> newComulPath = new Vector<int[]>();
                //                         newComulPath = clonePath(comulPath);
                //                         //add the carrent piece to the path (from)
                //                         int [] newRowAndColumn = new int[2];
                //                         newRowAndColumn[0] = row; newRowAndColumn[1] = column; 
                //                         newComulPath.addElement(newRowAndColumn);
                                    
                //                         getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + i, column - i, false, true, false);
                //                     }

                //                     lastSlotChar = nextSlotChar;
                //                 }
                //             }
                //             {
                //                 char lastSlotChar = ' ';
                //                 char nextSlotChar;
                //                 for(int i = 1; i < 8; i++){
                //                     nextSlotChar = getSlotChar(board, row - i, column + i);

                //                     if((lastSlotChar == 'b' || lastSlotChar == 'B') && (nextSlotChar == 'b' || nextSlotChar == 'B')){
                //                         break;
                //                     }

                //                     if(nextSlotChar == 'a' || nextSlotChar == 'A' || nextSlotChar == '!'){
                //                         break;
                //                     }else if(nextSlotChar == ' '){
                //                         lastSlotChar = nextSlotChar; continue;
                //                     }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
                //                         char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - i, column + i);

                //                         Vector<int[]> newComulPath = new Vector<int[]>();
                //                         newComulPath = clonePath(comulPath);
                //                         //add the carrent piece to the path (from)
                //                         int [] newRowAndColumn = new int[2];
                //                         newRowAndColumn[0] = row; newRowAndColumn[1] = column; 
                //                         newComulPath.addElement(newRowAndColumn);
                                    
                //                         getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - i, column + i, false, true, false);
                //                     }

                //                     lastSlotChar = nextSlotChar;
                //                 }
                //             }
                //             {
                //                 char lastSlotChar = ' ';
                //                 char nextSlotChar;
                //                 for(int i = 1; i < 8; i++){
                //                     nextSlotChar = getSlotChar(board, row - i, column - i);

                //                     if((lastSlotChar == 'b' || lastSlotChar == 'B') && (nextSlotChar == 'b' || nextSlotChar == 'B')){
                //                         break;
                //                     }

                //                     if(nextSlotChar == 'a' || nextSlotChar == 'A' || nextSlotChar == '!'){
                //                         break;
                //                     }else if(nextSlotChar == ' '){
                //                         lastSlotChar = nextSlotChar; continue;
                //                     }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
                //                         char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - i, column - i);

                //                         Vector<int[]> newComulPath = new Vector<int[]>();
                //                         newComulPath = clonePath(comulPath);
                //                         //add the carrent piece to the path (from)
                //                         int [] newRowAndColumn = new int[2];
                //                         newRowAndColumn[0] = row; newRowAndColumn[1] = column; 
                //                         newComulPath.addElement(newRowAndColumn);
                                    
                //                         getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - i, column - i, false, true, false);
                //                     }

                //                     lastSlotChar = nextSlotChar;
                //                 }
                //             }
                //         }
                //     }
                // }
            }
        }
    }
    
    //get the list of chooses available in the board of a specific player in one specific piece
    public Vector<Vector<int[]>> getListofChoosesAvailableOfOnePiece(char[][] board, int player, int row, int column){
        Vector<Vector<int[]>> listChooses = new Vector<Vector<int[]>>();
        Vector<int[]> comulPath = new Vector<int[]>();

        if(rule == "Spain"){
            getListOfChoosesOfOnePieceSpain(board, player, listChooses, ' ', comulPath, row, column, true, false, false);
        }

        //search for the longeest path
        int lengthOfTheLongestPath = 0;
        for(int i = 0; i < listChooses.size(); i++){
            if(listChooses.get(i).size() > lengthOfTheLongestPath){
                //System.out.println(listChooses.get(i).size());
                lengthOfTheLongestPath = listChooses.get(i).size();
            }
        }

        //filtering longeest paths
        Vector<Vector<int[]>> filtredListChooses = new Vector<Vector<int[]>>();
        for(int i = 0; i < listChooses.size(); i++){
            if(listChooses.get(i).size() == lengthOfTheLongestPath){
                filtredListChooses.addElement(listChooses.get(i));
            }
        }

        return filtredListChooses;
    }

    //get the list of chooses available in the board of a specific player
    public Vector<Vector<Vector<int[]>>> getListofChooses(char[][] board, int player){
        Vector<Vector<Vector<int[]>>> listChooses = new Vector<Vector<Vector<int[]>>>();
        Vector<int[]> AllPlayerPieces = getAllPieceRowAndColumn(board, player);


        for(int i = 0; i < AllPlayerPieces.size(); i++){
            listChooses.addElement(getListofChoosesAvailableOfOnePiece(board, player, AllPlayerPieces.elementAt(i)[0], AllPlayerPieces.elementAt(i)[1]));
        }


        //search for the longeest paths
        int lengthOfTheLongestPath = 0;
        for(int i = 0; i < listChooses.size(); i++){
            if(listChooses.get(i).size() > 0){
                if(listChooses.get(i).get(0).size() > lengthOfTheLongestPath){
                    lengthOfTheLongestPath = listChooses.get(i).get(0).size();
                }
            }
        }

        //filtering the longeest paths for every piece
        Vector<Vector<Vector<int[]>>> filtredListChooses = new  Vector<Vector<Vector<int[]>>>();
        for(int i = 0; i < listChooses.size(); i++){
            if(listChooses.get(i).size() > 0){
                if(listChooses.get(i).get(0).size() == lengthOfTheLongestPath){
                    filtredListChooses.addElement(listChooses.get(i));
                }
            }
        }

        return filtredListChooses;
    }

    //get the list of piece that the player can move
    public Vector<int[]> getListOfPieceCanMove(char[][] board, int player){
        Vector<Vector<Vector<int[]>>> listofChooses = getListofChooses(board, player);
        
        Vector<int[]> filtredListPieceSlot = new Vector<int[]>();
    
        
        for(int i = 0; i < listofChooses.size(); i++){
            if(listofChooses.get(i).size() > 0){
                char pieceChar = getSlotChar(board, listofChooses.get(i).get(0).get(0)[0], listofChooses.get(i).get(0).get(0)[1]);
                if(player == 1){
                    if(pieceChar == 'a' || pieceChar == 'A'){
                        int[] rowAndColumn = new int[2];

                        rowAndColumn[0] = listofChooses.get(i).get(0).get(0)[0]; rowAndColumn[1] = listofChooses.get(i).get(0).get(0)[1];
                        filtredListPieceSlot.addElement(rowAndColumn);
                    }
                }else if(player == 2){
                    if(pieceChar == 'b' || pieceChar == 'B'){
                        int[] rowAndColumn = new int[2];

                        rowAndColumn[0] = listofChooses.get(i).get(0).get(0)[0]; rowAndColumn[1] = listofChooses.get(i).get(0).get(0)[1];
                        filtredListPieceSlot.addElement(rowAndColumn);
                    }
                }
            }
        }

        return filtredListPieceSlot;
    }


    //get BGColor of the board and make the list of piece that can move yellow
    public char[][] coloringListOfTilesCanMove(char[][] board, int player){
        Vector<int[]> listOfPieceCanMove = getListOfPieceCanMove(board, player);
        char[][] BGColorboard = new DefaultBGColorVBoard().getDefaultBGColorVBoard();

        for(int i = 0; i < listOfPieceCanMove.size(); i++){
            BGColorboard[listOfPieceCanMove.get(i)[0]][listOfPieceCanMove.get(i)[1]] = 'y';
        }

        return BGColorboard;
    }

    public Vector<Vector<int[]>> traceFullPaths(char[][] board, int player, int row, int column){
        Vector<Vector<Vector<int[]>>> listofChooses = getListofChooses(board, player);
        Vector<Vector<int[]>> fullPaths = new Vector<Vector<int[]>>();

        for(int i = 0; i < listofChooses.size(); i++){
            if(listofChooses.get(i).get(0).get(0)[0] == row && listofChooses.get(i).get(0).get(0)[1] == column){
                for(int j = 0; j < listofChooses.get(i).size(); j++){
                    Vector<int[]> newPath = new Vector<int[]>();

                    int carrentRow;
                    int carrentColumn;

                    int[] rowAndColumn = new int[2];
                    rowAndColumn[0] = row; rowAndColumn[1] = column;

                    newPath.addElement(rowAndColumn);

                    for(int k = 1; k < listofChooses.get(i).get(j).size(); k++){
                        int rowDifferent = listofChooses.get(i).get(j).get(k)[0] - listofChooses.get(i).get(j).get(k - 1)[0];
                        int columnDifferent = listofChooses.get(i).get(j).get(k)[1] - listofChooses.get(i).get(j).get(k - 1)[1];

                        carrentRow = listofChooses.get(i).get(j).get(k - 1)[0];
                        carrentColumn = listofChooses.get(i).get(j).get(k - 1)[1];

                        for(int o = 1; o <= Math.sqrt(rowDifferent * rowDifferent); o++){
                            if(rowDifferent >= 1 && columnDifferent >= 1){
                                int[] rowAndColumn1 = new int[2];
                                rowAndColumn1[0] = carrentRow + o; rowAndColumn1[1] = carrentColumn + o;
    
                                newPath.addElement(rowAndColumn1);
                            }else if(rowDifferent >= 1 && columnDifferent <= -1){
                                int[] rowAndColumn1 = new int[2];
                                rowAndColumn1[0] = carrentRow + o; rowAndColumn1[1] = carrentColumn + (o * (-1));
    
                                newPath.addElement(rowAndColumn1);
                            }else if(rowDifferent <= -1 && columnDifferent >= 1){
                                int[] rowAndColumn1 = new int[2];
                                rowAndColumn1[0] = carrentRow + (o * (-1)); rowAndColumn1[1] = carrentColumn + o;
    
                                newPath.addElement(rowAndColumn1);
                            }else if(rowDifferent <= -1 && columnDifferent <= -1){
                                int[] rowAndColumn1 = new int[2];
                                rowAndColumn1[0] = carrentRow + (o * (-1)); rowAndColumn1[1] = carrentColumn + (o * (-1));
    
                                newPath.addElement(rowAndColumn1);
                            }
                        }
                    }
                    fullPaths.addElement(newPath);
                }
                break;
            }
        }

        return fullPaths;
    }

    //get background color board for all available paths for specific piece
    public char[][] coloringFullEattingPaths(char[][] board, int player, int row, int column){
        char[][] BGColorboard = coloringListOfTilesCanMove(board, player);

        Vector<Vector<int[]>> listOfPaths = traceFullPaths(board, player, row, column);

        int j = -1;
        for(int i = listOfPaths.size() - 1; i >= 0; i--){
            for(j = 0; j < listOfPaths.get(i).size() - 1; j++){
                BGColorboard[listOfPaths.get(i).get(j)[0]][listOfPaths.get(i).get(j)[1]] = 'b';
            }

            if(!(j == -1)){
                BGColorboard[listOfPaths.get(i).get(j)[0]][listOfPaths.get(i).get(j)[1]] = 'g';
            }
        }

        return BGColorboard;
    }

    public void movePiecebyRowAndColumnSlowlyWithDisplay(char[][] board, int player, int firstRow, int firstColumn, int lastRow, int lastColumn){
        Vector<Vector<int[]>> fullPaths = traceFullPaths(board, player, firstRow, firstColumn);

        //emit
        System.out.println("move sound");
        
        char[][] newBoard = cloneBoard(board);

        int pathLong;
        for(int i = 0; i < fullPaths.size(); i++){
            pathLong = fullPaths.get(i).size();
            if(fullPaths.get(i).get(0)[0] == firstRow && fullPaths.get(i).get(0)[1] == firstColumn && fullPaths.get(i).get(pathLong - 1)[0] == lastRow && fullPaths.get(i).get(pathLong - 1)[1] == lastColumn){
                int carrentRow = firstRow;
                int carrentColumn = firstColumn;
                boolean eated = false;
                char carrentSlotChar = getSlotChar(newBoard, carrentRow, carrentColumn);
                for(int j = 0; j < fullPaths.get(i).size() - 1; j++){
                    if(carrentSlotChar == 'b'){
                        if(fullPaths.get(i).get(j + 1)[0] == 7){
                            //emit
                            System.out.println("be king sound");
                        }
                    }else if(carrentSlotChar == 'a'){
                        if(fullPaths.get(i).get(j + 1)[0] == 0){
                            //emit
                            System.out.println("be king sound");
                        }
                    }

                    if(eated){ 
                        //emit
                        System.out.println("eat sound");
                    }

                    if(getSlotChar(newBoard, fullPaths.get(i).get(j + 1)[0], fullPaths.get(i).get(j + 1)[1]) != ' '){
                        eated = true;
                    }else{
                        eated = false;
                    }


                    newBoard = movePiecebyRowAndColumn(newBoard, carrentRow, carrentColumn, fullPaths.get(i).get(j + 1)[0], fullPaths.get(i).get(j + 1)[1]);

                    carrentRow = fullPaths.get(i).get(j + 1)[0];
                    carrentColumn = fullPaths.get(i).get(j + 1)[1];
                    carrentSlotChar = getSlotChar(newBoard, carrentRow, carrentColumn);

                    drawBoard(newBoard);
                    //sleep
                    // try{
                    //     TimeUnit.MILLISECONDS.sleep(150);
                    // }catch(InterruptedException e){
                    //     continue;
                    // }
                    
                }
                break;
            }
        }

        this.board = newBoard;
    }

    //computer random choose
    public void computerRandomchoose(char[][] board, int player){
        Vector<Vector<Vector<int[]>>> listofChooses = getListofChooses(board, player);
        if(listofChooses.size() > 0){
            int slot = ((int) (Math.random() * 10)) % listofChooses.size();
            int path = ((int) (Math.random() * 10)) % listofChooses.get(slot).size();
            int pathLength = listofChooses.get(slot).get(path).size();
        
            movePiecebyRowAndColumnSlowlyWithDisplay(board, player, listofChooses.get(slot).get(path).get(0)[0], listofChooses.get(slot).get(path).get(0)[1], listofChooses.get(slot).get(path).get(pathLength - 1)[0], listofChooses.get(slot).get(path).get(pathLength - 1)[1]);
    
            swapCarrentPlayerName(carrentPlayer);
            swapCarrentPlayer(carrentPlayer);
        }
    }

    //

    public void init(String rule, String playerOne, String playerTwo){
        this.rule = rule;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;

        //reintitialase the board
        board = new DefaultVBoard().getDefaultVboad();
        BGColorBoard = new DefaultBGColorVBoard().getDefaultBGColorVBoard();
        drawBoard(board);
        coloringBoard(BGColorBoard);

        //choose randomly who will start playing first
        int turn = ((int) (Math.random() * 10)) % 2;

        if(turn == 0){
            carrentPlayer = 1;
            carrentPlayerName = playerOne;
        }else{
            carrentPlayer = 2;
            carrentPlayerName = playerTwo;
        }

        play();
    }


    public void play(){
        if(carrentPlayerName == "human"){
            coloringBoard(coloringListOfTilesCanMove(board, carrentPlayer));
        }else if(carrentPlayerName == "random"){
            computerRandomchoose(board, carrentPlayer);
            coloringBoard(coloringListOfTilesCanMove(board, carrentPlayer));
        }


        //swapCarrentPlayerName(carrentPlayer);
        //swapCarrentPlayer(carrentPlayer);

        //drawBoard(board);
        //coloringBoard(BGColorBoard);
    }
}