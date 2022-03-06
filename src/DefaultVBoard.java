package com.Dame.Constances;


public class DefaultVBoard {
    public char[][] VBoard = new char[8][8];

    public DefaultVBoard (){
        //----------------------------------
        for(int i = 0; i < 8; i++){
            if(i % 2 == 0){
                VBoard[0][i] = ' ';
            }else{
                VBoard[0][i] = 'b';
            }
        }
        for(int i = 0; i < 8; i++){
            if(i % 2 == 0){
                VBoard[1][i] = 'b';
            }else{
                VBoard[1][i] = ' ';
            }
        }
        for(int i = 0; i < 8; i++){
            if(i % 2 == 0){
                VBoard[2][i] = ' ';
            }else{
                VBoard[2][i] = 'b';
            }
        }
        //----------------------------------
        for(int i = 0; i < 8; i++){
            VBoard[3][i] = ' ';
        }
        for(int i = 0; i < 8; i++){
            VBoard[4][i] = ' ';
        }
        //----------------------------------
        for(int i = 0; i < 8; i++){
            if(i % 2 == 0){
                VBoard[5][i] = 'a';
            }else{
                VBoard[5][i] = ' ';
            }
        }
        for(int i = 0; i < 8; i++){
            if(i % 2 == 0){
                VBoard[6][i] = ' ';
            }else{
                VBoard[6][i] = 'a';
            }
        }
        for(int i = 0; i < 8; i++){
            if(i % 2 == 0){
                VBoard[7][i] = 'a';
            }else{
                VBoard[7][i] = ' ';
            }
        }
    }
}
