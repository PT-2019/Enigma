package common.map;

import api.libgdx.actor.GameActor;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import common.enigmas.Enigma;
import common.enigmas.TileEvent;
import common.enigmas.TileEventEnum;
import common.entities.GameObject;
import common.entities.players.Player;
import common.entities.players.PlayerGame;
import common.entities.types.EnigmaContainer;
import data.Layer;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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
	/**
	 * Tableau des énigmes
	 */
	private HashMap<Vector2,TileEvent> enigmes;

	public GameMap(final String path, float unitScale) {
		super(path, unitScale);

		this.showLayer(Layer.COLLISION, true);

		this.entities = new ArrayList<>();
		this.enigmes = new HashMap<>();

		this.loadTileEvents();
	}

	private void loadTileEvents() {
		//parcours de tous les objects
		//si instace of EnigmaContainer
		//alors on ajoute au TileEvent ses énigmes

		//2,2 => 10 (Vector(2,2) , while => ajout au tile event) ajout du tout à la map

		//trier les EnigmaContainer dans GameObject
		for (Map.Entry<Vector2,ArrayList<GameObject>> a : this.objects.getAll().entrySet()){
			TileEvent event = new TileEvent();
			for (GameObject obj : a.getValue()){
				if (obj instanceof EnigmaContainer){
					//parcourir le event qui contient toute les enigmes
					Iterator<Enigma> enigma = ((EnigmaContainer) obj).getAllEnigmas();
					while(enigma.hasNext()){
						event.add(enigma.next());
					}
				}
			}
			this.enigmes.put(a.getKey(),event);
		}
	}

	/**
	 *  Définit si la case est accessible
	 * @param posX coordonnées X
	 * @param posY coordonnées Y
	 * @param actor
	 * @return true = accessible, false = non accessible
	 */
	public boolean isWalkable(float posX, float posY, GameActor actor) {
		System.out.println("isWalkable");
		Vector2 position = posToIndex(posX,posY,this);

		TiledMapTileLayer tiledmap = (TiledMapTileLayer) this.map.getMap().getLayers().get(Layer.COLLISION.name());
		TiledMapTileLayer.Cell c = tiledmap.getCell((int)position.x,(int)position.y);

		if (c == null){

		}else{
			//si on retourne null alors il n'y a pas collision
			if(c.getTile() != null){
				return false;
			}
		}
		return true;
	}

	/**
	 * Execute les actions sur une case
	 * @param posX coordonnées x de la case
	 * @param posY coordonnées y de la case
	 * @param actor
	 * @param action
	 * @return
	 */
	public boolean doAction(float posX, float posY, PlayerGame actor, TileEventEnum action){
		//convertit pos
		Vector2 position = posToIndex(posX,posY,this);

		//récup actions case
		for (Map.Entry<Vector2,TileEvent> e : this.enigmes.entrySet()){
			if (position.equals(e.getKey())){
				//test type avec action
				if (action == TileEventEnum.ON_USE){
					e.getValue().onUse(actor.getPlayer());
				}else if (action == TileEventEnum.ON_ENTER){
					e.getValue().onEnter(actor.getPlayer());
				}else if (action == TileEventEnum.ON_EXIT){
					e.getValue().onExit(actor.getPlayer());
				}
			}
		}
		return false;
	}

	@Override
	public void act(float delta) {
		super.act(delta);

		//trier la liste des entités par position y, par plus grand y
		//y = 0 <=> bas de la map
        //entities.sort(entities.);
	}

	@SuppressWarnings("LibGDXFlushInsideLoop")
	@Override
	public void draw(Batch batch, float parentAlpha) {
		//super.draw(batch, parentAlpha);//cette ligne dessine les composant ajoutés à la map

		//dessin de la map
		//....
		//Setup camera
		this.map.setView(this.camera);

		for (Layer layer : Layer.values()){
			if (!this.map.getMap().getLayers().get(layer.name()).isVisible()/*== Layer.COLLISION*/ ){
				continue;
			}
			for (GameActor entite : this.entities) {
				if (entite.getLayer() == layer){
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
