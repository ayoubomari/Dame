package com.dame;


import com.dame.concepts.Game;
import com.dame.gui.PlayFrame;


public class Main{
    public static void main(String agrs[]){
        //make new game object
        Game game = new Game();
        
        //make new playFrame
        PlayFrame playframe = new PlayFrame(game);
        //add the playeFrame to the game object
        game.setPlayFrame(playframe);

        //star playing the game
        game.init("Spain", "human", "AI2", true);
    }
}