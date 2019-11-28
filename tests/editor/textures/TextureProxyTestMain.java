package editor.textures;

import javax.swing.*;
import java.awt.*;

public class TextureProxyTestMain {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(new Dimension(322,322));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Texture t = new Texture(16,"assets/places/donjon10.png",6,16,117);
		TextureProxy content = new TextureProxy();
		//content.loadTexture("src/editor/texture/textureEditor");
		content.addTexture(64,"assets/monsters/019.png",4,0,15);

		try {
			Image img = content.getImage(10);
			Panneau panel = new Panneau(img);

			frame.add(panel,BorderLayout.CENTER);
			frame.setVisible(true);
		} catch (IllegalStateException e) {
			System.err.println("Aucune texture ne correspond au numéro donné");
		}
	}

}
