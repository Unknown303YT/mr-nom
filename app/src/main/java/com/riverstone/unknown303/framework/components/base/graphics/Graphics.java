package com.riverstone.unknown303.framework.components.base.graphics;

import com.riverstone.unknown303.framework.components.base.input.Input;

public interface Graphics {
    public static enum PixmapFormat {
        ARGB8888, ARGB4444, RGB565
    }

    public Pixmap newPixmap(String fileName, PixmapFormat format);

    public void clear(int color);

    public void drawPixel(int x, int y, int color);
    public void drawLine(int startX, int startY, int endX, int endY, int color);
    public void drawRect(int x, int y, int width, int height, int color);

    public void drawPixmap(Pixmap pixmap, int x, int y, int srcX, int srcY,
                           int srcWidth, int srcHeight);
    public void drawPixmap(Pixmap pixmap, int x, int y);

    public int getWidth();
    public int getHeight();

    public void drawText(Pixmap numbers, String line, int x, int y);
}
