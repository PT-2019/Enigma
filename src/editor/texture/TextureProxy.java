package editor.texture;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.util.*;

/**
 * La classe Texture permet de chargé des images et de les décomposer
 *
 */
public class TextureProxy{

	/**
	 * Toutes les textures déjà chargé sont stocké ici
	 * @see Texture
	 */
	private ArrayList<Texture> textures;

	public TextureProxy(){
		textures = new ArrayList<Texture>();
	}

	/**
	 * Cette méthode va découper, une image charger en fonction du paramètre
	 * @param id_texture
	 * @return Sous-Image décrite par la TextureType
	 */
	public Image getImage(int id_texture) {
		Image img = null;

		for (Texture t : textures) {
			if(t.getMax() >= id_texture && t.getMin() <= id_texture){
				img = t.getImage(id_texture);
			}
		}

		return img;
	}

	public void addTexture(Texture texture){
		boolean exist = false;
		//on vérifie si il y a pas déjà la texture
		for (Texture t : textures) {
			if (t.getPath().equals(texture.getPath())) {
				exist = true;
				break;
			}
		}

		if (!exist) {
			textures.add(texture);
		}
	}

	//main for the test
	public static void main(String[] args) {
		JFrame frame = new JFrame();

		frame.setSize(new Dimension(322,322));
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		//Texture t = new Texture(64,"assets/monsters/019.png",4,0,15);

		Texture t = new Texture(16,"assets/places/donjon10.png",6,16,117);

		TextureProxy content = new TextureProxy();

		content.addTexture(t);

		Image img = content.getImage(16);

		if (img == null){
			System.out.println("Aucune texture ne correspond au numéro donné");
		}

		Panneau panel = new Panneau(img);

		frame.add(panel,BorderLayout.CENTER);

		frame.setVisible(true);
	}
}

//test class
class Panneau extends JComponent{
	private Image img;

	public Panneau(Image i){
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