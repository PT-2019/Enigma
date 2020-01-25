package game.entity.item;

import api.entity.AbstractItem;
import api.entity.types.Content;
import api.enums.TypeEntite;
import editor.utils.lang.GameLanguage;
import editor.utils.lang.fields.GameFields;

import java.util.EnumMap;

/**
 * Un panneau
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0
 * @see api.entity.Item
 * @see api.entity.types.Content
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
	public EnumMap<TypeEntite, Boolean> getImplements() {
		EnumMap<TypeEntite, Boolean> imp = TypeEntite.emptyMap();
		imp.put(TypeEntite.CONTENT, true);

		imp.put(TypeEntite.ITEM, true);
		imp.put(TypeEntite.NEED_CONTAINER, true);
		imp.put(TypeEntite.ENIGMA_CONTAINER, true);
		return imp;
	}

	@Override
	public String getReadableName() {
		return GameLanguage.gl.get(GameFields.PANE);
	}
}
