package io.foodust.makeRamen.object.object.stage.item;

import io.foodust.makeRamen.object.object.BaseObject;
import io.foodust.makeRamen.object.object.stage.status.ItemStatus;

public class WaterObject extends BaseObject {
    public WaterObject(String textureName, float x, float y) {
        super(textureName,x,y);
        this.setItemStatus(ItemStatus.WATER);
//        setScale(0.8f);
    }
}
