package desktopLauncher;

import editor.api.Application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LauncherManagement implements ActionListener {

    private Application app;

    public LauncherManagement(Application app){
        this.app = app;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        DesktopLauncher.startApp(this.app);
    }
}
