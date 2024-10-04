package io.foodust.makeRamen.object.object.stage.item;

import io.foodust.makeRamen.object.object.BaseObject;
import io.foodust.makeRamen.object.object.stage.status.ItemStatus;

public class PotObject extends BaseObject {
    public PotObject(String textureName, float x, float y) {
        super(textureName,x,y);
        this.setItemStatus(ItemStatus.POT);
    }
}
