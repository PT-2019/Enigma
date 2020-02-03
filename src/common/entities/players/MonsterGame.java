package common.entities.players;

import api.libgdx.actor.GameActorAnimation;

public class MonsterGame extends GameActorAnimation {

    public MonsterGame(){
        this.setAnimationPaused(true);
        this.setBounds(4);
    }
}
