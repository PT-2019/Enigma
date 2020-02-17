package common.entities.players;

import api.libgdx.actor.GameActor;
import api.libgdx.actor.GameActorAnimation;
import api.libgdx.utils.InputAdapter;
import com.badlogic.gdx.graphics.Camera;
import common.enigmas.TileEventEnum;
import common.map.GameMap;
import common.utils.Logger;
import data.Direction;
import data.keys.PlayerKeys;

/**
 * Cette classe permet de déplacer le joueur et d'actionner son animation
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.1
 * @see GameActorAnimation
 * @since 5.0
 */
public class PlayerGame extends GameActorAnimation implements InputAdapter {

	/**
	 * Vitesse de déplacement du personnage
	 */
	@SuppressWarnings("WeakerAccess")
	public static final int SPEED = 7;

	/**
	 * La map dans la laquelle est placé le joueur
	 */
	private GameMap map;
	/**
	 * Joueur
	 */
	private Player joueur;

	/**
	 * Cette classe permet de déplacer le joueur et d'actionner son animation
	 *
	 * @param map La map dans la laquelle est placé le joueur
	 */
	public PlayerGame(GameMap map) {
		this.setAnimationPaused(true);
		this.map = map;
		this.joueur=new Player();
	}

	/**
	 * Méthode pour centrer la caméra du stage qui a le player sur le personnage
	 */
	public void center() {
		Camera cam = this.getStage().getCamera();
		//pour éviter un bug graphique on crée 2 variables
		int posy = (int) (cam.position.y);
		int posx = (int) (cam.position.x);
		cam.translate(-posx + this.getX(), -posy + this.getY(), 0);
	}

	/**
	 * Met à jour les informations du joueur
	 * @param offX déplacement x
	 * @param offY déplacement y
	 * @param keyF start key frame
	 * @param resetKey base key frame
	 * @param d direction du déplacement
	 * @since 6.1
	 */
	private void upPlayer(float offX, float offY, int keyF, int resetKey, Direction d){
		//position actuelle
		float x = this.getX();
		float y = this.getY();

		//on vérifie que la case où l'on compte se rendre est disponible
		if (this.map.isWalkable(x + offX, y + offY) && (!this.map.collision(this, offX, offY))) {
			this.map.doAction(x,y, this, TileEventEnum.ON_EXIT);//quitte
			this.setPosition(x + offX, y + offY);
			this.map.doAction(x+offX, y+offY, this, TileEventEnum.ON_ENTER); //entre
			//pour changer de sprite proprement
			if (isAnimationPaused() || this.facedDirection != d) {
				this.setKeyFrame(keyF);
			}
			//pour centrer la caméra sur le personnage
			this.getStage().getCamera().translate(offX, offY, 0);

			this.setFacedDirection(d);
			//l'animation n'est plus en pause pour la faire tourner
			this.setAnimationPaused(false);
			this.setAnimationLooping(true);
		} else {
			this.setKeyFrame(resetKey);
			this.setFacedDirection(d);
		}
	}

	/**
	 * Actionné lorsqu'une touche est appuyé
	 *
	 * @param i touche
	 * @return true si événement géré
	 */
	@Override
	public boolean keyDown(int i) {
		if(!map.getEnigmaDialog().isVisible()){
			//TODO: interdit de faire input et update en même temps !
			if (PlayerKeys.PLAYER_LEFT.isKey(i)) {
				this.upPlayer(-PlayerGame.SPEED, 0, 3, 5, Direction.LEFT);
				return true;
			} else if (PlayerKeys.PLAYER_RIGHT.isKey(i)) {
				this.upPlayer(PlayerGame.SPEED, 0, 6, 8, Direction.RIGHT);
				return true;
			} else if (PlayerKeys.PLAYER_UP.isKey(i)) {
				this.upPlayer(0, PlayerGame.SPEED, 9, 11, Direction.TOP);
				return true;
			} else if (PlayerKeys.PLAYER_DOWN.isKey(i)) {
				this.upPlayer(0, -PlayerGame.SPEED, 0, 2, Direction.BOTTOM);
				return true;
			}

			//interaction du joueur avec le milieu
			if (PlayerKeys.PLAYER_USE.isKey(i)) {
				float tmpX, tmpY;

				if (facedDirection == Direction.BOTTOM) {
					tmpX = this.getX();
					tmpY = this.getY() - PlayerGame.SPEED;
				} else if (facedDirection == Direction.TOP) {

					tmpX = this.getX();
					tmpY = this.getY() + PlayerGame.SPEED;
				} else if (facedDirection == Direction.RIGHT) {
					tmpX = this.getX() + PlayerGame.SPEED;
					tmpY = this.getY();
				} else {
					tmpX = this.getX() - PlayerGame.SPEED;
					tmpY = this.getY();
				}

				GameActor entityGame = map.collisionEntityGame(this, tmpX - this.getX(), tmpY - this.getY());
				if (entityGame != null) {
					Logger.printDebugALL("PlayerGame", entityGame.toString());
				}

				//doAction
				this.map.doAction(tmpX, tmpY, this, TileEventEnum.ON_USE);

				return true;
			}
		}
		return false;
	}

	/**
	 * Change le sprite
	 * @param min minimum pour effectuer le changement
	 * @param start sprite de départ
	 * @param idle sprite de base
	 */
	private void setSprite(int min, int start, int idle){
		//on contrôle les sprites affichés
		if (this.getKeyFrameIndex() > min) {
			this.setKeyFrame(start);
			//on met en pause
			this.setAnimationPaused(true);
			//on met un sprite immobile
			this.setKeyFrame(idle);
		}
	}

	/**
	 * Méthode qui est appelé par le stage associé, permet d'afficher les animations
	 *
	 * @param delta interval de temps depuis dernier appel
	 */
	@Override
	public void act(float delta) {
		super.act(delta);

		if (this.facedDirection == Direction.LEFT)
			setSprite(5,4,5);//6 ?

		if (this.facedDirection == Direction.RIGHT)
			setSprite(7,6,8);

		if (this.facedDirection == Direction.BOTTOM)
			setSprite(1,0,2);

		if (this.facedDirection == Direction.TOP)
			setSprite(10,9,11);
	}

	/**
	 * Retourne le joueur (model)
	 * @return le joueur (model)
	 */
	public Player getPlayer() {
		return this.joueur;
	}
}
