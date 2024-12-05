package com.riverstone.unknown303.mrnom.game.model;

import com.riverstone.unknown303.mrnom.game.model.Food;
import com.riverstone.unknown303.mrnom.game.model.Food.FoodType;
import com.riverstone.unknown303.mrnom.game.model.Snake;
import com.riverstone.unknown303.mrnom.game.model.SnakePart;
import com.riverstone.unknown303.mrnom.util.Assets;

import java.util.Random;

public class World {
    static final int WORLD_WIDTH = 10;
    static final int WORLD_HEIGHT = 11;

    static final int SCORE_INCREMENT = 5;

    static final float TICK_INITIAL = 1f;
    static final float TICK_DECREMENT = 0.05f;

    public Snake snake;
    public Food food;
    public boolean gameOver = false;
    public int score = 0;
    public int scoreBuffer = 0;

    boolean[][] fields = new boolean[WORLD_WIDTH][WORLD_HEIGHT];
    Random random = new Random();
    float tickTime = 0;
    static float tick = TICK_INITIAL;


    public World() {
        snake = new Snake();
        placeFood();
    }

    private void placeFood() {
        for (int x = 0; x < WORLD_WIDTH; x++) {
            for (int y = 0; y < WORLD_HEIGHT; y++) {
                fields[x][y] = false;
            }
        }

        int len = snake.parts.size();
        for (int i = 0; i < len; i++) {
            SnakePart part = snake.parts.get(i);
            fields[part.x][part.y] = true;
        }

        int foodX = random.nextInt(WORLD_WIDTH);
        int foodY = random.nextInt(WORLD_HEIGHT);
        while (true) {
            if (!fields[foodX][foodY]) {
                break;
            }
            foodX += 1;
            if (foodX >= WORLD_WIDTH) {
                foodX = 0;
                foodY += 1;
                if (foodY >= WORLD_HEIGHT) {
                    foodY = 0;
                }
            }
        }
        int type = random.nextInt(4);
        food = new Food(foodX, foodY, type);
    }

    public void update (float deltaTime) {
        if (gameOver) {
            return;
        }

        tickTime += deltaTime;
        if (tickTime > tick) {
            tickTime = 0;
            snake.advance();
            if (snake.checkBitten()) {
                gameOver = true;
            }
        }

        SnakePart head = snake.parts.get(0);
        if (head.x == food.x && head.y == food.y) {
            score += SCORE_INCREMENT;
            if (food.foodType == FoodType.CROISSANT) {
                score += SCORE_INCREMENT;
            } else if (food.foodType == FoodType.GLITTER) {
                score += SCORE_INCREMENT;
                score += (SCORE_INCREMENT - 2);
            }
            snake.eat(food);
            if (snake.parts.size() == WORLD_WIDTH * WORLD_HEIGHT) {
                gameOver = true;
                food = null;
                return;
            } else {
                placeFood();
            }

            if (score >= (scoreBuffer + 20)) {
                if (tick - TICK_DECREMENT > 0) {
                    tick -= TICK_DECREMENT;
                }
                scoreBuffer = score;
            }
        }
    }
}
