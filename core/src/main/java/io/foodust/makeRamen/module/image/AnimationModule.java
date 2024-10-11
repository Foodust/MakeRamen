package io.foodust.makeRamen.module.image;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import io.foodust.makeRamen.module.BaseModule;
import io.foodust.makeRamen.object.ObjectManager;

public class AnimationModule extends BaseModule {
    private final Integer defaultCols = 6;
    private final Integer defaultRows = 5;

    public AnimationModule() {

    }

    public Animation<TextureRegion> loadAnimation(String fileName, Float frame, Integer cols, Integer rows) {
        Texture texture = ObjectManager.getInstance().getModules().getTextureModule().makeTexture(fileName);
        TextureRegion[][] split = TextureRegion.split(texture, texture.getWidth() / cols, texture.getHeight() / rows);
        TextureRegion[] textureRegions = new TextureRegion[cols * rows];
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                textureRegions[index++] = split[i][j];
            }
        }
        return new Animation<>(frame, textureRegions);
    }

    public Animation<TextureRegion> loadAnimation(String fileName, Float frame) {
        return loadAnimation(fileName, frame, defaultCols, defaultRows);
    }

    @Override
    public void dispose() {

    }
}
