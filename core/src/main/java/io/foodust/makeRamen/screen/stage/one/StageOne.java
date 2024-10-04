package io.foodust.makeRamen.screen.stage.one;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import io.foodust.makeRamen.game.MakeRamen;
import io.foodust.makeRamen.module.Modules;
import io.foodust.makeRamen.object.ObjectManager;
import io.foodust.makeRamen.object.character.main.MainCharacter;
import io.foodust.makeRamen.object.object.BaseObject;
import io.foodust.makeRamen.object.object.stage.PlatObject;
import io.foodust.makeRamen.object.object.stage.StoveObject;
import io.foodust.makeRamen.object.object.stage.TrashObject;
import io.foodust.makeRamen.object.object.stage.item.*;
import io.foodust.makeRamen.object.object.stage.status.ItemStatus;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
public class StageOne implements Screen {

    private final Modules modules = ObjectManager.getInstance().getModules();

    private final SpriteBatch batch;
    private final OrthographicCamera camera;

    private final Texture background;

    private final MainCharacter character;

    private final List<BaseObject> objects = new ArrayList<>();
    private final List<StoveObject> stoves = new ArrayList<>();
    private final BeefObject beef;
    private final GosuObject gosu;
    private final NoodleObject noodle;
    private final PlatObject plat;
    private final PotObject pot;
    private final OnionObject onion;

    private final StoveObject stoveOne;
    private final StoveObject stoveTwo;
    private final StoveObject stoveThree;
    private final StoveObject stoveFour;

    private final TrashObject trash;

    private Sprite heldObject;

    private Float limitTime = 3000f;
    private Long score = 0L;

    public StageOne(MakeRamen makeRamen) {
        this.batch = makeRamen.getBatch();
        this.camera = makeRamen.getCamera();
        this.background = modules.getTextureModule().makeTexture("stage.png");
        this.character = new MainCharacter("character.png", 1600, 300, 70, 100, 1, 1);

        this.beef = new BeefObject("beef.png", 400f, 700f);
        this.noodle = new NoodleObject("noodle.png", 400f, 800f);
        this.gosu = new GosuObject("gosu.png", 250f, 700f);
        this.onion = new OnionObject("onion.png", 250f, 800f);

        this.pot = new PotObject("pot.png", 800f, 500f);

        this.plat = new PlatObject("plate.png", 1000f, 500f);

        this.stoveOne = new StoveObject("stove.png", 400f, 300f);
        this.stoveTwo = new StoveObject("stove.png", 400f, 400f);
        this.stoveThree = new StoveObject("stove.png", 500f, 300f);
        this.stoveFour = new StoveObject("stove.png", 500f, 400f);

        this.trash = new TrashObject("trash.png", 150f, 100);

        stoves.add(stoveOne);
        stoves.add(stoveTwo);
        stoves.add(stoveThree);
        stoves.add(stoveFour);

        objects.add(beef);
        objects.add(gosu);
        objects.add(noodle);
        objects.add(pot);
        objects.add(onion);
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

        character.getSprite().draw(batch);
        for (StoveObject stove : stoves) {
            stove.getSprite().draw(batch);
        }
        for (BaseObject object : objects) {
            object.getSprite().draw(batch);
        }

        if (heldObject != null) {
            Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(mousePos);
            heldObject.draw(batch);
            heldObject.setPosition(mousePos.x - heldObject.getWidth() / 2, mousePos.y - heldObject.getHeight() / 2);
        }

        batch.end();
        update(deltaTime);
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

        stoves.forEach(StoveObject::dispose);
        objects.forEach(BaseObject::dispose);
        trash.dispose();
    }

    private void update(float deltaTime) {
        limitTime -= deltaTime;

        if (character.getClickObject() instanceof StoveObject stove && character.getItemStatus().equals(ItemStatus.STOVE) && plat.isClicked(camera)) {
            calculateScore(stove);
            resetHand();
            return;
        }

        if (trash.isClicked(camera) && character.getClickObject() != null) {
            resetHand();
            return;
        }


        for (BaseObject object : objects) {
            if (object.isClicked(camera) && character.getClickObject() == null) {
                character.setClickObject(object);
                character.setItemStatus(object.getItemStatus());
                heldObject = modules.getSpriteModule().makeSprite(object.getTexture());
                break;
            }
        }
        for (StoveObject stove : stoves) {
            if (!stove.isClicked(camera)) continue;
            if (character.getClickObject() == null && character.getItemStatus() == null) {
                character.setClickObject(stove);
                character.setItemStatus(ItemStatus.STOVE);
                break;
            } else if (character.getClickObject() != null && !stove.getIsItem().get(character.getItemStatus())) {
                stove.getIsItem().put(character.getItemStatus(), true);
                resetHand();
                break;
            }
        }

    }

    private void calculateScore(StoveObject stove) {
        long addScore = 0;
        long itemCount = stove.getIsItem().entrySet().stream().filter(Map.Entry::getValue).count();
        if (stove.getCookTime() < 5 || itemCount == 2) {
            addScore -= 60;
            character.playAngry();
        } else if (stove.getCookTime() < 12 || itemCount == 3) {
            addScore += 10;
            character.playGood();
        } else if (stove.getCookTime() < 15 && itemCount > 4) {
            addScore += 40;
            character.playPerfect();
        } else if (stove.getCookTime() < 25 || itemCount <= 1) {
            addScore -= 60;
            character.playAngry();
        } else if (stove.getCookTime() >= 25) {
            addScore -= 120;
            character.playVeryAngry();
        }
        addScore += itemCount * 20;
        score += addScore;
    }

    private void resetHand() {
        character.setClickObject(null);
        character.setItemStatus(ItemStatus.NONE);
        heldObject = null;
    }
}
