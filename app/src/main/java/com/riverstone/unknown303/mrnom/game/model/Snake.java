package com.riverstone.unknown303.mrnom.game.model;

import java.util.ArrayList;
import java.util.List;
import com.riverstone.unknown303.mrnom.game.model.SnakePart.PartType;
import com.riverstone.unknown303.mrnom.game.model.Food.FoodType;
import com.riverstone.unknown303.mrnom.util.Assets;

public class Snake {
    public static final int UP = 0;
    public static final int LEFT = 1;
    public static final int DOWN = 2;
    public static final int RIGHT = 3;

    public List<SnakePart> parts = new ArrayList<SnakePart>();
    public int direction;

    public Snake() {
        direction = UP;
        parts.add(new SnakePart(5, 6, PartType.HEAD));
        parts.add(new SnakePart(5, 7, PartType.TAIL));
        parts.add(new SnakePart(5, 8, PartType.TAIL));
    }

    public void turnLeft() {
        direction += 1;
        if (direction > RIGHT) {
            direction = UP;
        }
    }

    public void turnRight() {
        direction -= 1;
        if (direction < UP) {
            direction = RIGHT;
        }
    }

    public void eat(Food food) {
        FoodType type = food.foodType;
        if (type == FoodType.CROISSANT || type == FoodType.BAGUETTE) {
            SnakePart end = parts.get(parts.size()-1);
            parts.add(new SnakePart(end.x, end.y, PartType.TAIL_UNCOLORED));
        } else {
            for (int i = 2; i < parts.size(); i++) {
                SnakePart part = parts.get(i);
                if (part.partType == PartType.TAIL_UNCOLORED) {
                    part.partType = PartType.TAIL;
                    return;
                }
            }
        }
    }

    public void advance() {
        SnakePart head = parts.get(0);

        for (int i = (parts.size() - 1); i > 0; i--) {
            SnakePart before = parts.get(i-1);
            SnakePart part = parts.get(i);
            part.x = before.x;
            part.y = before.y;
        }

        if (direction == UP) {
            head.y -= 1;
        } else if (direction == LEFT) {
            head.x -= 1;
        } else if (direction == DOWN) {
            head.y += 1;
        } else if (direction == RIGHT) {
            head.x += 1;
        }

        if (head.x < 0) {
            head.x = 9;
        } if (head.x > 9) {
            head.x = 0;
        } if (head.y < 0) {
            head.y = 12;
        } if (head.y > 12) {
            head.y = 0;
        }
    }

    public boolean checkBitten() {
        SnakePart head = parts.get(0);
        for (int i = 1; i < parts.size(); i++) {
            SnakePart part = parts.get(i);
            if (part.x == head.x && part.y == head.y) {
                return true;
            }
        }
        return false;
    }
}
