package editor.FileReader;

/**
 * Gère des ID pouvant être attribués à des objets quelconques
 * La classe connaît un ID qu'elle considère comme le plus grand, il faut donc la renseigner si un ID plus grand que celui qu'elle connaît existe
 * @version 2.0
 */
public class IDFactory {

    /**
     * Plus grand ID connu
     */
    private int biggestID;

    public IDFactory(){
        this.biggestID = -1;
    }

    /**
     * @param id Plus grand ID connu
     */
    public IDFactory(int id){
        this.biggestID = id;
    }

    /**
     * Définie id comme le plus grand ID connu de cette instance, à la condition qu'il soit plus grand que celui actuel
     * @param id ID
     */
    public void setID(int id){
        if(this.biggestID < id) this.biggestID = id;
    }

    /**
     * Définie id comme le plus grand ID connu de cette instance, même s'il est inférieur ou égal à celui actuel
     * @param id ID
     */
    public void forceSetID(int id){
        this.biggestID = id;
    }

    /**
     * Définie id comme le plus grand ID connu de cette instance, à la condition qu'il soit plus grand que celui actuel
     * @param id ID
     * @return true si le plus grand ID connu de cette instance à été changé, false sinon
     */
    public boolean confirmSetID(int id){
        if(this.biggestID < id){
            this.biggestID = id;
            return true;
        }
        return false;
    }

    /**
     * Définie id comme le plus grand ID connu de cette instance, à la condition qu'il soit plus grand que celui actuel
     * @param id ID
     * @return Le même ID que celui donné en paramètre si le plus grand ID connu de cette instance à été changé, un nouvel ID sinon
     */
    public int setOrGetNewID(int id){
        if(this.biggestID < id){
            this.biggestID = id;
            return this.biggestID;
        }
        return this.getNewID();
    }

    /**
     * Obtenir l'ID le plus grand connu de cette instance
     * Celui-çi peut être égal à -1, ce qui signifie qu'aucun ID n'a été affecté ou que l'instance n'en a pas conscience
     * @return Plus grand ID connu
     */
    public int getCurrentBiggestID(){
        return this.biggestID;
    }

    /**
     * Obtenir un nouvel ID. Incrémente le plus grand ID connu au passage
     * @return Nouvel ID
     */
    public int getNewID(){
        this.biggestID++;
        return this.biggestID;
    }
}
