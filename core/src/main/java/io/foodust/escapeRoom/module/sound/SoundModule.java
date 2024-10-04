package io.foodust.escapeRoom.module.sound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class SoundModule {
    public SoundModule() {

    }

    public Sound makeSound(String path) {
        return Gdx.audio.newSound(Gdx.files.internal(path));
    }

    public Music makeMusic(String path) {
        return Gdx.audio.newMusic(Gdx.files.internal(path));
    }

    public Music makeMusic(String path, Boolean loop) {
        Music music = Gdx.audio.newMusic(Gdx.files.internal(path));
        music.setLooping(loop);
        return music;
    }
}
