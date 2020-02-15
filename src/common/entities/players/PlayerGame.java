package common.entities.players;

import api.libgdx.actor.GameActor;
import api.libgdx.actor.GameActorAnimation;
import api.libgdx.utils.InputAdapter;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import common.enigmas.TileEventEnum;
import common.entities.GameObject;
import common.map.AbstractMap;
import common.map.GameMap;
import data.Direction;
import data.keys.CameraKeys;

/**
 * Cette classe permet de déplacer le joueur et d'actionner son animation
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0
 * @see GameActorAnimation
 * @since 5.0
 */
public class PlayerGame extends GameActorAnimation implements InputAdapter {

	/**
	 * Vitesse de déplacement du personnage
	 */
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
	 * Actionné lorsqu'une touche est appuyé
	 *
	 * @param i touche
	 * @return true si événement géré
	 */
	@Override
	public boolean keyDown(int i) {
		//position actuelle
		float x = this.getX();
		float y = this.getY();

		if (CameraKeys.CAMERA_LEFT.isKey(i)) {
			//on vérifie que la case où l'on compte se rendre est disponible

			if (map.isWalkable(x - PlayerGame.SPEED, y, this)
					&& (!map.collision(this, -PlayerGame.SPEED, 0))) {

				this.setPosition(x - PlayerGame.SPEED, y);
				//pour changer de sprite proprement
				if (isAnimationPaused() || this.facedDirection != Direction.LEFT) {
					this.setKeyFrame(3);
				}
				//pour centrer la caméra sur le personnage
				this.getStage().getCamera().translate(-PlayerGame.SPEED, 0, 0);

				this.setFacedDirection(Direction.LEFT);
				//l'animation n'est plus en pause pour la faire tourner
				this.setAnimationPaused(false);
				this.setAnimationLooping(true);
			} else {
				this.setKeyFrame(5);
				this.setFacedDirection(Direction.LEFT);
			}
		} else if (CameraKeys.CAMERA_RIGHT.isKey(i)) {
			if (map.isWalkable(x + PlayerGame.SPEED, y, this) &&
					(!map.collision(this, PlayerGame.SPEED, 0))) {

				this.setPosition(x + PlayerGame.SPEED, y);
				if (isAnimationPaused() || this.facedDirection != Direction.RIGHT) {
					this.setKeyFrame(6);
				}
				this.getStage().getCamera().translate(PlayerGame.SPEED, 0, 0);
				this.setFacedDirection(Direction.RIGHT);
				this.setAnimationPaused(false);
				this.setAnimationLooping(true);
			} else {
				this.setKeyFrame(8);
				this.setFacedDirection(Direction.RIGHT);
			}
		} else if (CameraKeys.CAMERA_UP.isKey(i)) {
			if (map.isWalkable(x, y + PlayerGame.SPEED, this) &&
					(!map.collision(this, 0, +PlayerGame.SPEED))) {

				this.setPosition(x, y + PlayerGame.SPEED);
				if (isAnimationPaused() || this.facedDirection != Direction.TOP) {
					this.setKeyFrame(9);
				}
				this.getStage().getCamera().translate(0, PlayerGame.SPEED, 0);
				this.setFacedDirection(Direction.TOP);
				this.setAnimationPaused(false);
				this.setAnimationLooping(true);
			} else {
				this.setKeyFrame(11);
				this.setFacedDirection(Direction.TOP);
			}
		} else if (CameraKeys.CAMERA_DOWN.isKey(i)) {
			if (map.isWalkable(x, y - PlayerGame.SPEED, this)
					&& (!map.collision(this, 0, -PlayerGame.SPEED))) {
				this.setPosition(x, y - PlayerGame.SPEED);
				if (isAnimationPaused() || this.facedDirection != Direction.BOTTOM) {
					this.setKeyFrame(0);
				}
				this.getStage().getCamera().translate(0, -PlayerGame.SPEED, 0);
				this.setFacedDirection(Direction.BOTTOM);
				this.setAnimationPaused(false);
				this.setAnimationLooping(true);
			} else {
				this.setKeyFrame(2);
				this.setFacedDirection(Direction.BOTTOM);
			}
		}

		//interaction du joueur avec le milieu
		if (Input.Keys.E == i || Input.Keys.ENTER == i) {
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
				System.out.println(entityGame);
			}

			map.doAction(tmpX, tmpY, this, TileEventEnum.ON_USE);
			//on récupère l'objet sur lequelle on a intéragit
			Vector2 position = AbstractMap.posToIndex(tmpX, tmpY, map);
			GameObject object = map.posToEntities((int) position.y, (int) position.x);
			System.out.println(object);
		}
		return false;
	}

	/**
	 * Méthode qui est appelé par le stage associé, permet d'afficher les animations
	 *
	 * @param delta interval de temps depuis dernier appel
	 */
	@Override
	public void act(float delta) {
		super.act(delta);

		if (this.facedDirection == Direction.LEFT) {
			//on controle les sprites affichés
			if (this.getKeyFrameIndex() > 5) {
				this.setKeyFrame(4);
				//on met en pause
				this.setAnimationPaused(true);
				//on met un sprite immobile
				this.setKeyFrame(5);
			}
		}

		if (this.facedDirection == Direction.RIGHT) {
			if (this.getKeyFrameIndex() > 7) {
				this.setKeyFrame(6);
				this.setAnimationPaused(true);
				this.setKeyFrame(8);
			}
		}

		if (this.facedDirection == Direction.BOTTOM) {
			if (this.getKeyFrameIndex() > 1) {
				this.setKeyFrame(0);
				this.setAnimationPaused(true);
				this.setKeyFrame(2);
			}
		}

		if (this.facedDirection == Direction.TOP) {
			if (this.getKeyFrameIndex() > 10) {
				this.setKeyFrame(9);
				this.setAnimationPaused(true);
				this.setKeyFrame(11);
			}
		}
	}

	/**
	 * Retourne le joueur (model)
	 * @return le joueur (model)
	 */
	public Player getPlayer() {
		return this.joueur;
	}
}
