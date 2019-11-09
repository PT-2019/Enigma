import javax.swing.*;
import java.awt.*;
import java.awt.image.*;

public class Texture{

	private BufferedImage buffer;

	

	private int dim;

	public Texture(String path,int dim) {
		buffer = null;
		this.path = path;
		this.dim = dim;
	}

	public Image getImage(TextureType type) {

		if (buffer==null) {			
			ImageIcon image = new ImageIcon(path);

			if (image.getIconHeight() < 0 || image.getIconWidth() < 0) {
				throw new IllegalStateException("File not found. Bad Path.");
			}

			buffer = new BufferedImage(image.getIconWidth(),image.getIconHeight(),BufferedImage.TYPE_3BYTE_BGR);

			Graphics g = buffer.getGraphics();

			g.drawImage(image.getImage(),0,0,new JLabel());
		}

		if (type.getRow()*dim >= buffer.getWidth() || type.getCol()*dim >= buffer.getHeight()) {
			throw new IllegalStateException("The arguments of TextureType are to big");
		}

		return buffer.getSubimage(type.getRow()*dim,type.getCol()*dim,dim,dim);
	}

	//main for the test
	public static void main(String[] args) {
		JFrame fenetre = new JFrame();

		fenetre.setSize(new Dimension(322,322));

		Texture t = new Texture("019.png",64);

		TextureType type = TextureType.TERRE;

		type.setRow(2);

		type.setCol(4);

		Panneau panel = new Panneau(t.getImage(type));

		fenetre.add(panel,BorderLayout.CENTER);

		fenetre.setVisible(true);
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