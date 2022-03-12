package com.Dame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.Dame.Concepts.Game;
import com.Dame.GUI.PlayFrame;

public class Main{
    public static void main(String agrs[]){
        //make new game object
        Game game = new Game();
        
        //make new playFrame
        PlayFrame playframe = new PlayFrame(game);
        //add the playeFrame to the game object
        game.setPlayFrame(playframe);

        //star playing the game
        //game.init("Spain", "human", "human");
        game.init("Spain", "human", "random");
    }
}