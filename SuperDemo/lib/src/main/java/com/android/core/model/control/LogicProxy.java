package com.android.core.model.control;


import com.android.core.model.annotation.Implement;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LogicProxy {
    private static final LogicProxy m_instance = new LogicProxy();

    public static LogicProxy getInstance() {
        return m_instance;
    }

    private LogicProxy() {
        m_objects = new HashMap<>();
    }

    private Map<Class, Object> m_objects;

    public void init(Class... clss) {
        List<Class> list = new LinkedList<Class>();
        for (Class cls : clss) {
            if (cls.isAnnotationPresent(Implement.class)) {
                list.add(cls);
                for (Annotation ann : cls.getDeclaredAnnotations()) {
                    if (ann instanceof Implement) {
                        try {
                            m_objects.put(cls, ((Implement) ann).value().newInstance());
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public <T> T getProxy(Class cls) {
        return (T) m_objects.get(cls);
    }

    public <T> T bind(Class cls, Object o) {
        Object ret = m_objects.get(cls);
        ((BaseLogic) ret).attachView(o);
        return (T) ret;
    }
}
