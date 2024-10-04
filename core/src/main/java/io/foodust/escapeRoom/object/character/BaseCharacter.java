package io.foodust.escapeRoom.object.character;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public abstract class BaseCharacter {
    protected String name = "none";
    protected long level = 0;
    protected long hp = 10;
    protected float speed = 0.25f;

    protected Texture texture;
    protected Sprite sprite;
    protected Rectangle rectangle;

    public BaseCharacter(String textureName) {
        this.texture = new Texture(textureName);
        this.sprite = new Sprite(texture);
        this.rectangle = new Rectangle(this.sprite.getX(), this.sprite.getY(), this.sprite.getWidth(), this.sprite.getHeight());
    }

    public BaseCharacter(String textureName, float x, float y, float width, float height) {
        this(textureName);
        this.sprite.setPosition(x, y);
        this.sprite.setBounds(x, y, width, height);
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

    public void dispose() {
        texture.dispose();
    }
}
