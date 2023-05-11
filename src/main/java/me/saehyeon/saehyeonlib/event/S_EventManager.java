package me.saehyeon.saehyeonlib.event;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

public class S_EventManager {
    static S_EventManager instance;

    public static S_EventManager getInstance() {
        if(instance == null)
            instance = new S_EventManager();

        return instance;
    }

    ArrayList<SaehyeonListener> listeners = new ArrayList<>();
    public void addEventListener(SaehyeonListener clazz) {
        if(!listeners.contains(clazz))
            listeners.add(clazz);
    }

    public void removeEventListener(SaehyeonListener clazz) {
        listeners.remove(clazz);
    }

    /**
     * 이벤트를 발동시킵니다.
     */
    public void trigger(Object obj) {
        for(SaehyeonListener clazz : listeners) {
            for(Method method : clazz.getClass().getDeclaredMethods()) {

                if(Arrays.asList(method.getAnnotations()).contains(SaehyeonEventHandler.class)) {
                    if(method.getParameters().length == 1 && Arrays.asList(method.getParameterTypes()).contains(obj.getClass())) {

                        try {
                            method.invoke(obj.getClass(),obj);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }
    }
}
