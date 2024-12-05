package com.riverstone.unknown303.mrnom.game.model;

public class Food {
    public enum FoodType {
        BAGUETTE,
        CROISSANT,
        GLITTER;
    }
    public int x, y;
    public FoodType foodType;

    public Food(int x, int y, int type) {
        this.x = x;
        this.y = y;
        if (type == 0) {
            this.foodType = FoodType.BAGUETTE;
        } else if (type == 1) {
            this.foodType = FoodType.BAGUETTE;
        } else if (type == 2) {
            this.foodType = FoodType.CROISSANT;
        } else if (type == 3) {
            this.foodType = FoodType.GLITTER;
        } else {
            this.foodType = FoodType.BAGUETTE;
        }
    }
}
