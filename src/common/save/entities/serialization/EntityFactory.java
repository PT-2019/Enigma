package common.save.entities.serialization;

import api.utils.Utility;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import common.entities.Consumable;
import common.entities.GameObject;
import common.entities.Item;
import common.entities.players.EntityGame;
import common.utils.IDFactory;
import common.utils.Logger;
import data.EntitiesCategories;
import data.Layer;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

/**
 * Charge les entités depuis un json
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0
 * @since 3.0 14 décembre 2019
 */
public class EntityFactory {

	/**
	 * Entités chargés
	 * <p>
	 * La clef contient la class des entités
	 * <p>
	 * Pour chaque clef, on a la liste des entités de ce type.
	 */
	private static HashMap<String, Array<EntitySerializable>> loaded = new HashMap<>();

	/**
	 * hashmap locale des entités chargés pour {@link Json#fromJson(Class, String)}
	 **/
	private Array<EntitySerializable> content = new Array<>();

	/**
	 * constructeur par défaut pour new Instance de {@link Json#fromJson(Class, String)}
	 **/
	EntityFactory() {
	}

	/**
	 * Charge les entités depuis un fichier json et le sauvegarde dans la classe.
	 * <p>
	 * On peut les récupérer avec {@link #getEntitiesByCategory(EntitiesCategories)}
	 *
	 * @param path chemin du json
	 * @since 3.0
	 */
	public static void loadEntities(String path) {
		Json j = new Json();
		Array<EntitySerializable> content = j.fromJson(EntityFactory.class, Utility.loadFile(path)).content;

		//ajout a la factory des entités chargés
		for (EntitySerializable entity : new Array.ArrayIterator<>(content)) {
			String key = entity.getClassName();//className
			if (loaded.containsKey(key))
				loaded.get(key).addAll(entity);
			else {
				Array<EntitySerializable> array = new Array<>();
				array.addAll(entity);
				loaded.put(key, array);
			}
		}
	}

	/**
	 * Charge les joueurs depuis un fichier json et le sauvegarde dans la classe.
	 * <p>
	 * On peut les récupérer avec {@link #getEntitiesByCategory(EntitiesCategories)}
	 *
	 * @param path chemin du json
	 * @since 3.0
	 */
	public static void loadPlayers(String path) {
		Json j = new Json();
		Array<PlayerSerializableToJson> content;

		content = j.fromJson(PlayerFactory.class, Utility.loadFile(path)).content;

		//ajout a la factory des entités chargés
		for (EntitySerializable entity : new Array.ArrayIterator<>(content)) {
			String key = entity.getClassName();//className
			if (loaded.containsKey(key))
				loaded.get(key).addAll(entity);
			else {
				Array<EntitySerializable> array = new Array<>();
				array.addAll(entity);
				loaded.put(key, array);
			}
		}
	}

	/**
	 * Charge les entités depuis un fichier json et le sauvegarde dans la classe.
	 * <p>
	 * On peut les récupérer avec {@link #getEntitiesByCategory(EntitiesCategories)}
	 *
	 * @param path chemin du json
	 * @since 5.0
	 */
	public static void loadItems(String path) {
		Json j = new Json();
		Array<? extends EntitySerializable> content = j.fromJson(ItemFactory.class, Utility.loadFile(path)).content;

		//ajout a la factory des entités chargés
		for (EntitySerializable entity : new Array.ArrayIterator<>(content)) {
			String key = entity.getClassName();//className
			if (loaded.containsKey(key))
				loaded.get(key).addAll(entity);
			else {
				Array<EntitySerializable> array = new Array<>();
				array.addAll(entity);
				loaded.put(key, array);
			}
		}
	}

	/**
	 * Renvoi toutes les entités appartenant a une catégorie
	 *
	 * @param categories la catégorie voulue
	 * @return toutes les entités appartenant a cette catégorie
	 * @since 3.0
	 */
	public static Array<EntitySerializable> getEntitiesByCategory(EntitiesCategories categories) {
		Array<EntitySerializable> entityGraphics = new Array<>();

		for (Map.Entry<String, Array<EntitySerializable>> classes : loaded.entrySet()) {
			for (EntitySerializable entity : new Array.ArrayIterator<>(classes.getValue())) {
				if (categories.equals(entity.getCategory())) {
					entityGraphics.add(entity.duplicates());
				}
			}
		}

		return entityGraphics;
	}

	/**
	 * Crée une entité depuis une entité serializable
	 *
	 * @param entity    une entité
	 * @param id        son identifiant ou -1 si aucun. NULL pour attribution automatique
	 * @param pos       sa position @Nullable
	 * @param idFactory idFactory
	 * @return un GameObject représentant l'entitée
	 * @since 4.0
	 */
	public static GameObject createEntity(EntitySerializable entity, Integer id, @Nullable Vector2 pos, IDFactory idFactory) {
		GameObject object;
		object = (GameObject) Utility.instance(entity.getClassName());

		//id
		if (id == null) {
			//attribution
			idFactory.newID(object);
		} else {
			idFactory.malloc(object, id);
		}
		//location
		object.setDimension(entity.getWidth(), entity.getHeight());

		//données en plus
		object.serialization(entity, object);

		//pas top, ajoute les informations en plus
		//on devrait faire une méthode qui demande a l'entité s'il elle veut
		//ajouter des info et une interface plutôt que donner direct NPC
		//mais j'ai flemme et manque de temps là ~#rush
		if (entity instanceof PlayerSerializableToJson && object instanceof EntityGame) {
			PlayerSerializableToJson player = ((PlayerSerializableToJson) entity);
			((EntityGame) object).setJson(player.getJson(), player.getKey());
			//Logger.printDebug("EntityFactory", "PlayerSerializable loaded.");
		}

		if (entity instanceof ItemSerializableToJson && object instanceof Item) {
			ItemSerializableToJson player = ((ItemSerializableToJson) entity);
			((Consumable) object).setAtlas(player.getAtlas(), player.getKey());
			Logger.printDebug("EntityFactory", "ItemSerializableToJson loaded.");
		}

		if (pos != null)
			object.setGameObjectPosition(pos);
		//layers
		for (Layer l : Layer.values()) {
			//récupère les tiles de l'entités pour ce niveau
			Array<Float> entities = entity.getTiles(l);

			//si pas de tiles a mettre sur ce layer, on passe au suivant
			if (entities == null) continue;

			object.setTiles(entities, l);
		}

		return object;
	}

	private static final class PlayerFactory {
		/**
		 * hashMap locale des entités chargés pour {@link Json#fromJson(Class, String)}
		 **/
		private Array<PlayerSerializableToJson> content = new Array<>();

		/**
		 * constructeur par défaut pour new Instance de {@link Json#fromJson(Class, String)}
		 **/
		PlayerFactory() {
		}
	}


	private static final class ItemFactory {
		/**
		 * hashmap locale des entités chargés pour {@link Json#fromJson(Class, String)}
		 **/
		private Array<ItemSerializableToJson> content = new Array<>();

		/**
		 * constructeur par défaut pour new Instance de {@link Json#fromJson(Class, String)}
		 **/
		ItemFactory() {
		}
	}
}
