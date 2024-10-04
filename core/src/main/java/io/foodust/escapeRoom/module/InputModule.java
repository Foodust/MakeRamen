package io.foodust.escapeRoom.module;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

public class InputModule {

    public InputModule() {
    }

    public Boolean getKeyBoardInput(Integer number) {
        return Gdx.input.isKeyPressed(number);
    }

    public Boolean getTouch() {
        return Gdx.input.isTouched();
    }

    public Boolean isCollideTouch(Rectangle rectangle) {
        int x = Gdx.input.getX();
        int y = Gdx.input.getY();
        return isCollideTouch(rectangle, x, y);
    }

    public Boolean isCollideTouch(Rectangle rectangle, int x, int y) {
        return Gdx.input.isTouched() && rectangle.contains(x, y);
    }
}
