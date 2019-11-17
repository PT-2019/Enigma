package editor.Enigma;

import editor.Enigma.Condition.Activated;
import editor.Enigma.Operation.Unlock;
import editor.Entity.Item.Door;
import editor.Entity.Item.Switch;
import editor.Entity.Player.Player;

public class MainTestEnigma {

    public static void main(String[] args) throws InterruptedException {
        //Ici, on attribu une énigme à une porte
        //cette énigme consite à activer un levier qui va déverrouiller la porte

        Door door = new Door();
        Player player = new Player();
        Switch switch1 = new Switch();
        Activated switchCond = new Activated(switch1);
        Unlock openDoor = new Unlock(door);

        Enigma enigma = new Enigma("Ouvrir porte","Serrez-vous capable d'ouvrir la porte?");
        //ajout d'indices
        enigma.addAdvice("activez le levier bon sang!");
        enigma.addAdvice("à supprimer");
        enigma.addAdvice("activez le levier");
        //suppression d'un indice
        enigma.removeAdvice("à supprimer");
        //changement dans l'ordre des indices
        enigma.switchAdvices(1,0);
        //ajout d'une condition
        enigma.addCondition(switchCond);
        //ajout d'une opération
        enigma.addOperation(openDoor);
        //on définie qu'il faut attente de 1min avant de révéler le prochain indice
        enigma.setTimeBetweenAdvices(1);

        //ajout de l'énigme à la porte
        door.addEnigma(enigma);
        //interraction avec la porte
        door.interactsWith(player);

        System.out.println(door.toLongString());

        //Attendre les indices
        System.out.println("aide début = "+enigma.getAdvice());
        Thread.sleep(61000); //attendre + de 1min (60000 millisecondes = 1min)
        System.out.println("aide après 1min = "+enigma.getAdvice());
        Thread.sleep(61000); //attendre encore + de 1min
        System.out.println("aide après 2min = "+enigma.getAdvice());

        //remplir la condition : activer le levier
        switch1.interactsWith(player);
        //interraction avec la porte, cette fois, ca doit marcher
        door.interactsWith(player);
    }
}
