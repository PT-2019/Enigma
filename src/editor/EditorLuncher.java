package editor;

import editor.listener.CloseWindowLibgdxApplication;
import editor.listener.LoadGameLibgdxApplication;

import javax.swing.JButton;
import javax.swing.JFrame;

public class EditorLuncher extends JFrame {

	public EditorLuncher(int width, int height) {
		super();
		this.setSize(width,height);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new CloseWindowLibgdxApplication(null));//pas d'application à fermer

		//un exemple : ce bouton charge l'application libgdx si on clique
		JButton next = new JButton("charger le jeu");
		this.add(next);
		next.addActionListener(new LoadGameLibgdxApplication(this));
	}

	public void start() {
		this.setVisible(true);
	}
}