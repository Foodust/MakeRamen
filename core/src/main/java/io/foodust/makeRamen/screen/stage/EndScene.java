package io.foodust.makeRamen.screen.stage;

import com.badlogic.gdx.Screen;
import io.foodust.makeRamen.game.MakeRamen;
import io.foodust.makeRamen.object.object.menu.QuitButton;
import io.foodust.makeRamen.object.object.menu.RestartButton;

public class EndScene implements Screen {

    private final MakeRamen makeRamen;
    private final Long score;

    private final RestartButton restartButton;
    private final QuitButton quitButton;

    public EndScene(MakeRamen makeRamen, Long score) {
        this.makeRamen = makeRamen;
        this.score = score;

        this.restartButton = new RestartButton("enter.png", 700, 200);
        this.quitButton = new QuitButton("quit.png", 1200, 200);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {

    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
