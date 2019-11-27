package editor.utils;

import javax.swing.JFrame;
import javax.swing.JComponent;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.util.HashMap;

public class TextureJson {

	private static HashMap<String, JsonFile> loadedTextures;

	static {
		TextureJson.loadedTextures = new HashMap<>();
	}

	public static Image getTexture(String name, String file){
		if(!loadedTextures.containsKey(file)){
			JsonFile json = JsonReader.importJson(file);
			TextureJson.loadedTextures.put(file, json);
		}
		return loadedTextures.get(file).getTexture(name);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(new Dimension(322,322));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Panneau panel = new Panneau(getTexture("tile912", "assets/test.atlas"));//ask for a texture by name

		frame.add(panel,BorderLayout.CENTER);
		frame.setVisible(true);
	}

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
