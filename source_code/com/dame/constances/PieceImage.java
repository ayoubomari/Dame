package com.dame.constances;

import javax.swing.ImageIcon;

public class PieceImage {
    public static ImageIcon EMPTY;
    
    public static ImageIcon NORMALYELLOW;
    public static ImageIcon NORMALBLACK;
    public static ImageIcon KINGYELLOW;
    public static ImageIcon KINGBLACK;

    public PieceImage(){
        EMPTY = new ImageIcon(getClass().getResource("/images/empty.png"));
        NORMALYELLOW =  new ImageIcon(getClass().getResource("/images/normalYellowPiece.png"));
        NORMALBLACK = new ImageIcon(getClass().getResource("/images/normalBlackPiece.png"));
        KINGYELLOW = new ImageIcon(getClass().getResource("/images/kingYellowPiece.png"));
        KINGBLACK = new ImageIcon(getClass().getResource("/images/kingBlackPiece.png"));
    }

    //getters
    public ImageIcon getEMPTY(){
        return EMPTY;
    }
    public ImageIcon getNORMALYELLOW(){
        return NORMALYELLOW;
    }
    public ImageIcon getNORMALBLACK(){
        return NORMALBLACK;
    }
    public ImageIcon getKINGYELLOW(){
        return KINGYELLOW;
    }
    public ImageIcon getKINGBLACK(){
        return KINGBLACK;
    }
}
