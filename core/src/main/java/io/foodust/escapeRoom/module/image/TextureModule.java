package io.foodust.escapeRoom.module.image;

import com.badlogic.gdx.graphics.Texture;

public class TextureModule {

    public TextureModule() {

    }

    public Texture makeTexture(String name){
        return new Texture(name);
    }
}
