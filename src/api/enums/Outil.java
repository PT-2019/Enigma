package api.enums;

public enum Outil {
    OPEN("Open"),
    SAVE("save"),
    UNDO("undo"),
    REDO("redo"),
    SOURIS("Souris"),
    GOMME("Gomme"),
    MOVE("Move");

    public final String name;

    Outil(String name) { this.name= name;}
}