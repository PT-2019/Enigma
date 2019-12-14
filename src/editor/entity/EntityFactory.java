package editor.entity;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import editor.datas.EntitiesCategories;
import editor.utils.Utility;

import java.util.HashMap;
import java.util.Map;

/**
 * Charge les entités depuis un json
 *
 * @version 3.0 14 décembre 2019
 * @since 14 december 2019
 */
public class EntityFactory {

	/**
	 * Entités chargés
	 *
	 * La clef contient la class des entités
	 *
	 * Pour chaque clef, on a la liste des entités de ce type.
	 */
	private static HashMap<String, Array<EntitySerializable>> loaded = new HashMap<>();

	/** hashmap locale des entités chargés pour {@link Json#fromJson(Class, String)} **/
	private HashMap<String, EntitySerializable> content = new HashMap<>();

	/** constructeur par défault pour new Instance de {@link Json#fromJson(Class, String)} **/
	EntityFactory(){}

	/**
	 * Charge les entités depuis un fichier json et le sauvegarde dans la classe.
	 *
	 * On peut les récupérer avec {@link #getEntitiesByCategory(EntitiesCategories)}
	 *
	 * @param path chemin du json
	 */
	public static void loadEntities(String path){
		Json j = new Json();
		EntityFactory entityFactory = j.fromJson(EntityFactory.class, Utility.loadFile(path));

		//ajout a la factory des entités chargés
		for (Map.Entry<String, EntitySerializable> entity : entityFactory.content.entrySet()) {
			String key = entity.getKey();
			EntitySerializable value = entity.getValue();
			if(loaded.containsKey(key))
				loaded.get(key).addAll(value);
			else {
				Array<EntitySerializable> array = new Array<>();
				array.addAll(value);
				loaded.put(key, array);
			}
		}
	}

	/**
	 * Renvoi toutes les entités appartenant a une catégorie
	 *
	 * @param categories la catégorie voulue
	 *
	 * @return toutes les entités appartenant a cette catégorie
	 */
	public static Array<EntitySerializable> getEntitiesByCategory(EntitiesCategories categories){
		Array<EntitySerializable> entityGraphics = new Array<>();

		for (Map.Entry<String, Array<EntitySerializable>> classes :loaded.entrySet()) {
			for (EntitySerializable entity:new Array.ArrayIterator<>(classes.getValue())) {
				if(categories.equals(entity.getCategory())){
					entityGraphics.add(new EntitySerializable(entity));
				}
			}
		}

		return entityGraphics;
	}
}
