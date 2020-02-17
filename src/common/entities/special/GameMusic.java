package common.entities.special;

import api.utils.Observer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Disposable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * Classe qui gère les music à lancer dans le jeu et les sons
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.1
 * @since 6.0
 */
public class GameMusic implements Observer, Disposable {

	/**
	 * Musique en cours
	 */
	private Music music;

	/**
	 * volume
	 */
	private float volume;

	/**
	 * Sons en cours
	 */
	private ArrayList<Sound> sounds;

	/**
	 * Classe qui gère les music à lancer dans le jeu et les sons
	 * @since 6.1
	 */
	public GameMusic(){
		this.volume = 0.5f;
		this.sounds = new ArrayList<>();
		this.music = null;
	}

	/**
	 * Classe qui gère les music à lancer dans le jeu et les sons
	 *
	 * @param path chemin de la musique ambiante
	 */
	public GameMusic(@NotNull String path) {
		//on prépare la musique
		this.music = Gdx.audio.newMusic(Gdx.files.internal(path));
		this.music.setLooping(true);
		this.volume = 0.5f;
		this.music.setVolume(this.volume);
		this.sounds = new ArrayList<>();
	}

	/**
	 * Change la musique ambiante
	 *
	 * @param newMusic nouvelle musique ambiante
	 * @since 6.0
	 */
	public void changeMusic(MusicEditor newMusic) {
		//libère la musique
		this.music.dispose();
		this.music = Gdx.audio.newMusic(Gdx.files.internal(newMusic.getPath()));
		this.music.setLooping(true);
		this.volume = 0.5f;
		this.music.setVolume(this.volume);
		this.music.play();
	}

	/**
	 * Jouer le son
	 *
	 * @param sound un son
	 * @since 6.0
	 */
	public void playSound(MusicEditor sound) {
		Sound audio = Gdx.audio.newSound(Gdx.files.internal(sound.getPath()));
		audio.play(this.volume);
		//ajoute à la liste
		this.sounds.add(audio);
	}

	/**
	 * Sert a changer le son
	 *
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
	 *
	 * @return la musique ambiante
	 */
	public Music getMusic() {
		return this.music;
	}

	/**
	 * Libère les sons
	 * @since 6.1
	 */
	@Override
	public void dispose() {
		for (Sound s: this.sounds) {
			s.dispose();
		}
	}

	/**
	 * Retourne s'il y a une musique qui peut être jouée
	 * @return true s'il y a une musique qui peut être jouée
	 * @since 6.1
	 */
	public boolean hasMusic() {
		return this.music != null;
	}

	/**
	 * Arrête la lecture des musiques
	 */
	public void stop() {
		if(this.music != null && this.music.isPlaying()) this.music.stop();
		this.dispose();
	}
}
