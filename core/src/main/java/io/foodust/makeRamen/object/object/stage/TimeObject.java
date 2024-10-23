package io.foodust.makeRamen.object.object.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.foodust.makeRamen.object.object.BaseObject;
import lombok.Getter;

@Getter
public class TimeObject extends BaseObject {

    private final Sprite bar;
    private final Float maxWidth;
    public static Float maxLimitTime = 300f;
    public static Float nowLimitTime = maxLimitTime;
    private final Music music;
    public TimeObject(String textureName, float x, float y) {
        super(textureName, x, y);
        this.bar = modules.getSpriteModule().makeSprite(modules.getTextureModule().makeTexture("beef.png"));
        this.bar.setPosition(x, y);
        this.maxWidth = sprite.getWidth();
        music = modules.getSoundModule().makeSound("time.wav", 0.3f, true);
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
            music.stop();
            modules.getSoundModule().makeSound("end.wav",1f);
            return;
        }
        float progress = nowLimitTime / maxLimitTime;
        sprite.setSize(maxWidth * progress, sprite.getHeight());
    }
}
