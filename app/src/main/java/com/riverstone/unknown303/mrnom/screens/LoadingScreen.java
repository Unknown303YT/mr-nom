package com.riverstone.unknown303.mrnom.screens;

import com.riverstone.unknown303.framework.components.base.Game;
import com.riverstone.unknown303.framework.components.base.Screen;
import com.riverstone.unknown303.framework.components.base.graphics.Graphics;
import com.riverstone.unknown303.framework.components.base.graphics.Graphics.PixmapFormat;
import com.riverstone.unknown303.mrnom.util.Assets;
import com.riverstone.unknown303.mrnom.util.Settings;

public class LoadingScreen extends Screen {
    public LoadingScreen(Game game)  {
        super(game);
    }

    @Override
    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        Assets.background = g.newPixmap("background.png", PixmapFormat.RGB565);
        Assets.logo = g.newPixmap("logo.png", PixmapFormat.ARGB4444);
        present(deltaTime);
        Assets.mainMenu = g.newPixmap("mainMenu.png", PixmapFormat.ARGB4444);
        Assets.buttons = g.newPixmap("buttons.png", PixmapFormat.ARGB4444);
        Assets.numbers = g.newPixmap("numbers.png", PixmapFormat.ARGB4444);
        Assets.game = g.newPixmap("game.png", PixmapFormat.ARGB4444);

        Assets.readyUI = g.newPixmap("ready.png", PixmapFormat.ARGB4444);
        Assets.pausedUI = g.newPixmap("paused.png", PixmapFormat.ARGB4444);
        Assets.gameOverUI = g.newPixmap("game_over.png", PixmapFormat.ARGB4444);

        Assets.headUp = g.newPixmap("snake_head_up.png", PixmapFormat.ARGB4444);
        Assets.headLeft = g.newPixmap("snake_head_left.png", PixmapFormat.ARGB4444);
        Assets.headRight = g.newPixmap("snake_head_right.png", PixmapFormat.ARGB4444);
        Assets.headDown = g.newPixmap("snake_head_down.png", PixmapFormat.ARGB4444);
        Assets.tailUncolored = g.newPixmap("snake_tail_uncolored.png", PixmapFormat.ARGB4444);
        Assets.tail = g.newPixmap("snake_tail.png", PixmapFormat.ARGB4444);

        Assets.baguette = g.newPixmap("baguette.png", PixmapFormat.ARGB4444);
        Assets.croissant = g.newPixmap("croissant.png", PixmapFormat.ARGB4444);
        Assets.glitter = g.newPixmap("glitter.png", PixmapFormat.ARGB4444);

        Assets.backgroundMusic = game.getAudio().newMusic("music.ogg");
        Assets.click = game.getAudio().newSound("click.ogg");
        Assets.bitten = game.getAudio().newSound("bitten.ogg");
        Assets.eat = game.getAudio().newSound("eat.ogg");

        Settings.load(game.getFileIO());
        game.setScreen(new MainMenuScreen(game));
    }

    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawPixmap(Assets.background, 0, 0);
        g.drawPixmap(Assets.logo, 0, 0);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void dispose() {}
}
