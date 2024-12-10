package io.foodust.makeRamen.object.character.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import io.foodust.makeRamen.object.character.BaseCharacter;
import io.foodust.makeRamen.object.object.BaseObject;
import io.foodust.makeRamen.object.object.stage.TimeObject;
import io.foodust.makeRamen.object.object.stage.status.ItemStatus;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MainCharacter extends BaseCharacter {

    private Texture normal;
    private Texture noGosu;
    private Texture noRamen;
    private Texture good;
    private Texture perfect;
    private Texture excellent;
    private Texture angry;
    private Texture veryAngry;

    private Sprite headSprite;
    private BaseObject clickObject = null;
    private ItemStatus itemStatus = ItemStatus.NONE;

    private CharacterStatus characterStatus = CharacterStatus.NORMAL;

    private Float rotationTime = 0f;
    private final Float maxRotation = 20f;
    private Float rotationSpeed = 2f;
    private Float characterSound = 0.9f;

    public MainCharacter(String textureName, float x, float y) {
        super(textureName, x, y);
        this.headSprite = modules.getSpriteModule().makeSprite(modules.getTextureModule().makeTexture("normal.png"));
        this.headSprite.setPosition(x + 90, y + 280);
//        this.headSprite.setScale(0.75f, 0.75f);
//        this.sprite.setScale(0.65f, 0.65f);

        this.normal = modules.getTextureModule().makeTexture("normal.png");
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        headSprite.draw(batch);
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    public void update() {
        float deltaTime = Gdx.graphics.getDeltaTime();
        rotationTime += deltaTime;
        headSprite.setOrigin(sprite.getWidth() / 2, 0);

        float rotation = maxRotation * MathUtils.sin(rotationTime * (rotationSpeed * (TimeObject.maxLimitTime / Math.max(TimeObject.nowLimitTime, 10f))));
        headSprite.setRotation(rotation);
    }

    public long playNoGosu() {
        modules.getSoundModule().makeSound("noGosu.wav", characterSound);
        return -150;
    }

    public long playNoRamen() {
        modules.getSoundModule().makeSound("noRamen.wav", characterSound);
        return -200;
    }

    public void playNormal() {
        modules.getSoundModule().makeSound("normal.wav", characterSound);
    }

    public long playGood() {
        modules.getSoundModule().makeSound("good.wav", characterSound);
        return 50;
    }

    public long playPerfect() {
        characterStatus = CharacterStatus.PERFECT;
        modules.getSoundModule().makeSound("great.wav", characterSound);
        return 100;
    }

    public long playExcellent() {
        modules.getSoundModule().makeSound("excellent.wav", characterSound);
        return 200;
    }

    public long playAngry() {
        modules.getSoundModule().makeSound("angry.wav", characterSound);
        return -60;
    }

    public long playVeryAngry() {
        modules.getSoundModule().makeSound("veryAngry.wav", characterSound);
        return -120;
    }

}
