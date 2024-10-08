package io.foodust.makeRamen.module.sound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import io.foodust.makeRamen.object.ObjectManager;

public class SoundModule {
    private final String rootPath = "sound/";

    public SoundModule() {
    }

    public Sound makeSound(String path) {
        Sound sound = Gdx.audio.newSound(Gdx.files.internal(rootPath + path));
        sound.setVolume(80, ObjectManager.interactionVolume);
        return sound;
    }

    public Music makeMusic(String path) {
        Music music = Gdx.audio.newMusic(Gdx.files.internal(rootPath + path));
        music.setVolume(ObjectManager.musicVolume);
        return music;
    }

    public Music makeMusic(String path, Boolean loop) {
        Music music = makeMusic(rootPath + path);
        music.setLooping(loop);
        return music;
    }
}
