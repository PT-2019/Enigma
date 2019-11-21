package editor.texture;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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

	/**
	 * Charge en mémoire toutes les textures décrites dans le fichier donné en argument.
	 * Voir le format du fichier en quesiton.
	 * @param pathFile
	 * @throws NumberFormatException causé par un fichier mal conçu
	 */
	public void loadTexture(String pathFile){
		BufferedReader buff;
		FileReader file;
		String line="";
		String tmpString="";
		String[] info = new String[5];
		char currentChar;

		try {

			file = new FileReader(pathFile);
			buff = new BufferedReader(file);

			while ((line = buff.readLine()) != null ){

				for(int j = 0,i = 0; i < line.length();i++){
					currentChar = line.charAt(i);

					if (currentChar == ' ') {

						info[j]=tmpString;
						j++;
						tmpString="";
					}else{
						tmpString += currentChar;
					}
				}

				info[4] = tmpString;

				Texture t = new Texture(Integer.parseInt(info[1]),info[0],Integer.parseInt(info[2]),Integer.parseInt(info[3]),Integer.parseInt(info[4]));

				this.addTexture(t);

				tmpString="";
			}

			file.close();
			buff.close();

		}catch(IOException e){
			System.out.println(e.getMessage());
		}
	}

	//main for the test
	public static void main(String[] args) {
		JFrame frame = new JFrame();

		frame.setSize(new Dimension(322,322));
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		Texture t = new Texture(64,"assets/monsters/019.png",4,0,15);

		//Texture t = new Texture(16,"assets/places/donjon10.png",6,16,117);

		TextureProxy content = new TextureProxy();

		//content.loadTexture("src/editor/texture/textureEditor");

		content.addTexture(t);

		Image img = content.getImage(10);

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