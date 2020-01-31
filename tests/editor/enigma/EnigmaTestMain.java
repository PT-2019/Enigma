package editor.enigma;

import common.enigmas.Advice;
import common.enigmas.Enigma;
import common.enigmas.condition.Activated;
import common.enigmas.operation.Unlock;
import common.entities.items.Door;
import common.entities.items.Switch;
import common.entities.players.Player;

/**
 * Main énigmes
 * @version 2.0
 */
public class EnigmaTestMain {
	public static void main(String[] args) throws InterruptedException {
		//Ici, on attribu une énigme à une porte
		//cette énigme consite à activer un levier qui va déverrouiller la porte
		//L'énigme comporte deux indices qui apparaisse après un délai de 1 min chacun

		Door door = new Door();
		Player player = new Player();
		Switch switch1 = new Switch();
		Activated switchCond = new Activated(switch1);
		Unlock openDoor = new Unlock(door);

		Enigma enigma = new Enigma("Ouvrir porte","Serrez-vous capable d'ouvrir la porte?");
		//ajout d'indices
		Advice advice1 = new Advice("activez le levier bon sang!",1);
		Advice toDeleteAdvice = new Advice("à supprimer",1);
		Advice advice2 = new Advice("activez le levier",1);

		enigma.addAdvice(advice1);
		enigma.addAdvice(toDeleteAdvice);
		enigma.addAdvice(advice2);

		//suppression d'un indice
		enigma.removeAdvice(toDeleteAdvice);

		//changement dans l'ordre des indices
		enigma.switchAdvices(advice1,advice2);

		//ajout d'une condition
		enigma.addCondition(switchCond);

		//ajout d'une opération
		enigma.addOperation(openDoor);

		//ajout de l'énigme à la porte
		door.addEnigma(enigma);
		//interraction avec la porte
		door.interactsWith(player);

		System.out.println(door.toLongString());

		//Attendre les indices
		System.out.println("aide début = "+enigma.getTextAdvice());
		Thread.sleep(61000); //attendre + de 1min (60000 millisecondes = 1min)
		System.out.println("aide après 1min = "+enigma.getTextAdvice());
		Thread.sleep(61000); //attendre encore + de 1min
		System.out.println("aide après 2min = "+enigma.getTextAdvice());

		//remplir la condition : activer le levier
		switch1.interactsWith(player);
		//interraction avec la porte, cette fois, ca doit marcher
		door.interactsWith(player);
	}
}

