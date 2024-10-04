package io.foodust.makeRamen.module.image;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SpriteModule {
    public SpriteModule() {

    }

    public Sprite makeSprite(Texture texture) {
        return new Sprite(texture);
    }
}
