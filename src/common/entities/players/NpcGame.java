package common.entities.players;

import api.libgdx.actor.GameActorAnimation;

public class NpcGame extends GameActorAnimation {

    public NpcGame(){
        this.setAnimationPaused(true);
        this.setBounds(4);
    }
}
