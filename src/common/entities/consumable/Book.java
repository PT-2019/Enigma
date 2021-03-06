package common.entities.consumable;

import api.utils.Utility;
import com.badlogic.gdx.maps.MapProperties;
import common.entities.types.AbstractConsumable;
import common.entities.types.Content;
import common.language.GameFields;
import common.language.GameLanguage;
import common.save.entities.PlayerSave;
import common.save.entities.SaveKey;
import common.save.entities.SaveTiles;
import data.TypeEntity;

import java.util.EnumMap;
import java.util.HashMap;

/**
 * Un livre
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0
 * implNote Factorisé avec AbstractItem
 * @since 2.0
 */
public class Book extends AbstractConsumable implements Content {

	/**
	 * Contenu de l'objet
	 *
	 * @since 2.0
	 */
	private String content;

	/**
	 * Crée un livre
	 *
	 * @since 2.0
	 */
	public Book() {
		this(-1);
	}

	/**
	 * Crée un livre avec un id unique pour identifier ses énigmes
	 *
	 * @param id ID
	 * @since 2.2
	 */
	public Book(int id) {
		super(id);
		this.content = "";
	}

	//content

	@Override
	public String getContent() {
		return this.content;
	}

	@Override
	public void setContent(String content) {
		this.content = content;
	}

	//toString

	@Override
	public String toString() {
		return "Book{" + "enigmas=" + enigmas + ", content='" + content + '\'' + ", id=" + id + '}';
	}

	@Override
	public EnumMap<TypeEntity, Boolean> getImplements() {
		EnumMap<TypeEntity, Boolean> imp = TypeEntity.emptyMap();
		imp.put(TypeEntity.CONTENT, true);
		imp.put(TypeEntity.CONSUMABLE, true);
		imp.put(TypeEntity.NEED_CONTAINER, true);
		imp.put(TypeEntity.NEED_CONTAINER_MANAGER, true);
		imp.put(TypeEntity.ENIGMA_CONTAINER, true);
		return imp;
	}

	@Override
	public String getReadableName() {
		return GameLanguage.gl.get(GameFields.BOOK);
	}

	@Override
	public HashMap<SaveKey, String> getSave() {
		HashMap<SaveKey, String> save = new HashMap<>();
		save.put(PlayerSave.CONTENT, this.content);
		save.put(PlayerSave.ALT_TILES, SaveTiles.save(this.altTiles));
		save.put(PlayerSave.KEY,this.atlasName);
		save.put(PlayerSave.PATH,this.atlasPath);
		return save;
	}

	@Override
	public void load(MapProperties data) {
		//récupère la chaîne non échappée
		this.content = data.get(PlayerSave.CONTENT.getKey(), String.class);
		if (this.content != null) this.content = Utility.asciiEscapedToNormalString(this.content);
		this.altTiles = SaveTiles.load(data.get(PlayerSave.ALT_TILES.getKey(), String.class));
		if(altTiles == null) this.altTiles = new HashMap<>();
		this.content = Utility.asciiEscapedToNormalString(data.get(PlayerSave.CONTENT.getKey(), String.class));
		this.atlasName = Utility.asciiEscapedToNormalString(data.get(PlayerSave.KEY.getKey(), String.class));
		this.atlasPath = Utility.asciiEscapedToNormalString(data.get(PlayerSave.PATH.getKey(), String.class));
	}
}
