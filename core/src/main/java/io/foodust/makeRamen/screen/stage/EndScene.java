package io.foodust.makeRamen.screen.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import io.foodust.makeRamen.game.MakeRamen;
import io.foodust.makeRamen.module.Modules;
import io.foodust.makeRamen.object.ObjectManager;
import io.foodust.makeRamen.object.character.main.MainCharacter;
import io.foodust.makeRamen.object.object.menu.QuitButton;
import io.foodust.makeRamen.object.object.menu.RestartButton;

public class EndScene implements Screen {

    private final Modules modules = ObjectManager.getInstance().getModules();
    private final MakeRamen makeRamen;
    private final Long score;

    private final Texture background;
    private final RestartButton restartButton;
    private final QuitButton quitButton;

    private final BitmapFont scoreText;

    private final SpriteBatch batch;
    private final OrthographicCamera camera;

    private final MainCharacter endCharacter;

    public EndScene(MakeRamen makeRamen, Long score) {
        this.makeRamen = makeRamen;
        this.batch = makeRamen.getBatch();
        this.camera = makeRamen.getCamera();
        this.score = score;

        this.background = modules.getTextureModule().makeTexture("background.png");
        this.restartButton = new RestartButton("restart.png", 700, 200);
        this.quitButton = new QuitButton("quit.png", 1200, 200);

        this.scoreText = modules.getFontManager().generateFont(70);
        this.endCharacter = new MainCharacter("character.png", 1500, 200);
        head();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0, 0, 0, 1);
        batch.begin();
        batch.draw(background, 0, 0);

        scoreText.draw(batch, score.toString(), ObjectManager.X / 2, ObjectManager.Y / 2);
        restartButton.draw(batch);
        quitButton.draw(batch);
        endCharacter.draw(batch);

        batch.end();
        update();
    }

    private void update() {
        if (restartButton.isClicked(camera)) {
            makeRamen.setScreen(new RamenStage(makeRamen));
        }
        if (quitButton.isClicked(camera)) {
            Gdx.app.exit();
        }
    }

    private void head() {
        if (score <= 0) {
            endCharacter.playVeryAngry();
        } else if (score <= 300) {
            endCharacter.playAngry();
        } else if (score <= 600) {
            endCharacter.playNormal();
        } else if (score <= 1200) {
            endCharacter.playGood();
        } else if (score <= 2400) {
            endCharacter.playExcellent();
        } else if (score <= 3600) {
            endCharacter.playPerfect();
        }
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
        batch.dispose();
        scoreText.dispose();
        restartButton.dispose();
        quitButton.dispose();
        endCharacter.dispose();
    }
}
