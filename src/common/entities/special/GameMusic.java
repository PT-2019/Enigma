package common.entities.special;


import api.utils.Observer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.backends.lwjgl.audio.Mp3;

public class GameMusic implements Observer {

    private Music music;

    public GameMusic(String path){
        this.music = Gdx.audio.newMusic(Gdx.files.internal(path));
        music.setLooping(true);
        music.setVolume(0.5f);
    }

    public Music getMusic() {
        return music;
    }

    @Override
    public void update(Object object) {

    }
}
