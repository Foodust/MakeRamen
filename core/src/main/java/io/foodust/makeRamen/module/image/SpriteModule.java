package io.foodust.makeRamen.module.image;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import io.foodust.makeRamen.module.BaseModule;

public class SpriteModule extends BaseModule {
    public SpriteModule() {

    }

    public Sprite makeSprite(Texture texture) {
        return new Sprite(texture);
    }

    @Override
    public void dispose() {

    }
}
