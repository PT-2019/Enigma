package data.keys;

import com.badlogic.gdx.Input.Keys;

/**
 * Les controls de la caméra
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0 22/12/2019
 * @since 4.0 22/12/2019
 */
public enum PlayerKeys implements EnigmaKeys {
	PLAYER_LEFT(Keys.LEFT, Keys.Q),
	PLAYER_RIGHT(Keys.RIGHT, Keys.D),
	PLAYER_UP(Keys.UP, Keys.Z),
	PLAYER_DOWN(Keys.DOWN, Keys.S),
	PLAYER_INVENTORY(Keys.I,Keys.SPACE),
	PLAYER_USE(Keys.E, Keys.ENTER);

	private int key1, key2;

	/**
	 * Touches associés au controleur
	 *
	 * @param key1 touche principale
	 * @param key2 touche secondaire
	 */
	PlayerKeys(int key1, int key2) {
		this.key1 = key1;
		this.key2 = key2;
	}

	@Override
	public void set(int key1, int key2) {
		this.key1 = key1;
		this.key2 = key2;
	}

	@Override
	public boolean isKey(int keyCode) {
		return keyCode == key1 || keyCode == key2;
	}

	@Override
	public int getKey1() {
		return key1;
	}

	@Override
	public int getKey2() {
		return key2;
	}
}
