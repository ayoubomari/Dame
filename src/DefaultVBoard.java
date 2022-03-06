package com.Dame.Constances;


public class DefaultVBoard {
    private char[][] VBoard = new char[8][8];

    public DefaultVBoard (){
        for(int i = 0; i < 8; i++){
            if(i % 2 == 0){
                VBoard[0][i] = ' ';
                VBoard[1][i] = 'b';
                VBoard[2][i] = ' ';

                VBoard[5][i] = 'a';
                VBoard[6][i] = ' ';
                VBoard[7][i] = 'a';
            }else{
                VBoard[0][i] = 'b';
                VBoard[1][i] = ' ';
                VBoard[2][i] = 'b';

                VBoard[5][i] = ' ';
                VBoard[6][i] = 'a';
                VBoard[7][i] = ' ';
            }
            VBoard[3][i] = ' ';
            VBoard[4][i] = ' ';
        }
    }

    //getters
    public char[][] getDefaultVboad(){
        return VBoard;
    }
}
