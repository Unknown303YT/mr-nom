package com.riverstone.unknown303.framework.components.base.input;

import java.util.List;

public interface Input {
    public static class TypeEvent {
        public static final int KEY_DOWN = 0;
        public static final int KEY_UP = 1;

        public int type;
        public int keyCode;
        public char keyChar;
    }

    public static class TouchEvent {
        public enum TouchEventType {
            TOUCH_DOWN,
            TOUCH_UP,
            TOUCH_DRAGGED;
        }

        public TouchEventType type;
        public int x, y;
        public int pointer;
    }

    public boolean isKeyPressed(int keyCode);
    public boolean isTouchDown(int pointer);

    public int getTouchX(int pointer);
    public int getTouchY(int pointer);

    public float getAccelX();
    public float getAccelY();

    public float getAccelZ();

    public List<TypeEvent> getKeyEvents();
    public List<TouchEvent> getTouchEvents();

    public boolean inBounds(TouchEvent event, int x, int y, int width, int height);
}
