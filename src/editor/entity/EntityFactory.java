package editor.entity;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import editor.bibliotheque.MenuCategories;
import editor.datas.Layer;
import editor.utils.Utility;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class EntityFactory {

	private static HashMap<String, Array<EntitySerializable>> loaded = new HashMap<>();

	private HashMap<String, EntitySerializable> content = new HashMap<>();

	public static final class EntitySerializable extends ImageIcon {
		private String path;
		private int width, height;
		private HashMap<Layer, Array<Integer>> tiles;
		private MenuCategories category;

		public EntitySerializable(){}

		public EntitySerializable(EntitySerializable entitySerializable){
			super(entitySerializable.path);
			width = entitySerializable.width;
			height = entitySerializable.height;
			tiles = entitySerializable.tiles;
			category = entitySerializable.category;
		}
	}

	public static void loadEntities(String path){
		Json j = new Json();
		EntityFactory p = j.fromJson(EntityFactory.class, Utility.loadFile(path));
		for (Map.Entry<String, EntitySerializable> entity :p.content.entrySet()) {
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

		Utility.printHashMap(loaded);
	}

	public static Array<EntitySerializable> getEntitiesByCategory(MenuCategories categories){
		Array<EntitySerializable> entityGraphics = new Array<>();

		for (Map.Entry<String, Array<EntitySerializable>> classes :loaded.entrySet()) {
			for (EntitySerializable entity:new Array.ArrayIterator<>(classes.getValue())) {
				if(categories.equals(entity.category)){
					entityGraphics.add(new EntitySerializable(entity));
				}
			}
		}

		return entityGraphics;
	}
}
