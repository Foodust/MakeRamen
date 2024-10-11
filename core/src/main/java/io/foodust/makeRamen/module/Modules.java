package io.foodust.makeRamen.module;

import io.foodust.makeRamen.module.image.AnimationModule;
import io.foodust.makeRamen.module.image.SpriteModule;
import io.foodust.makeRamen.module.image.TextureModule;
import io.foodust.makeRamen.module.input.InputModule;
import io.foodust.makeRamen.module.sound.SoundModule;
import io.foodust.makeRamen.module.text.FontManager;
import lombok.Getter;

@Getter
public class Modules {

    private final SpriteModule spriteModule;
    private final InputModule inputModule;
    private final TextureModule textureModule;
    private final SoundModule soundModule;
    private final AnimationModule animationModule;
    private final FontManager fontManager;

    public Modules() {
        this.spriteModule = new SpriteModule();
        this.inputModule = new InputModule();
        this.textureModule = new TextureModule();
        this.soundModule = new SoundModule();
        this.animationModule = new AnimationModule();
        this.fontManager = new FontManager();
    }
}
