package io.foodust.escapeRoom.object;

import io.foodust.escapeRoom.module.Modules;
import io.foodust.escapeRoom.object.object.BaseObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ObjectManager {
    private List<BaseObject> objects = new ArrayList<>();
    private Modules modules = new Modules();
    private static ObjectManager instance;
    public static Float X = 800f;
    public static Float Y = 420f;

    public static ObjectManager getInstance() {
        if (instance == null) {
            instance = new ObjectManager();
        }
        return instance;
    }
}
