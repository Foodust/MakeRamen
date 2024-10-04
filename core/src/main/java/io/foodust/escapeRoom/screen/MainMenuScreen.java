package io.foodust.escapeRoom.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import io.foodust.escapeRoom.game.EscapeRoom;
import io.foodust.escapeRoom.object.ObjectManager;
import io.foodust.escapeRoom.object.object.BaseObject;
import io.foodust.escapeRoom.object.object.menu.EnterButton;
import io.foodust.escapeRoom.object.object.menu.QuitButton;
import io.foodust.escapeRoom.screen.stage.one.StageOne;

import java.util.ArrayList;
import java.util.List;

/**
 * First screen of the application. Displayed after the application is created.
 */
public class MainMenuScreen implements Screen {
    private final EscapeRoom escapeRoom;

    private final EnterButton enterObject;
    private final QuitButton quitObject;
    private final SpriteBatch spriteBatch;

    private List<BaseObject> objects = new ArrayList<>();
    private Float currentFadeTime = 0f;
    private Float maxFadeTime = 2f;

    private Boolean isFade = false;

    public MainMenuScreen(EscapeRoom escapeRoom) {
        this.escapeRoom = escapeRoom;
        this.spriteBatch = escapeRoom.getBatch();
        this.enterObject = new EnterButton("enter.png", ObjectManager.X / 2, ObjectManager.Y / 2 - 120);
        this.quitObject = new QuitButton("quit.png", ObjectManager.X / 2, ObjectManager.Y / 2 - 360);
        objects.add(enterObject);
        objects.add(quitObject);
    }

    @Override
    public void show() {
        // Prepare your screen here.
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        escapeRoom.getCamera().update();
        spriteBatch.setProjectionMatrix(escapeRoom.getCamera().combined);

        spriteBatch.begin();
        draw(delta);
        spriteBatch.end();

        if (!isFade) {
            update(Gdx.graphics.getDeltaTime());
        } else {
            fadeOut(delta);
        }
    }

    @Override
    public void resize(int width, int height) {
        // Resize your screen here. The parameters represent the new window size.
    }

    @Override
    public void pause() {
        // Invoked when your application is paused.
    }

    @Override
    public void resume() {
        // Invoked when your application is resumed after pause.
    }

    @Override
    public void hide() {
        // This method is called when another screen replaces this one.
    }

    @Override
    public void dispose() {
        // Destroy screen's assets here.
        objects.forEach(BaseObject::dispose);
        objects.clear();
    }

    public void draw(float deltaTime) {
        for (BaseObject object : objects) {
            object.getSprite().draw(spriteBatch);
        }
    }

    private void update(float deltaTime) {
        if (enterObject.isClicked(escapeRoom.getCamera())) {
            isFade = true;
        } else if (quitObject.isClicked(escapeRoom.getCamera())) {
            Gdx.app.exit();
        }
    }

    private void fadeOut(float deltaTime) {
        currentFadeTime += deltaTime;
        float alpha = 1 - Math.min(currentFadeTime / maxFadeTime, 1);
        objects.forEach(baseObject -> baseObject.getSprite().setAlpha(alpha));
        if (currentFadeTime >= maxFadeTime) {
            dispose();
            StageOne stageOne = new StageOne(escapeRoom);
            escapeRoom.setScreen(stageOne.getNorth());
        }
    }
}
