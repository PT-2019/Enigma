package api.keys;

import com.badlogic.gdx.Input.Keys;

public enum  CameraKeys implements EnigmaKeys {
    CAMERA_LEFT(Keys.LEFT, Keys.Q),
    CAMERA_RIGHT(Keys.RIGHT, Keys.D),
    CAMERA_UP(Keys.UP, Keys.Z),
    CAMERA_DOWN(Keys.DOWN, Keys.S),
    ;

    private final int key1;
    private final int key2;

    CameraKeys(int key1, int key2){
        this.key1 = key1;
        this.key2 = key2;
    }

    @Override
    public boolean isKey(int keyCode) {
        return keyCode == key1 || keyCode == key2;
    }

    @Override
    public int getKey1() {
        return key1;
    }

    @Override
    public int getKey2() {
        return key2;
    }
}
