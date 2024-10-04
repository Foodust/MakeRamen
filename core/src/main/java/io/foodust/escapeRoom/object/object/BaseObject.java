package io.foodust.escapeRoom.object.object;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
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
        this.rectangle = new Rectangle(this.sprite.getX(), this.sprite.getY(), this.sprite.getWidth(), this.sprite.getHeight());
    }
}
