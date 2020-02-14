package common.utils;

import api.utils.Utility;
import data.config.Config;

import java.util.ArrayList;

/**
 * Des méthodes utiles pour Enigma
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0
 * @since 6.0
 */
public final class EnigmaUtility {

	/**
	 * Obtenir le nom de toutes les maps en local
	 * Les noms sont issus des fichiers tmx présents
	 *
	 * @return Le nom des maps
	 * @since 6.0
	 */
	public static ArrayList<String> getAllMapNames() {
		return Utility.getAllFiles(Config.MAP_FOLDER, new String[]{Config.MAP_EXTENSION}, true);
	}

	/**
	 * Obtenir le nom de toutes les parties en local
	 * Les noms sont issus des fichiers tmx présents
	 *
	 * @return Le nom des parties
	 * @since 6.0
	 */
	public static ArrayList<String> getAllGameName() {
		return Utility.getAllFiles(Config.GAME_DATA_FOLDER, new String[]{Config.DATA_EXTENSION}, true);
	}

	/**
	 * Obtenir le nom de toutes les musiques en local
	 * Les noms sont issus des fichiers tmx présents
	 *
	 * @return Le nom des maps
	 * @since 6.0
	 */
	public static ArrayList<String> getAllMusicName() {
		return Utility.getAllFiles(Config.MUSIC_FOLDER, Config.MUSIC_EXTENSIONS, true);
	}

	/**
	 * Obtenir le nom de toutes les maps en local
	 * Les noms sont issus des fichiers tmx présents
	 *
	 * @return Le nom des maps
	 * @since 6.0
	 */
	public static ArrayList<String> getAllSoundName() {
		return Utility.getAllFiles(Config.SOUND_FOLDER, Config.SOUND_EXTENSIONS, true);
	}


}
