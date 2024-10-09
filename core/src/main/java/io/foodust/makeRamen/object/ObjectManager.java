package io.foodust.makeRamen.object;

import io.foodust.makeRamen.module.Modules;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ObjectManager {
    private Modules modules = new Modules();
    private static ObjectManager instance;
    public static Float X = 1920f;
    public static Float Y = 1080f;
    public static Float soundVolume = 0.6f;

    public static ObjectManager getInstance() {
        if (instance == null) {
            instance = new ObjectManager();
        }
        return instance;
    }
}
