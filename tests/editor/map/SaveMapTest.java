package editor.map;

import editor.texture.TextureArea;
import editor.texture.TextureProxy;

public class SaveMapTest {
    public static void main(String argv[]) {
        Map map = new Map(30,20);
        Room room1 = new Room();
        Room room2 = new Room();

        TextureProxy p =  new TextureProxy();

        TextureArea a = new TextureArea(64,"assets/monsters/019.png",4,1,16);
        TextureArea b = new TextureArea(16,"assets/monsters/023s.png",4,17,117);
        p.addTexture(a);
        p.addTexture(b);

        SaveMap save = new SaveMap(p.getTextureArea(),map);

        p.getImage(5);
        p.getImage(19);

        map.addRoom(1,3, room1);

        map.addRoom(11,2, room2);

        map.render();

        save.saveMap("assets/map/result.tmx");
    }
}
