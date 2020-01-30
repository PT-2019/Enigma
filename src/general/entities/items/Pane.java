package general.entities.items;

import datas.TypeEntity;
import general.entities.types.AbstractItem;
import general.entities.types.Content;
import general.language.GameFields;
import general.language.GameLanguage;

import java.util.EnumMap;

/**
 * Un panneau
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0
 * @see general.entities.Item
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
}
