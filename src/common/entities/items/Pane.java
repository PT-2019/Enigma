package common.entities.items;

import com.badlogic.gdx.maps.MapProperties;
import common.entities.types.AbstractItem;
import common.entities.types.Content;
import common.language.GameFields;
import common.language.GameLanguage;
import common.save.entities.PlayerSave;
import common.save.entities.SaveKey;
import data.TypeEntity;

import java.util.EnumMap;
import java.util.HashMap;

/**
 * Un panneau
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0
 * @see common.entities.Item
 * @see Content
 * @see AbstractItem
 * @since 2.0
 */
public class Pane extends AbstractItem implements Content {

	/**
	 * Contenu de l'objet
	 *
	 * @since 2.0
	 */
	private String content;

	/**
	 * Crée un panneau
	 *
	 * @since 2.0
	 */
	public Pane() {
		this(-1);
	}

	/**
	 * Crée un panneau avec un id unique pour identifier ses énigmes
	 *
	 * @param id ID
	 * @since 2.2
	 */
	public Pane(int id) {
		super(id);
		this.content = "";
	}

	//content

	@Override
	public void addContent(String content) {
		this.content = content;
	}

	@Override
	public String getContent() {
		return this.content;
	}

	//toString

	@Override
	public String toString() {
		return "Pane{" + "content='" + content + '\'' + ", id=" + id + '}';
	}

	@Override
	public EnumMap<TypeEntity, Boolean> getImplements() {
		EnumMap<TypeEntity, Boolean> imp = TypeEntity.emptyMap();
		imp.put(TypeEntity.CONTENT, true);

		imp.put(TypeEntity.ITEM, true);
		imp.put(TypeEntity.NEED_CONTAINER_MANAGER, true);
		imp.put(TypeEntity.ENIGMA_CONTAINER, true);
		return imp;
	}

	@Override
	public String getReadableName() {
		return GameLanguage.gl.get(GameFields.PANE);
	}

	@Override
	public HashMap<SaveKey,String> getSave(){
		HashMap<SaveKey, String> save = new HashMap<>();
		save.put(PlayerSave.CONTENT, this.content);
		return save;
	}

	@Override
	public void load(MapProperties data) {
		this.content = data.get(PlayerSave.CONTENT.getKey(), String.class);
	}
}
