package editor.Enigma;

import editor.Enigma.Condition.Activated;
import editor.Enigma.Condition.Answer;
import editor.Enigma.Operation.Unlock;
import editor.Entity.Item.Door;
import editor.Entity.Item.Switch;
import editor.Entity.Player.Player;

public class MainTestEnigma {

    public static void main(String[] args){
        Door door = new Door();
        Activated switchCond = new Activated(new Switch());
        Unlock openDoor = new Unlock(door);
        Enigma enigma = new Enigma("ouvrir porte","Serrez-vous capable d'ouvrir la porte?");
        enigma.addAdvice(new Advice("activez le levier",1));
        enigma.addCondition(switchCond);
        enigma.addOperation(openDoor);
        door.addEnigma(enigma);

        System.out.println(enigma.getTitle());
        System.out.println(enigma.getDescription());

        door.interactsWith(new Player());

        while(enigma.getTextAdvice().equals("")){}
        System.out.println(enigma.getTextAdvice());
    }
}
