package com.Dame.Concepts;

import java.util.Vector;

import javax.swing.plaf.metal.MetalBorders.PaletteBorder;

import com.Dame.Constances.DefaultVBoard;
import com.Dame.GUI.Board;
import com.Dame.GUI.PlayFrame;

public class Game {
    private PlayFrame playFrame;
    private String rule;
    private char[][] board = new DefaultVBoard().getDefaultVboad();
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


        board = movePiecebyRowAndColumn(board, 2, 1, 4, 3);
        board = removePiecebyRowAndColumn(board, 5, 0);
        // board = removePiecebyRowAndColumn(board, 0, 3);
        // board = movePiecebyRowAndColumn(board, 1, 2, 2, 1);
        // board = removePiecebyRowAndColumn(board, 2, 5);
        // board = movePiecebyRowAndColumn(board, 5, 6, 4, 7);
        // board = removePiecebyRowAndColumn(board, 5, 4);
        // board = removePiecebyRowAndColumn(board, 1, 0);
        // board = movePiecebyRowAndColumn(board, 2, 7, 3, 6);
        // board = removePiecebyRowAndColumn(board, 1, 4);
        // board = removePiecebyRowAndColumn(board, 0, 7);
        board = removePiecebyRowAndColumn(board, 5, 2);
        board = movePiecebyRowAndColumn(board, 2, 3, 1, 4);
        board = movePiecebyRowAndColumn(board, 0, 1, 1, 2);
        //board = removePiecebyRowAndColumn(board, 0, 3);
        board = removePiecebyRowAndColumn(board, 2, 5);
        board = movePiecebyRowAndColumn(board, 5, 6, 2, 5);
        board[5][4] = 'A';
        board[1][6] = 'B';  
        board = removePiecebyRowAndColumn(board, 5, 4);
        board = movePiecebyRowAndColumn(board, 1, 0, 2, 1);
        board[4][3] = 'A'; 
        board[1][2] = 'A';  

        drawBoard(board);


        Vector<Vector<Vector<int[]>>> listofChooses = getListofChooses(board, 2);
        System.out.println("---------- list of paths can choose ----------");
        for(int i = 0; i < listofChooses.size(); i++){
            System.out.println("##############################");
            if(listofChooses.get(i).size() > 0){
                for(int j = 0; j < listofChooses.get(i).size(); j++){
                    System.out.println("---------------------------------");
                    for(int k  = 0; k < listofChooses.get(i).get(j).size(); k++){
                        System.out.println("row: " + listofChooses.get(i).get(j).get(k)[0]);;
                        System.out.println("column: " + listofChooses.get(i).get(j).get(k)[1]);
                    }
                }
            }
        }

        // Vector<int []> listOfPieceCanMove = getListOfPieceCanMove(board, 1);
        // System.out.println("---------- list of piece can move ----------");
        // for(int i = 0; i < listOfPieceCanMove.size(); i++){
        //     System.out.println("##############################");
        //     System.out.println("row: " + listOfPieceCanMove.get(i)[0]);;
        //     System.out.println("column: " + listOfPieceCanMove.get(i)[1]);
        // }
    }


    //draw the board
    public void drawBoard(char [][] board){
        this.playFrame.getBoard().charsToPieces(board);
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
        // if((isEmtySlot(board, carrentRow, carrentColumn)) /*|| !(isEmtySlot(board, newRow, newColumn)) || ((newRow + newColumn) % 2 == 0)*/){
        //     System.err.println("this move is Ilegal because the carrentSlotIndex is empty or the newSlotIndex isn't empty or unavailable.");
        //     return board;
        // }

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
    //get the all list of chooses in the board of a specific player in one specific piece recursively
    public void getListOfChoosesOfOnePieceSpain(char[][] board, int player, Vector<Vector<int[]>> listChooses, char lastCarrentSlot, Vector<int[]> comulPath, int row, int column, boolean isFirstStep, boolean eatting, boolean eated){
        char pieceChar = getSlotChar(board, row, column);
        //Vector<int[]> path = new Vector<int[]>();
        //int [] rowAndColumn = new int[2];
        if(isFirstStep){
            //---------------- corners ----------------//
            if(row == 0 && column == 0){
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
            }
            else if(row == 0 && column == 7){
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
            }
            else if(row == 7 && column == 0){
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
            }
            else if(row == 7 && column == 7){
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
            }

            //---------------- touch lines ----------------//
            else if(row == 0){
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

                //------------------------------------------------

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
            } else if(column == 0){
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

                //-------------------------------------------------

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
            } else if (row == 7){
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

                //----------------------------------------------

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
            } else if(column == 7){
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

                //--------------------------------------------------------

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
            }

            //the hart of the board
            else {
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


                //-----------------------------------------------------

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


                //-----------------------------------------------------------------------

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

                //-----------------------------------------------------------


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
            }
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
                        // if(!(getSlotChar(board, row + 1, column - 1) != ' ')){
                        //     char lastSlotChar = 'a';
                        //     char nextSlotChar;
                        //     for(int i = 1; i < 8; i++){
                        //         nextSlotChar = getSlotChar(board, row + i, column - i);

                        //         if(lastSlotChar != ' ' && nextSlotChar != ' '){
                        //             break;
                        //         }

                        //         if(nextSlotChar == 'b' || nextSlotChar == 'B' || nextSlotChar == '!'){
                        //             break;
                        //         }else if(nextSlotChar == ' '){
                        //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + i, column - i);

                        //             Vector<int[]> newComulPath = new Vector<int[]>();
                        //             newComulPath = clonePath(comulPath);
                        //             //add the carrent piece to the path (from)
                        //             int [] rowAndColumn = new int[2];
                        //             rowAndColumn[0] = row; rowAndColumn[1] = column; 
                        //             newComulPath.addElement(rowAndColumn);

                        //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + 1, column - 1, false, false, true);
                        //         }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
                        //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + i, column - i);

                        //             Vector<int[]> newComulPath = new Vector<int[]>();
                        //             newComulPath = clonePath(comulPath);
                        //             //add the carrent piece to the path (from)
                        //             int [] rowAndColumn = new int[2];
                        //             rowAndColumn[0] = row; rowAndColumn[1] = column; 
                        //             newComulPath.addElement(rowAndColumn);
                                
                        //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + i, column - i, false, true, false);
                        //         }

                        //         lastSlotChar = nextSlotChar;
                        //     }
                        // }
                        // if(!(getSlotChar(board, row - 1, column + 1) != ' ')){
                        //     char lastSlotChar = 'a';
                        //     char nextSlotChar;
                        //     for(int i = 1; i < 8; i++){
                        //         nextSlotChar = getSlotChar(board, row - i, column + i);

                        //         if(lastSlotChar != ' ' && nextSlotChar != ' '){
                        //             break;
                        //         }

                        //         if(nextSlotChar == 'b' || nextSlotChar == 'B' || nextSlotChar == '!'){
                        //             break;
                        //         }else if(nextSlotChar == ' '){
                        //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - i, column + i);

                        //             Vector<int[]> newComulPath = new Vector<int[]>();
                        //             newComulPath = clonePath(comulPath);
                        //             //add the carrent piece to the path (from)
                        //             int [] rowAndColumn = new int[2];
                        //             rowAndColumn[0] = row; rowAndColumn[1] = column; 
                        //             newComulPath.addElement(rowAndColumn);

                        //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - 1, column + 1, false, false, true);
                        //         }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
                        //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - i, column + i);

                        //             Vector<int[]> newComulPath = new Vector<int[]>();
                        //             newComulPath = clonePath(comulPath);
                        //             //add the carrent piece to the path (from)
                        //             int [] rowAndColumn = new int[2];
                        //             rowAndColumn[0] = row; rowAndColumn[1] = column; 
                        //             newComulPath.addElement(rowAndColumn);
                                
                        //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - i, column + i, false, true, false);
                        //         }

                        //         lastSlotChar = nextSlotChar;
                        //     }
                        // }
                    }
                    ////////////
                    else if(rowDifferent >= 1 && columnDifferent <= -1){//the backward is top right
                        // if(!(getSlotChar(board, row + 1, column + 1) != ' ')){
                        //     char lastSlotChar = 'a';
                        //     char nextSlotChar;
                        //     for(int i = 1; i < 8; i++){
                        //         nextSlotChar = getSlotChar(board, row + i, column + i);

                        //         if(lastSlotChar != ' ' && nextSlotChar != ' '){
                        //             break;
                        //         }

                        //         if(nextSlotChar == 'b' || nextSlotChar == 'B' || nextSlotChar == '!'){
                        //             break;
                        //         }else if(nextSlotChar == ' '){
                        //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + i, column + i);

                        //             Vector<int[]> newComulPath = new Vector<int[]>();
                        //             newComulPath = clonePath(comulPath);
                        //             //add the carrent piece to the path (from)
                        //             int [] rowAndColumn = new int[2];
                        //             rowAndColumn[0] = row; rowAndColumn[1] = column; 
                        //             newComulPath.addElement(rowAndColumn);

                        //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + 1, column + 1, false, false, true);
                        //         }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
                        //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + i, column + i);

                        //             Vector<int[]> newComulPath = new Vector<int[]>();
                        //             newComulPath = clonePath(comulPath);
                        //             //add the carrent piece to the path (from)
                        //             int [] rowAndColumn = new int[2];
                        //             rowAndColumn[0] = row; rowAndColumn[1] = column; 
                        //             newComulPath.addElement(rowAndColumn);
                                
                        //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + i, column + i, false, true, false);
                        //         }

                        //         lastSlotChar = nextSlotChar;
                        //     }
                        // }
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
                        // if(!(getSlotChar(board, row - 1, column - 1) != ' ')){
                        //     char lastSlotChar = 'a';
                        //     char nextSlotChar;
                        //     for(int i = 1; i < 8; i++){
                        //         nextSlotChar = getSlotChar(board, row - i, column - i);

                        //         if(lastSlotChar != ' ' && nextSlotChar != ' '){
                        //             break;
                        //         }

                        //         if(nextSlotChar == 'b' || nextSlotChar == 'B' || nextSlotChar == '!'){
                        //             break;
                        //         }else if(nextSlotChar == ' '){
                        //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - i, column - i);

                        //             Vector<int[]> newComulPath = new Vector<int[]>();
                        //             newComulPath = clonePath(comulPath);
                        //             //add the carrent piece to the path (from)
                        //             int [] rowAndColumn = new int[2];
                        //             rowAndColumn[0] = row; rowAndColumn[1] = column; 
                        //             newComulPath.addElement(rowAndColumn);

                        //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - 1, column - 1, false, false, true);
                        //         }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
                        //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - i, column - i);

                        //             Vector<int[]> newComulPath = new Vector<int[]>();
                        //             newComulPath = clonePath(comulPath);
                        //             //add the carrent piece to the path (from)
                        //             int [] rowAndColumn = new int[2];
                        //             rowAndColumn[0] = row; rowAndColumn[1] = column; 
                        //             newComulPath.addElement(rowAndColumn);
                                
                        //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - i, column - i, false, true, false);
                        //         }

                        //         lastSlotChar = nextSlotChar;
                        //     }
                        // }
                    }
                    ////////////
                    else if(rowDifferent <= -1 && columnDifferent >= 1){//the backward is buttom left
                        // if(!(getSlotChar(board, row + 1, column + 1) != ' ')){
                        //     char lastSlotChar = 'a';
                        //     char nextSlotChar;
                        //     for(int i = 1; i < 8; i++){
                        //         nextSlotChar = getSlotChar(board, row + i, column + i);

                        //         if(lastSlotChar != ' ' && nextSlotChar != ' '){
                        //             break;
                        //         }

                        //         if(nextSlotChar == 'b' || nextSlotChar == 'B' || nextSlotChar == '!'){
                        //             break;
                        //         }else if(nextSlotChar == ' '){
                        //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + i, column + i);

                        //             Vector<int[]> newComulPath = new Vector<int[]>();
                        //             newComulPath = clonePath(comulPath);
                        //             //add the carrent piece to the path (from)
                        //             int [] rowAndColumn = new int[2];
                        //             rowAndColumn[0] = row; rowAndColumn[1] = column; 
                        //             newComulPath.addElement(rowAndColumn);

                        //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + 1, column + 1, false, false, true);
                        //         }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
                        //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + i, column + i);

                        //             Vector<int[]> newComulPath = new Vector<int[]>();
                        //             newComulPath = clonePath(comulPath);
                        //             //add the carrent piece to the path (from)
                        //             int [] rowAndColumn = new int[2];
                        //             rowAndColumn[0] = row; rowAndColumn[1] = column; 
                        //             newComulPath.addElement(rowAndColumn);
                                
                        //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + i, column + i, false, true, false);
                        //         }

                        //         lastSlotChar = nextSlotChar;
                        //     }
                        // }
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
                        // if(!(getSlotChar(board, row - 1, column - 1) != ' ')){
                        //     char lastSlotChar = 'a';
                        //     char nextSlotChar;
                        //     for(int i = 1; i < 8; i++){
                        //         nextSlotChar = getSlotChar(board, row - i, column - i);

                        //         if(lastSlotChar != ' ' && nextSlotChar != ' '){
                        //             break;
                        //         }

                        //         if(nextSlotChar == 'b' || nextSlotChar == 'B' || nextSlotChar == '!'){
                        //             break;
                        //         }else if(nextSlotChar == ' '){
                        //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - i, column - i);

                        //             Vector<int[]> newComulPath = new Vector<int[]>();
                        //             newComulPath = clonePath(comulPath);
                        //             //add the carrent piece to the path (from)
                        //             int [] rowAndColumn = new int[2];
                        //             rowAndColumn[0] = row; rowAndColumn[1] = column; 
                        //             newComulPath.addElement(rowAndColumn);

                        //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - 1, column - 1, false, false, true);
                        //         }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
                        //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - i, column - i);

                        //             Vector<int[]> newComulPath = new Vector<int[]>();
                        //             newComulPath = clonePath(comulPath);
                        //             //add the carrent piece to the path (from)
                        //             int [] rowAndColumn = new int[2];
                        //             rowAndColumn[0] = row; rowAndColumn[1] = column; 
                        //             newComulPath.addElement(rowAndColumn);
                                
                        //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - i, column - i, false, true, false);
                        //         }

                        //         lastSlotChar = nextSlotChar;
                        //     }
                        // }
                    }
                    ////////////
                    else if(rowDifferent <= -1 && columnDifferent <= -1){//the backward is buttom right
                        // if(!(getSlotChar(board, row + 1, column - 1) != ' ')){
                        //     char lastSlotChar = 'a';
                        //     char nextSlotChar;
                        //     for(int i = 1; i < 8; i++){
                        //         nextSlotChar = getSlotChar(board, row + i, column - i);

                        //         if(lastSlotChar != ' ' && nextSlotChar != ' '){
                        //             break;
                        //         }

                        //         if(nextSlotChar == 'b' || nextSlotChar == 'B' || nextSlotChar == '!'){
                        //             break;
                        //         }else if(nextSlotChar == ' '){
                        //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + i, column - i);

                        //             Vector<int[]> newComulPath = new Vector<int[]>();
                        //             newComulPath = clonePath(comulPath);
                        //             //add the carrent piece to the path (from)
                        //             int [] rowAndColumn = new int[2];
                        //             rowAndColumn[0] = row; rowAndColumn[1] = column; 
                        //             newComulPath.addElement(rowAndColumn);

                        //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + 1, column - 1, false, false, true);
                        //         }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
                        //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + i, column - i);

                        //             Vector<int[]> newComulPath = new Vector<int[]>();
                        //             newComulPath = clonePath(comulPath);
                        //             //add the carrent piece to the path (from)
                        //             int [] rowAndColumn = new int[2];
                        //             rowAndColumn[0] = row; rowAndColumn[1] = column; 
                        //             newComulPath.addElement(rowAndColumn);
                                
                        //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + i, column - i, false, true, false);
                        //         }

                        //         lastSlotChar = nextSlotChar;
                        //     }
                        // }
                        // if(!(getSlotChar(board, row - 1, column + 1) != ' ')){
                        //     char lastSlotChar = 'a';
                        //     char nextSlotChar;
                        //     for(int i = 1; i < 8; i++){
                        //         nextSlotChar = getSlotChar(board, row - i, column + i);

                        //         if(lastSlotChar != ' ' && nextSlotChar != ' '){
                        //             break;
                        //         }

                        //         if(nextSlotChar == 'b' || nextSlotChar == 'B' || nextSlotChar == '!'){
                        //             break;
                        //         }else if(nextSlotChar == ' '){
                        //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - i, column + i);

                        //             Vector<int[]> newComulPath = new Vector<int[]>();
                        //             newComulPath = clonePath(comulPath);
                        //             //add the carrent piece to the path (from)
                        //             int [] rowAndColumn = new int[2];
                        //             rowAndColumn[0] = row; rowAndColumn[1] = column; 
                        //             newComulPath.addElement(rowAndColumn);

                        //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - 1, column + 1, false, false, true);
                        //         }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
                        //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - i, column + i);

                        //             Vector<int[]> newComulPath = new Vector<int[]>();
                        //             newComulPath = clonePath(comulPath);
                        //             //add the carrent piece to the path (from)
                        //             int [] rowAndColumn = new int[2];
                        //             rowAndColumn[0] = row; rowAndColumn[1] = column; 
                        //             newComulPath.addElement(rowAndColumn);
                                
                        //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - i, column + i, false, true, false);
                        //         }

                        //         lastSlotChar = nextSlotChar;
                        //     }
                        // }
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
                        // if(!(getSlotChar(board, row + 1, column - 1) != ' ')){
                        //     char lastSlotChar = 'a';
                        //     char nextSlotChar;
                        //     for(int i = 1; i < 8; i++){
                        //         nextSlotChar = getSlotChar(board, row + i, column - i);

                        //         if(lastSlotChar != ' ' && nextSlotChar != ' '){
                        //             break;
                        //         }

                        //         if(nextSlotChar == 'a' || nextSlotChar == 'A' || nextSlotChar == '!'){
                        //             break;
                        //         }else if(nextSlotChar == ' '){

                        //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + i, column - i);

                        //             Vector<int[]> newComulPath = new Vector<int[]>();
                        //             newComulPath = clonePath(comulPath);
                        //             //add the carrent piece to the path (from)
                        //             int [] rowAndColumn = new int[2];
                        //             rowAndColumn[0] = row; rowAndColumn[1] = column; 
                        //             newComulPath.addElement(rowAndColumn);

                        //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + 1, column - 1, false, false, true);
                        //         }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
                        //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + i, column - i);

                        //             Vector<int[]> newComulPath = new Vector<int[]>();
                        //             newComulPath = clonePath(comulPath);
                        //             //add the carrent piece to the path (from)
                        //             int [] rowAndColumn = new int[2];
                        //             rowAndColumn[0] = row; rowAndColumn[1] = column; 
                        //             newComulPath.addElement(rowAndColumn);
                                
                        //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + i, column - i, false, true, false);
                        //         }

                        //         lastSlotChar = nextSlotChar;
                        //     }
                        // }
                        // if(!(getSlotChar(board, row - 1, column + 1) != ' ')){
                        //     char lastSlotChar = 'a';
                        //     char nextSlotChar;
                        //     for(int i = 1; i < 8; i++){
                        //         nextSlotChar = getSlotChar(board, row - i, column + i);

                        //         if(lastSlotChar != ' ' && nextSlotChar != ' '){
                        //             break;
                        //         }

                        //         if(nextSlotChar == 'a' || nextSlotChar == 'A' || nextSlotChar == '!'){
                        //             break;
                        //         }else if(nextSlotChar == ' '){
                        //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - i, column + i);

                        //             Vector<int[]> newComulPath = new Vector<int[]>();
                        //             newComulPath = clonePath(comulPath);
                        //             //add the carrent piece to the path (from)
                        //             int [] rowAndColumn = new int[2];
                        //             rowAndColumn[0] = row; rowAndColumn[1] = column; 
                        //             newComulPath.addElement(rowAndColumn);

                        //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - 1, column + 1, false, false, true);
                        //         }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
                        //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - i, column + i);

                        //             Vector<int[]> newComulPath = new Vector<int[]>();
                        //             newComulPath = clonePath(comulPath);
                        //             //add the carrent piece to the path (from)
                        //             int [] rowAndColumn = new int[2];
                        //             rowAndColumn[0] = row; rowAndColumn[1] = column; 
                        //             newComulPath.addElement(rowAndColumn);
                                
                        //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - i, column + i, false, true, false);
                        //         }

                        //         lastSlotChar = nextSlotChar;
                        //     }
                        // }
                    }
                    ////////////
                    else if(rowDifferent >= 1 && columnDifferent <= -1){//the backward is top right
                        // if(!(getSlotChar(board, row + 1, column + 1) != ' ')){
                        //     char lastSlotChar = 'a';
                        //     char nextSlotChar;
                        //     for(int i = 1; i < 8; i++){
                        //         nextSlotChar = getSlotChar(board, row + i, column + i);

                        //         if(lastSlotChar != ' ' && nextSlotChar != ' '){
                        //             break;
                        //         }

                        //         if(nextSlotChar == 'a' || nextSlotChar == 'A' || nextSlotChar == '!'){
                        //             break;
                        //         }else if(nextSlotChar == ' '){
                        //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + i, column + i);

                        //             Vector<int[]> newComulPath = new Vector<int[]>();
                        //             newComulPath = clonePath(comulPath);
                        //             //add the carrent piece to the path (from)
                        //             int [] rowAndColumn = new int[2];
                        //             rowAndColumn[0] = row; rowAndColumn[1] = column; 
                        //             newComulPath.addElement(rowAndColumn);

                        //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + 1, column + 1, false, false, true);
                        //         }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
                        //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + i, column + i);

                        //             Vector<int[]> newComulPath = new Vector<int[]>();
                        //             newComulPath = clonePath(comulPath);
                        //             //add the carrent piece to the path (from)
                        //             int [] rowAndColumn = new int[2];
                        //             rowAndColumn[0] = row; rowAndColumn[1] = column; 
                        //             newComulPath.addElement(rowAndColumn);
                                
                        //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + i, column + i, false, true, false);
                        //         }

                        //         lastSlotChar = nextSlotChar;
                        //     }
                        // }
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
                        // if(!(getSlotChar(board, row - 1, column - 1) != ' ')){
                        //     char lastSlotChar = 'a';
                        //     char nextSlotChar;
                        //     for(int i = 1; i < 8; i++){
                        //         nextSlotChar = getSlotChar(board, row - i, column - i);

                        //         if(lastSlotChar != ' ' && nextSlotChar != ' '){
                        //             break;
                        //         }

                        //         if(nextSlotChar == 'a' || nextSlotChar == 'A' || nextSlotChar == '!'){
                        //             break;
                        //         }else if(nextSlotChar == ' '){
                        //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - i, column - i);

                        //             Vector<int[]> newComulPath = new Vector<int[]>();
                        //             newComulPath = clonePath(comulPath);
                        //             //add the carrent piece to the path (from)
                        //             int [] rowAndColumn = new int[2];
                        //             rowAndColumn[0] = row; rowAndColumn[1] = column; 
                        //             newComulPath.addElement(rowAndColumn);

                        //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - 1, column - 1, false, false, true);
                        //         }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
                        //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - i, column - i);

                        //             Vector<int[]> newComulPath = new Vector<int[]>();
                        //             newComulPath = clonePath(comulPath);
                        //             //add the carrent piece to the path (from)
                        //             int [] rowAndColumn = new int[2];
                        //             rowAndColumn[0] = row; rowAndColumn[1] = column; 
                        //             newComulPath.addElement(rowAndColumn);
                                
                        //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - i, column - i, false, true, false);
                        //         }

                        //         lastSlotChar = nextSlotChar;
                        //     }
                        // }
                    }
                    ////////////
                    else if(rowDifferent <= -1 && columnDifferent >= 1){//the backward is buttom left
                        // if(!(getSlotChar(board, row + 1, column + 1) != ' ')){
                        //     char lastSlotChar = 'a';
                        //     char nextSlotChar;
                        //     for(int i = 1; i < 8; i++){
                        //         nextSlotChar = getSlotChar(board, row + i, column + i);

                        //         if(lastSlotChar != ' ' && nextSlotChar != ' '){
                        //             break;
                        //         }

                        //         if(nextSlotChar == 'a' || nextSlotChar == 'A' || nextSlotChar == '!'){
                        //             break;
                        //         }else if(nextSlotChar == ' '){
                        //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + i, column + i);

                        //             Vector<int[]> newComulPath = new Vector<int[]>();
                        //             newComulPath = clonePath(comulPath);
                        //             //add the carrent piece to the path (from)
                        //             int [] rowAndColumn = new int[2];
                        //             rowAndColumn[0] = row; rowAndColumn[1] = column; 
                        //             newComulPath.addElement(rowAndColumn);

                        //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + 1, column + 1, false, false, true);
                        //         }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
                        //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + i, column + i);

                        //             Vector<int[]> newComulPath = new Vector<int[]>();
                        //             newComulPath = clonePath(comulPath);
                        //             //add the carrent piece to the path (from)
                        //             int [] rowAndColumn = new int[2];
                        //             rowAndColumn[0] = row; rowAndColumn[1] = column; 
                        //             newComulPath.addElement(rowAndColumn);
                                
                        //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + i, column + i, false, true, false);
                        //         }

                        //         lastSlotChar = nextSlotChar;
                        //     }
                        // }
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
                        // if(!(getSlotChar(board, row - 1, column - 1) != ' ')){
                        //     char lastSlotChar = 'a';
                        //     char nextSlotChar;
                        //     for(int i = 1; i < 8; i++){
                        //         nextSlotChar = getSlotChar(board, row - i, column - i);

                        //         if(lastSlotChar != ' ' && nextSlotChar != ' '){
                        //             break;
                        //         }

                        //         if(nextSlotChar == 'a' || nextSlotChar == 'A' || nextSlotChar == '!'){
                        //             break;
                        //         }else if(nextSlotChar == ' '){
                        //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - i, column - i);

                        //             Vector<int[]> newComulPath = new Vector<int[]>();
                        //             newComulPath = clonePath(comulPath);
                        //             //add the carrent piece to the path (from)
                        //             int [] rowAndColumn = new int[2];
                        //             rowAndColumn[0] = row; rowAndColumn[1] = column; 
                        //             newComulPath.addElement(rowAndColumn);

                        //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - 1, column - 1, false, false, true);
                        //         }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
                        //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - i, column - i);

                        //             Vector<int[]> newComulPath = new Vector<int[]>();
                        //             newComulPath = clonePath(comulPath);
                        //             //add the carrent piece to the path (from)
                        //             int [] rowAndColumn = new int[2];
                        //             rowAndColumn[0] = row; rowAndColumn[1] = column; 
                        //             newComulPath.addElement(rowAndColumn);
                                
                        //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - i, column - i, false, true, false);
                        //         }

                        //         lastSlotChar = nextSlotChar;
                        //     }
                        // }
                    }
                    ////////////
                    else if(rowDifferent <= -1 && columnDifferent <= -1){//the backward is buttom right
                        // if(!(getSlotChar(board, row + 1, column - 1) != ' ')){
                        //     char lastSlotChar = 'a';
                        //     char nextSlotChar;
                        //     for(int i = 1; i < 8; i++){
                        //         nextSlotChar = getSlotChar(board, row + i, column - i);

                        //         if(lastSlotChar != ' ' && nextSlotChar != ' '){
                        //             break;
                        //         }

                        //         if(nextSlotChar == 'a' || nextSlotChar == 'A' || nextSlotChar == '!'){
                        //             break;
                        //         }else if(nextSlotChar == ' '){
                        //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + i, column - i);

                        //             Vector<int[]> newComulPath = new Vector<int[]>();
                        //             newComulPath = clonePath(comulPath);
                        //             //add the carrent piece to the path (from)
                        //             int [] rowAndColumn = new int[2];
                        //             rowAndColumn[0] = row; rowAndColumn[1] = column; 
                        //             newComulPath.addElement(rowAndColumn);

                        //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + 1, column - 1, false, false, true);
                        //         }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
                        //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row + i, column - i);

                        //             Vector<int[]> newComulPath = new Vector<int[]>();
                        //             newComulPath = clonePath(comulPath);
                        //             //add the carrent piece to the path (from)
                        //             int [] rowAndColumn = new int[2];
                        //             rowAndColumn[0] = row; rowAndColumn[1] = column; 
                        //             newComulPath.addElement(rowAndColumn);
                                
                        //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row + i, column - i, false, true, false);
                        //         }

                        //         lastSlotChar = nextSlotChar;
                        //     }
                        // }
                        // if(!(getSlotChar(board, row - 1, column + 1) != ' ')){
                        //     char lastSlotChar = 'a';
                        //     char nextSlotChar;
                        //     for(int i = 1; i < 8; i++){
                        //         nextSlotChar = getSlotChar(board, row - i, column + i);

                        //         if(lastSlotChar != ' ' && nextSlotChar != ' '){
                        //             break;
                        //         }

                        //         if(nextSlotChar == 'a' || nextSlotChar == 'A' || nextSlotChar == '!'){
                        //             break;
                        //         }else if(nextSlotChar == ' '){
                        //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - i, column + i);

                        //             Vector<int[]> newComulPath = new Vector<int[]>();
                        //             newComulPath = clonePath(comulPath);
                        //             //add the carrent piece to the path (from)
                        //             int [] rowAndColumn = new int[2];
                        //             rowAndColumn[0] = row; rowAndColumn[1] = column; 
                        //             newComulPath.addElement(rowAndColumn);

                        //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - 1, column + 1, false, false, true);
                        //         }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
                        //             char[][] newBoard = movePiecebyRowAndColumn(board, row, column, row - i, column + i);

                        //             Vector<int[]> newComulPath = new Vector<int[]>();
                        //             newComulPath = clonePath(comulPath);
                        //             //add the carrent piece to the path (from)
                        //             int [] rowAndColumn = new int[2];
                        //             rowAndColumn[0] = row; rowAndColumn[1] = column; 
                        //             newComulPath.addElement(rowAndColumn);
                                
                        //             getListOfChoosesOfOnePieceSpain(newBoard, player, listChooses, getSlotChar(board, row, column), newComulPath, row - i, column + i, false, true, false);
                        //         }

                        //         lastSlotChar = nextSlotChar;
                        //     }
                        // }
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
                //the top and the buttom touch lines without corners
                if(row == 0){
                    if(pieceChar == 'B'){
                        int rowDifferent = row - comulPath.lastElement()[0];
                        int columnDifferent = column - comulPath.lastElement()[1];

                        //the piece can't go backward
                        if(columnDifferent >= 1){//the backward is left
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
                                    lastSlotChar = nextSlotChar; continue;
                                }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
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
                        }else if(columnDifferent <= -1){
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
                                    lastSlotChar = nextSlotChar; continue;
                                }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
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
                        }
                    }else if(pieceChar == 'A'){
                        int rowDifferent = row - comulPath.lastElement()[0];
                        int columnDifferent = column - comulPath.lastElement()[1];

                        //the piece can't go backward
                        if(columnDifferent >= 1){//the backward is left
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
                                    lastSlotChar = nextSlotChar; continue;
                                }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
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
                        }else if(columnDifferent <= -1){
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
                                    lastSlotChar = nextSlotChar; continue;
                                }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
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
                        }
                    }
                }else if(row == 7){
                    if(pieceChar == 'B'){
                        int rowDifferent = row - comulPath.lastElement()[0];
                        int columnDifferent = column - comulPath.lastElement()[1];

                        //the piece can't go backward
                        if(columnDifferent >= 1){//the backward is left
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
                                    lastSlotChar = nextSlotChar; continue;
                                }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
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
                        }else if(columnDifferent <= -1){
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
                                    lastSlotChar = nextSlotChar; continue;
                                }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
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
                    }else if(pieceChar == 'A'){
                        int rowDifferent = row - comulPath.lastElement()[0];
                        int columnDifferent = column - comulPath.lastElement()[1];

                        //the piece can't go backward
                        if(columnDifferent >= 1){//the backward is left
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
                                    lastSlotChar = nextSlotChar; continue;
                                }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
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
                        }else if(columnDifferent <= -1){
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
                                    lastSlotChar = nextSlotChar; continue;
                                }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
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
                }else {
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
                            {
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
                                        lastSlotChar = nextSlotChar; continue;
                                    }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
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
                            }
                            {
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
                                        lastSlotChar = nextSlotChar; continue;
                                    }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
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
                            }
                            {
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
                                        lastSlotChar = nextSlotChar; continue;
                                    }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
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
                            }
                        } 
                        else if(rowDifferent >= 1 && columnDifferent <= -1){//the backward is top left
                            {
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
                                        lastSlotChar = nextSlotChar; continue;
                                    }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
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
                            }
                            {
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
                                        lastSlotChar = nextSlotChar; continue;
                                    }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
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
                            }
                            {
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
                                        lastSlotChar = nextSlotChar; continue;
                                    }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
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
                        else if(rowDifferent <= -1 && columnDifferent >= 1){//the backward is buttom right
                            {
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
                                        lastSlotChar = nextSlotChar; continue;
                                    }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
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
                            }
                            {
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
                                        lastSlotChar = nextSlotChar; continue;
                                    }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
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
                            }
                            {
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
                                        lastSlotChar = nextSlotChar; continue;
                                    }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
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
                        else if(rowDifferent <= -1 && columnDifferent <= -1){//the backward is buttom left
                            {
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
                                        lastSlotChar = nextSlotChar; continue;
                                    }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
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
                            }
                            {
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
                                        lastSlotChar = nextSlotChar; continue;
                                    }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
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
                            }
                            {
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
                                        lastSlotChar = nextSlotChar; continue;
                                    }else if(nextSlotChar == 'a' || nextSlotChar == 'A'){
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
                    }else if(pieceChar == 'A'){
                        int rowDifferent = row - comulPath.lastElement()[0];
                        int columnDifferent = column - comulPath.lastElement()[1];

                        //the piece can't go backward
                        if(rowDifferent >= 1 && columnDifferent >= 1){//the backward is top right
                            {
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
                                        lastSlotChar = nextSlotChar; continue;
                                    }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
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
                            }
                            {
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
                                        lastSlotChar = nextSlotChar; continue;
                                    }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
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
                            }
                            {
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
                                        lastSlotChar = nextSlotChar; continue;
                                    }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
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
                            }
                        } 
                        else if(rowDifferent >= 1 && columnDifferent <= -1){//the backward is top left
                            {
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
                                        lastSlotChar = nextSlotChar; continue;
                                    }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
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
                            }
                            {
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
                                        lastSlotChar = nextSlotChar; continue;
                                    }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
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
                            }
                            {
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
                                        lastSlotChar = nextSlotChar; continue;
                                    }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
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
                        else if(rowDifferent <= -1 && columnDifferent >= 1){//the backward is buttom left
                            {
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
                                        lastSlotChar = nextSlotChar; continue;
                                    }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
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
                            }
                            {
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
                                        lastSlotChar = nextSlotChar; continue;
                                    }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
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
                            }
                            {
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
                                        lastSlotChar = nextSlotChar; continue;
                                    }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
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
                        else if(rowDifferent <= -1 && columnDifferent <= -1){//the backward is buttom right
                            {
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
                                        lastSlotChar = nextSlotChar; continue;
                                    }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
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
                            }
                            {
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
                                        lastSlotChar = nextSlotChar; continue;
                                    }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
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
                            }
                            {
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
                                        lastSlotChar = nextSlotChar; continue;
                                    }else if(nextSlotChar == 'b' || nextSlotChar == 'B'){
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
                    }
                }
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
}