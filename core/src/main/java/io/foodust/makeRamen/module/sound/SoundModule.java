package io.foodust.makeRamen.module.sound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import io.foodust.makeRamen.module.BaseModule;
import io.foodust.makeRamen.object.ObjectManager;

public class SoundModule extends BaseModule {


    public SoundModule() {
    }

    public Music makeSound(String path) {
        return makeSound(path, ObjectManager.soundVolume);
    }

    public Music makeSound(String path, Float volume) {
        return makeSound(path, volume, false);
    }

    public Music makeSound(String path, Boolean loop) {
        return makeSound(path, ObjectManager.soundVolume, loop);
    }

    public Music makeSound(String path, Float volume, Boolean loop) {
        String rootPath = "sound/";
        Music audio = Gdx.audio.newMusic(Gdx.files.internal(rootPath + path));
        audio.setVolume(volume);
        audio.setLooping(loop);
        audio.play();
        return audio;
    }

    @Override
    public void dispose() {

    }
}
