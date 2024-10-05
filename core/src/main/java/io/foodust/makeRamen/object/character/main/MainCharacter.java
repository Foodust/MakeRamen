package io.foodust.makeRamen.object.character.main;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.foodust.makeRamen.object.ObjectManager;
import io.foodust.makeRamen.object.character.BaseCharacter;
import io.foodust.makeRamen.object.object.BaseObject;
import io.foodust.makeRamen.object.object.stage.status.ItemStatus;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MainCharacter extends BaseCharacter {

    private Sprite head;
    private BaseObject clickObject = null;
    private ItemStatus itemStatus = ItemStatus.NONE;

    public MainCharacter(String textureName, float x, float y) {
        super(textureName, x, y);
        this.head = ObjectManager.getInstance().getModules().getSpriteModule().makeSprite(ObjectManager.getInstance().getModules().getTextureModule().makeTexture("normal.png"));
        this.head.setPosition(x-50, y+100);
        this.head.setScale(0.5f);
    }

    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
        head.draw(batch);
    }

    public void playNoGosu() {

    }

    public void playNormal() {

    }

    public void playGood() {

    }

    public void playPerfect() {

    }

    public void playAngry() {

    }

    public void playVeryAngry() {

    }

}
