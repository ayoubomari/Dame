package com.Dame.GUI;


import java.awt.event.*;
import javax.swing.*;

import com.Dame.Concepts.Game;

public class Menubar extends JMenuBar implements ActionListener{
	
    private Game game;
    JMenuBar menuBar;
    JMenu NewGameMenu;
    JMenu helpMenu;
    JMenu rulesMenu;
    JMenu EatstrictModeMenu;
    JRadioButtonMenuItem HVHItem;
    JRadioButtonMenuItem HVCEItem;
    JRadioButtonMenuItem HVCMItem;
    JRadioButtonMenuItem HVCHItem;
    JMenuItem exitItem;
    JRadioButtonMenuItem SpainItem;
    JRadioButtonMenuItem USAItem;
    JRadioButtonMenuItem InternationalItem;
    JRadioButtonMenuItem ONItem;
    JRadioButtonMenuItem OFFItem;
    JButton undo;
    ButtonGroup Mode;
    ButtonGroup Rules;
    ButtonGroup Eatstrict;
    
    
    Menubar(Game game){
        this.game = game;

        menuBar = new JMenuBar();
        NewGameMenu = new JMenu("New Game");
        rulesMenu = new JMenu ("Rules");
        EatstrictModeMenu = new JMenu("Eat strict Mode");
        
        undo =new JButton ("Undo");
        Mode = new ButtonGroup();
        
        HVHItem = new JRadioButtonMenuItem ("Human vs Human");     
        HVCEItem = new JRadioButtonMenuItem ("Human vs Computer (easy)");
        HVCMItem = new JRadioButtonMenuItem ("Human vs Computer (medium)");
        HVCHItem = new JRadioButtonMenuItem ("Human vs Computer (hard)");
        HVCHItem.setSelected(true);
        exitItem = new JMenuItem ("Exit");
        Mode.add(HVHItem);
        Mode.add(HVCEItem);
        Mode.add(HVCMItem);
        Mode.add(HVCHItem);
        
        
        Rules = new ButtonGroup();
        
        InternationalItem = new JRadioButtonMenuItem ("International");
        SpainItem = new JRadioButtonMenuItem ("Spain");
        USAItem = new JRadioButtonMenuItem ("USA");
        Rules.add(InternationalItem);
        SpainItem.setSelected(true);
        Rules.add(SpainItem);
        Rules.add(USAItem);

        
        Eatstrict = new ButtonGroup();

        ONItem = new JRadioButtonMenuItem("ON");
        OFFItem = new JRadioButtonMenuItem("OFF");
        
        Eatstrict.add(ONItem);
        ONItem.setSelected(true);
        Eatstrict.add(OFFItem);
        
        undo.addActionListener(this);
        exitItem.addActionListener(this);
        HVHItem.addActionListener(this);
        HVCEItem.addActionListener(this);
        HVCMItem.addActionListener(this);
        HVCHItem.addActionListener(this);
        InternationalItem.addActionListener(this);
        SpainItem.addActionListener(this);
        USAItem.addActionListener(this);
        ONItem.addActionListener(this);
        OFFItem.addActionListener(this);
        
        
        NewGameMenu.add(HVHItem);
        NewGameMenu.add(HVCEItem);
        NewGameMenu.add(HVCMItem);
        NewGameMenu.add(HVCHItem);
        NewGameMenu.add(exitItem);
        
        
        rulesMenu.add(InternationalItem);
        rulesMenu.add(SpainItem);
        rulesMenu.add(USAItem);
        
        
        EatstrictModeMenu.add(ONItem);
        EatstrictModeMenu.add(OFFItem);
        
        
        
        menuBar.add(NewGameMenu);
        menuBar.add(rulesMenu);
        menuBar.add(EatstrictModeMenu);
        menuBar.add(undo);
        
        add(menuBar);
    }
    
    public void actionPerformed(ActionEvent e) {
    	//eatting strict mode
    	if (e.getSource() == ONItem) {
       		game.setEatStrictMode(true);
       	}
    	else if (e.getSource() == OFFItem) {
       		game.setEatStrictMode(false);
       	}
    	
        //rules
    	else if (e.getSource() == InternationalItem) {
       		game.setRule("International");
       	}
        else if (e.getSource() == SpainItem) {
            game.setRule("Spain");
        }
        else if (e.getSource() == USAItem) {
            game.setRule("USA");
        }

        //new Game
    	else if (e.getSource() == HVHItem) {
    		 game.init(game.getRule(), "human", "human", game.getEatStrictMode());
    	}
    	else if (e.getSource() == HVCEItem) {
    		game.init(game.getRule(), "human", "random", game.getEatStrictMode());
    	}
    	else if (e.getSource() == HVCMItem) {
    		game.init(game.getRule(), "human", "AI1",game.getEatStrictMode());
    	}
    	else if (e.getSource() == HVCHItem) {
    		game.init(game.getRule(), "human", "AI2",game.getEatStrictMode());
    	}

        //undo
    	else if (e.getSource() == undo) {
    		game.undoToPreviousBoard();
    	}

        //exit
    	else if(e.getSource() == exitItem) {
    		System.exit(0);
    	}
    }
}