package io.foodust.makeRamen.object.object.stage.item;

import io.foodust.makeRamen.object.object.BaseObject;
import io.foodust.makeRamen.object.object.stage.status.ItemStatus;

public class GosuObject extends BaseObject {
    public GosuObject(String textureName, float x, float y) {
        super(textureName,x,y);
        this.setItemStatus(ItemStatus.GOSU);
    }
}
