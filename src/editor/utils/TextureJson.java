package editor.utils;

import org.jetbrains.annotations.TestOnly;

import javax.swing.JFrame;
import javax.swing.JComponent;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.util.HashMap;

/**
 * Charge une sous-texture depuis son nom et son fichier .atlas
 *
 * @version 2.0
 */
public class TextureJson {

	/**
	 * garde les anciennes textures pour éviter de devoir le recharger
	 * String = chemin du .atlas
	 * JSonFile = fichier .atlas
	 */
	private static HashMap<String, JsonFile> loadedTextures = new HashMap<>();

	/**
	 * Renvoi l'image correspond a la sous-texture name dans file.
	 *
	 * @param name nom de la clef (=son nom) de la sous-texture
	 * @param file le chemin du fichier .atlas
	 * @return la sous-image voulue
	 * @since 2.0
	 */
	public static Image getTexture(String name, String file){
		if(!loadedTextures.containsKey(file)){
			JsonFile json = JsonReader.importJson(file);
			TextureJson.loadedTextures.put(file, json);
		}
		return loadedTextures.get(file).getTexture(name);
	}

	/**
	 * Charge une sous-texture d'un fichier atlas pour vérifier que ça marche
	 */
	@TestOnly
	public static void main(String[] ignore) {
		JFrame frame = new JFrame();
		frame.setSize(new Dimension(322,322));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//on met a gauche le nom de la sous-texture et a droite son fichier
		Panneau panel = new Panneau(getTexture("tile723", "assets/test.atlas"));

		frame.add(panel,BorderLayout.CENTER);
		frame.setVisible(true);
	}

	/**
	 * Un panneau qui affiche une Image.
	 * Utilisé pour tester que l'image est bien récupérée
	 *
	 * @version 1.0
	 */
	@TestOnly
	static final class Panneau extends JComponent {
		private Image img;

		Panneau(Image i){
			img = i;
		}

		@Override
		protected void paintComponent(Graphics pinceau) {
			Graphics secondPinceau = pinceau.create();
			if (this.isOpaque()) {
				// obligatoire : on repeint toute la surface avec la couleur de fond
				secondPinceau.setColor(this.getBackground());
				secondPinceau.fillRect(0, 0, this.getWidth(), this.getHeight());
			}
			// maintenant on dessine ce que l'on veut
			secondPinceau.setColor(Color.GREEN);
			secondPinceau.drawImage(img,0,0,this);
		}
	}
}
