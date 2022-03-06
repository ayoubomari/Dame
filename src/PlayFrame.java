package com.Dame.GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.Dame.GUI.Board;
import com.Dame.GUI.Menubar;

public class PlayFrame extends JFrame{
    private boolean son = true;
    private Menubar menubar = new Menubar();
    private Board b = new Board();
    
    public PlayFrame(){
        //frame title
        super("Dame");

        //-----------------------------------------
        //set icon
        ImageIcon icon = new ImageIcon("images/icon.png");
        setIconImage(icon.getImage());

        //set components
        setJMenuBar(menubar);
        add(b);
        //-----------------------------------------



        //display
        setLayout(new GridLayout(1,1));
        //setSize();
        setBounds(100, 100, 630, 630);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
