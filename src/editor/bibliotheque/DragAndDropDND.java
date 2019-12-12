package editor.bibliotheque;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.utils.UIUtils;
import editor.window.Window;
import game.EnigmaGame;
import game.entity.MapLibgdx;
import game.screen.TestScreen;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.*;
import java.io.IOException;
import java.lang.invoke.VarHandle;

public class DragAndDropDND implements DragGestureListener, DragSourceListener, DropTargetListener, Transferable {

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
			MenuScreen.CustomLabel component = (MenuScreen.CustomLabel) ((DragSourceContext) source).getComponent();

			if(EnigmaGame.getInstance() != null){
				//récupère la map
				MapLibgdx m = ((TestScreen) EnigmaGame.getCurrentScreen()).getMap();
				//lui transmet le 'label' déplacé
				m.load(component.getContent(), dtde.getLocation());
			} else {
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
}
