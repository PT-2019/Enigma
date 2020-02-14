package editor.menus.enimas.create.listeners;

import common.entities.special.MusicEditor;
import common.hud.EnigmaOptionPane;
import common.hud.EnigmaWindow;
import common.map.MapTestScreen;
import editor.menus.enimas.create.MusicPanel;
import editor.menus.enimas.create.Operations;
import game.EnigmaGame;
import game.screens.TestScreen;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Contrôleur pour afficher le choix de musique
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 6.0
 * @since 6.0
 */
public class MusicListener implements MouseListener {

	private Operations operations;

	public MusicListener(Operations ope) {
		this.operations = ope;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		String chose;
		if (this.operations == Operations.MAIN_MUSIC) {
			chose = EnigmaOptionPane.showMusicChoiceDialog(new EnigmaWindow());
		} else {
			chose = EnigmaOptionPane.showSoundChoiceDialog(new EnigmaWindow());
		}
		Object tmp;

		if (!chose.equals(EnigmaOptionPane.CANCEL)) {
			MusicEditor object = new MusicEditor(chose);
			object.setTemp(true); //temporaire

			tmp = e.getSource();
			if (tmp instanceof JLabel) {
				//pour avoir la factory de id
				MapTestScreen map = ((TestScreen) EnigmaGame.getInstance().getScreen()).getMap();
				MusicPanel panel = (MusicPanel) ((JLabel) tmp).getParent();
				map.getIdFactory().newID(object);
				panel.getPanelOperation().update(object);
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (e.getSource() instanceof JLabel) {
			JLabel label = (JLabel) e.getSource();
			label.setForeground(new Color(203, 64, 249));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getSource() instanceof JLabel) {
			JLabel label = (JLabel) e.getSource();
			label.setForeground(Color.BLACK);
		}
	}
}
