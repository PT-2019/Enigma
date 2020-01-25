package editor.io;

import editor.enigma.Advice;
import editor.enigma.Enigma;
import editor.enigma.condition.Activated;
import editor.enigma.condition.HaveInHands;
import editor.enigma.operation.Summon;
import editor.enigma.operation.Unlock;
import editor.entity.IDFactory;
import editor.entity.Player;
import editor.utils.json.EnigmaJsonReader;
import editor.utils.json.EnigmaJsonWriter;
import editor.utils.map.Case;
import game.entity.item.Door;
import game.entity.item.Switch;

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
		//e.addOperation(new Summon(p2,c1));
		e.addOperation(new Unlock(d1));

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

