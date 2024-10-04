package io.foodust.escapeRoom.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.foodust.escapeRoom.object.ObjectManager;
import io.foodust.escapeRoom.screen.MainMenuScreen;
import lombok.Getter;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
 */
@Getter
public class EscapeRoom extends Game {

    private SpriteBatch batch;
    private OrthographicCamera camera;

    public EscapeRoom() {
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        setScreen(new MainMenuScreen(this));
    }
}
