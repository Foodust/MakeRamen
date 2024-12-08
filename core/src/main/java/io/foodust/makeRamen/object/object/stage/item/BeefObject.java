package io.foodust.makeRamen.object.object.stage.item;

import io.foodust.makeRamen.object.object.BaseObject;
import io.foodust.makeRamen.object.object.stage.status.ItemStatus;

public class BeefObject extends BaseObject {
    public BeefObject(String textureName, float x, float y) {
        super(textureName, x, y);
        this.setItemStatus(ItemStatus.BEEF);
//        setScale(0.7f);
    }
}
