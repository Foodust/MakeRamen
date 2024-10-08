package io.foodust.makeRamen.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import io.foodust.makeRamen.game.MakeRamen;
import io.foodust.makeRamen.module.Modules;
import io.foodust.makeRamen.object.ObjectManager;
import io.foodust.makeRamen.object.object.BaseObject;
import io.foodust.makeRamen.object.object.menu.EnterButton;
import io.foodust.makeRamen.object.object.menu.QuitButton;
import io.foodust.makeRamen.screen.stage.one.RamenStage;

import java.util.ArrayList;
import java.util.List;

/**
 * First screen of the application. Displayed after the application is created.
 */
public class MainMenuScreen implements Screen {
    private final MakeRamen makeRamen;

    private final Modules modules = ObjectManager.getInstance().getModules();

    private final EnterButton enterObject;
    private final QuitButton quitObject;
    private final SpriteBatch spriteBatch;
    private final Texture background;
    private final List<BaseObject> objects = new ArrayList<>();

    public MainMenuScreen(MakeRamen makeRamen) {
        this.makeRamen = makeRamen;
        this.spriteBatch = makeRamen.getBatch();
        this.background = modules.getTextureModule().makeTexture("background.png");
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
        makeRamen.getCamera().update();
        spriteBatch.setProjectionMatrix(makeRamen.getCamera().combined);

        spriteBatch.begin();
        draw(delta);
        spriteBatch.end();

        update(Gdx.graphics.getDeltaTime());
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
        background.dispose();
        objects.forEach(BaseObject::dispose);
        objects.clear();
    }

    public void draw(float deltaTime) {
        spriteBatch.draw(background, 0, 0, background.getWidth(), background.getHeight());
        for (BaseObject object : objects) {
            object.getSprite().draw(spriteBatch);
        }
    }

    private void update(float deltaTime) {
        if (enterObject.isClicked(makeRamen.getCamera())) {
            dispose();
            RamenStage ramenStage = new RamenStage(makeRamen);
            makeRamen.setScreen(ramenStage);
        } else if (quitObject.isClicked(makeRamen.getCamera())) {
            Gdx.app.exit();
        }
    }

}
