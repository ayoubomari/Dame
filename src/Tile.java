package com.Dame.GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Tile{
    public JLabel label = new JLabel();
    public int x,y;

    public Tile(int x, int y){
        this.x = x;
        this.y = y;
    }
}