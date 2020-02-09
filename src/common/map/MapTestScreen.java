package common.map;

import api.utils.Utility;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.utils.Array;
import common.enigmas.Enigma;
import common.enigmas.condition.Condition;
import common.enigmas.operation.Operation;
import common.entities.types.EnigmaContainer;
import common.utils.Logger;
import api.libgdx.utils.Bounds;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import common.entities.GameObject;
import common.entities.special.GameExit;
import common.entities.types.ContainersManager;
import common.entities.types.NeedContainer;
import common.entities.types.NeedContainerManager;
import common.save.entities.serialization.EntityFactory;
import common.save.entities.serialization.EntitySerializable;
import data.EntitiesCategories;
import data.Layer;
import data.NeedToBeTranslated;
import editor.EditorLauncher;
import editor.bar.edition.ActionTypes;
import editor.bar.edition.ActionsManager;
import editor.bar.edition.actions.EditorActionFactory;
import editor.popup.cases.CasePopUp;
import editor.popup.cases.CaseView;
import editor.popup.listeners.CaseListener;
import game.gui.CategoriesMenu;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.JComponent;
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
 * @version 5.5
 * @since 2.0 5 décembre 2019
 */
public class MapTestScreen extends AbstractMap {

	/**
	 * Indique si une sortie est présente
	 *
	 * @since 5.5
	 */
	private boolean hasExit;

	/**
	 * Surveille les actions effectuées
	 */
	private ActionsManager manager;

	/**
	 * Crée une map depuis un fichier tmx
	 *
	 * @param path      fichier .tmx
	 * @param unitScale zoom
	 * @since 2.0
	 */
	public MapTestScreen(@NotNull final String path, float unitScale) {
		super(path, unitScale);

		//affiche la grille
		this.showGrid(true);

		//cache collision
		this.showLayer(Layer.COLLISION, false);

		//setup camera
		this.camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		this.camera.position.set(
				Gdx.graphics.getWidth() / 2f - this.getMapWidth() / 2f - CategoriesMenu.WIDTH,
				Gdx.graphics.getHeight() / 2f - this.getMapHeight() / 2f, 0);
		this.camera.update();

		//composant swing
		JComponent container = (JComponent) EditorLauncher.getInstance().getWindow().getContentPane();

		//historique
		this.manager = ActionsManager.getInstance();

		//ajoute les listeners aux cellules
		createCell(container);
	}

	/**
	 * Charge une entité sur la map a un position si elle est sur la map
	 *
	 * @param entity l'entité à charger
	 * @param pos    la position o&#249; charger
	 * @return true si l'entité a étée chargée
	 * @since 3.0
	 */
	public LoadedEntity loadEntity(EntitySerializable entity, @Nullable Vector2 pos) {
		Vector2 start = null;
		//récupère la position sur la map depuis pos, s'il y a une pos
		if (pos != null){
			start = getMapPosition(pos, entity);
			if(start == null) return new LoadedEntity(null, LoadEntityError.OUT_MAP.message);
		}

		if (entity.getCategory().name.equals(EntitiesCategories.ACTIONS.name)) {
			if (entity.getClassName() == null) {
				Logger.printError("MapTestScreen#loadEntity", "Action "+entity+" non supportée.");
				return new LoadedEntity(null, LoadEntityError.NOT_SUPPORTED.message);
			} else if (entity.getClassName().equals(GameExit.class.getName())) {
				//une seule sortie
				if (this.hasExit) {
					return new LoadedEntity(null, LoadEntityError.EXIT_MESSAGE.message);
				}
			}
		}

		GameObject object;

		//si l'entité doit être placée sur la map
		if (start != null) {
			//instancie l'entité
			object = EntityFactory.createEntity(entity, null, start, this.idFactory);

			//vérifie que le placement est possible
			String s = checkPlacement(object, start);
			if(s != null) return new LoadedEntity(null, s);

			if(object instanceof GameExit) this.hasExit = true;

			//print debug
			Logger.printDebug("MapTestScreen#loadEntity", object.toString() + " " + object.getID());

			//ajout à la liste des entités de la map
			this.objects.put(start, object);

			//ajoute à l'historique
			this.manager.add(EditorActionFactory.entity(ActionTypes.ADD_ENTITY, this, this.objects.getEntry(object)));

			// on place les tiles
			this.set(object, start);
		} else {
			//sinon c'est une entité dite temporaire donc pas sur la map mais que l'on garde
			//par exemple pour les énigmes, on peut sélectionner des entités depuis le menu
			int id = idFactory.getNextID();
			start = new Vector2(-1 * id, -1 * id);

			//instancie l'entité
			object = EntityFactory.createEntity(entity, null, start, this.idFactory);
			Logger.printDebug("MapTestScreen#loadEntity", object.toString() + " " + object.getID());

			//ajout à la liste des entités de la map
			this.objects.put(start, object);
		}

		return new LoadedEntity(object, null);
	}

	/**
	 * Retourne la position du entité dans la map ou null si pas dedans
	 * @param pos position x,y
	 * @param entity entité
	 * @return la position du entité dans la map ou null si pas dedans
	 */
	private Vector2 getMapPosition(Vector2 pos, EntitySerializable entity) {
		//calcules les 4 coins de la map
		Rectangle bounds = this.getMapSize();
		bounds.setPosition((Gdx.graphics.getWidth() / 2f - camera.position.x) * 1,
				(Gdx.graphics.getHeight() / 2f - camera.position.y) * 1);
		this.mapBounds = new Bounds(bounds);

		//Utility.printDebug("MapLibgdx - placement", mapBounds + " pos=" + pos + " " + mapBounds.contains(pos));

		//si pas dans la map
		if (!mapBounds.contains(pos)) return null;
		if (!mapBounds.contains(new Vector2(pos).add(entity.getWidth() * tileWidth, 0))) return null;
		if (!mapBounds.contains(new Vector2(pos).add(0, -entity.getHeight() * tileHeight))) return null;

        /*
            retire l'offset de l'espace

            la position x commence du clic est à 350 et la map à 300
            donc on x=50
         */
		pos.x -= this.mapBounds.left;
		pos.y -= this.mapBounds.bot;

		//obtient le coin supérieur gauche ou commencer a placer des tiles
		return posToIndex(pos.x, pos.y, this);
	}

	/**
	 * Vérifie que le placement est possible
	 * @param object object
	 * @param start position
	 * @return true si possible
	 */
	private String checkPlacement(GameObject object, Vector2 start) {
		//s'il y a des conditions pour placer l'item
		boolean needManager = object instanceof NeedContainerManager;
		boolean needContainer = object instanceof NeedContainer;
		HashMap<Vector2, GameObject> parentObject;
		if (needContainer || needManager) {
			//Vérifie que la case est vide.
			parentObject = this.getParentObject(start, object, false, true);

			if(parentObject != null && parentObject.size() != 0) {
				for (GameObject o : parentObject.values()) {
					if(o instanceof ContainersManager) continue;
					return o.getReadableName()+" "+ LoadEntityError.ENTITY_UNREACHABLE.message;
				}
			}

			parentObject = this.getParentObject(start, object);
			//pas de parent, placement raté
			if (parentObject == null || parentObject.isEmpty()) return LoadEntityError.NO_CONTAINER.message;

			//useless ...
			for (Map.Entry<Vector2, GameObject> entries : parentObject.entrySet()) {
				//ok
				if (needContainer && entries.getValue() instanceof common.entities.types.Container)
					break;
				//ok
				//ajoute aux manager leur nouvel enfant
				if (needManager && entries.getValue() instanceof ContainersManager)
					break;
			}

			Logger.printDebug("MapTestScreen#checkPlacement", "Restoration des entités.");

			saveParents(parentObject);

			return null;
		}

		parentObject = getParentObject(start, object, false, true);

		//Vérifie que la salle est vide.
		for (Map.Entry<Vector2, GameObject> entry :parentObject.entrySet()) {
			if(!(entry.getValue() instanceof ContainersManager))
				return LoadEntityError.SPOT_NOT_EMPTY.message;
		}

		Logger.printDebug("MapTestScreen#checkPlacement", "Restoration des room(s)");

		saveParents(parentObject);

		return null;
	}

	/**
	 * Liste des éléments déjà restorés
	 * @since 6.1
	 * @see #saveParents(HashMap)
	 */
	private ArrayList<GameObject> restored = new ArrayList<>();

	/**
	 * Restaure les tiles des parents s'ils viennent d'une sauvegarde en case
	 * de suppression de l'enfant.
	 * @param parents parent
	 * @since 6.1
	 */
	private void saveParents(HashMap<Vector2, GameObject> parents) {
		boolean stop;
		for (Map.Entry<Vector2, GameObject> entry: parents.entrySet()) {
			GameObject value = entry.getValue();
			Vector2 start = entry.getKey();
			if(this.restored.contains(value)) continue;
			stop = false;

			//on parcours toutes les niveaux de la map et on y ajoute les tiles de l'entité
			for (MapLayer mapLayer : this.map.getMap().getLayers()) {
				//c'est un layer de tiles ?
				if (!(mapLayer instanceof TiledMapTileLayer)) continue;

				TiledMapTileLayer tileLayer = (TiledMapTileLayer) mapLayer;

				//récupère les tiles de l'entités pour ce niveau
				Array<Float> ent = value.getTiles(Utility.stringToEnum(tileLayer.getName(), Layer.values()));

				//System.out.println("avant"+ent);

				//si pas de tiles a mettre sur ce layer, on passe au suivant
				if (ent == null) continue;

				//calcul pour placer les tiles depuis x et y
				//sachant que y est inversé, on part de la dernière tile et on remonte
				//pas de problème pour x
				for (int i = (int) start.y - 1, index = 0; i >= (start.y - value.getGameObjectHeight()); i--) {
					for (int j = (int) start.x; j < start.x + value.getGameObjectWidth() && index < ent.size; j++, index++) {
						MapTestScreenCell c = (MapTestScreenCell) tileLayer.getCell(j, i);
						if (c == null || c.getTile() == null) continue;
						if(ent.get(index) != 0) {
							stop = true;
							break;
						}
						ent.set(index, (float) c.getTile().getId());
					}
					if(stop) break;
				}
				//System.out.println("après"+ent);
				if(stop) break;
			}

			this.restored.add(value);
		}
	}

	@Override
	public String removeEntity(GameObject entity) {
		//Récupère l'object
		MapObject object = this.objects.getEntry(entity);
		//vérifie si suppression possible
		String check = this.checkDelete(object);
		if(check != null) return check;
		//suppression
		boolean removed = super.removeEntity(entity) == null;
		//si supprimé
		if(removed){
			if (entity instanceof GameExit) this.hasExit = false;
			if(object.getPosition().y >= 0 && object.getPosition().x >= 0) {
				//ajoute à l'historique si pas déjà concerné
				this.manager.add(EditorActionFactory.entity(ActionTypes.REMOVE_ENTITY, this, object));
				//libère l'id
				this.idFactory.free(entity, false);
			}
		}
		return null;
	}


	/**
	 * Vérifie si la suppression est possible
	 * @param mapObject entité (MapObject)
	 * @return true si c'est possible
	 */
	private String checkDelete(MapObject mapObject) {
		int parentID = mapObject.getEntity().getID();
		ArrayList<Integer> ids = new ArrayList<>();
		ids.add(parentID);
		if(mapObject.getEntity() instanceof ContainersManager){
			HashMap<Vector2, GameObject> parents;
			//récupère les enfants
			parents = this.getParentObject(mapObject.getPosition(), mapObject.getEntity(), false, true);
			//garde les ids des enigmaContainer
			for (GameObject son :parents.values()) {
				if(son instanceof EnigmaContainer) ids.add(son.getID());
			}
		}

		ArrayList<EnigmaContainer> enigmaContainers = this.objects.getAllObjectsByClass(EnigmaContainer.class);
		//vérifie si utilisé dans une énigme
		for (EnigmaContainer container : enigmaContainers) {
			Iterator<Enigma> allEnigmas = container.getAllEnigmas();
			while (allEnigmas.hasNext()){
				Enigma enigma = allEnigmas.next();
				//parcours des conditions
				Iterator<Condition> allConditions = enigma.getAllConditions();
				while(allConditions.hasNext()){
					Condition c = allConditions.next();
					//si utilisé
					int id = c.getEntity().getID();
					if(c.getEntity() != null && ids.contains(id)) {
						//si parent a un pb
						if (id == parentID) return LoadEntityError.USED_IN_ENIGMA_COND.message;
						//si enfant
						else return LoadEntityError.CHILD_HAD_ENIGMA_COND.message;
					}
				}

				Iterator<Operation> allOperations = enigma.getAllOperations();
				//parcours des opérations
				while(allOperations.hasNext()){
					Operation o = allOperations.next();
					//si utilisé
					int id = o.getEntity().getID();
					if(o.getEntity() != null && ids.contains(id))
						//si parent a un pb
						if (id == parentID) return LoadEntityError.USED_IN_ENIGMA_OP.message;
							//si enfant
						else return LoadEntityError.CHILD_HAD_ENIGMA_OP.message;
				}
			}
		}

		return null;
	}

	/**
	 * Permet de créer tout les listeners sur les cases
	 *
	 * @param jComponent component swing
	 */
	private void createCell(JComponent jComponent) {
		CasePopUp popUp = new CasePopUp(jComponent, this.map.getMap());
		CaseListener listenerCase = new CaseListener(popUp);
		MapLayers layers = this.map.getMap().getLayers();

		TiledMapTileLayer layer = (TiledMapTileLayer) layers.get(0);
		for (int y = 0; y < layer.getHeight(); y++) {
			for (int x = 0; x < layer.getWidth(); x++) {
				MapTestScreenCell cell = (MapTestScreenCell) layer.getCell(x, y);

				CaseView actor = new CaseView(cell);

				actor.setBounds(x * layer.getTileWidth(), y * layer.getTileHeight(),
						layer.getTileWidth(), layer.getTileHeight());

				addActor(actor);

				layer.setCell(x, y, cell);

				actor.addListener(listenerCase);
			}
		}
	}

	/**
	 * Les messages d'erreurs de loadEntity
	 *
	 * @author Jorys-Micke ALAÏS
	 * @author Louka DOZ
	 * @author Loic SENECAT
	 * @author Quentin RAMSAMY-AGEORGES
	 *
	 * @version 6.0 09/02/2020
	 * @since 6.0 09/02/2020
	 */
	private enum LoadEntityError {
		OUT_MAP(NeedToBeTranslated.DRAG_AND_DROP_NOT_IN_MAP),
		NO_CONTAINER(NeedToBeTranslated.DRAG_AND_DROP_FAILED_CONTAINER),
		NOT_SUPPORTED(NeedToBeTranslated.DRAG_AND_DROP_NOT_SUPPORTED),
		EXIT_MESSAGE(NeedToBeTranslated.DRAG_AND_DROP_EXIT_FAILED),
		ALREADY_TAKEN(NeedToBeTranslated.DRAG_AND_DROP_ALREADY_TAKEN),
		USED_IN_ENIGMA_COND(NeedToBeTranslated.DELETE_FAILED_IN_ENIGMA_COND),
		USED_IN_ENIGMA_OP(NeedToBeTranslated.DELETE_FAILED_IN_ENIGMA_OP),
		CHILD_HAD_ENIGMA_COND(NeedToBeTranslated.DELETE_FAILED_CHILD_ENIGMA_COND),
		CHILD_HAD_ENIGMA_OP(NeedToBeTranslated.DELETE_FAILED_CHILD_ENIGMA_OP),
		SPOT_NOT_EMPTY(NeedToBeTranslated.DRAG_AND_DROP_NOT_EMPTY_SAVE),
		ENTITY_UNREACHABLE(NeedToBeTranslated.DRAG_AND_DROP_ENTITY_UNREACHABLE);

		public final String message;

		LoadEntityError(String message) {
			this.message = message;
		}
	}

	/**
	 * Entité chargée avec un message d'erreur si aucune
	 *
	 * @author Jorys-Micke ALAÏS
	 * @author Louka DOZ
	 * @author Loic SENECAT
	 * @author Quentin RAMSAMY-AGEORGES
	 *
	 * @version 6.0 09/02/2020
	 * @since 6.0 09/02/2020
	 */
	public static final class LoadedEntity {

		/**
		 * L'entité placée
		 */
		@Nullable
		public final GameObject entity;
		/**
		 * Message de placement
		 */
		@Nullable
		public final String message;

		/**
		 * Entité chargée avec un message d'erreur si aucune
		 * @param entity L'entité placée
		 * @param message Message de placement
		 */
		LoadedEntity(@Nullable GameObject entity, @Nullable String message) {
			this.entity = entity;
			this.message = message;
		}
	}
}
