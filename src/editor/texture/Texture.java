package editor.texture;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;

/**
 * La classe Texture permet de chargé des images et de les décomposer
 * Cette classe sont fonctionnement est lié avec TextureType
 *@see TextureType
 */
public class Texture{
	/**
	 * L'image qui va être chargé sera stocké ici
	 */
	private BufferedImage buffer;
	/**
	 * Longeur des sous-textures
	 */
	private int width;

	/**
	 * Hauteur des sous-textures
	 */
	private int height;

	/**
	 * @param width Longeur des sous-textures désiré
	 * @param height Hauteur des sous-textures désiré
	 */
	public Texture(int width,int height) {
		buffer = null;
		this.width = width;
		this.height = height;
	}

	/**
	 * Cette méthode va découper, une image charger en fonction de la TextureType
	 * @see TextureType
	 * @throws IllegalStateException Fichier de l'image impossible à charger
	 * @throws IllegalStateException Valeur trop élevé, soit les dimensions width et
	 * height, soit les attributs col et row de la TextureType
	 * @param type
	 * @return Sous-Image décrite par la TextureType
	 */
	public Image getImage(TextureType type) {

		if (buffer==null) {			
			ImageIcon image = new ImageIcon(type.getPath());

			if (image.getIconHeight() < 0 || image.getIconWidth() < 0) {
				throw new IllegalStateException("File not found. Bad Path.");
			}

			buffer = new BufferedImage(image.getIconWidth(),image.getIconHeight(),BufferedImage.TYPE_3BYTE_BGR);

			Graphics g = buffer.getGraphics();

			g.drawImage(image.getImage(),0,0,new JLabel());
	}

		if (type.getRow()*width >= buffer.getWidth() || type.getCol()*height >= buffer.getHeight()) {
			throw new IllegalStateException("The arguments of TextureType are to big");
		}

		return buffer.getSubimage(type.getRow()*width,type.getCol()*height,width,height);
	}

	//main for the test
	public static void main(String[] args) {
		JFrame fenetre = new JFrame();

		fenetre.setSize(new Dimension(322,322));
		fenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		Texture t = new Texture(64,64);

		TextureType type = Ratata.DROITE_1;//enum donc pas instanciable

		Panneau panel = new Panneau(t.getImage(type));

		fenetre.add(panel,BorderLayout.CENTER);

		fenetre.setVisible(true);
	}

	public String getPosition() {
		return null;
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