package com.riverstone.unknown303.framework.components;

import java.util.ArrayList;
import java.util.List;

public class Pool<T> {
    private final List<T> freeObjects;
    private final PoolObjectFactory<T> factory;
    private final int maxSize;

    public interface PoolObjectFactory<T> {
        public T createObject();
    }

    public Pool(PoolObjectFactory<T> factory, int maxSize) {
        this.factory = factory;
        this.maxSize = maxSize;
        this.freeObjects = new ArrayList<>(maxSize);
    }

    public T newObject() {
        T object = null;
        if (freeObjects.size() == 0) {
            object = factory.createObject();
        } else {
            object = freeObjects.remove(freeObjects.size() - 1);
        }
        return object;
    }

    public void free(T object) {
        if (freeObjects.size() < maxSize) {
            freeObjects.add(object);
        }
    }
}
