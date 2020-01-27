package game.utils;

public enum OperationEventEnum {

    /**
     * A l'entrée
     */
    ON_ENTER,

    /**
     * A la sortie
     */
    ON_EXIT,

    /**
     * Lors de l'interaction
     */
    ON_USE;

    public static OperationEventEnum parse(String s){
        s = s.toLowerCase();

        if(s.equals(ON_ENTER.toString().toLowerCase()))
            return ON_ENTER;

        if(s.equals(ON_EXIT.toString().toLowerCase()))
            return ON_EXIT;

        if(s.equals(ON_USE.toString().toLowerCase()))
            return ON_USE;

        return null;
    }
}
