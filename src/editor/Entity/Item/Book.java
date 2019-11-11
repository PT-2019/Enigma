package editor.Entity.Item;

import editor.Entity.Interface.Content;
import editor.Entity.Interface.Item;
import editor.texture.Texture;

public class Book implements Item, Content {
    @Override
    public Texture getTexture() {
        return null;
    }

    @Override
    public void showDialog() {}

    @Override
    public void addContent(String content) {

    }

    @Override
    public String getContent() {
        return null;
    }
}
