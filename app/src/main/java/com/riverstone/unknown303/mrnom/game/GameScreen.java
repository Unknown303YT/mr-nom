package com.riverstone.unknown303.mrnom.game;

import android.text.method.Touch;
import android.util.Log;

import com.riverstone.unknown303.framework.components.base.Game;
import com.riverstone.unknown303.framework.components.base.Screen;
import com.riverstone.unknown303.framework.components.base.graphics.Graphics;
import com.riverstone.unknown303.framework.components.base.graphics.Pixmap;
import com.riverstone.unknown303.framework.components.base.input.Input;
import com.riverstone.unknown303.mrnom.game.model.Food;
import com.riverstone.unknown303.mrnom.game.model.Food.FoodType;
import com.riverstone.unknown303.mrnom.game.model.Snake;
import com.riverstone.unknown303.mrnom.game.model.SnakePart;
import com.riverstone.unknown303.mrnom.game.model.World;
import com.riverstone.unknown303.framework.components.base.input.Input.TouchEvent;
import com.riverstone.unknown303.mrnom.screens.MainMenuScreen;
import com.riverstone.unknown303.mrnom.util.Assets;
import com.riverstone.unknown303.mrnom.util.Settings;
import com.riverstone.unknown303.framework.components.base.input.Input.TouchEvent.TouchEventType;

import java.util.List;

public class GameScreen extends Screen {
    enum GameState {
        Ready,
        Running,
        Paused,
        GameOver;
    }

    GameState state = GameState.Running;
    World world;
    int oldScore = 0;
    String score = "0";

    public GameScreen(Game game) {
        super(game);
        world = new World();
    }

    @Override
    public void update(float deltaTime) {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();
        if (state == GameState.Running) {
            updateRunning(touchEvents, deltaTime);
        } if (state == GameState.Ready) {
            updateReady(touchEvents);
        } if (state == GameState.Paused) {
            updatePaused(touchEvents);
        } if (state == GameState.GameOver) {
            updateGameOver(touchEvents);
        }
    }

    private void updateReady(List<TouchEvent> touchEvents) {
        if (!touchEvents.isEmpty()) {
            state = GameState.Running;
        }
    }

    private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {
        for (int i = 0; i < touchEvents.size(); i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEventType.TOUCH_UP) {
                if (event.x < 64 && event.y < 64) {
                    if (Settings.soundEnabled) {
                        Assets.click.play(1);
                    }
                    state = GameState.Paused;
                    return;
                }
            } else if (event.type == TouchEventType.TOUCH_DOWN) {
                if (event.x < 64 && event.y > 416) {
                    world.snake.turnLeft();
                } if (event.x > 256 && event.y > 416) {
                    world.snake.turnRight();
                }
            }
        }
        world.update(deltaTime);
        if (world.gameOver) {
            if (Settings.soundEnabled) {
                Assets.bitten.play(1);
            }
            state = GameState.GameOver;
        } if (oldScore != world.score) {
            oldScore = world.score;
            score = Integer.toString(oldScore);
            if (Settings.soundEnabled) {
                Assets.eat.play(1);
            }
        }
    }

    private void updatePaused(List<TouchEvent> touchEvents) {
        for (int i = 0; i < touchEvents.size(); i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEventType.TOUCH_UP) {
                if (event.x > 80 && event.x <= 240) {
                    if (event.y > 100 && event.y <= 148) {
                        if (Settings.soundEnabled) {
                            Assets.click.play(1);
                        }
                        state = GameState.Running;
                        return;
                    } if (event.y > 148 && event.y < 196) {
                        if (Settings.soundEnabled) {
                            Assets.click.play(1);
                        }
                        game.setScreen(new MainMenuScreen(game));
                        return;
                    }
                }
            }
        }
    }

    private void updateGameOver(List<TouchEvent> touchEvents) {
        for (int i = 0; i < touchEvents.size(); i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.x > 80 && event.x <= 240) {
                if (event.y > 100 && event.y <= 148) {
                    if (Settings.soundEnabled) {
                        Assets.click.play(1);
                    }
                    state = GameState.Running;
                    return;
                } if (event.y > 148 && event.y < 196) {
                    if (Settings.soundEnabled) {
                        Assets.click.play(1);
                    }
                    game.setScreen(new MainMenuScreen(game));
                    return;
                }
            }
        }
    }

    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawPixmap(Assets.background, 0, 0);
        g.drawPixmap(Assets.game, 0, 0);
        drawWorld(world, g);
//        if (state == GameState.Ready) {
//            drawReadyUI(g);
//        } if (state == GameState.Running) {
            drawRunningUI(g);
//        } if (state == GameState.Paused) {
//            drawPausedUI(g);
//        } if (state == GameState.GameOver) {
//            drawGameOverUI(g);
//        }

        g.drawText(Assets.numbers, score, g.getWidth() / 2 - score.length()*20 / 2,
                g.getHeight() - 42);
    }

    private void drawWorld(World world, Graphics g) {
        Snake snake = world.snake;
        SnakePart head = snake.parts.get(0);
        Food food = world.food;

        g.drawPixmap(Assets.game, 0, 0);

        Pixmap foodPixmap = null;
        if (food.foodType == FoodType.BAGUETTE) {
            foodPixmap = Assets.baguette;
        } if (food.foodType == FoodType.CROISSANT) {
            foodPixmap = Assets.croissant;
        } if (food.foodType == FoodType.GLITTER) {
            foodPixmap = Assets.glitter;
        }
        int x = food.x * 32;
        int y = 64 + (food.y * 32);
        g.drawPixmap(foodPixmap, x, y);

        for (int i = 1; i < snake.parts.size(); i++) {
            SnakePart part = snake.parts.get(i);
            Pixmap tailPixmap = null;
            x = part.x * 32;
            y = 64 + (part.y * 32);
            if (part.partType == SnakePart.PartType.TAIL) {
                tailPixmap = Assets.tail;
            } else if (part.partType == SnakePart.PartType.TAIL_UNCOLORED) {
                tailPixmap = Assets.tailUncolored;
            } else {
                Log.e("Game", "Error: tail part type not equal to uncolored or normal. Reverting to uncolored.");
                tailPixmap = Assets.tailUncolored;
            }
            g.drawPixmap(tailPixmap, x, y);
        }

        Pixmap headPixmap = null;
        if (snake.direction == Snake.UP) {
            headPixmap = Assets.headUp;
        } if (snake.direction == Snake.LEFT) {
            headPixmap = Assets.headLeft;
        } if (snake.direction == Snake.DOWN) {
            headPixmap = Assets.headDown;
        } if (snake.direction == Snake.RIGHT) {
            headPixmap = Assets.headRight;
        } else {
            headPixmap = Assets.headLeft;
        }
        x = (head.x * 32) + 16;
        y = (64 + (head.y * 32)) + 16;
        g.drawPixmap(headPixmap, x - headPixmap.getWidth() / 2,
                y - headPixmap.getHeight() / 2);
    }

    private void drawReadyUI(Graphics g) {
        g.drawPixmap(Assets.readyUI, 0, 0);
    }

    private void drawRunningUI(Graphics g) {
        g.drawPixmap(Assets.buttons, );
    }

    private void drawPausedUI(Graphics g) {
        g.drawPixmap(Assets.pausedUI, 0, 0);
    }
}
