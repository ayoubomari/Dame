package com.dame.gui;

import java.awt.event.*;
import javax.swing.*;

import com.dame.concepts.Game;
import com.dame.constances.Colors;
import com.dame.constances.PieceImage;
import com.dame.constances.Sound;

public class Tile implements MouseListener {
    private int row;
    private int column;
    private JLabel label = new JLabel();
    private char carrentPieceChar = ' ';
    private char carrentBGColorChar = ' ';
    private PieceImage pieceImage = new PieceImage();
    private Game game;

    public Tile(Game game, int row, int column){
        this.row = row;
        this.column = column;
        this.game = game;

        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);

        label.setIcon(null);
        label.addMouseListener(this);
    }
    
    //translate char to piece in the label of the tile
    public void chatToPiece(char pieceName){
        if(pieceName == ' '){
            carrentPieceChar = ' ';
            label.setIcon(null);
        }else if(pieceName == 'a'){
            carrentPieceChar = 'a';
            label.setIcon(pieceImage.getNORMALYELLOW());
        }else if(pieceName == 'b'){
            carrentPieceChar = 'b';
            label.setIcon(pieceImage.getNORMALBLACK());
        }else if(pieceName == 'A'){
            carrentPieceChar = 'A';
            label.setIcon(pieceImage.getKINGYELLOW());
        }else if(pieceName == 'B'){
            carrentPieceChar = 'B';
            label.setIcon(pieceImage.getKINGBLACK());
        }
    }

    //translate char to background color in the label of the tile
    public void chatToBGColor(char BGColor){
        if(BGColor == 'w'){
            carrentBGColorChar = 'w';
            label.setBackground(Colors.WHITE);
        }else if(BGColor == 'n'){
            carrentBGColorChar = 'n';
            label.setBackground(Colors.BROWN);
        }else if(BGColor == 'b'){
            carrentBGColorChar = 'b';
            label.setBackground(Colors.BLUE);
        }else if(BGColor == 'g'){
            carrentBGColorChar = 'g';
            label.setBackground(Colors.GREEN);
        }else if(BGColor == 'r'){
            carrentBGColorChar = 'r';
            label.setBackground(Colors.RED);
        }else if(BGColor == 'y'){
            carrentBGColorChar = 'y';
            label.setBackground(Colors.YELLOW);
        }
    }

    //getting the index of the tile from 0 to 63
    public int getTileIndex(){
        return ((column * 8) + row);
    }

    //check if the tile is empty (the tile did not contain any piece)
    public boolean isEmpty(){
        if(carrentPieceChar == ' '){
            return true;
        }
        return false;
    }


    //getters
    public JLabel getLabel(){
        return label;
    }
    public char getCarrentPieceChar(){
        return carrentPieceChar;
    }
    public char carrentBGColorChar(){
        return carrentBGColorChar;
    }
    public int getRow(){
        return row;
    }
    public int getColumn(){
        return column;
    }
    public Game getGame(){
        return game;
    }

    @Override
    public void mouseClicked(MouseEvent e){
        if(carrentBGColorChar != 'y' && carrentBGColorChar != 'g'){
            //emit
            if(game.getPlayFrame().getSon()){
                Sound.EMIT("prohibatedMove");
            }


            if(carrentBGColorChar != 'b'){
                //last
                game.coloringBoard(game.coloringListOfTilesCanMove(game.getBoard(), game.getCarrentPlayer()));
                label.setBackground(Colors.RED);
            }
        }
        else if(carrentBGColorChar == 'y'){
            game.coloringBoard(game.coloringFullEattingPaths(game.getBoard(), game.getCarrentPlayer(), row, column));
            game.setCarrentSlotIndex(row, column);
        }
        else if(carrentBGColorChar == 'g'){
            game.coloringBoard(game.getBGColorBoard());
            game.movePiecebyRowAndColumnSlowlyWithDisplay(game.getCarrentPlayer(), game.getCarrentSlotIndex()[0], game.getCarrentSlotIndex()[1], row, column);

            game.swapCarrentPlayerName(game.getCarrentPlayer());
            game.swapCarrentPlayer(game.getCarrentPlayer());


            //add the new board to the history in "allPreviousBoard" vector
            game.addBoardToAllPreviousBoard(game.getBoard());


            if(game.getCarrentPlayerName() == "human"){
                game.coloringBoard(game.coloringListOfTilesCanMove(game.getBoard(), game.getCarrentPlayer()));

                if(game.getListOfPieceCanMove(game.getBoard(), game.getCarrentPlayer()).size() == 0){
                    game.swapCarrentPlayerName(game.getCarrentPlayer());
                    game.swapCarrentPlayer(game.getCarrentPlayer());

                    //if the user confirm then restart the game with the same players and the same tools
                    int opt = JOptionPane.showConfirmDialog(game.getPlayFrame(), "Do you want to play again?", "The Player " + game.getCarrentPlayer() + " win.", JOptionPane.YES_NO_OPTION);
                    if(opt == 0){
                        game.init(game.getRule(), "human", "human", game.getEatStrictMode());
                    }
                    
                    
                    return;
                }
            } else if(game.getCarrentPlayerName() == "random"){
                if(game.getListOfPieceCanMove(game.getBoard(), game.getCarrentPlayer()).size() == 0){
                    //emit
                    if(game.getPlayFrame().getSon()){
                        Sound.EMIT("win");
                    }
                    

                    //if the user confirm then restart the game with the same players and the same tools
                    int opt = JOptionPane.showConfirmDialog(game.getPlayFrame(), "Do you want to play again?", "You win.", JOptionPane.YES_NO_OPTION);
                    if(opt == 0){
                        game.init(game.getRule(), "human", "random", game.getEatStrictMode());
                    }
                    
                    
                    return;
                }

                game.computerRandomchoose(game.getBoard(), game.getCarrentPlayer());
                game.coloringBoard(game.coloringListOfTilesCanMove(game.getBoard(), game.getCarrentPlayer()));

                if(game.getListOfPieceCanMove(game.getBoard(), game.getCarrentPlayer()).size() == 0){
                    //emit
                    if(game.getPlayFrame().getSon()){
                        Sound.EMIT("lost");
                    }
                    

                    //if the user confirm then restart the game with the same players and the same tools
                    int opt = JOptionPane.showConfirmDialog(game.getPlayFrame(), "Do you want to play again?", "You lost.", JOptionPane.YES_NO_OPTION);
                    if(opt == 0){
                        game.init(game.getRule(), "human", "random", game.getEatStrictMode());
                    }
                    
                    
                    return;
                }
            } else if(game.getCarrentPlayerName() == "AI1"){
                if(game.getListOfPieceCanMove(game.getBoard(), game.getCarrentPlayer()).size() == 0){
                    //emit
                    if(game.getPlayFrame().getSon()){
                        Sound.EMIT("win");
                    }
                    

                    //if the user confirm then restart the game with the same players and the same tools
                    int opt = JOptionPane.showConfirmDialog(game.getPlayFrame(), "Do you want to play again?", "You win.", JOptionPane.YES_NO_OPTION);
                    if(opt == 0){
                        game.init(game.getRule(), "human", "AI1", game.getEatStrictMode());
                    }
                    
                    
                    return;
                }

                game.computerAIchoose(game.getBoard(), game.getCarrentPlayer(), 1);
                game.coloringBoard(game.coloringListOfTilesCanMove(game.getBoard(), game.getCarrentPlayer()));

                if(game.getListOfPieceCanMove(game.getBoard(), game.getCarrentPlayer()).size() == 0){
                    //emit
                    if(game.getPlayFrame().getSon()){
                        Sound.EMIT("lost");
                    }
                    

                    //if the user confirm then restart the game with the same players and the same tools
                    int opt = JOptionPane.showConfirmDialog(game.getPlayFrame(), "Do you want to play again?", "You lost.", JOptionPane.YES_NO_OPTION);
                    if(opt == 0){
                        game.init(game.getRule(), "human", "AI1", game.getEatStrictMode());
                    }
                    
                    
                    return;
                }
            } else if(game.getCarrentPlayerName() == "AI2"){
                if(game.getListOfPieceCanMove(game.getBoard(), game.getCarrentPlayer()).size() == 0){
                    //emit
                    if(game.getPlayFrame().getSon()){
                        Sound.EMIT("win");
                    }
                    

                    //if the user confirm then restart the game with the same players and the same tools
                    int opt = JOptionPane.showConfirmDialog(game.getPlayFrame(), "Do you want to play again?", "You win.", JOptionPane.YES_NO_OPTION);
                    if(opt == 0){
                        game.init(game.getRule(), "human", "AI2", game.getEatStrictMode());
                    }
                    
                    
                    return;
                }

                game.computerAIchoose(game.getBoard(), game.getCarrentPlayer(), 5);
                game.coloringBoard(game.coloringListOfTilesCanMove(game.getBoard(), game.getCarrentPlayer()));

                if(game.getListOfPieceCanMove(game.getBoard(), game.getCarrentPlayer()).size() == 0){
                    //emit
                    if(game.getPlayFrame().getSon()){
                        Sound.EMIT("lost");
                    }


                    //if the user confirm then restart the game with the same players and the same tools
                    int opt = JOptionPane.showConfirmDialog(game.getPlayFrame(), "Do you want to play again?", "You lost.", JOptionPane.YES_NO_OPTION);
                    if(opt == 0){
                        game.init(game.getRule(), "human", "AI2", game.getEatStrictMode());
                    }
                    
                    
                    return;
                }
            }
        }
    }
    @Override
    public void mousePressed(MouseEvent e){

    }
    @Override
    public void mouseReleased(MouseEvent e){

    }
    @Override
    public void mouseEntered(MouseEvent e){

    }
    @Override
    public void mouseExited(MouseEvent e){

    }
}