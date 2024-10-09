package io.foodust.makeRamen.object.object.stage;

import com.badlogic.gdx.graphics.OrthographicCamera;
import io.foodust.makeRamen.object.object.BaseObject;

public class TrashObject extends BaseObject {
    public TrashObject(String textureName, float x, float y) {
        super(textureName, x, y);
    }

    @Override
    public Boolean isClicked(OrthographicCamera camera) {
        Boolean clicked = super.isClicked(camera);
        if (!clicked) return false;

        modules.getSoundModule().makeSound("trash.wav");
        return true;
    }
}
