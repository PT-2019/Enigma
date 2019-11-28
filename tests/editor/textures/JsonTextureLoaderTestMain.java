package editor.textures;

import org.jetbrains.annotations.TestOnly;

import javax.swing.*;
import java.awt.*;

public class JsonTextureLoaderTestMain {

	/**
	 * Charge une sous-texture d'un fichier atlas pour vérifier que ça marche
	 */
	@TestOnly
	public static void main(String[] ignore) {
		JFrame frame = new JFrame();
		frame.setSize(new Dimension(322,322));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//on met a gauche le nom de la sous-texture et a droite son fichier
		Panneau panel = new Panneau(JsonTextureLoader.getTexture("tile723", "assets/test.atlas"));

		frame.add(panel,BorderLayout.CENTER);
		frame.setVisible(true);
	}

}
