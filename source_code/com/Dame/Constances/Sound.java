package com.Dame.Constances;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound{
    public static void EMIT(String soundName){
        String audioName;
        switch(soundName){
            case "moving": audioName = "moving"; break;
            case "beKing": audioName = "beKing"; break;
            case "eatting": audioName = "eatting"; break;
            case "missToEat": audioName = "missToEat"; break;
            case "win": audioName = "win"; break;
            case "lost": audioName = "lost"; break;
            case "prohibatedMove": audioName = "prohibatedMove"; break;
            default: return;
        }
        
        new Thread() {
            public void run()
            {
                try{
                    AudioInputStream audioStream = AudioSystem.getAudioInputStream(getClass().getResource("/sounds/" + audioName + ".wav"));
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioStream);
                    clip.start();
                }catch(Exception err){
                    System.err.println(err);
                }
            }
        }.start();
    }
}
