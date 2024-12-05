package com.riverstone.unknown303.framework.components.base.graphics;

public interface Pixmap {
    public int getWidth();
    public int getHeight();

    public Graphics.PixmapFormat getFormat();

    public void dispose();
}
