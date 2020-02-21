package common.map;

import api.libgdx.actor.GameActor;
import api.utils.Utility;
import api.utils.annotations.ConvenienceMethod;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import common.dialog.Dialog;
import common.dialog.EnigmaDialogPopup;
import common.enigmas.Enigma;
import common.enigmas.TileEvent;
import common.enigmas.TileEventEnum;
import common.enigmas.condition.Answer;
import common.enigmas.reporting.ConditionReport;
import common.enigmas.reporting.EnigmaReport;
import common.enigmas.reporting.Report;
import common.entities.Consumable;
import common.entities.GameObject;
import common.entities.Item;
import common.entities.players.Monster;
import common.entities.players.NPC;
import common.entities.players.PlayerGame;
import common.entities.special.*;
import common.entities.special.inventory.InventoryDisplay;
import common.entities.types.ChangeState;
import common.entities.types.ChangeStateReport;
import common.entities.types.Container;
import common.entities.types.Content;
import common.entities.types.EnigmaContainer;
import common.entities.types.ShowContent;
import common.save.MapsNameUtils;
import common.save.TmxProperties;
import common.save.entities.serialization.EntityFactory;
import common.save.entities.serialization.EntitySerializable;
import common.save.entities.serialization.MonsterFactory;
import common.save.entities.serialization.NpcFactory;
import common.save.entities.serialization.PlayerFactory;
import common.utils.EnigmaUtility;
import common.utils.Logger;
import data.Layer;
import data.NeedToBeTranslated;
import game.screens.GameScreen;
import org.jetbrains.annotations.NotNull;

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
 * @version 6.5
 * @since 6.0
 */
public class GameMap extends AbstractMap {

	/**
	 * Tableau des entités
	 */
	private ArrayList<GameActor> entities;

	/**
	 * Tableau des énigmes
	 */
	private HashMap<Vector2, TileEvent> enigmes;

	/**
	 * Musique ambiante
	 */
	private GameMusic music;

	/**
	 * Popup de dialogue
	 */
	private static EnigmaDialogPopup enigmaDialog;

	/**
	 * Map du jeu
	 *
	 * @param path      chemin
	 * @param unitScale taux de zoom
	 */
	public GameMap(final String path, float unitScale) {
		super(path, unitScale, false);

		//affiche le layer de collision
		this.showLayer(Layer.COLLISION, true);

		//attributes
		this.entities = new ArrayList<>();
		this.enigmes = new HashMap<>();
		this.enigmaDialog = new EnigmaDialogPopup();
		this.music = new GameMusic();

		//relance init
		this.init();
	}

	@Override
	public void reload() {
		super.reload();

		//recharge les tileEvent depuis les énigmes
		this.loadTileEvents();
	}

	/**
	 * Charge les tileEvent
	 *
	 * @since 6.1
	 */
	private void loadTileEvents() {
		//parcours de tous les niveaux
		for (MapLayer l : this.map.getMap().getLayers()) {
			//si collision ou pas un niveau de tiles
			if (!(l instanceof TiledMapTileLayer)) continue;
			if (l.getName().equals(Layer.COLLISION.name())) continue;

			//Récupération du niveau
			TiledMapTileLayer layer = (TiledMapTileLayer) l;

			//parcours de tous les objects
			for (int i = 0; i < layer.getHeight(); i++) {
				for (int j = 0; j < layer.getWidth(); j++) {
					//récupération de la cellule
					MapTestScreenCell cell = (MapTestScreenCell) layer.getCell(j, i);

					//si instance of EnigmaContainer
					if (cell != null && cell.getEntity() instanceof EnigmaContainer) {
						//alors on ajoute au TileEvent ses énigmes
						Vector2 pos = new Vector2(j, i);
						TileEvent event = new TileEvent();

						//parcourir  toutes les enigmes de l'object
						Iterator<Enigma> enigma = ((EnigmaContainer) cell.getEntity()).getAllEnigmas();
						while (enigma.hasNext()) {
							//ajout à l'event
							event.add(enigma.next());
						}
						//ajout au tileEvent existant
						if (this.enigmes.containsKey(pos)) {
							this.enigmes.get(pos).add(event);
						} else {//sinon crée
							this.enigmes.put(pos, event);
						}
					}
				}
			}
		}
	}

	/**
	 * Définit si la case est accessible
	 *
	 * @param actor celui qui veut se déplacer
	 * @param offX  décalage X
	 * @param offY  décalage Y
	 * @return true = accessible, false = non accessible
	 * @since 6.0
	 */
	public boolean isWalkable(GameActor actor, float offX, float offY) {
		final float posX = actor.getX(), posY = actor.getY();
		//collision basique
		Vector2 official = posToIndex(posX + offX, posY + offY, this);

		TiledMapTileLayer tiledMap = (TiledMapTileLayer) this.map.getMap().getLayers().get(Layer.COLLISION.name());
		MapTestScreenCell c = (MapTestScreenCell) tiledMap.getCell((int) official.x, (int) official.y);

		//si retourne null alors il n'y a pas collision
		if (c == null || c.getTile() == null) {
			//si la case est walkable, on fait une petite vérification
			int directionX = offX == 0 ? 0 : offX < 0 ? -1 : 1;
			int directionY = offY == 0 ? 0 : offY < 0 ? -1 : 1;
			//collision avancée
			final float xC = directionX * actor.getOriginX() / 2;//ça marche avec /2 mais je sais pas pk.
			final float yC = directionY * actor.getOriginY();//et là il faut pas faire /2, vraiment bizarre
			Vector2 accurate = posToIndex(posX + offX + xC, posY + offY + yC, this);

			MapTestScreenCell correct = (MapTestScreenCell) tiledMap.getCell((int) accurate.x, (int) accurate.y);

			//vérifie tiles exclusifs
			for (MapLayer l : this.getTiledMap().getLayers()) {
				if (!(l instanceof TiledMapTileLayer)) continue;
				MapTestScreenCell tmp = (MapTestScreenCell) ((TiledMapTileLayer) l).getCell((int) accurate.x, (int) accurate.y);
				if (tmp != null && tmp.getTile() != null && tmp.getTile().getId() == MapsNameUtils.ABYSS)
					return false;
			}

			//correction sur le layer
			if (correct != null && correct.getTile() != null) {
				this.correctMap(actor, c, correct);
			}

			if (correct != null) {
				//gérer les cases supérieures et inférieures au centre de gravité
				Vector2 myCellPos = EnigmaUtility.getPosFromCellIndex(correct);
				//noinspection SuspiciousNameCombination
				Vector2 supCellPos = myCellPos.cpy().add(directionY, directionX);//case sup
				Vector2 opposedCellPos = myCellPos.cpy().add(-directionY, -directionX);//case opposée

				MapTestScreenCell cell = (MapTestScreenCell) tiledMap.getCell((int) supCellPos.x, (int) supCellPos.y);
				this.correctMap(actor, c, cell);
				cell = (MapTestScreenCell) tiledMap.getCell((int) opposedCellPos.x, (int) opposedCellPos.y);
				this.correctMap(actor, c, cell);
			}

			return true;
		}

		return false;
	}

	/**
	 * Corrige l'affichage du joueur et des tiles de la map.
	 * <p>
	 * Si le joueur (Y) est supérieur à celui de la case ou il veut se déplacer
	 * et on tolère la collision. Alors le joueur doit être affiché derrière l'object.
	 * <p>
	 * Si le joueur (Y) est inférieur à à celui de la case ou il veut se déplacer
	 * et on tolère la collision. Alors le joueur doit être affiché devant l'object.
	 *
	 * @param actor   acteur qui veut se déplacer
	 * @param c       la cellule ou on croit qu'il est
	 * @param correct la vraie cellule qu'il chevauche
	 * @since 6.5
	 */
	private void correctMap(GameActor actor, MapTestScreenCell c, MapTestScreenCell correct) {
		//problèmes
		if (c == null || correct == null) {
			System.out.println("c null ou correct null");
			return;
		}
		if (correct.getEntity() instanceof Room) {
			return;
		}

		//System.out.println("Correction de l'affichage, problème avec "+correct.getEntity().getReadableName());

		//récupère la position des entités
		Vector2 cPos = EnigmaUtility.getPosFromCellIndex(c), correctPos = EnigmaUtility.getPosFromCellIndex(correct);

		//l'entité sur laquelle on permet le chevauchement
		GameObject entity = correct.getEntity();

		if (entity instanceof Consumable) return;

		Layer entityLayer = getLayer(entity);

		//compare les deux niveaux
		int result = Layer.compare(actor.getLayer(), entityLayer);

		//le joueur est au dessus
		if (cPos.y >= correctPos.y) {
			if (result > 0) {//on doit augmenter layer de l'entité sur laquelle on permet le chevauchement
				this.changeLayer(entity, entityLayer, actor.getLayer());
			}
		} else {//le joueur est en dessous
			if (result <= 0) {//on doit baisser l'entité sur laquelle on permet le chevauchement
				this.changeLayer(entity, entityLayer, Layer.values()[actor.getLayer().ordinal() - 1]);
			}
		}
	}

	/**
	 * La procédure de changement de niveau
	 *
	 * @param entity      entité
	 * @param entityLayer son niveau
	 * @param increaseTo  le niveau auquel là placer
	 */
	private void changeLayer(GameObject entity, Layer entityLayer, Layer increaseTo) {
		//TODO: optimiser, obliger de supprimer puis remettre c'est pas top
		//save tiles
		saveChild(entity);
		//save pos
		Vector2 pos = this.objects.getVectorByObject(entity);
		//supprime
		removeEntity(entity);
		//augmente layer
		changeEntityLayer(entity, entityLayer, increaseTo);
		//ré-ajoute
		set(entity, pos);
		//repaint
		repaint(entity);
	}

	/**
	 * Change le niveau des tiles
	 *
	 * @param entity      entité
	 * @param entityLayer niveau de l'entité a changer
	 * @param increaseTo  niveau auquel mettre l'entité
	 * @since 6.5
	 */
	private void changeEntityLayer(GameObject entity, Layer entityLayer, Layer increaseTo) {
		//change les tiles alternatives
		if (entity instanceof ChangeState) {
			Map<String, Array<Float>> tilesFromState = ((ChangeState) entity).getTilesFromState();
			if (tilesFromState.containsKey(entityLayer.name())) {
				Array<Float> tiles = tilesFromState.get(entityLayer.name());
				tilesFromState.put(increaseTo.name(), tiles);
				tilesFromState.remove(entityLayer.name());
			}
		}

		//change les tiles normales
		Map<Layer, Array<Float>> tilesFromState = entity.getTiles();
		if (tilesFromState.containsKey(entityLayer)) {
			Array<Float> tiles = tilesFromState.get(entityLayer);
			tilesFromState.put(increaseTo, tiles);
			tilesFromState.remove(entityLayer);
		}
	}

	/**
	 * Retourne le plus haut niveau ou se trouve un entité
	 *
	 * @param entity une entité
	 * @return le plus haut niveau ou se trouve une entité
	 * @since 6.5
	 */
	private Layer getLayer(GameObject entity) {
		Layer highest = null;
		for (Map.Entry<Layer, Array<Float>> entry : entity.getTiles().entrySet()) {
			Layer layer = entry.getKey();
			if (layer.name().equals(Layer.COLLISION.name())) continue;//veut pas le layer de collision
			if (highest == null) highest = layer;
			else if (Layer.compare(layer, highest) > 0) highest = layer;//si le nouveau est plus grand
		}
		return highest;
	}

	/**
	 * Execute les actions sur une case
	 *
	 * @param posX   coordonnées x de la case
	 * @param posY   coordonnées y de la case
	 * @param actor  acteur qui exécute l'action
	 * @param action le type d'action
	 * @return true si une action a étée faite
	 * @since 6.3
	 */
	public boolean doAction(float posX, float posY, PlayerGame actor, TileEventEnum action) {
		//convertit pos
		Vector2 position = posToIndex(posX, posY, this);
		//entité en face
		final GameObject current = posToEntities((int) position.y, (int) position.x);

		//list des retours
		ArrayList<EnigmaReport> reports = new ArrayList<>();

		//up état jeu
		String stated = updateGameMap(current, actor, action);

		//récupère actions case
		for (Map.Entry<Vector2, TileEvent> e : this.enigmes.entrySet()) {
			if (position.equals(e.getKey())) {
				//test type avec action
				if (action == TileEventEnum.ON_USE) {
					reports.addAll(e.getValue().onUse(actor.getPlayer()));
				} else if (action == TileEventEnum.ON_ENTER) {
					reports.addAll(e.getValue().onEnter(actor.getPlayer()));
				} else if (action == TileEventEnum.ON_EXIT) {
					reports.addAll(e.getValue().onExit(actor.getPlayer()));
				}
			}
		}

		if (!reports.isEmpty()) {
			Report.sort(EnigmaReport.getAllReports(reports));
			//Récupère le report le plus important
			Report report = reports.get(0).getReport();
			//Affiche que si c'est au moins un peu important xD
			if (report.getImportance() == Report.MUST_BE_SHOWED) {
				EnigmaDialogPopup dialog = this.getEnigmaDialog();
				Dialog node = new Dialog(report.getReport());
				dialog.showDialog(node, this);
			} else if (stated != null && !stated.isEmpty()) {
				EnigmaDialogPopup dialog = this.getEnigmaDialog();
				Dialog node = new Dialog(stated);
				dialog.showDialog(node, this);
			} else if (report.equals(ConditionReport.NO_ANSWER)) {//demande réponse
				EnigmaDialogPopup dialog = this.getEnigmaDialog();
				//seul object du report noAnswer est l'answer
				dialog.showAnswer((Answer) reports.get(0).getEntities().get(0), this);
			}
			if (current instanceof ChangeState) {
				ChangeState o = (ChangeState) current;
				if (o.needReloadAfterStateChange()) {
					o.changeState(actor, action);
				}
			}
			//up des entités modifiées
			Array<GameObject> allEntities = EnigmaReport.getAllEntities(reports);
			this.repaint(allEntities, current);
		} else if (stated != null && !stated.isEmpty()) {
			EnigmaDialogPopup dialog = this.getEnigmaDialog();
			Dialog node = new Dialog(stated);
			dialog.showDialog(node, this);
		}

		return reports.isEmpty();
	}

	/**
	 * Re-dessine les entités passées en argument
	 *
	 * @param object  les entités a re-dessiner. Instance de ChangeState uniquement.
	 * @param current l'object current, forcément re-dessiné
	 * @since 6.5
	 */
	public void repaint(Array<GameObject> object, GameObject current) {
		for (GameObject o : new Array.ArrayIterator<>(object)) {
			if (!(o instanceof ChangeState)) continue; //pas de repaint, si change pas
			//save
			saveChild(o);
			//repaint si doit être re-dessiné
			if (((ChangeState) o).shouldAutomaticRepaint() || o.equals(current)) {
				this.updateEntity(o, true);
			}
		}
	}

	/**
	 * Re-dessine les entités passées en argument
	 *
	 * @param object l'entité a re-dessiner.
	 * @since 6.5
	 */
	public void repaint(GameObject object) {
		//save
		saveChild(object);
		//repaint
		this.updateEntity(object, true);
	}

	/**
	 * Met à jour l'affichage des gameObjects.
	 * Par exemple, on appuie sur 'e' devant un bouton, avant de lancer
	 * ses énigmes, il faut changer son état (+affichage)
	 *
	 * @param entity objects dont on doit changer/mettre à jour l'affichage
	 * @param action action effectuée
	 * @param actor  la personne qui fait l'action
	 * @return le message que l'on veux afficher
	 * @since 6.5
	 */
	private String updateGameMap(GameObject entity, PlayerGame actor, TileEventEnum action) {
		if (action == TileEventEnum.ON_USE) {
			EnigmaReport report = null;
			//si c'est une entité qui change d'état
			if (entity instanceof ChangeState) {
				//save
				saveChild(entity);
				//change state
				report = ((ChangeState) entity).changeState(actor, action);
				//si c'est une entité dont on affiche le contenu
			} else if (entity instanceof ShowContent) {
				EnigmaDialogPopup dialog = this.getEnigmaDialog();
				String content = ((Content) entity).getContent();
				if (content == null || content.isEmpty()) content = NeedToBeTranslated.NO_CONTENT;
				Dialog node = new Dialog(content);
				dialog.showDialog(node, this);
			}

			//si rien fait, on quitte
			if (report == null) return null;

			//parcours des (de l'entité xD) entités modifiées
			for (Object obj : report.getEntities()) {
				if (obj instanceof ChangeState) {
					GameObject o = (GameObject) obj;
					//met à jour son affichage
					this.updateEntity(o, true);

					EnigmaDialogPopup dialog = this.getEnigmaDialog();

					if (o instanceof Container && !(o instanceof NPC)) {
						if (report.getReport().equals(ChangeStateReport.LOCKED)) {
							//si verrouillé, fait rien
							String msg = report.getReport().getReport();
							if (msg == null || msg.isEmpty()) continue;
							return msg;
						}
						actor.getInventoryView().setVisible(true);
						InventoryDisplay objectInventory = GameScreen.getInventoryDisplay();
						objectInventory.setContainer((Container)o);
						//on met la fenêtre au dessus de celle de l'inventaire
						objectInventory.setPosition(0,actor.getInventoryView().getHeight());
						objectInventory.changeView();
						objectInventory.setVisible(true);

					} else if (o instanceof Consumable) {
						InventoryDisplay inventoryView = actor.getInventoryView();
						actor.getPlayer().addItem((Item)o);
						inventoryView.addItem((Item)o);
						inventoryView.setVisible(true);

						//préviens
						/*Dialog node = new Dialog("Ajoute dans l'inventaire " + o.getReadableName());
						dialog.showDialog(node, this);*/

						//supprime de la map
						this.removeEntity(o);
					} else {
						String msg = report.getReport().getReport();
						if (msg == null || msg.isEmpty()) continue;
						return msg;
					}
				}
			}
		}

		return null;
	}

	/**
	 * Sauvegarde les tiles d'un enfant
	 *
	 * @param o un gameObject
	 * @since 6.5
	 */
	@ConvenienceMethod
	private void saveChild(GameObject o) {
		if (restored.contains(o)) return;
		HashMap<Vector2, GameObject> m = new HashMap<>();
		m.put(this.objects.getVectorByObject(o), o);
		saveParents(m);
	}

	/**
	 * Re-affiche les tiles d'une entité
	 *
	 * @param o un entité
	 * @since 6.5
	 */
	private void updateEntity(GameObject o) {
		this.updateEntity(o, false);
	}

	/**
	 * Re-affiche les tiles d'une entité
	 *
	 * @param o          un entité
	 * @param interacted s'il y a eu interaction (touche utiliser)
	 * @since 6.5
	 */
	private void updateEntity(GameObject o, boolean interacted) {
		Vector2 start = this.objects.getVectorByObject(o);

		//on parcours toutes les niveaux de la map et on y ajoute les tiles de l'entité
		for (MapLayer mapLayer : this.map.getMap().getLayers()) {
			//c'est un layer de tiles ?
			if (!(mapLayer instanceof TiledMapTileLayer)) continue;

			TiledMapTileLayer tileLayer = (TiledMapTileLayer) mapLayer;

			//récupère les tiles de l'entités pour ce niveau
			Array<Float> ent;
			Layer layer = Utility.stringToEnum(tileLayer.getName(), Layer.values());
			if (interacted && o instanceof ChangeState) {
				ent = ((ChangeState) o).getTilesFromState(layer);
			} else {
				ent = o.getTiles(layer);
			}

			//si pas de tiles a mettre sur ce layer, on passe au suivant
			if (ent == null) continue;

			//calcul pour placer les tiles depuis x et y
			//sachant que y est inversé, on part de la dernière tile et on remonte
			//pas de problème pour x
			for (int i = (int) start.y - 1, index = 0; i >= (start.y - o.getGameObjectHeight()); i--) {
				for (int j = (int) start.x; j < start.x + o.getGameObjectWidth() && index < ent.size; j++, index++) {
					MapTestScreenCell c = (MapTestScreenCell) tileLayer.getCell(j, i);
					if (c == null || c.getTile() == null) continue;
					if (c.getEntity() != o) continue; //vole pas les tiles des autres

					c.setTile(this.map.getMap().getTileSets().getTile(MathUtils.ceil(ent.get(index))));
				}
			}
		}
	}

	/**
	 * Permet de savoir si c'est possible de bouger un personnage en fonction des autres GameActor
	 *
	 * @param actor On va tester en fonction de cet actor
	 * @param movX  déplacement X
	 * @param movY  déplacement Y
	 * @return boolean true si collision false sinon
	 * @since 6.2
	 */
	@SuppressWarnings({"unchecked", "UnusedAssignment"})
	public boolean collision(GameActor actor, float movX, float movY) {
		boolean result = false;

		//on va itérer tout les actors connu sur la map
		//todo : peut être un autre système --> se limiter au acteur au même niveau ?
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
	 * @param actor entité
	 * @param movX  position x
	 * @param movY  position y
	 * @return Renvoie EntityGame avec laquelle il a touché
	 * @since 6.2
	 */
	@SuppressWarnings("unchecked")
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
		for (int i = 0; i < this.getTiledMap().getLayers().getCount(); i++) {
			this.entities.sort((o1, o2) -> Float.compare(o2.getY(), o1.getY()));
		}
	}

	@SuppressWarnings("LibGDXFlushInsideLoop")
	@Override
	public void draw(Batch batch, float parentAlpha) {
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
	 * @since 6.0
	 */
	@SuppressWarnings("WeakerAccess")
	public void addEntity(@NotNull GameActor actor) {
		if (actor instanceof PlayerGame) this.addActor(actor);
		this.entities.add(actor);
	}

	/**
	 * Supprime une entité de l'array d'entités
	 *
	 * @param actor entité à supprimer
	 * @since 6.0
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
	 * @since 6.2
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
	 *
	 * @param col colonne
	 * @param row ligne
	 * @return le gameObject a cette position, null si aucun
	 * @since 6.2
	 */
	private GameObject posToEntities(int row, int col) {
		MapLayer mapLayer = this.map.getMap().getLayers().get(Layer.FLOOR2.name());
		TiledMapTileLayer tileLayer = (TiledMapTileLayer) mapLayer;
		MapTestScreenCell c = (MapTestScreenCell) tileLayer.getCell(col, row);
		c = EnigmaUtility.getRelevantEntity(c, this.getTiledMap());
		return c.getEntity();
	}

	@Override
	protected void initEntities() {
		ArrayList<MapProperties> entities = getProperty(TmxProperties.TMX_PROP_ENTITY, this);
		float x, y;
		int width, height;
		Integer id;
		//variable pour savoir si il faut afficher ou non l'entité
		boolean doNotDisplay = false;
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
						entity = NpcFactory.createNpcGame(npc.getKey(), npc.getJson(), npc.getContent());
					}
					this.addEntity(entity);
					Vector2 pos = indexToPos((int) x, (int) y, this);
					entity.setPosition(pos.x, pos.y);
					doNotDisplay = true;
				} else if (object instanceof Monster) {
					GameActor monster = MonsterFactory.createMonsterGame(((Monster) object).getKey(),
							((Monster) object).getJson(), ((Monster) object).getContent());
					this.addEntity(monster);
					Vector2 pos = indexToPos((int) x, (int) y, this);
					monster.setPosition(pos.x, pos.y);
					doNotDisplay = true;
				} else if (object instanceof MusicEditor) {
					if (((MusicEditor) object).isMainMusic() && ((MusicEditor) object).isStarter())
						this.music = new GameMusic(((MusicEditor) object).getPath());
				} else {
					if (object instanceof GameExit) doNotDisplay = true;
				}

				if (doNotDisplay) {
					this.setEntityFromSave(object, start);
					doNotDisplay = false;
				} else {
					// on place les tiles
					this.setFromSave(object, start);
				}

				//ajout à la liste des entités de la map
				this.objects.put(start, object);
			} else {
				//entité temporaire, pas sur la map
				e = new EntitySerializable(width, height, className, new HashMap<>());
				GameObject object = EntityFactory.createEntity(e, id, start, this.idFactory);

				//ajout à la liste des entités de la map
				//this.added.put(start, object);
				this.objects.put(start, object);

				Logger.printDebug("(tmp) MapTestScreen#initEntities", object + " " + start);
			}
		}
	}

	/**
	 * Lance la musique
	 *
	 * @since 6.2
	 */
	public void launchMusic() {
		if (this.music != null && this.music.hasMusic())
			this.music.getMusic().play();
	}

	/**
	 * Retournes les entités (actor) de la map
	 *
	 * @return les entités (actor) de la map
	 * @since 6.2
	 */
	public ArrayList<GameActor> getGameEntities() {
		return entities;
	}

	/**
	 * Retourne le gestionnaire de la musique du jeu
	 *
	 * @return le gestionnaire de la musique du jeu
	 * @since 6.4
	 */
	public GameMusic getGameMusic() {
		return music;
	}

	/**
	 * Le popup de dialogue
	 *
	 * @return Le popup de dialogue
	 * @since 6.4
	 */
	public static EnigmaDialogPopup getEnigmaDialog() {
		return enigmaDialog;
	}
}
