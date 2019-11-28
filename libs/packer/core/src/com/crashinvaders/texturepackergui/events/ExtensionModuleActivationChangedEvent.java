package com.crashinvaders.texturepackergui.events;

import com.crashinvaders.texturepackergui.controllers.extensionmodules.ExtensionModuleController;

public class ExtensionModuleActivationChangedEvent {
    private final ExtensionModuleController moduleController;

    public ExtensionModuleActivationChangedEvent(ExtensionModuleController moduleController) {
        this.moduleController = moduleController;
    }

    public ExtensionModuleController getModuleController() {
        return moduleController;
    }
}
