package io.foodust.makeRamen.module.text;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScoreText {

    private final BitmapFont font;
    private String text;
    private float x, y;
    private Rectangle bounds;
    private final Camera camera;

    public ScoreText(BitmapFont font, String text, float x, float y, Camera camera) {
        this.font = font;
        this.text = text;
        this.x = x;
        this.y = y;
        this.camera = camera;
        this.font.getData().setScale(3f);

    }

    public void draw(com.badlogic.gdx.graphics.g2d.Batch batch) {
        font.draw(batch, text, x, y);
    }
}
