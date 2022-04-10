package com.dame.gui;

import java.awt.*;
import javax.swing.*;

import com.dame.concepts.Game;

public class PlayFrame extends JFrame{
    private boolean son = true;
    private Menubar menubar;
    private Board board;
    
    public PlayFrame(Game game){
        //frame title
        super("Dame");

        menubar = new Menubar(game);
        board = new Board(game);

        //-----------------------------------------
        //set icon
        ImageIcon icon = new ImageIcon(getClass().getResource("/images/icon.png"));
        setIconImage(icon.getImage());

        //set components-----------------
        //set menubar
        setJMenuBar(menubar);
        
        //body components---------
        //set the board panel
        add(board);
        //-----------------------------------------



        //display
        setLayout(new GridLayout(1,1));
        setSize(630, 630);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }


    //getters
    public boolean getSon(){
        return son;
    }  
    public Menubar getMenubar(){
        return menubar;
    }
    public Board getBoard(){
        return board;
    }

    //setters
    public void setSon(boolean son){
        this.son = son;
    }
}