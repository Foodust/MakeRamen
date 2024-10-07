package io.foodust.makeRamen.object.character;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import io.foodust.makeRamen.module.Modules;
import io.foodust.makeRamen.object.ObjectManager;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public abstract class BaseCharacter {
    protected String name = "none";

    protected final Modules modules = ObjectManager.getInstance().getModules();
    protected Texture texture;
    protected Sprite sprite;
    protected Rectangle rectangle;

    public BaseCharacter(String textureName) {
        this.texture = modules.getTextureModule().makeTexture(textureName);
        this.sprite = new Sprite(texture);
        this.rectangle = new Rectangle(this.sprite.getX(), this.sprite.getY(), this.sprite.getWidth(), this.sprite.getHeight());
    }

    public BaseCharacter(String textureName, float x, float y) {
        this(textureName);
        this.sprite.setPosition(x, y);
        this.rectangle.set(x, y, sprite.getWidth(), sprite.getHeight());
    }

    public BaseCharacter(String textureName, float x, float y, float width, float height) {
        this(textureName);
        this.sprite.setPosition(x, y);
        this.rectangle.set(x, y, width, height);
    }

    public BaseCharacter(String textureName, float x, float y, float width, float height, float sizeWidth, float sizeHeight) {
        this(textureName, x, y, width, height);
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

    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public void dispose() {
        texture.dispose();
    }
}
