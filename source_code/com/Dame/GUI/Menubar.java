package com.Dame.GUI;


import java.awt.event.*;
import javax.swing.*;

import com.Dame.Concepts.Game;

public class Menubar extends JMenuBar implements ActionListener{
    private Game game;
    
    private JMenuBar menuBar;
    private JMenu NewGameMenu;
    private JMenu rulesMenu;
    private JMenu EatstrictModeMenu;
    private JMenu SoundMenu;
    private JRadioButtonMenuItem humanVsHuman;
    private JRadioButtonMenuItem easyLavel;
    private JRadioButtonMenuItem mediumLavel;
    private JRadioButtonMenuItem hardLavel;
    private JMenuItem exitItem;
    private JRadioButtonMenuItem SpainItem;
    private JRadioButtonMenuItem USAItem;
    private JRadioButtonMenuItem InternationalItem;
    private JRadioButtonMenuItem ONItem;
    private JRadioButtonMenuItem OFFItem;
    private JRadioButtonMenuItem ONSoundItem;
    private JRadioButtonMenuItem OFFSoundItem;
    private JButton undo;
    private ButtonGroup lavel;
    private ButtonGroup Rules;
    private ButtonGroup Eatstrict;
    private ButtonGroup Sound;
    
    
    Menubar(Game game){
        this.game = game;
        
        menuBar = new JMenuBar();
        NewGameMenu = new JMenu("New Game");
        rulesMenu = new JMenu ("Rules");
        EatstrictModeMenu = new JMenu("Eat strict Mode");
        SoundMenu = new JMenu ("Sound");
        undo =new JButton ("Undo");
        
        
        
        lavel = new ButtonGroup();
        
        humanVsHuman = new JRadioButtonMenuItem ("Human vs Human");     
        easyLavel = new JRadioButtonMenuItem ("Human vs Computer (easy)");
        mediumLavel = new JRadioButtonMenuItem ("Human vs Computer (medium)");
        hardLavel = new JRadioButtonMenuItem ("Human vs Computer (hard)");
        hardLavel.setSelected(true);
        exitItem = new JMenuItem ("Exit");
        lavel.add(humanVsHuman);
        lavel.add(easyLavel);
        lavel.add(mediumLavel);
        lavel.add(hardLavel);
        
        
        
        Rules = new ButtonGroup();
        
        InternationalItem = new JRadioButtonMenuItem ("International");
        SpainItem = new JRadioButtonMenuItem ("Spain");
        USAItem = new JRadioButtonMenuItem ("USA");
        Rules.add(InternationalItem);
        SpainItem.setSelected(true);
        Rules.add(SpainItem);
        Rules.add(USAItem);
        
        
        
        
        Sound = new ButtonGroup();
        
        ONSoundItem = new JRadioButtonMenuItem ("ON 🔊");
        OFFSoundItem = new JRadioButtonMenuItem ("OFF 🔇");
        
        Sound.add(ONSoundItem);
        ONSoundItem.setSelected(true);
        Sound.add(OFFSoundItem);
        
        
        
        Eatstrict = new ButtonGroup();

        ONItem = new JRadioButtonMenuItem("ON");
        OFFItem = new JRadioButtonMenuItem("OFF");
        
        Eatstrict.add(ONItem);
        ONItem.setSelected(true);
        Eatstrict.add(OFFItem);
        
        
        undo.addActionListener(this);
        exitItem.addActionListener(this);
        humanVsHuman.addActionListener(this);
        easyLavel.addActionListener(this);
        mediumLavel.addActionListener(this);
        hardLavel.addActionListener(this);
        InternationalItem.addActionListener(this);
        SpainItem.addActionListener(this);
        USAItem.addActionListener(this);
        ONItem.addActionListener(this);
        OFFItem.addActionListener(this);
        ONSoundItem.addActionListener(this);
        OFFSoundItem.addActionListener(this);
        
        
        NewGameMenu.add(humanVsHuman);
        NewGameMenu.add(easyLavel);
        NewGameMenu.add(mediumLavel);
        NewGameMenu.add(hardLavel);
        NewGameMenu.add(exitItem);
        
        
        rulesMenu.add(InternationalItem);
        rulesMenu.add(SpainItem);
        rulesMenu.add(USAItem);
        
        
        EatstrictModeMenu.add(ONItem);
        EatstrictModeMenu.add(OFFItem);
        
        SoundMenu.add(ONSoundItem);
        SoundMenu.add(OFFSoundItem);
        
        menuBar.add(NewGameMenu);
        menuBar.add(rulesMenu);
        menuBar.add(EatstrictModeMenu);
        menuBar.add(SoundMenu);
        menuBar.add(undo);
        add(menuBar);
    }

    
    public void actionPerformed(ActionEvent e) {
        //new game
        if (e.getSource() == humanVsHuman) {
    		game.init(game.getRule(), "human", "human", game.getEatStrictMode());
    	}
    	else if (e.getSource()== easyLavel) {
    		game.init(game.getRule(), "human", "random", game.getEatStrictMode());
    	}
    	else if (e.getSource()== mediumLavel) {
    		game.init(game.getRule(), "human", "AI1",game.getEatStrictMode());
    	}
    	else if (e.getSource()== hardLavel) {
    		game.init(game.getRule(), "human", "AI2",game.getEatStrictMode());
    	}

        //rule
    	else if (e.getSource()== InternationalItem) {
       		game.setRule("International");
       	}
        else if (e.getSource()== SpainItem) {
            game.setRule("Spain");
        }
        else if (e.getSource()== USAItem) {
            game.setRule("USA");
        }
        
        //eatting strinct mode
        else if (e.getSource()== ONItem) {
       		game.setEatStrictMode(true);
       	}
    	else if (e.getSource()== OFFItem) {
       		game.setEatStrictMode(false);
       	}

        //son
        else if (e.getSource()== ONSoundItem) {
    		game.getPlayFrame().setSon(true);
    	}
    	else if (e.getSource()== OFFSoundItem) {
    		game.getPlayFrame().setSon(false);
    	}

        //undo
    	else if (e.getSource()== undo) {
    		game.undoToPreviousBoard();
    		return;
    	}

        //exit
    	else if (e.getSource() == exitItem) {
    		int opt = JOptionPane.showConfirmDialog(game.getPlayFrame(), "Do you really want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
            if(opt == 0){
                System.exit(0);
            }
    	}
    }
}