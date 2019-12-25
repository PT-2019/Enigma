package editor.hud;

import javax.swing.JPanel;
import java.awt.Cursor;
import java.util.ArrayList;

/**
 * TODO: comment ResizeComponent and write Readme.md in editor.hud
 *
 * @author Jorys-Micke ALAÏS
 * @author Louka DOZ
 * @author Loic SENECAT
 * @author Quentin RAMSAMY-AGEORGES
 * @version 4.0
 * @since 3.0
 */
public class ResizeComponent extends JPanel {

	private Cursor cursor;
	private ArrayList<Resize> resizeManagers;

	public ResizeComponent(Cursor cursor) {
		super();
		this.cursor = cursor;
		this.setCursor(cursor);
		this.resizeManagers = new ArrayList<>();
	}

	public void addResizer(Resize r) {
		this.resizeManagers.add(r);
		super.addMouseMotionListener(r);
	}

	public void disableResize() {
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		for (Resize r : this.resizeManagers) r.disable();
	}

	public void enableResize() {
		this.setCursor(this.cursor);
		for (Resize r : this.resizeManagers) r.enable();
	}
}
