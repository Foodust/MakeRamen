package io.foodust.makeRamen.module.image;

import com.badlogic.gdx.graphics.Texture;
import io.foodust.makeRamen.module.BaseModule;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TextureModule extends BaseModule {


    public TextureModule() {

    }

    public Texture makeTexture(String name) {
        Texture texture = new Texture("sprite/" + name);
        objects.add(texture);
        return texture;
    }

    @Override
    public void dispose() {
        objects.stream().filter(Objects::nonNull).filter(o -> o instanceof Texture).map(o -> (Texture) o).forEach(Texture::dispose);
    }
}
