package common.utils.textures;

import org.jetbrains.annotations.TestOnly;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import javax.swing.JComponent;

/**
 * Un panneau qui affiche une Image.
 * Utilisé pour tester que l'image est bien récupérée
 *
 * @version 1.0
 */
@TestOnly
final class Panneau extends JComponent {
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
