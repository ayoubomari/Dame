package com.Dame.Constances;


public class DefaultBGColorVBoard {
    private char[][] BGColorVBoard = new char[8][8];

    // w == white
    // n == brown

    public DefaultBGColorVBoard (){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if((i + j) % 2 == 0){
                    BGColorVBoard[i][j] = 'w';
                }else{
                    BGColorVBoard[i][j] = 'n';
                }
            }
        }
    }

    //getters
    public char[][] getDefaultBGColorVBoard(){
        return BGColorVBoard;
    }
}