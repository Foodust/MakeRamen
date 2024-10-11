package io.foodust.makeRamen.module;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseModule {
    public final List<Object> objects = new ArrayList<>();
    public abstract void dispose();
}
