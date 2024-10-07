package io.foodust.makeRamen.object.object.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.foodust.makeRamen.object.object.BaseObject;
import lombok.Getter;

@Getter
public class TimeObject extends BaseObject {

    private final Sprite bar;
    private final Float maxWidth;
    public static Float maxLimitTime = 3000f;
    public static Float nowLimitTime = maxLimitTime;

    public TimeObject(String textureName, float x, float y) {
        super(textureName, x, y);
        this.bar = modules.getSpriteModule().makeSprite(modules.getTextureModule().makeTexture("beef.png"));
        this.maxWidth = sprite.getWidth();
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        this.bar.draw(batch);
    }

    @Override
    public void update() {
        nowLimitTime -= Gdx.graphics.getDeltaTime();
        if (nowLimitTime <= 0) {

            return;
        }
        float progress = nowLimitTime / maxLimitTime;
        sprite.setSize(maxWidth * progress, sprite.getHeight());
    }
}
