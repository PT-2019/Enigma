package editor.bibliotheque;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.utils.UIUtils;
import editor.window.Window;
import game.EnigmaGame;
import game.entity.MapLibgdx;
import game.screen.TestScreen;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.*;
import java.io.IOException;

public class DragAndDropDND extends TransferHandler implements DragGestureListener, DragSourceListener, DropTargetListener, Transferable {

	private final DataFlavor[] dataFlavor;
	private final Window window;
	private Object dataTransferObject;

	public DragAndDropDND(Window window){
		try {
			dataFlavor = new DataFlavor[]{new DataFlavor(DataFlavor.javaJVMLocalObjectMimeType)};
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("could not create data flavor");
		}

		//if os is windows then we do something about' cursor
		if(UIUtils.isWindows) {
			this.window = window;
		} else {
			this.window = null;
		}
	}

	//dépôt de l'élément déplacé sur target

	@Override
	public void drop(DropTargetDropEvent dtde) {
		dtde.acceptDrop(dtde.getDropAction());

		try {
			//récupère l'object déplacé
			Object source = dtde.getTransferable().getTransferData(dataFlavor[0]);

			//on le transforme en label
			EntityContainer component = (EntityContainer) ((DragSourceContext) source).getComponent();

			if(EnigmaGame.getInstance() != null){
				//récupère la map
				MapLibgdx m = ((TestScreen) EnigmaGame.getCurrentScreen()).getMap();
				//lui transmet le 'label' déplacé
				m.load(component.getContent(), dtde.getLocation());
			} else {
				Gdx.app.error(this.getClass().toString(),"Game isn't loaded !");
				throw new IllegalStateException("Game isn't loaded !");
			}
		} catch (UnsupportedFlavorException| IOException ex) {
			Gdx.app.error(this.getClass().toString(),"failed dnd drop");
		}

		dtde.dropComplete(true);
	}

	//fin drag et drop

	@Override
	public void dragDropEnd(DragSourceDropEvent dsde){
		if(window != null) //reset cursor
			this.window.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}

	//Ici les méthodes sur l'object déplacé

	@Override
	public void dragEnter(DragSourceDragEvent dsde) {}

	@Override
	public void dragExit(DragSourceEvent dse) {}

	@Override
	public void dragOver(DragSourceDragEvent dsde) {
		dataTransferObject = dsde.getSource();
	}

	@Override
	public void dropActionChanged(DragSourceDragEvent dsde) {}

	//Ici les méthodes du drag and drop sur l'object target (cible dans laquelle on dépose)

	@Override
	public void dragEnter(DropTargetDragEvent dtde) {}

	@Override
	public void dragExit(DropTargetEvent dte) {}

	@Override
	public void dragOver(DropTargetDragEvent dtde) {
		dtde.acceptDrag(dtde.getDropAction());
	}

	@Override
	public void dropActionChanged(DropTargetDragEvent dtde) {
		dtde.acceptDrag(dtde.getDropAction());
	}

	// Utils

	@Override@NotNull
	public Object getTransferData(DataFlavor flavor) {
		if (isDataFlavorSupported(flavor)) {
			return dataTransferObject;
		}
		throw new IllegalStateException("not valid data flavor");
	}

	@Override
	public DataFlavor[] getTransferDataFlavors() { return dataFlavor; }

	@Override
	public boolean isDataFlavorSupported(DataFlavor flavor) {
		return flavor.isMimeTypeEqual(DataFlavor.javaJVMLocalObjectMimeType);
	}

	@Override
	public void dragGestureRecognized(DragGestureEvent dge) {
		if(window != null) {
			this.window.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
		dge.startDrag(null, this, this);
	}

	//handler

	private Image image;

	@Override
	public boolean canImport(JComponent comp, DataFlavor[] flavor) {
		if (!(comp instanceof JLabel) && !(comp instanceof AbstractButton)) {
			return false;
		}
		for (DataFlavor item : flavor) {
			for (DataFlavor value : dataFlavor) {
				if (item.equals(value)) {
					return true;
				}
			}
		}

		return false;
	}

	@Override
	public boolean importData(JComponent comp, Transferable t) {
		if (comp instanceof JLabel) {
			JLabel label = (JLabel) comp;
			if (t.isDataFlavorSupported(dataFlavor[0])) {
				try {
					image = (Image) t.getTransferData(dataFlavor[0]);
					ImageIcon icon = new ImageIcon(image);
					label.setIcon(icon);
					return true;
				} catch (UnsupportedFlavorException | IOException ignored) {
				}
			}
		} else if (comp instanceof AbstractButton) {
			AbstractButton button = (AbstractButton) comp;
			if (t.isDataFlavorSupported(dataFlavor[0])) {
				try {
					image = (Image) t.getTransferData(dataFlavor[0]);
					ImageIcon icon = new ImageIcon(image);
					button.setIcon(icon);
					return true;
				} catch (UnsupportedFlavorException | IOException ignored) {
				}
			}
		}
		return false;
	}

	@Override
	public Transferable createTransferable(JComponent comp) {
		// Clear
		image = null;

		if (comp instanceof JLabel) {
			JLabel label = (JLabel) comp;
			Icon icon = label.getIcon();
			if (icon instanceof ImageIcon) {
				image = ((ImageIcon) icon).getImage();
				return this;
			}
		} else if (comp instanceof AbstractButton) {
			AbstractButton button = (AbstractButton) comp;
			Icon icon = button.getIcon();
			if (icon instanceof ImageIcon) {
				image = ((ImageIcon) icon).getImage();
				return this;
			}
		}
		return null;
	}
}
