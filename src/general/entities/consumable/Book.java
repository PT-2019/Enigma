package general.entities.consumable;

import datas.TypeEntity;
import general.entities.types.AbstractConsumable;
import general.entities.types.Content;
import general.language.GameFields;
import general.language.GameLanguage;

import java.util.EnumMap;

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
}
