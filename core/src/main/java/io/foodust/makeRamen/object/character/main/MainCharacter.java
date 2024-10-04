package io.foodust.makeRamen.object.character.main;

import com.badlogic.gdx.graphics.g2d.Sprite;
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

    public MainCharacter(String textureName, float x, float y, float width, float height, float sizeWidth, float sizeHeight) {
        super(textureName, x, y, width, height, sizeWidth, sizeHeight);
        this.head = ObjectManager.getInstance().getModules().getSpriteModule().makeSprite(ObjectManager.getInstance().getModules().getTextureModule().makeTexture("normal.png"));
        this.head.setX(x);
        this.head.setY(y - 100);
    }

    public void playNoGosu() {

    }

    public void playNormal() {

    }

    public void playGood() {

    }
    public void playPerfect(){

    }

    public void playAngry() {

    }

    public void playVeryAngry() {

    }

}
