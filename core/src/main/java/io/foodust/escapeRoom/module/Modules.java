package io.foodust.escapeRoom.module;

import io.foodust.escapeRoom.module.image.SpriteModule;
import io.foodust.escapeRoom.module.image.TextureModule;
import io.foodust.escapeRoom.module.sound.SoundModule;
import lombok.Getter;

@Getter
public class Modules {

    private final SpriteModule spriteModule;
    private final InputModule inputModule;
    private final TextureModule textureModule;
    private final SoundModule soundModule;

    public Modules() {
        this.spriteModule = new SpriteModule();
        this.inputModule = new InputModule();
        this.textureModule = new TextureModule();
        this.soundModule = new SoundModule();
    }
}
