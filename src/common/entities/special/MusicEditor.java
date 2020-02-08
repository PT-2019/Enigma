package common.entities.special;

import api.libgdx.utils.Border;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.utils.BooleanArray;
import common.entities.types.AbstractGameObject;
import common.language.GameFields;
import common.language.GameLanguage;
import common.save.entities.PlayerSave;
import common.save.entities.SaveKey;
import data.TypeEntity;

import java.util.EnumMap;
import java.util.HashMap;

/**
 * Cette classe permet de stocker une musique en tant que game object,
 * elle permet d'avoir toute les informations nécessaire pour lancer les musiques du jeu
 */
public class MusicEditor extends AbstractGameObject {

    /**
     * Chemin de la musique
     */
    private String path;

    /**
     * Pour savoir si c'est un son à lancer en arrière plan ou pas
     */
    private boolean isMainMusic;

    /**
     * Nom de la musique qui se fait à partir du path
     */
    private String name;

    /**
     * Pour savoir si c'est la musique qui démarre au début du jeu
     */
    private boolean isStarter;

    /**
     * Dossier où sont stocker toute les musiques
     */
    public final static String PATH = "assets/files/music/";

    public final static String EXTENSION = ".mp3";

    public MusicEditor(){
        super(-1);
    }

    public MusicEditor(String path){
        this.path = MusicEditor.PATH + path + MusicEditor.EXTENSION;
        this.name = path;
        this.isMainMusic = false;
        this.isStarter = false;
    }

    public MusicEditor(String path,int id) {
        super(id);
        this.path = MusicEditor.PATH + path + MusicEditor.EXTENSION;
        this.name = path;
    }

    @Override
    public EnumMap<TypeEntity, Boolean> getImplements() {
        return TypeEntity.emptyMap();
    }

    @Override
    public String getReadableName() {
        return GameLanguage.gl.get(GameFields.MUSIC);
    }

    @Override
    public HashMap<SaveKey,String> getSave(){
        HashMap<SaveKey, String> save = new HashMap<>();
        save.put(PlayerSave.PATH,this.path);
        save.put(PlayerSave.NAME,this.name);
        save.put(PlayerSave.MAIN,String.valueOf(this.isMainMusic));
        save.put(PlayerSave.STARTER,String.valueOf(this.isStarter));
        return save;
    }

    @Override
    public void load(MapProperties data) {
        this.path = data.get(PlayerSave.PATH.getKey(), String.class);
        this.name = data.get(PlayerSave.NAME.getKey(), String.class);
        this.isMainMusic = Boolean.parseBoolean(data.get(PlayerSave.MAIN.getKey(), String.class));
        this.isStarter = Boolean.parseBoolean(data.get(PlayerSave.STARTER.getKey(), String.class));
    }

    public void setPathMusic(String path){
        this.path = path;
    }

    public void setMainMusic(boolean b){
        this.isMainMusic = b;
    }

    public void setStarter(boolean b){
        this.isStarter = b;
    }

    public boolean isMainMusic() {
        return isMainMusic;
    }

    public boolean isStarter() {
        return isStarter;
    }

    public String getPath() {
        return path;
    }
}
