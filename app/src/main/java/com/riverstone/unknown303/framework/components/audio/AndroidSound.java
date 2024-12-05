package com.riverstone.unknown303.framework.components.audio;

import android.media.SoundPool;

import com.riverstone.unknown303.framework.components.base.audio.Sound;

public class AndroidSound implements Sound {
    int soundId;
    SoundPool soundPool;

    public AndroidSound(SoundPool soundPool, int soundId) {
        this.soundId = soundId;
        this.soundPool = soundPool;
    }
    @Override
    public void play(float volume) {
        soundPool.play(soundId, volume, volume, 0, 0, 1);
        // TODO: MAKE LOOP ADJUSTABLE
    }

    @Override
    public void dispose() {
        soundPool.unload(soundId);
    }
}
