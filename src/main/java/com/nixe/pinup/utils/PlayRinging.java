package com.nixe.pinup.utils;

import com.nixe.pinup.view.home.Home;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.BufferedInputStream;
import java.io.InputStream;

public class PlayRinging {
    private String song = Home.toque;

    public void audio() throws Exception {

        InputStream audio = getClass().getResourceAsStream(
                String.format("/audio/%s.wav",song)
        );

        AudioInputStream audioIn = AudioSystem.getAudioInputStream(
                new BufferedInputStream(audio)
        );

        Clip clip = AudioSystem.getClip();
        clip.open(audioIn);
        clip.start();
    }
}
