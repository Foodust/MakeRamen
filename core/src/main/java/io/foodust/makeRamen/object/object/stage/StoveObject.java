package io.foodust.makeRamen.object.object.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import io.foodust.makeRamen.game.MakeRamen;
import io.foodust.makeRamen.module.Modules;
import io.foodust.makeRamen.object.ObjectManager;
import io.foodust.makeRamen.object.object.BaseObject;
import io.foodust.makeRamen.object.object.stage.status.ItemStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Getter
@Setter
public class StoveObject extends BaseObject {

    private final Animation<TextureRegion> fireAnimation;
    private Float stateTime = 0f;

    public StoveObject(String textureName, float x, float y) {
        super(textureName, x, y);
        fireAnimation = modules.getAnimationModule().loadAnimation("fire.png", 0.025f);
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion keyFrame = fireAnimation.getKeyFrame(stateTime, true);
        batch.draw(keyFrame, getSprite().getOriginX(), getSprite().getOriginY());
    }
}
