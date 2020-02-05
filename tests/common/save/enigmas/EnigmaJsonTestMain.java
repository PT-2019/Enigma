package common.save.enigmas;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import common.enigmas.Advice;
import common.enigmas.Enigma;
import common.enigmas.condition.Activated;
import common.enigmas.condition.HaveInHands;
import common.enigmas.operation.Summon;
import common.enigmas.operation.Unlock;
import common.entities.items.Door;
import common.entities.items.Switch;
import common.entities.players.Player;
import common.map.MapTestScreenCell;
import common.map.model.Case;
import common.save.enigmas.EnigmaJsonReader;
import common.save.enigmas.EnigmaJsonWriter;
import common.utils.IDFactory;

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
		IDFactory idf = new IDFactory();
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

		idf.newID(s1);
		idf.newID(s2);
		idf.newID(s3);
		idf.newID(s4);
		idf.newID(d1);
		idf.newID(d2);
		idf.newID(d3);
		idf.newID(p1);
		idf.newID(p2);
		idf.newID(c1);

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

