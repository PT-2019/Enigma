package game.entity.item;

import api.entity.interfaces.AbstractItem;
import api.entity.interfaces.Content;
import api.entity.interfaces.Item;
import editor.enigma.Enigma;
import editor.entity.player.Player;
import editor.utils.textures.Texture;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Un panneau
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 *
 * @version 4.0
 * @since 2.0
 *
 * @see api.entity.interfaces.Item
 * @see api.entity.interfaces.Content
 * @see AbstractItem
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
}
