package editor.bibliotheque;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.*;

public class DragAndDropDND implements DragGestureListener, DragSourceListener, DropTargetListener, Transferable {

	private static final DataFlavor[] dataflavor = {null};
	private Object object;

	static {
		try {
			dataflavor[0] = new DataFlavor(
					DataFlavor.javaJVMLocalObjectMimeType);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Transferable methods.
	public Object getTransferData(DataFlavor flavor) {
		if (flavor.isMimeTypeEqual(DataFlavor.javaJVMLocalObjectMimeType)) {
			return object;
		} else {
			return null;
		}
	}

	public DataFlavor[] getTransferDataFlavors() {
		return dataflavor;
	}

	public boolean isDataFlavorSupported(DataFlavor flavor) {
		return flavor.isMimeTypeEqual(DataFlavor.javaJVMLocalObjectMimeType);
	}

	// DragGestureListener method.
	public void dragGestureRecognized(DragGestureEvent dge) {
		dge.startDrag(null, this, this);
	}

	// DragSourceListener methods.
	public void dragDropEnd(DragSourceDropEvent dsde) {
		System.out.println("DragSourceDropEvent Fired");
	}

	public void dragEnter(DragSourceDragEvent dsde) {
		System.out.println("DragSourceDragEvent Fired");
	}

	public void dragExit(DragSourceEvent dse) {
		System.out.println("DragSourceEvent Fired");
	}

	public void dragOver(DragSourceDragEvent dsde) {
		object = dsde.getSource();
	}

	public void dropActionChanged(DragSourceDragEvent dsde) {
		System.out.println("DragSourceDragEvent Fired");
	}

	// DropTargetListener methods.
	public void dragEnter(DropTargetDragEvent dtde) {
	}

	public void dragExit(DropTargetEvent dte) {
	}

	public void dragOver(DropTargetDragEvent dtde) {
		dropTargetDrag(dtde);
	}

	public void dropActionChanged(DropTargetDragEvent dtde) {
		dropTargetDrag(dtde);
	}

	private void dropTargetDrag(DropTargetDragEvent dtde) {
		dtde.acceptDrag(dtde.getDropAction());
	}


	//PB ICI POUR LA COPIE --> VOIR SI AVOIR A PASSER PAR UN TRANSFER HANDLER OBLIGATOIRE
	public void drop(DropTargetDropEvent dtde) {
		dtde.acceptDrop(dtde.getDropAction());
		try {
			//get source and target
			Object source = dtde.getTransferable().getTransferData(dataflavor[0]);
			Object target = dtde.getSource();

			//get transfer object (label)
			JLabel component = (JLabel) ((DragSourceContext) source).getComponent();

			Container oldContainer = component.getParent();
			Container newContainer = (Container) ((DropTarget) target).getComponent();

			JLabel copy = new JLabel();
			copy.setIcon(component.getIcon());

			newContainer.add(copy);

			oldContainer.validate();
			oldContainer.repaint();
			newContainer.validate();
			newContainer.repaint();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		dtde.dropComplete(true);
	}


}
