package common.map;

import api.libgdx.actor.GameActor;
import com.badlogic.gdx.graphics.g2d.Batch;
import common.entities.players.PlayerGame;
import data.Layer;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * Map de la libgdx
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 1.0.0
 * @since 1.0 28 janvier 2019
 */
public class GameMap extends AbstractMap {

	/**
	 * Tableau des entités
	 */
	private ArrayList<GameActor> entities;

	public GameMap(final String path, float unitScale) {
		super(path, unitScale);

		this.entities = new ArrayList<>();
	}

	public boolean isWalkable(float posX, float posY, GameActor actor) {
		//ICI CODER, gameActor est pas obligé, la classe peut être changé (GameActorAnimation..., PlayerGame)...
		//throw new UnsupportedOperationException("non codé");
		return true;
	}

	//boolean doAction(float posX, float posY, GameActor actor, ???  action)

	@SuppressWarnings("LibGDXFlushInsideLoop")
	@Override
	public void draw(Batch batch, float parentAlpha) {
		//Setup camera
		this.map.setView(this.camera);

		//dessin de la map
		for (Layer layer : Layer.values()) {
			if (layer == Layer.COLLISION) {
				continue;
			}
			for (GameActor entite : this.entities) {
				if (entite.getLayer() == layer) {
					batch.end();
					batch.begin();
					entite.draw(batch, parentAlpha);
					batch.end();
					batch.begin();
				}
			}
			//render map
			this.map.render(new int[]{layer.ordinal()});
		}

		//render borders
		if (this.showGrid)
			this.border.draw();

	}

	/**
	 * Ajoute une entité à l'array d'entités
	 *
	 * @param actor entité à ajouter
	 */
	public void addEntity(@NotNull GameActor actor) {
		if (actor instanceof PlayerGame) this.addActor(actor);
		this.entities.add(actor);
	}

	/**
	 * Supprime une entité de l'array d'entités
	 *
	 * @param actor entité à supprimer
	 */
	public void removeEntity(@NotNull GameActor actor) {
		if (this.entities.contains(actor)) {
			this.entities.remove(actor);
		} else {
			throw new IllegalArgumentException("MapGame#remove : actor not in mapEntities");
		}
	}
}
