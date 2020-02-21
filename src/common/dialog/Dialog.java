package common.dialog;

import java.util.ArrayList;

/**
 * Classe actuel du dialog
 */
public class Dialog {
	/**
	 * Nombre maximum de caractère par frame
	 */
	private static int MAX_CHAR = 150;
	/**
	 * Les différent texte du dialog
	 */
	private ArrayList<String> text;
	/**
	 * Pour savoir si il y a un choix
	 */
	private boolean isChoice;
	/**
	 * Les différent choix possible
	 */
	private String[] choice;

	private int index = 0;

	public Dialog(String content) {
		this.text = new ArrayList<>();
		this.isChoice = false;
		addText(content);
	}

	public Dialog(String content, String[] choice) {
		this.text = new ArrayList<>();
		this.isChoice = true;
		this.choice = choice;
		addText(content);
	}

	private void addText(String content) {
		if (content == null) return;
		if (content.length() <= MAX_CHAR) {
			this.text.add(content);
		} else {
			while (content.length() - MAX_CHAR * 2 > 0) {
				this.text.add(content.substring(0, MAX_CHAR));
				content = content.substring(MAX_CHAR);
			}
			this.text.add(content);
		}
	}

	public boolean isChoice() {
		return isChoice;
	}

	public String[] getChoice() {
		return choice;
	}

	public boolean isFinish() {
		return index == text.size();
	}

	/**
	 * A travers cette méthode on parcours 1 fois le dialogue
	 *
	 * @return le texte à afficher
	 */
	public String getCurrentText() {
		index++;
		return text.get(index - 1);
	}

	public void reset() {
		this.index = 0;
	}
}