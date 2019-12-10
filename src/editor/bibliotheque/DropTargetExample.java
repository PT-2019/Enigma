package editor.bibliotheque;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.dnd.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DropTargetExample implements DragGestureListener, DragSourceListener, DropTargetListener, Transferable {
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
            Object source = dtde.getTransferable().getTransferData(dataflavor[0]);
            Object target = dtde.getSource();
            Component component = ((DragSourceContext) source).getComponent();
            Container oldContainer = component.getParent();
            Container newContainer = (Container) ((DropTarget) target).getComponent();
            newContainer.add(component);
            oldContainer.validate();
            oldContainer.repaint();
            newContainer.validate();
            newContainer.repaint();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        dtde.dropComplete(true);
    }

    public static void main(String[] arg) {
        Button button = new Button("Drag Button");
        Frame sourceFrame = new Frame("Source Frame");
        JPanel a = new JPanel();
        a.setBackground(Color.BLACK);
        JPanel b = new JPanel();
        b.setBackground(Color.GREEN);
        sourceFrame.setLayout(new BorderLayout());
        a.setPreferredSize(new Dimension(100, 100));
        b.setPreferredSize(new Dimension(100, 100));
        sourceFrame.add(a, BorderLayout.NORTH);
        sourceFrame.add(b, BorderLayout.SOUTH);
        sourceFrame.add(button);
        //Frame targetFrame = new Frame("Target Frame");
        //targetFrame.setLayout(new FlowLayout());
        sourceFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
            /*targetFrame.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent we) {
                    System.exit(0);
                }
            });*/
        DropTargetExample DragSourceExample = new DropTargetExample();
        DragSource dragSource = new DragSource();
        DropTarget sourceDropTarget = new DropTarget(a,
                DnDConstants.ACTION_MOVE, DragSourceExample);
        DropTarget targetDropTarget = new DropTarget(b,
                DnDConstants.ACTION_COPY, DragSourceExample);
        DragGestureRecognizer draggesturerecognizer = dragSource
                .createDefaultDragGestureRecognizer(button,
                        DnDConstants.ACTION_COPY, DragSourceExample);
        sourceFrame.setSize(500, 500);
        //targetFrame.setBounds(220, 200, 100, 60);
        sourceFrame.setVisible(true);
        //targetFrame.setVisible(true);
    }
}
