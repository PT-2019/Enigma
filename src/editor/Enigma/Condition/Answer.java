package editor.Enigma.Condition;

import editor.Entity.Player.Player;

public class Answer extends Condition {

    public Answer(){
        super(null);
    }

    @Override
    public boolean verify(Player p) {
        //poser la question et tester si la réponse est bonne
        return false;
    }
}
