package io.foodust.makeRamen.module.image;

import com.badlogic.gdx.graphics.Texture;

public class TextureModule {

    public TextureModule() {

    }

    public Texture makeTexture(String name) {
        return new Texture("sprite/" + name);
    }
}
