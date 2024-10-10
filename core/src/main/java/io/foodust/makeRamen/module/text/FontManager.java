package io.foodust.makeRamen.module.text;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class FontManager {
    private final FreeTypeFontGenerator generator;


    public FontManager() {
        generator = new FreeTypeFontGenerator(Gdx.files.internal("font/font.ttf"));
    }

    public BitmapFont generateFont(int size) {
        return generateFont(size, Color.WHITE);
    }

    public BitmapFont generateFont(int size, Color color) {
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = size;
        parameter.color = color;
        return generator.generateFont(parameter);
    }

    public void dispose() {
        if (generator != null) {
            generator.dispose();
        }
    }
}
