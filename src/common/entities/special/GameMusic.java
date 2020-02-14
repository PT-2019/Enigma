package common.entities.special;

import api.utils.Observer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import org.jetbrains.annotations.NotNull;

/**
 * Classe qui gère les music à lancer dans le jeu et les sons
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 6.0
 * @since 6.0
 */
public class GameMusic implements Observer {

    /**
     * Musique en cours
     */
    private Music music;

    /**
     * volume
     */
    private float volume;

    /**
     * Son en cours
     */
    private Sound sound;

    /**
     * Classe qui gère les music à lancer dans le jeu et les sons
     * @param path chemin de la musique ambiante
     */
    public GameMusic(@NotNull String path){
        //on prépare la musique
        this.music = Gdx.audio.newMusic(Gdx.files.internal(path));
        this.music.setLooping(true);
        this.volume = 0.5f;
        this.music.setVolume(this.volume);
        this.sound = null;
    }

    /**
     * Change la musique ambiante
     * @param newMusic nouvelle musique ambiante
     * @since 6.0
     */
    public void changeMusic(MusicEditor newMusic){
        //libère la musique
        this.music.dispose();
        this.music = Gdx.audio.newMusic(Gdx.files.internal(newMusic.getPath()));
        this.music.setLooping(true);
        this.volume = 0.5f;
        this.music.setVolume(this.volume);
    }

    /**
     * Jouer le son
     * @param sound un son
     * @since 6.0
     */
    public void playSound(MusicEditor sound){
        //libère le son ?
        if(this.sound != null) this.sound.dispose();
        this.sound = Gdx.audio.newSound(Gdx.files.internal(sound.getPath()));
        this.sound.play(this.volume);
    }

    /**
     * Sert a changer le son
     * @param object un object qui contient les données du changement
     * @since 6.0
     */
    @Override
    public void update(Object object) {
        this.volume = (float) object;
        this.music.setVolume(this.volume);
    }

    /**
     * Retourne la musique ambiante
     * @return la musique ambiante
     */
    public Music getMusic() {
        return this.music;
    }
}
