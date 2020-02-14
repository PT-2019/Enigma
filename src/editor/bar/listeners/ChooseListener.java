package editor.bar.listeners;

import com.badlogic.gdx.math.Vector2;
import common.data.MapData;
import common.entities.GameObject;
import common.entities.special.MusicEditor;
import common.hud.EnigmaOptionPane;
import common.hud.EnigmaWindow;
import common.map.MapObject;
import common.map.MapObjects;
import common.map.MapTestScreen;
import editor.menus.enimas.create.MusicPanel;
import editor.menus.enimas.create.listeners.MusicListener;
import game.EnigmaGame;
import game.screens.TestScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class ChooseListener extends MenuListener {

    public ChooseListener(EnigmaWindow window){
        super(window);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String choice = EnigmaOptionPane.showMusicChoiceDialog(window);
        if (!choice.equals(MusicListener.CANCEL)){
            MapTestScreen map = ((TestScreen) EnigmaGame.getInstance().getScreen()).getMap();

            MusicEditor object = new MusicEditor(choice);
            map.getIdFactory().newID(object);
            object.setMainMusic(true);
            object.setStarter(true);

            //on cherche dans la map si il y a pas une autre mainmusic starter pour la virer
            MapObjects data = map.getEntities();
            ArrayList<MusicEditor> musics = data.getAllObjectsByClass(MusicEditor.class,true);
            //lorsqu'on supprime une entité on supprime toute les entités au même endroit donc
            // les musiques sont toutes placées au même endroit donc on va toutes les virer

            for (MusicEditor music: musics) {
                if (music.isMainMusic() && music.isStarter()){
                    musics.remove(music);
                    //cela va détruire toutes les musiques
                    map.removeEntity(music);
                }
            }
            //on remet nos chères musiques sur la map
            for (MusicEditor music: musics) {
                map.set(music,new Vector2(0,0));
            }

            //on met la nouvelle musique
            map.set(object,new Vector2(0,0));
        }
    }
}

