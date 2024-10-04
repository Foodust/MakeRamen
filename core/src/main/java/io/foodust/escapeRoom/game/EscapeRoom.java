package io.foodust.escapeRoom.game;

import com.badlogic.gdx.Game;
import io.foodust.escapeRoom.screen.MainMenuScreen;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
 */
public class EscapeRoom extends Game {
    @Override
    public void create() {
        setScreen(new MainMenuScreen(this));
    }
}
