package io.foodust.makeRamen.module;

import io.foodust.makeRamen.module.image.SpriteModule;
import io.foodust.makeRamen.module.image.TextureModule;
import io.foodust.makeRamen.module.input.InputModule;
import io.foodust.makeRamen.module.object.ObjectModule;
import io.foodust.makeRamen.module.sound.SoundModule;
import lombok.Getter;

@Getter
public class Modules {

    private final SpriteModule spriteModule;
    private final InputModule inputModule;
    private final TextureModule textureModule;
    private final SoundModule soundModule;
    private final ObjectModule objectModule;

    public Modules() {
        this.spriteModule = new SpriteModule();
        this.inputModule = new InputModule();
        this.textureModule = new TextureModule();
        this.soundModule = new SoundModule();
        this.objectModule = new ObjectModule();
    }
}
