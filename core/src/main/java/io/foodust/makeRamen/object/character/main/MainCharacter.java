package io.foodust.makeRamen.object.character.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import io.foodust.makeRamen.object.character.BaseCharacter;
import io.foodust.makeRamen.object.object.BaseObject;
import io.foodust.makeRamen.object.object.stage.TimeObject;
import io.foodust.makeRamen.object.object.stage.status.ItemStatus;
import io.foodust.makeRamen.screen.stage.one.StageOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MainCharacter extends BaseCharacter {

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

    private Float nowStatusTime = 0f;
    private CharacterStatus characterStatus = CharacterStatus.NORMAL;

    private Float rotationTime = 0f;
    private final Float maxRotation = 20f;
    private Float rotationSpeed = 2f;

    public MainCharacter(String textureName, float x, float y) {
        super(textureName, x, y);
        this.headSprite = modules.getSpriteModule().makeSprite(modules.getTextureModule().makeTexture("normal2.png"));
        this.headSprite.setPosition(x - 50, y + 100);
        this.headSprite.setScale(0.5f);

//        this.noGosu = modules.getTextureModule().makeTexture("noGosu.png");
//        this.noRamen = modules.getTextureModule().makeTexture("noRamen.png");
//        this.good = modules.getTextureModule().makeTexture("good.png");
//        this.perfect = modules.getTextureModule().makeTexture("perfect.png");
//        this.excellent = modules.getTextureModule().makeTexture("excellent.png");
//        this.angry = modules.getTextureModule().makeTexture("angry.png");
//        this.veryAngry = modules.getTextureModule().makeTexture("veryAngry.png");

    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        headSprite.draw(batch);
        update();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    private void update() {
        float deltaTime = Gdx.graphics.getDeltaTime();
        nowStatusTime += deltaTime;
        rotationTime += deltaTime;
        if (nowStatusTime >= 3f) {
            playNormal();
            nowStatusTime = 0f;
        }
        headSprite.setOrigin(sprite.getWidth() / 2, 0);

        float rotation = maxRotation * MathUtils.sin(rotationTime * (rotationSpeed * (TimeObject.maxLimitTime / Math.max(TimeObject.nowLimitTime, 0.1f))));
        headSprite.setRotation(rotation);
    }

    public long playNoGosu() {
        characterStatus = CharacterStatus.NO_GOSU;
        headSprite.setTexture(noGosu);
        return -150;
    }

    public long playNoRamen() {
        characterStatus = CharacterStatus.NO_RAMEN;
        headSprite.setTexture(noRamen);
        return -200;
    }

    public void playNormal() {
        characterStatus = CharacterStatus.NORMAL;
        headSprite.setTexture(getTexture());
    }

    public long playGood() {
        characterStatus = CharacterStatus.GOOD;
        headSprite.setTexture(good);
        return 50;
    }

    public long playPerfect() {
        characterStatus = CharacterStatus.PERFECT;
        headSprite.setTexture(perfect);
        return 100;
    }

    public long playExcellent() {
        characterStatus = CharacterStatus.EXCELLENT;
        headSprite.setTexture(excellent);
        return 100;
    }

    public long playAngry() {
        characterStatus = CharacterStatus.ANGRY;
        headSprite.setTexture(angry);
        return -60;
    }

    public long playVeryAngry() {
        characterStatus = CharacterStatus.VERY_ANGRY;
        headSprite.setTexture(veryAngry);
        return -120;
    }

}
