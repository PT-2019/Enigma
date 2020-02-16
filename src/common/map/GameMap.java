package common.map;

import api.libgdx.actor.GameActor;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import common.dialog.EnigmaDialogPopup;
import common.enigmas.TileEventEnum;
import common.entities.GameObject;
import common.entities.players.Monster;
import common.entities.players.NPC;
import common.entities.players.PlayerGame;
import common.entities.special.GameExit;
import common.entities.special.GameMusic;
import common.entities.special.MusicEditor;
import common.save.TmxProperties;
import common.save.entities.serialization.EntityFactory;
import common.save.entities.serialization.EntitySerializable;
import common.save.entities.serialization.MonsterFactory;
import common.save.entities.serialization.NpcFactory;
import common.save.entities.serialization.PlayerFactory;
import common.utils.Logger;
import data.Layer;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;

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
	 * Musique ambiante
	 */
	private GameMusic music;

	private EnigmaDialogPopup enigmaDialog;

	/**
	 * Resultat suite à un dialogue
	 *
	 */
	//todo pour l'instant c'est un string peut être changé cela
	private String resultDialog;

	public GameMap(final String path, float unitScale) {
		super(path, unitScale, false);

		this.showLayer(Layer.COLLISION, true);

		//change les cellules de la map
		this.initCells();

		//hahsmap <Vector2, TileEvent> ???
		this.loadTileEvents();

		this.entities = new ArrayList<>();
		this.enigmaDialog = new EnigmaDialogPopup();

		this.init();
	}

	/**
	 * Initialisation de la map.
	 *
	 * @since 6.0
	 */
	protected void init() {
		//recharge les entités depuis sauvegarde
		this.initEntities();

		//bounds
		this.setMapBounds();
	}

	private void loadTileEvents() {
		//parcours de tous les objects
		//si instace of EnigmaContainer
		//alors on ajoute au TileEvent ses énigmes

		//2,2 => 10 (Vector(2,2) , while => ajout au tile event) ajout du tout à la map
	}

	/**
	 * Définit si la case est accessible
	 *
	 * @param posX  coordonnées X
	 * @param posY  coordonnées Y
	 * @param actor
	 * @return true = accessible, false = non accessible
	 */
	public boolean isWalkable(float posX, float posY, GameActor actor) {
		Vector2 position = posToIndex(posX, posY, this);

		TiledMapTileLayer tiledmap = (TiledMapTileLayer) this.map.getMap().getLayers().get(Layer.COLLISION.name());
		TiledMapTileLayer.Cell c = tiledmap.getCell((int) position.x, (int) position.y);

		if (c == null) {

		} else {
			//si on retourne null alors il n'y a pas collision
			return c.getTile() == null;
		}
		return true;
	}

	/**
	 * @param posX
	 * @param posY
	 * @param actor
	 * @return
	 */
	public boolean doAction(float posX, float posY, GameActor actor, TileEventEnum action) {
		Vector2 position = posToIndex(posX, posY, this);

		//convertit pos

		//réceup actions case

		//test type avec action

		//lance

		return false;
	}

	/**
	 * Permet de savoir si c'est possible de bouger un personnage en fonction des autres GameActor
	 *
	 * @param actor On va tester en fonction de cet actor
	 * @return boolean true si collision false sinon
	 */
	@SuppressWarnings({"unchecked", "UnusedAssignment"})
	public boolean collision(GameActor actor, float movX, float movY) {
		boolean result = false;

		//on va itérer tout les actors connu sur la map
		//todo : peut être un autre système
		ArrayList<GameActor> actors = (ArrayList<GameActor>) this.entities.clone();
		actors.remove(actor);
		for (GameActor act : actors) {
			if (actor.overlaps(act, movX, movY)) {
				result = true;
				break;
			}
		}
		//pour vider au cas où l'arrayList temporaire
		actors = null;
		return result;
	}

	/**
	 * Renvoie EntityGame avec laquelle il a touché
	 *
	 * @param actor
	 * @param movX
	 * @param movY
	 * @return
	 */
	public GameActor collisionEntityGame(GameActor actor, float movX, float movY) {
		GameActor entity = null;

		ArrayList<GameActor> actors = (ArrayList<GameActor>) this.entities.clone();
		actors.remove(actor);
		for (GameActor act : actors) {
			if (actor.overlaps(act, movX, movY)) {
				entity = act;
				break;
			}
		}

		return entity;
	}

	@Override
	public void act(float delta) {
		super.act(delta);

		//trier la liste des entités par position y, par plus grand y
		//y = 0 <=> bas de la map
	}

	@SuppressWarnings("LibGDXFlushInsideLoop")
	@Override
	public void draw(Batch batch, float parentAlpha) {
		//super.draw(batch, parentAlpha);//cette ligne dessine les composant ajoutés à la map

		//dessin de la map
		//....
		//Setup camera
		this.map.setView(this.camera);

		for (Layer layer : Layer.values()) {
			if (!this.map.getMap().getLayers().get(layer.name()).isVisible()/*== Layer.COLLISION*/) {
				continue;
			}
			//render actors
			for (GameActor entity : this.entities) {
				if (entity.getLayer() == layer) {
					batch.end();
					batch.begin();
					entity.draw(batch, parentAlpha);
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

	/**
	 * Enlève l'entités vivante de la map et les actions comme la sortie
	 *
	 * @param entity l'entité à charger
	 * @param start  le coin supérieur gauche ou commencer a placer des tiles
	 * @since 4.0
	 */
	private void setEntityFromSave(GameObject entity, Vector2 start) {

		//on prends le layer où sont afficher les entités
		MapLayer mapLayer = this.map.getMap().getLayers().get(Layer.FLOOR2.name());
		TiledMapTileLayer tileLayer = (TiledMapTileLayer) mapLayer;

		//calcul pour placer les tiles depuis x et y
		//sachant que y est inversé, on part de la dernière tile et on remonte
		//pas de problème pour x
		for (int i = (int) start.y - 1, index = 0; i >= (start.y - entity.getGameObjectHeight()); i--) {
			for (int j = (int) start.x; j < start.x + entity.getGameObjectWidth(); j++, index++) {
				MapTestScreenCell c = (MapTestScreenCell) tileLayer.getCell(j, i);
				if (c == null) continue;
				c.setTile(null);

				tileLayer.setCell(j, i, c);
			}
		}
	}

	/**
	 * Renvoi les entités présente sur cette case
	 */
	public GameObject posToEntities(int row, int col) {
		MapLayer mapLayer = this.map.getMap().getLayers().get(Layer.FLOOR2.name());
		TiledMapTileLayer tileLayer = (TiledMapTileLayer) mapLayer;
		MapTestScreenCell c = (MapTestScreenCell) tileLayer.getCell(col, row);
		return c.getEntity();
	}

	@Override
	protected void initEntities() {
		ArrayList<MapProperties> entities = getProperty(TmxProperties.TMX_PROP_ENTITY, this);
		float x, y;
		int width, height;
		Integer id;
		//variable pour savoir si il faut afficher ou non l'entité
		boolean notdisplay = false;
		String className;
		EntitySerializable e;

		for (MapProperties prop : entities) {
			width = Math.round(prop.get(TmxProperties.TMX_WIDTH, Float.class));
			height = Math.round(prop.get(TmxProperties.TMX_HEIGHT, Float.class));
			className = prop.get(TmxProperties.TMX_PROP_ENTITY_CLASS, String.class);
			x = prop.get(TmxProperties.TMX_X, Float.class);
			//obligé de faire ce truc sale y2 car y renvoi truc bizarres y=789 renvoi y=0...
			y = Float.parseFloat(prop.get(TmxProperties.TMX_Y, String.class));

			id = prop.get(TmxProperties.TMX_ID, Integer.class);
			if (id == null) id = 0;

			Vector2 start = new Vector2(x, y);
			if (start.x >= 0 && start.y >= 0) {
				//TILES
				HashMap<String, Array<Float>> tiles = new HashMap<>();
				for (Layer layer : Layer.values()) {
					String sizeS = prop.get(layer.toString(), String.class);
					if (sizeS == null || sizeS.length() == 0) continue;
					int size = Integer.parseInt(sizeS);
					Array<Float> tile = new Array<>();
					for (int i = 0; i < size; i++) {
						tile.add(0f);
					}
					tiles.put(layer.toString(), tile);
				}
				e = new EntitySerializable(width, height, className, tiles);
				GameObject object = EntityFactory.createEntity(e, id, start, this.idFactory);
				object.load(prop);
				Logger.printDebugALL("MapTestScreen#initEntities", object + " " + start);

				//on place pas les entités "vivantes" dans les tiles de la map
				if (object instanceof NPC) {
					NPC npc = (NPC) object;
					GameActor entity;

					if (npc.isHero()) {
						entity = PlayerFactory.createPlayerGame(npc.getKey(), npc.getJson(), this);
						npc.setHero(true);
					} else {
						//c'est un npc
						entity = NpcFactory.createNpcGame(npc.getKey(), npc.getJson(),npc.getContent());
					}
					this.addEntity(entity);
					//on soustrait et augmente pour que ce soit à la bonne position
					entity.setPosition((x + 0.5f) * this.tileWidth * this.getUnitScale(),
							(y - 2) * this.tileHeight * this.getUnitScale());
					notdisplay = true;
				} else if (object instanceof Monster) {
					GameActor monster = MonsterFactory.createMonsterGame(((Monster) object).getKey(),
							((Monster) object).getJson(),((Monster) object).getContent());
					this.addEntity(monster);
					monster.setPosition((x + 0.5f) * this.tileWidth * this.getUnitScale(),
							(y - 2) * this.tileHeight * this.getUnitScale());
					notdisplay = true;
				} else if (object instanceof MusicEditor) {
					if (((MusicEditor) object).isMainMusic() && ((MusicEditor) object).isStarter())
						this.music = new GameMusic(((MusicEditor) object).getPath());
				} else {
					if (object instanceof GameExit) notdisplay = true;
				}

				if (notdisplay) {
					this.setEntityFromSave(object, start);
					notdisplay = false;
				} else {
					// on place les tiles
					this.setFromSave(object, start);
				}

				//ajout à la liste des entités de la map
				this.objects.put(start, object);
			} else {
				//entitée temporaire, pas sur la map
				e = new EntitySerializable(width, height, className, new HashMap<>());
				GameObject object = EntityFactory.createEntity(e, id, start, this.idFactory);

				//ajout à la liste des entités de la map
				//this.added.put(start, object);
				this.objects.put(start, object);

				Logger.printDebug("(tmp) MapTestScreen#initEntities", object + " " + start);
			}
		}
	}

	public void launchMusic() {
		if (this.music != null)
			this.music.getMusic().play();
	}

	public ArrayList<GameActor> getGameEntities() {
		return entities;
	}

	public void setResult(String resultDialog) {
		this.resultDialog = resultDialog;
	}

	public EnigmaDialogPopup getEnigmaDialog() {
		return enigmaDialog;
	}
}
