package io.foodust.makeRamen.object.object.stage;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import io.foodust.makeRamen.object.object.BaseObject;

public class PlatObject extends BaseObject {
    public PlatObject(String textureName, float x, float y) {
        super(textureName, x, y);
    }

    @Override
    public Boolean isClicked(OrthographicCamera camera) {
        Boolean isClicked = super.isClicked(camera);
        if (!isClicked) return false;
        modules.getSoundModule().makeSound("enter.wav");
        return true;
    }
}
