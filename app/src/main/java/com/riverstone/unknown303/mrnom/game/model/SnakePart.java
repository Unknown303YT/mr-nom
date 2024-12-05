package com.riverstone.unknown303.mrnom.game.model;

public class SnakePart {
    public enum PartType {
        HEAD,
        TAIL_UNCOLORED,
        TAIL;
    }
    public int x, y;
    public PartType partType;

    public SnakePart(int x, int y, PartType type) {
        this.x = x;
        this.y = y;
        this.partType = type;
    }
}
