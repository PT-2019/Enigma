package starter.gameConfig.displayManagers.lowLevel;

import editor.hud.EnigmaPanel;

public interface DisplayManager {
    void refreshContent();
    void refreshRightBar();
    void refreshAll();
    EnigmaPanel getContent();
    EnigmaPanel getRightBar();
}
