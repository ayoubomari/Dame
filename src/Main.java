package com.Dame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.Dame.Concepts.Game;
import com.Dame.GUI.PlayFrame;

public class Main{
    public static void main(String agrs[]){
        Game game;
        PlayFrame playframe = new PlayFrame(game);
        game = new Game(playframe, "Spain","humain", "humain");
    }
}