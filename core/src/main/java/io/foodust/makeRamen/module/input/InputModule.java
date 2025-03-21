package io.foodust.makeRamen.module.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import io.foodust.makeRamen.module.BaseModule;
import io.foodust.makeRamen.module.sound.SoundModule;
import io.foodust.makeRamen.object.ObjectManager;

public class InputModule extends BaseModule {

    public InputModule() {
    }

    public Boolean getKeyBoardTouch(Integer number) {
        return Gdx.input.isKeyJustPressed(number);
    }


    public Boolean getTouch(OrthographicCamera camera, Rectangle rectangle) {
        if (Gdx.input.justTouched()) {
            Vector3 touchPoint = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPoint);
            boolean contains = rectangle.contains(touchPoint.x, touchPoint.y);
            if (!contains) return false;
            SoundModule soundModule = ObjectManager.getInstance().getModules().getSoundModule();
            Music sound = soundModule.makeSound("click.wav",0.5f);
            sound.play();
            return true;
        }
        return false;
    }

    @Override
    public void dispose() {

    }
}
