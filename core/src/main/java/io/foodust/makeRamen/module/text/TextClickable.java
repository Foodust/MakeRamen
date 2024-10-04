package io.foodust.makeRamen.module.text;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TextClickable {
    private final BitmapFont font;
    private String text;
    private float x, y;
    private Rectangle bounds;
    private final GlyphLayout glyphLayout;
    private final Camera camera;

    public TextClickable(BitmapFont font, String text, float x, float y, Camera camera) {
        this.font = font;
        this.text = text;
        this.x = x;
        this.y = y;
        this.camera = camera;
        this.glyphLayout = new GlyphLayout();
        updateBounds();
    }

    public void updateBounds() {
        glyphLayout.setText(font, text);
        float textWidth = glyphLayout.width;
        float textHeight = glyphLayout.height;
        bounds = new Rectangle(x, y - textHeight, textWidth, textHeight);
    }

    public void draw(com.badlogic.gdx.graphics.g2d.Batch batch) {
        font.draw(batch, text, x, y);
    }

    public boolean isClicked() {
        if (Gdx.input.justTouched()) {
            Vector3 touchPoint = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPoint);
            return bounds.contains(touchPoint.x, touchPoint.y);
        }
        return false;
    }
}
