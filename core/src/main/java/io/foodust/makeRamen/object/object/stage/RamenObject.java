package io.foodust.makeRamen.object.object.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.foodust.makeRamen.module.Modules;
import io.foodust.makeRamen.object.ObjectManager;
import io.foodust.makeRamen.object.object.BaseObject;
import io.foodust.makeRamen.object.object.stage.status.ItemStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class RamenObject extends BaseObject {
    private final Modules modules = ObjectManager.getInstance().getModules();
    private final LinkedHashMap<ItemStatus, Boolean> isItem = new LinkedHashMap<>();

    private final Sprite pot;
    private final Sprite beef;
    private final Sprite gosu;
    private final Sprite noodle;
    private final Sprite onion;
    private final Sprite water;
    private final Music boilMusic;

    private final List<Texture> objets = new ArrayList<>();

    private Float cookTime = 0f;
    private Float maxCookTime = 13f;

    public RamenObject(String textureName, float x, float y) {
        super(textureName, x, y);
        Arrays.stream(ItemStatus.values()).forEach(value -> isItem.put(value, false));
        Texture potTexture = modules.getTextureModule().makeTexture("pot.png");
        Texture noodleTexture = modules.getTextureModule().makeTexture("noodle.png");
        Texture onionTexture = modules.getTextureModule().makeTexture("onion.png");
        Texture beefTexture = modules.getTextureModule().makeTexture("beef.png");
        Texture gosuTexture = modules.getTextureModule().makeTexture("gosu.png");
        Texture waterTexture = modules.getTextureModule().makeTexture("water.png");

        this.pot = modules.getSpriteModule().makeSprite(potTexture);
        this.noodle = modules.getSpriteModule().makeSprite(noodleTexture);
        this.onion = modules.getSpriteModule().makeSprite(onionTexture);
        this.beef = modules.getSpriteModule().makeSprite(beefTexture);
        this.gosu = modules.getSpriteModule().makeSprite(gosuTexture);
        this.water = modules.getSpriteModule().makeSprite(waterTexture);

        objets.add(potTexture);
        objets.add(noodleTexture);
        objets.add(onionTexture);
        objets.add(beefTexture);
        objets.add(gosuTexture);
        objets.add(waterTexture);

        updatePosition();
        boilMusic = modules.getSoundModule().makeSound("boiled.wav", 0.3f, true);
        boilMusic.play();
    }


    public void move(float x, float y) {
        sprite.setPosition(x, y);
        updatePosition();
        updateBounds();
    }


    private void updatePosition() {
        pot.setPosition(sprite.getX(), sprite.getY());
        noodle.setPosition(sprite.getX(), sprite.getY());
        onion.setPosition(sprite.getX(), sprite.getY());
        beef.setPosition(sprite.getX(), sprite.getY());
        gosu.setPosition(sprite.getX(), sprite.getY());
        water.setPosition(sprite.getX(), sprite.getY());
    }

    @Override
    public void draw(SpriteBatch batch) {
        cookTime += Gdx.graphics.getDeltaTime();
        updatePotColor();
        isItem.forEach((key, value) -> {
            if (value) {
                switch (key) {
                    case POT -> pot.draw(batch);
                    case NOODLE -> noodle.draw(batch);
                    case ONION -> onion.draw(batch);
                    case BEEF -> beef.draw(batch);
                    case GOSU -> gosu.draw(batch);
                    case WATER -> water.draw(batch);
                }
            }
        });
    }

    private void updatePotColor() {
        if (cookTime > maxCookTime) {
            if(pot.getColor().r != 0){
                modules.getSoundModule().makeSound("burn.wav", 0.5f);
            }
            pot.setColor(0, 0, 0, 1);
        } else {
            float alpha = Math.min(cookTime / 20, 1f); // 알파값으로 사용
            pot.setColor(1 - alpha, 1 - alpha, 1 - alpha, 1);
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        objets.forEach(Texture::dispose);
        isItem.clear();
        boilMusic.dispose();
    }
}
