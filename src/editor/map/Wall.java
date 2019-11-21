package editor.map;

/**
 * Le mur d'une pièce
 * @see Room
 */
public class Wall {

	/** la pièce que si charge de l'afficher */
	private Room printer;
	/** les cases qui le compose */
	private Case[] cases;

	/**
	 * Crée un mur
	 * @param printer la pièce que si charge de l'afficher
	 * @param wall les cases qui le compose
	 */
	public Wall(Room printer, Case[] wall){
		this.printer = printer;
		this.cases = wall;
	}

}
