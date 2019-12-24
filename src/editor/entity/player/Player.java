package editor.entity.player;

import api.entity.interfaces.Entity;
import api.entity.interfaces.Living;
import api.enums.Layer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import editor.utils.textures.Texture;

/**
 * Définie un personnage contrôlable : un joueur
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 2.0
 * @see Entity
 * @see api.entity.interfaces.Living
 * @since 2.0
 */
public class Player implements Entity, Living {

	/**
	 * Points de vie maximaux du joueur
	 */
	public final int MAX_PLAYER_PV = 100;
	/**
	 * Point de vie du joueur
	 */
	private int pv;
	/**
	 * Dialogue de l'objet
	 */
	private String dialog;

	/**
	 * Texture de l'objet
	 */
	private Texture texture;

	/**
	 * ID
	 */
	private int id;

	public Player() {
		this.pv = MAX_PLAYER_PV;
	}

	/**
	 * @param id ID
	 */
	public Player(int id) {
		this.pv = MAX_PLAYER_PV;
		this.id = id;
	}

	/**
	 * Est appelé quand un joueur intéragit avec l'objet
	 *
	 * @param p Joueur ayant intéragit avec l'objet
	 */
	@Override
	public void interactsWith(Player p) {
	}

	/**
	 * Obtenir la texture de l'objet
	 *
	 * @return Texture de l'objet, null sinon
	 */
	@Override
	public Texture getTexture() {
		return null;
	}

	@Override
	public void setTexture(Texture t) {
		texture = t;
	}

	/**
	 * Affiche un dialogue avec l'objet
	 */
	@Override
	public void showDialog() {
	}

	/**
	 * Obtenir l'ID
	 *
	 * @return L'ID, -1 si pas initialisé
	 */
	@Override
	public int getID() {
		return this.id;
	}

	/**
	 * Définir l'ID
	 *
	 * @param id ID
	 */
	@Override
	public void setID(int id) {
		this.id = id;
	}

	/**
	 * Obtenir les points de vie de l'entité
	 *
	 * @return Les points de vie
	 */
	@Override
	public int getHP() {
		return this.pv;
	}

	/**
	 * Version texte de l'objet
	 *
	 * @return Texte représentant l'objet
	 */
	@Override
	public String toString() {
		return "[Player  : ID = " + this.id + ", dialog = " + this.dialog + ", pv = " + this.pv + ", texture = " + this.texture + "]";
	}

	@Override
	public float getWidth() {
		return 0;
	}

	@Override
	public float getHeight() {
		return 0;
	}

	@Override
	public void setDimension(int width, int height) {

	}

	@Override
	public Vector2 getPosition() {
		return null;
	}

	@Override
	public void setPosition(Vector2 pos) {

	}

	@Override
	public Array<Float> getTexture(Layer layer) {
		return null;
	}

	@Override
	public void setTexture(Array<Float> texture, Layer layer) {

	}
}