package com.riverstone.unknown303.mrnom;

import com.riverstone.unknown303.framework.AndroidGame;
import com.riverstone.unknown303.framework.components.base.Screen;
import com.riverstone.unknown303.mrnom.screens.LoadingScreen;

public class MrNomGame extends AndroidGame {
    @Override
    public Screen getStartScreen() {
        return new LoadingScreen(this);
    }
}
