package editor.Enigma;

import editor.Enigma.Condition.Activated;
import editor.Enigma.Operation.Unlock;
import editor.Entity.Item.Door;
import editor.Entity.Item.Switch;
import editor.Entity.Player.Player;

public class MainTestEnigma {

    public static void main(String[] args) throws InterruptedException {
        Door door = new Door();
        Player player = new Player();
        Switch switch1 = new Switch();
        Activated switchCond = new Activated(switch1);
        Unlock openDoor = new Unlock(door);

        Enigma enigma = new Enigma("Ouvrir porte","Serrez-vous capable d'ouvrir la porte?");
        enigma.addAdvice("activez le levier bon sang!");
        enigma.addAdvice("à supprimer");
        enigma.addAdvice("activez le levier");
        enigma.removeAdvice("à supprimer");
        enigma.switchAdvices(1,0);
        enigma.addCondition(switchCond);
        enigma.addOperation(openDoor);
        enigma.setTimeBetweenAdvices(1);    //attente de 1min avant de révéler le prochain indice

        door.addEnigma(enigma);
        door.interactsWith(player); //Première interraction

        System.out.println(door.toLongString());

        //Attendre les indices
        System.out.println("aide début = "+enigma.getAdvice());
        Thread.sleep(61000); //attendre + de 1min (60000 millisecondes = 1min)
        System.out.println("aide après 1min = "+enigma.getAdvice());
        Thread.sleep(61000); //attendre encore + de 1min
        System.out.println("aide après 2min = "+enigma.getAdvice());

        switch1.interactsWith(player);  //remplir la condition
        door.interactsWith(player); //Seconde interraction
    }
}
