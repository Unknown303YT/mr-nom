package com.riverstone.unknown303.framework.components.input;

import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;

import com.riverstone.unknown303.framework.components.Pool;
import com.riverstone.unknown303.framework.components.base.input.Input;
import com.riverstone.unknown303.framework.components.base.input.Input.TypeEvent;

import java.util.ArrayList;
import java.util.List;


public class KeyboardHandler implements OnKeyListener {
    boolean[] pressedKeys = new boolean[128];
    Pool<TypeEvent> keyEventPool;
    List<TypeEvent> typeEventsBuffer = new ArrayList<TypeEvent>();
    List<TypeEvent> typeEvents = new ArrayList<TypeEvent>();

    public KeyboardHandler(View view) {
        Pool.PoolObjectFactory<TypeEvent> factory = new Pool.PoolObjectFactory<TypeEvent>() {
            @Override
            public TypeEvent createObject() {
                return new TypeEvent();
            }
        };
        keyEventPool = new Pool<TypeEvent>(factory, 100);
        view.setOnKeyListener(this);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
    }


    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_MULTIPLE) {
            return false;
        }
        synchronized (this) {
            TypeEvent typeEvent = keyEventPool.newObject();
            typeEvent.keyCode = keyCode;
            typeEvent.keyChar = (char) event.getUnicodeChar();
            if (event.getAction() == KeyEvent.ACTION_DOWN) {
                typeEvent.type = TypeEvent.KEY_DOWN;
                if (keyCode > 0 && keyCode <127) {
                    pressedKeys[keyCode] = true;
                }
            } if (event.getAction() == KeyEvent.ACTION_UP) {
                typeEvent.type = TypeEvent.KEY_UP;
                if (keyCode > 0 && keyCode < 127) {
                    pressedKeys[keyCode] = false;
                }
            }
            typeEventsBuffer.add(typeEvent);
        }
        return false;
    }

    public boolean isKeyPressed(int keyCode) {
        if (keyCode < 0 || keyCode > 127) {
            return false;
        }
        return pressedKeys[keyCode];
    }

    public List<TypeEvent> getKeyEvents() {
        synchronized (this) {
            int len = typeEvents.size();
            for (int i = 0; i < len; i++) {
                keyEventPool.free(typeEvents.get(i));
            }
            typeEvents.clear();
            typeEvents.addAll(typeEventsBuffer);
            typeEventsBuffer.clear();
            return typeEvents;
        }
    }
}
