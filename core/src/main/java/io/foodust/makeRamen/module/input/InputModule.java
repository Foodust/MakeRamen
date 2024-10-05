package io.foodust.makeRamen.module.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class InputModule {

    public InputModule() {
    }

    public Boolean getKeyBoardInput(Integer number) {
        return Gdx.input.isKeyPressed(number);
    }


    public Boolean getTouch(OrthographicCamera camera, Rectangle rectangle) {
        if (Gdx.input.justTouched()) {
            Vector3 touchPoint = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPoint);
            return rectangle.contains(touchPoint.x, touchPoint.y);
        }
        return false;
    }
}
