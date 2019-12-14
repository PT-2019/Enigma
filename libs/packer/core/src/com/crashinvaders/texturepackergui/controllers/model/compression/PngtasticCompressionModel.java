package com.crashinvaders.texturepackergui.controllers.model.compression;

import com.badlogic.gdx.utils.*;
import com.crashinvaders.common.statehash.StateHashUtils;
import com.crashinvaders.texturepackergui.controllers.model.PngCompressionType;

import java.io.StringWriter;

public class PngtasticCompressionModel extends PngCompressionModel {

    private int level = 5;
    private boolean removeGamma = false;

    public PngtasticCompressionModel() {
        super(PngCompressionType.PNGTASTIC);
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isRemoveGamma() {
        return removeGamma;
    }

    public void setRemoveGamma(boolean removeGamma) {
        this.removeGamma = removeGamma;
    }

    @Override
    public String serializeState() {
        StringWriter buffer = new StringWriter();
        try {
            Json json = new Json();
            json.setWriter(new JsonWriter(buffer));
            json.writeObjectStart();
            json.writeValue("level", level);
            json.writeValue("removeGamma", removeGamma);
            json.writeObjectEnd();
            return buffer.toString();
        } finally {
            StreamUtils.closeQuietly(buffer);
        }
    }

    @Override
    public void deserializeState(String data) {
        if (data == null) return;

        JsonValue jsonValue = new JsonReader().parse(data);
        level = jsonValue.getInt("level", level);
        removeGamma = jsonValue.getBoolean("removeGamma", removeGamma);
    }

    @Override
    public int computeStateHash() {
        return StateHashUtils.computeHash(super.computeStateHash(), level, removeGamma);
    }
}
