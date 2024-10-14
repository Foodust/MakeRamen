package io.foodust.makeRamen.screen.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import io.foodust.makeRamen.game.MakeRamen;
import io.foodust.makeRamen.module.Modules;
import io.foodust.makeRamen.object.ObjectManager;
import io.foodust.makeRamen.object.character.main.MainCharacter;
import io.foodust.makeRamen.object.object.BaseObject;
import io.foodust.makeRamen.object.object.menu.QuitButton;
import io.foodust.makeRamen.object.object.menu.RestartButton;
import io.foodust.makeRamen.object.object.stage.*;
import io.foodust.makeRamen.object.object.stage.item.*;
import io.foodust.makeRamen.object.object.stage.status.ItemStatus;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Getter
public class RamenStage implements Screen {
    private final MakeRamen makeRamen;
    private final Modules modules = ObjectManager.getInstance().getModules();

    private final SpriteBatch batch;
    private final OrthographicCamera camera;

    private final Texture background;

    private final MainCharacter character;

    private final BitmapFont scoreText;

    private final List<BaseObject> objects = new ArrayList<>();
    private final List<StoveObject> stoves = new ArrayList<>();
    private final List<RamenObject> ramens = new ArrayList<>();

    private final PlatObject plat;

    private final TrashObject trash;
    private final TimeObject timeObject;

    private BaseObject heldObject;

    private Long score = 0L;

    private final RestartButton restartButton;
    private final QuitButton quitButton;
    private final Texture white;
    private Boolean stopGame = false;

    private Float delayTime = 3f;

    private final Random random = new Random();

    public RamenStage(MakeRamen makeRamen) {
        this.makeRamen = makeRamen;
        this.batch = makeRamen.getBatch();
        this.camera = makeRamen.getCamera();
        this.background = modules.getTextureModule().makeTexture("stage.png");
        this.character = new MainCharacter("character.png", 1600, 300);

        this.plat = new PlatObject("plate.png", 1120f, 400f);
        this.trash = new TrashObject("trash.png", 70f, 180f);

        this.timeObject = new TimeObject("time.png", ObjectManager.X / 2, ObjectManager.Y - 200);

        this.restartButton = new RestartButton("enter.png", ObjectManager.X / 2, ObjectManager.Y / 2 + 200);
        this.quitButton = new QuitButton("quit.png", ObjectManager.X / 2, ObjectManager.Y / 2);
        this.white = modules.getTextureModule().makeTexture("white.png");

        stoves.add(new StoveObject("stove.png", 290f, 470f));
        stoves.add(new StoveObject("stove.png", 560f, 470f));
        stoves.add(new StoveObject("stove.png", 320f, 330f));
        stoves.add(new StoveObject("stove.png", 590f, 330f));

        objects.add(new BeefObject("beef.png", 560f, 730f));
        objects.add(new GosuObject("gosu.png", 400f, 730f));
        objects.add(new NoodleObject("noodle.png", 560f, 900f));
        objects.add(new PotObject("pot.png", 850f, 320f));
        objects.add(new WaterObject("water.png", 850f, 520f));
        objects.add(new OnionObject("onion.png", 400f, 900f));

        scoreText = modules.getFontManager().generateFont(50);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float deltaTime) {

        ScreenUtils.clear(0, 0, 0, 1);
        batch.begin();

        batch.draw(background, 0, 0);
        trash.getSprite().draw(batch);
        plat.getSprite().draw(batch);
        timeObject.draw(batch);
        character.draw(batch);
        scoreText.draw(batch, score.toString(), 1400f, 700f);

        for (StoveObject stove : stoves) {
            stove.draw(batch);
        }
        for (RamenObject ramen : ramens) {
            ramen.draw(batch);
        }
        for (BaseObject object : objects) {
            object.getSprite().draw(batch);
        }

        if (heldObject != null) {
            Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(mousePos);
            float x = mousePos.x - heldObject.getSprite().getWidth() / 2;
            float y = mousePos.y - heldObject.getSprite().getHeight() / 2;
            heldObject.getSprite().setPosition(x, y);
            heldObject.draw(batch);
            if (heldObject instanceof RamenObject ramenObject) {
                ramenObject.move(x, y);
            }
        }
        if (stopGame) {
            batch.draw(white, 0, 0);
            restartButton.draw(batch);
            quitButton.draw(batch);
        }

        batch.end();
        update();
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
        background.dispose();
        character.dispose();

        ramens.forEach(RamenObject::dispose);
        objects.forEach(BaseObject::dispose);

        scoreText.dispose();
        timeObject.dispose();

        trash.dispose();
        white.dispose();
        quitButton.dispose();
        restartButton.dispose();
    }

    private void update() {
        if (updateEscape()) return;
        if (updateTime()) return;
        timeObject.update();

        if (character.getClickObject() instanceof RamenObject ramen && character.getItemStatus().equals(ItemStatus.STOVE) && plat.isClicked(camera)) {
            calculateScore(ramen);
            resetHand();
            return;
        }

        if (trash.isClicked(camera) && character.getClickObject() != null) {
            resetHand();
            return;
        }
        character.update();
        updateRamen();
        updateStove();
        updateObject();
    }

    private Boolean updateTime() {
        if (TimeObject.nowLimitTime > 0) return false;

        if (delayTime == 3f) {
            modules.getSoundModule().makeSound("enter.wav", 0.9f);
        }
        delayTime -= Gdx.graphics.getDeltaTime();
        if (delayTime < 0) {
            makeRamen.setScreen(new EndScene(makeRamen, score));
        }
        return true;
    }

    private Boolean updateEscape() {
        if (stopGame && modules.getInputModule().getKeyBoardTouch(Input.Keys.ESCAPE)) {
            stopGame = false;
        } else if (!stopGame && modules.getInputModule().getKeyBoardTouch(Input.Keys.ESCAPE)) {
            stopGame = true;
        }
        if (!stopGame) return false;

        if (restartButton.isClicked(camera)) {
            makeRamen.setScreen(new RamenStage(makeRamen));
        }
        if (quitButton.isClicked(camera)) {
            Gdx.app.exit();
        }
        return true;
    }

    private void updateRamen() {
        for (RamenObject ramen : ramens) {
            if (!ramen.isClicked(camera)) continue;
            if (character.getClickObject() == null && character.getItemStatus().equals(ItemStatus.NONE)) {
                character.setClickObject(ramen);
                character.setItemStatus(ItemStatus.STOVE);
                heldObject = ramen;
                ramens.remove(ramen);
                break;
            } else if (character.getClickObject() != null && !ramen.getIsItem().get(character.getItemStatus())) {
                if (character.getItemStatus() != ItemStatus.POT && !ramen.getIsItem().get(ItemStatus.POT)) {
                    continue;
                }
                ramen.getIsItem().put(character.getItemStatus(), true);
                resetHand();
                break;
            }
        }
    }

    private void updateStove() {
        for (StoveObject stove : stoves) {
            float stoveCenterX = stove.getSprite().getX() + stove.getSprite().getWidth() / 2;
            float stoveCenterY = stove.getSprite().getY() + stove.getSprite().getHeight() / 2;

            boolean ramenExists = ramens.stream().anyMatch(ramen ->
                Math.abs(ramen.getSprite().getX() + ramen.getSprite().getWidth() / 2 - stoveCenterX) < 1 &&
                    Math.abs(ramen.getSprite().getY() + ramen.getSprite().getHeight() / 2 - stoveCenterY) < 1
            );
            if (ramenExists) continue;
            if (!stove.isClicked(camera) || character.getItemStatus() != ItemStatus.POT) continue;
            RamenObject ramenObject = new RamenObject("pot.png", stoveCenterX, stoveCenterY);
            ramenObject.getIsItem().put(ItemStatus.POT, true);
            ramens.add(ramenObject);
            resetHand();
        }
    }

    private void updateObject() {
        for (BaseObject object : objects) {
            if (object.isClicked(camera) && character.getClickObject() == null) {
                character.setClickObject(object);
                character.setItemStatus(object.getItemStatus());
                heldObject = new BaseObject(object.getTexture());
                break;
            }
        }
    }

    private void calculateScore(RamenObject ramen) {
        long itemCount = ramen.getIsItem().entrySet().stream().filter(Map.Entry::getValue).count();
        boolean isGosu = ramen.getIsItem().entrySet().stream().anyMatch(filter -> filter.getKey().equals(ItemStatus.GOSU));
        score += getScore(isGosu, ramen, itemCount);
    }

    private long getScore(Boolean isGosu, RamenObject ramen, long itemCount) {
        // 특별한 경우 처리
        if (isGosu && random.nextInt(100) == 0) {
            return character.playNoGosu();
        }
        if (random.nextInt(100) == 1) {
            return character.playNoRamen();
        }
        // 일반적인 경우 처리
        float cookTime = ramen.getCookTime();

        if (cookTime >= ramen.getMaxCookTime() || itemCount <= 2) {
            return character.playVeryAngry();
        }
        if (cookTime < 4 || itemCount <= 3) {
            return character.playAngry();
        }
        if (cookTime < 8 || itemCount <= 4) {
            return character.playGood();
        }
        if (cookTime < 10) {
            return (random.nextInt(100) <= 10) ? character.playExcellent() : character.playPerfect();
        }
        return character.playAngry();
    }

    private void resetHand() {
        character.setClickObject(null);
        character.setItemStatus(ItemStatus.NONE);
        heldObject.dispose();
        heldObject = null;
    }
}
