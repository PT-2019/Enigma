package common.entities.special;


import api.utils.Observer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

/**
 * Classe qui gère les music à lancer dans le jeu et les sons
 */
public class GameMusic implements Observer {

    private Music music;

    private float volume;

    /**
     * Son cours
     */
    private Sound sound;

    public GameMusic(String path){
        //on prépare la musique
        this.music = Gdx.audio.newMusic(Gdx.files.internal(path));
        music.setLooping(true);
        volume = 0.5f;
        music.setVolume(volume);
    }

    public Music getMusic() {
        return music;
    }

    public void changeMusic(MusicEditor newMusic){
        music.dispose();
        this.music = Gdx.audio.newMusic(Gdx.files.internal(newMusic.getPath()));
        music.setLooping(true);
        volume = 0.5f;
        music.setVolume(volume);
    }

    /**
     * Jouer le son
     * @param sound
     */
    public void playSound(MusicEditor sound){
        this.sound = Gdx.audio.newSound(Gdx.files.internal(sound.getPath()));
        this.sound.play(volume);
    }

    /**
     * Sert a changer le son
     * @param object un object qui contient les données du changement
     */
    @Override
    public void update(Object object) {
        volume = (float) object;
        music.setVolume(volume);
    }
}
