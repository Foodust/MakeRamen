package io.foodust.makeRamen.screen.stage.one;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
    private final WaterObject water;

    private final StoveObject stoveOne;
    private final StoveObject stoveTwo;
    private final StoveObject stoveThree;
    private final StoveObject stoveFour;

    private final TrashObject trash;

    private Float limitTime = 3000f;
    private Long score = 0L;

    public StageOne(MakeRamen makeRamen) {
        this.batch = makeRamen.getBatch();
        this.camera = makeRamen.getCamera();
        this.background = modules.getTextureModule().makeTexture("stage.png");
        this.character = new MainCharacter("character", 1600, 300, 70, 100, 1, 1);

        this.beef = new BeefObject("beef.png", 100f, 100f);
        this.noodle = new NoodleObject("noodle.png", 100f, 200f);
        this.gosu = new GosuObject("gosu.png", 200f, 100f);
        this.onion = new OnionObject("ramen.png", 200f, 200f);

        this.pot = new PotObject("pot.png", 600f, 400f);
        this.water = new WaterObject("water.png", 600f, 600f);

        this.plat = new PlatObject("plat.png", 1200f, 600f);

        this.stoveOne = new StoveObject("stove.png", 100f, 600f);
        this.stoveTwo = new StoveObject("stove.png", 100f, 800f);
        this.stoveThree = new StoveObject("stove.png", 200f, 600f);
        this.stoveFour = new StoveObject("stove.png", 200f, 800f);

        this.trash = new TrashObject("trash.png", 50f, 800);

        stoves.add(stoveOne);
        stoves.add(stoveTwo);
        stoves.add(stoveThree);
        stoves.add(stoveFour);

        objects.add(beef);
        objects.add(gosu);
        objects.add(noodle);
        objects.add(pot);
        objects.add(onion);
        objects.add(water);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float deltaTime) {
        ScreenUtils.clear(0, 0, 0, 1);
        batch.begin();

        for (StoveObject stove : stoves) {
            stove.render(batch);
        }
        for (BaseObject object : objects) {
            object.getSprite().draw(batch);
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
    }
}
