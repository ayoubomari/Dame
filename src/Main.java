package com.Dame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.Dame.Concepts.Game;
import com.Dame.GUI.PlayFrame;

public class Main{
    public static void main(String agrs[]){
        PlayFrame playframe = new PlayFrame();
        Game game = new Game(playframe, "humain", "humain");
    }
}