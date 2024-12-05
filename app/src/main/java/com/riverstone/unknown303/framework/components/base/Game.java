package com.riverstone.unknown303.framework.components.base;

import com.riverstone.unknown303.framework.components.base.audio.Audio;
import com.riverstone.unknown303.framework.components.base.graphics.Graphics;
import com.riverstone.unknown303.framework.components.base.input.Input;

public interface Game {
    public Input getInput();

    public FileIO getFileIO();

    public Graphics getGraphics();

    public Audio getAudio();

    public void setScreen(Screen screen);

    public Screen getCurrentScreen();
    public Screen getStartScreen();
}
