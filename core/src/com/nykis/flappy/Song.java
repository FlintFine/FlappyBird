package com.nykis.flappy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class Song {
    private Sound sound;
    private Sound soundColisao;
    private Music music;
    private float volume = 0.9f;
    private boolean looping = true;

    private boolean somDeColisaoTocado=false;
    private String soundPath = new String("movimento.ogg") ;
    private String soundColisaoPath= new String("colisao.wav");
    private String musicPath= new String("musica.wav");

    //"movimento.ogg", "colisao.wav", "musica.wav"
    //private Texture fundo = new Texture("fundo.png");


    public Song() {
        sound = Gdx.audio.newSound(Gdx.files.internal(soundPath));
        soundColisao = Gdx.audio.newSound(Gdx.files.internal(soundColisaoPath));
        music = Gdx.audio.newMusic(Gdx.files.internal(musicPath));
        music.setVolume(volume);
        music.setLooping(looping);

    }

    public void playMusic() {
        music.play();
    }

    public void playSound() {
        sound.play();
    }

    public void playSoundColisao() {
        soundColisao.play();
    }

    public void stopMusic() {
        music.stop();
    }

    public Sound getSound() {
        return sound;
    }

    public Sound getSoundColisao() {
        return soundColisao;
    }

    public Music getMusic() {
        return music;
    }

    public boolean isSomDeColisaoTocado() {
        return somDeColisaoTocado;
    }

    public void setSomDeColisaoTocado(boolean somDeColisaoTocado) {
        this.somDeColisaoTocado = somDeColisaoTocado;
    }

    public void dispose() {
        sound.dispose();
        soundColisao.dispose();
        music.dispose();
    }
}
