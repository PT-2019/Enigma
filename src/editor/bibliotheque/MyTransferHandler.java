package editor.bibliotheque;

import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.datatransfer.Transferable;


public class MyTransferHandler extends TransferHandler {

    public MyTransferHandler(String property) {
        super(property);
    }

    @Override
    public boolean importData(TransferSupport support) {
        return super.importData(support);
    }

    @Override
    public boolean canImport(TransferSupport support) {
        return super.canImport(support);
    }

    @Override
    public int getSourceActions(JComponent c) {
        return super.getSourceActions(c);
    }

    @Nullable
    @Override
    protected Transferable createTransferable(JComponent c) {
        return super.createTransferable(c);
    }
}

