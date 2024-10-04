package io.foodust.escapeRoom.object.object;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import io.foodust.escapeRoom.object.ObjectManager;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseObject {
    protected Texture texture;
    protected Sprite sprite;
    protected Rectangle rectangle;

    public BaseObject(String textureName) {
        this.texture = new Texture(textureName);
        this.sprite = new Sprite(texture);
        this.sprite.setOriginCenter();
    }

    public BaseObject(String textureName, float x, float y) {
        this(textureName);
        this.sprite.setCenter(x, y);
        this.rectangle = new Rectangle(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        updateBounds();
    }

    public BaseObject(String textureName, float x, float y, float sizeWidth, float sizeHeight) {
        this(textureName, x, y);
        this.sprite.setSize(sizeWidth, sizeHeight);
    }

    public void updateBounds(float x, float y, float width, float height) {
        this.rectangle.x = x;
        this.rectangle.y = y;
        this.rectangle.width = width;
        this.rectangle.height = height;
    }

    public void updateBounds() {
        updateBounds(this.sprite.getX(), this.sprite.getY(), this.sprite.getWidth(), this.sprite.getHeight());
    }

    public void setScale(float percentage) {
        sprite.setScale(percentage);
        updateBounds();
    }

    public Boolean isClicked(OrthographicCamera camera) {
        return ObjectManager.getInstance().getModules().getInputModule().getTouch(camera, this.getRectangle());
    }

    public void dispose() {
        texture.dispose();
    }
}
