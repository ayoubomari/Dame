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
    private int carrentPieceIndex = -1;
    private Vector<int[]> listChooses; 
    
    public Game(PlayFrame playFrame, String playerOne, String PlayerTwo){
        this.playFrame = playFrame;
        this.playerOne = playerOne;
        this.PlayerTwo = PlayerTwo;
    }
}
