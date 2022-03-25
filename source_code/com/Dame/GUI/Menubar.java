package com.Dame.GUI;

import javax.swing.*;

import com.Dame.Concepts.Game;

public class Menubar extends JMenuBar{
    private Game game;
    Menubar(Game game){
        this.game = game;

        JMenu newGame = new JMenu("new Game");
        add(newGame);
    }

    public Game getGame(){
        return game;
    }
}