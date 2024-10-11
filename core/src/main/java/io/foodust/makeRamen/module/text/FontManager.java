package io.foodust.makeRamen.module.text;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import io.foodust.makeRamen.module.BaseModule;

import java.util.Objects;

public class FontManager extends BaseModule {

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
        BitmapFont bitmapFont = generator.generateFont(parameter);
        objects.add(bitmapFont);
        return bitmapFont;
    }

    @Override
    public void dispose() {
        objects.stream().filter(Objects::nonNull).filter(o -> o instanceof BitmapFont).map(o -> (BitmapFont) o).forEach(BitmapFont::dispose);
        if (generator != null) {
            generator.dispose();
        }
    }
}
