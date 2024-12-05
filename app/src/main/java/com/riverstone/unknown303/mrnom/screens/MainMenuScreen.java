package com.riverstone.unknown303.mrnom.screens;

import com.riverstone.unknown303.framework.components.base.Game;
import com.riverstone.unknown303.framework.components.base.Screen;
import com.riverstone.unknown303.framework.components.base.graphics.Graphics;
import com.riverstone.unknown303.framework.components.base.input.Input;
import com.riverstone.unknown303.framework.components.base.input.Input.TouchEvent;
import com.riverstone.unknown303.framework.components.base.input.Input.TypeEvent;
import com.riverstone.unknown303.mrnom.util.Assets;
import com.riverstone.unknown303.mrnom.util.Settings;

import java.util.List;

public class MainMenuScreen extends Screen {
    public MainMenuScreen(Game game) {
        super(game);
    }

    @Override
    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();

        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);

        }
    }

    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();

        g.drawPixmap(Assets.background, 0, 0);
        g.drawPixmap(Assets.logo, 0, 0);
        g.drawPixmap(Assets.mainMenu, 0, 0);
        if (Settings.soundEnabled) {
            g.drawPixmap(Assets.buttons, 0, 416, 0, 0, 48, 48);
        } else {
            g.drawPixmap(Assets.buttons, 0, 416, 48, 0, 48, 48);
        }
    }

    @Override
    public void pause() {
        Settings.save(game.getFileIO());
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }
}