package game.entity.item;

import api.entity.AbstractItem;
import api.entity.types.Content;

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
public class Book extends AbstractItem implements Content {

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
}
