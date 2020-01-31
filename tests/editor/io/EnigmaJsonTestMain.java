package editor.io;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import general.enigmas.Advice;
import general.enigmas.Enigma;
import general.enigmas.condition.Activated;
import general.enigmas.condition.HaveInHands;
import general.enigmas.operation.Summon;
import general.enigmas.operation.Unlock;
import general.entities.items.Door;
import general.entities.items.Switch;
import general.entities.players.Player;
import general.map.MapTestScreenCell;
import general.map.model.Case;
import general.save.enigmas.EnigmaJsonReader;
import general.save.enigmas.EnigmaJsonWriter;
import general.utils.IDFactory;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * Main écriture et lecture
 * @version 2.0
 */
public class EnigmaJsonTestMain {

	public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
		ArrayList<Enigma> enigmas = new ArrayList<Enigma>();
		IDFactory idf = IDFactory.getInstance();
		Switch s1 = new Switch();
		Switch s2 = new Switch();
		Switch s3 = new Switch();
		Switch s4 = new Switch();
		Door d1 = new Door();
		Door d2 = new Door();
		Door d3 = new Door();
		Player p1 = new Player();
		Player p2 = new Player();
		Case c1 = new Case();
		MapTestScreenCell cell = new MapTestScreenCell(new TiledMapTileLayer(10, 10, 0, 0),9);
		cell.setTile(new TiledMapTile() {
			@Override
			public int getId() {
				return 0;
			}

			@Override
			public void setId(int i) {

			}

			@Override
			public BlendMode getBlendMode() {
				return null;
			}

			@Override
			public void setBlendMode(BlendMode blendMode) {

			}

			@Override
			public TextureRegion getTextureRegion() {
				return null;
			}

			@Override
			public void setTextureRegion(TextureRegion textureRegion) {

			}

			@Override
			public float getOffsetX() {
				return 0;
			}

			@Override
			public void setOffsetX(float v) {
			}

			@Override
			public float getOffsetY() {
				return 0;
			}

			@Override
			public void setOffsetY(float v) {

			}

			@Override
			public MapProperties getProperties() {
				return null;
			}
		});

		s1.setID(idf.newID(s1));
		s2.setID(idf.newID(s2));
		s3.setID(idf.newID(s3));
		s4.setID(idf.newID(s4));
		d1.setID(idf.newID(d1));
		d2.setID(idf.newID(d2));
		d3.setID(idf.newID(d3));
		p1.setID(idf.newID(p1));
		p2.setID(idf.newID(p2));
		c1.setID(idf.newID(c1));

		Enigma e = new Enigma("enigme","oui c'est une énigme");
		e.addAdvice(new Advice("advice1"));
		e.addAdvice(new Advice("advice2"));
		e.addCondition(new HaveInHands(s1));
		e.addCondition(new Activated(s2));
		e.addOperation(new Unlock(d1));
		e.addOperation(new Summon(d1,cell));

		Enigma e2 = new Enigma("enigme2","oui c'est une énigme");
		e2.addAdvice(new Advice("advice3"));
		e2.addAdvice(new Advice("advice4"));
		e2.addCondition(new HaveInHands(s3));
		e2.addCondition(new Activated(s4));
		e2.addOperation(new Unlock(d2));
		e2.addOperation(new Unlock(d3));

		enigmas.add(e);
		enigmas.add(e2);

		EnigmaJsonWriter.writeEnigmas("assets/files/enigma/test.json",enigmas);
		System.out.println("Ecriture");
		for (Enigma en: enigmas) {
			System.out.println(en.toLongString());
		}
		enigmas = EnigmaJsonReader.readEnigmas("assets/files/enigma/test.json");
		System.out.println("Lecture");
		for (Enigma en: enigmas) {
			System.out.println(en.toLongString());
		}
	}
}

